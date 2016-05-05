package be.rdhaese.packetdelivery.back_end.model.app_state;

import be.rdhaese.packetdelivery.back_end.model.AbstractModelTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createAppState;
import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createAppStateCollection;

/**
 * Created on 3/05/2016.
 *
 * @author Robin D'Haese
 */
public class AppStateCollectionTest extends AbstractModelTest {

    private static final String FILE_NAME = "appstates.xml";
    private AppStateCollection appStateCollection;

    @Before
    public void setUp() {
        List<AppState> appStateList = Arrays.asList(new AppState[]{
                createAppState("appId", 1L, AppStateActivity.LOADING, 1),
                createAppState("appId2", null, null, null)
        });
        appStateCollection = createAppStateCollection(appStateList);
    }

    @After
    public void afterTestMethod() {
        removeFile(FILE_NAME);
    }

    @Test
    public void testCanPersistToXml() throws Exception {
        //Save to file
        persistToXml(AppStateCollection.class, appStateCollection, FILE_NAME);

        //Read from file
        AppStateCollection newAppStateCollection = readFromXml(AppStateCollection.class, FILE_NAME);
        assertNotNull(newAppStateCollection);
        assertEquals(2, newAppStateCollection.getAppStates().size());
    }
}
