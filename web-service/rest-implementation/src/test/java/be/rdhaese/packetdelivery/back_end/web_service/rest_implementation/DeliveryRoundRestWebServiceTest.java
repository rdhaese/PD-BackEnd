package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.CompanyContactDetailsInternalService;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.DeliveryRoundInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.LongLat;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyContactDetails;
import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import be.rdhaese.packetdelivery.dto.PacketDTO;
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

import java.util.Arrays;
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
@SpringApplicationConfiguration(classes = DeliveryRoundRestWebServiceTest.Config.class)
@WebAppConfiguration
public class DeliveryRoundRestWebServiceTest {

    @Configuration
    @EnableWebMvc
    static class Config{

        //Controller to test
        @Bean
        public DeliveryRoundRestWebService deliveryRoundRestWebService(){
            return new DeliveryRoundRestWebService();
        }

        //Mocks
        @Bean
        public DeliveryRoundInternalService deliveryRoundInternalService(){
            return mock(DeliveryRoundInternalService.class);
        }

        @Bean
        public Mapper<Packet, PacketDTO> packetMapper(){
            return mock(Mapper.class);
        }

        @Bean
        public Mapper<LongLat, LongLatDTO> longLatMapper(){
            return mock(Mapper.class);
        }

        @Bean
        public Mapper<Address, AddressDTO> addressMapper(){
            return mock(Mapper.class);
        }
    }

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        Mockito.reset(packetMapper);
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Autowired
    private DeliveryRoundInternalService deliveryRoundInternalService;

    @Autowired
   private Mapper<Packet, PacketDTO> packetMapper;

    @Autowired
    private Mapper<LongLat, LongLatDTO> longLatMapper;

    @Autowired
    private Mapper<Address, AddressDTO> addressMapper;

    @Test
    public void testNewRound() throws Exception {
        when(deliveryRoundInternalService.createNewRound(5)).thenReturn(2L);

        mockMvc.perform(get("/round/new/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));

        verify(deliveryRoundInternalService, times(1)).createNewRound(5);
    }

    @Test
    public void testGetPackets() throws Exception {
        Packet packet1 = new Packet();
        packet1.setPacketId("packetId1");
        Packet packet2 = new Packet();
        packet1.setPacketId("packetId2");
        List<Packet> packets = Arrays.asList(packet1, packet2);
        PacketDTO packetDto1 = new PacketDTO();
        packetDto1.setPacketId("packetId1");
        PacketDTO packetDto2 = new PacketDTO();
        packetDto2.setPacketId("packetId2");
        List<PacketDTO> packetDtos = Arrays.asList(packetDto1, packetDto2);

        when(deliveryRoundInternalService.getPackets(2L)).thenReturn(packets);
        when(packetMapper.mapToDto(packets)).thenReturn(packetDtos);

        mockMvc.perform(get("/round/packets/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].packetId", is("packetId1")))
                .andExpect(jsonPath("$.[1].packetId", is("packetId2")));

        verify(deliveryRoundInternalService, times(1)).getPackets(2L);
        verify(packetMapper, times(1)).mapToDto(packets);
    }

    @Test
    public void testMarkAsLost() throws Exception {
        Packet packet = new Packet();
        packet.setPacketId("packetId1");
        PacketDTO packetDto = new PacketDTO();
        packetDto.setPacketId("packetId1");

        when(packetMapper.mapToBus(packetDto)).thenReturn(packet);
        when(deliveryRoundInternalService.markAsLost(2L, packet)).thenReturn(true);

        mockMvc.perform(post("/round/mark-as-lost/2")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(packetDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(packetMapper, times(1)).mapToBus(packetDto);
        verify(deliveryRoundInternalService, times(1)).markAsLost(2L, packet);
    }

    @Test
    public void testDeliver() throws Exception {
        Packet packet = new Packet();
        packet.setPacketId("packetId1");
        PacketDTO packetDto = new PacketDTO();
        packetDto.setPacketId("packetId1");

        when(packetMapper.mapToBus(packetDto)).thenReturn(packet);
        when(deliveryRoundInternalService.deliver(2L, packet)).thenReturn(true);

        mockMvc.perform(post("/round/deliver/2")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(packetDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(packetMapper, times(1)).mapToBus(packetDto);
        verify(deliveryRoundInternalService, times(1)).deliver(2L, packet);
    }

    @Test
    public void testCannotDeliver() throws Exception {
        Packet packet = new Packet();
        packet.setPacketId("packetId1");
        PacketDTO packetDto = new PacketDTO();
        packetDto.setPacketId("packetId1");

        when(packetMapper.mapToBus(packetDto)).thenReturn(packet);
        when(deliveryRoundInternalService.cannotDeliver(2L, packet, "reason")).thenReturn(true);

        mockMvc.perform(post("/round/cannot-deliver/2/reason")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(packetDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(packetMapper, times(1)).mapToBus(packetDto);
        verify(deliveryRoundInternalService, times(1)).cannotDeliver(2L, packet, "reason");
    }

    @Test
    public void testAddRemark() throws Exception {
        when(deliveryRoundInternalService.addRemark(2L, "remark")).thenReturn(true);

        mockMvc.perform(get("/round/add-remark/2/remark"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(deliveryRoundInternalService, times(1)).addRemark(2L, "remark");
    }

    @Test
    public void testAddLocationUpdate() throws Exception {
        LongLat longLat = new LongLat(2D, 3D);
        LongLatDTO longLatDTO = new LongLatDTO(3D, 2D);

        when(longLatMapper.mapToBus(longLatDTO)).thenReturn(longLat);
        when(deliveryRoundInternalService.addLocationUpdate(2L, longLat)).thenReturn(true);

        mockMvc.perform(post("/round/add-location-update/2")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(longLatDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(longLatMapper, times(1)).mapToBus(longLatDTO);
        verify(deliveryRoundInternalService, times(1)).addLocationUpdate(2L, longLat);
    }

    @Test
    public void testEndRound() throws Exception {
        when(deliveryRoundInternalService.endRound(2L)).thenReturn(true);

        mockMvc.perform(get("/round/end/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(deliveryRoundInternalService, times(1)).endRound(2L);
    }

    @Test
    public void testStartRound() throws Exception {
        when(deliveryRoundInternalService.startRound(2L)).thenReturn(true);

        mockMvc.perform(get("/round/start/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(deliveryRoundInternalService, times(1)).startRound(2L);
    }

    @Test
    public void testGetCompanyAddress() throws Exception {
        Address address = createAddress("street", "number", null, "postalCode", "city");
        AddressDTO addressDTO = new AddressDTO("street", "number", null, "city","postalCode");

        when(deliveryRoundInternalService.getCompanyAddress()).thenReturn(address);
        when(addressMapper.mapToDto(address)).thenReturn(addressDTO);

        mockMvc.perform(get("/round/company-address"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.street", is("street")))
                .andExpect(jsonPath("$.city", is("city")));

        verify(deliveryRoundInternalService, times(1)).getCompanyAddress();
        verify(addressMapper, times(1)).mapToDto(address);
    }
}
