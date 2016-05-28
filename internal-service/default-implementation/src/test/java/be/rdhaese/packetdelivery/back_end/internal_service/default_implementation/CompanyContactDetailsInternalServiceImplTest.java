package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyContactDetails;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.CompanyContactDetailsRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

import java.io.FileNotFoundException;

import static org.mockito.Mockito.*;

/**
 *
 * @author Robin D'Haese
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration
public class CompanyContactDetailsInternalServiceImplTest extends TestCase {

    @InjectMocks
    private CompanyContactDetailsInternalServiceImpl companyContactDetailsInternalService;

    @Mock
    private CompanyContactDetailsRepository companyContactDetailsRepository;

    @Test
    public void testGet() throws Exception {
        //Setup mocks
        CompanyContactDetails companyContactDetails = new CompanyContactDetails();
        companyContactDetails.setCompanyName("companyName");
        when(companyContactDetailsRepository.get()).thenReturn(companyContactDetails);

        //Test
        TestCase.assertEquals("companyName", companyContactDetailsInternalService.get().getCompanyName());
        verify(companyContactDetailsRepository, times(1)).get();
    }

    @Test
    public void testSave() throws Exception {
        TestCase.assertFalse(companyContactDetailsInternalService.save(null));
        TestCase.assertTrue(companyContactDetailsInternalService.save(new CompanyContactDetails()));

        verify(companyContactDetailsRepository, times(1)).save(any());
    }

    @Test
    public void testGetCompanyName() throws Exception {
        //Setup mocks
        CompanyContactDetails companyContactDetails = new CompanyContactDetails();
        companyContactDetails.setCompanyName("companyName");
        when(companyContactDetailsRepository.get()).thenReturn(companyContactDetails);

        //Test
        TestCase.assertEquals("companyName", companyContactDetailsInternalService.getCompanyName());
        verify(companyContactDetailsRepository, times(1)).get();
    }

    @Test
    public void testGetCompanyNameDetailsNull() throws Exception {
        when(companyContactDetailsRepository.get()).thenReturn(null);

        //Test
        TestCase.assertNull(companyContactDetailsInternalService.getCompanyName());
        verify(companyContactDetailsRepository, times(1)).get();
    }
}
