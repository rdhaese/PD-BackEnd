package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.properties.InternalServiceProperties;
import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util.*;
import be.rdhaese.packetdelivery.back_end.model.*;
import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyContactDetails;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.DeliveryRoundJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.PacketJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.RegionJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.CompanyContactDetailsRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.*;

/**
 *
 * @author Robin D'Haese
 */
@SuppressWarnings("unchecked") //for warnings on any...() methods
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration
public class DeliveryRoundInternalServiceImplTest extends TestCase {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @InjectMocks
    private DeliveryRoundInternalServiceImpl deliveryRoundInternalService;

    @Mock
    private DeliveryRoundJpaRepository deliveryRoundJpaRepository;

    @Mock
    private PacketJpaRepository packetJpaRepository;

    @Mock
    private RegionJpaRepository regionJpaRepository;

    @Mock
    private CompanyContactDetailsRepository companyContactDetailsRepository;

    @Mock
    private DeliveryRoundCreator deliveryRoundCreator;

    @Mock
    private RegionWithPriorityUtil regionWithPriorityUtil;

    @Mock
    private PacketsListMerger packetsListMerger;

    @Mock
    private DeliveryOrderResolver deliveryOrderResolver;

    @Mock
    private Mailer mailer;

    @Mock
    private MailContentLoader mailContentLoader;

    @Mock
    private TagReplacer tagReplacer;

    @Mock
    private InternalServiceProperties internalServiceProperties;

    @Test
    public void testCreateNewRoundNoPackets() {
        Region region1 = createRegion(null, "CODE1");
        region1.setId(1L);
        Region region2 = createRegion(null, "CODE2");
        region2.setId(2L);
        Region region3 = createRegion(null, "CODE3");
        region3.setId(3L);
        List<Region> regions = Arrays.asList(region1, region2, region3);

        when(regionJpaRepository.findAll()).thenReturn(regions);
        when(regionWithPriorityUtil.getRegionWithHighestTotalPriority(regions)).thenReturn(null);

        TestCase.assertEquals(-1L, deliveryRoundInternalService.createNewRound(5).longValue());

        verify(regionJpaRepository, times(1)).findAll();
        verify(regionWithPriorityUtil, times(1)).getRegionWithHighestTotalPriority(regions);
    }

    @Test
    public void testCreateNewRoundEnoughPackets() {
        Region region1 = createRegion(null, "CODE1");
        region1.setId(1L);
        Region region2 = createRegion(null, "CODE2");
        region2.setId(2L);
        Region region3 = createRegion(null, "CODE3");
        region3.setId(3L);
        List<Region> regions = Arrays.asList(region1, region2, region3);
        List<Packet> packets = Arrays.asList(new Packet(), new Packet(), new Packet());
        DeliveryRound deliveryRound = new DeliveryRound();
        deliveryRound.setId(1L);

        when(regionJpaRepository.findAll()).thenReturn(regions);
        when(regionWithPriorityUtil.getRegionWithHighestTotalPriority(regions)).thenReturn(region2);
        when(packetJpaRepository.getForRegion(2L)).thenReturn(packets);
        when(deliveryRoundCreator.createRound(2, packets)).thenReturn(deliveryRound);

        TestCase.assertEquals(1L, deliveryRoundInternalService.createNewRound(2).longValue());

        verify(deliveryRoundJpaRepository, times(1)).save(deliveryRound);
        verify(regionJpaRepository, times(1)).findAll();
        verify(regionWithPriorityUtil, times(1)).getRegionWithHighestTotalPriority(anyCollection());
        verify(packetJpaRepository, times(1)).getForRegion(any());
        verify(deliveryRoundCreator, times(1)).createRound(anyInt(), anyList());
    }

    @Test
    public void testCreateNewRoundWithNoPacketsFromAdjacentRegion() {
        Region region1 = createRegion(null, "CODE1");
        region1.setId(1L);
        Region region2 = createRegion(null, "CODE2");
        region2.setId(2L);
        Region region3 = createRegion(null, "CODE3");
        region3.setId(3L);
        List<Region> regions = Arrays.asList(region1, region2, region3);
        List<Packet> packets = Arrays.asList(new Packet(), new Packet(), new Packet());
        DeliveryRound deliveryRound = new DeliveryRound();
        deliveryRound.setId(1L);
        List<Region> adjacentRegions = new ArrayList<>();
        adjacentRegions.add(region1);
        adjacentRegions.add(region3);

        when(regionJpaRepository.findAll()).thenReturn(regions);
        when(regionWithPriorityUtil.getRegionWithHighestTotalPriority(regions)).thenReturn(region2);
        when(packetJpaRepository.getForRegion(2L)).thenReturn(packets);
        when(deliveryRoundCreator.createRound(4, packets)).thenReturn(deliveryRound);
        when(regionWithPriorityUtil.getRegionWithHighestTotalPriority(adjacentRegions)).thenReturn(null);

        TestCase.assertEquals(1L, deliveryRoundInternalService.createNewRound(4).longValue());

        verify(deliveryRoundJpaRepository, times(1)).save(deliveryRound);
        verify(regionJpaRepository, times(1)).findAll();
        verify(regionWithPriorityUtil, times(2)).getRegionWithHighestTotalPriority(anyCollection());
        verify(packetJpaRepository, times(1)).getForRegion(anyLong());
        verify(deliveryRoundCreator, times(1)).createRound(anyInt(), anyList());
    }

    @Test
    public void testCreateNewRoundWithPacketsFromAdjacentRegion() {
        Packet packet1 = new Packet();
        packet1.setPacketId("packetId1");
        Packet packet2 = new Packet();
        packet2.setPacketId("packetId2");
        Packet packet3 = new Packet();
        packet3.setPacketId("packetId3");
        Packet packet4 = new Packet();
        packet4.setPacketId("packetId4");
        Packet packet5 = new Packet();
        packet5.setPacketId("packetId5");
        Region region1 = createRegion(null, "CODE1");
        region1.setId(1L);
        Region region2 = createRegion(null, "CODE2");
        region2.setId(2L);
        Region region3 = createRegion(null, "CODE3");
        region3.setId(3L);
        region2.getAdjacentRegions().add(region1);
        region2.getAdjacentRegions().add(region3);
        List<Region> regions = Arrays.asList(region1, region2, region3);
        List<Packet> packets = Arrays.asList(packet1, packet2, packet3);
        DeliveryRound deliveryRound = new DeliveryRound();
        deliveryRound.setId(1L);
        List<Packet> packetsFromAdjacentRegion = Arrays.asList(packet4, packet5);
        List<Packet> mergedList = Arrays.asList(packet1, packet2, packet3, packet4);

        when(regionJpaRepository.findAll()).thenReturn(regions);
        when(regionWithPriorityUtil.getRegionWithHighestTotalPriority(regions)).thenReturn(region2);
        when(packetJpaRepository.getForRegion(2L)).thenReturn(packets);
        when(deliveryRoundCreator.createRound(4, packets)).thenReturn(deliveryRound);
        when(regionWithPriorityUtil.getRegionWithHighestTotalPriority(region2.getAdjacentRegions())).thenReturn(region1);
        when(packetJpaRepository.getForRegion(1L)).thenReturn(packetsFromAdjacentRegion);
        when(packetsListMerger.mergeLists(4, packets, packetsFromAdjacentRegion)).thenReturn(mergedList);

        TestCase.assertEquals(1L, deliveryRoundInternalService.createNewRound(4).longValue());

        verify(deliveryRoundJpaRepository, times(1)).save(deliveryRound);
        verify(regionJpaRepository, times(1)).findAll();
        verify(regionWithPriorityUtil, times(2)).getRegionWithHighestTotalPriority(anyCollection());
        verify(packetJpaRepository, times(2)).getForRegion(anyLong());
        verify(packetsListMerger, times(1)).mergeLists(anyInt(), anyList(), anyList());
        verify(deliveryRoundCreator, times(1)).createRound(anyInt(), anyList());
    }

    @Test
    public void testGetPacketForRoundId() throws Exception {
        //Setup mocks
        Packet packet1 = createPacket("packetId1", null, null, null, null, 0);
        Packet packet2 = createPacket("packetId2", null, null, null, null, 0);
        List<Packet> packets = new ArrayList<>();
        packets.add(packet1);
        packets.add(packet2);
        List<Packet> sortedPackets = new ArrayList<>();
        sortedPackets.add(packet2);
        sortedPackets.add(packet1);
        DeliveryRound deliveryRound = createDeliveryRound(
                packets,
                new ArrayList<>(),
                new ArrayList<>(),
                null
        );
        Address address = createAddress("street", "number", "mailbox", "postalCode", "city");
        CompanyContactDetails companyContactDetails = createCompanyContactDetails(null, address, null, null, null, null);
        when(deliveryRoundJpaRepository.getOne(1L)).thenReturn(deliveryRound);
        when(companyContactDetailsRepository.get()).thenReturn(companyContactDetails);
        when(deliveryOrderResolver.sort(address, packets)).thenReturn(sortedPackets);

        //Test
        sortedPackets = deliveryRoundInternalService.getPackets(1L);
        TestCase.assertNotNull(sortedPackets);
        TestCase.assertEquals(2, sortedPackets.size());
        TestCase.assertEquals(packet2, sortedPackets.get(0));
        TestCase.assertEquals(packet1, sortedPackets.get(1));

        verify(deliveryRoundJpaRepository, times(1)).getOne(any());
        verify(companyContactDetailsRepository, times(1)).get();
        verify(deliveryOrderResolver, times(1)).sort(any(), anyList());
    }

    @Test
    public void testMarkAsLost() throws Exception {
        //Setup mocks
        Date date = DATE_FORMAT.parse("17/04/2016");
        Packet packet = createPacket(
                "packetId",
                createClientInfo(
                        createContactDetails(
                                null, new ArrayList<>(), Collections.singletonList("email1@test.com")), null),
                createDeliveryInfo(
                        createClientInfo(
                                createContactDetails(
                                        null, new ArrayList<>(), Collections.singletonList("email2@test.com")), null),
                        null
                ),
                null,
                date,
                0
        );
        List<Packet> packets = new ArrayList<>();
        packets.add(packet);
        DeliveryRound deliveryRound = createDeliveryRound(
                packets,
                new ArrayList<>(),
                new ArrayList<>(),
                null
        );
        when(deliveryRoundJpaRepository.getOne(1L)).thenReturn(deliveryRound);
        when(packetJpaRepository.getPacket("packetId")).thenReturn(packet);
        when(internalServiceProperties.getPacketLostSubject()).thenReturn("packetLostSubject");
        when(mailContentLoader.getLostMail()).thenReturn("packetLostMail [packetId]");
        when(tagReplacer.replaceTags(eq("packetLostMail [packetId]"), anyMap())).thenReturn("packetLostMail packetId");

        //Test
        TestCase.assertTrue(deliveryRoundInternalService.markAsLost(1L, packet));
        TestCase.assertEquals(0, deliveryRound.getPackets().size());
        TestCase.assertEquals(PacketStatus.NOT_FOUND, packet.getPacketStatus());
        assertThat(packet.getPacketStatus(), is(not(date)));

        verify(deliveryRoundJpaRepository, times(1)).getOne(any());
        verify(packetJpaRepository, times(1)).getPacket(any());
        verify(deliveryRoundJpaRepository, times(1)).flush();
        verify(packetJpaRepository, times(1)).flush();
        verify(internalServiceProperties, times(1)).getPacketLostSubject();
        verify(tagReplacer, times(1)).replaceTags(any(), any());
        verify(mailer, times(1)).send("email1@test.com", "packetLostSubject", "packetLostMail packetId");
        verify(mailer, times(1)).send("email2@test.com", "packetLostSubject", "packetLostMail packetId");
    }

    @Test
    public void testEndRound() {
        TestCase.assertTrue(deliveryRoundInternalService.endRound(1L));
        verify(deliveryRoundJpaRepository, times(1)).delete(anyLong());
    }

    @Test
    public void testStartRound() throws Exception {
        //Setup mocks
        Packet packet1 = createPacket(
                "packetId1",
                createClientInfo(
                        createContactDetails(
                                null, new ArrayList<>(), Collections.singletonList("email1@test.com")), null),
                createDeliveryInfo(
                        createClientInfo(
                                createContactDetails(
                                        null, new ArrayList<>(), Collections.singletonList("email2@test.com")), null),
                        null
                ),
                null,
                null,
                0
        );
        Packet packet2 = createPacket(
                "packetId2",
                createClientInfo(
                        createContactDetails(
                                null, new ArrayList<>(), Collections.singletonList("email3@test.com")), null),
                createDeliveryInfo(
                        createClientInfo(
                                createContactDetails(
                                        null, new ArrayList<>(), Collections.singletonList("email4@test.com")), null),
                        null
                ),
                null,
                null,
                0
        );
        List<Packet> packets = new ArrayList<>();
        packets.add(packet1);
        packets.add(packet2);
        DeliveryRound deliveryRound = createDeliveryRound(
                packets,
                new ArrayList<>(),
                new ArrayList<>(),
                null
        );
        when(deliveryRoundJpaRepository.getOne(1L)).thenReturn(deliveryRound);
        when(internalServiceProperties.getPacketDepartedSubject()).thenReturn("departedSubject");
        when(mailContentLoader.getDepartedMail()).thenReturn("departed [packetId]");
        when(tagReplacer.replaceTags(eq("departed [packetId]"), anyMap())).thenReturn("departed packetId");

        //Test
        TestCase.assertTrue(deliveryRoundInternalService.startRound(1L));
        TestCase.assertEquals(RoundStatus.STARTED, deliveryRound.getRoundStatus());

        verify(deliveryRoundJpaRepository, times(1)).getOne(any());
        verify(deliveryRoundJpaRepository, times(1)).flush();
        verify(internalServiceProperties, times(1)).getPacketDepartedSubject();
        verify(tagReplacer, times(2)).replaceTags(eq("departed [packetId]"), anyMap());
        verify(mailer, times(1)).send("email1@test.com", "departedSubject", "departed packetId");
        verify(mailer, times(1)).send("email2@test.com", "departedSubject", "departed packetId");
        verify(mailer, times(1)).send("email3@test.com", "departedSubject", "departed packetId");
        verify(mailer, times(1)).send("email4@test.com", "departedSubject", "departed packetId");
    }

    @Test
    public void testAddRemark() {
        //Setup mocks
        DeliveryRound deliveryRound = new DeliveryRound();
        when(deliveryRoundJpaRepository.getOne(1L)).thenReturn(deliveryRound);

        //Test
        TestCase.assertTrue(deliveryRoundInternalService.addRemark(1L, "remark"));
        List<Remark> remarks = new ArrayList<>(deliveryRound.getRemarks());
        TestCase.assertEquals(1, remarks.size());
        TestCase.assertEquals("remark", remarks.get(0).getRemark());
        TestCase.assertNotNull(remarks.get(0).getTimeAdded());

        verify(deliveryRoundJpaRepository, times(1)).getOne(any());
        verify(deliveryRoundJpaRepository, times(1)).flush();
    }

    @Test
    public void testCannotDeliverIncreasePriority() throws Exception {
        //Setup mocks
        Packet packet = createPacket(
                "packetId",
                createClientInfo(
                        createContactDetails(
                                null, new ArrayList<>(), Collections.singletonList("email1@test.com")), null),
                createDeliveryInfo(
                        createClientInfo(
                                createContactDetails(
                                        null, new ArrayList<>(), Collections.singletonList("email2@test.com")), null),
                        null
                ),
                null,
                null,
                0
        );
        List<Packet> packets = new ArrayList<>();
        packets.add(packet);
        DeliveryRound deliveryRound = createDeliveryRound(
                packets,
                new ArrayList<>(),
                new ArrayList<>(),
                null
        );
        when(deliveryRoundJpaRepository.getOne(1L)).thenReturn(deliveryRound);
        when(packetJpaRepository.getPacket("packetId")).thenReturn(packet);
        when(internalServiceProperties.getPacketNotDeliveredSubject()).thenReturn("notDeliveredSubject");
        when(mailContentLoader.getNotDeliveredMail()).thenReturn("notDeliveredMail [packetId] [reason]");
        when(tagReplacer.replaceTags(eq("notDeliveredMail [packetId] [reason]"), anyMap())).thenReturn("notDeliveredMail packetId reason");

        //Test
        TestCase.assertTrue(deliveryRoundInternalService.cannotDeliver(1L, packet, "reason"));
        TestCase.assertEquals(0, deliveryRound.getPackets().size());
        TestCase.assertEquals(1, packet.getPriority().intValue());

        verify(deliveryRoundJpaRepository, times(1)).getOne(any());
        verify(deliveryRoundJpaRepository, times(1)).flush();
        verify(packetJpaRepository, times(1)).getPacket(any());
        verify(packetJpaRepository, times(1)).flush();
        verify(internalServiceProperties, times(1)).getPacketNotDeliveredSubject();
        verify(tagReplacer, times(1)).replaceTags(any(), any());
        verify(mailer, times(1)).send("email1@test.com", "notDeliveredSubject", "notDeliveredMail packetId reason");
        verify(mailer, times(1)).send("email2@test.com", "notDeliveredSubject", "notDeliveredMail packetId reason");
    }

    @Test
    public void testCannotDeliverMaxPriority() throws Exception {
        //Setup mocks
        Packet packet = createPacket(
                "packetId",
                createClientInfo(
                        createContactDetails(
                                null, new ArrayList<>(), Collections.singletonList("email1@test.com")), null),
                createDeliveryInfo(
                        createClientInfo(
                                createContactDetails(
                                        null, new ArrayList<>(), Collections.singletonList("email2@test.com")), null),
                        null
                ),
                null,
                null,
                3
        );
        List<Packet> packets = new ArrayList<>();
        packets.add(packet);
        DeliveryRound deliveryRound = createDeliveryRound(
                packets,
                new ArrayList<>(),
                new ArrayList<>(),
                null
        );
        when(deliveryRoundJpaRepository.getOne(1L)).thenReturn(deliveryRound);
        when(packetJpaRepository.getPacket("packetId")).thenReturn(packet);
        when(internalServiceProperties.getPacketNotDeliveredSubject()).thenReturn("notDeliveredSubject");
        when(mailContentLoader.getNotDeliveredMail()).thenReturn("notDeliveredMail [packetId] [reason]");
        when(tagReplacer.replaceTags(eq("notDeliveredMail [packetId] [reason]"), anyMap())).thenReturn("notDeliveredMail packetId reason");

        //Test
        TestCase.assertTrue(deliveryRoundInternalService.cannotDeliver(1L, packet, "reason"));
        TestCase.assertEquals(0, deliveryRound.getPackets().size());
        TestCase.assertEquals(PacketStatus.PROBLEMATIC, packet.getPacketStatus());

        verify(deliveryRoundJpaRepository, times(1)).getOne(any());
        verify(deliveryRoundJpaRepository, times(1)).flush();
        verify(packetJpaRepository, times(1)).getPacket(any());
        verify(packetJpaRepository, times(1)).flush();
        verify(internalServiceProperties, times(1)).getPacketNotDeliveredSubject();
        verify(tagReplacer, times(1)).replaceTags(any(), any());
        verify(mailer, times(1)).send("email1@test.com", "notDeliveredSubject", "notDeliveredMail packetId reason");
        verify(mailer, times(1)).send("email2@test.com", "notDeliveredSubject", "notDeliveredMail packetId reason");
    }

    @Test
    public void testDeliver() throws Exception {
        //Setup mocks
        Packet packet = createPacket(
                "packetId",
                createClientInfo(
                        createContactDetails(
                                null, new ArrayList<>(), Collections.singletonList("email1@test.com")), null),
                createDeliveryInfo(
                        createClientInfo(
                                createContactDetails(
                                        null, new ArrayList<>(), Collections.singletonList("email2@test.com")), null),
                        null
                ),
                null,
                null,
                0
        );
        List<Packet> packets = new ArrayList<>();
        packets.add(packet);
        DeliveryRound deliveryRound = createDeliveryRound(
                packets,
                new ArrayList<>(),
                new ArrayList<>(),
                null
        );
        when(deliveryRoundJpaRepository.getOne(1L)).thenReturn(deliveryRound);
        when(packetJpaRepository.getPacket("packetId")).thenReturn(packet);
        when(internalServiceProperties.getPacketDeliveredSubject()).thenReturn("deliveredSubject");
        when(mailContentLoader.getDeliveredMail()).thenReturn("deliveredMail [packetId]");
        when(tagReplacer.replaceTags(eq("deliveredMail [packetId]"), anyMap())).thenReturn("deliveredMail packetId");

        //Test
        TestCase.assertTrue(deliveryRoundInternalService.deliver(1L, packet));
        TestCase.assertEquals(0, deliveryRound.getPackets().size());

        verify(deliveryRoundJpaRepository, times(1)).getOne(any());
        verify(deliveryRoundJpaRepository, times(1)).flush();
        verify(packetJpaRepository, times(1)).getPacket(any());
        verify(packetJpaRepository, times(1)).delete(any(Packet.class));
        verify(internalServiceProperties, times(1)).getPacketDeliveredSubject();
        verify(tagReplacer, times(1)).replaceTags(any(), any());
        verify(mailer, times(1)).send("email1@test.com", "deliveredSubject", "deliveredMail packetId");
        verify(mailer, times(1)).send("email2@test.com", "deliveredSubject", "deliveredMail packetId");
    }

    @Test
    public void testAddLocationUpdate() {
        //Setup mocks
        DeliveryRound deliveryRound = new DeliveryRound();
        when(deliveryRoundJpaRepository.getOne(1L)).thenReturn(deliveryRound);

        //Test
        LongLat longLat = new LongLat(2D, 3D);
        TestCase.assertTrue(deliveryRoundInternalService.addLocationUpdate(1L, longLat));
        List<LocationUpdate> locationUpdates = new ArrayList<>(deliveryRound.getLocationUpdates());
        TestCase.assertEquals(1, locationUpdates.size());
        TestCase.assertEquals(longLat, locationUpdates.get(0).getLongLat());
        TestCase.assertNotNull(locationUpdates.get(0).getTimeCreated());

        verify(deliveryRoundJpaRepository, times(1)).getOne(any());
        verify(deliveryRoundJpaRepository, times(1)).flush();
    }

    @Test
    public void testGetCompanyAddress() throws Exception {
        //Setup mocks
        Address address = createAddress("street", "number", "mailbox", "postalCode", "city");
        CompanyContactDetails companyContactDetails = createCompanyContactDetails("companyName", address, null, null, null, null);
        when(companyContactDetailsRepository.get()).thenReturn(companyContactDetails);

        //Test
        TestCase.assertEquals(address, deliveryRoundInternalService.getCompanyAddress());
        verify(companyContactDetailsRepository, times(1)).get();
    }
}
