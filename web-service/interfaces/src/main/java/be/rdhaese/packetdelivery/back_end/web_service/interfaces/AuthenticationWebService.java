package be.rdhaese.packetdelivery.back_end.web_service.interfaces;

/**
 *
 * @author Robin D'Haese
 */
public interface AuthenticationWebService {
    String authenticate(String username, String password);
}
