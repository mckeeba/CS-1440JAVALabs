
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Test;


/**
 * Tests that the main method is now printing information
 * about savings.
 * 
 * @author Julia Dana
 * @version 02.24.2014
 */
public class Activity2Main extends Activity1Main
{
    /**
     * Tests that savings message does not print when
     * there would be no savings.
     */
    @Test
    public void testMainA1()
    {
        super.testMainA1();
        assertFalse("Main printed savings information when "
            + "it should not have.",
            Pattern.compile("You would have saved")
                .matcher(outContent.toString()).find());
    }
    
    /**
     * Tests that savings message does print when
     * there are savings.
     */
    @Test
    public void testMainA2()
    {
        super.testMainA2();
        assertTrue("Main did not print savings information "
            + "when it should have.",
            Pattern.compile("You would have saved")
                .matcher(outContent.toString()).find());
    }


}
