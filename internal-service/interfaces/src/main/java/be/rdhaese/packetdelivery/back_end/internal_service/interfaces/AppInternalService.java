package be.rdhaese.packetdelivery.back_end.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.model.app_state.AppState;

/**
 *
 * @author Robin D'Haese
 */
public interface AppInternalService {

    String getNewId() throws Exception;

    AppState getAppState(String appId) throws Exception;

    AppState getAppState(Long roundId) throws Exception;

    Boolean roundStarted(String appId, Long roundId) throws Exception;

    Boolean loadingIn(Long roundId) throws Exception;

    Boolean nextPacket(Long roundId) throws Exception;

    Boolean ongoingDelivery(Long roundId) throws Exception;

    Boolean roundEnded(Long roundId) throws Exception;
}
