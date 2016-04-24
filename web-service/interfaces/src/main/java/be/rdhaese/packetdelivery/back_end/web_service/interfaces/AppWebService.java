package be.rdhaese.packetdelivery.back_end.web_service.interfaces;


import be.rdhaese.packetdelivery.dto.AppStateDTO;

/**
 * Created on 21/04/2016.
 *
 * @author Robin D'Haese
 */
public interface AppWebService {

    String getNewId();

    AppStateDTO getAppState(String appId);

    Boolean roundStarted(String appId, Long roundId);

    Boolean loadingIn(Long roundId);

    Boolean nextPacket(Long roundId);

    Boolean ongoingDelivery(Long roundId);

    Boolean roundEnded(Long roundId);

}
