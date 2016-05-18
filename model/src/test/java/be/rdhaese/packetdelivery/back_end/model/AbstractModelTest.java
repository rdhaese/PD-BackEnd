package be.rdhaese.packetdelivery.back_end.model;

import be.rdhaese.packetdelivery.back_end.model.config.ModelTestConfig;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created on 2/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ModelTestConfig.class)
@Transactional
public abstract class AbstractModelTest extends TestCase {

    @PersistenceContext
    private EntityManager entityManager;

    EntityManager getEntityManager() {
        return entityManager;
    }

    void persistFlushAndClear(Object o) {
        entityManager.persist(o);
        entityManager.flush();
        entityManager.clear();
    }

    protected void persistToXml(Class clazz, Object object, String fileName) throws Exception {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, new File(fileName));
    }

    protected <T> T readFromXml(Class clazz, String fileName) throws FileNotFoundException, JAXBException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        //noinspection unchecked
        return (T) jaxbUnmarshaller.unmarshal(file);
    }

    protected void removeFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.delete();
        }
    }

}
