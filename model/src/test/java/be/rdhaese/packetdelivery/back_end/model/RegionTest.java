package be.rdhaese.packetdelivery.back_end.model;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.List;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createRegion;
import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createRegionName;

/**
 * Created on 3/05/2016.
 *
 * @author Robin D'Haese
 */
@SuppressWarnings("unchecked") //When getting result list from query, no type is known
public class RegionTest extends AbstractModelTest {

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
    }

    @Test
    public void testCanPersist() {
        //Check if id is null on creation
        assertNull(region.getId());

        //Persist
        persistFlushAndClear(region);

        //Check if id is assigned
        assertNotNull(region.getId());

        //Check if region can be found on assigned id
        Region newRegion = getEntityManager().find(Region.class, region.getId());
        assertNotNull(newRegion);
        assertEquals(region.getRegionCode(), newRegion.getRegionCode());
    }

    @Test
    public void testAreAdjacentRegionsSaved() {
        //Persist
        persistFlushAndClear(region);

        //Check amount of regions in database
        List<Region> regionsInDatabase = getEntityManager().createQuery("SELECT r FROM Region r").getResultList();
        assertEquals(4, regionsInDatabase.size());
    }

    @Test
    public void testAreAdjacentRegionsNotRemoved() {
        //Persist
        persistFlushAndClear(region);

        //Remove region
        region = getEntityManager().find(Region.class, region.getId());
        getEntityManager().remove(region);

        //Check amount of regions in database
        List<Region> regionsInDatabase = getEntityManager().createQuery("SELECT r FROM Region r").getResultList();
        assertEquals(3, regionsInDatabase.size());

    }

    @Test(expected = ConstraintViolationException.class)
    public void testRegionNameCannotBeNull() {
        region.setName(null);

        persistFlushAndClear(region);
    }

    @Test(expected = PersistenceException.class) //See RegionName class why not ConstraintValidationException
    public void testRegionNameNlCannotBeNull() {
        region.getName().setNl(null);

        persistFlushAndClear(region);
    }

    @Test(expected = PersistenceException.class) //See RegionName class why not ConstraintValidationException
    public void testRegionNameFrCannotBeNull() {
        region.getName().setFr(null);

        persistFlushAndClear(region);
    }

    @Test(expected = PersistenceException.class) //See RegionName class why not ConstraintValidationException
    public void testRegionNameDeCannotBeNull() {
        region.getName().setDe(null);

        persistFlushAndClear(region);
    }

    @Test(expected = PersistenceException.class) //See RegionName class why not ConstraintValidationException
    public void testRegionNameEnCannotBeNull() {
        region.getName().setEn(null);

        persistFlushAndClear(region);
    }

    @Test(expected = ConstraintViolationException.class)
    public void regionCodeCannotBeNull() {
        region.setRegionCode(null);

        persistFlushAndClear(region);
    }
}
