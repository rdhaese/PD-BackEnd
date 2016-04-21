package be.rdhaese.packetdelivery.back_end.model;


import javax.persistence.*;
import java.util.List;

/**
 * Created on 24/11/2015.
 *
 * @author Robin D'Haese
 */
@Entity
public class DeliveryRound extends AbstractEntity {

    @OneToMany (cascade = {CascadeType.DETACH, CascadeType.REFRESH} )//{CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    private List<Packet> packets;
    @OneToMany (cascade = CascadeType.ALL)
    private List<LocationUpdate> locationUpdates;
    @OneToMany (cascade = CascadeType.ALL)
    private List<Remark> remarks;
    @Enumerated(EnumType.STRING)
    private RoundStatus roundStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryRound that = (DeliveryRound) o;

        if (getPackets() != null ? !getPackets().equals(that.getPackets()) : that.getPackets() != null) return false;
        if (getLocationUpdates() != null ? !getLocationUpdates().equals(that.getLocationUpdates()) : that.getLocationUpdates() != null)
            return false;
        if (getRemarks() != null ? !getRemarks().equals(that.getRemarks()) : that.getRemarks() != null) return false;
        return getRoundStatus() == that.getRoundStatus();

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

    public List<LocationUpdate> getLocationUpdates() {
        return locationUpdates;
    }

    public void setLocationUpdates(List<LocationUpdate> locationUpdates) {
        this.locationUpdates = locationUpdates;
    }

    public List<Remark> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<Remark> remarks) {
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
        return null;
    }
}
