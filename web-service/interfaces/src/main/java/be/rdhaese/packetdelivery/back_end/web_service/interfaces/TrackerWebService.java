package be.rdhaese.packetdelivery.back_end.web_service.interfaces;

import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.LocationUpdateDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import be.rdhaese.packetdelivery.dto.RemarkDTO;

import java.util.Collection;

/**
 * Created on 19/04/2016.
 *
 * @author Robin D'Haese
 */
public interface TrackerWebService {

    LongLatDTO getCompanyAddress() throws Exception;
    LongLatDTO getPacketAddress(String packetId) throws Exception;
    Collection<LocationUpdateDTO> getLocationUpdates(String packetId);
    Collection<RemarkDTO> getRemarks(String packetId);
    Integer getAmountOfPacketsLeftBefore(String packetId) throws Exception;
}
