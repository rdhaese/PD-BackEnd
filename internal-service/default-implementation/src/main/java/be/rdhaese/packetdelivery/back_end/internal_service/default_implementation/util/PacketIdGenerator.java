package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import be.rdhaese.packetdelivery.back_end.model.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 9/05/2016.
 *
 * @author Robin D'Haese
 */
@Component
public class PacketIdGenerator {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("ddMMyyyy");
    private static final String ID_SEPARATOR = "-";

    @Autowired
    private PacketIdDateKeeper dateKeeper;

    private int previousNumber = 0;

    public String generatePacketId(Packet packet) throws Exception {
        if (packet == null){
            return null;
        }
        if (previousNumber > 99999){
            throw new Exception("Max of packet ids for a day reached [99999]");
        }
        StringBuilder packetId = new StringBuilder();
        if (packet.getClientInfo().getContactDetails().getName().length() <= 1) {
            packetId.append(packet.getClientInfo().getContactDetails().getName());
            while (packetId.length() < 2) {
                packetId.append("_");
            }
        } else {
            packetId.append(packet.getClientInfo().getContactDetails().getName().substring(0, 2));
        }
        packetId.append(ID_SEPARATOR);
        if (packet.getDeliveryInfo().getClientInfo().getContactDetails().getName().length() <= 1) {
            packetId.append(packet.getDeliveryInfo().getClientInfo().getContactDetails().getName());
            while (packetId.length() < 5) {
                packetId.append("_");
            }
        } else {
            packetId.append(packet.getDeliveryInfo().getClientInfo().getContactDetails().getName().substring(0, 2));
        }
        packetId.append(ID_SEPARATOR)
                .append(DATE_FORMAT.format(packet.getStatusChangedOn()))
                .append(ID_SEPARATOR)
                .append(getLast5Numbers(packet.getStatusChangedOn()));

       return packetId.toString().toUpperCase();
    }

    private StringBuilder getLast5Numbers(Date date) {
        StringBuilder numbers = new StringBuilder();
        if(dateKeeper.isAfterLastDateChecked(date)){
            previousNumber = 0;
        }
        numbers.append(previousNumber);
        while (numbers.length() < 5) {
            numbers.insert(0, 0);
        }
        previousNumber++;
        return numbers;
    }
}
