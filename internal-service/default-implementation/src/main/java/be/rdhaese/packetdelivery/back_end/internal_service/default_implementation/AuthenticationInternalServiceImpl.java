package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.AuthenticationInternalService;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.enums.AuthenticationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import java.util.List;

/**
 * Created on 22/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class AuthenticationInternalServiceImpl implements AuthenticationInternalService {

    private static final String MASTER_ACCOUNT = "pdmaster";

    public static final AttributesMapper<String> USERNAME_MAPPER = new AuthenticationInternalServiceImpl.UsernameAttributesMapper();
    public static final AttributesMapper<String> PASSWORD_MAPPER = new AuthenticationInternalServiceImpl.PasswordAttributesMapper();

    @Autowired
    private LdapTemplate ldapTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthenticationResult authenticate(String username, String password) {
        //Check if its the master account to use the application without AD
        if (MASTER_ACCOUNT.equals(username) && passwordEncoder.matches(MASTER_ACCOUNT, password)){
            //Logging in with master account --> permission is granted
            return AuthenticationResult.GRANTED;
        }

        //First check if the given username exists
        List<String> users = ldapTemplate.search("", String.format("(sAMAccountName=%s)", username), USERNAME_MAPPER);


        //If none returned
        if (users.size() == 0){
            //--> User not known to system
            return AuthenticationResult.NOT_KNOWN;
        }

        //Get password from ldap
        List<String> passwords = ldapTemplate.search("", String.format("(sAMAccountName=%s)", username), PASSWORD_MAPPER);

       if (passwords.isEmpty()){
           //No password in ldap -> grant permission
           return AuthenticationResult.GRANTED;
       }

        if (passwordEncoder.matches(passwords.get(0), password)){
            //Passwords match
            return AuthenticationResult.GRANTED;
        }

        //No permission was granted -> wrong password
        return AuthenticationResult.WRONG_PASSWORD;
    }

    private static class UsernameAttributesMapper implements AttributesMapper<String> {
        @Override
        public String mapFromAttributes(Attributes attributes) throws NamingException {
            return (String) attributes.get("sAMAccountName").get();
        }
    }

    private static class PasswordAttributesMapper implements AttributesMapper<String> {
        @Override
        public String mapFromAttributes(Attributes attributes) throws NamingException {
            Attribute userPassword = attributes.get("userPassword");
           return new String((byte[]) userPassword.get());
        }
    }
}
