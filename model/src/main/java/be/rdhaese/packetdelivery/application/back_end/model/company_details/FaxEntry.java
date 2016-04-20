package be.rdhaese.packetdelivery.application.back_end.model.company_details;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created on 27/12/2015.
 *
 * @author Robin D'Haese
 */
@XmlRootElement (name = "fax")
public class FaxEntry implements Serializable{
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

        if (title != null ? !title.equals(faxEntry.title) : faxEntry.title != null) return false;
        return !(number != null ? !number.equals(faxEntry.number) : faxEntry.number != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    @XmlElement (name = "fax-title")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @XmlElement (name = "fax-number")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
