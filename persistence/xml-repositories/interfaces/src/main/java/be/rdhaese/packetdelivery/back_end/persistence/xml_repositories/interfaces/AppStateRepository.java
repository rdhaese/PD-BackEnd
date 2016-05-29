package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces;

import be.rdhaese.packetdelivery.back_end.model.app_state.AppState;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author Robin D'Haese
 */
public interface AppStateRepository {
    @SuppressWarnings("SameReturnValue")
    Boolean save(AppState newAppState) throws JAXBException, IOException;

    AppState getAppState(String appId) throws JAXBException, IOException;

    AppState getAppState(Long roundId) throws JAXBException, IOException;

    String getLatestId() throws JAXBException, IOException;

}
