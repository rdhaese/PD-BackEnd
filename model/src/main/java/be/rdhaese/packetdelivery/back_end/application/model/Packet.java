package be.rdhaese.packetdelivery.back_end.application.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created on 24/11/2015.
 *
 * @author Robin D'Haese
 */
@Entity
public class Packet extends AbstractEntity {

    @NotNull
    private String packetId;
    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ClientInfo clientInfo;
    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private DeliveryInfo deliveryInfo;
    @Enumerated(EnumType.STRING)
    private PacketStatus packetStatus;
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusChangedOn;
    @NotNull
    private Integer priority = 1;

    public String getPacketId() {
        return packetId;
    }

    public void setPacketId(String packetId) {
        this.packetId = packetId;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public PacketStatus getPacketStatus() {
        return packetStatus;
    }

    public void setPacketStatus(PacketStatus packetStatus) {
        this.packetStatus = packetStatus;
        setStatusChangedOn(new Date());
    }

    public Date getStatusChangedOn() {
        return statusChangedOn;
    }

    public void setStatusChangedOn(Date statusChangedOn) {
        this.statusChangedOn = statusChangedOn;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Packet packet = (Packet) o;

        if (getPacketId() != null ? !getPacketId().equals(packet.getPacketId()) : packet.getPacketId() != null)
            return false;
        if (getClientInfo() != null ? !getClientInfo().equals(packet.getClientInfo()) : packet.getClientInfo() != null)
            return false;
        if (getDeliveryInfo() != null ? !getDeliveryInfo().equals(packet.getDeliveryInfo()) : packet.getDeliveryInfo() != null)
            return false;
        if (getPacketStatus() != packet.getPacketStatus()) return false;
        if (getStatusChangedOn() != null ? !getStatusChangedOn().equals(packet.getStatusChangedOn()) : packet.getStatusChangedOn() != null)
            return false;
        return !(getPriority() != null ? !getPriority().equals(packet.getPriority()) : packet.getPriority() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getPacketId() != null ? getPacketId().hashCode() : 0);
        result = 31 * result + (getClientInfo() != null ? getClientInfo().hashCode() : 0);
        result = 31 * result + (getDeliveryInfo() != null ? getDeliveryInfo().hashCode() : 0);
        result = 31 * result + (getPacketStatus() != null ? getPacketStatus().hashCode() : 0);
        result = 31 * result + (getStatusChangedOn() != null ? getStatusChangedOn().hashCode() : 0);
        result = 31 * result + (getPriority() != null ? getPriority().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Packet [ID: %s]", packetId);
    }
}
