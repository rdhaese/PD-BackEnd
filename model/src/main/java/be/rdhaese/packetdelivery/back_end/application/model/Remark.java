package be.rdhaese.packetdelivery.back_end.application.model;


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
