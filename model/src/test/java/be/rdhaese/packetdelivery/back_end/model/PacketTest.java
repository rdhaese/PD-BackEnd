package be.rdhaese.packetdelivery.back_end.model;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.*;

/**
 * Created on 3/05/2016.
 *
 * @author Robin D'Haese
 */
@SuppressWarnings("unchecked") //When getting result list from query, no type is known
public class PacketTest extends AbstractModelTest {

    private Packet packet;

    @Before
    public void setUp() {
        Region region = createRegion(createRegionName("nl", "fr", "de", "en"), "CODE");
        Region adjacentRegion1 = createRegion(createRegionName("nl1", "fr1", "de1", "en1"), "CODE1");
        Region adjacentRegion2 = createRegion(createRegionName("nl2", "fr2", "de2", "en2"), "CODE2");
        Region adjacentRegion3 = createRegion(createRegionName("nl3", "fr3", "de3", "en3"), "CODE3");
        adjacentRegion1.addAdjacentRegion(adjacentRegion2);
        adjacentRegion1.addAdjacentRegion(adjacentRegion3);
        region.addAdjacentRegion(adjacentRegion1);
        packet = createPacket(
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
                PacketStatus.NORMAL,
                Calendar.getInstance().getTime(),
                2
        );
    }

    @Test
    public void testCanPersist() {
        //Check if id is null on creation
        assertNull(packet.getId());

        //Persist
        persistFlushAndClear(packet);

        //Check if id is assigned
        assertNotNull(packet.getId());

        //Check if packet can be found on assigned id
        Packet newPacket = getEntityManager().find(Packet.class, packet.getId());
        assertNotNull(newPacket);
        assertEquals(packet, newPacket);
    }

    @Test
    public void testClientInfoRemovedWithPacket() {
        //Persist
        persistFlushAndClear(packet);

        //Remove packet
        packet = getEntityManager().find(Packet.class, packet.getId());
        getEntityManager().remove(packet);

        //Check if packet is also removed
        List<ClientInfo> clientInfosInDatabase = getEntityManager().createQuery("SELECT c FROM ClientInfo c").getResultList();
        assertEquals(0, clientInfosInDatabase.size());
    }

    @Test
    public void testDeliveryInfoRemovedWithPacket() {
        //Persist
        persistFlushAndClear(packet);

        //Remove packet
        packet = getEntityManager().find(Packet.class, packet.getId());
        getEntityManager().remove(packet);

        //Check if delivery info is also removed
        List<DeliveryInfo> deliveryInfosInDatabase = getEntityManager().createQuery("SELECT d FROM DeliveryInfo d").getResultList();
        assertEquals(0, deliveryInfosInDatabase.size());
    }

    @Test(expected = ConstraintViolationException.class)
    public void testPacketIdCannotBeNull() {
        packet.setPacketId(null);

        persistFlushAndClear(packet);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testClientInfoCannotBeNull() {
        packet.setClientInfo(null);

        persistFlushAndClear(packet);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testDeliveryInfoCannotBeNull() {
        packet.setDeliveryInfo(null);

        persistFlushAndClear(packet);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testPacketStatusCannotBeNull() {
        packet.setPacketStatus(null);

        persistFlushAndClear(packet);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testStatusChangedOnCannotBeNull() {
        packet.setStatusChangedOn(null);

        persistFlushAndClear(packet);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testPriorityCannotBeNull() {
        packet.setPriority(null);

        persistFlushAndClear(packet);
    }
}
