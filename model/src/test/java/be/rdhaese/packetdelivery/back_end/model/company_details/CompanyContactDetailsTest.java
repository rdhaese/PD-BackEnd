package be.rdhaese.packetdelivery.back_end.model.company_details;

import be.rdhaese.packetdelivery.back_end.model.AbstractModelTest;
import be.rdhaese.packetdelivery.back_end.model.Address;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.*;

/**
 *
 * @author Robin D'Haese
 */
public class CompanyContactDetailsTest extends AbstractModelTest {

    private static final String FILE_NAME = "contact-details.xml";
    private CompanyContactDetails companyContactDetails;

    @Before
    public void setUp() {

        Address address = createAddress(
                "Ezelberg",
                "2",
                "12",
                "9500",
                "Geraardsbergen");

        List<PhoneEntry> phoneEntries = Arrays.asList(createPhoneEntry("title1", "number1"),
                createPhoneEntry("title2", "number2"));
        List<FaxEntry> faxEntries = Collections.singletonList(createFaxEntry("title", "number"));
        List<EmailEntry> emailEntries = Arrays.asList(createEmailEntry("title1", "address1"),
                createEmailEntry("title2", "address2"));

        companyContactDetails = createCompanyContactDetails(
                "bedrijfnaam",
                address,
                phoneEntries,
                faxEntries,
                emailEntries,
                "aboutText"
        );
    }

    @After
    public void afterTestMethod() {
        removeFile(FILE_NAME);
    }

    @Test
    public void testCanPersistToXml() throws Exception {
        //Save to file
        persistToXml(CompanyContactDetails.class, companyContactDetails, FILE_NAME);

        //Read from file
        CompanyContactDetails newCompanyContactDetails = readFromXml(CompanyContactDetails.class, FILE_NAME);
        assertNotNull(newCompanyContactDetails);
        assertEquals(companyContactDetails, newCompanyContactDetails);
        assertEquals(2, newCompanyContactDetails.getPhoneNumbers().size());
        assertEquals(1, newCompanyContactDetails.getFaxNumbers().size());
        assertEquals(2, newCompanyContactDetails.getEmailAddresses().size());
    }
}
