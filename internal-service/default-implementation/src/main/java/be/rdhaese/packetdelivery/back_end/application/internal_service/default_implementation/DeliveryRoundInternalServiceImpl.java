package be.rdhaese.packetdelivery.back_end.application.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.application.internal_service.interfaces.DeliveryRoundInternalService;
import be.rdhaese.packetdelivery.back_end.application.internal_service.util.RegionWithPriority;
import be.rdhaese.packetdelivery.back_end.application.model.*;
import be.rdhaese.packetdelivery.back_end.application.persistence.jpa_repositories.DeliveryRoundJpaRepository;
import be.rdhaese.packetdelivery.back_end.application.persistence.jpa_repositories.PacketJpaRepository;
import be.rdhaese.packetdelivery.back_end.application.persistence.jpa_repositories.RegionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.reflect.Reflection;

import java.util.*;

/**
 * Created on 26/02/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class DeliveryRoundInternalServiceImpl implements DeliveryRoundInternalService {

    @Autowired
    private DeliveryRoundJpaRepository roundRepository;

    @Autowired
    private PacketJpaRepository packetRepository;

    @Autowired
    private RegionJpaRepository regionRepository;

    @Autowired
    @Qualifier("packetOnPriorityComparator")
    private Comparator<Packet> packetOnPriorityComparator;

    @Autowired
    @Qualifier("packetOnStatusChangedComparator")
    private Comparator<Packet> packetOnStatusChangedComparator;

    @Autowired
    @Qualifier("regionWithPriorityOnPriorityComparator")
    private Comparator<RegionWithPriority> regionWithPriorityOnPriorityComparator;

    @Autowired
    @Qualifier("regionWithPriorityOnPacketCountComparator")
    private Comparator<RegionWithPriority> regionWithPriorityOnPacketCountComparator;

    @Autowired
    @Qualifier("regionWithPriorityOnRegionNameComparator")
    private Comparator<RegionWithPriority> regionWithPriorityOnRegionNameComparator;

    @Override
    public Long createNewRound(int amountOfPackets) {
        //Get region with highest priority
        Region regionWithHighestPriority =
                getRegionWithHighestTotalPriority(regionRepository.findAll());

        //If it is null, there are no packets -> return -1L
        if (regionWithHighestPriority == null) {
            return -1L;
        }

        //Get packets that need to be delivered in that region
        List<Packet> packetsForRegionWithHighestPriority = new ArrayList<>(
                packetRepository.getForRegion(regionWithHighestPriority.getId()));

        //If there are enough packets to fulfill the request, we can create the round now
        if (packetsForRegionWithHighestPriority.size() >= amountOfPackets) {
            return createRound(amountOfPackets, packetsForRegionWithHighestPriority);
        }

        //
        //There are not enough packets -> supplement with packets from adjacent region with highest priority
        //

        //Get the adjacent region with the highest priority
        Region adjacentRegionWithHighestPriority = getRegionWithHighestTotalPriority(regionWithHighestPriority.getAdjacentRegions());

        //If adjacentRegionWithHighestPriority is null, then there are no extra packets to add
        if (adjacentRegionWithHighestPriority == null) {
            //We can create the round with the packets we have and fulfill the request partially
            return createRound(amountOfPackets, packetsForRegionWithHighestPriority);
        }

        //Get packets that need to be delivered in that region
        List<Packet> packetsForAdjacentRegionWithHighestPriority = new ArrayList<>(
                packetRepository.getForRegion(adjacentRegionWithHighestPriority.getId()));

        //Merge the lists together in packetsForRegionWithHighestPriority
        mergeLists(amountOfPackets, packetsForRegionWithHighestPriority, packetsForAdjacentRegionWithHighestPriority);

        //Create the round with all the packets from previous processing
        return createRound(amountOfPackets, packetsForRegionWithHighestPriority);
    }

    private void mergeLists(int amountOfPackets, List<Packet> packetsForRegionWithHighestPriority, List<Packet> packetsForAdjacentRegionWithHighestPriority) {
        //If there are too much packets, we need only the ones with the highest priority from the adjacent region
        if (packetsForRegionWithHighestPriority.size() + packetsForAdjacentRegionWithHighestPriority.size() > amountOfPackets) {
            //Sort the list on priority and if needed on status changed
            Collections.sort(packetsForAdjacentRegionWithHighestPriority,
                    packetOnPriorityComparator
                            .thenComparing(
                                    packetOnStatusChangedComparator));

            //Only add the amount of packets from the adjacent region that are needed to fulfill the request
            packetsForRegionWithHighestPriority.addAll(
                    packetsForAdjacentRegionWithHighestPriority
                            .subList(0,
                                    amountOfPackets - packetsForRegionWithHighestPriority.size() - 1));

        } else {
            //If there are not too much packets, both collections can just be added together
            packetsForRegionWithHighestPriority.addAll(
                    packetsForAdjacentRegionWithHighestPriority);
        }
    }

    private Long createRound(int amountOfPackets, List<Packet> packetsForRegionWithHighestPriority) {
        //Sort the list on priority and if needed on status changed
        Collections.sort(packetsForRegionWithHighestPriority,
                packetOnPriorityComparator
                        .thenComparing(
                                packetOnStatusChangedComparator));

        //Truncate the list to the requested amount if needed
        if (packetsForRegionWithHighestPriority.size() > amountOfPackets) {
            packetsForRegionWithHighestPriority =
                    packetsForRegionWithHighestPriority.subList(0, amountOfPackets - 1);
        }

        //Create the DeliveryRound with the packets
        DeliveryRound deliveryRound = new DeliveryRound();
        deliveryRound.setRoundStatus(RoundStatus.NOT_STARTED);
        deliveryRound.setPackets(packetsForRegionWithHighestPriority);

        //Change status of packets to
        for (Packet packet : deliveryRound.getPackets()) {
            packet.setPacketStatus(PacketStatus.ON_DELIVERY);
        }

        //Save the deliveryRound, changes to the packets are cascaded (= also saved)
        roundRepository.save(deliveryRound);

        //Return the id that was assigned by the persistence context
        return deliveryRound.getId();
    }

    private Region getRegionWithHighestTotalPriority(Collection<Region> regions) {
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

        if ((regionsWithPriority.isEmpty()) || (allPrioritiesAreZero(regionsWithPriority))) {
            //If there are no regions or all priorities are zero (= no packets to deliver):
            return null;
        }

        //Sort the list on priority, packet count and region name
        Collections.sort(regionsWithPriority,
                regionWithPriorityOnPriorityComparator
                        .thenComparing(
                                regionWithPriorityOnPacketCountComparator
                                        .thenComparing(
                                                regionWithPriorityOnRegionNameComparator)));

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

    @Override
    public List<Packet> getPackets(Long roundId) {
        List<Packet> packets = roundRepository.getOne(roundId).getPackets();
        //TODO let google sort determine best order to deliver
        return packets;
    }

    @Override
    public Boolean markAsLost(Long roundId, Packet packet) {
        DeliveryRound round = roundRepository.getOne(roundId);
        round.getPackets().remove(packet);
        packet = packetRepository.getPacket(packet.getPacketId());
        packet.setPacketStatus(PacketStatus.NOT_FOUND);
        roundRepository.flush();
        packetRepository.flush();
        return true;
    }
}
