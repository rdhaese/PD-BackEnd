package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.AddPacketInternalServiceImpl;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.AddPacketInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.default_implementation.PacketMapper;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.AddPacketWebService;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created on 13/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AddPacketRestWebServiceTest.Config.class)
@WebAppConfiguration
public class AddPacketRestWebServiceTest {

    @Configuration
    @EnableWebMvc
    static class Config{

        //Controller to test
        @Bean
        public AddPacketRestWebService addPacketWebService(){
            return new AddPacketRestWebService();
        }

        //Mocks
        @Bean
        public AddPacketInternalService addPacketInternalService(){
            return mock(AddPacketInternalService.class);
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
    private AddPacketInternalService addPacketInternalService;

    @Autowired
    private Mapper<Packet, PacketDTO> packetMapper;

    @Test
    public void testAddPacket() throws Exception {
        PacketDTO packetDTO = new PacketDTO();
        Packet packet = new Packet();
        when(packetMapper.mapToBus(packetDTO)).thenReturn(packet);
        when(addPacketInternalService.savePacket(packet)).thenReturn("packetId");

        mockMvc.perform(post("/packet/add")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(packetDTO))
        )
                .andExpect(status().isOk())
                .andExpect(content().string("packetId"));

        verify(packetMapper, times(1)).mapToBus(packetDTO);
        verify(addPacketInternalService, times(1)).savePacket(packet);
    }

}
