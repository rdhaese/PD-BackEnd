package be.rdhaese.packetdelivery.back_end.model.comparator;

import be.rdhaese.packetdelivery.back_end.model.Remark;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static junit.framework.TestCase.assertTrue;

/**
 * Created on 3/05/2016.
 *
 * @author Robin D'Haese
 */
public class RemarksOnTimeAddedComparatorTest {

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private RemarksOnTimeAddedComparator comparator = new RemarksOnTimeAddedComparator();
    private Remark remark1;
    private Remark remark2;
    private Remark remark3;

    @Before
    public void setUp() throws ParseException {
        remark1 = new Remark();
        remark1.setTimeAdded(dateFormat.parse("19/02/2016"));

        remark2 = new Remark();
        remark2.setTimeAdded(dateFormat.parse("20/02/2016"));

        remark3 = new Remark();
        remark3.setTimeAdded(dateFormat.parse("19/02/2016"));
    }

    @Test
    public void testCompare(){
        assertTrue(comparator.compare(remark1, remark2) > 0);
        assertTrue(comparator.compare(remark2, remark3) < 0);
        assertTrue(comparator.compare(remark1, remark3) == 0);
    }
}
