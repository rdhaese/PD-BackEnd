package be.rdhaese.packetdelivery.back_end.model.app_state;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Robin D'Haese
 */
@XmlType
@XmlEnum
public enum AppStateActivity {
    @XmlEnumValue("searching")SEARCHING,
    @XmlEnumValue("loading")LOADING,
    @XmlEnumValue("ongoing")ONGOING;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
