package be.rdhaese.packetdelivery.application.back_end.web_service.interfaces;

import be.rdhaese.packetdelivery.dto.OptionsDTO;

/**
 * Created on 17/04/2016.
 *
 * @author Robin D'Haese
 */
public interface OptionsWebService {
    public static final Integer NEVER = 0;
    public static final Integer ASK = 1;
    public static final Integer PRINT = 2;

    public static final String NL = "Nederlands";
    public static final String FR = "Français";
    public static final String DE = "Deutsch";
    public static final String EN = "English";

    public static final String[] SUPPORTED_LANGUAGES = new String[]{NL, FR, DE, EN};

    public static final String TAG_NL = "nl";

    OptionsDTO getFor(String username);
    Boolean save(OptionsDTO optionsDTO);
}
