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

    @ResponseBody
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @Override
    public
    OptionsDTO getFor(@PathVariable String username) throws Exception {
        return optionsMapper.mapToDto(optionsService.getFor(username));
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Override
    public
    Boolean save(@RequestBody OptionsDTO optionsDTO) throws Exception {
        return optionsService.save(optionsMapper.mapToBus(optionsDTO));
    }
}
