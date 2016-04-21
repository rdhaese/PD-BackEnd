package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.OptionsInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.options.Options;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.OptionsWebService;
import be.rdhaese.packetdelivery.dto.OptionsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 17/04/2016.
 *
 * @author Robin D'Haese
 */
@Controller
@RequestMapping("/options")
public class OptionsRestWebService implements OptionsWebService {

    @Autowired
    private OptionsInternalService optionsService;
    @Autowired
    private Mapper<Options, OptionsDTO> optionsMapper;

    @Override
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public @ResponseBody OptionsDTO getFor(@PathVariable String username) {
        return optionsMapper.mapToDto(optionsService.getFor(username));
    }

    @Override
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody Boolean save(@RequestBody OptionsDTO optionsDTO) {
        System.out.println("DTO: " + optionsDTO);
        Boolean result = optionsService.save(optionsMapper.mapToBus(optionsDTO));
        System.out.println("Response: " + result);
        return result;
    }
}
