
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the printSavings method described in activity 2.
 * 
 * Uses Dr. Fenwick's method for testing with System.in and System.out.
 * 
 * @author Julia Dana
 * @version 02.24.2014
 */
public class Activity2
{
    private static final double EPSILON = 0.001;

    ISP isp;

    private PrintStream oldSystemOut;
    private ByteArrayOutputStream outContent;

    /**
     * Sets up the test fixture.
     * 
     * Called before every test case method. Sets up new I/O streams. Creates
     * test object.
     */
    @Before
    public void setUp()
    {
        isp = new ISP();

        oldSystemOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Resets I/O streams.
     */
    @After
    public void tearDown()
    {
        System.setOut(oldSystemOut);
    }

    /**
     * Tests the output of printSavings with package 
     * A and 21 hours used. (Savings on both packages)
     */
    @Test
    public void testSavingsA1()
    {
        isp.setPkg('A');

        isp.setHoursUsed(21);
        isp.printSavings();

        String printSavingsOutput = outContent.toString();

        if (printSavingsOutput.trim().equals(""))
        {
            String msg = "";
            msg = "Failure in printSavings for package " + isp.getPkg() + ".\n";
            msg+= "Your printSavings method did not print anything.\n";
            msg+= "Savings messages must be printed in printSavings and NOT in main.\n";
            fail(msg);
        }
        
        String expectedPattern = 
            "You would have saved $16.00 by choosing package B";
            
        assertTrue("Failed calculating savings for package 'A' with 21"
                + " hours used.\n"
                + "\tExpected: " + expectedPattern + " \n"
                + "\tGot: " + printSavingsOutput,
            Pattern.compile(Pattern.quote(expectedPattern))
                .matcher(printSavingsOutput).find());
        
        expectedPattern = 
            "You would have saved $12.00 by choosing package C";
        assertTrue("Failed calculating savings for package 'A' with 21"
                + " hours used.\n"
                + "\tExpected: " + expectedPattern + " \n"
                + "\tGot: " + printSavingsOutput,
            Pattern.compile(Pattern.quote(expectedPattern))
                .matcher(printSavingsOutput).find());
    }

    /**
     * Tests the output of printSavings with package 
     * A and 13 hours used. (Savings on package B only)
     */
    @Test
    public void testSavingsA2()
    {
        isp.setPkg('A');

        isp.setHoursUsed(13);
        isp.printSavings();

        String printSavingsOutput = outContent.toString();

        if (printSavingsOutput.trim().equals(""))
        {
            String msg = "";
            msg = "Failure in printSavings for package " + isp.getPkg() + ".\n";
            msg+= "Your printSavings method did not print anything.\n";
            msg+= "Savings messages must be printed in printSavings and NOT in main.\n";
            fail(msg);
        }

        String expectedPattern = 
            "You would have saved $1.00 by choosing package B";
        assertTrue("Failed calculating savings for package 'A' with 13"
                + " hours used.\n"
                + "\tExpected: " + expectedPattern + " \n"
                + "\tGot: " + printSavingsOutput,
            Pattern.compile(Pattern.quote(expectedPattern))
                .matcher(printSavingsOutput).find());

        assertFalse("Failed calculating savings for package 'A' with 13"
            + "hours. There should not be saving for package C.",
            Pattern.compile("C").matcher(outContent.toString()).find());
    }

    /**
     * Tests the output of printSavings with package 
     * A and 11 hours used. (No savings)
     */
    @Test
    public void testSavingsA3()
    {
        isp.setPkg('A');

        isp.setHoursUsed(11);
        isp.printSavings();

        String printSavingsOutput = outContent.toString();

        assertFalse("Failed calculating savings for package 'A' with 11"
            + "hours. There should not be any savings.",
            Pattern.compile("You would saved")
                .matcher(outContent.toString()).find());
    }

    /**
     * Tests the output of printSavings with package 
     * B and 15 hours used. (No savings)
     */
    @Test
    public void testSavingsB1()
    {
        isp.setPkg('B');

        isp.setHoursUsed(15);
        isp.printSavings();

        String printSavingsOutput = outContent.toString();

        assertFalse("Failed calculating savings for package 'B' with 15"
            + "hours. There should not be any savings.",
            Pattern.compile("You would saved")
                .matcher(outContent.toString()).find());
    }

    /**
     * Tests the output of printSavings with package 
     * B and 25 hours used. (No savings)
     */
    @Test
    public void testSavingsB2()
    {
        isp.setPkg('B');

        isp.setHoursUsed(25);
        isp.printSavings();

        String printSavingsOutput = outContent.toString();

        assertFalse("Failed calculating savings for package 'B' with 25"
            + "hours. There should not be any savings.",
            Pattern.compile("You would saved")
                .matcher(outContent.toString()).find());
    }

    /**
     * Tests the output of printSavings with package 
     * B and 30 hours used. (Savings on C only.)
     */
    @Test
    public void testSavingsB3()
    {
        isp.setPkg('B');

        isp.setHoursUsed(30);
        isp.printSavings();

        String printSavingsOutput = outContent.toString();
        
        if (printSavingsOutput.trim().equals(""))
        {
            String msg = "";
            msg = "Failure in printSavings for package " + isp.getPkg() + ".\n";
            msg+= "Your printSavings method did not print anything.\n";
            msg+= "Savings messages must be printed in printSavings and NOT in main.\n";
            fail(msg);
        }

        String expectedPattern = 
            "You would have saved $5.00 by choosing package C";
        assertTrue("Failed calculating savings for package 'B' with 30"
                + " hours used.\n"
                + "\tExpected: " + expectedPattern + " \n"
                + "\tGot: " + printSavingsOutput,
            Pattern.compile(Pattern.quote(expectedPattern))
                .matcher(printSavingsOutput).find());
        
        assertFalse("Failed calculating savings for package 'B' with 30"
            + "hours used. Should not report savings compared to "
            + "package A.",
            Pattern.compile("A").matcher(outContent.toString()).find());
    }

    /**
     * Tests the output of printSavings with package 
     * C and 5 hours used. (No savings printed)
     */
    @Test
    public void testSavingsC1()
    {
        isp.setPkg('C');

        isp.setHoursUsed(5);
        isp.printSavings();

        String printSavingsOutput = outContent.toString();

        assertFalse("Failed calculating savings for package 'C' with 5"
            + "hours. There should not be any savings reported.",
            Pattern.compile("You would saved")
                .matcher(outContent.toString()).find());
    }

    /**
     * Tests the output of printSavings with package 
     * C and 30 hours used. (No savings)
     */
    @Test
    public void testSavingsC2()
    {
        isp.setPkg('C');

        isp.setHoursUsed(30);
        isp.printSavings();

        String printSavingsOutput = outContent.toString();

        assertFalse("Failed calculating savings for package 'C' with 30"
            + "hours. There should not be any savings.",
            Pattern.compile("You would saved")
                .matcher(outContent.toString()).find());
    }

}
