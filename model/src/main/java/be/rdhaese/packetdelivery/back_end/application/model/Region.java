package be.rdhaese.packetdelivery.back_end.application.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 24/11/2015.
 *
 * @author Robin D'Haese
 */
@Entity
public class Region extends AbstractEntity {

    @NotNull
    @Embedded
    private RegionName name;

    @NotNull
    private String regionCode;
    
    @ManyToMany (cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    private Set<Region> adjacentRegions = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        if (getName() != null ? !getName().equals(region.getName()) : region.getName() != null) return false;
        return !(getRegionCode() != null ? !getRegionCode().equals(region.getRegionCode()) : region.getRegionCode() != null);

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getRegionCode() != null ? getRegionCode().hashCode() : 0);
        return result;
    }

    public boolean addAdjacentRegion(Region region){
        return adjacentRegions.add(region);
    }

    public Set<Region> getAdjacentRegions() {
        return adjacentRegions;
    }

    public void setAdjacentRegions(Set<Region> adjacentRegions) {
        this.adjacentRegions = adjacentRegions;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public RegionName getName() {
        return name;
    }

    public void setName(RegionName name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return regionCode;
    }
}
