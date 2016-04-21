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

    private static final String FILE_NAME = "company-contact-details.xml";
    @Override
    public CompanyContactDetails get() throws JAXBException, IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()){
            throw new FileNotFoundException("Contact details don't exist");
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

    public static void main(String... args) throws JAXBException, IOException {
        //TODO remove or put in test
        CompanyContactDetails companyContactDetails = new CompanyContactDetails();
        companyContactDetails.setCompanyName("test company name");
        companyContactDetails.setAboutText("this is about text");
        companyContactDetails.setAddress(createAddress());
        companyContactDetails.setPhoneNumbers(createPhoneNumbersList());
        companyContactDetails.setFaxNumbers(createFaxNumbersList());

        CompanyContactDetailsXmlRepository r = new CompanyContactDetailsXmlRepository();
        r.save(companyContactDetails);

        System.out.println(r.get().getPhoneNumbers().get(0).getTitle());
    }

    private static List<PhoneEntry> createPhoneNumbersList() {
        List<PhoneEntry> phoneEntries = new ArrayList<>();
        phoneEntries.add(new PhoneEntry("phone title", "phone number"));
        phoneEntries.add(new PhoneEntry("phone title 1", "phone number 1"));
        phoneEntries.add(new PhoneEntry("phone title 2", "phone number 2"));
        return phoneEntries;
    }

    private static List<FaxEntry> createFaxNumbersList() {
        List<FaxEntry> faxEntries = new ArrayList<>();
        faxEntries.add(new FaxEntry("fax entry", "fax entry number"));
        return faxEntries;
    }

    private static Address createAddress() {
        Address address = new Address();
        address.setStreet("teststreet");
        address.setNumber("77");
        address.setCity("testcity");
        address.setPostalCode("testpostalcode");
        return address;
    }
}
