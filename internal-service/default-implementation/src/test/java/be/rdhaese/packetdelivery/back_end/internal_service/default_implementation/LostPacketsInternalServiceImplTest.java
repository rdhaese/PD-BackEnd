package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.model.PacketStatus;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.PacketJpaRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createPacket;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.*;

/**
 * @author Robin D'Haese
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration
public class LostPacketsInternalServiceImplTest extends TestCase {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @InjectMocks
    private LostPacketsInternalServiceImpl lostPacketsInternalService;

    @Mock
    private PacketJpaRepository packetJpaRepository;

    @Test
    public void testGetLostPackets() {
        //Setup mocks
        List<Packet> packets = Arrays.asList(new Packet(), new Packet());
        when(packetJpaRepository.getLostPackets()).thenReturn(packets);

        //Test
        TestCase.assertEquals(2, lostPacketsInternalService.getLostPackets().size());
        verify(packetJpaRepository, times(1)).getLostPackets();
    }

    @Test
    public void testMarkAsFoundForPacketId() throws ParseException {
        //Setup mocks
        Date date = DATE_FORMAT.parse("27/04/2016");
        Packet packet = createPacket("packetId", null, null,
                PacketStatus.NOT_FOUND, date, 0);
        when(packetJpaRepository.getPacket("packetId")).thenReturn(packet);

        //Test
        lostPacketsInternalService.markAsFound("packetId");
        TestCase.assertEquals(PacketStatus.NORMAL, packet.getPacketStatus());
        assertThat(packet.getStatusChangedOn(), is(not(date)));

        verify(packetJpaRepository, times(1)).getPacket(any());
        verify(packetJpaRepository, times(1)).save(any(Packet.class));
    }

    @Test
    public void testMarkAsFoundForPacketIdDoesNotExists() {
        //Setup mocks
        when(packetJpaRepository.getPacket("packetId")).thenReturn(null);

        //Test
        lostPacketsInternalService.markAsFound("packetId");

        verify(packetJpaRepository, times(1)).getPacket(any());
        verify(packetJpaRepository, times(0)).save(any(Packet.class));
    }

    @Test
    public void testMarkAsFoundForPacketIdOtherStatus() throws ParseException {
        //Setup mocks
        Date date = DATE_FORMAT.parse("27/04/2016");
        Packet packet = createPacket("packetId", null, null,
                PacketStatus.PROBLEMATIC, date, 0);
        when(packetJpaRepository.getPacket("packetId")).thenReturn(packet);

        //Test
        lostPacketsInternalService.markAsFound("packetId");
        TestCase.assertEquals(PacketStatus.PROBLEMATIC, packet.getPacketStatus());
        assertThat(packet.getStatusChangedOn(), is(date));

        verify(packetJpaRepository, times(1)).getPacket(any());
        verify(packetJpaRepository, times(0)).save(any(Packet.class));
    }

    @Test
    public void testRemoveFromSystemForPacketId() {
        //Setup mocks
        Packet packet = new Packet();
        packet.setPacketId("packetId");
        packet.setPacketStatus(PacketStatus.NOT_FOUND);
        when(packetJpaRepository.getPacket("packetId")).thenReturn(packet);

        //Test
        lostPacketsInternalService.removeFromSystem("packetId");
        verify(packetJpaRepository, times(1)).getPacket(any());
        verify(packetJpaRepository, times(1)).delete(packet);
    }

    @Test
    public void testRemoveFromSystemForPacketIdNotFound(){

        when(packetJpaRepository.getPacket("packetId")).thenReturn(null);

        lostPacketsInternalService.removeFromSystem("packetId");
        verify(packetJpaRepository, times(1)).getPacket(any());
        verify(packetJpaRepository, times(0)).delete(any(Packet.class));
    }

    @Test
    public void testRemoveFromSystemForPacketIdOtherStatus(){
        //Setup mocks
        Packet packet = new Packet();
        packet.setPacketId("packetId");
        packet.setPacketStatus(PacketStatus.NORMAL);
        when(packetJpaRepository.getPacket("packetId")).thenReturn(packet);

        //Test
        lostPacketsInternalService.removeFromSystem("packetId");
        verify(packetJpaRepository, times(1)).getPacket(any());
        verify(packetJpaRepository, times(0)).delete(packet);
    }
}
