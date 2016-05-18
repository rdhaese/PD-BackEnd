package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.enums.AuthenticationResult;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

/**
 * Created on 7/05/2016.
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

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void testAuthenticateWithMasterAccount() {
        //Setup mocks
        when(passwordEncoder.matches("pdmaster", "pdmaster")).thenReturn(true);

        TestCase.assertEquals(AuthenticationResult.GRANTED,
                authenticationInternalService.authenticate("pdmaster", "pdmaster"));
        verify(passwordEncoder, times(1)).matches(any(), any());
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
    public void testAuthenticateNoPasswordInLdap() {
        //Setup mocks
        when(ldapTemplate.search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.USERNAME_MAPPER)))
                .thenReturn(Collections.singletonList("username"));
        when(ldapTemplate.search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.PASSWORD_MAPPER)))
                .thenReturn(new ArrayList<>());


        //Test
        TestCase.assertEquals(AuthenticationResult.GRANTED,
                authenticationInternalService.authenticate("username", "password"));
        verify(ldapTemplate, times(1)).search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.USERNAME_MAPPER));
        verify(ldapTemplate, times(1)).search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.PASSWORD_MAPPER));
    }

    @Test
    public void testAuthenticateGranted() {
        //Setup mocks
        when(ldapTemplate.search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.USERNAME_MAPPER)))
                .thenReturn(Collections.singletonList("username"));
        when(ldapTemplate.search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.PASSWORD_MAPPER)))
                .thenReturn(Collections.singletonList("password"));
        when(passwordEncoder.matches("password", "password")).thenReturn(true);

        //Test
        TestCase.assertEquals(AuthenticationResult.GRANTED,
                authenticationInternalService.authenticate("username", "password"));
        verify(ldapTemplate, times(1)).search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.USERNAME_MAPPER));
        verify(ldapTemplate, times(1)).search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.PASSWORD_MAPPER));
        verify(passwordEncoder, times(1)).matches(any(), any());
    }

    @Test
    public void testAuthenticateWrongPassword() {
        //Setup mocks
        when(ldapTemplate.search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.USERNAME_MAPPER)))
                .thenReturn(Collections.singletonList("username"));
        when(ldapTemplate.search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.PASSWORD_MAPPER)))
                .thenReturn(Collections.singletonList("wrongpass"));


        //Test
        TestCase.assertEquals(AuthenticationResult.WRONG_PASSWORD,
                authenticationInternalService.authenticate("username", "password"));
        verify(ldapTemplate, times(1)).search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.USERNAME_MAPPER));
        verify(ldapTemplate, times(1)).search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.PASSWORD_MAPPER));
        verify(passwordEncoder, times(1)).matches(any(), any());
    }
}
