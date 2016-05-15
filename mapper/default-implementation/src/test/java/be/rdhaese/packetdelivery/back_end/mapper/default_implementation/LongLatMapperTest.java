package be.rdhaese.packetdelivery.back_end.mapper.default_implementation;

import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.LongLat;
import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createAddress;
import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createLongLat;

/**
 * Created on 13/05/2016.
 *
 * @author Robin D'Haese
 */
public class LongLatMapperTest extends TestCase {

    private Mapper<LongLat, LongLatDTO> mapper;

    private LongLat longLat;
    private LongLatDTO longLatDto;

    @Before
    public void setUp(){
        mapper = new LongLatMapper();
        longLat = createLongLat(2D, 3D);
        longLatDto = new LongLatDTO(3D, 2D);
    }

    @Test
    public void testMapToBus(){
        assertEquals(longLat, mapper.mapToBus(longLatDto));
    }

    @Test
    public void testMapToDto(){
        assertEquals(longLatDto, mapper.mapToDto(longLat));
    }
}
