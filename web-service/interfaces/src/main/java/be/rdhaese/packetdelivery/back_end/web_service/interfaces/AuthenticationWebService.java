package be.rdhaese.packetdelivery.back_end.web_service.interfaces;

/**
 * Created on 4/01/2016.
 *
 * @author Robin D'Haese
 */
public interface AuthenticationWebService {
    String authenticate(String username, String password);
}
