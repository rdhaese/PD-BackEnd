package be.rdhaese.packetdelivery.back_end.internal_service.util;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created on 21/04/2016.
 *
 * @author Robin D'Haese
 */
@Component
public class TagReplacer {

    public String replaceTags(String textWithTags, Map<String, String> tagReplacementMap){
        for (Map.Entry<String, String> tagWithReplacement : tagReplacementMap.entrySet()){
            String tag = String.format("\\[%s\\]", tagWithReplacement.getKey());
            textWithTags = textWithTags.replaceAll(tag, tagWithReplacement.getValue());
        }
        return textWithTags;
    }
}
