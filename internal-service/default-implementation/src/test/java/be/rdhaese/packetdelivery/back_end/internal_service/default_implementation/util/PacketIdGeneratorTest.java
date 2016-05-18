package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import be.rdhaese.packetdelivery.back_end.model.Packet;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.*;
import static org.mockito.Mockito.*;

/**
 * Created on 9/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration
public class PacketIdGeneratorTest extends TestCase {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @InjectMocks
    private PacketIdGenerator packetIdGenerator;

    @Mock
    private PacketIdDateKeeper packetIdDateKeeper;

    @Test
    public void testGeneratePacketIdForNullPacket() throws Exception {
        verifyNoMoreInteractions(packetIdDateKeeper);
        TestCase.assertNull(packetIdGenerator.generatePacketId(null));
    }

    @Test
    public void testGeneratePacketId() throws Exception {
        //Setup mocks
        Packet packet = createPacket(
                null,
                createClientInfo(
                        createContactDetails(
                                "Robin",
                                new ArrayList<>(),
                                new ArrayList<>()
                        ),
                        null
                ),
                createDeliveryInfo(
                        createClientInfo(
                                createContactDetails(
                                        "Gwen",
                                        new ArrayList<>(),
                                        new ArrayList<>()
                                ),
                                null
                        ),
                        null
                ),
                null,
                DATE_FORMAT.parse("27/04/2016"),
                0
        );
        when(packetIdDateKeeper.isAfterLastDateChecked(packet.getStatusChangedOn())).thenReturn(true);

        //Test
        String packetId = packetIdGenerator.generatePacketId(packet);
        TestCase.assertNotNull(packetId);
        TestCase.assertEquals("RO-GW-27042016-00000", packetId);

        verify(packetIdDateKeeper, times(1)).isAfterLastDateChecked(any());
    }

    @Test
    public void testGeneratePacketIdClientName1Letter() throws Exception {
        //Setup mocks
        Packet packet = createPacket(
                null,
                createClientInfo(
                        createContactDetails(
                                "R",
                                new ArrayList<>(),
                                new ArrayList<>()
                        ),
                        null
                ),
                createDeliveryInfo(
                        createClientInfo(
                                createContactDetails(
                                        "Gwen",
                                        new ArrayList<>(),
                                        new ArrayList<>()
                                ),
                                null
                        ),
                        null
                ),
                null,
                DATE_FORMAT.parse("27/04/2016"),
                0
        );
        when(packetIdDateKeeper.isAfterLastDateChecked(packet.getStatusChangedOn())).thenReturn(true);

        //Test
        String packetId = packetIdGenerator.generatePacketId(packet);
        TestCase.assertNotNull(packetId);
        TestCase.assertEquals("R_-GW-27042016-00000", packetId);

        verify(packetIdDateKeeper, times(1)).isAfterLastDateChecked(any());
    }

    @Test
    public void testGeneratePacketIdDeliveryName1Letter() throws Exception {
        //Setup mocks
        Packet packet = createPacket(
                null,
                createClientInfo(
                        createContactDetails(
                                "Robin",
                                new ArrayList<>(),
                                new ArrayList<>()
                        ),
                        null
                ),
                createDeliveryInfo(
                        createClientInfo(
                                createContactDetails(
                                        "G",
                                        new ArrayList<>(),
                                        new ArrayList<>()
                                ),
                                null
                        ),
                        null
                ),
                null,
                DATE_FORMAT.parse("27/04/2016"),
                0
        );
        when(packetIdDateKeeper.isAfterLastDateChecked(packet.getStatusChangedOn())).thenReturn(true);

        //Test
        String packetId = packetIdGenerator.generatePacketId(packet);
        TestCase.assertNotNull(packetId);
        TestCase.assertEquals("RO-G_-27042016-00000", packetId);

        verify(packetIdDateKeeper, times(1)).isAfterLastDateChecked(any());
    }

    @Test
    public void testGeneratePacketIdAreLast5NumbersIncrementing() throws Exception {
        //Setup mocks
        Packet packet = createPacket(
                null,
                createClientInfo(
                        createContactDetails(
                                "Robin",
                                new ArrayList<>(),
                                new ArrayList<>()
                        ),
                        null
                ),
                createDeliveryInfo(
                        createClientInfo(
                                createContactDetails(
                                        "Gwen",
                                        new ArrayList<>(),
                                        new ArrayList<>()
                                ),
                                null
                        ),
                        null
                ),
                null,
                DATE_FORMAT.parse("27/04/2016"),
                0
        );
        when(packetIdDateKeeper.isAfterLastDateChecked(packet.getStatusChangedOn())).thenReturn(false);

        //Test
        String packetId = packetIdGenerator.generatePacketId(packet);
        TestCase.assertNotNull(packetId);
        TestCase.assertEquals("RO-GW-27042016-00000", packetId);

        verify(packetIdDateKeeper, times(1)).isAfterLastDateChecked(any());

        for (int index = 0; index < 123; index++) {
            packetId = packetIdGenerator.generatePacketId(packet);
            TestCase.assertNotNull(packetId);
        }

        TestCase.assertEquals("RO-GW-27042016-00123", packetId);
        verify(packetIdDateKeeper, times(124)).isAfterLastDateChecked(any());
    }

    @Test(expected = Exception.class)
    public void testGeneratePacketMaxPacketIdsReached() throws Exception {
        //Setup mocks
        Packet packet = createPacket(
                null,
                createClientInfo(
                        createContactDetails(
                                "Robin",
                                new ArrayList<>(),
                                new ArrayList<>()
                        ),
                        null
                ),
                createDeliveryInfo(
                        createClientInfo(
                                createContactDetails(
                                        "Gwen",
                                        new ArrayList<>(),
                                        new ArrayList<>()
                                ),
                                null
                        ),
                        null
                ),
                null,
                DATE_FORMAT.parse("27/04/2016"),
                0
        );
        when(packetIdDateKeeper.isAfterLastDateChecked(packet.getStatusChangedOn())).thenReturn(false);

        //Test
        String packetId;
        for (int index = 0; index < 100000; index++) {
            packetId = packetIdGenerator.generatePacketId(packet);
            TestCase.assertNotNull(packetId);
        }

        //Exception will be thrown on next generation of a packet id
        try {
            packetIdGenerator.generatePacketId(packet);
        } catch (Exception e) {
            TestCase.assertEquals("Max of packet ids for a day reached [99999]", e.getMessage());
            throw e;
        }
    }

    @Test
    public void testGeneratePacketIdAreLast5NumbersResetOnNewDay() throws Exception {
        //Setup mocks
        Packet packet = createPacket(
                null,
                createClientInfo(
                        createContactDetails(
                                "Robin",
                                new ArrayList<>(),
                                new ArrayList<>()
                        ),
                        null
                ),
                createDeliveryInfo(
                        createClientInfo(
                                createContactDetails(
                                        "Gwen",
                                        new ArrayList<>(),
                                        new ArrayList<>()
                                ),
                                null
                        ),
                        null
                ),
                null,
                DATE_FORMAT.parse("27/04/2016"),
                0
        );
        //Means a new day has started
        when(packetIdDateKeeper.isAfterLastDateChecked(packet.getStatusChangedOn())).thenReturn(true);

        //Test
        String packetId = packetIdGenerator.generatePacketId(packet);
        TestCase.assertNotNull(packetId);
        TestCase.assertEquals("RO-GW-27042016-00000", packetId);

        packetId = packetIdGenerator.generatePacketId(packet);
        TestCase.assertNotNull(packetId);
        TestCase.assertEquals("RO-GW-27042016-00000", packetId);

        verify(packetIdDateKeeper, times(2)).isAfterLastDateChecked(any());
    }
}
