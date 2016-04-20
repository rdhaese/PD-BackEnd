package be.rdhaese.packetdelivery.application.back_end.internal_service.comparator;

import be.rdhaese.packetdelivery.application.back_end.internal_service.util.RegionWithPriority;
import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 * Created on 3/04/2016.
 *
 * @author Robin D'Haese
 */
@Component("regionWithPriorityOnPriorityComparator")
public class RegionWithPriorityOnPriorityComparator implements Comparator<RegionWithPriority> {

    @Override
    public int compare(RegionWithPriority o1, RegionWithPriority o2) {
        return Integer.compare(o2.getPriority(), o1.getPriority());
    }
}
