package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.AddPacketInternalService;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.OptionsInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.model.options.Options;
import be.rdhaese.packetdelivery.dto.OptionsDTO;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Created on 13/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OptionsRestWebServiceTest.Config.class)
@WebAppConfiguration
public class OptionsRestWebServiceTest {

    @Configuration
    @EnableWebMvc
    static class Config{

        //Controller to test
        @Bean
        public OptionsRestWebService optionsRestWebService(){
            return new OptionsRestWebService();
        }

        //Mocks
        @Bean
        public OptionsInternalService optionsInternalService(){
            return mock(OptionsInternalService.class);
        }

        @Bean
        public Mapper<Options, OptionsDTO> optionsMapper(){
            return mock(Mapper.class);
        }
    }

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Autowired
    private OptionsInternalService optionsInternalService;

    @Autowired
    private Mapper<Options, OptionsDTO> optionsMapper;

    @Test
    public void testGetForUsername() throws Exception {
        Options options = new Options("user", "lang", 1, true);
        OptionsDTO optionsDTO = new OptionsDTO("user", "lang", 1 ,true);

        when(optionsInternalService.getFor("username")).thenReturn(options);
        when(optionsMapper.mapToDto(options)).thenReturn(optionsDTO);

        mockMvc.perform(get("/options/username"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(content().bytes(TestUtil.convertObjectToJsonBytes(optionsDTO)));

        verify(optionsInternalService, times(1)).getFor("username");
        verify(optionsMapper, times(1)).mapToDto(options);
    }

    @Test
    public void testSave() throws Exception {
        Options options = new Options("user", "lang", 1, true);
        OptionsDTO optionsDTO = new OptionsDTO("user", "lang", 1 ,true);

        when(optionsMapper.mapToBus(optionsDTO)).thenReturn(options);
        when(optionsInternalService.save(options)).thenReturn(true);

        mockMvc.perform(post("/options/save")
        .contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(optionsDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(optionsMapper, times(1)).mapToBus(optionsDTO);
        verify(optionsInternalService, times(1)).save(options);
    }
}
