/**
 * TestAddress.java
 *
 * @author Joel Swanson
 * @version 01.15.2014
 */

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

/**
 * Describe TestAddress here.
 */
public class TestAddress
{
    private PrintStream terminal;
    private ByteArrayOutputStream output;
    private String studentData;    
    private String[] result;
    public int grade;    
    /**
     * Default constructor for test class TestAddress.
     */
    public TestAddress()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    public void setUp()
    {
        //terminal now prints to the Terminal window like this
        //terminal.println("Hello");
        terminal = System.out;

        //Set up System to print to byte array
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        //Run the students main and capture the results
        //STUDENT: If you are reading this because the following
        //line caused a compile error then you did not 
        //create a proper main in your class.        
        Address.main(null);
        System.out.flush();

        //Print user output to terminal
        terminal.println(output);
        
        //Restore printing.
        System.setOut(terminal);
        
        //Replace all crnl with nl
        //Replace all cr with nl
        //Only nl should remain
        studentData =  output.toString();
        studentData = studentData.replaceAll("\r\n", "\n");
        studentData = studentData.replaceAll("\r", "\n");        

        //Split the lines into an array of strings
        result = studentData.split("\n");

        //Don't penalize for case or extra spaces
        //at the end of each line.  Cut them
        //a little slack on their first program.
        for (int i = 0; i < result.length; i++)
        {
            result[i] = result[i].trim().toUpperCase();
        }        
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    /**
     * Run all tests.
     */
    public void testSilent()
    {
        setUp(); 
        String data1 = "525 Rivers Street\nBoone, NC 28608";
        String data2 = "123 Main Street\nHickory, NC 28601";
        if (!studentData.trim().equals(data1) 
            && !studentData.trim().equals(data2))
        {
            String fb = "";
            fb += "TestAddress failed.\n";
            fb += "Your output should look EXACTLY like the\n";
            fb += "output described from one of the example inputs.\n\n";
            fb += data1 +"\n\n\t\tOR\n\n" + data2 + "\n";
            fb += "Make sure all spacing is correct.\n";
            fb += "Make sure all words are spelled correctly.\n";            
            fail(fb);
        }
    }
    
    /**
     * Make sure the output matches exactly.
     */
    @Test
    public void testData()
    {
        testSilent();
        printGrade(4);
    }
    
    /**
     * Store the grade then print the report.
     */
    private void printGrade(int grade)    
    {
        this.grade = grade;
        System.out.println(gradeReport());
    }
    
    /**
     * Creates a string with the grades for output.
     */
    private String gradeReport()
    {
        String report = "\n____________________________________________\n";
        report += "GRADE:\n";
        report += "TestAddress.. " + grade + " of 4\n";
        report += "TestName..... 0 of 6\n";
        report += "TestSales.... 0 of 15\n";
        report += "TestNameV2... 0 of 15\n";
        report += "TotalGrade... " + grade + " of 40\n";
        report += "\nNo test will be graded until the previous tests are passed.\n";        
        report += "For example, TestName grade will be 0 until TestAddress\n";
        report += "passes all tests.\n";        
                
        return report;
    }        
}
