package be.rdhaese.packetdelivery.back_end.mapper.default_implementation;


import be.rdhaese.packetdelivery.back_end.mapper.interfaces.AbstractMapper;
import be.rdhaese.packetdelivery.back_end.model.company_details.PhoneEntry;
import be.rdhaese.packetdelivery.back_end.model.company_details.EmailEntry;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyContactDetails;
import be.rdhaese.packetdelivery.back_end.model.company_details.FaxEntry;
import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created on 27/12/2015.
 *
 * @author Robin D'Haese
 */
@Component
public class CompanyContactDetailsMapper extends AbstractMapper<CompanyContactDetails, ContactDetailsDTO> {

    @Override
    public CompanyContactDetails mapToBus(ContactDetailsDTO dto) {
        if (dto == null){
            return null;
        }
        CompanyContactDetails companyContactDetails = new CompanyContactDetails();
        companyContactDetails.setCompanyName(dto.getCompanyName());
        companyContactDetails.setAboutText(dto.getAboutText());
        companyContactDetails.setAddress(mapAddress(dto));
        companyContactDetails.setPhoneNumbers(mapPhoneNumbers(dto));
        companyContactDetails.setFaxNumbers(mapFaxNumbers(dto));
        companyContactDetails.setEmailAddresses(mapEmailAddresses(dto));
        return companyContactDetails;
    }

    private Address mapAddress(ContactDetailsDTO dto) {
        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setNumber(dto.getNumber());
        address.setMailbox(dto.getMailbox());
        address.setCity(dto.getCity());
        address.setPostalCode(dto.getPostalCode());
        return address;
    }

    private List<PhoneEntry> mapPhoneNumbers(ContactDetailsDTO dto) {
        List<PhoneEntry> phoneEntries = new ArrayList<>();
        for (Map.Entry<String,String> entry : dto.getPhoneNumbers().entrySet()){
            PhoneEntry phoneEntry = new PhoneEntry();
            phoneEntry.setTitle(entry.getKey());
            phoneEntry.setNumber(entry.getValue());
            phoneEntries.add(phoneEntry);
        }
        return phoneEntries;
    }

    private List<FaxEntry> mapFaxNumbers(ContactDetailsDTO dto) {
        List<FaxEntry> faxNumbers = new ArrayList<>();
        for (Map.Entry<String, String> entry : dto.getFaxNumbers().entrySet()) {
               FaxEntry faxEntry = new FaxEntry();
            faxEntry.setTitle(entry.getKey());
            faxEntry.setNumber(entry.getValue());
            faxNumbers.add(faxEntry);
        }
        return faxNumbers;
    }

    private List<EmailEntry> mapEmailAddresses(ContactDetailsDTO dto) {
        List<EmailEntry> emailAddresses = new ArrayList<>();
        for (Map.Entry<String, String> entry : dto.getEmailAddresses().entrySet()) {
            EmailEntry emailEntry = new EmailEntry();
            emailEntry.setTitle(entry.getKey());
            emailEntry.setAddress(entry.getValue());
            emailAddresses.add(emailEntry);
        }
        return emailAddresses;
    }

    @Override
    public ContactDetailsDTO mapToDto(CompanyContactDetails busObj) {
        if (busObj == null){
            return null;
        }
        ContactDetailsDTO contactDetailsDTO = new ContactDetailsDTO();
        contactDetailsDTO.setCompanyName(busObj.getCompanyName());
        contactDetailsDTO.setAboutText(busObj.getAboutText());
        mapAddress(contactDetailsDTO, busObj);
        mapPhoneNumbers(contactDetailsDTO, busObj);
        mapFaxNumbers(contactDetailsDTO, busObj);
        mapEmailAddresses(contactDetailsDTO, busObj);
        return contactDetailsDTO;
    }

    private void mapAddress(ContactDetailsDTO contactDetailsDTO, CompanyContactDetails busObj) {
        if (busObj.getAddress() == null) return;
        contactDetailsDTO.setStreet(busObj.getAddress().getStreet());
        contactDetailsDTO.setNumber(busObj.getAddress().getNumber());
        contactDetailsDTO.setMailbox(busObj.getAddress().getMailbox());
        contactDetailsDTO.setCity(busObj.getAddress().getCity());
        contactDetailsDTO.setPostalCode(busObj.getAddress().getPostalCode());
    }

    private void mapPhoneNumbers(ContactDetailsDTO contactDetailsDTO, CompanyContactDetails busObj) {
        if (busObj.getPhoneNumbers() == null) return;
        for (PhoneEntry phoneEntry : busObj.getPhoneNumbers()){
            contactDetailsDTO.getPhoneNumbers().put(phoneEntry.getTitle(), phoneEntry.getNumber());
        }
    }

    private void mapFaxNumbers(ContactDetailsDTO contactDetailsDTO, CompanyContactDetails busObj) {
        if (busObj.getFaxNumbers() == null) return;
        for (FaxEntry faxEntry : busObj.getFaxNumbers()){
            contactDetailsDTO.getFaxNumbers().put(faxEntry.getTitle(), faxEntry.getNumber());
        }
    }

    private void mapEmailAddresses(ContactDetailsDTO contactDetailsDTO, CompanyContactDetails busObj) {
        if (busObj.getEmailAddresses() == null) return;
        for (EmailEntry emailEntry : busObj.getEmailAddresses()){
            contactDetailsDTO.getEmailAddresses().put(emailEntry.getTitle(),emailEntry.getAddress());
        }
    }
}
