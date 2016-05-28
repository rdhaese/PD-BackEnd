package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.enums.AuthenticationResult;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

/**
 *
 * @author Robin D'Haese
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration
public class AuthenticationInternalServiceImplTest extends TestCase {

    @InjectMocks
    private AuthenticationInternalServiceImpl authenticationInternalService;

    @Mock
    private LdapTemplate ldapTemplate;

    @Test
    public void testAuthenticateWithMasterAccount() {
        TestCase.assertEquals(AuthenticationResult.GRANTED,
                authenticationInternalService.authenticate("pdmaster", "pdmaster"));
    }

    @Test
    public void testAuthenticateUserNotKnown() {
        //Setup mocks
        when(ldapTemplate.search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.USERNAME_MAPPER)))
                .thenReturn(new ArrayList<>());

        //Test
        TestCase.assertEquals(AuthenticationResult.NOT_KNOWN,
                authenticationInternalService.authenticate("username", "password"));
        verify(ldapTemplate, times(1)).search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.USERNAME_MAPPER));
    }

    @Test
    public void testAuthenticateGranted() {
        //Setup mocks
        when(ldapTemplate.search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.USERNAME_MAPPER)))
                .thenReturn(Collections.singletonList("username"));
        when(ldapTemplate.authenticate(anyString(), anyString(), eq("password"))).thenReturn(true);

        //Test
        TestCase.assertEquals(AuthenticationResult.GRANTED,
                authenticationInternalService.authenticate("username", "password"));
        verify(ldapTemplate, times(1)).search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.USERNAME_MAPPER));
        verify(ldapTemplate, times(1)).authenticate(anyString(), anyString(), eq("password"));
    }

    @Test
    public void testAuthenticateWrongPassword() {
        //Setup mocks
        when(ldapTemplate.search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.USERNAME_MAPPER)))
                .thenReturn(Collections.singletonList("username"));
        when(ldapTemplate.authenticate(anyString(), anyString(), eq("password"))).thenReturn(false);

        //Test
        TestCase.assertEquals(AuthenticationResult.WRONG_PASSWORD,
                authenticationInternalService.authenticate("username", "password"));
        verify(ldapTemplate, times(1)).search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.USERNAME_MAPPER));
        verify(ldapTemplate, times(1)).authenticate(anyString(), anyString(), eq("password"));
    }

    @Test
    public void testAuthenticateUserNotKnownWhenNoLdapConnection(){
        when(ldapTemplate.search(anyString(), anyString(), any(AttributesMapper.class))).thenThrow(new RuntimeException());

        //Test
        TestCase.assertEquals(AuthenticationResult.NOT_KNOWN,
                authenticationInternalService.authenticate("username", "password"));

        verify(ldapTemplate, times(1)).search(anyString(), anyString(), any(AttributesMapper.class));

    }
}
