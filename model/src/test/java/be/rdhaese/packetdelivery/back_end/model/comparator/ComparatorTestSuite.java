package be.rdhaese.packetdelivery.back_end.model.comparator;

import be.rdhaese.packetdelivery.back_end.model.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created on 3/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        LocationUpdateOnTimeCreatedComparatorTest.class,
        RemarksOnTimeAddedComparatorTest.class
})
public class ComparatorTestSuite {
}
