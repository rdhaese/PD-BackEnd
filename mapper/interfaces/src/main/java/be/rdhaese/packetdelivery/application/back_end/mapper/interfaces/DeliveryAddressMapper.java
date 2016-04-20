package be.rdhaese.packetdelivery.application.back_end.mapper.interfaces;

import be.rdhaese.packetdelivery.application.back_end.model.Region;
import be.rdhaese.packetdelivery.application.back_end.model.Address;
import be.rdhaese.packetdelivery.dto.DeliveryAddressDTO;

/**
 * Created on 16/01/2016.
 *
 * @author Robin D'Haese
 */
public interface DeliveryAddressMapper {

    Object[] mapToBus(DeliveryAddressDTO dto);
    DeliveryAddressDTO mapToDto(Address busObj, Region region, String packetId);
}
