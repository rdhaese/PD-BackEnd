package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.AppInternalService;
import be.rdhaese.packetdelivery.back_end.model.DeliveryRound;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppState;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppStateActivity;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.DeliveryRoundJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.AppStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Robin D'Haese
 */
@Service
public class AppInternalServiceImpl implements AppInternalService {

    @Autowired
    private AppStateRepository appStateRepository;

    @Autowired
    private DeliveryRoundJpaRepository deliveryRoundJpaRepository;

    @Override
    public String getNewId() throws Exception {
        Integer newId = Integer.parseInt(appStateRepository.getLatestId()) + 1;
        AppState newAppState = new AppState();
        newAppState.setAppId(newId.toString());
        if (appStateRepository.save(newAppState)) {
            return newAppState.getAppId();
        }
        return null;
    }

    @Override
    public AppState getAppState(String appId) throws Exception {
        AppState appState = appStateRepository.getAppState(appId);
        if (appState.getRoundId() != null) {
            DeliveryRound deliveryRound = deliveryRoundJpaRepository.findOne(appState.getRoundId());
            if (deliveryRound != null && !deliveryRound.getPackets().isEmpty()) {
                return appState;
            }
            appState = new AppState();
            appState.setAppId(appId);
            appStateRepository.save(appState);
            return appState;
        }
        return appState;
    }

    @Override
    public AppState getAppState(Long roundId) throws Exception {
        return appStateRepository.getAppState(roundId);
    }

    @Override
    public Boolean roundStarted(String appId, Long roundId) throws Exception {
        AppState appState = getAppState(appId);
        appState.setRoundId(roundId);
        appState.setActivity(AppStateActivity.SEARCHING);
        return appStateRepository.save(appState);
    }

    @Override
    public Boolean loadingIn(Long roundId) throws Exception {
        AppState appState = getAppState(roundId);
        appState.setActivity(AppStateActivity.LOADING);
        return appStateRepository.save(appState);
    }

    @Override
    public Boolean nextPacket(Long roundId) throws Exception {
        AppState appState = getAppState(roundId);
        appState.setCurrentPacketIndex(appState.getCurrentPacketIndex() + 1);
        return appStateRepository.save(appState);
    }

    @Override
    public Boolean ongoingDelivery(Long roundId) throws Exception {
        AppState appState = getAppState(roundId);
        appState.setActivity(AppStateActivity.ONGOING);
        return appStateRepository.save(appState);
    }

    @Override
    public Boolean roundEnded(Long roundId) throws Exception {
        AppState appState = getAppState(roundId);
        appState.setRoundId(null);
        appState.setActivity(null);
        appState.setCurrentPacketIndex(0);
        return appStateRepository.save(appState);
    }
}
