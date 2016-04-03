package be.rdhaese.packetdelivery.back_end.application.model.company_details;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created on 27/12/2015.
 *
 * @author Robin D'Haese
 */
@XmlRootElement (name = "email")
public class EmailEntry implements Serializable{
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

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return !(address != null ? !address.equals(that.address) : that.address != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
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
}
