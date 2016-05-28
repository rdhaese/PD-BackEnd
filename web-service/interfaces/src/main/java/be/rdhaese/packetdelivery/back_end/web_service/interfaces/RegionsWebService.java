package be.rdhaese.packetdelivery.back_end.web_service.interfaces;

import be.rdhaese.packetdelivery.dto.RegionDTO;

import java.util.Collection;

/**
 *
 * @author Robin D'Haese
 */
public interface RegionsWebService {
    Collection<RegionDTO> regions();
}
