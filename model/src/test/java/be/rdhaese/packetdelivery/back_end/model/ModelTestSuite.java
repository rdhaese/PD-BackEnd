package be.rdhaese.packetdelivery.back_end.model;

import be.rdhaese.packetdelivery.back_end.model.app_state.AppStateTestSuite;
import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyDetailsTestSuite;
import be.rdhaese.packetdelivery.back_end.model.comparator.ComparatorTestSuite;
import be.rdhaese.packetdelivery.back_end.model.options.OptionsTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created on 3/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddressTest.class,
        ClientInfoTest.class,
        ContactDetailsTest.class,
        DeliveryInfoTest.class,
        DeliveryRoundTest.class,
        LocationUpdateTest.class,
        PacketTest.class,
        RegionTest.class,
        RemarkTest.class,
        CompanyDetailsTestSuite.class,
        AppStateTestSuite.class,
        OptionsTestSuite.class,
        ComparatorTestSuite.class
})
public class ModelTestSuite {
}
