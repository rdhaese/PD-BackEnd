package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

/**
 *
 * @author Robin D'Haese
 */
public interface Mailer {

    void send(String toAddress, String subject, String message);
}
