package be.rdhaese.packetdelivery.back_end.mapper.impl;

import be.rdhaese.packetdelivery.back_end.mapper.DeliveryAddressMapper;
import be.rdhaese.packetdelivery.back_end.mapper.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.dto.DeliveryAddressDTO;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 16/01/2016.
 *
 * @author Robin D'Haese
 */
@Component
public class DeliveryAddressMapperImpl implements DeliveryAddressMapper {

    @Autowired
    private Mapper<Region, RegionDTO> regionMapper;

    @Override
    public Object[] mapToBus(DeliveryAddressDTO dto) {
        Object[] objects = new Object[3];

        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setNumber(dto.getNumber());
        address.setMailbox(dto.getMailbox());
        address.setCity(dto.getCity());
        address.setPostalCode(dto.getPostalCode());

        RegionDTO regionDTO = new RegionDTO(dto.getRegionName(), dto.getRegionCode());
        Region region = regionMapper.mapToBus(regionDTO);

        objects[0] = dto.getPacketId();
        objects[1] = address;
        objects[2] = region;
        return objects;
    }

    @Override
    public DeliveryAddressDTO mapToDto(Address busObj, Region region, String packetId) {
        DeliveryAddressDTO dto = new DeliveryAddressDTO();
        dto.setPacketId(packetId);
        dto.setStreet(busObj.getStreet());
        dto.setNumber(busObj.getNumber());
        dto.setMailbox(busObj.getMailbox());
        dto.setCity(busObj.getCity());
        dto.setPostalCode(busObj.getPostalCode());
        dto.setRegionName(region.getName());
        dto.setRegionCode(region.getRegionCode());
        return dto;
    }
}