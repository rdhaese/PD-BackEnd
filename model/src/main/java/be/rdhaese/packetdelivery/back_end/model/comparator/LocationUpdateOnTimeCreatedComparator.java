package be.rdhaese.packetdelivery.back_end.model.comparator;

import be.rdhaese.packetdelivery.back_end.model.LocationUpdate;

import java.util.Comparator;

/**
 * Created on 23/04/2016.
 *
 * @author Robin D'Haese
 */
public class LocationUpdateOnTimeCreatedComparator implements Comparator<LocationUpdate> {

    @Override
    public int compare(LocationUpdate o1, LocationUpdate o2) {
        return o1.getTimeCreated().compareTo(o2.getTimeCreated()); //TODO maybe switch o's around
    }
}
