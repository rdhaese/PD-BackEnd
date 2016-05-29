package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation;

import be.rdhaese.packetdelivery.back_end.model.options.OptionsCollection;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.OptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import javax.xml.bind.*;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Robin D'Haese
 */
@Repository
public class OptionsXmlRepository implements OptionsRepository {

    @Autowired
    @Qualifier("optionsResource")
    private Resource optionsResource;

    @Override
    public OptionsCollection getOptionsCollection() throws JAXBException, IOException {
        createFileIfItDoesNotExist();
        JAXBContext jaxbContext = JAXBContext.newInstance(OptionsCollection.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        try {
            return (OptionsCollection) jaxbUnmarshaller.unmarshal(optionsResource.getInputStream());
        } catch (UnmarshalException unmarshalException) {
            throw new JAXBException(unmarshalException);
        }
    }



    @Override
    public Boolean save(OptionsCollection optionsCollection) throws JAXBException, IOException {
        createFileIfItDoesNotExist();
        JAXBContext context = JAXBContext.newInstance(OptionsCollection.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(optionsCollection, optionsResource.getFile());

        //Return true if the application makes it to here
        return true;
    }

    private void createFileIfItDoesNotExist() throws IOException {
        if (!optionsResource.exists()){
            File file = optionsResource.getFile();
            file.createNewFile();
            PrintWriter writer = new PrintWriter(file);
            writer.println("<options></options>");
            writer.close();
        }
    }
}
