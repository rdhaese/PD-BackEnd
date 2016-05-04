package be.rdhaese.packetdelivery.back_end.web_service.interfaces;

import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;

/**
 * Created on 4/01/2016.
 *
 * @author Robin D'Haese
 */
public interface ContactInformationWebService {

    ContactDetailsDTO get() throws Exception;
    boolean post(ContactDetailsDTO contactDetailsDTO) throws Exception;
    String getCompanyName() throws Exception;
}
