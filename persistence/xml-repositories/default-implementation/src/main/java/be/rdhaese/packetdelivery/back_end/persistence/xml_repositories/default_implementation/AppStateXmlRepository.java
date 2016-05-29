package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation;

import be.rdhaese.packetdelivery.back_end.model.app_state.AppState;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppStateCollection;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.AppStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Robin D'Haese
 */
@Repository
public class AppStateXmlRepository implements AppStateRepository {

    @Autowired
    @Qualifier("appStatesResource")
    private Resource appStatesResource;

    @Override
    public Boolean save(AppState newAppState) throws JAXBException, IOException {
        AppStateCollection appStateCollection = null;
        try {
             appStateCollection = getAppStateCollection();
        } catch (JAXBException je){
            //Swallow exception, new appStateCollection will be created
        }
        if (appStateCollection == null){
            appStateCollection = new AppStateCollection();
        }
        appStateCollection.addAppState(newAppState);

        JAXBContext context = JAXBContext.newInstance(AppStateCollection.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(appStateCollection, appStatesResource.getFile());

        //Return true if the application makes it to here
        return true;
    }

    @Override
    public AppState getAppState(String appId) throws JAXBException, IOException {
        if (appId == null) {
            return null;
        }
        for (AppState appState : getAppStateCollection().getAppStates()) {
            if (appId.equals(appState.getAppId())) {
                return appState;
            }
        }
        return null;
    }

    @Override
    public AppState getAppState(Long roundId) throws JAXBException, IOException {
        if (roundId == null) {
            return null;
        }
        for (AppState appState : getAppStateCollection().getAppStates()) {
            if (roundId.equals(appState.getRoundId())) {
                return appState;
            }
        }
        return null;
    }

    @Override
    public String getLatestId() throws JAXBException, IOException {
        List<AppState> appStates = new ArrayList<>(getAppStateCollection().getAppStates());
        if (appStates.isEmpty()) {
            return "0";
        }
        Collections.sort(appStates, new Comparator<AppState>() {
            @Override
            public int compare(AppState o1, AppState o2) {
                return Integer.compare(Integer.parseInt(o1.getAppId()), Integer.parseInt(o2.getAppId()));
            }
        });
        return appStates.get(appStates.size() - 1).getAppId();
    }

    private AppStateCollection getAppStateCollection() throws JAXBException, IOException {
        createFileIfItDoesNotExist();
        JAXBContext jaxbContext = JAXBContext.newInstance(AppStateCollection.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        try {
            return (AppStateCollection) jaxbUnmarshaller.unmarshal(appStatesResource.getInputStream());
        } catch (UnmarshalException unmarshalException) {
            throw new JAXBException(unmarshalException);
        }
    }

    private void createFileIfItDoesNotExist() throws IOException {
        if (!appStatesResource.exists()) {
            File file = appStatesResource.getFile();
            file.createNewFile();
            PrintWriter writer = new PrintWriter(file);
            writer.println("<appStates></appStates>");
            writer.close();
        }
    }
}
