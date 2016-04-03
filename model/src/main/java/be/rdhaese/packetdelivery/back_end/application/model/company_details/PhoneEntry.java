package be.rdhaese.packetdelivery.back_end.application.model.company_details;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created on 27/12/2015.
 *
 * @author Robin D'Haese
 */
@XmlRootElement (name = "phone")
public class PhoneEntry implements Serializable{
    private String title;
    private String number;

    public PhoneEntry() {
    }

    public PhoneEntry(String title, String number) {
        this.title = title;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneEntry that = (PhoneEntry) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return !(number != null ? !number.equals(that.number) : that.number != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }


    @XmlElement (name = "phone-title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement (name = "phone-number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
