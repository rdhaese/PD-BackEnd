package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.comparator;

import be.rdhaese.packetdelivery.back_end.model.Packet;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import static junit.framework.TestCase.assertTrue;

/**
 *
 * @author Robin D'Haese
 */
public class PacketOnStatusChangedComparatorTest {
    private static final PacketOnStatusChangedComparator COMPARATOR = new PacketOnStatusChangedComparator();
    private Packet packet1;
    private Packet packet2;
    private Packet packet3;

    @Before
    public void setUp() {
        Date date = new Date();
        packet1 = new Packet();
        packet1.setId(1L);
        packet1.setStatusChangedOn(date);

        packet2 = new Packet();
        packet2.setId(2L);
        packet2.setStatusChangedOn(Date.from(LocalDateTime.now().minusDays(1).toInstant(ZoneOffset.UTC)));

        packet3 = new Packet();
        packet3.setId(3L);
        packet3.setStatusChangedOn(date);
    }

    @Test
    public void testCompare() {
        assertTrue(COMPARATOR.compare(packet1, packet2) > 0);
        assertTrue(COMPARATOR.compare(packet2, packet3) < 0);
        assertTrue(COMPARATOR.compare(packet1, packet3) == 0);
    }
}
