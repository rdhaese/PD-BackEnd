package be.rdhaese.packetdelivery.back_end.application.internal_service.util;

import be.rdhaese.packetdelivery.back_end.application.model.Address;
import org.springframework.stereotype.Component;

/**
 * Created on 10/04/2016.
 *
 * @author Robin D'Haese
 */
@Component
public class AddressToGoogleApiStringConverter {
    public String convert(Address address) {
        return String.format("%s %s, %s %s", address.getStreet(), address.getNumber(), address.getPostalCode(), address.getCity());
    }
}
