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
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Describe TestName here.
 */
public class TestSales
{   
    private PrintStream terminal;
    private ByteArrayOutputStream output;
    private String studentData;
    private String[] result;    
    private TestAddress ta;
    private TestName tn;
    private int grade;
    
    /**
     * No-arg constructor for test class TestName.
     */
    public TestSales()
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
    public void setUp(double testData)
    {
        //terminal now prints to the Terminal Window like this
        //      terminal.println("Hello");
        terminal = System.out;

        //Set up System to print to a byte array
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        
        //Direct a string to System.in so it reads from 
        //the string.
        String data = testData + "\r\n";
        InputStream stdin = System.in;        
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        //Run the students main and capture the results
        //STUDENT: If you are reading this because the following
        //line caused a compile error then you did not 
        //create a proper main in your class.   
        Sales.main(null);
        System.out.flush();

        //Print user output to terminal
        terminal.println(output);
        
        //Restore printing.
        System.setOut(terminal);
        System.setIn(stdin);

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
    public void testSales()
    {
        //Test all previous activies first.
        testPrevious();
        
        //Run all tests for this activity.  
        testSilent();
        
        //All tests passed.  Print the grade report.
        printGrade(15);
    }
    
    
    public void test1()
    {
        //Print a blank line to separate previous test output from
        //from this test output.
        System.out.println("\nRunning TestSales with 32.0 for input");
        System.out.println("Note that you wont see the input" 
            + " data while testing.\n");

        setUp(32.0); 


        //Use two sets of numbers for checking.  One assumes a slight error
        //above the exact answer and one assumes a slight error below exact.
        String[] data1 = new String[5];
        data1[0] = "Amount of Purchase:\t$32.0";
        data1[1] = "State Sales Tax Paid:\t$1.6";
        data1[2] = "County Sales Tax Paid:\t$0.96";
        data1[3] = "Total Sales Tax Paid:\t$2.56";
        data1[4] = "Total Sales Price:\t$34.56";

        String[] data2 = new String[5];
        data2[0] = "Amount of Purchase:\t$32.0";
        data2[1] = "State Sales Tax Paid:\t$1.5";
        data2[2] = "County Sales Tax Paid:\t$0.95";
        data2[3] = "Total Sales Tax Paid:\t$2.55";
        data2[4] = "Total Sales Price:\t$34.55";
         
        testData(data1, data2);
    }
    
    public void test2()
    {
        //Print a blank line to separate previous test output from
        //from this test output.
        System.out.println("\nRunning TestSales with 65.0 for input");
        System.out.println("Note that you wont see the input" 
            + " data while testing.\n");

        setUp(65.0); 

        //Use two sets of numbers for checking.  One assumes a slight error
        //above the exact answer and one assumes a slight error below exact.
        String[] data1 = new String[5];
        data1[0] = "Amount of Purchase:\t$65.0";
        data1[1] = "State Sales Tax Paid:\t$3.25";
        data1[2] = "County Sales Tax Paid:\t$1.95";
        data1[3] = "Total Sales Tax Paid:\t$5.2";
        data1[4] = "Total Sales Price:\t$70.2";

        String[] data2 = new String[5];
        data2[0] = "Amount of Purchase:\t$65.0";
        data2[1] = "State Sales Tax Paid:\t$3.24";
        data2[2] = "County Sales Tax Paid:\t$1.94";
        data2[3] = "Total Sales Tax Paid:\t$5.1";
        data2[4] = "Total Sales Price:\t$70.1";
         
        testData(data1, data2);
    }    
    
    public void testData(String[] data1, String[] data2)
    {
        
        for (int i = 0; i < data1.length; i++)
        {
            //Remove ALL spaces from the test data.
            String test1 = removeAllSpaces(data1[i]);
            String test2 = removeAllSpaces(data2[i]);

            //Remove ALL spaces from the student answer.
            String student = removeAllSpaces(result[i+1]);

            System.out.println("Stdnt:\t" + student);
            System.out.println("test1:\t" + test1);
            System.out.println("test2:\t" + test2);
            
            //Truncate the student answer to the same length as the test data to
            //get rid of any tiny bits of odd rounding that goes on with doubles.
            //Check the students answer against a data point slightly higher than
            //the expected exact answer and sligtly below the exact answer. If neither
            //matches then the line is in error.

            //Different lengths then obvious error
            if (student.length() < test1.length())
            {
                printFail(i+1, data1[i], result[i+1]);                
            }
            else
            {
                //Cut off student answer to same length as correct answer
                //to remove possible rounding errors.
                student = student.substring(0, test1.length());
                if (!test1.equals(student)  && !test2.equals(student))
                {
                    printFail(i+1, data1[i], result[i+1]);
                }
            }
        }
    }
    
    public String removeAllSpaces(String data)
    {
        while (data.contains(" "))
        {
            data = data.replaceAll(" ","");
        }
        return data;
    }
    
    public void printFail(int line, String mine, String yours)
    {
            String fb = "";
            fb += "TestSales failed.\n";
            fb += "Line " + line + " doesn't match the expected output.\n";
            fb += "Yours : " + yours + "\n";
            fb += "Mine  : " + mine + "\n";
            fb += "If the above looks the same, make sure you used a tab (\\t)\n";
            fb += "to align the dollar amounts and not spaces.\n";
            fb += "Make sure all spacing is correct.\n";
            fb += "Make sure all words are spelled correctly.\n";        
            printGrade(0);
            fail(fb);        
        
    }   
    
    /**
     * Run all tests for this class.  The silent means
     * no output to the terminal.  This method will be 
     * called from later activities so that testing from
     * later activies doesnt print multiple grade reports.
     */
    public void testSilent()
    {
        test1();
        test2();
    }

    /**
     * Run previous tests; TestAddress.
     */
    public void testPrevious()
    {
        ta = new TestAddress();
        ta.testSilent();
        
        tn = new TestName();
        tn.testSilent();
        
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
        report += "TestName..... 6 of 6\n";
        report += "TestSales.... " + grade + " of 15\n";
        report += "TestNameV2... 0 of 15\n";
        report += "TotalGrade... " + (grade + 10) + " of 40\n";
        report += "\nNo test will be graded until the previous tests are passed.\n";        
        report += "For example, TestName grade will be 0 until TestAddress\n";
        report += "passes all tests.\n";        
                
        return report;
    }   
    
}
