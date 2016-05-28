package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.AuthenticationInternalService;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.enums.AuthenticationResult;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Robin D'Haese
 */
public class AuthenticationRestWebServiceTest extends AbstractRestWebServiceTest {

    @Autowired //Mock, see TestConfig
    private AuthenticationInternalService authenticationInternalService;

    @After
    public void tearDown() {
        reset(authenticationInternalService);
    }

    @Test
    public void testAuthenticate() throws Exception {
        when(authenticationInternalService.authenticate("username", "password")).thenReturn(AuthenticationResult.GRANTED);

        mockMvc.perform(post("/authenticate/username").content("password"))
                .andExpect(status().isOk())
                .andExpect(content().string("GRANTED"));

        verify(authenticationInternalService, times(1)).authenticate("username", "password");
    }
}
