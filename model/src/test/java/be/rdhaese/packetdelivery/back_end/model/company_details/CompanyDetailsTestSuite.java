package be.rdhaese.packetdelivery.back_end.model.company_details;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created on 4/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        EmailEntryTest.class,
        FaxEntryTest.class,
        PhoneEntryTest.class,
        CompanyContactDetailsTest.class
})
public class CompanyDetailsTestSuite {
}
