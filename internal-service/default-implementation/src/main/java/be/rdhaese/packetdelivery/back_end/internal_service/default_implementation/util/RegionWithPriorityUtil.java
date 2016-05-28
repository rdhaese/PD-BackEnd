package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.PacketJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 *
 * @author Robin D'Haese
 */
@Component
public class RegionWithPriorityUtil {

    @Autowired
    private PacketJpaRepository packetRepository;

    @Autowired
    @Qualifier("regionWithPriorityOnPriorityComparator")
    private Comparator<RegionWithPriority> regionWithPriorityOnPriorityComparator;

    @Autowired
    @Qualifier("regionWithPriorityOnPacketCountComparator")
    private Comparator<RegionWithPriority> regionWithPriorityOnPacketCountComparator;

    @Autowired
    @Qualifier("regionWithPriorityOnRegionCodeComparator")
    private Comparator<RegionWithPriority> regionWithPriorityOnRegionCodeComparator;

    public Region getRegionWithHighestTotalPriority(Collection<Region> regions) {
        if (regions == null || regions.isEmpty()) {
            return null;
        }

        //Sum priorities...
        List<RegionWithPriority> regionsWithPriority = new ArrayList<>();
        //...for all regions...
        for (Region region : regions) {
            //...their packets
            for (Packet packet : packetRepository.getForRegion(region.getId())) {
                //Get the index of the RegionWithPriority so we can retrieve it
                int index = regionsWithPriority.indexOf(new RegionWithPriority(region));

                RegionWithPriority regionWithPriority;
                if (index == -1) {
                    //Add a new RegionWithPriority if index == -1 (none found)
                    regionWithPriority = new RegionWithPriority(region);
                    regionsWithPriority.add(regionWithPriority);
                } else {
                    //Retrieve the RegionWithPriority
                    regionWithPriority = regionsWithPriority.get(index);
                }
                //Increment packet count (by one)
                regionWithPriority.incrementPacketCount();
                //Increment the priority with the packet its priority
                regionWithPriority.incrementPriority(packet.getPriority());
            }
        }

        if (regionsWithPriority.isEmpty() || allPrioritiesAreZero(regionsWithPriority)) {
            //If there are no regions or all priorities are zero (= no packets to deliver):
            return null;
        }

        //Sort the list on priority, packet count and region name
        Collections.sort(regionsWithPriority,
                regionWithPriorityOnPriorityComparator
                        .thenComparing(
                                regionWithPriorityOnPacketCountComparator
                                        .thenComparing(
                                                regionWithPriorityOnRegionCodeComparator)));

        //Return the first element in the list = region with highest priority thanks to sort
        return regionsWithPriority.get(0).getRegion();
    }

    private boolean allPrioritiesAreZero(List<RegionWithPriority> regionsWithPriority) {
        for (RegionWithPriority regionWithPriority : regionsWithPriority) {
            if (regionWithPriority.getPriority() > 0) {
                //If one of the regions its priority is higher than 0:
                return false;
            }
        }

        //else:
        return true;
    }
}
