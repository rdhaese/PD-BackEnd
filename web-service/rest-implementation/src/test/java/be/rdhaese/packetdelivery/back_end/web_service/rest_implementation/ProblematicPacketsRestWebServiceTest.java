package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.ProblematicPacketsInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.DeliveryAddressMapper;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.dto.DeliveryAddressDTO;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author Robin D'Haese
 */
public class ProblematicPacketsRestWebServiceTest extends AbstractRestWebServiceTest {

    @Autowired //Mock, see TestConfig
    private ProblematicPacketsInternalService problematicPacketsInternalService;

    @Autowired //Mock, see TestConfig
    private Mapper<Packet, PacketDTO> packetMapper;

    @Autowired //Mock, see TestConfig
    private Mapper<Region, RegionDTO> regionMapper;

    @Autowired //Mock, see TestConfig
    private DeliveryAddressMapper deliveryAddressMapper;

    @After
    public void tearDown() {
        reset(problematicPacketsInternalService, packetMapper,
                regionMapper, deliveryAddressMapper);
    }

    @Test
    public void testGetProblematicPackets() throws Exception {
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

        when(problematicPacketsInternalService.getProblematicPackets()).thenReturn(packets);
        when(packetMapper.mapToDto(packets)).thenReturn(packetDtos);

        mockMvc.perform(get("/problematic-packets/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[0].packetId", is("packetId1")))
                .andExpect(jsonPath("$.[1].packetId", is("packetId2")));

        verify(problematicPacketsInternalService, times(1)).getProblematicPackets();
        verify(packetMapper, times(1)).mapToDto(packets);
    }

    @Test
    public void testGetProblematicPacket() throws Exception {
        Packet packet = new Packet();
        packet.setPacketId("packetId1");
        PacketDTO packetDto = new PacketDTO();
        packetDto.setPacketId("packetId1");

        when(problematicPacketsInternalService.getProblematicPacket("packetId1")).thenReturn(packet);
        when(packetMapper.mapToDto(packet)).thenReturn(packetDto);

        mockMvc.perform(get("/problematic-packets/for-id/packetId1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.packetId", is("packetId1")));

        verify(problematicPacketsInternalService, times(1)).getProblematicPacket("packetId1");
        verify(packetMapper, times(1)).mapToDto(packet);
    }

    @Test
    public void testReSend() throws Exception {
        mockMvc.perform(post("/problematic-packets/re-send")
                .content("packetId"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(problematicPacketsInternalService, times(1)).reSend("packetId");
    }

    @Test
    public void testReturnToSender() throws Exception {
        Region region = createRegion(createRegionName("nl", "fr", "de", "en"), "CODE");
        RegionDTO regionDTO = new RegionDTO("nl", "fr", "de", "en", "CODE");

        when(regionMapper.mapToBus(regionDTO)).thenReturn(region);

        mockMvc.perform(post("/problematic-packets/return-to-sender/packetId")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(AbstractRestWebServiceTest.convertObjectToJsonBytes(regionDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(regionMapper, times(1)).mapToBus(regionDTO);
        verify(problematicPacketsInternalService, times(1)).returnToSender("packetId", region);
    }

    @Test
    public void testGetDeliveryAddress() throws Exception {
        Address address = createAddress("street", "number", null, "postalCode", "city");
        Region region = createRegion(createRegionName("nl", "fr", "de", "en"), "CODE");
        DeliveryAddressDTO deliveryAddressDTO = new DeliveryAddressDTO();
        deliveryAddressDTO.setPacketId("packetId");

        when(problematicPacketsInternalService.getProblematicPacketAddress("packetId")).thenReturn(address);
        when(problematicPacketsInternalService.getProblematicPacketRegion("packetId")).thenReturn(region);
        when(deliveryAddressMapper.mapToDto(address, region, "packetId")).thenReturn(deliveryAddressDTO);

        mockMvc.perform(get("/problematic-packets/delivery-address/packetId"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.packetId", is("packetId")));

        verify(problematicPacketsInternalService, times(1)).getProblematicPacketAddress("packetId");
        verify(problematicPacketsInternalService, times(1)).getProblematicPacketRegion("packetId");
        verify(deliveryAddressMapper, times(1)).mapToDto(address, region, "packetId");
    }

    @Test
    public void testSaveDeliveryAddress() throws Exception {
        Address address = createAddress("street", "number", null, "postalCode", "city");
        Region region = createRegion(createRegionName("nl", "fr", "de", "en"), "CODE");
        DeliveryAddressDTO deliveryAddressDTO = new DeliveryAddressDTO();
        deliveryAddressDTO.setPacketId("packetId");
        Object[] objects = {"packetId", address, region};

        when(deliveryAddressMapper.mapToBus(deliveryAddressDTO)).thenReturn(objects);

        mockMvc.perform(post("/problematic-packets/save-delivery-address")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(AbstractRestWebServiceTest.convertObjectToJsonBytes(deliveryAddressDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(deliveryAddressMapper, times(1)).mapToBus(deliveryAddressDTO);
        verify(problematicPacketsInternalService, times(1)).saveDeliveryAddress("packetId", address, region);
    }
}
