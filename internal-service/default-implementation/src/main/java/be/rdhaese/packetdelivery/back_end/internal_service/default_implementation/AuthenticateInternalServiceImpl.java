package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.AuthenticateInternalService;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.enums.AuthenticationResult;
import org.springframework.stereotype.Service;

/**
 * Created on 22/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class AuthenticateInternalServiceImpl implements AuthenticateInternalService {

    public AuthenticationResult authenticate(String username, String password) {
        //TODO connect with Active Directory
        System.out.println("Mocked connecting to Active Directory");
        if ("noob".equals(username.trim().toLowerCase())){
            return AuthenticationResult.NOT_KNOWN;
        }
        return AuthenticationResult.GRANTED;
    }
}
