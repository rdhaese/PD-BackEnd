package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.AddPacketInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Robin D'Haese
 */
public class AddPacketRestWebServiceTest extends AbstractRestWebServiceTest {

    @Autowired //Mock, see TestConfig
    private AddPacketInternalService addPacketInternalService;

    @Autowired //Mock, see TestConfig
    private Mapper<Packet, PacketDTO> packetMapper;

    @After
    public void tearDown() {
        reset(addPacketInternalService, packetMapper);
    }

    @Test
    public void testAddPacket() throws Exception {
        PacketDTO packetDTO = new PacketDTO();
        Packet packet = new Packet();
        when(packetMapper.mapToBus(packetDTO)).thenReturn(packet);
        when(addPacketInternalService.savePacket(packet)).thenReturn("packetId");

        mockMvc.perform(post("/packet/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(AbstractRestWebServiceTest.convertObjectToJsonBytes(packetDTO))
        )
                .andExpect(status().isOk())
                .andExpect(content().string("packetId"));

        verify(packetMapper, times(1)).mapToBus(packetDTO);
        verify(addPacketInternalService, times(1)).savePacket(packet);
    }
}