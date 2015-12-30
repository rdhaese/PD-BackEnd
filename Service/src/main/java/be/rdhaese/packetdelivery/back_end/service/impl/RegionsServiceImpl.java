package be.rdhaese.packetdelivery.back_end.service.impl;

import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.back_end.persistence.RegionJpaRepository;
import be.rdhaese.packetdelivery.back_end.service.RegionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 25/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class RegionsServiceImpl implements RegionsService {

    @Autowired
    private RegionJpaRepository regionRepository;

    public void save(Region region) {
        regionRepository.save(region);
    }

    public Region getRegionFor(String regionCode) {
        return regionRepository.getRegionFor(regionCode);
    }

    public List<Region> getRegions() {
        return regionRepository.findAll();
    }
}
