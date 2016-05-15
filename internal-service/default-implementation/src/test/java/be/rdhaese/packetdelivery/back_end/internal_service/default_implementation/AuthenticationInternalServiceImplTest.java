package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.enums.AuthenticationResult;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.CompanyContactDetailsRepository;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;

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
    public void testAuthenticateWithMasterAccount(){
        //Setup mocks
        when(passwordEncoder.matches("pdmaster", "pdmaster")).thenReturn(true);

        assertEquals(AuthenticationResult.GRANTED,
                authenticationInternalService.authenticate("pdmaster", "pdmaster"));
        verify(passwordEncoder, times(1)).matches(any(), any());
    }

    @Test
    public void testAuthenticateUserNotKnown(){
        //Setup mocks
        when(ldapTemplate.search(any(String.class), any(String.class), any(AttributesMapper.class)))
                .thenReturn(new ArrayList<Object>());

        //Test
        assertEquals(AuthenticationResult.NOT_KNOWN,
                authenticationInternalService.authenticate("username", "password"));
        verify(ldapTemplate, times(1)).search(any(String.class), any(String.class), any(AttributesMapper.class));
    }

    @Test
    public void testAuthenticateNoPasswordInLdap(){
        //Setup mocks
        when(ldapTemplate.search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.USERNAME_MAPPER)))
                .thenReturn(Arrays.asList(new String[]{"username"}));
        when(ldapTemplate.search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.PASSWORD_MAPPER)))
                .thenReturn(new ArrayList<String>());


        //Test
        assertEquals(AuthenticationResult.GRANTED,
                authenticationInternalService.authenticate("username", "password"));
        verify(ldapTemplate, times(2)).search(any(String.class), any(String.class), any(AttributesMapper.class));
    }

    @Test
    public void testAuthenticateGranted(){
        //Setup mocks
        when(ldapTemplate.search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.USERNAME_MAPPER)))
                .thenReturn(Arrays.asList(new String[]{"username"}));
        when(ldapTemplate.search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.PASSWORD_MAPPER)))
                .thenReturn(Arrays.asList(new String[]{"password"}));
        when(passwordEncoder.matches("password", "password")).thenReturn(true);

        //Test
        assertEquals(AuthenticationResult.GRANTED,
                authenticationInternalService.authenticate("username", "password"));
        verify(ldapTemplate, times(2)).search(any(String.class), any(String.class), any(AttributesMapper.class));
        verify(passwordEncoder, times(1)).matches(any(), any());
    }

    @Test
    public void testAuthenticateWrongPassword(){
        //Setup mocks
        when(ldapTemplate.search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.USERNAME_MAPPER)))
                .thenReturn(Arrays.asList(new String[]{"username"}));
        when(ldapTemplate.search(any(String.class), any(String.class), eq(AuthenticationInternalServiceImpl.PASSWORD_MAPPER)))
                .thenReturn(Arrays.asList(new String[]{"wrongpass"}));


        //Test
        assertEquals(AuthenticationResult.WRONG_PASSWORD,
                authenticationInternalService.authenticate("username", "password"));
        verify(ldapTemplate, times(2)).search(any(String.class), any(String.class), any(AttributesMapper.class));
        verify(passwordEncoder, times(1)).matches(any(), any());
    }
}
