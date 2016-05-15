package be.rdhaese.packetdelivery.back_end.mapper.default_implementation;

import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.options.Options;
import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.OptionsDTO;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createAddress;
import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createOptions;

/**
 * Created on 13/05/2016.
 *
 * @author Robin D'Haese
 */
public class OptionsMapperTest extends TestCase {

    private Mapper<Options, OptionsDTO> mapper;

    private Options options;
    private OptionsDTO optionsDto;

    @Before
    public void setUp(){
        mapper = new OptionsMapper();
        options = createOptions("user", "language", 1, true);
        optionsDto = new OptionsDTO("user", "language", 1, true);
    }

    @Test
    public void testMapToBus(){
        assertEquals(options, mapper.mapToBus(optionsDto));
    }

    @Test
    public void testMapToDto(){
        assertEquals(optionsDto, mapper.mapToDto(options));
    }
}
