package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces;

import be.rdhaese.packetdelivery.back_end.model.options.OptionsCollection;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author Robin D'Haese
 */
public interface OptionsRepository {
    OptionsCollection getOptionsCollection() throws JAXBException, URISyntaxException, IOException;

    @SuppressWarnings("SameReturnValue")
    Boolean save(OptionsCollection optionsCollection) throws JAXBException, IOException;
}
