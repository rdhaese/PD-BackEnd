package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.OptionsInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.options.Options;
import be.rdhaese.packetdelivery.dto.OptionsDTO;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Robin D'Haese
 */
public class OptionsRestWebServiceTest extends AbstractRestWebServiceTest {

    @Autowired //Mock, see TestConfig
    private OptionsInternalService optionsInternalService;

    @Autowired //Mock, see TestConfig
    private Mapper<Options, OptionsDTO> optionsMapper;

    @After
    public void tearDown() {
        reset(optionsInternalService, optionsMapper);
    }

    @Test
    public void testGetForUsername() throws Exception {
        Options options = new Options("user", "lang", 1, true);
        OptionsDTO optionsDTO = new OptionsDTO("user", "lang", 1, true);

        when(optionsInternalService.getFor("username")).thenReturn(options);
        when(optionsMapper.mapToDto(options)).thenReturn(optionsDTO);

        mockMvc.perform(get("/options/username"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().bytes(AbstractRestWebServiceTest.convertObjectToJsonBytes(optionsDTO)));

        verify(optionsInternalService, times(1)).getFor("username");
        verify(optionsMapper, times(1)).mapToDto(options);
    }

    @Test
    public void testSave() throws Exception {
        Options options = new Options("user", "lang", 1, true);
        OptionsDTO optionsDTO = new OptionsDTO("user", "lang", 1, true);

        when(optionsMapper.mapToBus(optionsDTO)).thenReturn(options);
        when(optionsInternalService.save(options)).thenReturn(true);

        mockMvc.perform(post("/options/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(AbstractRestWebServiceTest.convertObjectToJsonBytes(optionsDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(optionsMapper, times(1)).mapToBus(optionsDTO);
        verify(optionsInternalService, times(1)).save(options);
    }
}
