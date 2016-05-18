package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.AppInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppState;
import be.rdhaese.packetdelivery.dto.AppStateDTO;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created on 13/05/2016.
 *
 * @author Robin D'Haese
 */
public class AppRestWebServiceTest extends AbstractRestWebServiceTest {

    @Autowired //Mock, see TestConfig
    private AppInternalService appInternalService;

    @Autowired //Mock, see TestConfig
    private Mapper<AppState, AppStateDTO> appStateMapper;

    @After
    public void tearDown() {
        reset(appInternalService, appStateMapper);
    }

    @Test
    public void testGetNewId() throws Exception {
        when(appInternalService.getNewId()).thenReturn("1");

        mockMvc.perform(get("/app/state/new"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));


        verify(appInternalService, times(1)).getNewId();
    }

    @Test
    public void testGetAppState() throws Exception {
        AppState appState = new AppState();
        appState.setAppId("1");
        AppStateDTO appStateDto = new AppStateDTO("1", 1L, "loading", 0);
        when(appInternalService.getAppState("1")).thenReturn(appState);
        when(appStateMapper.mapToDto(appState)).thenReturn(appStateDto);

        mockMvc.perform(get("/app/state/get/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().bytes(AbstractRestWebServiceTest.convertObjectToJsonBytes(appStateDto)));

        verify(appInternalService, times(1)).getAppState("1");
        verify(appStateMapper, times(1)).mapToDto(appState);
    }

    @Test
    public void testRoundStarted() throws Exception {
        when(appInternalService.roundStarted("1", 1L)).thenReturn(true);

        mockMvc.perform(get("/app/state/round-started/1/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("true"));

        verify(appInternalService, times(1)).roundStarted("1", 1L);
    }

    @Test
    public void testLoadingIn() throws Exception {
        when(appInternalService.loadingIn(1L)).thenReturn(true);

        mockMvc.perform(get("/app/state/loading-in/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("true"));
    }

    @Test
    public void testNextPacket() throws Exception {
        when(appInternalService.nextPacket(1L)).thenReturn(true);

        mockMvc.perform(get("/app/state/next-packet/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("true"));
    }

    @Test
    public void testOngoingDelivery() throws Exception {
        when(appInternalService.ongoingDelivery(1L)).thenReturn(true);

        mockMvc.perform(get("/app/state/round-ongoing/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("true"));
    }

    @Test
    public void testRoundEnded() throws Exception {
        when(appInternalService.roundEnded(1L)).thenReturn(true);

        mockMvc.perform(get("/app/state/round-ended/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("true"));
    }
}
