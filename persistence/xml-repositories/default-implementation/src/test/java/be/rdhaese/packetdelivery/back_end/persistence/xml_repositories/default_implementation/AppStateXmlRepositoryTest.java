package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation;

import be.rdhaese.packetdelivery.back_end.model.app_state.AppState;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppStateActivity;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppStateCollection;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation.config.XmlTestConfig;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.AppStateRepository;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

/**
 *
 * @author Robin D'Haese
 */
public class AppStateXmlRepositoryTest extends AbstractXmlRepositoryTest {

    @Autowired
    private AppStateRepository appStateRepository;

    @Before
    public void setUp() throws Exception {
        clearFile(XmlTestConfig.APP_STATES_XML);

        AppState appState1 = new AppState("1", 1L, AppStateActivity.LOADING, 0);
        AppState appState2 = new AppState("2", 2L, AppStateActivity.SEARCHING, 2);
        AppStateCollection appStateCollection = new AppStateCollection();
        appStateCollection.addAppState(appState1);
        appStateCollection.addAppState(appState2);
        persistToXml(AppStateCollection.class, appStateCollection, XmlTestConfig.APP_STATES_XML);
    }

    @After
    public void tearDown() throws IOException {
        applicationContext.getResource(XmlTestConfig.APP_STATES_XML).getFile().delete();
    }

    @Test
    public void testSaveAppState() throws JAXBException, IOException, URISyntaxException {
        AppStateCollection appStateCollection = readFromXml(AppStateCollection.class, XmlTestConfig.APP_STATES_XML);
        assertEquals(2, appStateCollection.getAppStates().size());

        AppState appState = new AppState("3", 3L, AppStateActivity.LOADING, 0);
        appStateRepository.save(appState);

        appStateCollection = readFromXml(AppStateCollection.class, XmlTestConfig.APP_STATES_XML);
        assertEquals(3, appStateCollection.getAppStates().size());

        clearFile(XmlTestConfig.APP_STATES_XML);
        appStateRepository.save(appState);
        appStateCollection = readFromXml(AppStateCollection.class, XmlTestConfig.APP_STATES_XML);
        assertEquals(1, appStateCollection.getAppStates().size());
    }

    @Test
    public void testGetAppStateForAppId() throws JAXBException, IOException, URISyntaxException {
        assertNotNull(appStateRepository.getAppState("1"));
        assertNull(appStateRepository.getAppState("unknownAppId"));

        clearFile(XmlTestConfig.APP_STATES_XML);
        assertNull(appStateRepository.getAppState("1"));
    }

    @Test
    public void testGetAppStateForRoundId() throws JAXBException, IOException, URISyntaxException {
        assertNotNull(appStateRepository.getAppState(1L));
        assertNull(appStateRepository.getAppState(9999999L));

        clearFile(XmlTestConfig.APP_STATES_XML);
        assertNull(appStateRepository.getAppState(1L));
    }

    @Test
    public void testGetLatestId() throws Exception {
        assertEquals("2", appStateRepository.getLatestId());

        clearFile(XmlTestConfig.APP_STATES_XML);
        AppStateCollection emptyAppStateCollection = new AppStateCollection();
        persistToXml(AppStateCollection.class, emptyAppStateCollection, XmlTestConfig.APP_STATES_XML);

        assertEquals("0", appStateRepository.getLatestId());

        clearFile(XmlTestConfig.APP_STATES_XML);
        assertEquals("0", appStateRepository.getLatestId());
    }

    private void clearFile(String fileName) throws IOException {
        File file = applicationContext.getResource(fileName).getFile();
        PrintWriter writer = new PrintWriter(file);
        writer.println("<appStates></appStates>");
        writer.close();
    }
}
