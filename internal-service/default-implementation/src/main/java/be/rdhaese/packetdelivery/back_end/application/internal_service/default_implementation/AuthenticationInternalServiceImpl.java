package be.rdhaese.packetdelivery.back_end.application.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.application.internal_service.interfaces.AuthenticationInternalService;
import be.rdhaese.packetdelivery.back_end.application.internal_service.interfaces.enums.AuthenticationResult;
import org.springframework.stereotype.Service;

/**
 * Created on 22/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class AuthenticationInternalServiceImpl implements AuthenticationInternalService {

    public AuthenticationResult authenticate(String username, String password) {
        //TODO connect with Active Directory
        System.out.println("Mocked connecting to Active Directory");
        if ("noob".equals(username.trim().toLowerCase())){
            return AuthenticationResult.NOT_KNOWN;
        }
        return AuthenticationResult.GRANTED;
    }
}
