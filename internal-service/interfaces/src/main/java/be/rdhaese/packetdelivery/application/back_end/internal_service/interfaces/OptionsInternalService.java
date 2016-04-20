package be.rdhaese.packetdelivery.application.back_end.internal_service.interfaces;

import be.rdhaese.packetdelivery.application.back_end.model.options.Options;

/**
 * Created on 17/04/2016.
 *
 * @author Robin D'Haese
 */
public interface OptionsInternalService {

    Options getFor(String username);
    Boolean save(Options options);
}
