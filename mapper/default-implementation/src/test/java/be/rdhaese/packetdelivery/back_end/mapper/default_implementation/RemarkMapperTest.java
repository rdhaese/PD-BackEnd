package be.rdhaese.packetdelivery.back_end.mapper.default_implementation;

import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Remark;
import be.rdhaese.packetdelivery.dto.RemarkDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createRemark;
import static junit.framework.TestCase.assertEquals;

/**
 * Created on 13/05/2016.
 *
 * @author Robin D'Haese
 */
public class RemarkMapperTest {

    private Mapper<Remark, RemarkDTO> mapper;

    private Remark remark;
    private RemarkDTO remarkDto;

    @Before
    public void setUp() {
        mapper = new RemarkMapper();
        Date date = Calendar.getInstance().getTime();
        remark = createRemark(date, "remark");
        remarkDto = new RemarkDTO("remark", date);
    }

    @Test
    public void testMapToBus() {
        assertEquals(remark, mapper.mapToBus(remarkDto));
    }

    @Test
    public void testMapToDto() {
        assertEquals(remarkDto, mapper.mapToDto(remark));
    }
}
