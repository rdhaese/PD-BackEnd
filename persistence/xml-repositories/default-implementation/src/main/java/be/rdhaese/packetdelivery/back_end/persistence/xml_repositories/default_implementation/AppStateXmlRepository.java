package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation;

import be.rdhaese.packetdelivery.back_end.model.app_state.AppState;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppStateCollection;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.AppStateRepository;
import org.springframework.stereotype.Repository;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
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

    public static final String FILE_NAME = "app-states.xml";

    @Override
    public Boolean save(AppState newAppState) throws JAXBException {
        AppStateCollection appStateCollection = getAppStateCollection();
        appStateCollection.addAppState(newAppState);

        JAXBContext context = JAXBContext.newInstance(AppStateCollection.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(appStateCollection, new File(FILE_NAME));

        //Return true if the application makes it to here
        return true;
    }

    @Override
    public AppState getAppState(String appId) throws JAXBException {
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
    public AppState getAppState(Long roundId) throws JAXBException {
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
    public String getLatestId() throws JAXBException {
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

    private AppStateCollection getAppStateCollection() throws JAXBException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new AppStateCollection();
        }
        JAXBContext jaxbContext = JAXBContext.newInstance(AppStateCollection.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        try {
            return (AppStateCollection) jaxbUnmarshaller.unmarshal(file);
        } catch (UnmarshalException unmarshalException) {
            throw new JAXBException(unmarshalException);
        }
    }
}
