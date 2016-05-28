package be.rdhaese.packetdelivery.back_end.mapper.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.RegionsInternalService;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.model.PacketStatus;
import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.*;
import static org.mockito.Mockito.*;


/**
 *
 * @author Robin D'Haese
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration
public class PacketMapperTest extends TestCase {

    @InjectMocks
    private PacketMapper mapper;

    @Mock
    private RegionsInternalService regionsInternalService;

    private final Date date = Calendar.getInstance().getTime();
    private Packet packet;
    private PacketDTO packetDto;

    @Before
    public void setUp() {
        Region region = createRegion(
                createRegionName("nameNl", "nameFr", "nameDe", "nameEn"),
                "CODE"
        );

        when(regionsInternalService.getRegionFor("CODE")).thenReturn(region);

        packet = createPacket(
                "packetId",
                createClientInfo(
                        createContactDetails(
                                "name1",
                                Collections.singletonList("phonenumber1"),
                                Collections.singletonList("email1")
                        ),
                        createAddress("Ezelberg", "2", "12", "9500", "Geraardsbergen")
                ),
                createDeliveryInfo(
                        createClientInfo(
                                createContactDetails(
                                        "name2",
                                        Collections.singletonList("phonenumber3"),
                                        Collections.singletonList("email5")
                                ),
                                createAddress("Dagmoedstraat", "77", null, "9506", "Schendelbeke")
                        ),
                        region
                ),
                PacketStatus.NORMAL,
                date,
                0
        );

        packetDto = new PacketDTO();
        packetDto.setPacketId("packetId");
        packetDto.setPacketStatus("NORMAL");
        packetDto.setStatusChangedOn(date);
        packetDto.setClientName("name1");
        packetDto.setClientPhone("phonenumber1");
        packetDto.setClientEmail("email1");
        packetDto.setClientStreet("Ezelberg");
        packetDto.setClientNumber("2");
        packetDto.setClientMailbox("12");
        packetDto.setClientCity("Geraardsbergen");
        packetDto.setClientPostalCode("9500");
        packetDto.setDeliveryName("name2");
        packetDto.setDeliveryPhone("phonenumber3");
        packetDto.setDeliveryEmail("email5");
        packetDto.setDeliveryStreet("Dagmoedstraat");
        packetDto.setDeliveryNumber("77");
        packetDto.setDeliveryMailbox(null);
        packetDto.setDeliveryCity("Schendelbeke");
        packetDto.setDeliveryPostalCode("9506");
        packetDto.setDeliveryRegionNameNl("nameNl");
        packetDto.setDeliveryRegionNameFr("nameFr");
        packetDto.setDeliveryRegionNameDe("nameDe");
        packetDto.setDeliveryRegionNameEn("nameEn");
        packetDto.setDeliveryRegionCode("CODE");
    }

    @Test
    public void testMapToBus() {
        TestCase.assertEquals(packet, mapper.mapToBus(packetDto));
        verify(regionsInternalService, times(1)).getRegionFor(anyString());
    }

    @Test
    public void testMapToDto() {
        verifyNoMoreInteractions(regionsInternalService);
        TestCase.assertEquals(packetDto, mapper.mapToDto(packet));
    }
}
