package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import be.rdhaese.packetdelivery.back_end.model.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Robin D'Haese
 */
@Component
public class PacketsListMerger {

    @Autowired
    @Qualifier("packetOnPriorityComparator")
    private Comparator<Packet> packetOnPriorityComparator;

    @Autowired
    @Qualifier("packetOnStatusChangedComparator")
    private Comparator<Packet> packetOnStatusChangedComparator;

    public List<Packet> mergeLists(int amountOfPackets, List<Packet> packets1, List<Packet> packets2) {
        List<Packet> packets = new ArrayList<>(packets1);
        if (amountOfPackets < 1) {
            return packets;
        }
        //If there are too much packets, we need only the ones with the highest priority from the second list
        if (packets1.size() + packets2.size() > amountOfPackets) {
            //Sort the list on priority and if needed on status changed
            Collections.sort(packets2,
                    packetOnPriorityComparator
                            .thenComparing(
                                    packetOnStatusChangedComparator));

            //Only add the amount of packets from the second list that are needed to fulfill the request
            packets.addAll(
                    packets2
                            .subList(0,
                                    amountOfPackets - packets1.size()));

        } else {
            //If there are not too much packets, both collections can just be added together
            packets.addAll(
                    packets2);
        }
        return packets;
    }
}
