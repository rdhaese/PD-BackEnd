package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.LongLatInternalService;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.TrackerInternalService;
import be.rdhaese.packetdelivery.back_end.model.*;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.PacketJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.DeliveryRoundJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.CompanyContactDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

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

    @Override
    public LongLat getCompanyAddress() {
        try {
            return longLatService.getForAddress(
                    companyContactDetailsRepository.get().getAddress()
            );
        } catch (Exception e) {
            e.printStackTrace();//TODO handle this
        }
        return null;
    }

    @Override
    public LongLat getPacketAddress(String packetId) {
        try {
            Packet packet = packetRepository.getPacket(packetId);
            return longLatService.getForAddress(
                    packet.getDeliveryInfo().getClientInfo().getAddress()
            );
        } catch (Exception e) {
            e.printStackTrace();//TODO handle this
        }
        return null;
    }

    @Override
    public Collection<LocationUpdate> getLocationsUpdates(String packetId) {
        Collection<DeliveryRound> ongoingRounds = deliveryRoundRepository.getOngoingRounds();
        DeliveryRound roundForPacket = null;
        for (DeliveryRound deliveryRound : ongoingRounds){
            for (Packet packet : deliveryRound.getPackets()){
                if (packet.getPacketId().equals(packetId)){
                    roundForPacket = deliveryRound;
                    break;
                }
            }
            if (roundForPacket != null){
                break;
            }
        }
        if (roundForPacket != null){
            return roundForPacket.getLocationUpdates();
        }
        return null;
    }

    @Override
    public Collection<Remark> getRemarks(String packetId) {
        Collection<DeliveryRound> ongoingRounds = deliveryRoundRepository.getOngoingRounds();
        DeliveryRound roundForPacket = null;
        for (DeliveryRound deliveryRound : ongoingRounds){
            for (Packet packet : deliveryRound.getPackets()){
                if (packet.getPacketId().equals(packetId)){
                    roundForPacket = deliveryRound;
                    break;
                }
            }
            if (roundForPacket != null){
                break;
            }
        }
        if (roundForPacket != null){
            return roundForPacket.getRemarks();
        }
        return null;
    }
}
