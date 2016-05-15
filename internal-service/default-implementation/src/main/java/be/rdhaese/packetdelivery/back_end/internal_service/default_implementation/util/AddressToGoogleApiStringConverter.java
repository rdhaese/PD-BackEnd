package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import be.rdhaese.packetdelivery.back_end.model.Address;
import org.springframework.stereotype.Component;

/**
 * Created on 10/04/2016.
 *
 * @author Robin D'Haese
 */
@Component
public class AddressToGoogleApiStringConverter {
    public String convert(Address address) {
        if (address == null){
            return null;
        }
        return String.format("%s %s, %s %s", address.getStreet(), address.getNumber(), address.getPostalCode(), address.getCity());
    }
}
