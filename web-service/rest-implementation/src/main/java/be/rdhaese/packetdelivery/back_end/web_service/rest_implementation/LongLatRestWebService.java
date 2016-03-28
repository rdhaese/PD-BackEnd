package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.LongLatInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.LongLat;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.LongLatWebService;
import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 21/02/2016.
 *
 * @author Robin D'Haese
 */
@RestController
@RequestMapping("/long-lat")
public class LongLatRestWebService implements LongLatWebService {

    @Autowired
    private LongLatInternalService longLatInternalService;
    @Autowired
    private Mapper<Address, AddressDTO> addressMapper;
    @Autowired
    private Mapper<LongLat, LongLatDTO> longLatMapper;

    @Override
    @RequestMapping(value = "/for-address", method = RequestMethod.POST)
    public LongLatDTO getForAddress(@RequestBody AddressDTO addressDTO) {
        try {
            return longLatMapper.mapToDto(longLatInternalService.getForAddress(addressMapper.mapToBus(addressDTO)));
        } catch (Exception e) {
            //TODO do something
        }
        return new LongLatDTO(null, null);
    }
}
