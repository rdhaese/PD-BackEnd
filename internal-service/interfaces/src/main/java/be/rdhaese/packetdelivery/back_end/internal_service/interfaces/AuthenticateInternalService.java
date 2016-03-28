package be.rdhaese.packetdelivery.back_end.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.enums.AuthenticationResult;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
public interface AuthenticateInternalService {
    AuthenticationResult authenticate(String username, String password);
}
