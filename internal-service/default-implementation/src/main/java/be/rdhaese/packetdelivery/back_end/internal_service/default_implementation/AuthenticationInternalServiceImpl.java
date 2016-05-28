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
 *
 * @author Robin D'Haese
 */
@Service
public class AuthenticationInternalServiceImpl implements AuthenticationInternalService {

    public static final AttributesMapper<String> USERNAME_MAPPER = new AuthenticationInternalServiceImpl.UsernameAttributesMapper();

    private static final String MASTER_ACCOUNT = "pdmaster";

    @Autowired
    private LdapTemplate ldapTemplate;

    public AuthenticationResult authenticate(String username, String password) {
        //Check if its the master account to use the application without AD
        if (MASTER_ACCOUNT.equals(username) && MASTER_ACCOUNT.equals(password)) {
            //Logging in with master account --> permission is granted
            return AuthenticationResult.GRANTED;
        }

        //First check if the given username exists
        try {
            List<String> users = ldapTemplate.search("", String.format("(sAMAccountName=%s)", username), USERNAME_MAPPER);


            //If none returned
            if (users.size() == 0) {
                //--> User not known to system
                return AuthenticationResult.NOT_KNOWN;
            }

            //Check password
            if (ldapTemplate.authenticate("", String.format("(sAMAccountName=%s)", username), password)) {
                return AuthenticationResult.GRANTED;
            }

            //No permission was granted -> wrong password
            return AuthenticationResult.WRONG_PASSWORD;
        } catch (Exception e){
            return AuthenticationResult.NOT_KNOWN;
        }
    }

    private static class UsernameAttributesMapper implements AttributesMapper<String> {
        @Override
        public String mapFromAttributes(Attributes attributes) throws NamingException {
            return (String) attributes.get("sAMAccountName").get();
        }
    }
}
