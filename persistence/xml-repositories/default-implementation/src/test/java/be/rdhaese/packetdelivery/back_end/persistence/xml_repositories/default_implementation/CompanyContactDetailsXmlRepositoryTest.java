package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation;

import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyContactDetails;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.CompanyContactDetailsRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 *
 * @author Robin D'Haese
 */
public class CompanyContactDetailsXmlRepositoryTest extends AbstractXmlRepositoryTest {

    @Autowired
    private CompanyContactDetailsRepository companyContactDetailsRepository;

    @Before
    public void setUp() {
        removeFile(CompanyContactDetailsXmlRepository.FILE_NAME);
    }

    @Test
    public void testGetCompanyContactDetails() throws Exception {
        CompanyContactDetails companyContactDetails = companyContactDetailsRepository.get();
        assertNotNull(companyContactDetails);
        assertNull(companyContactDetails.getCompanyName());

        companyContactDetails = new CompanyContactDetails();
        companyContactDetails.setCompanyName("companyName");
        persistToXml(CompanyContactDetails.class, companyContactDetails, CompanyContactDetailsXmlRepository.FILE_NAME);

        companyContactDetails = companyContactDetailsRepository.get();
        assertNotNull(companyContactDetails);
        assertNotNull(companyContactDetails.getCompanyName());
    }

    @Test
    public void testSaveCompanyContactDetails() throws IOException, JAXBException {
        CompanyContactDetails companyContactDetails = companyContactDetailsRepository.get();
        assertNull(companyContactDetails.getCompanyName());

        companyContactDetails.setCompanyName("companyName");
        companyContactDetailsRepository.save(companyContactDetails);

        companyContactDetails = readFromXml(CompanyContactDetails.class, CompanyContactDetailsXmlRepository.FILE_NAME);
        assertNotNull(companyContactDetails.getCompanyName());
    }
}
