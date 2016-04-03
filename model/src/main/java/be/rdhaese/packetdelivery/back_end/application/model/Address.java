package be.rdhaese.packetdelivery.back_end.application.model;


import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created on 24/11/2015.
 *
 * @author Robin D'Haese
 */
@Entity
@XmlRootElement (name = "address")
public class Address extends AbstractEntity {
    @NotNull
    private String street;
    @NotNull
    private String number;
    private String mailbox;
    @NotNull
    private String city;
    @NotNull
    private String postalCode;

    @XmlElement (name = "street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @XmlElement (name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @XmlElement (name = "mailbox")
    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    @XmlElement (name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlElement (name = "postal-code")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @java.beans.Transient
    @Override
    public Long getId() {
        return super.getId();
    }

    @java.beans.Transient
    @Override
    public Long getVersion() {
        return super.getVersion();
    }

    @Override
    public String toString() {
        return null;
    }
}
