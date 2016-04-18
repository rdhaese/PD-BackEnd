package be.rdhaese.packetdelivery.back_end.application.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Created on 15/04/2016.
 *
 * @author Robin D'Haese
 */
@Embeddable
public class RegionName {

    @NotNull
    private String nl;
    @NotNull
    private String fr;
    @NotNull
    private String de;
    @NotNull
    private String en;

    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }
}
