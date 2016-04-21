package be.rdhaese.packetdelivery.back_end.model.options;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created on 17/04/2016.
 *
 * @author Robin D'Haese
 */
@XmlRootElement(name = "options-entry")
public class Options {

    private String user;
    private String language = "English";
    private Integer print = 0;
    private Boolean imageViewer = true;

    public Options() {
    }

    public Options(String user, String language, Integer print, Boolean imageViewer) {
        this.user = user;
        this.language = language;
        this.print = print;
        this.imageViewer = imageViewer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Options options = (Options) o;

        return !(getUser() != null ? !getUser().equals(options.getUser()) : options.getUser() != null);

    }

    @Override
    public int hashCode() {
        return getUser() != null ? getUser().hashCode() : 0;
    }

    @XmlElement(name = "user")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @XmlElement (name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @XmlElement (name = "print")
    public Integer getPrint() {
        return print;
    }

    public void setPrint(Integer print) {
        this.print = print;
    }

    @XmlElement (name = "image-viewer")
    public Boolean getImageViewer() {
        return imageViewer;
    }

    public void setImageViewer(Boolean imageViewer) {
        this.imageViewer = imageViewer;
    }
}
