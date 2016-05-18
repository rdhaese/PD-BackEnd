package be.rdhaese.packetdelivery.back_end.model.company_details;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created on 27/12/2015.
 *
 * @author Robin D'Haese
 */
@XmlRootElement(name = "email")
public class EmailEntry implements Serializable {
    private String title;
    private String address;

    public EmailEntry() {
    }

    public EmailEntry(String title, String address) {
        this.title = title;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailEntry that = (EmailEntry) o;

        return !(getTitle() != null ? !getTitle().equals(that.getTitle()) : that.getTitle() != null);

    }

    @Override
    public int hashCode() {
        return getTitle() != null ? getTitle().hashCode() : 0;
    }

    @XmlElement(name = "email-title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "email-address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
