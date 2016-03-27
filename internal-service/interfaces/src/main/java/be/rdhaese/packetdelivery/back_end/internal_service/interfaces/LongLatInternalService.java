package be.rdhaese.packetdelivery.back_end.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.LongLat;

/**
 * Created on 21/02/2016.
 *
 * @author Robin D'Haese
 */
public interface LongLatInternalService {
    LongLat getForAddress(Address address) throws Exception;
}
