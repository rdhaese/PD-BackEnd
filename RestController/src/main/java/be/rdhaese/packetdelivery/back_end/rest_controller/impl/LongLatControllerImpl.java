package be.rdhaese.packetdelivery.back_end.rest_controller.impl;

import be.rdhaese.packetdelivery.back_end.mapper.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.LongLat;
import be.rdhaese.packetdelivery.back_end.rest_controller.LongLatController;
import be.rdhaese.packetdelivery.back_end.service.LongLatService;
import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 21/02/2016.
 *
 * @author Robin D'Haese
 */
@RestController
@RequestMapping("/long-lat")
public class LongLatControllerImpl implements LongLatController {

    @Autowired
    private LongLatService longLatService;
    @Autowired
    private Mapper<Address, AddressDTO> addressMapper;
    @Autowired
    private Mapper<LongLat, LongLatDTO> longLatMapper;

    @Override
    @RequestMapping(value = "/for-address", method = RequestMethod.POST)
    public LongLatDTO getForAddress(@RequestBody AddressDTO addressDTO) throws Exception {
        return longLatMapper.mapToDto(longLatService.getForAddress(addressMapper.mapToBus(addressDTO)));
    }
}
