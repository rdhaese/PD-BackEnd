package be.rdhaese.packetdelivery.back_end.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created on 15/04/2016.
 *
 * @author Robin D'Haese
 */
@Embeddable
public class RegionName {

    @Column(nullable = false) //@NotNull not working in @Embeddable...
    private String nl;
    @Column(nullable = false) //@NotNull not working in @Embeddable...
    private String fr;
    @Column(nullable = false) //@NotNull not working in @Embeddable...
    private String de;
    @Column(nullable = false) //@NotNull not working in @Embeddable...
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegionName that = (RegionName) o;

        if (getNl() != null ? !getNl().equals(that.getNl()) : that.getNl() != null) return false;
        return !(getFr() != null ? !getFr().equals(that.getFr()) : that.getFr() != null) && !(getDe() != null ? !getDe().equals(that.getDe()) : that.getDe() != null) && !(getEn() != null ? !getEn().equals(that.getEn()) : that.getEn() != null);

    }

    @Override
    public int hashCode() {
        int result = getNl() != null ? getNl().hashCode() : 0;
        result = 31 * result + (getFr() != null ? getFr().hashCode() : 0);
        result = 31 * result + (getDe() != null ? getDe().hashCode() : 0);
        result = 31 * result + (getEn() != null ? getEn().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
