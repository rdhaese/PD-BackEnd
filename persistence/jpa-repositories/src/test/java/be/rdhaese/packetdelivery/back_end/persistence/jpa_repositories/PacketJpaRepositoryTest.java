package be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories;

import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.model.PacketStatus;
import be.rdhaese.packetdelivery.back_end.model.Region;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Calendar;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.*;

/**
 *
 * @author Robin D'Haese
 */
public class PacketJpaRepositoryTest extends AbstractJpaRepositoryTest {

    @Autowired
    private PacketJpaRepository packetJpaRepository;
    @Autowired
    private RegionJpaRepository regionJpaRepository;
    private Region region;

    @Before
    public void setUp() {
        region = createRegion(createRegionName("nl", "fr", "de", "en"), "CODE");
        Region adjacentRegion1 = createRegion(createRegionName("nl1", "fr1", "de1", "en1"), "CODE1");
        Region adjacentRegion2 = createRegion(createRegionName("nl2", "fr2", "de2", "en2"), "CODE2");
        Region adjacentRegion3 = createRegion(createRegionName("nl3", "fr3", "de3", "en3"), "CODE3");
        adjacentRegion1.getAdjacentRegions().add(adjacentRegion2);
        adjacentRegion1.getAdjacentRegions().add(adjacentRegion3);
        region.getAdjacentRegions().add(adjacentRegion1);
        regionJpaRepository.saveAndFlush(region);

        packetJpaRepository.save(createNormalPacket1(region.getId()));
        packetJpaRepository.save(createNormalPacket2(region.getId()));
        packetJpaRepository.save(createProblematicPacket1(adjacentRegion2.getId()));
        packetJpaRepository.save(createProblematicPacket2(adjacentRegion3.getId()));
        packetJpaRepository.save(createNotFoundPacket(region.getId()));
        packetJpaRepository.flush();
    }

    @After
    public void afterTestMethod() {
        packetJpaRepository.deleteAll();
        regionJpaRepository.deleteAll();
    }

    @Test
    public void testGetPacketForPacketId() {
        assertNotNull(packetJpaRepository.getPacket("packetId1"));
        assertNotNull(packetJpaRepository.getPacket("packetId5"));
        assertNull(packetJpaRepository.getPacket("unknownPacketId"));
    }

    @Test
    public void testGetLostPackets() {
        assertEquals(1, packetJpaRepository.getLostPackets().size());
    }

    @Test
    public void testGetProblematicPackets() {
        assertEquals(2, packetJpaRepository.getProblematicPackets().size());
    }

    @Test
    public void testGetProblematicPacketForPacketId() {
        assertNotNull(packetJpaRepository.getProblematicPacket("packetId3"));
        assertNull(packetJpaRepository.getProblematicPacket("packetId5"));
    }

    @Test
    public void testGetForRegionOnRegionId() {
        assertEquals(2, packetJpaRepository.getForRegion(region.getId()).size());
        assertEquals(0, packetJpaRepository.getForRegion(9999999L).size());
    }

    private Packet createNormalPacket1(Long regionId) {
        Region region = regionJpaRepository.findOne(regionId);
        return createPacket(
                "packetId1",
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
                PacketStatus.NORMAL,
                Calendar.getInstance().getTime(),
                2
        );
    }

    private Packet createNormalPacket2(Long regionId) {
        Region region = regionJpaRepository.findOne(regionId);
        return createPacket(
                "packetId2",
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
                PacketStatus.NORMAL,
                Calendar.getInstance().getTime(),
                2
        );
    }

    private Packet createProblematicPacket1(Long regionId) {
        Region region = regionJpaRepository.findOne(regionId);
        return createPacket(
                "packetId3",
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
                Calendar.getInstance().getTime(),
                2
        );
    }

    private Packet createProblematicPacket2(Long regionId) {
        Region region = regionJpaRepository.findOne(regionId);
        return createPacket(
                "packetId4",
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
                Calendar.getInstance().getTime(),
                2
        );
    }

    private Packet createNotFoundPacket(Long regionId) {
        Region region = regionJpaRepository.findOne(regionId);
        return createPacket(
                "packetId5",
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
                PacketStatus.NOT_FOUND,
                Calendar.getInstance().getTime(),
                2
        );
    }
}
