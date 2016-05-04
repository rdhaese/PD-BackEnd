package be.rdhaese.packetdelivery.back_end.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.model.LongLat;
import be.rdhaese.packetdelivery.back_end.model.LocationUpdate;
import be.rdhaese.packetdelivery.back_end.model.Remark;

import java.util.Collection;

/**
 * Created on 19/04/2016.
 *
 * @author Robin D'Haese
 */
public interface TrackerInternalService {
    LongLat getCompanyAddress() throws Exception;

    LongLat getPacketAddress(String packetId) throws Exception;

    Collection<LocationUpdate> getLocationsUpdates(String packetId);

    Collection<Remark> getRemarks(String packetId);

    Integer getAmountOfPacketsLeftBefore(String packetId) throws Exception;
}
