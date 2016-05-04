package be.rdhaese.packetdelivery.back_end.model;

import static be.rdhaese.packetdelivery.back_end.testing.TestUtil.*;

import be.rdhaese.packetdelivery.back_end.config.ModelTestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created on 2/05/2016.
 *
 * @author Robin D'Haese
 */
public class RemarkTest extends AbstractModelTest {

    private Remark remark;

    @Before
    public void setUp(){
        remark = createRemark(Calendar.getInstance().getTime() , "test remark text");
    }

    @Test
    public void testCanPersist(){
        //Check if id is null on creation
        assertNull(remark.getId());

        //Persist
        persistFlushAndClear(remark);

        //Check if id is assigned
        assertNotNull(remark.getId());

        //Check if remark can be found on assigned id
        Remark newRemark =  getEntityManager().find(Remark.class, remark.getId());
        assertNotNull(newRemark);
        assertEquals(remark.getRemark(), newRemark.getRemark());
    }

    @Test(expected = ConstraintViolationException.class)
    public void testTimeAddedCannotBeNull(){
        remark.setTimeAdded(null);

        persistFlushAndClear(remark);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testRemarkCannotBeNull(){
        remark.setRemark(null);

        persistFlushAndClear(remark);
    }
}
