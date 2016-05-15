package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.comparator.ComparatorTestSuite;
import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util.UtilTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created on 5/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddPacketInternalServiceImplTest.class,
        AppInternalServiceImplTest.class,
        AuthenticationInternalServiceImplTest.class,
        CompanyContactDetailsInternalServiceImplTest.class,
        DeliveryRoundInternalServiceImplTest.class,
        LongLatInternalServiceImplTest.class,
        LostPacketsInternalServiceImplTest.class,
        OptionsInternalServiceImplTest.class,
        ProblematicPacketsInternalServiceImplTest.class,
        RegionsInternalServiceImplTest.class,
        TrackerInternalServiceImplTest.class,
        ComparatorTestSuite.class,
        UtilTestSuite.class
})
public class InternalServiceTestSuite {
}
