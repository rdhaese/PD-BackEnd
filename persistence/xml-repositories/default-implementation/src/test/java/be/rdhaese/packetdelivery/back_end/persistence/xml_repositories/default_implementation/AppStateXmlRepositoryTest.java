package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation;

import be.rdhaese.packetdelivery.back_end.model.app_state.AppState;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppStateActivity;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppStateCollection;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.AppStateRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created on 4/05/2016.
 *
 * @author Robin D'Haese
 */
public class AppStateXmlRepositoryTest extends AbstractXmlRepositoryTest {

    @Autowired
    private AppStateRepository appStateRepository;

    @Before
    public void setUp() throws Exception {
        AppState appState1 = new AppState("1", 1L, AppStateActivity.LOADING, 0);
        AppState appState2 = new AppState("2", 2L, AppStateActivity.SEARCHING, 2);
        AppStateCollection appStateCollection = new AppStateCollection();
        appStateCollection.addAppState(appState1);
        appStateCollection.addAppState(appState2);
        persistToXml(AppStateCollection.class, appStateCollection, AppStateXmlRepository.FILE_NAME);
    }

    @After
    public void afterTestMethod() {
        removeFile(AppStateXmlRepository.FILE_NAME);
    }

    @Test
    public void testSaveAppState() throws JAXBException, IOException {
        AppStateCollection appStateCollection = readFromXml(AppStateCollection.class, AppStateXmlRepository.FILE_NAME);
        assertEquals(2, appStateCollection.getAppStates().size());

        AppState appState = new AppState("3", 3L, AppStateActivity.LOADING, 0);
        appStateRepository.save(appState);

        appStateCollection = readFromXml(AppStateCollection.class, AppStateXmlRepository.FILE_NAME);
        assertEquals(3, appStateCollection.getAppStates().size());

        removeFile(AppStateXmlRepository.FILE_NAME);
        appStateRepository.save(appState);
        appStateCollection = readFromXml(AppStateCollection.class, AppStateXmlRepository.FILE_NAME);
        assertEquals(1, appStateCollection.getAppStates().size());
    }

    @Test
    public void testGetAppStateForAppId() throws JAXBException, IOException {
        assertNotNull(appStateRepository.getAppState("1"));
        assertNull(appStateRepository.getAppState("unknownAppId"));

        removeFile(AppStateXmlRepository.FILE_NAME);
        assertNull(appStateRepository.getAppState("1"));
    }

    @Test
    public void testGetAppStateForRoundId() throws JAXBException, IOException {
        assertNotNull(appStateRepository.getAppState(1L));
        assertNull(appStateRepository.getAppState(9999999L));

        removeFile(AppStateXmlRepository.FILE_NAME);
        assertNull(appStateRepository.getAppState(1L));
    }

    @Test
    public void testGetLatestId() throws Exception {
        assertEquals("2", appStateRepository.getLatestId());

        removeFile(AppStateXmlRepository.FILE_NAME);
        AppStateCollection emptyAppStateCollection = new AppStateCollection();
        persistToXml(AppStateCollection.class, emptyAppStateCollection, AppStateXmlRepository.FILE_NAME);

        assertEquals("0", appStateRepository.getLatestId());

        removeFile(AppStateXmlRepository.FILE_NAME);
        assertEquals("0", appStateRepository.getLatestId());
    }
}
