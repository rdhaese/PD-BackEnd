package be.rdhaese.packetdelivery.back_end.model;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.*;

/**
 * Created on 3/05/2016.
 *
 * @author Robin D'Haese
 */
@SuppressWarnings("unchecked")//When getting result list from query, no type is known
public class DeliveryInfoTest extends AbstractModelTest {

    private DeliveryInfo deliveryInfo;

    @Before
    public void setUp() {
        Region region = createRegion(createRegionName("nl", "fr", "de", "en"), "CODE");
        Region adjacentRegion1 = createRegion(createRegionName("nl1", "fr1", "de1", "en1"), "CODE1");
        Region adjacentRegion2 = createRegion(createRegionName("nl2", "fr2", "de2", "en2"), "CODE2");
        Region adjacentRegion3 = createRegion(createRegionName("nl3", "fr3", "de3", "en3"), "CODE3");
        adjacentRegion1.addAdjacentRegion(adjacentRegion2);
        adjacentRegion1.addAdjacentRegion(adjacentRegion3);
        region.addAdjacentRegion(adjacentRegion1);
        deliveryInfo = createDeliveryInfo(
                createClientInfo(
                        createContactDetails(
                                "name",
                                Arrays.asList("phonenumber1", "phoneNumber2"),
                                Arrays.asList("email1", "email2")
                        ),
                        createAddress("Ezelberg", "2", "12", "9500", "Geraardsbergen")
                ),
                region
        );
    }

    @Test
    public void testCanPersist() {
        //Check if id is null on creation
        assertNull(deliveryInfo.getId());

        //Persist
        persistFlushAndClear(deliveryInfo);

        //Check if id is assigned
        assertNotNull(deliveryInfo.getId());

        //Check if delivery info can be found on assigned id
        DeliveryInfo newDeliveryInfo = getEntityManager().find(DeliveryInfo.class, deliveryInfo.getId());
        assertNotNull(newDeliveryInfo);
        assertEquals(deliveryInfo.getClientInfo(), newDeliveryInfo.getClientInfo());
        assertEquals(deliveryInfo.getRegion(), newDeliveryInfo.getRegion());
    }

    @Test
    public void testClientInfoRemovedWithDeliveryInfo() {
        //Persist
        persistFlushAndClear(deliveryInfo);

        //Remove delivery info
        deliveryInfo = getEntityManager().find(DeliveryInfo.class, deliveryInfo.getId());
        getEntityManager().remove(deliveryInfo);

        //Check if delivery info is also removed
        List<ClientInfo> clientInfosInDatabase = getEntityManager().createQuery("SELECT c FROM ClientInfo c").getResultList();
        assertEquals(0, clientInfosInDatabase.size());
    }

    @Test
    public void testRegionNotRemovedWithDeliveryInfo() {
        //Persist
        persistFlushAndClear(deliveryInfo);

        //Remove delivery info
        deliveryInfo = getEntityManager().find(DeliveryInfo.class, deliveryInfo.getId());
        getEntityManager().remove(deliveryInfo);

        //Check if regions are not removed
        List<Region> regionsInDatabase = getEntityManager().createQuery("SELECT r FROM Region r").getResultList();
        assertEquals(4, regionsInDatabase.size());
    }

    @Test(expected = ConstraintViolationException.class)
    public void testClientInfoCannotBeNull() {
        deliveryInfo.setClientInfo(null);

        persistFlushAndClear(deliveryInfo);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testRegionCannotBeNull() {
        deliveryInfo.setRegion(null);

        persistFlushAndClear(deliveryInfo);
    }
}
