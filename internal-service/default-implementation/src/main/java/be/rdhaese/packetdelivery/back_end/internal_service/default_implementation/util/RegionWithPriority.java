package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import be.rdhaese.packetdelivery.back_end.model.Region;

/**
 * Created on 3/04/2016.
 *
 * @author Robin D'Haese
 */
public class RegionWithPriority {
    private Region region;
    private Integer priority = 0;
    private Integer packetCount = 0;

    public RegionWithPriority(Region region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegionWithPriority that = (RegionWithPriority) o;

        return !(getRegion() != null ? !getRegion().equals(that.getRegion()) : that.getRegion() != null);

    }

    @Override
    public int hashCode() {
        return getRegion() != null ? getRegion().hashCode() : 0;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
        if (this.priority == null) {
            //noinspection UnusedAssignment
            priority = 0;
        }
    }

    public void incrementPriority(Integer priority) {
        if (priority == null) {
            priority = 0;
        }
        this.priority += priority;
    }

    public Integer getPacketCount() {
        return packetCount;
    }

    public void setPacketCount(Integer packetCount) {
        this.packetCount = packetCount;
    }

    public void incrementPacketCount() {
        this.packetCount += 1;
    }
}
