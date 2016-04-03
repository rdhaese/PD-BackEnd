package be.rdhaese.packetdelivery.back_end.application.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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
    private String name;
    @NotNull
    private String regionCode;
    @ManyToMany (cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    private Set<Region> adjacentRegions = new HashSet<>();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
