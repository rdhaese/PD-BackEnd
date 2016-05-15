package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.DeliveryRoundInternalService;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.TrackerInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.*;
import be.rdhaese.packetdelivery.back_end.model.options.Options;
import be.rdhaese.packetdelivery.dto.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createAddress;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Created on 13/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TrackerRestWebServiceTest.Config.class)
@WebAppConfiguration
public class TrackerRestWebServiceTest {

    @Configuration
    @EnableWebMvc
    static class Config{

        //Controller to test
        @Bean
        public TrackerRestWebService trackerRestWebService(){
            return new TrackerRestWebService();
        }

        //Mocks
        @Bean
        public TrackerInternalService trackerInternalService(){
            return mock(TrackerInternalService.class);
        }

        @Bean
        public Mapper<LocationUpdate, LocationUpdateDTO> locationUpdateMapper(){
            return mock(Mapper.class);
        }

        @Bean
        public Mapper<LongLat, LongLatDTO> longLatMapper(){
            return mock(Mapper.class);
        }

        @Bean
        public Mapper<Remark, RemarkDTO> remarkMapper(){
            return mock(Mapper.class);
        }
    }

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        Mockito.reset(longLatMapper);
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private TrackerInternalService trackerService;

    @Autowired
    private Mapper<LongLat, LongLatDTO> longLatMapper;
    @Autowired
    private Mapper<LocationUpdate, LocationUpdateDTO> locationUpdateMapper;
    @Autowired
    private Mapper<Remark, RemarkDTO> remarkMapper;


    @Test
    public void testGetCompanyAddress() throws Exception {
        LongLat longLat = new LongLat(2D, 3D);
        LongLatDTO longLatDTO = new LongLatDTO(3D, 2D);

        when(trackerService.getCompanyAddress()).thenReturn(longLat);
        when(longLatMapper.mapToDto(longLat)).thenReturn(longLatDTO);

        mockMvc.perform(get("/tracker/company-address"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(content().bytes(TestUtil.convertObjectToJsonBytes(longLatDTO)));

        verify(trackerService, times(1)).getCompanyAddress();
        verify(longLatMapper, times(1)).mapToDto(longLat);
    }

    @Test
    public void testGetPacketAddress() throws Exception {
        LongLat longLat = new LongLat(2D, 3D);
        LongLatDTO longLatDTO = new LongLatDTO(3D, 2D);

        when(trackerService.getPacketAddress("packetId")).thenReturn(longLat);
        when(longLatMapper.mapToDto(longLat)).thenReturn(longLatDTO);

        mockMvc.perform(get("/tracker/packet-address/packetId"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(content().bytes(TestUtil.convertObjectToJsonBytes(longLatDTO)));

        verify(trackerService, times(1)).getPacketAddress("packetId");
        verify(longLatMapper, times(1)).mapToDto(longLat);
    }

    @Test
    public void testGetLocationUpdates() throws Exception {
        Date date1 = dateFormat.parse("17/04/2016");
        Date date2 = dateFormat.parse("17/04/2016");
        LocationUpdate locationUpdate1 = new LocationUpdate();
        locationUpdate1.setTimeCreated(date1);
        LocationUpdate locationUpdate2 = new LocationUpdate();
        locationUpdate2.setTimeCreated(date2);
        List<LocationUpdate> locationUpdates = Arrays.asList(locationUpdate1, locationUpdate2);
        LocationUpdateDTO locationUpdateDto1 = new LocationUpdateDTO(date1, 2D, 3D);
        LocationUpdateDTO locationUpdateDto2 = new LocationUpdateDTO(date2, 4D, 5D);
        List<LocationUpdateDTO> locationUpdateDtos = Arrays.asList(locationUpdateDto1, locationUpdateDto2);

        when(trackerService.getLocationsUpdates("packetId")).thenReturn(locationUpdates);
        when(locationUpdateMapper.mapToDto(locationUpdates)).thenReturn(locationUpdateDtos);

        mockMvc.perform(get("/tracker/location-updates/packetId"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[0].timeCreated", is(date1.getTime())))
                .andExpect(jsonPath("$.[1].timeCreated", is(date2.getTime())));

        verify(trackerService, times(1)).getLocationsUpdates("packetId");
        verify(locationUpdateMapper, times(1)).mapToDto(locationUpdates);
    }

    @Test
    public void testGetRemarks() throws Exception {
        Date date1 = dateFormat.parse("17/04/2016");
        Date date2 = dateFormat.parse("17/04/2016");
        Remark remark1 = new Remark();
        remark1.setTimeAdded(date1);
        Remark remark2 = new Remark();
        remark2.setTimeAdded(date2);
        List<Remark> remarks = Arrays.asList(remark1, remark2);
        RemarkDTO remarkDto1 = new RemarkDTO();
        remarkDto1.setTimeAdded(date1);
        RemarkDTO remarkDto2 = new RemarkDTO();
        remarkDto2.setTimeAdded(date2);
        List<RemarkDTO> remarkDtos = Arrays.asList(remarkDto1, remarkDto2);

        when(trackerService.getRemarks("packetId")).thenReturn(remarks);
        when(remarkMapper.mapToDto(remarks)).thenReturn(remarkDtos);

        mockMvc.perform(get("/tracker/remarks/packetId"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[0].timeAdded", is(date1.getTime())))
                .andExpect(jsonPath("$.[1].timeAdded", is(date2.getTime())));

        verify(trackerService, times(1)).getRemarks("packetId");
        verify(remarkMapper, times(1)).mapToDto(remarks);
    }

    @Test
    public void testGetAmountOfPacketsLeftBefore() throws Exception {
        when(trackerService.getAmountOfPacketsLeftBefore("packetId")).thenReturn(2);

        mockMvc.perform(get("/tracker/packets-left-before/packetId"))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));

        verify(trackerService, times(1)).getAmountOfPacketsLeftBefore("packetId");
    }
}
