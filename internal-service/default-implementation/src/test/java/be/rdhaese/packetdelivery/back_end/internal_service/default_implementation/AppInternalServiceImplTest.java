package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.model.DeliveryRound;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppState;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppStateActivity;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.DeliveryRoundJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.AppStateRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

import java.util.Collections;
import java.util.List;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createAppState;
import static org.mockito.Mockito.*;

/**
 *
 * @author Robin D'Haese
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration
public class AppInternalServiceImplTest extends TestCase {

    @InjectMocks
    private AppInternalServiceImpl appInternalService;

    @Mock
    private AppStateRepository appStateRepository;

    @Mock
    private DeliveryRoundJpaRepository deliveryRoundJpaRepository;

    @Test
    public void testGetNewId() throws Exception {
        //Setup mocks
        when(appStateRepository.getLatestId()).thenReturn("2");
        when(appStateRepository.save(any())).thenReturn(true);

        //Test
        TestCase.assertEquals("3", appInternalService.getNewId());

        verify(appStateRepository, times(1)).getLatestId();
        verify(appStateRepository, times(1)).save(any());
    }

    @Test
    public void testGetNewIdCouldNotSave() throws Exception {
        //Setup mocks
        when(appStateRepository.getLatestId()).thenReturn("2");
        when(appStateRepository.save(any())).thenReturn(false);

        //Test
        TestCase.assertNull(appInternalService.getNewId());

        verify(appStateRepository, times(1)).getLatestId();
        verify(appStateRepository, times(1)).save(any());
    }

    @Test(expected = NumberFormatException.class)
    public void testGetNewIdInvalidIdFromRepo() throws Exception {
        //Setup mocks
        when(appStateRepository.getLatestId()).thenReturn("a");

        //Test
        appInternalService.getNewId();
        //Probably not reaching following statement, no big deal
        verify(appStateRepository, times(1)).getLatestId();
        verify(appStateRepository, times(0)).save(any());
    }

    @Test
    public void testGetAppStateForAppIdWithoutRoundId() throws Exception {
        //Setup mocks
        AppState appState = createAppState("2", null, null, null);
        when(appStateRepository.getAppState("2")).thenReturn(appState);

        //Test
        verifyNoMoreInteractions(deliveryRoundJpaRepository);

        TestCase.assertEquals(appState, appInternalService.getAppState("2"));

        verify(appStateRepository, times(1)).getAppState(any(String.class));
    }

    @Test
    public void testGetAppStateForAppIdDeliveryRoundNull() throws Exception {
        //Setup mocks
        AppState appState = createAppState("2", 1L, null, 0);
        when(appStateRepository.getAppState("2")).thenReturn(appState);
        when(deliveryRoundJpaRepository.getOne(1L)).thenReturn(null);

        //Test
        AppState returnedAppState = appInternalService.getAppState("2");
        TestCase.assertEquals("2", returnedAppState.getAppId());
        TestCase.assertNull(returnedAppState.getRoundId());
        TestCase.assertNull(returnedAppState.getActivity());
        TestCase.assertEquals(0, returnedAppState.getCurrentPacketIndex().intValue());

        verify(appStateRepository, times(1)).getAppState(any(String.class));
        verify(deliveryRoundJpaRepository, times(1)).findOne(any());
        verify(appStateRepository, times(1)).save(any());
    }

    @Test
    public void testGetAppStateForAppIdNoPacketsInDeliveryRound() throws Exception {
        //Setup mocks
        AppState appState = createAppState("2", 1L, null, 0);
        when(appStateRepository.getAppState("2")).thenReturn(appState);
        DeliveryRound deliveryRound = new DeliveryRound();
        when(deliveryRoundJpaRepository.getOne(1L)).thenReturn(deliveryRound);

        //Test
        AppState returnedAppState = appInternalService.getAppState("2");
        TestCase.assertEquals("2", returnedAppState.getAppId());
        TestCase.assertNull(returnedAppState.getRoundId());
        TestCase.assertNull(returnedAppState.getActivity());
        TestCase.assertEquals(0, returnedAppState.getCurrentPacketIndex().intValue());

        verify(appStateRepository, times(1)).getAppState(any(String.class));
        verify(deliveryRoundJpaRepository, times(1)).findOne(any());
        verify(appStateRepository, times(1)).save(any());
    }

    @Test
    public void testGetAppStateForAppIdWithDeliveryRoundWithPackets() throws Exception {
        //Setup mocks
        AppState appState = createAppState("2", 1L, null, 0);
        when(appStateRepository.getAppState("2")).thenReturn(appState);
        List<Packet> packets = Collections.singletonList(new Packet());
        DeliveryRound deliveryRound = new DeliveryRound();
        deliveryRound.setPackets(packets);
        when(deliveryRoundJpaRepository.findOne(1L)).thenReturn(deliveryRound);

        //Test
        TestCase.assertEquals(appState, appInternalService.getAppState("2"));
        TestCase.assertEquals("2", appState.getAppId());
        TestCase.assertEquals(1L, appState.getRoundId().longValue());
        TestCase.assertNull(appState.getActivity());
        TestCase.assertEquals(0, appState.getCurrentPacketIndex().intValue());

        verify(appStateRepository, times(1)).getAppState(any(String.class));
        verify(deliveryRoundJpaRepository, times(1)).findOne(any());
        verify(appStateRepository, times(0)).save(any());
    }

    @Test
    public void testGetAppStateForRoundId() throws Exception {
        //Setup mocks
        AppState appState = createAppState("2", null, null, 0);
        when(appStateRepository.getAppState("2")).thenReturn(appState);

        //Test
        TestCase.assertEquals(appState, appInternalService.getAppState("2"));
        verify(appStateRepository, times(1)).getAppState(any(String.class));
    }

    @Test
    public void testRoundStarted() throws Exception {
        //Setup mocks
        AppState appState = createAppState("2", null, null, 0);
        when(appStateRepository.getAppState("2")).thenReturn(appState);
        when(appStateRepository.save(any())).thenReturn(true);

        //Test
        TestCase.assertTrue(appInternalService.roundStarted("2", 1L));
        TestCase.assertEquals(1L, appState.getRoundId().longValue());
        TestCase.assertEquals(AppStateActivity.SEARCHING, appState.getActivity());

        verify(appStateRepository, times(1)).getAppState(any(String.class));
        verify(appStateRepository, times(1)).save(any());
    }

    @Test
    public void testLoadingIn() throws Exception {
        //Setup mocks
        AppState appState = createAppState("2", 1L, null, 0);
        when(appStateRepository.getAppState(1L)).thenReturn(appState);
        when(appStateRepository.save(any())).thenReturn(true);

        //Test
        TestCase.assertTrue(appInternalService.loadingIn(1L));
        TestCase.assertEquals(AppStateActivity.LOADING, appState.getActivity());

        verify(appStateRepository, times(1)).getAppState(any(Long.class));
        verify(appStateRepository, times(1)).save(any());
    }

    @Test
    public void testNextPacket() throws Exception {
        //Setup mocks
        AppState appState = createAppState("2", 1L, null, 0);
        when(appStateRepository.getAppState(1L)).thenReturn(appState);
        when(appStateRepository.save(any())).thenReturn(true);

        //Test
        TestCase.assertTrue(appInternalService.nextPacket(1L));
        TestCase.assertEquals(1, appState.getCurrentPacketIndex().intValue());

        verify(appStateRepository, times(1)).getAppState(any(Long.class));
        verify(appStateRepository, times(1)).save(any());
    }

    @Test
    public void testOngoingDelivery() throws Exception {
        //Setup mocks
        AppState appState = createAppState("2", 1L, null, 0);
        when(appStateRepository.getAppState(1L)).thenReturn(appState);
        when(appStateRepository.save(any())).thenReturn(true);

        //Test
        TestCase.assertTrue(appInternalService.ongoingDelivery(1L));
        TestCase.assertEquals(AppStateActivity.ONGOING, appState.getActivity());

        verify(appStateRepository, times(1)).getAppState(any(Long.class));
        verify(appStateRepository, times(1)).save(any());
    }

    @Test
    public void testRoundEnded() throws Exception {
        //Setup mocks
        AppState appState = createAppState("2", 1L, AppStateActivity.ONGOING, 2);
        when(appStateRepository.getAppState(1L)).thenReturn(appState);
        when(appStateRepository.save(any())).thenReturn(true);

        //Test
        TestCase.assertTrue(appInternalService.roundEnded(1L));
        TestCase.assertNull(appState.getRoundId());
        TestCase.assertNull(appState.getActivity());
        TestCase.assertEquals(0, appState.getCurrentPacketIndex().intValue());

        verify(appStateRepository, times(1)).getAppState(any(Long.class));
        verify(appStateRepository, times(1)).save(any());
    }
}
