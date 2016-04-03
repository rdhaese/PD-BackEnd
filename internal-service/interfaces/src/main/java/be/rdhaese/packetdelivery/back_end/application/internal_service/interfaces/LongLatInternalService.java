package be.rdhaese.packetdelivery.back_end.application.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.application.model.Address;
import be.rdhaese.packetdelivery.back_end.application.model.LongLat;

/**
 * Created on 21/02/2016.
 *
 * @author Robin D'Haese
 */
public interface LongLatInternalService {
    LongLat getForAddress(Address address) throws Exception;
}
