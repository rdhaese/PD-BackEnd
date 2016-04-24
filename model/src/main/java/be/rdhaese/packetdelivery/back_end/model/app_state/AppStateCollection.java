package be.rdhaese.packetdelivery.back_end.model.app_state;

import be.rdhaese.packetdelivery.back_end.model.options.Options;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created on 21/04/2016.
 *
 * @author Robin D'Haese
 */
@XmlRootElement(name = "appStates")
public class AppStateCollection {

    private Collection<AppState> appStates;

    public AppStateCollection(){
        appStates = new HashSet<>();
    }


    @XmlElementWrapper(name = "appStates-entries", required = false)
    @XmlElement(name = "appState")
    public Collection<AppState> getAppStates() {
        return appStates;
    }

    public Boolean addAppState(AppState appState){
        this.appStates.remove(appState);
        return this.appStates.add(appState);
    }

    public void setAppStates(Collection<AppState> appStates) {
        this.appStates = appStates;
    }
}
