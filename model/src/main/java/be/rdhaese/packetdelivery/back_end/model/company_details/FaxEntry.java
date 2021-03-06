package be.rdhaese.packetdelivery.back_end.model.company_details;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author Robin D'Haese
 */
@XmlRootElement(name = "fax")
public class FaxEntry implements Serializable {
    private String title;
    private String number;

    public FaxEntry() {
    }

    public FaxEntry(String title, String number) {
        this.title = title;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FaxEntry faxEntry = (FaxEntry) o;

        return !(getTitle() != null ? !getTitle().equals(faxEntry.getTitle()) : faxEntry.getTitle() != null);

    }

    @Override
    public int hashCode() {
        return getTitle() != null ? getTitle().hashCode() : 0;
    }

    @XmlElement(name = "fax-title")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @XmlElement(name = "fax-number")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
