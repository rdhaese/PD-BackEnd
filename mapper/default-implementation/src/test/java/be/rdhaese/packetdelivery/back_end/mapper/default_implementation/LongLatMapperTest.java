package be.rdhaese.packetdelivery.back_end.mapper.default_implementation;

import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.LongLat;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import org.junit.Before;
import org.junit.Test;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createLongLat;
import static junit.framework.TestCase.assertEquals;

/**
 * Created on 13/05/2016.
 *
 * @author Robin D'Haese
 */
public class LongLatMapperTest {

    private Mapper<LongLat, LongLatDTO> mapper;

    private LongLat longLat;
    private LongLatDTO longLatDto;

    @Before
    public void setUp() {
        mapper = new LongLatMapper();
        longLat = createLongLat(2D, 3D);
        longLatDto = new LongLatDTO(3D, 2D);
    }

    @Test
    public void testMapToBus() {
        assertEquals(longLat, mapper.mapToBus(longLatDto));
    }

    @Test
    public void testMapToDto() {
        assertEquals(longLatDto, mapper.mapToDto(longLat));
    }
}
