package be.rdhaese.packetdelivery.back_end.mapper.default_implementation;

import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppState;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppStateActivity;
import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.AppStateDTO;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createAddress;
import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createAppState;

/**
 * Created on 13/05/2016.
 *
 * @author Robin D'Haese
 */
public class AppStateMapperTest extends TestCase {

    private Mapper<AppState, AppStateDTO> mapper;

    private AppState appState;
    private AppStateDTO appStateDto;

    @Before
    public void setUp(){
        mapper = new AppStateMapper();
        appState = createAppState("1", 1L, AppStateActivity.LOADING, 0);
        appStateDto = new AppStateDTO("1", 1L, "loading", 0);
    }

    @Test
    public void testMapToBus(){
        assertEquals(appState, mapper.mapToBus(appStateDto));
    }

    @Test
    public void testMapToDto(){
        assertEquals(appStateDto, mapper.mapToDto(appState));
    }
}
