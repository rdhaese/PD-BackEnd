package be.rdhaese.packetdelivery.back_end.rest_controller;

import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;

/**
 * Created on 21/02/2016.
 *
 * @author Robin D'Haese
 */
public interface LongLatController {

    LongLatDTO getForAddress(AddressDTO addressDTO) throws Exception;
}
