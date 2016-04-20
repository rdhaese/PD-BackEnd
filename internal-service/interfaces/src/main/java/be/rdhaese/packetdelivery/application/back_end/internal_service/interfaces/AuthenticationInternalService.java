package be.rdhaese.packetdelivery.application.back_end.internal_service.interfaces;

import be.rdhaese.packetdelivery.application.back_end.internal_service.interfaces.enums.AuthenticationResult;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
public interface AuthenticationInternalService {
    AuthenticationResult authenticate(String username, String password);
}
