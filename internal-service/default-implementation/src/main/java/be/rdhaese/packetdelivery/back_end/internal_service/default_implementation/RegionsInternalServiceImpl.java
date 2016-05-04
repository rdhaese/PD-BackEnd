package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.RegionsInternalService;
import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.RegionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created on 25/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class RegionsInternalServiceImpl implements RegionsInternalService {

    @Autowired
    private RegionJpaRepository regionRepository;

    @CachePut(value = "regions", key = "#region.regionCode")
    @Transactional
    public void save(Region region) {
        regionRepository.save(region);
    }

    public Region getRegionFor(String regionCode) {
        return regionRepository.getRegionFor(regionCode);
    }

    //Due to the adjacent regions, this operation is heavy to perform and takes about 8 seconds to finish
    //The data that is returned is not going to change very often, so a cache if the perfect solution.
    //Some request will have to wait a while, but most will get an instant result
    //@Cacheable enables caching, implementation should be given in module higher in the hierarchy
    @Cacheable("regions")
    public List<Region> getRegions() {
        return regionRepository.findAll();
    }


}
