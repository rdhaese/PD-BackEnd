package be.rdhaese.packetdelivery.back_end.application.model;


import javax.persistence.*;
import java.util.List;

/**
 * Created on 24/11/2015.
 *
 * @author Robin D'Haese
 */
@Entity
public class DeliveryRound extends AbstractEntity {

    @OneToMany
    private List<Packet> packets;
    @OneToMany (cascade = CascadeType.ALL)
    private List<LocationUpdate> locationUpdates;
    @OneToMany (cascade = CascadeType.ALL)
    private List<Remark> remarks;
    @Enumerated(EnumType.STRING)
    private RoundStatus roundStatus;

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
