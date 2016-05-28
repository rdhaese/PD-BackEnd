package be.rdhaese.packetdelivery.back_end.mapper.default_implementation;

import be.rdhaese.packetdelivery.back_end.mapper.interfaces.AbstractMapper;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppState;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppStateActivity;
import be.rdhaese.packetdelivery.dto.AppStateDTO;
import org.springframework.stereotype.Component;

/**
 *
 * @author Robin D'Haese
 */
@Component
public class AppStateMapper extends AbstractMapper<AppState, AppStateDTO> {

    @Override
    public AppState mapToBus(AppStateDTO dto) {
        AppState appState = new AppState();
        appState.setAppId(dto.getAppId());
        appState.setRoundId(dto.getRoundId());
        appState.setActivity(AppStateActivity.valueOf(dto.getActivity().toUpperCase()));
        appState.setCurrentPacketIndex(dto.getCurrentPacketIndex());
        return appState;
    }

    @Override
    public AppStateDTO mapToDto(AppState busObj) {
        AppStateDTO dto = new AppStateDTO();
        dto.setAppId(busObj.getAppId());
        dto.setRoundId(busObj.getRoundId());
        if (busObj.getActivity() != null) {
            dto.setActivity(busObj.getActivity().toString());
        }
        dto.setCurrentPacketIndex(busObj.getCurrentPacketIndex());
        return dto;
    }
}
