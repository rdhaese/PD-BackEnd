package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.AppInternalService;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppState;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppStateActivity;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.AppStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created on 21/04/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class AppInternalServiceImpl implements AppInternalService {

    @Autowired
    private AppStateRepository appStateRepository;

    @Override
    public String getNewId() {
        Integer newId = null;
        try {
            newId = Integer.parseInt(appStateRepository.getLatestId()) + 1;
            AppState newAppState = new AppState();
            newAppState.setAppId(newId.toString());
            if (appStateRepository.save(newAppState)) {
                return newAppState.getAppId();
            }
        } catch (JAXBException e) {
            e.printStackTrace(); //TODO handle this
        } catch (IOException e) {
            e.printStackTrace(); //TODO handle this
        }
        return null;
    }

    @Override
    public AppState getAppState(String appId) {
        try {
            return appStateRepository.getAppState(appId);
        } catch (JAXBException e) {
            e.printStackTrace();//TODO handle this
        } catch (IOException e) {
            e.printStackTrace();//TODO handle this
        }
        return null;
    }

    @Override
    public AppState getAppState(Long roundId) {
        try {
            return appStateRepository.getAppState(roundId);
        } catch (JAXBException e) {
            e.printStackTrace();//TODO handle this
        } catch (IOException e) {
            e.printStackTrace();//TODO handle this
        }
        return null;
    }

    @Override
    public Boolean roundStarted(String appId, Long roundId) {
        AppState appState = getAppState(appId);
        appState.setRoundId(roundId);
        appState.setActivity(AppStateActivity.SEARCHING);
        try {
            return appStateRepository.save(appState);
        } catch (JAXBException e) {
            e.printStackTrace();//TODO handle this
        } catch (IOException e) {
            e.printStackTrace();//TODO handle this
        }
        return false;
    }

    @Override
    public Boolean loadingIn(Long roundId) {
        AppState appState = getAppState(roundId);
        appState.setActivity(AppStateActivity.LOADING);
        try {
            return appStateRepository.save(appState);
        } catch (JAXBException e) {
            e.printStackTrace();//TODO handle this
        } catch (IOException e) {
            e.printStackTrace();//TODO handle this
        }
        return false;
    }

    @Override
    public Boolean nextPacket(Long roundId) {
        AppState appState = getAppState(roundId);
        appState.setCurrentPacketIndex(appState.getCurrentPacketIndex() + 1);
        try {
            return appStateRepository.save(appState);
        } catch (JAXBException e) {
            e.printStackTrace();//TODO handle this
        } catch (IOException e) {
            e.printStackTrace();//TODO handle this
        }
        return false;
    }

    @Override
    public Boolean ongoingDelivery(Long roundId) {
        AppState appState = getAppState(roundId);
        appState.setActivity(AppStateActivity.ONGOING);
        try {
            return appStateRepository.save(appState);
        } catch (JAXBException e) {
            e.printStackTrace();//TODO handle this
        } catch (IOException e) {
            e.printStackTrace();//TODO handle this
        }
        return false;
    }

    @Override
    public Boolean roundEnded(Long roundId) {
        AppState appState = getAppState(roundId);
        appState.setRoundId(null);
        appState.setActivity(null);
        appState.setCurrentPacketIndex(0);
        try {
            return appStateRepository.save(appState);
        } catch (JAXBException e) {
            e.printStackTrace();//TODO handle this
        } catch (IOException e) {
            e.printStackTrace();//TODO handle this
        }
        return false;
    }
}
