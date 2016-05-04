package be.rdhaese.packetdelivery.back_end.model;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static be.rdhaese.packetdelivery.back_end.testing.TestUtil.*;

/**
 * Created on 3/05/2016.
 *
 * @author Robin D'Haese
 */
public class DeliveryRoundTest extends AbstractModelTest {

    private DeliveryRound deliveryRound;
    private LocationUpdate firstLocationUpdate;
    private LocationUpdate lastLocationUpdate;
    private Remark firstRemark;
    private Remark lastRemark;

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Before
    public void setUp() throws ParseException {
        Region region = createRegion(createRegionName("nl", "fr", "de", "en"), "CODE");
        Region adjacentRegion1 = createRegion(createRegionName("nl1", "fr1", "de1", "en1"), "CODE1");
        Region adjacentRegion2 = createRegion(createRegionName("nl2", "fr2", "de2", "en2"), "CODE2");
        Region adjacentRegion3 = createRegion(createRegionName("nl3", "fr3", "de3", "en3"), "CODE3");
        adjacentRegion1.addAdjacentRegion(adjacentRegion2);
        adjacentRegion1.addAdjacentRegion(adjacentRegion3);
        region.addAdjacentRegion(adjacentRegion1);

        List<Packet> packets = Arrays.asList(new Packet[] {
                createPacket(
                        "packetId1",
                        createClientInfo(
                                createContactDetails(
                                        "name",
                                        Arrays.asList(new String[]{"phonenumber1", "phoneNumber2"}),
                                        Arrays.asList(new String[]{"email1", "email2"})
                                ),
                                createAddress("Ezelberg", "2", "12", "9500", "Geraardsbergen")
                        ),
                        createDeliveryInfo(
                                createClientInfo(
                                        createContactDetails(
                                                "name",
                                                Arrays.asList(new String[]{"phonenumber3", "phoneNumber4"}),
                                                Arrays.asList(new String[]{"email5", "email6"})
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
                                        Arrays.asList(new String[]{"phonenumber1", "phoneNumber2"}),
                                        Arrays.asList(new String[]{"email1", "email2"})
                                ),
                                createAddress("Ezelberg", "2", "12", "9500", "Geraardsbergen")
                        ),
                        createDeliveryInfo(
                                createClientInfo(
                                        createContactDetails(
                                                "name",
                                                Arrays.asList(new String[]{"phonenumber3", "phoneNumber4"}),
                                                Arrays.asList(new String[]{"email5", "email6"})
                                        ),
                                        createAddress("Dagmoedstraat", "77", null, "9506", "Schendelbeke")
                                ),
                                adjacentRegion1
                        ),
                        PacketStatus.ON_DELIVERY,
                        Calendar.getInstance().getTime(),
                        3
                )
        });

        for (Packet packet : packets){
            getEntityManager().persist(packet);
        }

        List<LocationUpdate> locationUpdates = Arrays.asList(new LocationUpdate[]{
                (lastLocationUpdate = createLocationUpdate(dateFormat.parse("14/02/2016"), createLongLat(13.345, 7d))),
                (firstLocationUpdate = createLocationUpdate(dateFormat.parse("19/02/2016"), createLongLat(12.345, 6d))),
                createLocationUpdate(dateFormat.parse("17/02/2016"), createLongLat(11.345, 5d)),
        });

        List<Remark> remarks = Arrays.asList(new Remark[]{
                (lastRemark = createRemark(dateFormat.parse("14/02/2016") , "test remark text 1")),
                (firstRemark = createRemark(dateFormat.parse("19/02/2016"), "test remark text 2")),
                createRemark(dateFormat.parse("17/02/2016") , "test remark text 3")
        });

        deliveryRound = createDeliveryRound(packets, locationUpdates, remarks, RoundStatus.NOT_STARTED);
    }

    @Test
    public void testCanPersist(){
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
        assertEquals(3,newDeliveryRound.getLocationUpdates().size());
        assertEquals(3,newDeliveryRound.getRemarks().size());
    }

    @Test
    public void testPacketsNotRemovedWithDeliveryRound(){
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
    public void testLocationsUpdatesRemovedWithDeliveryRound(){
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
    public void testRemarksRemovedWithDeliveryRound(){
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
    public void testAreLocationUpdatesSortedOnTimeCreated(){
        assertEquals(
                firstLocationUpdate.getLongLat(),
                ((TreeSet<LocationUpdate>)deliveryRound.getLocationUpdates()).first().getLongLat());

        assertEquals(
                lastLocationUpdate.getLongLat(),
                ((TreeSet<LocationUpdate>)deliveryRound.getLocationUpdates()).last().getLongLat());

    }

    @Test
    public void testAreRemarksSortedOnTimeAdded(){
        assertEquals(
                firstRemark.getRemark(),
                ((TreeSet<Remark>)deliveryRound.getRemarks()).first().getRemark());

        assertEquals(
                lastRemark.getRemark(),
                ((TreeSet<Remark>)deliveryRound.getRemarks()).last().getRemark());
    }

    @Test(expected = ConstraintViolationException.class)
    public void testRoundStatusCannotBeNull(){
        deliveryRound.setRoundStatus(null);

        persistFlushAndClear(deliveryRound);
    }
}