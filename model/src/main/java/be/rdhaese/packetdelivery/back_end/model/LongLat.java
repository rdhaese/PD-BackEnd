package be.rdhaese.packetdelivery.back_end.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created on 21/02/2016.
 *
 * @author Robin D'Haese
 */
@Embeddable
public class LongLat {

    @Column(nullable = false) //@NotNull not working in @Embeddable...
    private Double longitude;
    @Column(nullable = false) //@NotNull not working in @Embeddable...
    private Double latitude;

    public LongLat() {
    }

    public LongLat(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LongLat longLat = (LongLat) o;

        return !(longitude != null ? !longitude.equals(longLat.longitude) : longLat.longitude != null) && !(latitude != null ? !latitude.equals(longLat.latitude) : longLat.latitude != null);

    }

    @Override
    public int hashCode() {
        int result = longitude != null ? longitude.hashCode() : 0;
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        return result;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
