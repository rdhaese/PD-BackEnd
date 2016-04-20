package be.rdhaese.packetdelivery.back_end.application.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.application.model.LocationUpdate;
import be.rdhaese.packetdelivery.back_end.application.model.LongLat;
import be.rdhaese.packetdelivery.back_end.application.model.Remark;

import java.util.Collection;

/**
 * Created on 19/04/2016.
 *
 * @author Robin D'Haese
 */
public interface TrackerInternalService {
    LongLat getCompanyAddress();

    LongLat getPacketAddress(String packetId);

    Collection<LocationUpdate> getLocationsUpdates(String packetId);

    Collection<Remark> getRemarks(String packetId);
}
