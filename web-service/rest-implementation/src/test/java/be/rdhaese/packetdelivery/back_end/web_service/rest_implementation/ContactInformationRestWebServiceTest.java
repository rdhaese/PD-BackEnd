package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.CompanyContactDetailsInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyContactDetails;
import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created on 13/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ContactInformationRestWebServiceTest.Config.class)
@WebAppConfiguration
public class ContactInformationRestWebServiceTest {

    private CompanyContactDetails companyContactDetails;
    private ContactDetailsDTO contactDetailsDTO;

    @Configuration
    @EnableWebMvc
    static class Config {

        //Controller to test
        @Bean
        public ContactInformationRestWebService contactInformationRestWebService() {
            return new ContactInformationRestWebService();
        }

        //Mocks
        @Bean
        public CompanyContactDetailsInternalService companyContactDetailsInternalService() {
            return mock(CompanyContactDetailsInternalService.class);
        }

        @Bean
        public Mapper<CompanyContactDetails, ContactDetailsDTO> contactDetailsMapper() {
            return mock(Mapper.class);
        }
    }

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        companyContactDetails = new CompanyContactDetails();
        companyContactDetails.setCompanyName("companyName");
        contactDetailsDTO = new ContactDetailsDTO();
        contactDetailsDTO.setCompanyName("companyName");
    }

    @Autowired
    private CompanyContactDetailsInternalService companyContactDetailsInternalService;

    @Autowired
    private Mapper<CompanyContactDetails, ContactDetailsDTO> contactDetailsMapper;

    @Test
    public void testGet() throws Exception {
        when(companyContactDetailsInternalService.get()).thenReturn(companyContactDetails);
        when(contactDetailsMapper.mapToDto(companyContactDetails)).thenReturn(contactDetailsDTO);

        mockMvc.perform(get("/contact-information/get")
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.companyName", is("companyName")));

        verify(companyContactDetailsInternalService, times(1)).get();
        verify(contactDetailsMapper, times(1)).mapToDto(companyContactDetails);
    }

    @Test
    public void testPost() throws Exception {
        when(companyContactDetailsInternalService.save(companyContactDetails)).thenReturn(true);
        when(contactDetailsMapper.mapToBus(contactDetailsDTO)).thenReturn(companyContactDetails);

        mockMvc.perform(
                post("/contact-information/post")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(contactDetailsDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(companyContactDetailsInternalService, times(1)).save(companyContactDetails);
        verify(contactDetailsMapper, times(1)).mapToBus(contactDetailsDTO);
    }

    @Test
    public void testGetCompanyName() throws Exception {
        when(companyContactDetailsInternalService.getCompanyName()).thenReturn("companyName");

        mockMvc.perform(get("/contact-information/company-name"))
                .andExpect(status().isOk())
                .andExpect(content().string("companyName"));

        verify(companyContactDetailsInternalService, times(1)).getCompanyName();
    }
}
