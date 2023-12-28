// For checkstyle testing
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import com.puppycrawl.tools.checkstyle.api.Configuration;
import com.puppycrawl.tools.checkstyle.DefaultLogger;
import com.puppycrawl.tools.checkstyle.PropertiesExpander;
import com.puppycrawl.tools.checkstyle.ConfigurationLoader;
import com.puppycrawl.tools.checkstyle.Checker;
import org.xml.sax.InputSource;
import java.net.URL;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Describe TestShapeFun here.
 * @author Joel Swanson
 * @version 02.02.2014
 */
public class TestShapeFun
{
    private int grade;
    private int styleErrors;

    /**
     * Default constructor for test class TestShapeFun.
     */
    public TestShapeFun()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
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
     * Test ShapeFun class.  Only testing for style.
     */
    @Test
    public void testActivity1()
    {
        testSilent();
        printGrade(grade);
    }

    /**
     * Test ShapeFun class.  Does not provide a printGrade
     * call at the end.  Allows calling from later JUnit
     * tests.
     */
    public void testSilent()
    {
        StyleTest styleTest = new StyleTest();
        styleErrors = styleTest.testCheckStyle("ShapeFun.java");
        grade = Math.max(4 - styleErrors, 0);
    }

    /**
     * Store the grade at the point of failure then prints the report.
     * @param grade The grade at the point of failure.
     */
    private void printGrade(int grade)
    {
        this.grade = grade;
        System.out.println(gradeReport());
    }

    /**
     * Creates a string with the grades for output.
     * @return Returns a report of the grade for the student
     * at the point of the failure.
     */
    private String gradeReport()
    {
        String report = "\n____________________________________________\n";
        report += "GRADE:\n";
        report += "ShapeFun Style...... " + grade
            + " of  4  Style Errors = " + styleErrors + ")\n";
        report += "TestHouseActivity2.. 0 of 10 (Not graded)\n";
        report += "House Style..........0 of  4 (Not graded)\n";
        report += "TestHouseActivity3.. 0 of 12 (Not graded)\n";
        report += "TestScene........... 0 of 12 (Not graded)\n";
        report += "Scene Style..........0 of  4 (Not graded)\n";
        report += "TotalGrade..........." + (grade) + " of 50\n";
        report += "\nNo test will be graded until the"
            + " previous tests are passed.\n";
        report += "For example, TestName grade will be 0 until TestAddress\n";
        report += "passes all tests.\n";
        report += "\n10 additional points will be added from the prelab \n";
        report += "quizzes on ASULearn for a total of 60.\n";
        return report;

    }
}

/**
 * The test class StyleTest.
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

class StyleTest
{
    private static final String CHECKER_XML =
        "http://student2.cs.appstate.edu/classes/JavaCodingStyle/"
        + "cs_appstate2.xml";

    private String filename;
    private int errorCount;
    private String errors;

    /**
     * No-arg constructor initializes fields.
     */
    public StyleTest()
    {
    }

    /**
     * Accessor for errorCount.
     * @return the number of errors.
     */
    public int getErrorCount()
    {
        return errorCount;
    }

    /**
     * Accessor for errors.
     * @return - string description of all errors.
     */
    public String getErrors()
    {
        return errors;
    }

    /**
     * Print the errors to the terminal.  Mostly for testing.
     */
    public void printErrors()
    {
        System.out.println(errors);
    }

    /**
     * Performs a Checkstyle test.
     * @param filename The name of the file to run through checkstyle.
     * @return The number of errors.
     */
    public int testCheckStyle(String filename)
    {

        if (!filename.endsWith(".java"))
        {
            filename += ".java";
        }

        /* Files */
        List<File> files = new ArrayList<File>();
        File file = new File(filename);


        if (!file.exists())
        {
            errorCount = -1;
            errors = "Cannot find file " + filename + ".";
            return errorCount;
        }

        files.add(new File(filename));

        /* Listener */
        ByteArrayOutputStream sos = new ByteArrayOutputStream();
        AuditListener listener = new DefaultLogger(sos, false);

        /* Configuration */
        InputSource inputSource;
        Configuration configuration;
        try
        {
            URL xmlConfigFile = new URL(CHECKER_XML);
            inputSource = new InputSource(xmlConfigFile.openStream());
            configuration = ConfigurationLoader.loadConfiguration(inputSource,
                new PropertiesExpander(System.getProperties()), false);
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
                configuration = ConfigurationLoader.loadConfiguration(
                    inputSource,
                    new PropertiesExpander(System.getProperties()), false);
            }
            catch (Exception e2)
            {
                System.err.println("WARNING: Unable to configure checkstyle "
                    + "manually for testing.");
                errorCount = -1;
                return errorCount;
            }
        }

        /* Create checker */
        Checker checker = null;
        try
        {
            checker = new Checker();
            checker.setModuleClassLoader(Checker.class.getClassLoader());
            checker.configure(configuration);
            checker.addListener(listener);
        }
        catch (Exception e)
        {
            System.err.println("WARNING: Unable to execute checkstyle for"
                + "testing.");
            errorCount = -1;
            return errorCount;
        }

        /* Invoke the checkstyle processing. */
        errorCount = checker.process(files);
        errors = sos.toString();
        //System.out.print(errors);
        /* Clean up */
        checker.destroy();

        return errorCount;
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
        // String s = "<module name=\"MagicNumber\">\n";
        // s += "  <property name=\"ignoreNumbers\" value=\"-1,0,1,2,3,4,5,"
            // + "6,7,8,9,10,11,12,13,14,15,16,20,32,50,100,212,200\"/>\n";
        // s += "</module>\n";

        // return s;
        return "";
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
        s += getMagicNumbersConfig();
        s += "<module name=\"SimplifyBooleanReturn\"/>\n";
        s += "</module>\n</module>\n";

        return s;
    }
}
