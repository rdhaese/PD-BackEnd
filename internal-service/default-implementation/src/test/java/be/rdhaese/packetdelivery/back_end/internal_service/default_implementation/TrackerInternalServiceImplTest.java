package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util.DeliveryOrderResolver;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.LongLatInternalService;
import be.rdhaese.packetdelivery.back_end.model.*;
import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyContactDetails;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.DeliveryRoundJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.PacketJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.CompanyContactDetailsRepository;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.*;
import static org.mockito.Mockito.*;

/**
 * Created on 6/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration
public class TrackerInternalServiceImplTest extends TestCase {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @InjectMocks
    private TrackerInternalServiceImpl trackerInternalService;

    @Mock
    private CompanyContactDetailsRepository companyContactDetailsRepository;
    @Mock
    private PacketJpaRepository packetRepository;
    @Mock
    private DeliveryRoundJpaRepository deliveryRoundRepository;
    @Mock
    private LongLatInternalService longLatService;
    @Mock
    private DeliveryOrderResolver deliveryOrderResolver;

    @Test
    public void testGetCompanyAddress() throws Exception {
        //Setup mocks
        Address address = createAddress("street", "number", "mailbox", "postalCode", "city");
        CompanyContactDetails companyContactDetails = createCompanyContactDetails("companyName", address, null, null, null, null);
        when(companyContactDetailsRepository.get()).thenReturn(companyContactDetails);

        LongLat longLat = createLongLat(3D, 4D);
        when(longLatService.getForAddress(address)).thenReturn(longLat);

        //Test
        TestCase.assertEquals(longLat, trackerInternalService.getCompanyAddress());
        verify(companyContactDetailsRepository, times(1)).get();
        verify(longLatService, times(1)).getForAddress(any());
    }

    @Test
    public void testGetPacketAddressForPacketId() throws Exception {
        //Setup mocks
        Address address = createAddress("street", "number", "mailbox", "postalCode", "city");
        Packet packet = createPacket(
                "packetId", null,
                createDeliveryInfo(createClientInfo(null, address), null), null, null, 1);
        when(packetRepository.getPacket("packetId")).thenReturn(packet);

        LongLat longLat = createLongLat(3D, 4D);
        when(longLatService.getForAddress(address)).thenReturn(longLat);

        //Test
        TestCase.assertNull(trackerInternalService.getPacketAddress("unknown id"));
        TestCase.assertEquals(longLat, trackerInternalService.getPacketAddress("packetId"));
        verify(packetRepository, times(2)).getPacket(any());
        verify(longLatService, times(1)).getForAddress(any());
    }

    @Test
    public void testGetLocationUpdatesForPacketId() throws ParseException {
        //Setup mocks
        List<Packet> packets = Arrays.asList(
                createPacket("sdopf", null, null, null, null, 0),
                createPacket("packetId", null, null, null, null, 0)
        );
        List<LocationUpdate> locationUpdates = Arrays.asList(
                createLocationUpdate(DATE_FORMAT.parse("17/04/2016"), createLongLat(1D, 2D)),
                createLocationUpdate(DATE_FORMAT.parse("16/04/2016"), createLongLat(3D, 4D)),
                createLocationUpdate(DATE_FORMAT.parse("20/04/2016"), createLongLat(5D, 6D))
        );
        List<DeliveryRound> deliveryRounds = Arrays.asList(
                createDeliveryRound(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null),
                createDeliveryRound(packets, locationUpdates, new ArrayList<>(), null)
        );
        when(deliveryRoundRepository.getOngoingRounds()).thenReturn(deliveryRounds);

        //Test
        TestCase.assertNull(trackerInternalService.getLocationsUpdates("unknown Id"));
        TestCase.assertEquals(3, trackerInternalService.getLocationsUpdates("packetId").size());
        verify(deliveryRoundRepository, times(2)).getOngoingRounds();
    }

    @Test
    public void getRemarksForPacketId() throws ParseException {
        //Setup mocks
        List<Packet> packets = Arrays.asList(
                createPacket("sdopf", null, null, null, null, 0),
                createPacket("packetId", null, null, null, null, 0)
        );
        List<Remark> remarks = Arrays.asList(
                createRemark(DATE_FORMAT.parse("17/04/2016"), "remark1"),
                createRemark(DATE_FORMAT.parse("16/04/2016"), "remark2262625"),
                createRemark(DATE_FORMAT.parse("20/04/2016"), "remark3uintrdrtsws")
        );
        List<DeliveryRound> deliveryRounds = Arrays.asList(
                createDeliveryRound(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null),
                createDeliveryRound(packets, new ArrayList<>(), remarks, null)
        );
        when(deliveryRoundRepository.getOngoingRounds()).thenReturn(deliveryRounds);

        //Test
        TestCase.assertNull(trackerInternalService.getRemarks("unknown Id"));
        TestCase.assertEquals(3, trackerInternalService.getRemarks("packetId").size());
        verify(deliveryRoundRepository, times(2)).getOngoingRounds();
    }

    @Test
    public void testGetAmountOfPacketsLeftBeforeForPacketId() throws Exception {
        //Setup mocks
        List<Packet> packets = Arrays.asList(
                createPacket("packetId1", null, null, null, null, 0),
                createPacket("packetId2", null, null, null, null, 0),
                createPacket("packetId3", null, null, null, null, 0)
        );
        List<DeliveryRound> deliveryRounds = Arrays.asList(
                createDeliveryRound(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null),
                createDeliveryRound(packets, new ArrayList<>(), new ArrayList<>(), null)
        );
        when(deliveryRoundRepository.getOngoingRounds()).thenReturn(deliveryRounds);

        Address address = createAddress("street", "number", "mailbox", "postalCode", "city");
        CompanyContactDetails companyContactDetails = createCompanyContactDetails("companyName", address, null, null, null, null);
        when(companyContactDetailsRepository.get()).thenReturn(companyContactDetails);

        List<Packet> sortedPackets = Arrays.asList(
                createPacket("packetId3", null, null, null, null, 0),
                createPacket("packetId1", null, null, null, null, 0),
                createPacket("packetId2", null, null, null, null, 0)
        );
        when(deliveryOrderResolver.sort(address, packets)).thenReturn(sortedPackets);

        //Test
        TestCase.assertNull(trackerInternalService.getAmountOfPacketsLeftBefore("unknown id"));
        TestCase.assertEquals(0, trackerInternalService.getAmountOfPacketsLeftBefore("packetId3").intValue());
        TestCase.assertEquals(1, trackerInternalService.getAmountOfPacketsLeftBefore("packetId1").intValue());
        TestCase.assertEquals(2, trackerInternalService.getAmountOfPacketsLeftBefore("packetId2").intValue());

        verify(deliveryRoundRepository, times(4)).getOngoingRounds();
        verify(companyContactDetailsRepository, times(3)).get();
        verify(deliveryOrderResolver, times(3)).sort(any(), any());
    }
}

