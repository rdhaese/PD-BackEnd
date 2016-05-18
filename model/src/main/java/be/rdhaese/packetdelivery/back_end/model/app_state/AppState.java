package be.rdhaese.packetdelivery.back_end.model.app_state;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created on 21/04/2016.
 *
 * @author Robin D'Haese
 */
@XmlRootElement(name = "appState")
public class AppState {

    private String appId;
    private Long roundId;
    private AppStateActivity activity;
    private Integer currentPacketIndex = 0;

    public AppState() {
    }

    public AppState(String appId, Long roundId, AppStateActivity activity, Integer currentPacketIndex) {
        this.appId = appId;
        this.roundId = roundId;
        this.activity = activity;
        this.currentPacketIndex = currentPacketIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppState appState = (AppState) o;

        return !(getAppId() != null ? !getAppId().equals(appState.getAppId()) : appState.getAppId() != null);

    }

    @Override
    public int hashCode() {
        return getAppId() != null ? getAppId().hashCode() : 0;
    }

    @XmlElement(name = "appId", required = true)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @XmlElement(name = "roundId")
    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    @XmlElement(name = "activity")
    public AppStateActivity getActivity() {
        return activity;
    }

    public void setActivity(AppStateActivity activity) {
        this.activity = activity;
    }

    @XmlElement(name = "currentPacketIndex")
    public Integer getCurrentPacketIndex() {
        return currentPacketIndex;
    }

    public void setCurrentPacketIndex(Integer currentPacketIndex) {
        this.currentPacketIndex = currentPacketIndex;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
