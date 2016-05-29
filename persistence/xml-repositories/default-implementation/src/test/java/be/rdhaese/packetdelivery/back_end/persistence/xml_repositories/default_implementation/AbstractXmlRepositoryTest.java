package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation;

import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation.config.XmlTestConfig;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Robin D'Haese
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = XmlTestConfig.class)
public abstract class AbstractXmlRepositoryTest extends TestCase {

    @Autowired
    ApplicationContext applicationContext;

    void persistToXml(Class clazz, Object object, String fileName) throws Exception {
        File file = applicationContext.getResource(fileName).getFile();
        JAXBContext context = JAXBContext.newInstance(clazz);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, file);
    }

    <T> T readFromXml(Class clazz, String fileName) throws IOException, JAXBException {
        File file = applicationContext.getResource(fileName).getFile();
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        //noinspection unchecked
        return (T) jaxbUnmarshaller.unmarshal(file);
    }


}
