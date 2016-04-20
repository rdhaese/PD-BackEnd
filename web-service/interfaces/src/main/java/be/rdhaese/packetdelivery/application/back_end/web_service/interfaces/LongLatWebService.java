package be.rdhaese.packetdelivery.application.back_end.web_service.interfaces;

import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;

/**
 * Created on 21/02/2016.
 *
 * @author Robin D'Haese
 */
public interface LongLatWebService {

    LongLatDTO getForAddress(AddressDTO addressDTO);
}
