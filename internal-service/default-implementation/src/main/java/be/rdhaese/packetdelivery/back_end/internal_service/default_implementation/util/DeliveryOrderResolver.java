package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Robin D'Haese
 */
@Component
public class DeliveryOrderResolver {

    @Autowired
    private AddressToGoogleApiStringConverter addressConverter;

    @Autowired
    private GeoApiContext geoApiContext;

    public List<Packet> sort(Address companyAddress, List<Packet> packets) throws Exception {
        //let google determine the best order to deliver
        //Convert company address to string that google API can handle
        String companyAddressAsString = addressConverter.convert(companyAddress);
        //Create the DirectionsApiRequest
        //This will give us directions, thanks to optimizeWaypoints(true) the addresses in the directions will be sorted to
        //a route Google thinks is good. We can use that order to sort our packets in the order they should be delivered
        DirectionsApiRequest directionsApiRequest =
                //companyAddress is the place to start and return to
                DirectionsApi.getDirections(geoApiContext, companyAddressAsString, companyAddressAsString)
                        //Alternative routes are not necessary
                        .alternatives(false)
                                //Makes google sort the addresses
                        .optimizeWaypoints(true);

        //Create waypoints array (packet addresses in string format)
        String[] waypoints = new String[packets.size()];
        for (int index = 0; index < packets.size(); index++) {
            waypoints[index] = addressConverter.convert(packets.get(index).getDeliveryInfo().getClientInfo().getAddress());
        }

        //Set the waypoints to the request
        directionsApiRequest.waypoints(waypoints);

        //Make the request and get the routes
        DirectionsRoute[] directionsRoutes = directionsApiRequest.await().routes;

        //Use the waypoint order to sort the packets
        List<Packet> packetsSortedOnOrderToDeliver = new ArrayList<>();
        for (Integer waypoint : directionsRoutes[0].waypointOrder) {
            packetsSortedOnOrderToDeliver.add(packets.get(waypoint));
        }

        return packetsSortedOnOrderToDeliver;
    }
}
