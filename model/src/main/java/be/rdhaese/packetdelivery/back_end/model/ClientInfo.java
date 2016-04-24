package be.rdhaese.packetdelivery.back_end.model;

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
public class ClientInfo extends AbstractEntity {

    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ContactDetails contactDetails;
    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientInfo that = (ClientInfo) o;

        if (getContactDetails() != null ? !getContactDetails().equals(that.getContactDetails()) : that.getContactDetails() != null)
            return false;
        return !(getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null);

    }

    @Override
    public int hashCode() {
        int result = getContactDetails() != null ? getContactDetails().hashCode() : 0;
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        return result;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("Client: %s", contactDetails.getName());
    }
}
