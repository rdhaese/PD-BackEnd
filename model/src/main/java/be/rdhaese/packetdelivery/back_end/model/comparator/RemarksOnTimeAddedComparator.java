package be.rdhaese.packetdelivery.back_end.model.comparator;

import be.rdhaese.packetdelivery.back_end.model.Remark;

import java.util.Comparator;

/**
 *
 * @author Robin D'Haese
 */
public class RemarksOnTimeAddedComparator implements Comparator<Remark> {
    @Override
    public int compare(Remark o1, Remark o2) {
        return o2.getTimeAdded().compareTo(o1.getTimeAdded());
    }
}
