// JUnit needs these.
import static org.junit.Assert.fail;  
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;

// for redirecting System.in and System.out
import java.io.PrintStream;             
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

// for checkstyle test
import java.util.List;     
import java.io.File;
import java.util.ArrayList;
/*import com.puppycrawl.tools.checkstyle.api.AuditListener;
import com.puppycrawl.tools.checkstyle.api.Configuration;
import com.puppycrawl.tools.checkstyle.DefaultLogger;
import com.puppycrawl.tools.checkstyle.PropertiesExpander;
import com.puppycrawl.tools.checkstyle.ConfigurationLoader;
import com.puppycrawl.tools.checkstyle.Checker;*/
import org.xml.sax.InputSource;
import java.net.URL;

// for testing the student class
import java.util.Scanner;      
import java.util.Random;


/**
 * The test class PostlabMethodTest.
 *
 * This is an advanced class used to unit test student work for CS 1440 Lab 3.  
 * Students are welcome to view the code as an example of "good" code.  At a 
 * "high level" 1440 students can understand that this code tests the code they 
 * are writing in a "companion" class.  They can see the inputs going into a 
 * test and the results expected.  However, there is much sophisticated stuff 
 * going on "under the hood."  Students should NOT be discouraged if they don't 
 * "get" the details.  
 *
 * @author  Jay Fenwick
 * @version Spring 2014
 */

public class PostlabMethodTest
{
    public static final double OVERTIME_RATE = 1.5;
    public static final double SS_RATE = 0.10;
    public static final double TAX_RATE = 0.20;

    private static final String CHECKER_XML = 
        "http://student2.cs.appstate.edu/classes/JavaCodingStyle/"
        + "cs_appstate2.xml";
    private static final String FILE_TO_TEST = "PayStub.java";
    
    //30 seconds max per method tested
    @Rule
    public Timeout globalTimeout = new Timeout(30000);   
    
    private final int checkstyleErrorsAllowed = 0;

    private int prelabGrade;
    private int activity1Grade;
    private int activity2Grade;
    private int postlabGrade;

    private PayStub paystub;
    private PrintStream oldSystemOut;
    private InputStream oldSystemIn;
    private ByteArrayOutputStream outContent;
    private String randomName;
    private String randomSSN;
    private int randomRegHrs;
    private int randomOtHrs;
    private double randomHourlyRate;
    private Random random;

    
    /**
     * Default constructor for test class PayStubTest.
     */
    public PostlabMethodTest()
    {
        paystub = null;
        oldSystemOut = null;
        oldSystemIn = null;
        random = new Random(System.currentTimeMillis()); 
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
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
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        resetStreams();
        printGrade();        
    }

    /**
     * Resets I/O streams.
     */
    private void resetStreams()
    {
        System.setOut(oldSystemOut);
        System.setIn(oldSystemIn);
    }

    /**
     * The "entry point" for testing activity 2.
     */
    @Test
    public void testPostlab()
    {
        prelabGrade = 0;
        activity1Grade = 0;
        activity2Grade = 0;
        testPrevious();
        prelabGrade = 3;
        activity1Grade = 15;
        activity2Grade = 15;
        resetStreams();

        postlabGrade = 0;
        testSilent();

        // grade accumulated points in test above
        // in tearDown? printGrade();

    }

    /**
     * Store the grade then print the report.
     */
    private void printGrade()    
    {
        resetStreams();
        System.out.println(gradeReport());
    }    

    /**
     * Creates a string with the grades for output.
     * 
     * @return a String containing the grader report
     */
    private String gradeReport()
    {
        int total = prelabGrade + activity1Grade 
                    + activity2Grade + postlabGrade;

        String report = "\n____________________________________________\n";
        report += "LAB 3 GRADE:\n";
        report += "PreLab: Quiz: ..... ?? of 7 (consult AsULearn)\n";
        report += "PreLab: StyleTest..  " + prelabGrade + " of 3\n";
        report += "Activity 1      ... " + activity1Grade + " of 15\n";
        report += "Activity 2      ... " + activity2Grade + " of 15\n";
        report += "PostLab         ... " + postlabGrade + " of 20\n";
        report += "TotalGrade... " + total + " of 53\n";
        report += "\nNo test will be graded until the previous tests "
            + "are passed.\n";        
        report += "For example, Activity1 and subsequent grades will "
            + "be 0 until StyleTest\n";
        report += "passes all tests.\n";        

        return report;
    }   

    /**
     * Test previous activities.
     */
    private void testPrevious()
    {
        //StyleTest st = new StyleTest();
        //st.testSilent();      
        resetStreams();
        setUp();
        Activity1MethodTest a1t = new Activity1MethodTest();
        a1t.testSilent();
        resetStreams();
        setUp();
        Activity2MethodTest a2t = new Activity2MethodTest();
        a2t.testSilent();
    }
    
    /**
     * Silently (no output) tests in various ways the activity 1 work.
     * Notice that we can assign partial grades as some "subtests" work using
     * the grade field.  But we must be sure to printGrade before failing.
     */
    public void testSilent()
    {
        PostlabDeclTest pdt = new PostlabDeclTest();
        pdt.testConstants();
        pdt.testFields();
        pdt.testMethods();
        postlabGrade += 6;
        
        setUp();
        testCheckStyle();
        resetStreams();
        postlabGrade += 4;

        setUp();
        testSampleData();
        resetStreams();
        postlabGrade += 10;
    }
    

    /**
     * Test with sample data.
     */
    public void testSampleData()
    {
        String inputString = "Homer Simpson\n111-11-1111\n40\n15\n15.50\n"
                                + "Marge Simpson\n222-22-2222\n40\n15\n15.50\n"
                                + "Bart Simpson\n333-33-3333\n40\n15\n15.50\n";
        ByteArrayInputStream inContent = 
            new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inContent);

        try 
        {
            PayStub.main(null);
        }
        catch (java.util.InputMismatchException e) 
        {
            e.printStackTrace();
            fail("Input did not match what your Scanner expected." 
                       + "Read data in order of description in assignment."
                + " \nView the exception details in the bottom section"  
                + " of your terminal window.");
        }
        catch (java.util.NoSuchElementException e) 
        {
            e.printStackTrace();
            fail("Your Scanner expected more input than provided." 
                       + "Assignment specified 5 input values."
                + " \nView the exception details in the bottom section"  
                + " of your terminal window.");
        }
        catch (java.lang.IllegalStateException e) 
        {
            e.printStackTrace();
            fail("Your Scanner malfunctioned; did it get closed somehow."
                + " \nView the exception details in the bottom section"  
                + " of your terminal window.");
        }
        catch (java.util.IllegalFormatConversionException e) 
        {
            e.printStackTrace();
            fail("You have an incorrect format in your printf."
                + " You seem to be trying to print a string with a d or f" 
                + " conversion."
                + " \nView the exception details in the bottom section"  
                + " of your terminal window.");
        }
        catch (java.util.MissingFormatWidthException e) 
        {
            e.printStackTrace();
            fail("In main, you have an incorrect format in your printf."
                + " You have an f conversion with no width specified."
                + " \nView the exception details in the bottom section"  
                + " of your terminal window.");
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail("Your main method failed with an unknown exception. "
                + " \nView the exception details in the bottom section"  
                + " of your terminal window.");
        }
        
        verifySampleOutput(outContent.toString());
    }

    /**
     * Build expected output array.
     * 
     * @return String[] array of expeccted outpout
     */
    String[] makeExpectedOutput()
    { 
        String[] expOutput = 
        {
            "_____________________________________________________________",
            "Name: Marge Simpson                         SSN: 222-22-2222",
            "Regular Hours: 40       Reg Rate: $15.50    Reg Pay: $620.00  ",
            "Regular Hours: 40       Reg Rate: $15.50    Reg Pay: 620.00  ",
            "Overtime Hours: 15      OT Rate: $23.25     OT Pay: $348.75  ",
            "Gross Pay: $968.75  ",
            "SS Withholding: $96.88   ",
            "Federal Tax: $174.38  ",
            "Net Pay: $697.50  ",
            "____________________________________________________________",
            "New gross pay: $1250.00 " 
        };
        return expOutput;
    }

    /**
     * Private method to verify student output when given sample input.
     * 
     * @param outputString is the student program's actual output
     */
    private void verifySampleOutput(String outputString) 
    {
        // empty strings are underscore lines; new gross pay may vary
        String expectedOutput[] = makeExpectedOutput();        
        Scanner output = new Scanner(outputString);
        
        /* check line 1 */
        if (!output.hasNextLine()) 
        {
            fail("No output?");
        }
        if (lineOfUnderscores(output.nextLine()) == false)
        { 
            fail("The first line of the pay stub should have only underscore" 
                + " characters and should be the width of the pay stub.");
        }

        /* check line 2 */
        checkLine(output, "Name and SSN line of output", expectedOutput[1]);

        /* check line 3; reg pay may/maynot have $, so do manually here */
        if (!output.hasNextLine()) 
        {
            fail("Missing Reg hours,rate,pay line of output?");
        }
        String line = output.nextLine();
        String e1;
        String e2;
        e1 = expectedOutput[2];
        e2 = expectedOutput[3];
        if (!line.equals(e1) && !line.equals(e2))
        {
            fail(" Reg hours, rate, pay  line of output is malformed.\n" 
                + "Expected:  \"" + e1 + "\"\n" 
                + "Your code: \"" + line + "\"\n");
        }

        /* check line 4 */
        checkLine(output, "OT hours,rate,pay line of output", 
            expectedOutput[4]);

        /* check line 5 */
        checkLine(output, "gross pay line of output", expectedOutput[5]);

        /* check line 6 */
        checkLine(output, "Soc.Sec. withholding line of output", 
            expectedOutput[6]);

        /* check line 7 */
        checkLine(output, "Fed tax line of output", expectedOutput[7]);

        /* check line 8 */
        checkLine(output, "net pay line of output", expectedOutput[8]);
            
        /* check line 9 */
        if (!output.hasNextLine()) 
        {
            fail("No last line of pay stub?");
        }
        if (lineOfUnderscores(output.nextLine()) == false)
        { 
            fail("The last line of the pay stub should have only underscore" 
                + " characters and should be the width of the pay stub.");
        }
            
        /* check line 10 */
        if (!output.hasNextLine()) 
        {
            fail("Missing new gross pay line of output?");
        }
        line = output.nextLine();
        if (!line.startsWith("New gross pay: $"))
        {
            fail("new gross pay line of output is malformed.\n"
                       + "Expected:  \"New gross pay: $\"\n"
                       + "Your code: \"" 
                       + line.substring(0, 1 + line.indexOf("$")) + "\"\n");
        }
        double newGrossPay = 
                    Double.parseDouble(line.substring(1 + line.indexOf("$")));
        if (newGrossPay <= 968.75)
        {
            fail("new gross pay not larger than old gross pay; "
                    + "mutator and/or accessor not working.");
        }
        
        // Seems to be working...
    }
    
    /**
     * Check proper format of output consisting of a line of underscores.
     * 
     * @param line is the line to check 
     * @return boolean value indicating success of the check
     */
    private boolean lineOfUnderscores(String line)
    {
        
        return line.contains("_____________________________________________" 
            + "_______________");
        
    }

    /**
     * Private method to check proper format of output line.
     * 
     * @param output Scanner containing next line to check
     * @param msg is a pretty printing message
     * @param expected is the expected output
     */
    private void checkLine(Scanner output, String msg, String expected)
    {
        if (!output.hasNextLine()) 
        {
            fail("Missing " + msg);
        }
        String line = output.nextLine();
        if (!line.equals(expected))
        {
            fail(" " + msg + " is malformed.\n"
                + "Expected:  \"" + expected + "\"\n" 
                + "Your code: \"" + line + "\"\n");
        }
    }
        
    /**
     * Entry point for testing conformance to Checkstyle.
     */
    public void testCheckStyle() 
    {
        /* Files */
        List<File> files = new ArrayList<File>();
        files.add(new File(FILE_TO_TEST));

        /* Listener */
        ByteArrayOutputStream sos = new ByteArrayOutputStream();
        //AuditListener listener = new DefaultLogger(sos, false);

        /* Configuration */
        InputSource inputSource;
        //Configuration configuration;
        try 
        {
            URL xmlConfigFile = new URL(CHECKER_XML);
            inputSource = new InputSource(xmlConfigFile.openStream());
            /*configuration = ConfigurationLoader.loadConfiguration(inputSource, 
                new PropertiesExpander(System.getProperties()), false);*/
        }
        catch (Exception e) 
        {
            /* Next version should hide all this inside of a userlib jar */
            System.err.println("WARNING: Unable to read online checkstyle "
                + "checks file. Using hardcoded 1/28/14 version of "
                + "cs_appstate2.xml.");

            String xmlConfig = getXmlConfig();

            inputSource = new InputSource(
                new ByteArrayInputStream(xmlConfig.getBytes()));
            try 
            {
                /*configuration = ConfigurationLoader.loadConfiguration(
                    inputSource, 
                    new PropertiesExpander(System.getProperties()), false);*/
            }
            catch (Exception e2)
            {
                System.err.println("WARNING: Unable to configure checkstyle "
                    + "manually for testing.");
                return;
            }
        }

        /* Create checker */
        //Checker checker = null;
        int errors = 0;
        try
        {
            /*checker = new Checker();
            checker.setModuleClassLoader(Checker.class.getClassLoader());
            checker.configure(configuration);
            checker.addListener(listener);*/

            /* Invoke the checkstyle processing. */
            //errors = checker.process(files);

            /* Clean up */
            //checker.destroy();
        }
        catch (Exception e)
        {
            System.err.println("WARNING: Unable to execute checkstyle for"
                + "testing.");
            return;
        }

        if (errors > checkstyleErrorsAllowed) 
        {
            fail(errors + " check style errors found." + sos.toString());
        }
    }

    /**
     * Local method to build XML for checkstyle.
     * 
     * @return header string for XML file
     */
    private String getXmlHeaderConfig()
    {
        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        s += "<!DOCTYPE module PUBLIC \"-//Puppy Crawl//DTD Check "
            + "Configuration 1.3//EN\" "
            + "\"http://www.puppycrawl.com/dtds/configuration_1_3.dtd\""
            + ">\n";
        return s;
    }

    /**
     * Local method to build XML for checkstyle.
     * 
     * @return checkstyle module definition for magic numbers
     */
    private String getMagicNumbersConfig()
    {
        String s = "<module name=\"MagicNumber\">\n";
        s += "  <property name=\"ignoreNumbers\" value=\"-1,0,1,2,3,4,5,"
            + "6,7,8,9,10,11,12,13,14,15,16,20,32,50,100,212\"/>\n";
        s += "</module>\n";

        return s;
    }

    /**
     * Local method to build XML for checkstyle.
     * 
     * @return checkstyle module definition for whitespace around operators
     */
    private String getWhitespacecConfig()
    {
        String s = "<module name=\"WhitespaceAfter\"/>\n";
        s += "<module name=\"WhitespaceAround\">\n";
        s += "<property name=\"tokens\" value=\"ASSIGN, BAND, BAND_ASSIGN, "
            + "BOR, BOR_ASSIGN, BSR, BSR_ASSIGN, BXOR, BXOR_ASSIGN, COLON, "
            + "DIV, DIV_ASSIGN, EQUAL, GE, GT, LAND, LCURLY, LE, "
            + "LITERAL_ASSERT, LITERAL_CATCH, LITERAL_DO, LITERAL_ELSE, "
            + "LITERAL_FINALLY, LITERAL_FOR, LITERAL_IF, LITERAL_RETURN, "
            + "LITERAL_SYNCHRONIZED, LITERAL_TRY, LITERAL_WHILE, LOR, LT, "
            + "MINUS, MINUS_ASSIGN, MOD, MOD_ASSIGN, NOT_EQUAL, PLUS, "
            + "PLUS_ASSIGN, RCURLY, SL, SLIST, SL_ASSIGN, SR, SR_ASSIGN, "
            + "STAR, STAR_ASSIGN, TYPE_EXTENSION_AND, QUESTION\"/>\n";
        s += "</module>\n";

        return s;
    }

    /**
     * Local method to build XML for checkstyle.
     * 
     * @return XML configuration string
     */
    private String getXmlConfig() 
    {
        String s = getXmlHeaderConfig();
        s += "<module name=\"Checker\">\n";
        s += "<module name=\"NewlineAtEndOfFile\">\n";
        s += "  <property name=\"severity\" value=\"warning\"/>\n";
        s += "  <property name=\"lineSeparator\" value=\"lf\"/>\n";
        s += "</module>\n";
        s += "<module name=\"TreeWalker\">\n";
        s += "<module name=\"DeclarationOrder\"/>\n";
        s += "<module name=\"AvoidStarImport\"/>\n";
        s += "<module name=\"RedundantImport\"/>\n";
        s += "<module name=\"UnusedImports\"/>\n";
        s += "<module name=\"Indentation\">\n";
        s += "  <property name=\"caseIndent\" value=\"4\"/>\n";
        s += "</module>\n";
        s += "<module name=\"LineLength\">\n";
        s += "  <property name=\"severity\" value=\"warning\"/>\n";
        s += "  <property name=\"tabWidth\" value=\"4\"/>\n";
        s += "  <property name=\"ignorePattern\" value=\"^$\"/>\n";
        s += "  <property name=\"max\" value=\"80\"/>\n";
        s += "</module>\n";
        s += "<module name=\"JavadocStyle\"/>\n";
        s += "<module name=\"JavadocType\">\n";
        s += "  <property name=\"authorFormat\" value=\"\\S\"/>\n";
        s += "  <property name=\"versionFormat\" value=\"\\S\"/>\n";
        s += "</module>\n";
        s += "<module name=\"JavadocMethod\"/>\n";
        s += "<module name=\"TrailingComment\"/>\n";
        s += "<module name=\"MultipleVariableDeclarations\"/>\n";
        s += "<module name=\"MethodLength\">\n";
        s += "  <property name=\"severity\" value=\"warning\"/>\n";
        s += "  <property name=\"countEmpty\" value=\"false\"/>\n";
        s += "  <property name=\"max\" value=\"100\"/>\n";
        s += "</module>\n";
        s += "<module name=\"LeftCurly\">\n";
        s += "  <property name=\"option\" value=\"nl\"/>\n";
        s += "</module>\n";
        s += "<module name=\"RightCurly\">\n";
        s += "  <property name=\"option\" value=\"alone\"/>\n";
        s += "</module>\n";
        s += "<module name=\"NeedBraces\"/>\n";
        s += "<module name=\"EmptyBlock\"/>\n";
        s += "<module name=\"MissingSwitchDefault\"/>\n";
        s += "<module name=\"DefaultComesLast\"/>\n";
        s += "<module name=\"FallThrough\"/>\n";
        s += "<module name=\"MethodParamPad\"/>\n";
        s += "<module name=\"NoWhitespaceAfter\"/>\n";
        s += "<module name=\"NoWhitespaceBefore\"/>\n";
        s += "<module name=\"OperatorWrap\"/>\n";
        s += "<module name=\"ParenPad\"/>\n";
        s += "<module name=\"TypecastParenPad\"/>\n";
        s += getWhitespacecConfig();
        s += "<module name=\"PackageName\"/>\n";
        s += "<module name=\"TypeName\"/>\n";
        s += "<module name=\"ConstantName\">\n";
        s += "  <property name=\"format\" value=\"^[A-Z][A-Z0-9_]*$\"/>\n";
        s += "</module>\n";
        s += "<module name=\"LocalFinalVariableName\">\n";
        s += "  <property name=\"format\" value=\"^[A-Z][A-Z0-9_]*$\"/>\n";
        s += "</module>\n";
        s += "<module name=\"LocalVariableName\"/>\n";
        s += "<module name=\"MemberName\"/>\n";
        s += "<module name=\"MethodName\"/>\n";
        s += "<module name=\"ParameterName\"/>\n";
        s += "<module name=\"StaticVariableName\"/>\n";
        //s += getMagicNumbersConfig();
        s += "<module name=\"SimplifyBooleanReturn\"/>\n";
        s += "</module>\n</module>\n";

        return s;
    }
}



