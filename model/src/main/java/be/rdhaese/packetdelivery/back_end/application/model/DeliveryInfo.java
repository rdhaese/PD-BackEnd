package be.rdhaese.packetdelivery.back_end.application.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

/**
 * Created on 24/11/2015.
 *
 * @author Robin D'Haese
 */
@Entity
public class DeliveryInfo extends AbstractEntity {

    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ClientInfo clientInfo;

    @OneToOne (cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private Region region;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryInfo that = (DeliveryInfo) o;

        if (getClientInfo() != null ? !getClientInfo().equals(that.getClientInfo()) : that.getClientInfo() != null)
            return false;
        return !(getRegion() != null ? !getRegion().equals(that.getRegion()) : that.getRegion() != null);

    }

    @Override
    public int hashCode() {
        int result = getClientInfo() != null ? getClientInfo().hashCode() : 0;
        result = 31 * result + (getRegion() != null ? getRegion().hashCode() : 0);
        return result;
    }

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
