package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created on 9/05/2016.
 *
 * @author Robin D'Haese
 */
public class PacketIdDateKeeperTest extends TestCase {

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private PacketIdDateKeeper packetIdDateKeeper;

    @Before
    public void setUp(){
        packetIdDateKeeper = new PacketIdDateKeeper();
    }

    @Test
    public void testIsDateSetOnConstructing(){
        assertNotNull(packetIdDateKeeper.getLastDateChecked());
        assertEquals(LocalDate.now(), packetIdDateKeeper.getLastDateChecked());
    }

    @Test
    public void testIsBeforeLastDateCheckedTrue() throws ParseException {
        packetIdDateKeeper.setLastDateChecked(LocalDate.parse("27/04/2016", dateTimeFormatter));
        assertTrue(packetIdDateKeeper.isAfterLastDateChecked(dateFormat.parse("28/04/2016")));
        assertEquals(LocalDate.now(), packetIdDateKeeper.getLastDateChecked());
    }

    @Test
    public void testIsBeforeLastDateCheckedFalse() throws ParseException {
        packetIdDateKeeper.setLastDateChecked(LocalDate.parse("27/04/2016", dateTimeFormatter));
        assertFalse(packetIdDateKeeper.isAfterLastDateChecked(dateFormat.parse("26/04/2016")));
        assertEquals(LocalDate.now(), packetIdDateKeeper.getLastDateChecked());

        packetIdDateKeeper.setLastDateChecked(LocalDate.parse("27/04/2016", dateTimeFormatter));
        assertFalse(packetIdDateKeeper.isAfterLastDateChecked(dateFormat.parse("27/04/2016")));
        assertEquals(LocalDate.now(), packetIdDateKeeper.getLastDateChecked());
    }
}
