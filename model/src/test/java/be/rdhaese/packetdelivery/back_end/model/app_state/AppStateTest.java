package be.rdhaese.packetdelivery.back_end.model.app_state;

import be.rdhaese.packetdelivery.back_end.model.AbstractModelTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createAppState;

/**
 *
 * @author Robin D'Haese
 */
public class AppStateTest extends AbstractModelTest {

    private static final String FILE_NAME = "appstate.xml";

    private AppState appState;

    @Before
    public void setUp() {
        appState = createAppState("appId", 1L, AppStateActivity.LOADING, 1);
    }

    @After
    public void afterTestMethod() {
        removeFile(FILE_NAME);
    }

    @Test
    public void testCanPersistToXml() throws Exception {
        //Save to file
        persistToXml(AppState.class, appState, FILE_NAME);

        //Read from file
        AppState newAppState = readFromXml(AppState.class, FILE_NAME);
        assertNotNull(newAppState);
        assertEquals(appState, newAppState);
    }
}
