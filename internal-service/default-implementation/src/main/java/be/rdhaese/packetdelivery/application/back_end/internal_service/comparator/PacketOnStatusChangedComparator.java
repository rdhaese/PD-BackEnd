package be.rdhaese.packetdelivery.application.back_end.internal_service.comparator;

import be.rdhaese.packetdelivery.application.back_end.model.Packet;
import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 * Created on 3/04/2016.
 *
 * @author Robin D'Haese
 */
@Component("packetOnStatusChangedComparator")
public class PacketOnStatusChangedComparator implements Comparator<Packet> {
    @Override
    public int compare(Packet o1, Packet o2) {
        return o1.getStatusChangedOn().compareTo(o2.getStatusChangedOn());
    }
}
