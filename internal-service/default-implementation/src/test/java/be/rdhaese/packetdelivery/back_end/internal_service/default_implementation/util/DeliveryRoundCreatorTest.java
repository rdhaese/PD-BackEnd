package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.comparator.PacketOnPriorityComparator;
import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.comparator.PacketOnStatusChangedComparator;
import be.rdhaese.packetdelivery.back_end.model.DeliveryRound;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.model.PacketStatus;
import be.rdhaese.packetdelivery.back_end.model.RoundStatus;
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
@SpringApplicationConfiguration(classes = {DeliveryRoundCreator.class, DeliveryRoundCreatorTest.Config.class})
public class DeliveryRoundCreatorTest extends TestCase {

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
    private DeliveryRoundCreator deliveryRoundCreator;

    private List<Packet> packets;

    @Before
    public void setUp() throws ParseException {
        packets = new ArrayList<>();
        packets.add(createPacket("packetId5", null, null , null, dateFormat.parse("27/04/2016"), 2));
        packets.add(createPacket("packetId6", null, null , null, dateFormat.parse("26/04/2016"), 2));
        packets.add(createPacket("packetId7", null, null , null, dateFormat.parse("30/04/2016"), 3));
        packets.add(createPacket("packetId8", null, null , null, dateFormat.parse("17/04/2016"), 1));
    }

    @Test
    public void testCreateRoundAmountOfPacketsLessThanOne(){
        assertNull(deliveryRoundCreator.createRound(0, null));
    }

    @Test
    public void testCreateRoundNullPackets(){
        assertNull(deliveryRoundCreator.createRound(1, null));
    }

    @Test
    public void testCreateRoundEmptyPackets(){
        assertNull(deliveryRoundCreator.createRound(1, new ArrayList<>()));
    }

    @Test
    public void testCreateRound(){
        DeliveryRound deliveryRound = deliveryRoundCreator.createRound(3, packets);
        assertNotNull(deliveryRound);
        assertEquals(3, deliveryRound.getPackets().size());
        assertTrue(deliveryRound.getPackets().contains(packets.get(0)));
        assertTrue(deliveryRound.getPackets().contains(packets.get(1)));
        assertTrue(deliveryRound.getPackets().contains(packets.get(2)));
        for (Packet packet : deliveryRound.getPackets()){
            assertEquals(PacketStatus.ON_DELIVERY, packet.getPacketStatus());
        }
        assertEquals(RoundStatus.NOT_STARTED, deliveryRound.getRoundStatus());
    }

}
