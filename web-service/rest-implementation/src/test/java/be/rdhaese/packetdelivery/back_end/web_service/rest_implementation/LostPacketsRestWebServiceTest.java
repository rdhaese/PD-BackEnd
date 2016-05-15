package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.AddPacketInternalService;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.LostPacketsInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.LostPacketsWebService;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@SpringApplicationConfiguration(classes = LostPacketsRestWebServiceTest.Config.class)
@WebAppConfiguration
public class LostPacketsRestWebServiceTest {

    @Configuration
    @EnableWebMvc
    static class Config{

        //Controller to test
        @Bean
        public LostPacketsRestWebService lostPacketsRestWebService(){
            return new LostPacketsRestWebService();
        }

        //Mocks
        @Bean
        public LostPacketsInternalService lostPacketsInternalService(){
            return mock(LostPacketsInternalService.class);
        }

        @Bean
        public Mapper<Packet, PacketDTO> packetMapper(){
            return mock(Mapper.class);
        }
    }

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Autowired
    private LostPacketsInternalService lostPacketsInternalService;

    @Autowired
    private Mapper<Packet, PacketDTO> packetMapper;

    @Test
    public void testGetLostPackets() throws Exception {
        Packet packet1 = new Packet();
        packet1.setPacketId("packetId1");
        Packet packet2 = new Packet();
        packet2.setPacketId("packetId2");
        PacketDTO packetDto1 = new PacketDTO();
        packetDto1.setPacketId("packetId1");
        PacketDTO packetDto2 = new PacketDTO();
        packetDto2.setPacketId("packetId2");
        List<Packet> packets = Arrays.asList(packet1, packet2);
        List<PacketDTO> packetDtos = Arrays.asList(packetDto1, packetDto2);

        when(lostPacketsInternalService.getLostPackets()).thenReturn(packets);
        when(packetMapper.mapToDto(packets)).thenReturn(packetDtos);

        mockMvc.perform(get("/lost-packets/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[0].packetId", is("packetId1")))
                .andExpect(jsonPath("$.[1].packetId", is("packetId2")));

        verify(lostPacketsInternalService, times(1)).getLostPackets();
        verify(packetMapper, times(1)).mapToDto(packets);
    }

    @Test
    public void testMarkAsFound() throws Exception {
        List<String> packetIds = Arrays.asList("packetId1", "packetId2", "packetId3");

        mockMvc.perform(post("/lost-packets/mark-as-found")
        .contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(packetIds)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(lostPacketsInternalService, times(1)).markAsFound("packetId1");
        verify(lostPacketsInternalService, times(1)).markAsFound("packetId2");
        verify(lostPacketsInternalService, times(1)).markAsFound("packetId3");
    }

    @Test
    public void testRemoveFromSystem() throws Exception {
        List<String> packetIds = Arrays.asList("packetId1", "packetId2", "packetId3");

        mockMvc.perform(post("/lost-packets/remove-from-system")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(packetIds)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(lostPacketsInternalService, times(1)).removeFromSystem("packetId1");
        verify(lostPacketsInternalService, times(1)).removeFromSystem("packetId2");
        verify(lostPacketsInternalService, times(1)).removeFromSystem("packetId3");
    }
}
