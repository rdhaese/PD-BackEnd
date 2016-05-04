package be.rdhaese.packetdelivery.back_end.testing;

import be.rdhaese.packetdelivery.back_end.model.*;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppState;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppStateActivity;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppStateCollection;
import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyContactDetails;
import be.rdhaese.packetdelivery.back_end.model.company_details.EmailEntry;
import be.rdhaese.packetdelivery.back_end.model.company_details.FaxEntry;
import be.rdhaese.packetdelivery.back_end.model.company_details.PhoneEntry;
import be.rdhaese.packetdelivery.back_end.model.options.Options;
import be.rdhaese.packetdelivery.back_end.model.options.OptionsCollection;

import java.util.Date;
import java.util.List;

/**
 * Created on 2/05/2016.
 *
 * @author Robin D'Haese
 */
public class TestUtil {

    public static Remark createRemark(Date timeAdded, String remarkText) {
        Remark remark = new Remark();
        remark.setTimeAdded(timeAdded);
        remark.setRemark(remarkText);
        return remark;
    }

    public static Region createRegion(RegionName regionName, String regionCode) {
        Region region = new Region();
        region.setName(regionName);
        region.setRegionCode(regionCode);
        return region;
    }

    public static RegionName createRegionName(String nl, String fr, String de, String en) {
        RegionName regionName = new RegionName();
        regionName.setNl(nl);
        regionName.setFr(fr);
        regionName.setDe(de);
        regionName.setEn(en);
        return regionName;
    }

    public static LocationUpdate createLocationUpdate(Date timeCreated, LongLat longLat) {
        LocationUpdate locationUpdate = new LocationUpdate();
        locationUpdate.setTimeCreated(timeCreated);
        locationUpdate.setLongLat(longLat);
        return locationUpdate;
    }

    public static LongLat createLongLat(double longitude, double latitude) {
        LongLat longLat = new LongLat();
        longLat.setLongitude(longitude);
        longLat.setLatitude(latitude);
        return longLat;
    }

    public static Address createAddress(String street, String number, String mailBox, String postalCode, String city) {
        Address address = new Address();
        address.setStreet(street);
        address.setNumber(number);
        address.setMailbox(mailBox);
        address.setPostalCode(postalCode);
        address.setCity(city);
        return address;
    }

    public static ContactDetails createContactDetails(String name, List<String> phoneNumbers, List<String> emails) {
        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setName(name);
        contactDetails.setPhoneNumbers(phoneNumbers);
        contactDetails.setEmails(emails);
        return contactDetails;
    }

    public static ClientInfo createClientInfo(ContactDetails contactDetails, Address address) {
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setContactDetails(contactDetails);
        clientInfo.setAddress(address);
        return clientInfo;
    }

    public static DeliveryInfo createDeliveryInfo(ClientInfo clientInfo, Region region) {
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setClientInfo(clientInfo);
        deliveryInfo.setRegion(region);
        return deliveryInfo;
    }

    public static Packet createPacket(String packetId, ClientInfo clientInfo, DeliveryInfo deliveryInfo, PacketStatus packetStatus, Date statusChangedOn, int priority) {
        Packet packet = new Packet();
        packet.setPacketId(packetId);
        packet.setClientInfo(clientInfo);
        packet.setDeliveryInfo(deliveryInfo);
        packet.setPacketStatus(packetStatus);
        packet.setStatusChangedOn(statusChangedOn);
        packet.setPriority(priority);
        return packet;
    }

    public static DeliveryRound createDeliveryRound(List<Packet> packets, List<LocationUpdate> locationUpdates, List<Remark> remarks, RoundStatus roundStatus) {
        DeliveryRound deliveryRound = new DeliveryRound();
        deliveryRound.setPackets(packets);
        for (LocationUpdate locationUpdate : locationUpdates) {
            deliveryRound.getLocationUpdates().add(locationUpdate);
        }
        for (Remark remark : remarks) {
            deliveryRound.getRemarks().add(remark);
        }
        deliveryRound.setRoundStatus(roundStatus);
        return deliveryRound;
    }

    public static Options createOptions(String user, String language, int print, boolean imageViewer) {
        Options options = new Options();
        options.setUser(user);
        options.setLanguage(language);
        options.setPrint(print);
        options.setImageViewer(imageViewer);
        return options;
    }

    public static OptionsCollection createOptionsCollection(List<Options> optionsList) {
        OptionsCollection optionsCollection = new OptionsCollection();
        optionsCollection.setOptions(optionsList);
        return optionsCollection;
    }

    public static AppState createAppState(String appId, Long roundId, AppStateActivity activity, Integer currentPacketIndex) {
        AppState appState = new AppState();
        appState.setAppId(appId);
        appState.setRoundId(roundId);
        appState.setActivity(activity);
        appState.setCurrentPacketIndex(currentPacketIndex);
        return appState;
    }

    public static AppStateCollection createAppStateCollection(List<AppState> appStateList){
        AppStateCollection appStateCollection = new AppStateCollection();
        appStateCollection.setAppStates(appStateList);
        return appStateCollection;
    }

    public static EmailEntry createEmailEntry(String title, String address){
        EmailEntry emailEntry = new EmailEntry();
        emailEntry.setTitle(title);
        emailEntry.setAddress(address);
        return emailEntry;
    }

    public static FaxEntry createFaxEntry(String title, String number){
        FaxEntry faxEntry = new FaxEntry();
        faxEntry.setTitle(title);
        faxEntry.setNumber(number);
        return faxEntry;
    }

    public static PhoneEntry createPhoneEntry(String title, String number){
        PhoneEntry phoneEntry = new PhoneEntry();
        phoneEntry.setTitle(title);
        phoneEntry.setNumber(number);
        return phoneEntry;
    }

    public static CompanyContactDetails createCompanyContactDetails(String companyName, Address address, List<PhoneEntry> phoneEntries, List<FaxEntry> faxEntries, List<EmailEntry> emailEntries, String aboutText){
        CompanyContactDetails companyContactDetails = new CompanyContactDetails();
        companyContactDetails.setCompanyName(companyName);
        companyContactDetails.setAddress(address);
        companyContactDetails.setPhoneNumbers(phoneEntries);
        companyContactDetails.setFaxNumbers(faxEntries);
        companyContactDetails.setEmailAddresses(emailEntries);
        companyContactDetails.setAboutText(aboutText);
        return companyContactDetails;
    }
}
