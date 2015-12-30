package be.rdhaese.packetdelivery.back_end.service;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
public interface AuthenticateService {
    boolean authenticate(String username, String password);
}
