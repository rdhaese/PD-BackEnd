package be.rdhaese.packetdelivery.back_end.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.model.app_state.AppState;

/**
 * Created on 21/04/2016.
 *
 * @author Robin D'Haese
 */
public interface AppInternalService {

    String getNewId();

    AppState getAppState(String appId);

    AppState getAppState(Long roundId);

    Boolean roundStarted(String appId, Long roundId);

    Boolean loadingIn(Long roundId);

    Boolean nextPacket(Long roundId);

    Boolean ongoingDelivery(Long roundId);

    Boolean roundEnded(Long roundId);
}
