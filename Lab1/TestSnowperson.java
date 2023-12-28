/**
 * TestSnowperson.java
 *
 * @author Joel Swanson
 * @version 01/02/2014
 */

import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.util.HashSet;

/**
 * Describe TestSnowperson here.
 */
public class TestSnowperson
{
    private PrintStream terminal;
    private ByteArrayOutputStream output;
    private String studentData;
    private String[] result;
    
    private TestVariable tv;
    private TestShape ts;
    private int grade;
    
    /**
     * Default constructor for test class TestSnowperson.
     */
    public TestSnowperson()
    {
    }

    /**
     * Accessor for grade.
     * @return Returns the students grade for this test.
     */
    public int getGrade()
    {
        return 0;
    }
    
    /**
     * Sets up the test fixture.
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
        Snowperson.main(null);
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
     * Run all tests.
     */
    @Test
    public void testSnowperson()
    {
        testPrevious();
        testSilent();
       
        //This only prints if all tests pass.
        System.out.println(gradeReport());
        
    }
    
    public void testSilent()
    {
        setUp();
        testNumberOfCharacters();
        testNumberOfRows();
        testWidestRow();
        grade = 10;
        
    }
    
    /**
     * Run previous tests; TestVariable, TestShape.
     */
    public void testPrevious()
    {
        tv = new TestVariable();
        tv.testSilent();

        ts = new TestShape();
        ts.testSilent();
        
    }    
    /**
     * Make sure the student used at least eight different
     * characters.
     */
    public void testNumberOfCharacters()
    {
        //Snow person complexity.
        //Should use at least 8 different characters.

        HashSet<Character> chars = new HashSet<Character>();
        String feedback = "TestSnowperson failed!\n";

        //Push all characters into a hash set to remove duplicates.
        for (int i = 0; i < result.length; i++)
        {
            for (int j = 0; j < result[i].length(); j++)
            {
                chars.add(result[i].charAt(j));
            }
        }

        if (chars.size() < 8)
        {

            feedback += "Not enough unique characters in your snow person.\n";
            feedback += "You must have at least 8 unique characters.\n";
            feedback += "You only have " + chars.size() + ".\n";
            feedback += "You will receive a zero if your picture does\n";
            feedback += "not look like a snow person when printed.\n";
            System.out.println(gradeReport());
            grade = 0;
            fail(feedback);            
        }
    }

    /**
     * Make sure there are at least 15 non-blank rows.
     */
    public void testNumberOfRows()
    {
        //Snow person complexity.
        //Should use at least 15 non-blank rows.

        String feedback = "TestSnowperson failed!\n";
        int count = 0;

        //Count the non-blank rows.
        for (String row : result)
        {
            if (!row.trim().equals(""))
            {
                count++;
            }
        }

        if (count < 15)
        {
            feedback += "Not enough non-blank rows in your snow person.\n";
            feedback += "You must have at least 15 non-blank rows.\n";
            feedback += "You only have " + count + ".\n";
            feedback += "You will receive a zero if your picture does\n";
            feedback += "not look like a snow person when printed.\n";
            System.out.println(gradeReport());
            grade = 0;
            fail(feedback);            
        }        
    }    

    
    
    /**
     * Test to make sure widest row in piture is at least
     * 20 characters wide.  This does not count spaces
     * at the beginning or end of the row.
     */
    public void testWidestRow()
    {
        //Snow person complexity.
        //Should have a widest line of 20 characters.
        //This does NOT count spaces at beginning or end of line.

        String feedback = "TestSnowperson failed!\n";
        int widest = 0;

        //Count the non-blank rows.
        for (String row : result)
        {
            if (row.trim().length() > widest)
            {
                widest = row.trim().length();
            }
        }

        if (widest < 19)
        {
            feedback += "Your snow person is not wide enough.\n";
            feedback += "The width must be 19 characters ";
            feedback += "NOT counting spaces on left or right side.\n";
            feedback += "Your widest row is " + widest + ".\n";
            feedback += "You will receive a zero if your picture does\n";
            feedback += "not look like a snow person when printed.\n";
            System.out.println(gradeReport());
            grade = 0;
            fail(feedback);            
        }        
    }  
    
    /**
     * Creates a string with the grades for output.
     */
    private String gradeReport()
    {      
        String report = "\n____________________________________________\n";
        report += "GRADE:\n";
        report += "TestVariable.... 10 of 10\n";
        report += "TestShape....... 10 of 10\n";
        report += "TestSnowperson.. " + grade + " of 10\n";
        report += "TestPeriodic.... 0 of 10\n";
        report += "TotalGrade...... " + (grade + 20) + " of 40\n";
        report += "\nNo test will be graded until the previous tests are passed.\n";        
        report += "For example, TestShape will not be graded until TestVariable\n";
        report += "passes with a score of 10.\n";       
                
        return report;        
    }    
    
}

