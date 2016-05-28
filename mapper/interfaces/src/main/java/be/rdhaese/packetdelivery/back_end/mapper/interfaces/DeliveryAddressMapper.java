package be.rdhaese.packetdelivery.back_end.mapper.interfaces;

import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.dto.DeliveryAddressDTO;

/**
 *
 * @author Robin D'Haese
 */
public interface DeliveryAddressMapper {

    Object[] mapToBus(DeliveryAddressDTO dto);

    DeliveryAddressDTO mapToDto(Address busObj, Region region, String packetId);
}
