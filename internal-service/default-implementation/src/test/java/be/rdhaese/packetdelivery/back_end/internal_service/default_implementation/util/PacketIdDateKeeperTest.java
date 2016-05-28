package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Robin D'Haese
 */
public class PacketIdDateKeeperTest {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private PacketIdDateKeeper packetIdDateKeeper;

    @Before
    public void setUp() {
        packetIdDateKeeper = new PacketIdDateKeeper();
    }

    @Test
    public void testIsDateSetOnConstructing() {
        assertNotNull(packetIdDateKeeper.getLastDateChecked());
        assertEquals(LocalDate.now(), packetIdDateKeeper.getLastDateChecked());
    }

    @Test
    public void testIsBeforeLastDateCheckedTrue() throws ParseException {
        packetIdDateKeeper.setLastDateChecked(LocalDate.parse("27/04/2016", DATE_TIME_FORMATTER));
        assertTrue(packetIdDateKeeper.isAfterLastDateChecked(DATE_FORMAT.parse("28/04/2016")));
        assertEquals(LocalDate.now(), packetIdDateKeeper.getLastDateChecked());
    }

    @Test
    public void testIsBeforeLastDateCheckedFalse() throws ParseException {
        packetIdDateKeeper.setLastDateChecked(LocalDate.parse("27/04/2016", DATE_TIME_FORMATTER));
        assertFalse(packetIdDateKeeper.isAfterLastDateChecked(DATE_FORMAT.parse("26/04/2016")));
        assertEquals(LocalDate.now(), packetIdDateKeeper.getLastDateChecked());

        packetIdDateKeeper.setLastDateChecked(LocalDate.parse("27/04/2016", DATE_TIME_FORMATTER));
        assertFalse(packetIdDateKeeper.isAfterLastDateChecked(DATE_FORMAT.parse("27/04/2016")));
        assertEquals(LocalDate.now(), packetIdDateKeeper.getLastDateChecked());
    }
}
