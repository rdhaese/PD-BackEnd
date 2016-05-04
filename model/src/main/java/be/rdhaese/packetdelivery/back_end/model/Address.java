package be.rdhaese.packetdelivery.back_end.model;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (getStreet() != null ? !getStreet().equals(address.getStreet()) : address.getStreet() != null) return false;
        if (getNumber() != null ? !getNumber().equals(address.getNumber()) : address.getNumber() != null) return false;
        if (getMailbox() != null ? !getMailbox().equals(address.getMailbox()) : address.getMailbox() != null)
            return false;
        if (getCity() != null ? !getCity().equals(address.getCity()) : address.getCity() != null) return false;
        return !(getPostalCode() != null ? !getPostalCode().equals(address.getPostalCode()) : address.getPostalCode() != null);

    }

    @Override
    public int hashCode() {
        int result = getStreet() != null ? getStreet().hashCode() : 0;
        result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
        result = 31 * result + (getMailbox() != null ? getMailbox().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getPostalCode() != null ? getPostalCode().hashCode() : 0);
        return result;
    }

    @XmlElement (name = "street", required = true)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @XmlElement (name = "number" , required = true)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @XmlElement (name = "mailbox", required = false)
    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    @XmlElement (name = "city", required = true)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlElement (name = "postal-code", required = true)
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
        return ReflectionToStringBuilder.toString(this);
    }
}
