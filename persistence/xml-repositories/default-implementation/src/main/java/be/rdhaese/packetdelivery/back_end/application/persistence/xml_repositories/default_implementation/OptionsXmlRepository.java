package be.rdhaese.packetdelivery.back_end.application.persistence.xml_repositories.default_implementation;

import be.rdhaese.packetdelivery.back_end.application.model.company_details.CompanyContactDetails;
import be.rdhaese.packetdelivery.back_end.application.model.options.OptionsCollection;
import be.rdhaese.packetdelivery.back_end.application.persistence.xml_repositories.interfaces.OptionsRepository;
import org.springframework.stereotype.Repository;

import javax.xml.bind.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created on 17/04/2016.
 *
 * @author Robin D'Haese
 */
@Repository
public class OptionsXmlRepository implements OptionsRepository {
    private static final String FILE_NAME = "options.xml";

    @Override
    public OptionsCollection getOptionsCollection() throws JAXBException, IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()){
            throw new FileNotFoundException("Options file doesn't exist");
        }
        JAXBContext jaxbContext = JAXBContext.newInstance(OptionsCollection.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        try {
            return (OptionsCollection) jaxbUnmarshaller.unmarshal(file);
        } catch (UnmarshalException unmarshalException){
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
