/**
 * TestVariable.java
 *
 * @author Joel Swanson
 * @version 01/02/14
 */

import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

/**
 * Describe TestVariable here.
 */
public class TestVariable
{
    private PrintStream terminal;
    private ByteArrayOutputStream output;
    private String studentData;    
    private String[] result;
    public int grade;

    /**
     * Default constructor for test class TestVariable.
     */
    public TestVariable()
    {

    }

    /**
     * Accessor for grade field.
     * @return Returns the grade field.
     */
    public int getGrade()
    {
        return grade;
    }

    /**
     * Sets up the test fixture.
     *
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
        Variable.main(null);
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
    //@After
    public void tearDown()
    {
     
    }

    /**
     * Test all data.
     */
    @Test
    public void testVariable()
    {
        testSilent();
        
        //This only prints if all tests pass.
        System.out.println(gradeReport());
    }

    /**
     * Test all data with no end report.  This method
     * can be called from the other methods to test
     * the Variable class and report the grade through
     * the accessor.
     */
    public void testSilent()
    {
        setUp();
        testForAnyOutput();
        testForOneLine();
        testForBothLines();
        testForMoreThanTwoLines();
        grade = 10;        
        tearDown();
    }
    
    /**
     * Make sure they printed something.  
     */
    public void testForAnyOutput()
    {
        String feedback = "TestVariable failed!\n";

        //Fail if they don't print anything.
        //Result.length is always 1 so test for one and
        //that one thing is empty.
        if (result.length == 1 && result[0].equals(""))
        {
            grade = 0;
            feedback += "You didn't print anything.\n";
            feedback += "Your output should look exactly like this:\n\n";
            feedback += "The value is 5\n";
            feedback += "I programmed this!\n\n";
            System.out.println(gradeReport());
            fail(feedback);       
        }
    }

    /**
     * Test to see if only one line was printed, but that one line
     * was correct.
     */
    public void testForOneLine()
    {    
        String feedback = "TestVariable failed!\n";

        if (result.length == 1 && result[0].equals("THE VALUE IS 5"))
        {
            grade = 5;
            feedback += "You only printed \"The value is 5\"\n";
            feedback += "You should have also printed ";
            feedback += "\"I programmed this!\"\n";            
            System.out.println(gradeReport());
            fail(feedback);
        } 
        else if (result.length == 1 && result[0].equals("I PROGRAMMED THIS!"))
        {
            grade = 5;
            feedback += "You only printed \"I programmed this!\"\n";
            feedback += "You must print \"The value is 5\" first.\n";
            System.out.println(gradeReport());
            fail(feedback);
        }
        else if (result.length == 1)
        {
            grade = 0;
            feedback += "You only printed one line and it wasn't correct.\n";
            feedback += "Your output should look exactly like this:\n\n";
            feedback += "The value is 5\n";
            feedback += "I programmed this!\n\n";
            System.out.println(gradeReport());
            fail(feedback);            
        }
    }
    
    /**
     * If two lines were printed, check to see if one or both
     * are correct.  Fail if only one is correct.
     */
    public void testForBothLines()
    {    
        boolean test;
        String feedback = "TestVariable failed!\n";

        if (result.length == 2 && !result[0].equals("THE VALUE IS 5") 
            && result[1].equals("I PROGRAMMED THIS!"))
        {
            grade = 5;
            feedback += "You printed two lines.\n";
            feedback += "The first line is incorrect.\n";
            feedback += "Your output should look exactly like this:\n\n";
            feedback += "The value is 5\n";
            feedback += "I programmed this!\n\n";
            feedback += "Make sure all words are spelled correctly.\n";
            feedback += "Make sure a single space exists between" 
                + " words.\n";           
            System.out.println(gradeReport());
            fail(feedback);

        } 
        else if (result.length == 2 && result[0].equals("THE VALUE IS 5") 
            && !result[1].equals("I PROGRAMMED THIS!"))
        {
            grade = 5;
            feedback += "You printed two lines.\n";
            feedback += "The second line is incorrect.\n";
            feedback += "Your output should look exactly like this:\n\n";
            feedback += "The value is 5\n";
            feedback += "I programmed this!\n\n";
            feedback += "Make sure all words are spelled correctly.\n";
            feedback += "Make sure a single space exists between" 
                + " words.\n";           
            System.out.println(gradeReport());
            fail(feedback);

        }
        else if (result.length == 2 && !result[0].equals("THE VALUE IS 5")
            && !result[1].equals("I PROGRAMMED THIS!"))
        {
            grade = 0;
            feedback += "You printed two lines but both are incorrect.\n";
            feedback += "Your output should look exactly like this:\n\n";
            feedback += "The value is 5\n";
            feedback += "I programmed this!\n\n";
            feedback += "Make sure all words are spelled correctly.\n";
            feedback += "Make sure a single space exists between"   
                + " words.\n";           
            System.out.println(gradeReport());
            fail(feedback);
        }
    }

    /**
     * There should only be two lines printed.  
     * Blank lines at the end are ignored, however
     * if blank lines were printed at the beginning
     * or between the two lines this will catch it.
     */
    public void testForMoreThanTwoLines()
    {   
        String feedback = "TestVariable failed!\n";
        if (result.length > 2 && linesCorrect() == 1)
        {
            grade = 5;
            feedback += "You printed more than two lines.\n";
            feedback += "However, you did print one line correctly.\n";
            feedback += "Automated testing requires that you follow ";
            feedback += "the specification EXACTLY.  \n";
            feedback += "Your output should look exactly like this:\n\n";
            feedback += "The value is 5\n";
            feedback += "I programmed this!\n\n";
            feedback += "Make sure all words are spelled correctly.\n";
            feedback += "Make sure a single space exists between"   
                + " words.\n";     
            feedback += "Do not print any blank lines.\n";     
            System.out.println(gradeReport());
            fail(feedback);
        }
        else if (result.length > 2 && linesCorrect() == 2)
        {
            grade = 7;
            feedback += "You printed more than two lines.\n";
            feedback += "However, you did print the two required lines" 
                + " correctly.\n";
            feedback += "Automated testing requires that you follow ";
            feedback += "the specification EXACTLY.  \n";
            feedback += "Your output should look exactly like this:\n\n";
            feedback += "The value is 5\n";
            feedback += "I programmed this!\n\n";
            feedback += "Make sure all words are spelled correctly.\n";
            feedback += "Make sure a single space exists between"   
                + " words.\n";     
            feedback += "DO NOT print any blank lines.\n";                  
            System.out.println(gradeReport());
            fail(feedback);
        }
    }

    /**
     * If more than three lines exist in the output, determine how
     * many of those lines are correct.
     * @return Returns the number of correct lines.
     */
    public int linesCorrect()
    {
        int value = 0;
        int programmed = 0;
        for (int i = 0; i < result.length; i++)
        {
            if (result[i].toUpperCase().equals("THE VALUE IS 5"))
            {
                value = 1;
            }
            else if (result[i].toUpperCase().equals("I PROGRAMMED THIS!"))
            {
                programmed = 1;
            }
        }
        return value + programmed;
    }
    
    /**
     * Creates a string with the grades for output.
     */
    private String gradeReport()
    {
        String report = "\n____________________________________________\n";
        report += "GRADE:\n";
        report += "TestVariable.... " + grade + " of 10\n";
        report += "TestShape....... 0 of 10\n";
        report += "TestSnowperson.. 0 of 10\n";
        report += "TestPeriodic.... 0 of 10\n";
        report += "TotalGrade...... " + grade + " of 40\n";
        report += "\nNo test will be graded until the previous tests are passed.\n";        
        report += "For example, TestShape will not be graded until TestVariable\n";
        report += "passes with a score of 10.\n";        
                
        return report;
    }    

}

