package be.rdhaese.packetdelivery.back_end.internal_service.interfaces;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
public interface AuthenticateInternalService {
    boolean authenticate(String username, String password);
}
