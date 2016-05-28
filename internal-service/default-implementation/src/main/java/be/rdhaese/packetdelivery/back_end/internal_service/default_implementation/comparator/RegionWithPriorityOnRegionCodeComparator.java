package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.comparator;

import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util.RegionWithPriority;
import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 *
 * @author Robin D'Haese
 */
@Component("regionWithPriorityOnRegionCodeComparator")
public class RegionWithPriorityOnRegionCodeComparator implements Comparator<RegionWithPriority> {
    @Override
    public int compare(RegionWithPriority o1, RegionWithPriority o2) {
        return o1.getRegion().getRegionCode().compareTo(o2.getRegion().getRegionCode());
    }
}
