package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author Robin D'Haese
 */
@Component
@ConfigurationProperties(prefix = "internal_service")
public class InternalServiceProperties {
    private String apiKey;
    private Double defaultLongitude;
    private Double defaultLatitude;
    private String fromAddress;
    private String replyToAddress;
    private String packetDepartedSubject;
    private String packetDepartedText;
    private String packetDeliveredSubject;
    private String packetDeliveredText;
    private String packetNotDeliveredSubject;
    private String packetNotDeliveredText;
    private String packetLostSubject;
    private String packetLostText;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Double getDefaultLongitude() {
        return defaultLongitude;
    }

    public void setDefaultLongitude(Double defaultLongitude) {
        this.defaultLongitude = defaultLongitude;
    }

    public Double getDefaultLatitude() {
        return defaultLatitude;
    }

    public void setDefaultLatitude(Double defaultLatitude) {
        this.defaultLatitude = defaultLatitude;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getReplyToAddress() {
        return replyToAddress;
    }

    public void setReplyToAddress(String replyToAddress) {
        this.replyToAddress = replyToAddress;
    }

    public String getPacketDepartedSubject() {
        return packetDepartedSubject;
    }

    public void setPacketDepartedSubject(String packetDepartedSubject) {
        this.packetDepartedSubject = packetDepartedSubject;
    }

    public String getPacketDepartedText() {
        return packetDepartedText;
    }

    public void setPacketDepartedText(String packetDepartedText) {
        this.packetDepartedText = packetDepartedText;
    }

    public String getPacketDeliveredSubject() {
        return packetDeliveredSubject;
    }

    public void setPacketDeliveredSubject(String packetDeliveredSubject) {
        this.packetDeliveredSubject = packetDeliveredSubject;
    }

    public String getPacketDeliveredText() {
        return packetDeliveredText;
    }

    public void setPacketDeliveredText(String packetDeliveredText) {
        this.packetDeliveredText = packetDeliveredText;
    }

    public String getPacketNotDeliveredSubject() {
        return packetNotDeliveredSubject;
    }

    public void setPacketNotDeliveredSubject(String packetNotDeliveredSubject) {
        this.packetNotDeliveredSubject = packetNotDeliveredSubject;
    }

    public String getPacketNotDeliveredText() {
        return packetNotDeliveredText;
    }

    public void setPacketNotDeliveredText(String packetNotDeliveredText) {
        this.packetNotDeliveredText = packetNotDeliveredText;
    }

    public String getPacketLostSubject() {
        return packetLostSubject;
    }

    public void setPacketLostSubject(String packetLostSubject) {
        this.packetLostSubject = packetLostSubject;
    }

    public String getPacketLostText() {
        return packetLostText;
    }

    public void setPacketLostText(String packetLostText) {
        this.packetLostText = packetLostText;
    }
}
