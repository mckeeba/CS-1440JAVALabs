import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the functionality of Activity 4.
 * 
 * @author Julia
 * @version 2014-03-18
 */
public class Activity2Test1
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
     * Tests the main method that prints tickets to
     * a file. 
     */
    @Test
    public void testMain1()
    {
        String[] filenames = {"myTestFile.txt", 
            "myTestFile.txt", 
            "anotherTestFile.txt"};
        
        int[] numTickets = {3, 8, 4};
        
        for (int i = 0; i < filenames.length; i++)
        {
            String inputString = String.format("%s\n%d\n", 
                filenames[i], numTickets[i]);
            ByteArrayInputStream inContent = new ByteArrayInputStream(
                                                    inputString.getBytes());
            System.setIn(inContent);
            
            try
            {
                PowerBallTicket.main(null);
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
            
            File f = new File(filenames[i]);
            Scanner s;
            try
            {
                s = new Scanner(f);
            }
            catch (FileNotFoundException e)
            {
                fail("Main did not create the file '" + filenames[i] + "'");
                return;
            }
            
            int numLines = 0;
            String line;
            
            while (s.hasNextLine())
            {
                line = s.nextLine();
                numLines++;
                
                assertTrue("Main did not write the ticket in the "
                    + "correct format.\n" 
                    + "Make sure all spacing and commas are correct, and that all numbers" 
                    + " (including the powerball number) display with 2 digits."
                    + "\nYour main wrote:\n"
                    + "\t" + line + "\n"
                    + "On line " + numLines,
                    Pattern.compile("\\d\\d, \\d\\d, \\d\\d, \\d\\d, \\d\\d, "
                        + "Powerball \\d\\d").matcher(line).matches());
                
            }
            
            assertFalse("No tickets written. Did you close the file?",
                0 == numLines);
            
            assertEquals("Main did not write the requested number of tickets.",
                numTickets[i], numLines);
            
            s.close();
        }
    }

}
