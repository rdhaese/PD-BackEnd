package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.comparator;

import be.rdhaese.packetdelivery.back_end.model.Packet;
import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
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
