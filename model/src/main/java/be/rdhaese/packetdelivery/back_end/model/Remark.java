package be.rdhaese.packetdelivery.back_end.model;


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
public class Remark extends AbstractEntity {
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeAdded;
    @NotNull
    private String remark;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Remark remark1 = (Remark) o;

        if (getTimeAdded() != null ? !getTimeAdded().equals(remark1.getTimeAdded()) : remark1.getTimeAdded() != null)
            return false;
        return !(getRemark() != null ? !getRemark().equals(remark1.getRemark()) : remark1.getRemark() != null);

    }

    @Override
    public int hashCode() {
        int result = getTimeAdded() != null ? getTimeAdded().hashCode() : 0;
        result = 31 * result + (getRemark() != null ? getRemark().hashCode() : 0);
        return result;
    }

    public Date getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Date timeAdded) {
        this.timeAdded = timeAdded;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return null;
    }
}
