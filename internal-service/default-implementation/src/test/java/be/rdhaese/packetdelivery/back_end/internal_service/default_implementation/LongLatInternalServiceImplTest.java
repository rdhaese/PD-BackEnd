package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.properties.InternalServiceProperties;
import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util.AddressToGoogleApiStringConverter;
import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util.GeoCoder;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.LongLat;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.DeliveryRoundJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.AppStateRepository;
import com.google.maps.GeoApiContext;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createAddress;
import static org.mockito.Mockito.*;

/**
 * Created on 7/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration
public class LongLatInternalServiceImplTest extends TestCase {

    @InjectMocks
    private LongLatInternalServiceImpl longLatInternalService;

    @Mock
    private InternalServiceProperties internalServiceProperties;

    @Mock
    private AddressToGoogleApiStringConverter addressConverter;
    @Mock
    private GeoCoder geoCoder;

    @Test
    public void testGetForAddress() throws Exception {
        //Setup mocks
        Address address = createAddress("street", "number", "mailbox", "postalCode", "city");
        String addressString = "street number, postalCode city";
        when(addressConverter.convert(address)).thenReturn(addressString);
        Geometry geometry = new Geometry();
        geometry.location = new LatLng(2D, 3D);
        when(geoCoder.getGeometry(addressString)).thenReturn(geometry);

        //Test
        verifyNoMoreInteractions(internalServiceProperties);
        LongLat result = longLatInternalService.getForAddress(address);
        assertNotNull(result);
        assertEquals(2D, result.getLatitude());
        assertEquals(3D, result.getLongitude());

        verify(addressConverter, times(1)).convert(any());
        verify(geoCoder, times(1)).getGeometry(any());
    }

    @Test
    public void testGetForAddressNoResult() throws Exception {
        //Setup mocks
        Address address = createAddress("street", "number", "mailbox", "postalCode", "city");
        String addressString = "street number, postalCode city";
        when(addressConverter.convert(address)).thenReturn(addressString);
        Geometry geometry = new Geometry();
        geometry.location = new LatLng(2D, 3D);
        when(geoCoder.getGeometry(any())).thenReturn(null);
        when(internalServiceProperties.getDefaultLatitude()).thenReturn(4D);
        when(internalServiceProperties.getDefaultLongitude()).thenReturn(5D);

        //Test
        LongLat result = longLatInternalService.getForAddress(address);
        assertNotNull(result);
        assertEquals(4D, result.getLatitude());
        assertEquals(5D, result.getLongitude());

        verify(addressConverter, times(1)).convert(any());
        verify(geoCoder, times(1)).getGeometry(any());
        verify(internalServiceProperties, times(1)).getDefaultLatitude();
        verify(internalServiceProperties, times(1)).getDefaultLongitude();
    }
}
