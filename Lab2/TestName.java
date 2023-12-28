/**
 * TestName.java
 *
 * @author Joel Swanson
 * @version 01.16.2014
 */

import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

/**
 * Describe TestName here.
 */
public class TestName
{   
    private PrintStream terminal;
    private ByteArrayOutputStream output;
    private String studentData;
    private String[] result;    
    private TestAddress ta;
    private int grade;
    
    /**
     * No-arg constructor for test class TestName.
     */
    public TestName()
    {
    }

    /**
     * Accessor for grade field.  
     */
    public int getGrade()
    {
        return grade;
    }    
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    public void setUp()
    {
        //terminal now prints to the Terminal Window like this
        //      terminal.println("Hello");
        terminal = System.out;

        //Set up System to print to a byte array
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        //Run the students main and capture the results
        //STUDENT: If you are reading this because the following
        //line caused a compile error then you did not 
        //create a proper main in your class.        
        FunWithNames.main(null);
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
     * Test FunWithNames class.
     */
    @Test
    public void testFunWithNames()
    {
        //Test all previous activies first.
        testPrevious();
        
        //Run all tests for this activity.  
        testSilent();
        
        //All tests passed.  Print the grade report.
        printGrade(6);
    }
    
    /**
     * Run all tests for this class.  The silent means
     * no output to the terminal.  This method will be 
     * called from later activities so that testing from
     * later activies doesnt print multiple grade reports.
     */
    public void testSilent()
    {
        //Print a blank line to separate previous test output from
        //from this test output.
        System.out.println();
        setUp(); 
               
        String data1 = "Name: John Clarence Doe\n";
        data1 += "Number of characters in full name: 15\n";
        data1 += "Login id: doejc";

        String data2 = "Name: Donald Richard Dirka\n";
        data2 += "Number of characters in full name: 18\n";
        data2 += "Login id: dirkadr";

        if (!studentData.trim().equals(data1) 
            && !studentData.trim().equals(data2))
        {
            String fb = "";
            fb += "TestName failed.\n";
            fb += "Your output should look EXACTLY like one of\n";
            fb += "the example outputs in the lab document.\n\n";
            fb += data1 +"\n\n\t\tOR\n\n" + data2 + "\n";
            fb += "Make sure all spacing is correct.\n";
            fb += "Make sure all words are spelled correctly.\n";
            printGrade(0);
            fail(fb);
        }          
    }

    /**
     * Run previous tests; TestAddress.
     */
    public void testPrevious()
    {
        ta = new TestAddress();
        ta.testSilent();
        
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
        report += "TestAddress.. 4 of 4\n";
        report += "TestName..... " + grade + " of 6\n";
        report += "TestSales.... 0 of 15\n";
        report += "TestNameV2... 0 of 15\n";
        report += "TotalGrade... " + (grade + 4) + " of 40\n";
        report += "\nNo test will be graded until the previous tests are passed.\n";        
        report += "For example, TestName grade will be 0 until TestAddress\n";
        report += "passes all tests.\n";        
                
        return report;
    }   
    
}
