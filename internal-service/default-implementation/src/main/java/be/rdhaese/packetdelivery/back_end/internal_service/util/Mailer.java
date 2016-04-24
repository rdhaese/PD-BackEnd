package be.rdhaese.packetdelivery.back_end.internal_service.util;

/**
 * Created on 13/04/2016.
 *
 * @author Robin D'Haese
 */
public interface Mailer {

    void send(String toAddress, String subject, String message);
}
