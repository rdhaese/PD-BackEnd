package be.rdhaese.packetdelivery.back_end.mapper.default_implementation;

import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyContactDetails;
import be.rdhaese.packetdelivery.back_end.model.company_details.EmailEntry;
import be.rdhaese.packetdelivery.back_end.model.company_details.FaxEntry;
import be.rdhaese.packetdelivery.back_end.model.company_details.PhoneEntry;
import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createAddress;
import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createCompanyContactDetails;

/**
 * Created on 13/05/2016.
 *
 * @author Robin D'Haese
 */
public class CompanyContactDetailsMapperTest extends TestCase {
    private Mapper<CompanyContactDetails, ContactDetailsDTO> mapper;

    private CompanyContactDetails companyContactDetails;
    private ContactDetailsDTO contactDetailsDto;
    private Address address;
    private List<PhoneEntry> phoneNumbers;
    private List<FaxEntry> faxNumbers;
    private List<EmailEntry> emails;

    @Before
    public void setUp() {
        mapper = new CompanyContactDetailsMapper();
        address = createAddress("street", "number", null, "postalCode", "city");
        phoneNumbers = Arrays.asList(new PhoneEntry("title1", "number1"), new PhoneEntry("title2", "number2"));
        faxNumbers = Arrays.asList(new FaxEntry("title3", "number3"), new FaxEntry("title4", "number4"));
        emails = Arrays.asList(new EmailEntry("title5", "email1"), new EmailEntry("title6", "email2"));
        companyContactDetails = createCompanyContactDetails("companyName", address, phoneNumbers, faxNumbers, emails, "aboutText");

        contactDetailsDto = new ContactDetailsDTO();
        contactDetailsDto.setCompanyName("companyName");
        contactDetailsDto.setStreet("street");
        contactDetailsDto.setNumber("number");
        contactDetailsDto.setMailbox(null);
        contactDetailsDto.setPostalCode("postalCode");
        contactDetailsDto.setCity("city");
        Map<String, String> phoneNumbersMap = new HashMap<>();
        phoneNumbersMap.put("title1", "number1");
        phoneNumbersMap.put("title2", "number2");
        contactDetailsDto.setPhoneNumbers(phoneNumbersMap);
        Map<String, String> faxNumbersMap = new HashMap<>();
        faxNumbersMap.put("title3", "number3");
        faxNumbersMap.put("title4", "number4");
        contactDetailsDto.setFaxNumbers(faxNumbersMap);
        Map<String, String> emailsMap = new HashMap<>();
        emailsMap.put("title5", "email1");
        emailsMap.put("title6", "email2");
        contactDetailsDto.setEmailAddresses(emailsMap);
        contactDetailsDto.setAboutText("aboutText");
    }

    @Test
    public void testMapToBus() {
        CompanyContactDetails mappedCompanyContactDetails = mapper.mapToBus(contactDetailsDto);
        assertEquals(companyContactDetails, mappedCompanyContactDetails);
        assertTrue(mappedCompanyContactDetails.getPhoneNumbers().containsAll(phoneNumbers));
        assertTrue(mappedCompanyContactDetails.getFaxNumbers().containsAll(faxNumbers));
        assertTrue(mappedCompanyContactDetails.getEmailAddresses().containsAll(emails));
    }

    @Test
    public void testMapToDto() {
        assertEquals(contactDetailsDto, mapper.mapToDto(companyContactDetails));
    }
}
