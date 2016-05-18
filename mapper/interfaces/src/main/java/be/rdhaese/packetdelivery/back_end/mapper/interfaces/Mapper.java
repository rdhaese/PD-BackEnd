package be.rdhaese.packetdelivery.back_end.mapper.interfaces;

import java.util.Collection;

/**
 * Created on 24/12/2015.
 *
 * @author Robin D'Haese
 */
public interface Mapper<B, D> {

    B mapToBus(D dto);

    D mapToDto(B busObj);

    Collection<B> mapToBus(Collection<D> dtos);

    Collection<D> mapToDto(Collection<B> busObjs);
}
