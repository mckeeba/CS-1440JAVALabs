
// Junit testing framework.
import static org.junit.Assert.fail;  
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;

// Redirecting System.in and System.out
import java.io.PrintStream;            
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

// For checkstyle testing
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

// for testing our class
import java.util.Scanner;      
import java.util.Random;

/**
 * The test class Activity1MethodTest.
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
public class Activity1MethodTest
{
    public static final double OVERTIME_RATE = 1.5;
    public static final double SS_RATE = 0.10;
    public static final double TAX_RATE = 0.20;

    private static final String CHECKER_XML = 
        "http://student2.cs.appstate.edu/classes/JavaCodingStyle/"
        + "cs_appstate2.xml";
    private static final String FILE_TO_TEST = "Activity1PayStub.java";
    
    //30 seconds max per method tested
    @Rule
    public Timeout globalTimeout = new Timeout(30000);  
    
    private final int checkstyleErrorsAllowed = 0;

    private int prelabGrade;
    private int activity1Grade;
    private Activity1PayStub paystub;
    private PrintStream oldSystemOut;
    private InputStream oldSystemIn;
    private ByteArrayOutputStream outContent;
    private String randomName;
    private String randomSSN;
    private int randomRegHrs;
    private int randomOtHrs;
    private double randomHourlyRate;
    private String randomHourlyRateStr;
    private Random random;

    /**
     * Default constructor.
     */
    public Activity1MethodTest()
    {
        paystub = null;
        oldSystemOut = null;
        oldSystemIn = null;
        random = new Random(System.currentTimeMillis()); 
        paystub = new Activity1PayStub();
    }

    /**
     * Grade accessor to get partial grade.
     * 
     * @return current grade accumulated for activity1
     */
    public int getGrade()
    {
        return activity1Grade;
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
    private void resetStreams()
    {
        System.setOut(oldSystemOut);
        System.setIn(oldSystemIn);
    }

    /**
     * The "entry point" for testing activity 1.
     */
    @Test
    public void testActivity1()
    {
        prelabGrade = 0;
        testPrevious();
        prelabGrade = 3;
        resetStreams();
        
        activity1Grade = 0;
        testSilent();
        
        // grade accumulated points in test above
        printGrade();
        
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
        String report = "\n____________________________________________\n";
        report += "LAB 3 GRADE:\n";
        report += "PreLab: Quiz: ..... ?? of 7 (consult AsULearn)\n";
        report += "PreLab: StyleTest..  " + prelabGrade + " of 3\n";
        report += "Activity 1      ... " + activity1Grade + " of 15\n";
        report += "Activity 2      ...  0 of 15\n";
        report += "PostLab         ...  0 of 20\n";
        report += "TotalGrade... " + (prelabGrade + activity1Grade) 
                    + " of 53\n";
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
    }
    
    /**
     * Silently (no output) tests in various ways the activity 1 work.
     * Notice that we can assign partial grades as some "subtests" work using
     * the grade field.  But we must be sure to printGrade before failing.
     */
    public void testSilent()
    {
        setUp();
        testCheckStyle();
        resetStreams();
        activity1Grade += 3;

        setUp();
        testSampleData();
        resetStreams();
        activity1Grade += 6;
        
        setUp();
        testRandomData();
        resetStreams();
        activity1Grade += 6;        
    }
    
    /**
     * Tests the sample data from the assignment specification.
     */
    private void testSampleData()
    {
        String inputString = "Tim Buctoo\n111-11-1112\n40\n15\n15.50\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(
                                                inputString.getBytes());
        System.setIn(inContent);

        try 
        {
            paystub.main(null);
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
     * Check proper format of output consisting of a line of underscores.
     * 
     * @param line is the line to check 
     * @return boolean value indicating success of the check
     */
    private boolean lineOfUnderscores(String line)
    {
        /*
          
        char[] charLine = line.toCharArray();
        int i = 0;
        for (i = 0; i < charLine.length && charLine[i] != '_'; i++) 
        {
            ; // may be some initial spaces, but once the underscores start...
        }
        for ( ; i < charLine.length; i++) 
        {
            if (charLine[i] != '_') 
            {
                return false;   
            }
        }
        return true;
        */
       
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
     * Private method to verify student output when given sample input.
     * 
     * @param outputString is the student program's actual output
     */
    private void verifySampleOutput(String outputString) 
    {
        String expectedOutput[] = 
        { 
            "",
            "Name: Tim Buctoo                            SSN: 111-11-1112",
            "",
            "Overtime Hours: 15      OT Rate: $23.25     OT Pay: $348.75  ",
            "Gross Pay: $968.75  ",
            "SS Withholding: $96.88   ",
            "Federal Tax: $174.38  ",
            "Net Pay: $697.50  ",
        };
            
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
        e1 = "Regular Hours: 40       Reg Rate: $15.50    Reg Pay: $620.00  ";
        e2 = "Regular Hours: 40       Reg Rate: $15.50    Reg Pay: 620.00  ";
        if (!line.equals(e1) && !line.equals(e2))
        {
            fail(" Reg hours, rate, pay  line of output is malformed.\n" 
                + "Expected:  \"" + e1 + "\"\n" 
                + "Your code: \"" + line + "\"\n");
        }
        
        /* check line 4 */
        checkLine(output, "OT hours,rate,pay line of output", 
                    expectedOutput[3]);

        /* check line 5 */
        checkLine(output, "gross pay line of output", expectedOutput[4]);

        /* check line 6 */
        checkLine(output, "Soc.Sec. withholding line of output", 
                    expectedOutput[5]);
        
        /* check line 7 */
        checkLine(output, "Fed tax line of output", expectedOutput[6]);

        /* check line 8 */
        checkLine(output, "net pay line of output", expectedOutput[7]);

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
    }

    /**
     * Helper method for making random data.
     * 
     * @return a random string
     */
    private String makeRandomAlphaString()
    {
        return makeRandomAlphaString(random.nextInt(100));
    }

    /**
     * Helper method for making random data.
     * 
     * @param len is the desired length of the random string
     * @return the random string generated
     */
    private String makeRandomAlphaString(int len)
    {
        final int NUM_ALPHA_CHARS = 26;
        int i;
        char[] charArray = new char[len];
        for (i = 0; i < len; i++) 
        {
            if (random.nextBoolean() == true) 
            {
                charArray[i] = (char) ('A' + random.nextInt(NUM_ALPHA_CHARS));
            }
            else
            {
                charArray[i] = (char) ('a' + random.nextInt(NUM_ALPHA_CHARS));
            }
        }

        return new String(charArray);
    }

    /**
     * Helper method for making radnom inputs.
     * 
     * @param len is the length (in digits) of the numeric value
     * @return a string of numeric digits
     */
    private String makeRandomDigitString(int len)
    {
        int i;
        char[] charArray = new char[len];
        for (i = 0; i < len; i++) 
        {
            charArray[i] = (char) ('0' + random.nextInt(10));
        }

        return new String(charArray);
    }

    /**
     * Helper method to generate random data and store in fields.
     */
    private void generateInputData()
    {
        final int NAME_FIELD_SIZE = 37;
        final int MAX_REG_HRS = 90;
        final int MAX_OT_HRS = 30;
        
        randomName = makeRandomAlphaString();
        if (randomName.length() > NAME_FIELD_SIZE) 
        {
            randomName = randomName.substring(0, NAME_FIELD_SIZE);
        }
        randomSSN = makeRandomDigitString(3) 
                    + "-" + makeRandomDigitString(2) 
                    + "-" + makeRandomDigitString(4);
                    
        randomRegHrs = random.nextInt(MAX_REG_HRS);
        
        randomOtHrs = random.nextInt(MAX_OT_HRS);
        
        randomHourlyRate = (5 + random.nextInt(15)) * random.nextDouble();
        randomHourlyRateStr = String.format("%-8.2f", randomHourlyRate);
        randomHourlyRate = Double.parseDouble(randomHourlyRateStr);
    }
    
    /**
     * Tests student code using random input data.
     */
    private void testRandomData()
    {
        generateInputData();

        // build inupt string from the random data and put into input stream
        String inputString = randomName + "\n" 
                                + randomSSN + "\n" 
                                + randomRegHrs + "\n" 
                                + randomOtHrs + "\n"  
                                + randomHourlyRateStr + "\n";
        ByteArrayInputStream inContent = 
                            new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inContent);

        // input is set, call the student's method!
        try 
        {
            paystub.main(null);
        }
        catch (java.util.InputMismatchException e) 
        {
            e.printStackTrace();
            fail("In main, input did not match what your Scanner expected." 
                + "Read data in order of description in assignment.");
        }
        catch (java.util.NoSuchElementException e) 
        {
            e.printStackTrace();
            fail("In main, your Scanner expected more input than provided." 
                + "Assignment specified 5 input values.");
        }
        catch (java.lang.IllegalStateException e) 
        {
            e.printStackTrace();
            fail("In main, your Scanner malfunctioned; " 
                + "did it get closed somehow."
                + " \nView the exception details in the bottom section"  
                + " of your terminal window.");
        }
        catch (java.util.IllegalFormatConversionException e) 
        {
            e.printStackTrace();
            fail("In main, you have an incorrect format in your printf."
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

        // okay, student code has run, now verify output
        verifyRandomOutput(outContent.toString());
    }

    /**
     * Helper method to build expected output from the random data fields.
     * 
     * @return String array of expected output based on random data in fields
     */
    private String[] buildExpectedOutput()
    {
        String output[] = new String[10];
        
        /* line of underscores */
        output[0] = new String("");
        
        output[1] = String.format("Name: %-37s SSN: %-11s", 
                                        randomName, randomSSN);
                                        
        /* There are two possibilities for this line; one has $ other doesn't */
        double regPay = randomRegHrs * randomHourlyRate;
        output[2] = String.format("Regular Hours: %-8d Reg Rate: "
                                            + "$%-8.2f Reg Pay: $%-8.2f", 
                                     randomRegHrs, randomHourlyRate, regPay);
        output[3] = String.format("Regular Hours: %-8d Reg Rate: "
                                            + "$%-8.2f Reg Pay: %-8.2f", 
                                     randomRegHrs, randomHourlyRate, regPay);
                                     
        double otPay = randomOtHrs * (randomHourlyRate * OVERTIME_RATE);
        output[4] = String.format("Overtime Hours: %-7d OT Rate: "
                                            + "$%-9.2f OT Pay: $%-8.2f", 
                       randomOtHrs, (randomHourlyRate * OVERTIME_RATE), otPay);
                       
        double grossPay = regPay + otPay;
        output[5] = String.format("Gross Pay: $%-8.2f", grossPay);
        
        double ssnWithhold = grossPay * SS_RATE;
        output[6] = String.format("SS Withholding: $%-8.2f", ssnWithhold);
        
        double fedTax = (grossPay - ssnWithhold) * TAX_RATE;
        output[7] = String.format("Federal Tax: $%-8.2f", fedTax);
        
        output[8] = String.format("Net Pay: $%-8.2f", 
                                    grossPay - ssnWithhold - fedTax);
                                    
        /* line of underscores */
        output[9] = new String("");
        
        return output;
    }
    
    /**
     * Helper method to verify output is expected.
     * 
     * @param outputString is the student's output
     */
    private void verifyRandomOutput(String outputString) 
    {
        String expectedOutput[] = buildExpectedOutput();
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

        /* check line 3; reg pay may/maynot have $, so do twice */
        if (!output.hasNextLine()) 
        {
            fail("Missing Reg hours,rate,pay line of output?");
        }
        String line = output.nextLine();
        if (!line.equals(expectedOutput[2]) 
                && !line.equals(expectedOutput[3]))
        {
            fail("Reg hours, rate, pay  line of output is malformed.\n"
                + "Expected:  \"" + expectedOutput[2] + "\"\n"
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
            //configuration = ConfigurationLoader.loadConfiguration(inputSource, 
            //    new PropertiesExpander(System.getProperties()), false);
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
                //configuration = ConfigurationLoader.loadConfiguration(
                    //inputSource, 
                    //new PropertiesExpander(System.getProperties()), false);
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
            //checker = new Checker();
            //checker.setModuleClassLoader(Checker.class.getClassLoader());
            //checker.configure(configuration);
            //checker.addListener(listener);
        
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

