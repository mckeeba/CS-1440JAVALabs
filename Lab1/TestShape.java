/**
 * TestShape.java
 *
 * @author (Your name here)
 * @version (Date or version number)
 */

import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

/**
 * Describe TestShape here.
 */
public class TestShape
{    
    private PrintStream terminal;
    private ByteArrayOutputStream output;
    private String studentData;
    private String[] result;    
    private TestVariable tv;
    private int grade;

    /**
     * Default constructor for test class TestShape.
     */
    public TestShape()
    {
    }

    /**
     * Sum the various grades and return.  
     */
    public int getGrade()
    {
        return grade;
    }
    
    @BeforeClass
    public static void oneTimeSetUp()
    {
    }
    
    /**
     * Sets up the test fixture.
     *
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
        Shape.main(null);
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
     */
    public void tearDown()
    {
    }

    /**
     * Test all of the shape parametes.
     */
    @Test
    public void testShape()
    {
        testPrevious();
        testSilent();
       
        //This only prints if all tests pass.
        System.out.println(gradeReport());
    }
    
    /**
     * Run all tests for the Shape class.
     */
    public void testSilent()
    {
        setUp(); 
        testForNoOutput();
        testNumberOfLines();
        testEachLine();
        grade = 10;
    }
    
    /**
     * Run previous tests; TestVariable.
     */
    public void testPrevious()
    {
        tv = new TestVariable();
        tv.testSilent();
        
    }
    
    
    /**
     * Fail if the student doesn't print anything.
     */
    public void testForNoOutput()
    {
        String feedback = "TestShape failed!\n";

        //Fail if they don't print anything.
        if (result.length == 1 && result[0].equals(""))
        {
            grade= 0;
            feedback += "You didn't print anything.\n";
            feedback += "You MUST print the pattern for this activity.\n";
            System.out.println(gradeReport());
            fail(feedback);       
        }

    }

    /**
     * Make sure the student shape has exactly as many lines as the
     * given shape.  Blank lines at the end will be ignored.
     */
   
    public void testNumberOfLines()
    {
        final int NUMBER_OF_ROWS = 7;
        String feedback = "TestShape failed!\n";
        //Fail if they don't print anything.
        if (result.length == 1 && !result[0].equals(""))
        {
            grade= 0;
            feedback += "The shape given has seven rows of asterisks.\n";
            feedback += "Your shape has 1 row.\n";
            System.out.println(gradeReport());
            fail(feedback);       
        } 
        else if (result.length > 1 && result.length < NUMBER_OF_ROWS)
        {
            grade= 0;
            feedback += "The shape given has seven rows of asterisks.\n";
            feedback += "Your shape has fewer than seven rows.\n";
            feedback += "Your shape has " + result.length + " rows.\n";
            feedback += gradeReport();
            fail(feedback);       
        }
        else if (result.length > NUMBER_OF_ROWS)
        {
            grade= 0;
            feedback += "The shape given has seven rows of asterisks.\n";
            feedback += "Your shape has more than seven rows.\n";
            feedback += "Your shape has " + result.length + " rows.\n";
            System.out.println(gradeReport());
            fail(feedback);       
        }      
    }
    
    /**
     * Make sure each row looks exactly like the row in the 
     * picture.
     */
    public void testEachLine()
    {
        final int NUMBER_OF_ROWS = 7;
        String feedback = "TestShape failed!\n";
        if (result.length == NUMBER_OF_ROWS 
            && !result[3].equals("*******"))
        {
            for (int i = 0; i < result[3].length(); i++)
            {
                int b = (int) (result[3].charAt(i));
            }

            //Check widest line.           
            grade= 5;
            feedback += "Line four should have seven asterisks\n";
            feedback += "with no extra spaces before or after.\n";
            feedback += "The first asterisk should be all the\n";
            feedback += "way against the left side of the terminal\n";
            feedback += "window.\n";
            System.out.println(gradeReport());
            fail(feedback);                   
        }
        else if (result.length == NUMBER_OF_ROWS && numberOfAsterisks() != 25)
        {            
            grade= 5;
            feedback += "You dont have the correct number of asterisks.\n";
            feedback += "The picture given contains 25 asterisks.\n";
            feedback += "Your picture contains " + numberOfAsterisks() 
                + " .\n";
            System.out.println(gradeReport());
            fail(feedback);            
        }
        else if (result.length == NUMBER_OF_ROWS)
        {
            grade = 5;
            if (!result[0].equals("   *")) 
            {
                rowFailMessage(0);
            }
            else if (!result[1].equals("  ***")) 
            {
                rowFailMessage(1);
            }
            else if (!result[2].equals(" *****")) 
            {
                rowFailMessage(2);
            }
            else if (!result[4].equals(" *****")) 
            {
                rowFailMessage(4);
            }
            else if (!result[5].equals("  ***")) 
            {
                rowFailMessage(5);
            }
            else if (!result[6].equals("   *")) 
            {
                rowFailMessage(6);
            }
        }
    }

    /**
     * Check that every row in the shape contains at least one 
     * asterisk
     */
    public int numberOfAsterisks()
    {
        int count = 0;
        for (int i = 0; i < result.length; i++)
        {
            for (int j = 0; j < result[i].length();j++)
            {
                if(result[i].charAt(j) == '*')
                {
                    count++;
                }
            }
        }
        return count;
    }
    
    /**
     * Print a message indicating which row is incorrect if the
     * row is not exactly like the picture.
     * @param row The row that failed.
     */
    public void rowFailMessage(int row)
    {
        String feedback = "TestShape failed!\n";
        feedback += "Line " + (row + 1) + " looks incorrect.\n";
        feedback += "Make sure you have the correct number of asterisks" 
            + " on that line.\n"; 
        feedback += "Make sure you have the correct number of spaces" 
            + " before the asterisk(s).\n";
        feedback += "Make sure you don't have any spaces after the" 
            + " asterisk(s).\n";
        System.out.println(gradeReport());
        fail(feedback);
    }

    /**
     * Creates a string with the grades for output.
     */
    private String gradeReport()
    {      
        String report = "\n____________________________________________\n";
        report += "GRADE:\n";
        report += "TestVariable.... 10 of 10\n";
        report += "TestShape....... " + grade + " of 10\n";
        report += "TestSnowperson.. 0 of 10\n";
        report += "TestPeriodic.... 0 of 10\n";
        report += "TotalGrade...... " + (grade + 10) + " of 40\n";
        report += "\nNo test will be graded until the previous tests are passed.\n";        
        report += "For example, TestShape will not be graded until TestVariable\n";
        report += "passes with a score of 10.\n"; 
                
        return report;        
    }
    
    
}

