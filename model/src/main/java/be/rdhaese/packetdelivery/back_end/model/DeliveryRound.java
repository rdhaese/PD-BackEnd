package be.rdhaese.packetdelivery.back_end.model;


import be.rdhaese.packetdelivery.back_end.model.comparator.LocationUpdateOnTimeCreatedComparator;
import be.rdhaese.packetdelivery.back_end.model.comparator.RemarksOnTimeAddedComparator;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Robin D'Haese
 */
@Entity
public class DeliveryRound extends AbstractEntity {

    public static final Comparator<Remark> REMARK_COMPARATOR = new RemarksOnTimeAddedComparator();
    public static final Comparator<LocationUpdate> LOCATION_UPDATE_COMPARATOR = new LocationUpdateOnTimeCreatedComparator();

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private List<Packet> packets;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<LocationUpdate> locationUpdates = new TreeSet<>(LOCATION_UPDATE_COMPARATOR);
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Remark> remarks = new TreeSet<>(REMARK_COMPARATOR);
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoundStatus roundStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryRound that = (DeliveryRound) o;

        if (getPackets() != null ? !getPackets().equals(that.getPackets()) : that.getPackets() != null) return false;
        return !(getLocationUpdates() != null ? !getLocationUpdates().equals(that.getLocationUpdates()) : that.getLocationUpdates() != null) && !(getRemarks() != null ? !getRemarks().equals(that.getRemarks()) : that.getRemarks() != null) && getRoundStatus() == that.getRoundStatus();
    }

    @Override
    public int hashCode() {
        int result = getPackets() != null ? getPackets().hashCode() : 0;
        result = 31 * result + (getLocationUpdates() != null ? getLocationUpdates().hashCode() : 0);
        result = 31 * result + (getRemarks() != null ? getRemarks().hashCode() : 0);
        result = 31 * result + (getRoundStatus() != null ? getRoundStatus().hashCode() : 0);
        return result;
    }

    public List<Packet> getPackets() {
        return packets;
    }

    public void setPackets(List<Packet> packets) {
        this.packets = packets;
    }

    public Set<LocationUpdate> getLocationUpdates() {
        return locationUpdates;
    }

    public void setLocationUpdates(Set<LocationUpdate> locationUpdates) {
        this.locationUpdates = locationUpdates;
    }

    public Set<Remark> getRemarks() {
        return remarks;
    }

    public void setRemarks(Set<Remark> remarks) {
        this.remarks = remarks;
    }

    public RoundStatus getRoundStatus() {
        return roundStatus;
    }

    public void setRoundStatus(RoundStatus roundStatus) {
        this.roundStatus = roundStatus;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
