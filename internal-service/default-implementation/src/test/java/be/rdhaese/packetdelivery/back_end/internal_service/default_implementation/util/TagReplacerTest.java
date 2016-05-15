package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 5/05/2016.
 *
 * @author Robin D'Haese
 */
public class TagReplacerTest extends TestCase {

    private TagReplacer tagReplacer;

    private Map<String, String> tagsMap;
    private String textWithTags;

    @Before
    public void setUp(){
        tagReplacer = new TagReplacer();
        tagsMap = new HashMap<>();
        tagsMap.put("tag1", "replacedTag1");
        tagsMap.put("tag2", "replacedTag2");
        textWithTags = "Test text to replace [tag1] and [tag2]. [tag1] even 2 times!";
    }

    @Test
    public void testReplaceTagsWithNullText(){
        textWithTags = null;
        assertNull(tagReplacer.replaceTags(textWithTags, tagsMap));
    }

    @Test
    public void testReplaceTagsWithEmptyText(){
        textWithTags = "";
        assertEquals("", tagReplacer.replaceTags(textWithTags, tagsMap));
    }

    @Test
    public void testReplaceTagsWithNullMap(){
        tagsMap = null;
        assertEquals(textWithTags, tagReplacer.replaceTags(textWithTags,tagsMap));
    }

    @Test
    public void testReplaceTagsWithEmptyMap(){
        tagsMap = new HashMap<>();
        assertEquals(textWithTags, tagReplacer.replaceTags(textWithTags,tagsMap));
    }

    @Test
    public void testReplaceTagsWithTextWithoutTags(){
        textWithTags = "this text contains no tags";
        assertEquals(textWithTags, tagReplacer.replaceTags(textWithTags, tagsMap));
    }

    @Test
    public void testReplaceTags(){
        String expectedText = "Test text to replace replacedTag1 and replacedTag2. replacedTag1 even 2 times!";
        assertEquals(expectedText, tagReplacer.replaceTags(textWithTags, tagsMap));
    }
}
