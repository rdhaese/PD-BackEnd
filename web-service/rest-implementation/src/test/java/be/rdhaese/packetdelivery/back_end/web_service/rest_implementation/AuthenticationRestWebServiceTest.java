package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.AppInternalService;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.AuthenticationInternalService;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.enums.AuthenticationResult;
import be.rdhaese.packetdelivery.back_end.mapper.default_implementation.AppStateMapper;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppState;
import be.rdhaese.packetdelivery.dto.AppStateDTO;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created on 13/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AuthenticationRestWebServiceTest.Config.class)
@WebAppConfiguration
public class AuthenticationRestWebServiceTest {

    @Configuration
    @EnableWebMvc
    static class Config {

        //Controller to test
        @Bean
        public AuthenticationRestWebService authenticationRestWebService() {
            return new AuthenticationRestWebService();
        }

        //Mocks
        @Bean
        public AuthenticationInternalService authenticationInternalService() {
            return mock(AuthenticationInternalService.class);
        }
    }

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Autowired
    private AuthenticationInternalService authenticationInternalService;

    @Test
    public void testAuthenticate() throws Exception {
        when(authenticationInternalService.authenticate("username", "password")).thenReturn(AuthenticationResult.GRANTED);

        mockMvc.perform(post("/authenticate/username").content("password"))
                .andExpect(status().isOk())
                .andExpect(content().string("GRANTED"));

        verify(authenticationInternalService, times(1)).authenticate("username", "password");
    }
}
