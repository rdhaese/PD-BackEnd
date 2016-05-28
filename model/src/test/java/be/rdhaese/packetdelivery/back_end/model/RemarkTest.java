package be.rdhaese.packetdelivery.back_end.model;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Calendar;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createRemark;

/**
 *
 * @author Robin D'Haese
 */
public class RemarkTest extends AbstractModelTest {

    private Remark remark;

    @Before
    public void setUp() {
        remark = createRemark(Calendar.getInstance().getTime(), "test remark text");
    }

    @Test
    public void testCanPersist() {
        //Check if id is null on creation
        assertNull(remark.getId());

        //Persist
        persistFlushAndClear(remark);

        //Check if id is assigned
        assertNotNull(remark.getId());

        //Check if remark can be found on assigned id
        Remark newRemark = getEntityManager().find(Remark.class, remark.getId());
        assertNotNull(newRemark);
        assertEquals(remark.getRemark(), newRemark.getRemark());
    }

    @Test(expected = ConstraintViolationException.class)
    public void testTimeAddedCannotBeNull() {
        remark.setTimeAdded(null);

        persistFlushAndClear(remark);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testRemarkCannotBeNull() {
        remark.setRemark(null);

        persistFlushAndClear(remark);
    }
}
