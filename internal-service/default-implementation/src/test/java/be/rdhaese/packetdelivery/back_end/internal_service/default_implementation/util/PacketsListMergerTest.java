package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.comparator.PacketOnPriorityComparator;
import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.comparator.PacketOnStatusChangedComparator;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createPacket;

/**
 * Created on 9/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {PacketsListMerger.class, PacketsListMergerTest.Config.class})
public class PacketsListMergerTest extends TestCase {

    @Configuration
    static class Config {

        @Bean
        public Comparator<Packet> packetOnStatusChangedComparator() {
            return new PacketOnStatusChangedComparator();
        }

        @Bean
        public Comparator<Packet> packetOnPriorityComparator() {
            return new PacketOnPriorityComparator();
        }
    }

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private PacketsListMerger packetsListMerger;

    private List<Packet> packets1;
    private List<Packet> packets2;

    @Before
    public void setUp() throws ParseException {
        packets1 = new ArrayList<>();
        packets1.add(createPacket("packetId1", null, null , null, dateFormat.parse("27/04/2016"), 1));
        packets1.add(createPacket("packetId2", null, null , null, dateFormat.parse("27/04/2016"), 1));
        packets1.add(createPacket("packetId3", null, null , null, dateFormat.parse("27/04/2016"), 1));
        packets1.add(createPacket("packetId4", null, null , null, dateFormat.parse("27/04/2016"), 1));

        packets2 = new ArrayList<>();
        packets2.add(createPacket("packetId5", null, null , null, dateFormat.parse("27/04/2016"), 2));
        packets2.add(createPacket("packetId6", null, null , null, dateFormat.parse("26/04/2016"), 2));
        packets2.add(createPacket("packetId7", null, null , null, dateFormat.parse("30/04/2016"), 3));
        packets2.add(createPacket("packetId8", null, null , null, dateFormat.parse("17/04/2016"), 1));
    }

    @Test
    public void testMergeLists() {
        List<Packet> mergedPackets = packetsListMerger.mergeLists(10, packets1, packets2);
        assertEquals(8, mergedPackets.size());
        assertTrue(mergedPackets.containsAll(packets1));
        assertTrue(mergedPackets.containsAll(packets2));
    }

    @Test
    public void testMergeListsToMuchPackets() {
        List<Packet> mergedPackets = packetsListMerger.mergeLists(6, packets1, packets2);
        assertEquals(6, mergedPackets.size());
        assertTrue(mergedPackets.containsAll(packets1));
        assertTrue(mergedPackets.contains(packets2.get(0)));
        assertTrue(mergedPackets.contains(packets2.get(1)));
        assertFalse(mergedPackets.contains(packets2.get(2)));
        assertFalse(mergedPackets.contains(packets2.get(3)));
    }

    @Test
    public void testMergeListsAmountOfPacketsLessThanOne() {
        List<Packet> mergedPackets = packetsListMerger.mergeLists(0, packets1, packets2);
        assertEquals(4, mergedPackets.size());
        assertTrue(mergedPackets.containsAll(packets1));
        for (Packet packet : packets2){
            assertFalse(mergedPackets.contains(packet));
        }
    }

}
