package be.rdhaese.packetdelivery.back_end.model.comparator;

import be.rdhaese.packetdelivery.back_end.model.Remark;

import java.util.Comparator;

/**
 * Created on 23/04/2016.
 *
 * @author Robin D'Haese
 */
public class RemarksOnTimeAddedComparator implements Comparator<Remark> {
    @Override
    public int compare(Remark o1, Remark o2) {
        return o1.getTimeAdded().compareTo(o2.getTimeAdded()); //TODO maybe switch o's around
    }
}
