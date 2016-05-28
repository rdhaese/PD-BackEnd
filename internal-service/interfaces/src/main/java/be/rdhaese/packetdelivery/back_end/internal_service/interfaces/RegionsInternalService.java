package be.rdhaese.packetdelivery.back_end.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.model.Region;

import java.util.List;

/**
 *
 * @author Robin D'Haese
 */
public interface RegionsInternalService {
    void save(Region region);

    Region getRegionFor(String regionCode);

    List<Region> getRegions();
}
