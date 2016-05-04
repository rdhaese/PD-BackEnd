package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.AppInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppState;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.AppWebService;
import be.rdhaese.packetdelivery.dto.AppStateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 21/04/2016.
 *
 * @author Robin D'Haese
 */
@RestController
@RequestMapping("/app/state")
public class AppRestWebService implements AppWebService {

    @Autowired
    private AppInternalService appService;
    @Autowired
    private Mapper<AppState, AppStateDTO> appStateMapper;

    @Override
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public @ResponseBody String getNewId() throws Exception {
        return appService.getNewId();
    }

    @Override
    @RequestMapping(value = "/get/{appId}", method = RequestMethod.GET)
    public @ResponseBody AppStateDTO getAppState(@PathVariable String appId) throws Exception {
        return appStateMapper.mapToDto(appService.getAppState(appId));
    }

    @Override
    @RequestMapping(value = "/round-started/{appId}/{roundId}", method = RequestMethod.GET)
    public Boolean roundStarted(@PathVariable String appId, @PathVariable Long roundId) throws Exception {
        return appService.roundStarted(appId, roundId);
    }

    @Override
    @RequestMapping(value = "/loading-in/{roundId}", method = RequestMethod.GET)
    public Boolean loadingIn(@PathVariable Long roundId) throws Exception {
        return appService.loadingIn(roundId);
    }

    @Override
    @RequestMapping(value = "/next-packet/{roundId}", method = RequestMethod.GET)
    public Boolean nextPacket(@PathVariable Long roundId) throws Exception {
        return appService.nextPacket(roundId);
    }

    @Override
    @RequestMapping(value = "/round-ongoing/{roundId}", method = RequestMethod.GET)
    public Boolean ongoingDelivery(@PathVariable Long roundId) throws Exception {
        return appService.ongoingDelivery(roundId);
    }

    @Override
    @RequestMapping(value = "/round-ended/{roundId}", method = RequestMethod.GET)
    public Boolean roundEnded(@PathVariable Long roundId) throws Exception {
        return appService.roundEnded(roundId);
    }
}
