package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation;

import be.rdhaese.packetdelivery.back_end.model.options.OptionsCollection;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.OptionsRepository;
import org.springframework.stereotype.Repository;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Robin D'Haese
 */
@Repository
public class OptionsXmlRepository implements OptionsRepository {

    public static final String FILE_NAME = "options.xml";

    @Override
    public OptionsCollection getOptionsCollection() throws JAXBException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new OptionsCollection();
        }
        JAXBContext jaxbContext = JAXBContext.newInstance(OptionsCollection.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        try {
            return (OptionsCollection) jaxbUnmarshaller.unmarshal(file);
        } catch (UnmarshalException unmarshalException) {
            throw new JAXBException(unmarshalException);
        }
    }

    @Override
    public Boolean save(OptionsCollection optionsCollection) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(OptionsCollection.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(optionsCollection, new File(FILE_NAME));

        //Return true if the application makes it to here
        return true;
    }
}
