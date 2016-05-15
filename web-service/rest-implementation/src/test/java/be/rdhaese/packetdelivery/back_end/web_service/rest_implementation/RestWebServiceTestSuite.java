package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created on 13/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddPacketRestWebServiceTest.class,
        AppRestWebServiceTest.class,
        AuthenticationRestWebServiceTest.class,
        ContactInformationRestWebServiceTest.class,
        DeliveryRoundRestWebServiceTest.class,
        LoadFromUrlTestRestWebServiceTest.class,
        LongLatRestWebServiceTest.class,
        LostPacketsRestWebServiceTest.class,
        OptionsRestWebServiceTest.class,
        ProblematicPacketsRestWebServiceTest.class,
        RegionsRestWebServiceTest.class,
        TrackerRestWebServiceTest.class
})
public class RestWebServiceTestSuite {
}
