package be.rdhaese.packetdelivery.back_end.web_service.interfaces;


import be.rdhaese.packetdelivery.dto.AppStateDTO;

/**
 * Created on 21/04/2016.
 *
 * @author Robin D'Haese
 */
public interface AppWebService {

    String getNewId() throws Exception;

    AppStateDTO getAppState(String appId) throws Exception;

    Boolean roundStarted(String appId, Long roundId) throws Exception;

    Boolean loadingIn(Long roundId) throws Exception;

    Boolean nextPacket(Long roundId) throws Exception;

    Boolean ongoingDelivery(Long roundId) throws Exception;

    Boolean roundEnded(Long roundId) throws Exception;

}
