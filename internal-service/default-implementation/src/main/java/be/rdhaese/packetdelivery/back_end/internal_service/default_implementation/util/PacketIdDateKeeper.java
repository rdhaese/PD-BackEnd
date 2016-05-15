package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created on 9/05/2016.
 *
 * @author Robin D'Haese
 */
@Component
public class PacketIdDateKeeper {

    private LocalDate lastDateChecked = LocalDate.now();

    public LocalDate getLastDateChecked() {
        return lastDateChecked;
    }

    public void setLastDateChecked(LocalDate lastDateChecked) {
        this.lastDateChecked = lastDateChecked;
    }

    public Boolean isAfterLastDateChecked(Date date) {
        LocalDate dateToCompareTo = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Boolean result = lastDateChecked.isBefore(dateToCompareTo);
        lastDateChecked = LocalDate.now();
        return result;
    }
}
