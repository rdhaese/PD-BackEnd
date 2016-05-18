package be.rdhaese.packetdelivery.back_end.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 24/11/2015.
 *
 * @author Robin D'Haese
 */
@Entity
public class ContactDetails extends AbstractEntity {

    @NotNull
    private String name;
    @ElementCollection
    private List<String> emails = new ArrayList<>();
    @ElementCollection
    private List<String> phoneNumbers = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactDetails that = (ContactDetails) o;

        return !(getName() != null ? !getName().equals(that.getName()) : that.getName() != null);
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    public void addEmail(String email) {
        emails.add(email);
    }

    public void addPhoneNumber(String phoneNumber) {
        phoneNumbers.add(phoneNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
