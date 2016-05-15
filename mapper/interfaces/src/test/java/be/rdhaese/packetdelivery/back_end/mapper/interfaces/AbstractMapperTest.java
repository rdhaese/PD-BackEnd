package be.rdhaese.packetdelivery.back_end.mapper.interfaces;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 12/05/2016.
 *
 * @author Robin D'Haese
 */
public class AbstractMapperTest extends TestCase{

    class AbstractMapperTestImpl extends AbstractMapper<Integer, String>{

        @Override
        public Integer mapToBus(String dto) {
            if (dto == null){
                return null;
            }
            return Integer.parseInt(dto);
        }

        @Override
        public String mapToDto(Integer busObj) {
           if (busObj == null){
               return null;
           }
            return busObj.toString();
        }
    }

    private AbstractMapperTestImpl abstractMapper;

    @Before
    public void setUp(){
        abstractMapper = new AbstractMapperTestImpl();
    }

    @Test
    public void testMapToBusCollection(){
        List<String> dtos = Arrays.asList("1", "2", null, "4");
        List<Integer> busObjs = new ArrayList<>(abstractMapper.mapToBus(dtos));
        assertNotNull(busObjs);
        assertEquals(4, busObjs.size());
        int nullPointerExceptionCount = 0;
        for (int index = 1; index <= busObjs.size(); index++){
            try {
                assertEquals(index, busObjs.get(index - 1).intValue());
            } catch (NullPointerException npe){
                nullPointerExceptionCount++;
            }
        }
        assertEquals(1, nullPointerExceptionCount);
    }

    @Test
    public void testMapToDtoCollection(){
        List<Integer> busObjs = Arrays.asList(1, 2, null, 4);
        List<String> dtos = new ArrayList<>(abstractMapper.mapToDto(busObjs));
        assertNotNull(dtos);
        assertEquals(4, dtos.size());
        assertEquals("1", dtos.get(0));
        assertEquals("2", dtos.get(1));
        assertNull(dtos.get(2));
        assertEquals("4", dtos.get(3));
    }
}
