package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Robin D'Haese
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddressToGoogleApiStringConverterTest.class,
        DeliveryRoundCreatorTest.class,
        PacketIdDateKeeperTest.class,
        PacketIdGeneratorTest.class,
        PacketsListMergerTest.class,
        RegionWithPriorityUtilTest.class,
        TagReplacerTest.class
})
public class UtilTestSuite {
}
