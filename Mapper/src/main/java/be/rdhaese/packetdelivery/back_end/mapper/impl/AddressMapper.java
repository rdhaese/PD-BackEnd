package be.rdhaese.packetdelivery.back_end.mapper.impl;

import be.rdhaese.packetdelivery.back_end.mapper.AbstractMapper;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import org.springframework.stereotype.Component;

/**
 * Created on 21/02/2016.
 *
 * @author Robin D'Haese
 */
@Component
public class AddressMapper extends AbstractMapper<Address, AddressDTO> {
    @Override
    public Address mapToBus(AddressDTO dto) {
        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setNumber(dto.getNumber());
        address.setMailbox(dto.getMailbox());
        address.setPostalCode(dto.getPostalCode());
        address.setCity(dto.getCity());
        return address;
    }

    @Override
    public AddressDTO mapToDto(Address busObj) {
       AddressDTO dto = new AddressDTO();
        dto.setStreet(busObj.getStreet());
        dto.setNumber(busObj.getNumber());
        dto.setMailbox(busObj.getMailbox());
        dto.setPostalCode(busObj.getPostalCode());
        dto.setCity(busObj.getCity());
        return dto;
    }
}
