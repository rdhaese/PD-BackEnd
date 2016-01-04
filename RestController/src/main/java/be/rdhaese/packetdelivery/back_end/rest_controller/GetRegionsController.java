package be.rdhaese.packetdelivery.back_end.rest_controller;

import be.rdhaese.packetdelivery.dto.RegionDTO;

import java.util.Collection;

/**
 * Created on 4/01/2016.
 *
 * @author Robin D'Haese
 */
public interface GetRegionsController {
    Collection<RegionDTO> regions();
}
