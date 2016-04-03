package be.rdhaese.packetdelivery.back_end.application.model;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created on 24/11/2015.
 *
 * @author Robin D'Haese
 */
@Entity
public class LocationUpdate extends AbstractEntity {

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;

    @Embedded
    private LongLat longLat;

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
        return null;
    }
}
