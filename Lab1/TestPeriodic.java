/**
 * TestPeriodic.java
 *
 * @author (Your name here)
 * @version (Date or version number)
 */

import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

/**
 * Describe TestPeriodic here.
 */
public class TestPeriodic
{
    private PrintStream terminal;
    private ByteArrayOutputStream output;
    private String studentData;
    private String[] result;    

    private TestVariable tv;
    private TestShape ts;
    private TestSnowperson tsp;
    private int grade;    
    /**
     * Default constructor for test class TestPeriodic.
     */
    public TestPeriodic()
    {
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

        try
        {
            //Make sure new PrintStream can handle unicode
            System.setOut(new PrintStream(output, true, "UTF-8"));

            //Make sure the terminal window can handle unicode
            terminal = new PrintStream(terminal, true, "UTF-8");
        }
        catch (Exception e)
        {
            terminal.println("Error setting up terminal");
        }
        
        //Run the students main and capture the results
        PeriodicTable.main(null);
        System.out.flush();

        //Restore printing.
        System.setOut(terminal);          
        
        //Convert printed output to a big UTF-8 string which will
        //allow unicode characters.
        try
        {
            studentData =  output.toString("UTF-8"); 
        }    
        catch (Exception e)
        {
            terminal.println("Error converting output stream to string");
        }           

        //Replace all crnl with nl
        //Replace all cr with nl
        //Only nl should remain, seemed easiest.        
        studentData = studentData.replaceAll("\r\n", "\n");
        studentData = studentData.replaceAll("\r", "\n");        
        
        //Print user output to terminal
        terminal.println(studentData);

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
     * Test all parts of the students periodic table.
     */
    @Test
    public void testPeriodic()
    {
        testPrevious();
        testSilent();
        grade = 10;
        System.out.println(gradeReport());
    }
    
    public void testSilent()
    {
        setUp();
        checkTotalHeight();
        testWidthRange();
        testTopAndBottom();
        testElements();       
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
        
        tsp = new TestSnowperson();
        tsp.testSilent();        
    }       
    
    /**
     * Test to make sure there are exacly 16 rows in the output.
     */
    public void checkTotalHeight()
    {
        String feedback = "TestPeriodic failed!\n";
        int count = 0;
                
        if (result.length != 16)
        {
            feedback += "Each element should have a four rows\n";
            feedback += "for a total of 16 rows.  There should\n";
            feedback += "be no extra spacing between rows.\n\n";
            feedback += "\tRow 1: Top border\n";
            feedback += "\tRow 2: Atomic number\n";
            feedback += "\tRow 3: Chemical symbol\n";
            feedback += "\tRow 4: Bottom border\n\n";
            feedback += "Your output incorrectly has " 
                + result.length + " rows.\n";
            System.out.println(gradeReport());
            fail(feedback);    
        }
    }
       
    /**
     * Trying not to force an exact copy.  Assume they may put an extra space
     * in somewhere and work around that as long as everything is aligned.  
     * This method also makes sure they didn't put any blank lines in anywhere.
     */
    public void testWidthRange()
    {
        String feedback = "TestPeriodic failed!\n";
        
        for (int i = 0; i < result.length; i++)
        {
            if (result[i].length() == 0)
            {
                feedback += "Row " + (i + 1) + " is blank.\n";
                feedback += "You should not have blank rows.\n";
                System.out.println(gradeReport());
                fail(feedback);                    
            }
            else if (result[i].length() < 4)
            {
                feedback += "You have too few characters on one of"
                    + " your rows.\n";
                feedback += "All rows should be from 4 to 7 characters.\n";
                feedback += "Row " + (i + 1) + " is " + result[i].length() 
                    + " characters.\n";
                    System.out.println(gradeReport());
                fail(feedback);    
            } 
            else if (result[i].length() > 7)
            {
                feedback += "You have too many characters on one of"
                    + " your rows.\n";
                feedback += "All rows should be from 4 to 7 characters.\n";
                feedback += "Row " + (i + 1) + " has " + result[i].length() 
                    + " characters.\n";
                    System.out.println(gradeReport());
                fail(feedback);    
            }            
        }
    }   
    
    /**
     * Check that the top of each box is exactly the same as the others.  
     * Check that the bottom of each box is exactly the same as the others.
     */
    public void testTopAndBottom()
    {
        String feedback = "TestPeriodic failed!\n";
        
        //All tops should be the same
        if (result.length != 16)
        {
            feedback += "You have too many rows.\n";
            feedback += "I can't tell where top and bottom of ";
            feedback += "each element is located.\n";
            System.out.println(gradeReport());
            fail(feedback);              
        } 
        else 
        {
            checkTopMatch();
        }
    }
    
    
    /**
     * Make sure the top row of all boxes match exactly.
     */
    public void checkTopMatch()
    {
        String feedback = "TestPeriodic failed!\n";
        
        if (!result[0].equals(result[4]))
        {
            feedback += "Top row of first box does not exactly match\n";
            feedback += "the top row of the second box.\n";
            System.out.println(gradeReport());
            fail(feedback);    
        }
        else if (!result[0].equals(result[8]))
        {
            feedback += "Top row of first box does not exactly match\n";
            feedback += "the top row of the third box.\n";
            System.out.println(gradeReport());
            fail(feedback);    
        }
        else if (!result[0].equals(result[12]))
        {
            feedback += "Top row of first box does not exactly match\n";
            feedback += "the top row of the fourth box.\n";
            System.out.println(gradeReport());
            fail(feedback);    
        }
        else
        {
            checkBottomMatch();
        }          
    }

    /**
     * Make sure the bottom row of all boxes match exactly.
     */    
    public void checkBottomMatch()
    {
        String feedback = "TestPeriodic failed!\n";
        
        if (!result[3].equals(result[7]))
        {
            feedback += "Bottom row of first box does not exactly match\n";
            feedback += "the bottom row of the second box.\n";
            System.out.println(gradeReport());
            fail(feedback);    
        }
        else if (!result[3].equals(result[11]))
        {
            feedback += "Bottom row of first box does not exactly match\n";
            feedback += "the bottom row of the third box.\n";
            System.out.println(gradeReport());
            fail(feedback);    
        }
        else if (!result[3].equals(result[15]))
        {
            feedback += "Bottom row of first box does not exactly match\n";
            feedback += "the bottom row of the fourth box.\n";
            System.out.println(gradeReport());
            fail(feedback);    
        }
        else if (result[0].length() != result[3].length())
        {
            feedback += "Top row and bottom row of each box should be the ";
            feedback += "same width.\n";
            feedback += "Your top rows are " + result[0].length() 
                + " and your bottom rows are " + result[3].length() + ".\n";
            
            System.out.println(gradeReport());
            fail(feedback);        
        }
    }
    
    /**
     * Test each element for various errors. 
     * 1. Make sure the data rows match in size.  
     *      Data rows are the rows with either an atomic 
     *      number or a chemical symbol.
     * 2. Make sure the correct data item is on the correct
     *      row. 
     * 3. Make sure all data items are aligned.  That is make
     *      sure they start on the same column.
     */
    public void testElements()
    {
        String feedback = "TestPeriodic failed!\n";
                
        if (result.length != 16)
        {
            feedback += "You have too many rows.\n";
            feedback += "I can't tell where top and bottom of\n";
            feedback += "each element's box is located.\n";
            feedback += "Correct that error first.";
            System.out.println(gradeReport());
            fail(feedback);              
        } 

        //Test data rows for same length.  Actually make sure all
        //match the top data row.
        else if (result[1].length() != result[2].length()) 
        {
            failRowMessage(2);
        }
        else if (result[1].length() != result[5].length()) 
        {
            failRowMessage(5);
        }
        else if (result[1].length() != result[6].length()) 
        {
            failRowMessage(6);
        }
        else if (result[1].length() != result[9].length()) 
        {
            failRowMessage(9);
        }
        else if (result[1].length() != result[10].length()) 
        {
            failRowMessage(10);
        }
        else if (result[1].length() != result[13].length()) 
        {
            failRowMessage(11);
        }
        else if (result[1].length() != result[14].length()) 
        {
            failRowMessage(12);
        }

        //All box tops and bottoms match, test that each row has
        //the correct symbol.
        else 
        {
            testDataValid();
        }   
       
    }
    
    /**
     * Test to see that each row has the correct symbol.
     */
    public void testDataValid()
    {
        if (!result[1].contains(" 1 ")) 
        {
            failValidMessage("1", 1);
        }
        else if (!result[2].contains(" H ")) 
        {
            failValidMessage("H", 2);
        }
        else if (!result[5].contains(" 3 ")) 
        {
            failValidMessage("3", 5);
        }
        else if (!result[6].contains(" Li ")) 
        {
            failValidMessage("Li", 6);
        }
        else if (!result[9].contains(" 11 ")) 
        {
            failValidMessage("11", 9);
        }
        else if (!result[10].contains(" Na ")) 
        {
            failValidMessage("Na", 10);
        }
        else if (!result[13].contains(" 19 ")) 
        {
            failValidMessage("19", 13);
        }
        else if (!result[14].contains(" K ")) 
        {
            failValidMessage("K", 14);
        }
        else
        {
            testAlign();
        }
    }
    
    /**
     * Prints a specific message telling the user exactly which
     * row they failed on if they entered invalid data on their
     * periodic table.
     * @param symbol The symbol the student messed up.
     * @param row The row the student messed up.
     */
    
    public void failValidMessage(String symbol, int row)
    {
        String feedback = "TestPeriodic failed!\n";
        feedback += "You have an incorrect atomic number or chemical symbol\n";
        feedback += "in row " + (row + 1) + ".  It could be a capitalization"
            + " or spacing error.\n"; 
        feedback += "The symbol should look like this: " 
            + symbol + "\n";        
        feedback += "and should have at least one space before"
            + " and after.\n";        
        System.out.println(gradeReport());
        fail(feedback);         
    }
    
    /**
     * Check to see that all lines with atomic numbers and 
     * chemical symbols are the same width and make sure all
     * atomic numbers and chemical symbols start on the same
     * column.
     */
    public void testAlign()
    {
        AlignChecker ac = new AlignChecker();
        ac.checkDataAlign();
       
    }
    
    /**
     * Inner class for checking the alignment of the data
     * items in the periodic table.  All atomic numbers
     * and chemical symbols should start on the same
     * column.
     */
    public class AlignChecker
    {
        private int startPosition = 0;
        private boolean aligned = false;

        /**
         * Check the alignment of the data items.  Assume the top
         * data row is correct and compare all others to it.
         */
        public void checkDataAlign()
        {
            String feedback = "TestPeriodic failed!\n";
            
            //Set alignment from first data row.
            startPosition = result[1].toUpperCase().indexOf("1");
            
            if (startPosition < 2)
            {
                feedback += "Row 1 does not contain the proper atomic" 
                    + " number.\n";
                System.out.println(gradeReport());
                fail(feedback);                 
            }
            else 
            {
                if (result[2].toUpperCase().indexOf("H") != startPosition)
                {
                    alignErrorMessage(2);
                }
                else if (result[5].toUpperCase().indexOf("3") != startPosition)
                {
                    alignErrorMessage(5);
                }
                else if (result[6].toUpperCase().indexOf("LI") != startPosition)
                {
                    alignErrorMessage(6);
                }
                else if (result[9].toUpperCase().indexOf("11") != startPosition)
                {
                    alignErrorMessage(9);
                }
                else if (result[10].toUpperCase().indexOf("NA") 
                    != startPosition)
                {
                    alignErrorMessage(10);
                }
                else if (result[13].toUpperCase().indexOf("19") 
                    != startPosition)
                {
                    alignErrorMessage(13);
                }
                else if (result[14].toUpperCase().indexOf("K") 
                    != startPosition)
                {
                    alignErrorMessage(14);
                }
            }
        }

        /**
         * Print a message informing the user on what row the align error
         * occured.
         * @param row The row on which the error occured.
         */
        public void alignErrorMessage(int row)
        {
            String feedback = "TestPeriodic failed!\n";
            feedback += "All atomic weights and chemical symbols must ";
            feedback += "be aligned vertically.\n";  
            feedback += "That is, they must all begin on the same colum.\n";
            feedback += "The item on row " + (row + 1)
                + " is not aligned with the item on row 2.\n";
            System.out.println(gradeReport());
            fail(feedback);            
        }        
    }

    
    /**
     * Print an error message indicating that the
     * given data row is not the same witdth as the top
     * data row.
     * @param row The row that does not match the top data row.
     */
    public void failRowMessage(int row)
    {
        String feedback = "TestPeriodic failed!\n";
        feedback += "The width of row 2 and the width of\n";
        feedback += "row " + (row + 1) + " should match but they don't.\n";
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
        report += "TestShape....... 10 of 10\n";
        report += "TestSnowperson.. 10 of 10\n";
        report += "TestPeriodic.... " + grade + " of 10\n";
        report += "TotalGrade...... " + (grade + 30) + " of 40\n";
        report += "\nNo test will be graded until the previous tests are passed.\n";        
        report += "For example, TestShape will not be graded until TestVariable\n";
        report += "passes with a score of 10.\n"; 
        return report;        
    }        

}

