package be.rdhaese.packetdelivery.back_end.application.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.application.internal_service.interfaces.AuthenticationInternalService;
import be.rdhaese.packetdelivery.back_end.application.internal_service.interfaces.enums.AuthenticationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.net.ConnectException;
import java.util.List;

/**
 * Created on 22/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class AuthenticationInternalServiceImpl implements AuthenticationInternalService {

    private static final String MASTER_ACCOUNT = "pdmaster";
    @Autowired
    private LdapTemplate ldapTemplate;

    public AuthenticationResult authenticate(String username, String password) {
        //Check if its the master account to use the application without AD
        if (MASTER_ACCOUNT.equals(username) && MASTER_ACCOUNT.equals(password)){
            //Logging in with master account --> permission is granted
            return AuthenticationResult.GRANTED;
        }

        //First check if the given username exists
        List<String> users = ldapTemplate.search("", String.format("(sAMAccountName=%s)", username), new AttributesMapper<String>() {
                @Override
                public String mapFromAttributes(Attributes attributes) throws NamingException {
                    System.out.println(attributes.toString());
                    return (String) attributes.get("sAMAccountName").get();
                }
            });


        //If none returned
        if (users.size() == 0){
            //--> User not known to system
            return AuthenticationResult.NOT_KNOWN;
        }

        //Authenticate and return AuthenticationResult according to result
        if (!ldapTemplate.authenticate("", String.format("(sAMAccountName=%s)", username), password)){
            //-->wrong password was entered
            return AuthenticationResult.WRONG_PASSWORD;
        }

        //All tests passed --> permission is granted
        return AuthenticationResult.GRANTED;
    }

}
