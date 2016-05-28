package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.comparator;

/**
 *
 * @author Robin D'Haese
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PacketOnPriorityComparatorTest.class,
        PacketOnStatusChangedComparatorTest.class,
        RegionWithPriorityOnPriorityComparatorTest.class,
        RegionWithPriorityOnPacketCountComparatorTest.class,
        RegionWithPriorityOnRegionCodeComparatorTest.class
})
public class ComparatorTestSuite {
}
