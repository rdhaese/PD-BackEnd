package be.rdhaese.packetdelivery.back_end.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Robin D'Haese
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
