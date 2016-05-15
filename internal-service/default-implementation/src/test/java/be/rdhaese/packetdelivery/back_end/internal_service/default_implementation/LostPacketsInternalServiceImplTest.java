package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.model.PacketStatus;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.PacketJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.RegionJpaRepository;
import junit.framework.TestCase;
import org.junit.Ignore;
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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created on 6/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration
public class LostPacketsInternalServiceImplTest extends TestCase{

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @InjectMocks
    private LostPacketsInternalServiceImpl lostPacketsInternalService;

    @Mock
    private PacketJpaRepository packetJpaRepository;

    @Test
    public void testGetLostPackets(){
        //Setup mocks
        List<Packet> packets = Arrays.asList( new Packet[]{
                new Packet(), new Packet()
        });
        when(packetJpaRepository.getLostPackets()).thenReturn(packets);

        //Test
        assertEquals(2, lostPacketsInternalService.getLostPackets().size());
        verify(packetJpaRepository, times(1)).getLostPackets();
    }

    @Test
    public void testMarkAsFoundForPacketId() throws ParseException {
        //Setup mocks
        Date date = dateFormat.parse("27/04/2016");
        Packet packet = createPacket("packetId", null, null,
                PacketStatus.NOT_FOUND, date, 0);
        when(packetJpaRepository.getPacket("packetId")).thenReturn(packet);

        //Test
        lostPacketsInternalService.markAsFound("packetId");
        assertEquals(PacketStatus.NORMAL, packet.getPacketStatus());
        assertThat(packet.getStatusChangedOn(), is(not(date)));

        verify(packetJpaRepository, times(1)).getPacket(any());
        verify(packetJpaRepository, times(1)).save(any(Packet.class));
    }

    @Test
    public void removeFromSystemForPacketId(){
        //Setup mocks
        Packet packet = new Packet();
        packet.setPacketId("packetId");
        when(packetJpaRepository.getPacket("packetId")).thenReturn(packet);

        //Test
        lostPacketsInternalService.removeFromSystem("packetId");
        verify(packetJpaRepository, times(1)).getPacket(any());
        verify(packetJpaRepository,  times(1)).delete(packet);
    }
}
