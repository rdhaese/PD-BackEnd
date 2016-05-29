package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation;

import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyContactDetails;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.CompanyContactDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Robin D'Haese
 */
@Repository
public class CompanyContactDetailsXmlRepository implements CompanyContactDetailsRepository {

    @Autowired
    @Qualifier("companyContactDetailsResource")
    private Resource companyContactDetailsResource;

    @Override
    public CompanyContactDetails get() throws Exception {
        createFileIfItDoesNotExist();
        JAXBContext jaxbContext = JAXBContext.newInstance(CompanyContactDetails.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        try {
            return (CompanyContactDetails) jaxbUnmarshaller.unmarshal(companyContactDetailsResource.getInputStream());
        } catch (UnmarshalException unmarshalException) {
            throw new JAXBException(unmarshalException);
        }
    }

    @Override
    public void save(CompanyContactDetails companyContactDetails) throws Exception {
        createFileIfItDoesNotExist();
        JAXBContext context = JAXBContext.newInstance(CompanyContactDetails.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(companyContactDetails, companyContactDetailsResource.getFile());

    }

    private void createFileIfItDoesNotExist() throws IOException {
        if (!companyContactDetailsResource.exists()) {
            File file = companyContactDetailsResource.getFile();
            file.createNewFile();
            PrintWriter writer = new PrintWriter(file);
            writer.println("<contact-details></contact-details>");
            writer.close();
        }
    }
}
