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
    }

    public Date getStatusChangedOn() {
        return statusChangedOn;
    }

    public void setStatusChangedOn(Date statusChangedOn) {
        this.statusChangedOn = statusChangedOn;
    }

    @Override
    public String toString() {
        return null;
    }
}
