
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests the main method described in activity 1.
 * 
 * Uses Dr. Fenwick's method for testing with System.in and System.out.
 * 
 * @author Julia Dana
 * @version 02.24.2014
 */
public class Activity1Main
{

    protected PrintStream oldSystemOut;
    protected InputStream oldSystemIn;
    protected ByteArrayOutputStream outContent;
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.  Sets up new I/O streams.
     * Creates test object.
     */
    @Before
    public void setUp()
    {
        oldSystemIn = System.in;
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
        System.setIn(oldSystemIn);
    }
    
    /**
     * Test main method given package A and 9 hours used.
     */
    @Test
    public void testMainA1()
    {
        String inputString = "A\n9\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(
                                                inputString.getBytes());
        System.setIn(inContent);
        
        try
        {
            ISPMain.main(null);
        }
        
        catch (java.util.InputMismatchException e) 
        {
            e.printStackTrace();
            fail("Input did not match what your Scanner expected." 
                + "Read data in order of description in assignment.");
        }
        catch (java.util.NoSuchElementException e) 
        {
            e.printStackTrace();
            fail("Your Scanner expected more input than provided." 
                + "Assignment specified 2 input values.");
        }
        catch (java.lang.IllegalStateException e) 
        {
            e.printStackTrace();
            fail("Your Scanner malfunctioned; did it get closed somehow?");
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail("Your code failed for a reason unknown to the testing "
                + "framework. See error window for execution stack trace.");
        }        

        String mainOutput = outContent.toString();
        
        assertTrue("Main did not print the correct price of $9.95. \n"
            + "for package A with 9 hours. Its output was:\n"
            + mainOutput,
            Pattern.compile("9\\.95").matcher(mainOutput).find());
    }
    
    
    /**
     * Tests the main method with package A and 21 hours used.
     */
    @Test
    public void testMainA2()
    {
        String inputString = "A\n21\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(
                                                inputString.getBytes());
        System.setIn(inContent);
        
        try
        {
            ISPMain.main(null);
        }
        
        catch (java.util.InputMismatchException e) 
        {
            e.printStackTrace();
            fail("Input did not match what your Scanner expected." 
                + "Read data in order of description in assignment.");
        }
        catch (java.util.NoSuchElementException e) 
        {
            e.printStackTrace();
            fail("Your Scanner expected more input than provided." 
                + "Assignment specified 2 input values.");
        }
        catch (java.lang.IllegalStateException e) 
        {
            e.printStackTrace();
            fail("Your Scanner malfunctioned; did it get closed somehow?");
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail("Your code failed for a reason unknown to the testing "
                + "framework. See error window for execution stack trace.");
        }        

        String mainOutput = outContent.toString();
        
        assertTrue("Main did not print the correct price of $31.95. \n"
            + "for package A with 21 hours. Its output was:\n"
            + mainOutput,
            Pattern.compile("31\\.95").matcher(mainOutput).find());
    }
    
    /**
     * Tests the main method with package C.
     */
    @Test
    public void testMainC1()
    {
        String inputString = "C\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(
                                                inputString.getBytes());
        System.setIn(inContent);
        
        try
        {
            ISPMain.main(null);
        }
        
        catch (java.util.InputMismatchException e) 
        {
            e.printStackTrace();
            fail("Input did not match what your Scanner expected." 
                + "Read data in order of description in assignment.");
        }
        catch (java.util.NoSuchElementException e) 
        {
            e.printStackTrace();
            fail("Your Scanner expected more input than provided." 
                + "Assignment specified 1 input value. Are you asking"
                + "for hours used for package C?");
        }
        catch (java.lang.IllegalStateException e) 
        {
            e.printStackTrace();
            fail("Your Scanner malfunctioned; did it get closed somehow?");
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail("Your code failed for a reason unknown to the testing "
                + "framework. See error window for execution stack trace.");
        }        

        String mainOutput = outContent.toString();
        
        assertTrue("Main did not print the correct price of $19.95. \n"
            + "for packageC. Its output was:\n"
            + mainOutput,
            Pattern.compile("19\\.95").matcher(mainOutput).find());
    }


}
