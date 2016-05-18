package be.rdhaese.packetdelivery.back_end.model.comparator;

import be.rdhaese.packetdelivery.back_end.model.LocationUpdate;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static junit.framework.TestCase.assertTrue;

/**
 * Created on 3/05/2016.
 *
 * @author Robin D'Haese
 */
public class LocationUpdateOnTimeCreatedComparatorTest {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final LocationUpdateOnTimeCreatedComparator COMPARATOR = new LocationUpdateOnTimeCreatedComparator();

    private LocationUpdate locationUpdate1;
    private LocationUpdate locationUpdate2;
    private LocationUpdate locationUpdate3;

    @Before
    public void setUp() throws ParseException {
        locationUpdate1 = new LocationUpdate();
        locationUpdate1.setTimeCreated(DATE_FORMAT.parse("19/02/2016"));

        locationUpdate2 = new LocationUpdate();
        locationUpdate2.setTimeCreated(DATE_FORMAT.parse("20/02/2016"));

        locationUpdate3 = new LocationUpdate();
        locationUpdate3.setTimeCreated(DATE_FORMAT.parse("19/02/2016"));
    }

    @Test
    public void testCompare() {
        assertTrue(COMPARATOR.compare(locationUpdate1, locationUpdate2) > 0);
        assertTrue(COMPARATOR.compare(locationUpdate2, locationUpdate3) < 0);
        assertTrue(COMPARATOR.compare(locationUpdate1, locationUpdate3) == 0);
    }
}
