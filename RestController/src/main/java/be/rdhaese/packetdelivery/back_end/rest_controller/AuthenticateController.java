package be.rdhaese.packetdelivery.back_end.rest_controller;

/**
 * Created on 4/01/2016.
 *
 * @author Robin D'Haese
 */
public interface AuthenticateController {
    boolean authenticate(String username, String password);
}
