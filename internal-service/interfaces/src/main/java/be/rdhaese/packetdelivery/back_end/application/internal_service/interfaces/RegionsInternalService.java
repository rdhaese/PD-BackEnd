package be.rdhaese.packetdelivery.back_end.application.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.application.model.Region;

import java.util.List;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
public interface RegionsInternalService {
     void save(Region region);
     Region getRegionFor(String regionCode);
     List<Region> getRegions();
}
