package be.rdhaese.packetdelivery.back_end.mapper.default_implementation;

import be.rdhaese.packetdelivery.back_end.mapper.default_implementation.aspect.AspectTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created on 13/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddressMapperTest.class,
        AppStateMapperTest.class,
        CompanyContactDetailsMapperTest.class,
        DeliveryAddressMapperImplTest.class,
        LocationUpdateMapperTest.class,
        LongLatMapperTest.class,
        OptionsMapperTest.class,
        PacketMapperTest.class,
        RegionMapperTest.class,
        RemarkMapperTest.class,
        AspectTestSuite.class
})
public class MapperTestSuite {
}
