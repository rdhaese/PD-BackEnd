package be.rdhaese.packetdelivery.back_end.model.app_state;

import be.rdhaese.packetdelivery.back_end.model.AbstractModelTest;
import be.rdhaese.packetdelivery.back_end.model.options.Options;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static be.rdhaese.packetdelivery.back_end.testing.TestUtil.createAppState;

/**
 * Created on 3/05/2016.
 *
 * @author Robin D'Haese
 */
public class AppStateTest extends AbstractModelTest {

    private static final String FILE_NAME = "appstate.xml";

    private AppState appState;

    @Before
    public void setUp(){
        appState = createAppState("appId", 1L, AppStateActivity.LOADING, 1);
    }

    @After
    public void afterTestMethod(){
        File file = new File(FILE_NAME);
        if (file.exists()){
            file.delete();
        }
    }

    @Test
    public void testCanPersistToXml() throws Exception{
        //Save to file
        persistToXml(AppState.class, appState, FILE_NAME);

        //Read from file
        AppState newAppState = readFromXml(AppState.class, FILE_NAME);
        assertNotNull(newAppState);
        assertEquals(appState, newAppState);
    }
}
