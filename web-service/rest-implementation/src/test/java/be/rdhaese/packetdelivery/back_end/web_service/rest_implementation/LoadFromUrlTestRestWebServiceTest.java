package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import org.junit.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author Robin D'Haese
 */
public class LoadFromUrlTestRestWebServiceTest extends AbstractRestWebServiceTest {

    @Test
    public void testPacket2ReturnsPacket2() throws Exception {
        mockMvc.perform(get("/test-service/packet2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.deliveryCity", is("Oostende")));
    }

    @Test
    public void testPacket3ReturnsPacket3() throws Exception {
        mockMvc.perform(get("/test-service/packet3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.deliveryCity", isEmptyOrNullString()));
    }

    @Test
    public void testAllOtherUrlsReturnPacket1() throws Exception {
        mockMvc.perform(get("/test-service/packet1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.deliveryCity", is("Genk")));

        mockMvc.perform(get("/test-service/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.deliveryCity", is("Genk")));

        mockMvc.perform(get("/test-service"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.deliveryCity", is("Genk")));

        mockMvc.perform(get("/test-service/packet1/pkosdfpkosdfo"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.deliveryCity", is("Genk")));

        mockMvc.perform(get("/test-service/packet1/sjisdjif/sdosdpkosdpkosdf/dsofksdof"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.deliveryCity", is("Genk")));
    }
}
