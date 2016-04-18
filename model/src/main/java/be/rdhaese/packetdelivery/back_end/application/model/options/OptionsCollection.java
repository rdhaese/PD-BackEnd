package be.rdhaese.packetdelivery.back_end.application.model.options;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * Created on 17/04/2016.
 *
 * @author Robin D'Haese
 */
@XmlRootElement(name = "options")
public class OptionsCollection {

    private Collection<Options> options;

    public OptionsCollection(){
        options = new HashSet<>();
    }


    @XmlElementWrapper(name = "user-options", required = false)
    @XmlElement(name = "options-entry")
    public Collection<Options> getOptions() {
        return options;
    }

    public Boolean addOptions(Options options){
        this.options.remove(options);
        return this.options.add(options);
    }

    public void setOptions(Collection<Options> options) {
        this.options = options;
    }
}
