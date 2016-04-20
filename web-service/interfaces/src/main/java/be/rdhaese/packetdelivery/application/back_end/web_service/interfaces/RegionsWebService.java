package be.rdhaese.packetdelivery.application.back_end.web_service.interfaces;

import be.rdhaese.packetdelivery.dto.RegionDTO;

import java.util.Collection;

/**
 * Created on 4/01/2016.
 *
 * @author Robin D'Haese
 */
public interface RegionsWebService {
    Collection<RegionDTO> regions();
}
