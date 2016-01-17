package be.rdhaese.packetdelivery.back_end.mapper;

import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.dto.DeliveryAddressDTO;
import javafx.util.Pair;

import java.util.Map;

/**
 * Created on 16/01/2016.
 *
 * @author Robin D'Haese
 */
public interface DeliveryAddressMapper {

    Object[] mapToBus(DeliveryAddressDTO dto);
    DeliveryAddressDTO mapToDto(Address busObj, Region region, String packetId);
}
