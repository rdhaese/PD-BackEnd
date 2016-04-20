package be.rdhaese.packetdelivery.application.back_end.web_service.interfaces;

import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;

/**
 * Created on 4/01/2016.
 *
 * @author Robin D'Haese
 */
public interface ContactInformationWebService {

    ContactDetailsDTO get();
    boolean post(ContactDetailsDTO contactDetailsDTO);
    String getCompanyName();
}
