package be.rdhaese.packetdelivery.back_end.model;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 *
 * @author Robin D'Haese
 */
@Entity
public class LocationUpdate extends AbstractEntity {

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated = new Date();

    @Embedded
    @NotNull
    private LongLat longLat;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationUpdate that = (LocationUpdate) o;

        return !(getTimeCreated() != null ? !getTimeCreated().equals(that.getTimeCreated()) : that.getTimeCreated() != null) && !(getLongLat() != null ? !getLongLat().equals(that.getLongLat()) : that.getLongLat() != null);

    }

    @Override
    public int hashCode() {
        int result = getTimeCreated() != null ? getTimeCreated().hashCode() : 0;
        result = 31 * result + (getLongLat() != null ? getLongLat().hashCode() : 0);
        return result;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LongLat getLongLat() {
        return longLat;
    }

    public void setLongLat(LongLat longLat) {
        this.longLat = longLat;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
