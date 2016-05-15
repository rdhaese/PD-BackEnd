package be.rdhaese.packetdelivery.back_end.mapper.default_implementation;

import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.LocationUpdate;
import be.rdhaese.packetdelivery.back_end.model.Remark;
import be.rdhaese.packetdelivery.dto.LocationUpdateDTO;
import be.rdhaese.packetdelivery.dto.RemarkDTO;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createLocationUpdate;
import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createLongLat;
import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createRemark;

/**
 * Created on 13/05/2016.
 *
 * @author Robin D'Haese
 */
public class RemarkMapperTest extends TestCase {

    private Mapper<Remark, RemarkDTO> mapper;

    private Remark remark;
    private RemarkDTO remarkDto;
    private Date date;

    @Before
    public void setUp(){
        mapper = new RemarkMapper();
        date = Calendar.getInstance().getTime();
        remark = createRemark(date, "remark");
        remarkDto = new RemarkDTO("remark", date);
    }

    @Test
    public void testMapToBus(){
        assertEquals(remark, mapper.mapToBus(remarkDto));
    }

    @Test
    public void testMapToDto(){
        assertEquals(remarkDto, mapper.mapToDto(remark));
    }
}
