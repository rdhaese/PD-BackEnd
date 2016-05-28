package be.rdhaese.packetdelivery.back_end.web_service.interfaces;

import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;

/**
 *
 * @author Robin D'Haese
 */
public interface LongLatWebService {

    LongLatDTO getForAddress(AddressDTO addressDTO) throws Exception;
}
