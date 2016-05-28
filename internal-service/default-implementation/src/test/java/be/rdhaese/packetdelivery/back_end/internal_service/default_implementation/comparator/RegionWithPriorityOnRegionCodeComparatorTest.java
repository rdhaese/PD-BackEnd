package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.comparator;

import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util.RegionWithPriority;
import be.rdhaese.packetdelivery.back_end.model.Region;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 *
 * @author Robin D'Haese
 */
public class RegionWithPriorityOnRegionCodeComparatorTest {

    private static final RegionWithPriorityOnRegionCodeComparator COMPARATOR = new RegionWithPriorityOnRegionCodeComparator();
    private RegionWithPriority regionWithPriority1;
    private RegionWithPriority regionWithPriority2;
    private RegionWithPriority regionWithPriority3;

    @Before
    public void setUp() {
        Region testRegion1 = new Region();
        testRegion1.setRegionCode("b");
        regionWithPriority1 = new RegionWithPriority(testRegion1);

        Region testRegion2 = new Region();
        testRegion2.setRegionCode("a");
        regionWithPriority2 = new RegionWithPriority(testRegion2);

        Region testRegion3 = new Region();
        testRegion3.setRegionCode("b");
        regionWithPriority3 = new RegionWithPriority(testRegion3);
    }

    @Test
    public void testCompare() {
        assertTrue(COMPARATOR.compare(regionWithPriority1, regionWithPriority2) > 0);
        assertTrue(COMPARATOR.compare(regionWithPriority2, regionWithPriority3) < 0);
        assertTrue(COMPARATOR.compare(regionWithPriority1, regionWithPriority3) == 0);
    }
}
