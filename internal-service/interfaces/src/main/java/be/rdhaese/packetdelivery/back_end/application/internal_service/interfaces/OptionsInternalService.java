package be.rdhaese.packetdelivery.back_end.application.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.application.model.options.Options;

/**
 * Created on 17/04/2016.
 *
 * @author Robin D'Haese
 */
public interface OptionsInternalService {

    Options getFor(String username);
    Boolean save(Options options);
}
