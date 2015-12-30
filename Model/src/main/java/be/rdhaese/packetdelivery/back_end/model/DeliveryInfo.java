package be.rdhaese.packetdelivery.back_end.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created on 24/11/2015.
 *
 * @author Robin D'Haese
 */
@Entity
public class DeliveryInfo extends AbstractEntity {

    @OneToOne (cascade = CascadeType.ALL)
    private ClientInfo clientInfo;

    @OneToOne (cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Region region;

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return null;
    }
}
