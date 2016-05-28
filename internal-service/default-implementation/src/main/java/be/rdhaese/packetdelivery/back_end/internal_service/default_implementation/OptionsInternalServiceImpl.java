package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.OptionsInternalService;
import be.rdhaese.packetdelivery.back_end.model.options.Options;
import be.rdhaese.packetdelivery.back_end.model.options.OptionsCollection;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.OptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Robin D'Haese
 */
@Service
public class OptionsInternalServiceImpl implements OptionsInternalService {

    @Autowired
    private OptionsRepository optionsRepository;

    @Override
    public Options getFor(String username) throws Exception {
        OptionsCollection optionsCollection = optionsRepository.getOptionsCollection();
        for (Options options : optionsCollection.getOptions()) {
            if (options.getUser().equals(username)) {
                return options;
            }
        }

        //No options found for user -> return new options
        return new Options();
    }

    @Override
    public Boolean save(Options options) throws Exception {
        if (options == null) {
            return false;
        }
        OptionsCollection optionsCollection = optionsRepository.getOptionsCollection();
        optionsCollection.addOptions(options);
        return optionsRepository.save(optionsCollection);
    }
}
