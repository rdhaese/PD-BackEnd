package be.rdhaese.packetdelivery.back_end.model;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.Calendar;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createLocationUpdate;
import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createLongLat;


/**
 *
 * @author Robin D'Haese
 */
public class LocationUpdateTest extends AbstractModelTest {

    private LocationUpdate locationUpdate;

    @Before
    public void setUp() {
        locationUpdate = createLocationUpdate(Calendar.getInstance().getTime(), createLongLat(12.345, 6d));
    }

    @Test
    public void testCanPersist() {
        //Check if id is null on creation
        assertNull(locationUpdate.getId());

        //Persist
        persistFlushAndClear(locationUpdate);

        //Check if id is assigned
        assertNotNull(locationUpdate.getId());

        //Check if location update can be found on assigned id
        LocationUpdate newLocationUpdate = getEntityManager().find(LocationUpdate.class, locationUpdate.getId());
        assertNotNull(newLocationUpdate);
        assertEquals(locationUpdate.getLongLat(), newLocationUpdate.getLongLat());
    }

    @Test(expected = ConstraintViolationException.class)
    public void testTimeCreatedCannotBeNull() {
        locationUpdate.setTimeCreated(null);

        persistFlushAndClear(locationUpdate);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testLongLatCannotBeNull() {
        locationUpdate.setLongLat(null);

        persistFlushAndClear(locationUpdate);
    }

    @Test(expected = PersistenceException.class) //See LongLat class why not ConstraintValidationException
    public void testLongitudeCannotBeNull() {
        locationUpdate.getLongLat().setLongitude(null);

        persistFlushAndClear(locationUpdate);
    }

    @Test(expected = PersistenceException.class) //See LongLat class why not ConstraintValidationException
    public void testLatitudeCannotBeNull() {
        locationUpdate.getLongLat().setLatitude(null);

        persistFlushAndClear(locationUpdate);
    }
}
