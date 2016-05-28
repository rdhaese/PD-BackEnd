package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.model.*;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.PacketJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.RegionJpaRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 *
 * @author Robin D'Haese
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration
public class ProblematicPacketsInternalServiceImplTest extends TestCase {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @InjectMocks
    private ProblematicPacketsInternalServiceImpl problematicPacketsInternalService;

    @Mock
    private PacketJpaRepository packetJpaRepository;

    @Mock
    private RegionJpaRepository regionJpaRepository;

    @Test
    public void testGetProblematicPackets() {
        //Setup mocks
        List<Packet> packets = Arrays.asList(
                createPacket("packetId1", null, null, PacketStatus.PROBLEMATIC, null, 0),
                createPacket("packetId2", null, null, PacketStatus.PROBLEMATIC, null, 0)
        );
        when(packetJpaRepository.getProblematicPackets()).thenReturn(packets);

        //Test
        TestCase.assertEquals(2, problematicPacketsInternalService.getProblematicPackets().size());
        verify(packetJpaRepository, times(1)).getProblematicPackets();
    }

    @Test
    public void testGetProblematicPacketForPacketId() {
        //Setup mocks
        Packet problematicPacket = createPacket("packetId1", null, null, PacketStatus.PROBLEMATIC, null, 0);
        when(packetJpaRepository.getProblematicPacket("packetId1")).thenReturn(problematicPacket);

        //Test
        TestCase.assertNull(problematicPacketsInternalService.getProblematicPacket("unknown id"));
        TestCase.assertEquals(problematicPacket, problematicPacketsInternalService.getProblematicPacket("packetId1"));
        verify(packetJpaRepository, times(2)).getProblematicPacket(any());
    }

    @Test
    public void testReSend() throws ParseException {
        //Setup mocks
        Date packetStatusChangedOn = DATE_FORMAT.parse("17/04/2016");
        Packet problematicPacket = createPacket("packetId1", null, null, PacketStatus.PROBLEMATIC, packetStatusChangedOn, 0);
        when(packetJpaRepository.getPacket("packetId1")).thenReturn(problematicPacket);

        //Test
        problematicPacketsInternalService.reSend("unknown id");
        TestCase.assertEquals(PacketStatus.PROBLEMATIC, problematicPacket.getPacketStatus());
        TestCase.assertTrue(packetStatusChangedOn.equals(problematicPacket.getStatusChangedOn()));

        problematicPacketsInternalService.reSend("packetId1");
        TestCase.assertEquals(PacketStatus.NORMAL, problematicPacket.getPacketStatus());
        TestCase.assertFalse(packetStatusChangedOn.equals(problematicPacket.getStatusChangedOn()));

        problematicPacket.setPacketStatus(PacketStatus.NOT_FOUND);
        problematicPacketsInternalService.reSend("packetId1");
        TestCase.assertEquals(PacketStatus.NOT_FOUND, problematicPacket.getPacketStatus());

        verify(packetJpaRepository, times(3)).getPacket(any());
        verify(packetJpaRepository, times(1)).save(any(Packet.class));
    }

    @Test
    public void testReturnToSender() throws ParseException {
        //Setup mocks
        Date date = DATE_FORMAT.parse("17/04/2016");
        Region clientRegion = createRegion(new RegionName(), "CLIENTREGION");
        Region deliveryregion = createRegion(new RegionName(), "DELIVERYREGION");
        Packet packet = createPacket(
                "packetId",
                createClientInfo(
                        createContactDetails(
                                "name",
                                Arrays.asList("phonenumber1", "phoneNumber2"),
                                Arrays.asList("email1", "email2")
                        ),
                        createAddress("Ezelberg", "2", "12", "9500", "Geraardsbergen")
                ),
                createDeliveryInfo(
                        createClientInfo(
                                createContactDetails(
                                        "name",
                                        Arrays.asList("phonenumber3", "phoneNumber4"),
                                        Arrays.asList("email5", "email6")
                                ),
                                createAddress("Dagmoedstraat", "77", null, "9506", "Schendelbeke")
                        ),
                        deliveryregion
                ),
                PacketStatus.PROBLEMATIC,
                date,
                2
        );
        when(packetJpaRepository.getPacket("packetId")).thenReturn(packet);
        when(regionJpaRepository.getRegionFor("CLIENTREGION")).thenReturn(clientRegion);

        //Test
        problematicPacketsInternalService.returnToSender("unkown id", clientRegion);
        problematicPacketsInternalService.returnToSender("packetId1", null);

        problematicPacketsInternalService.returnToSender("packetId", clientRegion);
        TestCase.assertEquals(packet.getClientInfo(), packet.getDeliveryInfo().getClientInfo());
        TestCase.assertEquals(clientRegion, packet.getDeliveryInfo().getRegion());
        TestCase.assertEquals(PacketStatus.NORMAL, packet.getPacketStatus());
        assertThat(packet.getStatusChangedOn(), is(not(date)));

        packet.setPacketStatus(PacketStatus.NOT_FOUND);
        problematicPacketsInternalService.returnToSender("packetId", clientRegion);

        verify(packetJpaRepository, times(4)).getPacket(any());
        verify(regionJpaRepository, times(1)).getRegionFor(any());
        verify(packetJpaRepository, times(1)).save(any(Packet.class));
    }

    @Test
    public void testGetProblematicPacketAddressForPacketId() {
        //Setup mocks
        Address address = createAddress("Dagmoedstraat", "77", null, "9506", "Schendelbeke");
        Packet packet = createPacket(
                "packetId",
                createClientInfo(
                        createContactDetails(
                                "name",
                                Arrays.asList("phonenumber1", "phoneNumber2"),
                                Arrays.asList("email1", "email2")
                        ),
                        createAddress("Ezelberg", "2", "12", "9500", "Geraardsbergen")
                ),
                createDeliveryInfo(
                        createClientInfo(
                                createContactDetails(
                                        "name",
                                        Arrays.asList("phonenumber3", "phoneNumber4"),
                                        Arrays.asList("email5", "email6")
                                ),
                                address
                        ),
                        new Region()
                ),
                PacketStatus.PROBLEMATIC,
                new Date(),
                2
        );
        when(packetJpaRepository.getProblematicPacket("packetId")).thenReturn(packet);

        //Test
        TestCase.assertEquals(address, problematicPacketsInternalService.getProblematicPacketAddress("packetId"));
        TestCase.assertNull(problematicPacketsInternalService.getProblematicPacketAddress("unkown id"));

        verify(packetJpaRepository, times(2)).getProblematicPacket(any());
    }

    @Test
    public void testGetProblematicPacketRegionForPacketId() {
        //Setup mocks
        Region region = createRegion(new RegionName(), "CODE");
        Packet packet = createPacket(
                "packetId",
                createClientInfo(
                        createContactDetails(
                                "name",
                                Arrays.asList("phonenumber1", "phoneNumber2"),
                                Arrays.asList("email1", "email2")
                        ),
                        createAddress("Ezelberg", "2", "12", "9500", "Geraardsbergen")
                ),
                createDeliveryInfo(
                        createClientInfo(
                                createContactDetails(
                                        "name",
                                        Arrays.asList("phonenumber3", "phoneNumber4"),
                                        Arrays.asList("email5", "email6")
                                ),
                                createAddress("Dagmoedstraat", "77", null, "9506", "Schendelbeke")
                        ),
                        region
                ),
                PacketStatus.PROBLEMATIC,
                new Date(),
                2
        );
        when(packetJpaRepository.getProblematicPacket("packetId")).thenReturn(packet);

        //Test
        TestCase.assertEquals(region, problematicPacketsInternalService.getProblematicPacketRegion("packetId"));
        TestCase.assertNull(problematicPacketsInternalService.getProblematicPacketRegion("unknown id"));

        verify(packetJpaRepository, times(2)).getProblematicPacket(any());
    }

    @Test
    public void testSaveDeliveryAddress() {
        //Setup mocks
        Packet packet = createPacket(
                "packetId",
                createClientInfo(
                        createContactDetails(
                                "name",
                                Arrays.asList("phonenumber1", "phoneNumber2"),
                                Arrays.asList("email1", "email2")
                        ),
                        createAddress("Ezelberg", "2", "12", "9500", "Geraardsbergen")
                ),
                createDeliveryInfo(
                        createClientInfo(
                                createContactDetails(
                                        "name",
                                        Arrays.asList("phonenumber3", "phoneNumber4"),
                                        Arrays.asList("email5", "email6")
                                ),
                                createAddress("Dagmoedstraat", "77", null, "9506", "Schendelbeke")
                        ),
                        createRegion(new RegionName(), "CODE1")
                ),
                PacketStatus.PROBLEMATIC,
                new Date(),
                2
        );
        when(packetJpaRepository.getProblematicPacket("packetId")).thenReturn(packet);

        //Test
        Address address = createAddress("street", "number", "mailbox", "postalCode", "city");
        Region region = createRegion(new RegionName(), "CODE2");
        problematicPacketsInternalService.saveDeliveryAddress("packetId", address, region);
        problematicPacketsInternalService.saveDeliveryAddress("unknown id", address, region);
        problematicPacketsInternalService.saveDeliveryAddress("packetId", null, region);
        problematicPacketsInternalService.saveDeliveryAddress("packetId", address, null);

        TestCase.assertEquals(address, packet.getDeliveryInfo().getClientInfo().getAddress());
        TestCase.assertEquals(region, packet.getDeliveryInfo().getRegion());

        verify(packetJpaRepository, times(4)).getProblematicPacket(any());
        verify(packetJpaRepository, times(1)).save(any(Packet.class));
    }
}
