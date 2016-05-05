package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation;

import be.rdhaese.packetdelivery.back_end.model.company_details.PhoneEntry;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyContactDetails;
import be.rdhaese.packetdelivery.back_end.model.company_details.FaxEntry;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.CompanyContactDetailsRepository;
import org.springframework.stereotype.Repository;

import javax.xml.bind.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 27/12/2015.
 *
 * @author Robin D'Haese
 */
@Repository
public class CompanyContactDetailsXmlRepository implements CompanyContactDetailsRepository {

    public static final String FILE_NAME = "company-contact-details.xml";

    @Override
    public CompanyContactDetails get() throws JAXBException, IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()){
            return new CompanyContactDetails();
        }
        JAXBContext jaxbContext = JAXBContext.newInstance(CompanyContactDetails.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        try {
            return (CompanyContactDetails) jaxbUnmarshaller.unmarshal(file);
        } catch (UnmarshalException unmarshalException){
            throw new JAXBException(unmarshalException);
        }
    }

    @Override
    public void save(CompanyContactDetails companyContactDetails) throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(CompanyContactDetails.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(companyContactDetails, new File(FILE_NAME));
    }
}
