package be.rdhaese.packetdelivery.back_end.mapper.default_implementation;

import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.LocationUpdate;
import be.rdhaese.packetdelivery.dto.LocationUpdateDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createLocationUpdate;
import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createLongLat;
import static junit.framework.TestCase.assertEquals;

/**
 * Created on 13/05/2016.
 *
 * @author Robin D'Haese
 */
public class LocationUpdateMapperTest {

    private Mapper<LocationUpdate, LocationUpdateDTO> mapper;

    private LocationUpdate locationUpdate;
    private LocationUpdateDTO locationUpdateDTO;

    @Before
    public void setUp() {
        mapper = new LocationUpdateMapper();
        Date date = Calendar.getInstance().getTime();
        locationUpdate = createLocationUpdate(date, createLongLat(2D, 3D));
        locationUpdateDTO = new LocationUpdateDTO(date, 2D, 3D);
    }

    @Test
    public void testMapToBus() {
        assertEquals(locationUpdate, mapper.mapToBus(locationUpdateDTO));
    }

    @Test
    public void testMapToDto() {
        assertEquals(locationUpdateDTO, mapper.mapToDto(locationUpdate));
    }
}
