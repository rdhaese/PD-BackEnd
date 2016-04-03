package be.rdhaese.packetdelivery.back_end.application.model;

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
    private List<String> emails = new ArrayList<String>();
    @ElementCollection
    private List<String> phoneNumbers = new ArrayList<String>();

    public boolean addEmail(String email){
        return emails.add(email);
    }

    public boolean addPhoneNumber(String phoneNumber){
        return phoneNumbers.add(phoneNumber);
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
        return null;
    }
}
