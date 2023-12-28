/**
 * TestNameV2.java
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
 * Describe TestNameV2 here.
 */
public class TestNameV2
{   
    private PrintStream terminal;
    private ByteArrayOutputStream output;
    private String studentData;
    private String[] result;    
    private TestAddress ta;
    private TestName tn;
    private TestSales ts;
    private int grade;
    
    /**
     * No-arg constructor for test class TestName.
     */
    public TestNameV2()
    {
    }

    /**
     * Accessor for grade field.  
     */
    public int getGrade()
    {
        return grade;
    }    
    public byte[] buildData(String name)
    {
        String[] names = name.split(" ");
        int size = names.length * 8192;
        byte[] data = new byte[size];
        
        for (int i = 0; i < names.length; i++)
        {
            String n = names[i];
            int start = i * 8192;
            int j = 0;
            for (j = 0; j < n.length(); j++)
            {
                data[start + j] = (byte)(n.charAt(j));
            }    
            data[start + j++] = '\n';
            data[start + j++] = '\r';
        }
        
        return data;
    }
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    public void setUp(String testData)
    {
        //terminal now prints to the Terminal Window like this
        //      terminal.println("Hello");
        terminal = System.out;

        //Set up System to print to a byte array
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        
        //Direct a string to System.in so it reads from 
        //the string.
        String data = testData;
        byte[] newData = buildData(testData);
        
        InputStream stdin = System.in;        
        //System.setIn(new ByteArrayInputStream(data.getBytes()));
        System.setIn(new ByteArrayInputStream(newData));
        
        //Run the students main and capture the results
        //STUDENT: If you are reading this because the following
        //line caused a compile error then you did not 
        //create a proper main in your class.  
        FunWithNamesV2.main(null);
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
    public void testFunWithNamesV2()
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
        System.out.println("\nRunning TestNamesV2 with James Tiberius Kirk for input.");
        System.out.println("Note that you wont see the input" 
            + " data while testing.\n");

        setUp("James Tiberius Kirk"); 

        String[] data = new String[3];
        data[0] = "Name: James Tiberius Kirk";
        data[1] = "Number of characters in full name: 17";
        data[2] = "Login id: kirkjt";

         
        checkCorrect(data);
    }
    
    public void test2()
    {
        //Print a blank line to separate previous test output from
        //from this test output.
        System.out.println("\nRunning TestNamesV2 with Mary Lou Retton for input.");
        System.out.println("Note that you wont see the input" 
            + " data while testing.\n");

        setUp("Mary Lou Retton"); 

        String[] data = new String[3];
        data[0] = "Name: Mary Lou Retton";
        data[1] = "Number of characters in full name: 13";
        data[2] = "Login id: rettonml";
        
        checkCorrect(data);
    }    
    
    public void checkCorrect(String[] data)
    {
        removeDoubleSpaces(data);
        
        for (int i = 0; i < data.length; i++)
        {
            if (!data[i].replace(" ", "").equals(result[i+3].replace(" ", "")))
            {
                printFail(i+3, data[i], result[i+3]);
            }
        }
    }
    
    public void removeDoubleSpaces(String[] data)
    {
        for (int i = 0; i < data.length; i ++)
        {
            while (data[i].contains("  "))
            {
                data[i].replaceAll("  "," ");
            }
        }        
    }
    
    public void printFail(int line, String mine, String yours)
    {
            String fb = "";
            fb += "TestNameV2 failed.\n";
            fb += "Line " + line + " doesn't match the expected output.\n";
            fb += "Yours : " + yours + "\n";
            fb += "Mine  : " + mine + "\n";
            fb += "If the above looks the same, make sure you used a tab (\\t)\n";
            fb += "instead of a bunch of spaces.\n";
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

        ts = new TestSales();
        ts.testSilent();
        
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
        report += "TestSales.... 15 of 15\n";
        report += "TestNameV2... " + grade + " of 15\n";
        report += "TotalGrade... " + (grade + 25) + " of 40\n";
        report += "\nNo test will be graded until the previous tests are passed.\n";        
        report += "For example, TestName grade will be 0 until TestAddress\n";
        report += "passes all tests.\n";        
                
        return report;
    }   
    
}
