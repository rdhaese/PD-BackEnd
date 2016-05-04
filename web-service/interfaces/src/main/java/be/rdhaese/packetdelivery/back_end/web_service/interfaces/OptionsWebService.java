package be.rdhaese.packetdelivery.back_end.web_service.interfaces;

import be.rdhaese.packetdelivery.dto.OptionsDTO;

/**
 * Created on 17/04/2016.
 *
 * @author Robin D'Haese
 */
public interface OptionsWebService {
    Integer NEVER = 0;
    Integer ASK = 1;
    Integer PRINT = 2;

    String NL = "Nederlands";
    String FR = "Français";
    String DE = "Deutsch";
    String EN = "English";

    String[] SUPPORTED_LANGUAGES = new String[]{NL, FR, DE, EN};

    String TAG_NL = "nl";

    OptionsDTO getFor(String username) throws Exception;
    Boolean save(OptionsDTO optionsDTO) throws Exception;
}
