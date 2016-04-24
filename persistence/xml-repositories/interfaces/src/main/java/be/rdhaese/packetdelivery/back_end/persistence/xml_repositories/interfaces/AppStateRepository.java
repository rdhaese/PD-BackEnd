package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces;

import be.rdhaese.packetdelivery.back_end.model.app_state.AppState;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created on 21/04/2016.
 *
 * @author Robin D'Haese
 */
public interface AppStateRepository {
    Boolean save(AppState newAppState) throws JAXBException, IOException;

    AppState getAppState(String appId) throws JAXBException, IOException;

    AppState getAppState(Long roundId) throws JAXBException, IOException;

    String getLatestId() throws JAXBException, IOException;

}
