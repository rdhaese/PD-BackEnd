package be.rdhaese.packetdelivery.back_end.mapper.interfaces;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Robin D'Haese
 */
public abstract class AbstractMapper<B, D> implements Mapper<B, D> {

    @Override
    public Collection<B> mapToBus(Collection<D> dtos) {
        if (dtos == null) {
            return null;
        }
        Collection<B> busObjs = new ArrayList<>();
        for (D dto : dtos) {
            busObjs.add(mapToBus(dto));
        }
        return busObjs;
    }

    @Override
    public Collection<D> mapToDto(Collection<B> busObjs) {
        if (busObjs == null) {
            return null;
        }
        Collection<D> dtos = new ArrayList<>();
        for (B busObj : busObjs) {
            dtos.add(mapToDto(busObj));
        }
        return dtos;
    }
}
