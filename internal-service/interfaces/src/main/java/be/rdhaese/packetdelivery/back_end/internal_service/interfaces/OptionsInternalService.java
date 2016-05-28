package be.rdhaese.packetdelivery.back_end.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.model.options.Options;

/**
 *
 * @author Robin D'Haese
 */
public interface OptionsInternalService {

    Options getFor(String username) throws Exception;

    Boolean save(Options options) throws Exception;
}
