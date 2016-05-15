package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.comparator;

import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util.RegionWithPriority;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created on 3/04/2016.
 *
 * @author Robin D'Haese
 */
public class RegionWithPriorityOnPriorityComparatorTest {

    private RegionWithPriorityOnPriorityComparator comparator = new RegionWithPriorityOnPriorityComparator();
    private RegionWithPriority regionWithPriority1;
    private RegionWithPriority regionWithPriority2;
    private RegionWithPriority regionWithPriority3;

    @Before
    public void setUp(){
        regionWithPriority1 = new RegionWithPriority(new Region());
        regionWithPriority1.setPriority(1);

        regionWithPriority2 = new RegionWithPriority(new Region());
        regionWithPriority2.setPriority(2);

        regionWithPriority3 = new RegionWithPriority(new Region());
        regionWithPriority3.setPriority(1);
    }

    @Test
    public void testCompare(){
        assertTrue(comparator.compare(regionWithPriority1, regionWithPriority2) > 0);
        assertTrue(comparator.compare(regionWithPriority2, regionWithPriority3) < 0);
        assertTrue(comparator.compare(regionWithPriority1, regionWithPriority3) == 0);
    }
}
