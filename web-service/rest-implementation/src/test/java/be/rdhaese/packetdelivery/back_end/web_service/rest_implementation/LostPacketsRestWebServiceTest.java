package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.LostPacketsInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author Robin D'Haese
 */
public class LostPacketsRestWebServiceTest extends AbstractRestWebServiceTest {

    @Autowired //Mock, see TestConfig
    private LostPacketsInternalService lostPacketsInternalService;

    @Autowired //Mock, see TestConfig
    private Mapper<Packet, PacketDTO> packetMapper;

    @After
    public void tearDown() {
        reset(lostPacketsInternalService, packetMapper);
    }

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
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[0].packetId", is("packetId1")))
                .andExpect(jsonPath("$.[1].packetId", is("packetId2")));

        verify(lostPacketsInternalService, times(1)).getLostPackets();
        verify(packetMapper, times(1)).mapToDto(packets);
    }

    @Test
    public void testMarkAsFound() throws Exception {
        List<String> packetIds = Arrays.asList("packetId1", "packetId2", "packetId3");

        mockMvc.perform(post("/lost-packets/mark-as-found")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(AbstractRestWebServiceTest.convertObjectToJsonBytes(packetIds)))
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
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(AbstractRestWebServiceTest.convertObjectToJsonBytes(packetIds)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(lostPacketsInternalService, times(1)).removeFromSystem("packetId1");
        verify(lostPacketsInternalService, times(1)).removeFromSystem("packetId2");
        verify(lostPacketsInternalService, times(1)).removeFromSystem("packetId3");
    }
}
