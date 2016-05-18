package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.properties.InternalServiceProperties;
import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util.*;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.DeliveryRoundInternalService;
import be.rdhaese.packetdelivery.back_end.model.*;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.DeliveryRoundJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.PacketJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.RegionJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.CompanyContactDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created on 26/02/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class DeliveryRoundInternalServiceImpl implements DeliveryRoundInternalService {

    private static final Integer MAX_PRIORITY = 3;

    @Autowired
    private DeliveryRoundJpaRepository roundRepository;

    @Autowired
    private PacketJpaRepository packetRepository;

    @Autowired
    private RegionJpaRepository regionRepository;

    @Autowired
    private CompanyContactDetailsRepository companyContactDetailsRepository;

    @Autowired
    private DeliveryRoundCreator deliveryRoundCreator;

    @Autowired
    private RegionWithPriorityUtil regionWithPriorityUtil;

    @Autowired
    private PacketsListMerger packetsListMerger;

    @Autowired
    private DeliveryOrderResolver deliveryOrderResolver;

    @Autowired
    private Mailer mailer;

    @Autowired
    private MailContentLoader mailContentLoader;

    @Autowired
    private TagReplacer tagReplacer;

    @Autowired
    private InternalServiceProperties properties;


    @Override
    @Transactional
    public Long createNewRound(int amountOfPackets) {
        //Get region with highest priority
        Region regionWithHighestPriority =
                regionWithPriorityUtil.getRegionWithHighestTotalPriority(regionRepository.findAll());

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
        Region adjacentRegionWithHighestPriority = regionWithPriorityUtil.getRegionWithHighestTotalPriority(regionWithHighestPriority.getAdjacentRegions());

        //If adjacentRegionWithHighestPriority is null, then there are no extra packets to add
        if (adjacentRegionWithHighestPriority == null) {
            //We can create the round with the packets we have and fulfill the request partially
            return createRound(amountOfPackets, packetsForRegionWithHighestPriority);
        }

        //Get packets that need to be delivered in that region
        List<Packet> packetsForAdjacentRegionWithHighestPriority = new ArrayList<>(
                packetRepository.getForRegion(adjacentRegionWithHighestPriority.getId()));

        //Merge the lists together in packetsForRegionWithHighestPriority
        packetsListMerger.mergeLists(amountOfPackets, packetsForRegionWithHighestPriority, packetsForAdjacentRegionWithHighestPriority);

        //Create the round with all the packets from previous processing
        return createRound(amountOfPackets, packetsForRegionWithHighestPriority);
    }

    private Long createRound(int amountOfPackets, List<Packet> packetsForRegionWithHighestPriority) {
        //Create delivery round
        DeliveryRound deliveryRound = deliveryRoundCreator.createRound(amountOfPackets, packetsForRegionWithHighestPriority);

        //Save the deliveryRound, changes to the packets are cascaded (= also saved)
        roundRepository.save(deliveryRound);

        //Return the id that was assigned by the persistence context
        return deliveryRound.getId();
    }

    @Override
    public List<Packet> getPackets(Long roundId) throws Exception {
        //Get round for roundId
        DeliveryRound round = roundRepository.getOne(roundId);
        //Get company address
        Address companyAddress = companyContactDetailsRepository.get().getAddress();

        return deliveryOrderResolver.sort(companyAddress, round.getPackets());
    }

    @Override
    @Transactional
    public Boolean markAsLost(Long roundId, Packet packet) throws Exception {
        DeliveryRound round = roundRepository.getOne(roundId);
        round.getPackets().remove(packet);
        packet = packetRepository.getPacket(packet.getPacketId());
        packet.setPacketStatus(PacketStatus.NOT_FOUND);
        roundRepository.flush();
        packetRepository.flush();

        //mail to stakeholders
        String subject = properties.getPacketLostSubject();
        String message = tagReplacer.replaceTags(mailContentLoader.getLostMail(), getDefaultTagReplacementMap(packet.getPacketId()));
        mailer.send(packet.getClientInfo().getContactDetails().getEmails().get(0), subject, message);
        mailer.send(packet.getDeliveryInfo().getClientInfo().getContactDetails().getEmails().get(0), subject, message);

        //Return true if application makes it to here
        return true;
    }

    @Override
    @Transactional
    public Boolean endRound(Long roundId) {
        roundRepository.delete(roundId);

        //Return true if application makes it to here
        return true;
    }

    @Override
    @Transactional
    public Boolean startRound(Long roundId) throws Exception {
        DeliveryRound deliveryRound = roundRepository.getOne(roundId);
        deliveryRound.setRoundStatus(RoundStatus.STARTED);
        roundRepository.flush();

        //Send mail to stakeholders of packets
        String subject = properties.getPacketDepartedSubject();
        for (Packet packet : deliveryRound.getPackets()) {
            String message = tagReplacer.replaceTags(mailContentLoader.getDepartedMail(), getDefaultTagReplacementMap(packet.getPacketId()));
            mailer.send(packet.getClientInfo().getContactDetails().getEmails().get(0), subject, message);
            mailer.send(packet.getDeliveryInfo().getClientInfo().getContactDetails().getEmails().get(0), subject, message);
        }

        //Return true if application makes it to here
        return true;
    }

    @Override
    @Transactional
    public Boolean addRemark(Long roundId, String remark) {
        DeliveryRound round = roundRepository.getOne(roundId);
        Remark newRemark = new Remark();
        newRemark.setRemark(remark);
        newRemark.setTimeAdded(new Date());
        round.getRemarks().add(newRemark);
        roundRepository.flush();

        //Return true if application makes it to here
        return true;
    }

    @Override
    @Transactional
    public Boolean cannotDeliver(Long roundId, Packet packet, String reason) throws Exception {
        //First get email addresses from packet to use after they are deleted
        String emailClient = packet.getClientInfo().getContactDetails().getEmails().get(0);
        String emailDelivery = packet.getDeliveryInfo().getClientInfo().getContactDetails().getEmails().get(0);
        //Same for packetId
        String packetId = packet.getPacketId();

        //Remove packet from round
        DeliveryRound deliveryRound = roundRepository.getOne(roundId);
        deliveryRound.getPackets().remove(packet);
        roundRepository.flush();
        //Increase priority of packet
        //If priority > MAX_PRIORITY -> set status to PROBLEMATIC
        packet = packetRepository.getPacket(packet.getPacketId());
        if (packet.getPriority() >= MAX_PRIORITY) {
            packet.setPacketStatus(PacketStatus.PROBLEMATIC);
        } else {
            packet.setPriority(packet.getPriority() + 1);
        }
        packetRepository.flush();

        //mail reason to stakeholders
        String subject = properties.getPacketNotDeliveredSubject();
        Map<String, String> tagReplacementMap = getDefaultTagReplacementMap(packetId);
        tagReplacementMap.put("reason", reason);
        String message = tagReplacer.replaceTags(mailContentLoader.getNotDeliveredMail(), tagReplacementMap);
        mailer.send(emailClient, subject, message);
        mailer.send(emailDelivery, subject, message);

        //Return true if application makes it to here
        return true;
    }

    @Override
    @Transactional
    public Boolean deliver(Long roundId, Packet packet) throws Exception {
        //First get email addresses from packet to use after they are deleted
        String emailClient = packet.getClientInfo().getContactDetails().getEmails().get(0);
        String emailDelivery = packet.getDeliveryInfo().getClientInfo().getContactDetails().getEmails().get(0);
        //Same for packetId
        String packetId = packet.getPacketId();

        //Remove packet from round
        DeliveryRound deliveryRound = roundRepository.getOne(roundId);
        deliveryRound.getPackets().remove(packet);
        roundRepository.flush();

        //Remove packet from system
        packet = packetRepository.getPacket(packet.getPacketId());
        packetRepository.delete(packet);

        //mail to stakeholders
        String subject = properties.getPacketDeliveredSubject();
        String message = tagReplacer.replaceTags(mailContentLoader.getDeliveredMail(), getDefaultTagReplacementMap(packetId));
        mailer.send(emailClient, subject, message);
        mailer.send(emailDelivery, subject, message);

        //Return true if application makes it to here
        return true;
    }

    @Override
    @Transactional
    public Boolean addLocationUpdate(Long roundId, LongLat longLat) {
        //Create LocationUpdate for LongLat
        LocationUpdate locationUpdate = new LocationUpdate();
        locationUpdate.setLongLat(longLat);

        //Get round
        DeliveryRound round = roundRepository.getOne(roundId);

        //Add LocationUpdate to it
        round.getLocationUpdates().add(locationUpdate);
        roundRepository.flush();

        //Return true if application makes it to here
        return true;
    }

    @Override
    public Address getCompanyAddress() throws Exception {
        return companyContactDetailsRepository.get().getAddress();
    }

    private Map<String, String> getDefaultTagReplacementMap(String packetId) {
        Map<String, String> tagReplacementMap = new HashMap<>();
        tagReplacementMap.put("packetId", packetId);
        return tagReplacementMap;
    }
}
