package be.rdhaese.packetdelivery.back_end.application.web_service.interfaces;

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

    LongLatDTO getCompanyAddress();
    LongLatDTO getPacketAddress(String packetId);
    Collection<LocationUpdateDTO> getLocationUpdates(String packetId);
    Collection<RemarkDTO> getRemarks(String packetId);
}
