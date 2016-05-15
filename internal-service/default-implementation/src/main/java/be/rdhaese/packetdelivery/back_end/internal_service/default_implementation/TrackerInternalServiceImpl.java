package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.LongLatInternalService;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.TrackerInternalService;
import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util.DeliveryOrderResolver;
import be.rdhaese.packetdelivery.back_end.model.*;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.PacketJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.DeliveryRoundJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.CompanyContactDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created on 19/04/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class TrackerInternalServiceImpl implements TrackerInternalService {

    @Autowired
    private CompanyContactDetailsRepository companyContactDetailsRepository;
    @Autowired
    private PacketJpaRepository packetRepository;
    @Autowired
    private DeliveryRoundJpaRepository deliveryRoundRepository;
    @Autowired
    private LongLatInternalService longLatService;
    @Autowired
    private DeliveryOrderResolver deliveryOrderResolver;

    @Override
    public LongLat getCompanyAddress() throws Exception{
            return longLatService.getForAddress(
                    companyContactDetailsRepository.get().getAddress()
            );
    }

    @Override
    public LongLat getPacketAddress(String packetId) throws Exception{
            Packet packet = packetRepository.getPacket(packetId);
            if (packet == null){
                return null;
            }
            return longLatService.getForAddress(
                    packet.getDeliveryInfo().getClientInfo().getAddress()
            );
    }

    @Override
    public Collection<LocationUpdate> getLocationsUpdates(String packetId) {
        DeliveryRound roundForPacket = getRoundFor(packetId);

        if (roundForPacket != null){
            Set<LocationUpdate> locationUpdates = new TreeSet<>(DeliveryRound.LOCATION_UPDATE_COMPARATOR);
            locationUpdates.addAll(roundForPacket.getLocationUpdates());
            return locationUpdates;
        }
        return null;
    }

    @Override
    public Collection<Remark> getRemarks(String packetId) {
        DeliveryRound roundForPacket = getRoundFor(packetId);

        if (roundForPacket != null){
            Set<Remark> remarks = new TreeSet<>(DeliveryRound.REMARK_COMPARATOR);
            remarks.addAll(roundForPacket.getRemarks());
            return remarks;
        }
        return null;
    }

    @Override
    public Integer getAmountOfPacketsLeftBefore(String packetId) throws Exception {
        DeliveryRound roundForPacket = getRoundFor(packetId);
        if (roundForPacket == null){
            return null;
        }

        Address companyAddress = companyContactDetailsRepository.get().getAddress();
        List<Packet> packetsInDeliveryOrder = deliveryOrderResolver.sort(companyAddress, roundForPacket.getPackets());
        for (int index = 0; index < roundForPacket.getPackets().size(); index++){
            if (packetId.equals(packetsInDeliveryOrder.get(index).getPacketId())){
                //Lucky us, the index is equal to the amount of packets left (as long the list of packets is sorted)
                return index;
            }
        }

        //Will normally never reach this, could not have found a round that has no packets...
        return null;
    }

    private DeliveryRound getRoundFor(String packetId){
        Collection<DeliveryRound> ongoingRounds = deliveryRoundRepository.getOngoingRounds();
        for (DeliveryRound deliveryRound : ongoingRounds){
            for (Packet packet : deliveryRound.getPackets()){
                if (packet.getPacketId().equals(packetId)){
                    return deliveryRound;
                }
            }
        }
        return null;
    }
}
