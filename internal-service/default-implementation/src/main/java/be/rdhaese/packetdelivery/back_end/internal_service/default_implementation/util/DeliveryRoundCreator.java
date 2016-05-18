package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import be.rdhaese.packetdelivery.back_end.model.DeliveryRound;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.model.PacketStatus;
import be.rdhaese.packetdelivery.back_end.model.RoundStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created on 9/05/2016.
 *
 * @author Robin D'Haese
 */
@Component
public class DeliveryRoundCreator {

    @Autowired
    @Qualifier("packetOnPriorityComparator")
    private Comparator<Packet> packetOnPriorityComparator;

    @Autowired
    @Qualifier("packetOnStatusChangedComparator")
    private Comparator<Packet> packetOnStatusChangedComparator;

    public DeliveryRound createRound(int amountOfPackets, List<Packet> packets) {
        if (amountOfPackets < 1 || packets == null || packets.size() < 1) {
            return null;
        }

        //Sort the list on priority and if needed on status changed
        Collections.sort(packets,
                packetOnPriorityComparator
                        .thenComparing(
                                packetOnStatusChangedComparator));

        //Truncate the list to the requested amount if needed
        if (packets.size() > amountOfPackets) {
            packets =
                    packets.subList(0, amountOfPackets);
        }

        //Create the DeliveryRound with the packets
        DeliveryRound deliveryRound = new DeliveryRound();
        deliveryRound.setRoundStatus(RoundStatus.NOT_STARTED);
        deliveryRound.setPackets(packets);

        //Change status of packets to
        for (Packet packet : deliveryRound.getPackets()) {
            packet.setPacketStatus(PacketStatus.ON_DELIVERY);
        }

        //Return the created round
        return deliveryRound;
    }
}
