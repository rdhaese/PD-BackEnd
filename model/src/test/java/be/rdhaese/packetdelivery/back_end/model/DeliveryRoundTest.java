package be.rdhaese.packetdelivery.back_end.model;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TreeSet;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.*;

/**
 *
 * @author Robin D'Haese
 */
@SuppressWarnings("unchecked") //When getting result list from query, no type is known
public class DeliveryRoundTest extends AbstractModelTest {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    private DeliveryRound deliveryRound;
    private LocationUpdate firstLocationUpdate;
    private LocationUpdate lastLocationUpdate;
    private Remark firstRemark;
    private Remark lastRemark;

    @Before
    public void setUp() throws ParseException {
        Region region = createRegion(createRegionName("nl", "fr", "de", "en"), "CODE");
        Region adjacentRegion1 = createRegion(createRegionName("nl1", "fr1", "de1", "en1"), "CODE1");
        Region adjacentRegion2 = createRegion(createRegionName("nl2", "fr2", "de2", "en2"), "CODE2");
        Region adjacentRegion3 = createRegion(createRegionName("nl3", "fr3", "de3", "en3"), "CODE3");
        adjacentRegion1.addAdjacentRegion(adjacentRegion2);
        adjacentRegion1.addAdjacentRegion(adjacentRegion3);
        region.addAdjacentRegion(adjacentRegion1);

        List<Packet> packets = Arrays.asList(createPacket(
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
                PacketStatus.ON_DELIVERY,
                Calendar.getInstance().getTime(),
                2
        ),
                createPacket(
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
                                adjacentRegion1
                        ),
                        PacketStatus.ON_DELIVERY,
                        Calendar.getInstance().getTime(),
                        3
                ));

        for (Packet packet : packets) {
            getEntityManager().persist(packet);
        }

        List<LocationUpdate> locationUpdates = Arrays.asList(
                firstLocationUpdate = createLocationUpdate(DATE_FORMAT.parse("14/02/2016"), createLongLat(13.345, 7d)),
                lastLocationUpdate = createLocationUpdate(DATE_FORMAT.parse("19/02/2016"), createLongLat(12.345, 6d)),
                createLocationUpdate(DATE_FORMAT.parse("17/02/2016"), createLongLat(11.345, 5d)));

        List<Remark> remarks = Arrays.asList(lastRemark = createRemark(DATE_FORMAT.parse("14/02/2016"), "test remark text 1"),
                firstRemark = createRemark(DATE_FORMAT.parse("19/02/2016"), "test remark text 2"),
                createRemark(DATE_FORMAT.parse("17/02/2016"), "test remark text 3"));

        deliveryRound = createDeliveryRound(packets, locationUpdates, remarks, RoundStatus.NOT_STARTED);
    }

    @Test
    public void testCanPersist() {
        //Check if id is null on creation
        assertNull(deliveryRound.getId());

        //Persist
        persistFlushAndClear(deliveryRound);

        //Check if id is assigned
        assertNotNull(deliveryRound.getId());

        //Check if delivery round can be found on assigned id
        DeliveryRound newDeliveryRound = getEntityManager().find(DeliveryRound.class, deliveryRound.getId());
        assertNotNull(newDeliveryRound);
        assertEquals(deliveryRound.getRoundStatus(), newDeliveryRound.getRoundStatus());
        assertEquals(2, newDeliveryRound.getPackets().size());
        assertEquals(3, newDeliveryRound.getLocationUpdates().size());
        assertEquals(3, newDeliveryRound.getRemarks().size());
    }

    @Test
    public void testPacketsNotRemovedWithDeliveryRound() {
        //Persist
        persistFlushAndClear(deliveryRound);

        //Remove delivery round
        deliveryRound = getEntityManager().find(DeliveryRound.class, deliveryRound.getId());
        getEntityManager().remove(deliveryRound);

        //Check if packet is also removed
        List<Packet> packetsInDatabase = getEntityManager().createQuery("SELECT p FROM Packet p").getResultList();
        assertEquals(2, packetsInDatabase.size());
    }

    @Test
    public void testLocationsUpdatesRemovedWithDeliveryRound() {
        //Persist
        persistFlushAndClear(deliveryRound);

        //Remove delivery round
        deliveryRound = getEntityManager().find(DeliveryRound.class, deliveryRound.getId());
        getEntityManager().remove(deliveryRound);

        //Check if packet is also removed
        List<LocationUpdate> locationUpdatesInDatabase = getEntityManager().createQuery("SELECT l FROM LocationUpdate l").getResultList();
        assertEquals(0, locationUpdatesInDatabase.size());
    }

    @Test
    public void testRemarksRemovedWithDeliveryRound() {
        //Persist
        persistFlushAndClear(deliveryRound);

        //Remove delivery round
        deliveryRound = getEntityManager().find(DeliveryRound.class, deliveryRound.getId());
        getEntityManager().remove(deliveryRound);

        //Check if packet is also removed
        List<Remark> remarksInDatabase = getEntityManager().createQuery("SELECT r FROM Remark r").getResultList();
        assertEquals(0, remarksInDatabase.size());
    }

    @Test
    public void testAreLocationUpdatesSortedOnTimeCreated() {
        assertEquals(
                firstLocationUpdate.getLongLat(),
                ((TreeSet<LocationUpdate>) deliveryRound.getLocationUpdates()).first().getLongLat());

        assertEquals(
                lastLocationUpdate.getLongLat(),
                ((TreeSet<LocationUpdate>) deliveryRound.getLocationUpdates()).last().getLongLat());

    }

    @Test
    public void testAreRemarksSortedOnTimeAdded() {
        assertEquals(
                firstRemark.getRemark(),
                ((TreeSet<Remark>) deliveryRound.getRemarks()).first().getRemark());

        assertEquals(
                lastRemark.getRemark(),
                ((TreeSet<Remark>) deliveryRound.getRemarks()).last().getRemark());
    }

    @Test(expected = ConstraintViolationException.class)
    public void testRoundStatusCannotBeNull() {
        deliveryRound.setRoundStatus(null);

        persistFlushAndClear(deliveryRound);
    }
}