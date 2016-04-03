package be.rdhaese.packetdelivery.back_end.application.internal_service.comparator;

import be.rdhaese.packetdelivery.back_end.application.internal_service.util.RegionWithPriority;
import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 * Created on 3/04/2016.
 *
 * @author Robin D'Haese
 */
@Component("regionWithPriorityOnPacketCountComparator")
public class RegionWithPriorityOnPacketCountComparator implements Comparator<RegionWithPriority> {
    @Override
    public int compare(RegionWithPriority o1, RegionWithPriority o2) {
        return Integer.compare(o2.getPacketCount(), o1.getPacketCount());
    }
}
