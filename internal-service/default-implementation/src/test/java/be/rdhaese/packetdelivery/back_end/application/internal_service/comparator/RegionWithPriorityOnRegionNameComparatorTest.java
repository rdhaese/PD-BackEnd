package be.rdhaese.packetdelivery.back_end.application.internal_service.comparator;

import be.rdhaese.packetdelivery.back_end.application.internal_service.util.RegionWithPriority;
import be.rdhaese.packetdelivery.back_end.application.model.Region;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Created on 3/04/2016.
 *
 * @author Robin D'Haese
 */
public class RegionWithPriorityOnRegionNameComparatorTest {

    private RegionWithPriorityOnRegionNameComparator comparator = new RegionWithPriorityOnRegionNameComparator();
    private RegionWithPriority regionWithPriority1;
    private RegionWithPriority regionWithPriority2;
    private RegionWithPriority regionWithPriority3;

    @Before
    public void setUp(){
        Region testRegion1 = new Region();
        testRegion1.setName("b");
        regionWithPriority1 = new RegionWithPriority(testRegion1);

        Region testRegion2 = new Region();
        testRegion2.setName("a");
        regionWithPriority2 = new RegionWithPriority(testRegion2);

        Region testRegion3 = new Region();
        testRegion3.setName("b");
        regionWithPriority3 = new RegionWithPriority(testRegion3);
    }

    @Test
    public void testCompare(){
        assertTrue(comparator.compare(regionWithPriority1, regionWithPriority2) > 0);
        assertTrue(comparator.compare(regionWithPriority2, regionWithPriority3) < 0);
        assertTrue(comparator.compare(regionWithPriority1, regionWithPriority3) == 0);
    }
}
