package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation;

import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyContactDetails;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation.config.XmlTestConfig;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.CompanyContactDetailsRepository;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

/**
 *
 * @author Robin D'Haese
 */
public class CompanyContactDetailsXmlRepositoryTest extends AbstractXmlRepositoryTest {

    @Autowired
    private CompanyContactDetailsRepository companyContactDetailsRepository;

    @Before
    public void setUp() throws IOException {
        clearFile(XmlTestConfig.COMPANY_CONTACT_DETAILS_XML);
    }

    @After
    public void tearDown() throws IOException {
        applicationContext.getResource(XmlTestConfig.COMPANY_CONTACT_DETAILS_XML).getFile().delete();
    }

    @Test
    public void testGetCompanyContactDetails() throws Exception {
        CompanyContactDetails companyContactDetails = companyContactDetailsRepository.get();
        assertNotNull(companyContactDetails);
        assertNull(companyContactDetails.getCompanyName());

        companyContactDetails = new CompanyContactDetails();
        companyContactDetails.setCompanyName("companyName");
        persistToXml(CompanyContactDetails.class, companyContactDetails, XmlTestConfig.COMPANY_CONTACT_DETAILS_XML);

        companyContactDetails = companyContactDetailsRepository.get();
        assertNotNull(companyContactDetails);
        assertNotNull(companyContactDetails.getCompanyName());
    }

    @Test
    public void testSaveCompanyContactDetails() throws Exception {
        CompanyContactDetails companyContactDetails = companyContactDetailsRepository.get();
        assertNull(companyContactDetails.getCompanyName());

        companyContactDetails.setCompanyName("companyName");
        companyContactDetailsRepository.save(companyContactDetails);

        companyContactDetails = readFromXml(CompanyContactDetails.class, XmlTestConfig.COMPANY_CONTACT_DETAILS_XML);
        assertNotNull(companyContactDetails.getCompanyName());
    }

    private void clearFile(String fileName) throws IOException {
        File file = applicationContext.getResource(fileName).getFile();
        PrintWriter writer = new PrintWriter(file);
        writer.println("<contact-details></contact-details>");
        writer.close();
    }
}
