package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.comparator;

import be.rdhaese.packetdelivery.back_end.model.Packet;
import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 * Created on 3/04/2016.
 *
 * @author Robin D'Haese
 */
@Component("packetOnPriorityComparator")
public class PacketOnPriorityComparator implements Comparator<Packet> {

    @Override
    public int compare(Packet o1, Packet o2) {
        return Integer.compare(o2.getPriority(), o1.getPriority());
    }
}
