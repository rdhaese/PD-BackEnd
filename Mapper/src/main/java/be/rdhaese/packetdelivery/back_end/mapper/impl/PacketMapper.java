package be.rdhaese.packetdelivery.back_end.mapper.impl;


import be.rdhaese.packetdelivery.back_end.mapper.AbstractMapper;
import be.rdhaese.packetdelivery.back_end.model.*;
import be.rdhaese.packetdelivery.back_end.service.RegionsService;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 24/12/2015.
 *
 * @author Robin D'Haese
 */
@Component
public class PacketMapper extends AbstractMapper<Packet, PacketDTO> {

    @Autowired
    private RegionsService regionsService;

    @Override
    public Packet mapToBus(PacketDTO dto) {
        if (dto == null) {
            return null;
        }
        Packet packet = new Packet();
        packet.setPacketId(dto.getPacketId());
        packet.setClientInfo(mapClientInfo(dto));
        packet.setDeliveryInfo(mapDeliveryInfo(dto));
        if (dto.getPacketStatus() != null) {
            packet.setPacketStatus(PacketStatus.valueOf(dto.getPacketStatus()));
        }
        packet.setStatusChangedOn(dto.getStatusChangedOn());
        return packet;
    }

    private DeliveryInfo mapDeliveryInfo(PacketDTO dto) {
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setClientInfo(mapDeliveryClientInfo(dto));
        deliveryInfo.setRegion(mapRegion(dto.getDeliveryRegionCode()));
        return deliveryInfo;
    }

    private ClientInfo mapDeliveryClientInfo(PacketDTO dto) {
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setAddress(mapDeliveryAddress(dto));
        clientInfo.setContactDetails(mapDeliveryContactDetails(dto));
        return clientInfo;
    }

    private Address mapDeliveryAddress(PacketDTO dto) {
        Address address = new Address();
        address.setStreet(dto.getDeliveryStreet());
        address.setNumber(dto.getDeliveryNumber());
        address.setMailbox(dto.getDeliveryMailbox());
        address.setCity(dto.getDeliveryCity());
        address.setPostalCode(dto.getDeliveryPostalCode());
        return address;
    }

    private ContactDetails mapDeliveryContactDetails(PacketDTO dto) {
        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setName(dto.getDeliveryName());
        contactDetails.addEmail(dto.getDeliveryEmail());
        contactDetails.addPhoneNumber(dto.getDeliveryPhone());
        return contactDetails;
    }

    private Region mapRegion(String regionCode) {
        return regionsService.getRegionFor(regionCode);
    }

    private ClientInfo mapClientInfo(PacketDTO dto) {
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setContactDetails(mapContactDetails(dto));
        clientInfo.setAddress(mapAddress(dto));
        return clientInfo;
    }

    private ContactDetails mapContactDetails(PacketDTO dto) {
        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setName(dto.getClientName());
        contactDetails.addEmail(dto.getClientEmail());
        contactDetails.addPhoneNumber(dto.getClientPhone());
        return contactDetails;
    }

    private Address mapAddress(PacketDTO dto) {
        Address address = new Address();
        address.setStreet(dto.getClientStreet());
        address.setNumber(dto.getClientNumber());
        address.setMailbox(dto.getClientNumber());
        address.setCity(dto.getClientCity());
        address.setPostalCode(dto.getClientPostalCode());
        return address;
    }

    @Override
    public PacketDTO mapToDto(Packet busObj) {
        if (busObj == null){
            return null;
        }
        PacketDTO packetDTO = new PacketDTO();
        mapPacketSpecificPropertiesToDto(busObj, packetDTO);
        mapClientInfoToDto(busObj.getClientInfo(), packetDTO);
        mapDeliveryInfoToDto(busObj.getDeliveryInfo(), packetDTO);
        return packetDTO;
    }

    private void mapPacketSpecificPropertiesToDto(Packet busObj, PacketDTO packetDTO) {
        packetDTO.setPacketId(busObj.getPacketId());
        packetDTO.setPacketStatus(busObj.getPacketStatus().toString());
        packetDTO.setStatusChangedOn(busObj.getStatusChangedOn());
    }

    private void mapDeliveryInfoToDto(DeliveryInfo deliveryInfo, PacketDTO packetDTO) {
        mapDeliveryClientInfoToDto(deliveryInfo.getClientInfo(), packetDTO);
        mapDeliverRegionToDto(deliveryInfo.getRegion(), packetDTO);
    }

    private void mapDeliveryClientInfoToDto(ClientInfo clientInfo, PacketDTO packetDTO) {
        mapDeliverInfoContactDetailsToDto(clientInfo.getContactDetails(), packetDTO);
        mapDeliveryInfoAddressToDto(clientInfo.getAddress(), packetDTO);
    }

    private void mapDeliverRegionToDto(Region region, PacketDTO packetDTO) {
        packetDTO.setDeliverRegionName(region.getName());
        packetDTO.setDeliveryRegionCode(region.getRegionCode());
    }

    private void mapDeliveryInfoAddressToDto(Address address, PacketDTO packetDTO) {
        packetDTO.setDeliveryStreet(address.getStreet());
        packetDTO.setDeliveryNumber(address.getNumber());
        packetDTO.setDeliveryMailbox(address.getMailbox());
        packetDTO.setDeliveryCity(address.getCity());
        packetDTO.setDeliveryPostalCode(address.getPostalCode());
    }

    private void mapDeliverInfoContactDetailsToDto(ContactDetails contactDetails, PacketDTO packetDTO) {
        packetDTO.setDeliveryName(contactDetails.getName());
        packetDTO.setDeliveryPhone(contactDetails.getPhoneNumbers().get(0));
        packetDTO.setDeliveryEmail(contactDetails.getEmails().get(0));
    }

    private void mapClientInfoToDto(ClientInfo clientInfo, PacketDTO packetDTO) {
        mapClientInfoContactDetailsToDto(clientInfo.getContactDetails(), packetDTO);
        mapClientInfoAddressToDto(clientInfo.getAddress(), packetDTO);
    }

    private void mapClientInfoAddressToDto(Address address, PacketDTO packetDTO) {
        packetDTO.setClientStreet(address.getStreet());
        packetDTO.setClientNumber(address.getNumber());
        packetDTO.setClientMailbox(address.getMailbox());
        packetDTO.setClientCity(address.getCity());
        packetDTO.setClientPostalCode(address.getPostalCode());
    }

    private void mapClientInfoContactDetailsToDto(ContactDetails contactDetails, PacketDTO packetDTO) {
        packetDTO.setClientName(contactDetails.getName());
        packetDTO.setClientPhone(contactDetails.getPhoneNumbers().get(0));
        packetDTO.setClientEmail(contactDetails.getPhoneNumbers().get(0));
    }
}
