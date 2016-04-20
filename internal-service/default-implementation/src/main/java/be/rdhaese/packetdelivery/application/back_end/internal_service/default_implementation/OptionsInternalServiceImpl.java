package be.rdhaese.packetdelivery.application.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.application.back_end.internal_service.interfaces.OptionsInternalService;
import be.rdhaese.packetdelivery.application.back_end.model.options.Options;
import be.rdhaese.packetdelivery.application.back_end.model.options.OptionsCollection;
import be.rdhaese.packetdelivery.application.back_end.persistence.xml_repositories.interfaces.OptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created on 17/04/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class OptionsInternalServiceImpl implements OptionsInternalService {

    @Autowired
    private OptionsRepository optionsRepository;

    @Override
    public Options getFor(String username) {
        try {
            OptionsCollection optionsCollection = optionsRepository.getOptionsCollection();
            for (Options options : optionsCollection.getOptions()) {
                if (options.getUser().equals(username)) {
                    return options;
                }
            }
        } catch (Exception e) {
            //TODO handle this
            e.printStackTrace();
        }
        System.out.println("returning new options");
        return new Options();
    }

    @Override
    public Boolean save(Options options) {
        OptionsCollection optionsCollection = null;
        try {
            optionsCollection = optionsRepository.getOptionsCollection();
        } catch (IOException | JAXBException e) {
            //TODO handle this
            e.printStackTrace();
            return false;
        }
        optionsCollection.addOptions(options);
        try {
            return optionsRepository.save(optionsCollection);
        } catch (JAXBException e) {
            //TODO Handle this
            e.printStackTrace();
            return false;
        }
    }
}
