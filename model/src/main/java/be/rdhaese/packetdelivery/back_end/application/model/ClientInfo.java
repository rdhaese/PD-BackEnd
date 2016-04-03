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
public class ClientInfo extends AbstractEntity {

    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ContactDetails contactDetails;
    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

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
