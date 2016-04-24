package be.rdhaese.packetdelivery.back_end.mapper.default_implementation;

import be.rdhaese.packetdelivery.back_end.mapper.interfaces.AbstractMapper;
import be.rdhaese.packetdelivery.back_end.model.options.Options;
import be.rdhaese.packetdelivery.dto.OptionsDTO;
import org.springframework.stereotype.Component;

/**
 * Created on 17/04/2016.
 *
 * @author Robin D'Haese
 */
@Component
public class OptionsMapper extends AbstractMapper<Options, OptionsDTO> {
    @Override
    public Options mapToBus(OptionsDTO dto) {
        Options options = new Options();
        options.setUser(dto.getUser());
        options.setLanguage(dto.getLanguage());
        options.setPrint(dto.getPrint());
        options.setImageViewer(dto.getImageViewer());
        return options;
    }

    @Override
    public OptionsDTO mapToDto(Options busObj) {
        OptionsDTO dto = new OptionsDTO();
        dto.setUser(busObj.getUser());
        dto.setLanguage(busObj.getLanguage());
        dto.setPrint(busObj.getPrint());
        dto.setImageViewer(busObj.getImageViewer());
        return dto;
    }
}
