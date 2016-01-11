package be.rdhaese.packetdelivery;

import org.junit.Test;
import org.springframework.test.context.TestExecutionListeners;

import static junit.framework.Assert.assertTrue;

/**
 * Created on 11/01/2016.
 *
 * @author Robin D'Haese
 */
public class FailingTest {

    @Test
    public void failingTest(){
        assertTrue(false);
    }
}
