package be.rdhaese.packetdelivery.back_end.model.app_state;

import be.rdhaese.packetdelivery.back_end.model.options.OptionsCollectionTest;
import be.rdhaese.packetdelivery.back_end.model.options.OptionsTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created on 3/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AppStateTest.class,
        AppStateCollectionTest.class
})
public class AppStateTestSuite {
}