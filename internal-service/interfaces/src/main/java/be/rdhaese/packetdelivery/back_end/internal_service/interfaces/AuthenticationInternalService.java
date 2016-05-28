package be.rdhaese.packetdelivery.back_end.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.enums.AuthenticationResult;

/**
 *
 * @author Robin D'Haese
 */
public interface AuthenticationInternalService {
    AuthenticationResult authenticate(String username, String password);
}
