package be.rdhaese.packetdelivery.back_end.application.internal_service.comparator;

import be.rdhaese.packetdelivery.back_end.application.internal_service.util.RegionWithPriority;
import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 * Created on 3/04/2016.
 *
 * @author Robin D'Haese
 */
@Component("regionWithPriorityOnRegionNameComparator")
public class RegionWithPriorityOnRegionNameComparator implements Comparator<RegionWithPriority> {
    @Override
    public int compare(RegionWithPriority o1, RegionWithPriority o2) {
        return o1.getRegion().getName().compareTo(o2.getRegion().getName());
    }
}
