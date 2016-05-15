package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util.PacketIdGenerator;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.model.PacketStatus;
import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.DeliveryRoundJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.PacketJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.AppStateRepository;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

import java.util.Arrays;
import java.util.Calendar;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.*;
import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createAddress;
import static org.mockito.Mockito.*;

/**
 * Created on 7/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration
public class AddPacketInternalServiceImplTest extends TestCase {

    @InjectMocks
    private AddPacketInternalServiceImpl addPacketInternalService;

    @Mock
    private PacketJpaRepository packetJpaRepository;

    @Mock
    private PacketIdGenerator packetIdGenerator;

    @Test
    public void testSavePacket() throws Exception {
        //Setup mocks
        Packet packet = new Packet();
        when(packetIdGenerator.generatePacketId(any())).thenReturn("packetId");

        //Test
        String packetId = addPacketInternalService.savePacket(packet);
        assertNotNull(packetId);
        assertEquals("packetId", packetId);
        assertEquals("packetId", packet.getPacketId());

        verify(packetIdGenerator, times(1)).generatePacketId(any());
        verify(packetJpaRepository, times(1)).save(any(Packet.class));
    }

    @Test
    public void testSaveNullPacket() throws Exception {
        //Setup mocks
        when(packetIdGenerator.generatePacketId(null)).thenReturn(null);

        //Test
        verifyNoMoreInteractions(packetJpaRepository);
        String packetId = addPacketInternalService.savePacket(null);
        assertNull(packetId);

        verify(packetIdGenerator, times(1)).generatePacketId(any());
    }
}
