package be.rdhaese.packetdelivery.back_end.rest_controller;

import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;

/**
 * Created on 4/01/2016.
 *
 * @author Robin D'Haese
 */
public interface EditContactInformationController {

    ContactDetailsDTO get();
    boolean post(ContactDetailsDTO contactDetailsDTO);
}
