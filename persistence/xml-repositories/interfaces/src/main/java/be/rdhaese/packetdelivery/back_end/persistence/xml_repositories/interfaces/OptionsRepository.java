package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces;

import be.rdhaese.packetdelivery.back_end.model.options.OptionsCollection;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created on 17/04/2016.
 *
 * @author Robin D'Haese
 */
public interface OptionsRepository {
    OptionsCollection getOptionsCollection() throws JAXBException;

    @SuppressWarnings("SameReturnValue")
    Boolean save(OptionsCollection optionsCollection) throws JAXBException;
}
