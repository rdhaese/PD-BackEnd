package be.rdhaese.packetdelivery.back_end.application.internal_service.comparator;

/**
 * Created on 3/04/2016.
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
