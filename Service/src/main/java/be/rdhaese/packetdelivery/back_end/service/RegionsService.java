package be.rdhaese.packetdelivery.back_end.service;

import be.rdhaese.packetdelivery.back_end.model.Region;

import java.util.List;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
public interface RegionsService {
     void save(Region region);
     Region getRegionFor(String regionCode);
     List<Region> getRegions();
}
