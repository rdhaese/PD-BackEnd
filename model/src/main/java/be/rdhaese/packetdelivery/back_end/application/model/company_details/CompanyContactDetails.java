package be.rdhaese.packetdelivery.back_end.application.model.company_details;

import be.rdhaese.packetdelivery.back_end.application.model.Address;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created on 27/12/2015.
 *
 * @author Robin D'Haese
 */
@XmlRootElement (name = "contact-details")
public class CompanyContactDetails implements Serializable{
    private String companyName;
    private String aboutText;
    private Address address;
    private List<PhoneEntry> phoneNumbers;
    private List<FaxEntry> faxNumbers;
    private List<EmailEntry> emailAddresses;

    public CompanyContactDetails() {
    }

    public CompanyContactDetails(String companyName, String aboutText, Address address, List<PhoneEntry> phoneNumbers, List<FaxEntry> faxNumbers, List<EmailEntry> emailAddresses) {
        this.companyName = companyName;
        this.aboutText = aboutText;
        this.address = address;
        this.phoneNumbers = phoneNumbers;
        this.faxNumbers = faxNumbers;
        this.emailAddresses = emailAddresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyContactDetails that = (CompanyContactDetails) o;

        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (aboutText != null ? !aboutText.equals(that.aboutText) : that.aboutText != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (phoneNumbers != null ? !phoneNumbers.equals(that.phoneNumbers) : that.phoneNumbers != null) return false;
        if (faxNumbers != null ? !faxNumbers.equals(that.faxNumbers) : that.faxNumbers != null) return false;
        return !(emailAddresses != null ? !emailAddresses.equals(that.emailAddresses) : that.emailAddresses != null);

    }

    @Override
    public int hashCode() {
        int result = companyName != null ? companyName.hashCode() : 0;
        result = 31 * result + (aboutText != null ? aboutText.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phoneNumbers != null ? phoneNumbers.hashCode() : 0);
        result = 31 * result + (faxNumbers != null ? faxNumbers.hashCode() : 0);
        result = 31 * result + (emailAddresses != null ? emailAddresses.hashCode() : 0);
        return result;
    }

    @XmlElement (name="company-name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @XmlElement (name = "about-text")
    public String getAboutText() {
        return aboutText;
    }

    public void setAboutText(String aboutText) {
        this.aboutText = aboutText;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @XmlElementWrapper(name = "phone-numbers")
    @XmlElement(name = "phone")
    public List<PhoneEntry> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneEntry> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @XmlElementWrapper(name = "fax-numbers")
    @XmlElement(name = "fax")
    public List<FaxEntry> getFaxNumbers() {
        return faxNumbers;
    }

    public void setFaxNumbers(List<FaxEntry> faxNumbers) {
        this.faxNumbers = faxNumbers;
    }

    @XmlElementWrapper(name = "email-addresses")
    @XmlElement(name = "email")
    public List<EmailEntry> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(List<EmailEntry> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }
}
