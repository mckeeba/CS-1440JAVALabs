/* JUnit testing needs these. */
import static org.junit.Assert.fail; 
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * The test class PayStubTest1.
 *
 * This is an advanced class used to unit test student work for CS 1440 Lab 3.  
 * Students are welcome to view the code as an example of "good" code.  At a 
 * "high level" 1440 students can understand that this code tests the code they
 * are writing in a "companion" class.  They can see the inputs going into a 
 * test and the results expected.  However, there is much sophisticated stuff 
 * going on "under the hood."  Students should NOT be discouraged if they 
 * don't "get" the details.  
 *
 * @author  Jay Fenwick
 * @version Spring 2014
 */

public class Activity2DeclTest
{
    // Constant fields should be used for: OT_RATE, SS_RATE, TAX_RATE
    private static final int NUM_CONST_FIELDS_EXPECTED = 3;
    // Non-const fields needed for data inputs: name, ssn, hrs, ot, pay
    private static final int NUM_FIELDS_EXPECTED = 5;  

    // modifier bitfields: public 0x01, private 0x02, static 0x08, final 0x10
    private static final int PUBLIC_MODBITS = 0x01; 
    private static final int PUBLIC_STATIC_MODBITS = 0x09; 
    private static final int PUBLIC_STATIC_FINAL_MODBITS = 0x19; 
    private static final int PRIVATE_STATIC_MODBITS = 0x0A; 
    private static final int PRIVATE_MODBITS = 0x02; 

    private static final String CLASS_TO_TEST = "Activity2PayStub";

    private Class c = null;

    /**
     * Default constructor for test class PayStubTest.
     */
    public Activity2DeclTest()
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
        try 
        {
            c = Class.forName(CLASS_TO_TEST);
        }
        catch (Exception e) 
        {
            c = null;
        }
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
     * Tests for declarations of code in Activity 2.
     * 
     * Student should be declaring three constant fields
     * Verify these properties exist as best as we can.
     */
    @Test
    public void testConstantDeclarations()
    {
        if (c == null)
        {
            fail("Unable to load class for testing. "
                + "Did you name the class correctly?");
        }

        Field[] fields = c.getDeclaredFields();
        if (fields.length < NUM_CONST_FIELDS_EXPECTED) 
        {
            fail("Activity2DeclTest: specification requires constant fields " 
                + "for OVERTIME_RATE, SS_RATE, and TAX_RATE.");
        }

        // check modifiers of fields
        int numConstantFields = 0;
        int constantFieldMods = PUBLIC_STATIC_FINAL_MODBITS;
        for (Field f : fields) 
        {
            int fieldMods = f.getModifiers();
            if ((fieldMods & constantFieldMods) == constantFieldMods) 
            {
                numConstantFields++;
            }
        }    
        if (numConstantFields != NUM_CONST_FIELDS_EXPECTED) 
        {
            fail("Activity2DeclTest: specification requires constant fields " 
                + "for OVERTIME_RATE, SS_RATE, and TAX_RATE.");
        }

        /* Don't know for sure if the constant fields found are the ones 
         * we expect, but this is best we can do.
         */
    }

    /**
     * Tests for declarations of code in Activity 2.
     * 
     * Student should be declaring fields to hold input data.
     * Verify these properties exist as best as we can.
     */
    @Test
    public void testFieldDeclarations()
    {
        if (c == null)
        {
            fail("Unable to perform test.  Did you name the class correctly?");
        }

        Field[] fields = c.getDeclaredFields();
        if (fields.length < NUM_FIELDS_EXPECTED) 
        {
            fail("Activity2DeclTest: specification requires fields " 
                + "(at least for the " + NUM_FIELDS_EXPECTED 
                + " input data values).");
        }

        // check modifiers of fields, should be "enough" private
        int numFields = 0;
        int expectedFieldMods = PRIVATE_MODBITS;
        for (Field f : fields) 
        {
            int fieldMods = f.getModifiers();
            if ((fieldMods & expectedFieldMods) == expectedFieldMods) 
            {
                numFields++;
            }
        }    
        if (numFields < NUM_FIELDS_EXPECTED) 
        {
            fail("Activity2DeclTest: specification requires fields " 
                + "(at least for the " + NUM_FIELDS_EXPECTED 
                + " input data values).");
        }

        // should be no public static not final fields
        numFields = 0;
        expectedFieldMods = PUBLIC_STATIC_MODBITS;
        for (Field f : fields) 
        {
            int fieldMods = f.getModifiers();
            if ((fieldMods & expectedFieldMods) == expectedFieldMods
                 && (fieldMods ^ PUBLIC_STATIC_FINAL_MODBITS) != 0) 
            {
                numFields++;
            }
        }    
        if (numFields > 0) 
        {
            fail("Activity2DeclTest: fields should not be public.");
        }

        /* Don't know for sure if the fields found are the ones 
         * we expect, but this is best we can do.
         */
    }

    /**
     * Test the expected method declarations.  
     * 
     * We are interested in main, getInput, calculate, printPayStub.
     */
    @Test
    public void testMethodDeclarations()
    {
        if (c == null)
        {
            fail("Unable to perform test.  Did you name the class correctly?");
        }

        String[] paramTypes;
        Method[] methods = c.getDeclaredMethods();

        for (Method m : methods) 
        {
            if ("main".equals(m.getName())) 
            {
                paramTypes = new String[1];
                paramTypes[0] = "class [Ljava.lang.String;";
                if (!methodIsProper(m, "main", PUBLIC_STATIC_MODBITS, 
                    "void", paramTypes))
                {
                    fail("main method missing or declared improperly.");
                }
            }
            else if ("getInput".equals(m.getName()))
            {
                paramTypes = new String[1];
                paramTypes[0] = "class java.util.Scanner";
                //Make sure not declared static (jas)
                if (methodIsProper(m, "getInput", PRIVATE_STATIC_MODBITS, 
                    "void", paramTypes) 
                    || methodIsProper(m, "getInput", PUBLIC_STATIC_MODBITS,
                        "void", paramTypes))
                {
                    fail("getInput method missing or declared improperly.");
                }
                //Make sure declared correctly:public and not static(jas)
                if (!methodIsProper(m, "getInput", PUBLIC_MODBITS,
                        "void", paramTypes))
                {
                    fail("getInput method missing or declared improperly.");
                }
            }
            else if ("calculate".equals(m.getName())) 
            {
                paramTypes = new String[0];
                //Make sure not declared static (jas)
                if (methodIsProper(m, "calculate", PRIVATE_STATIC_MODBITS, 
                    "void", paramTypes) 
                    || methodIsProper(m, "calculate", PUBLIC_STATIC_MODBITS,
                        "void", paramTypes))
                {
                    fail("calculate method missing or declared improperly.");
                }
                //Make sure public (jas)
                if (!methodIsProper(m, "calculate", PUBLIC_MODBITS,
                        "void", paramTypes))
                {
                    fail("calculate method missing or declared improperly.");
                }
            }
            else if ("printPayStub".equals(m.getName())) 
            {
                paramTypes = new String[0];
                //Make sure not static (jas)
                if (methodIsProper(m, "printPayStub", PRIVATE_STATIC_MODBITS, 
                    "void", paramTypes) 
                    || methodIsProper(m, "printPayStub", PUBLIC_STATIC_MODBITS,
                        "void", paramTypes))  
                {
                    fail("printPayStub method missing or declared improperly.");
                }
                
                //Make sure is public (jas)
                if (!methodIsProper(m, "printPayStub", PUBLIC_MODBITS,
                        "void", paramTypes))  
                {
                    fail("printPayStub method missing or declared improperly.");
                }
            }
            else
            {
                System.err.println("Activity2DeclTest: "
                    + "ignoring declaration of " + m.getName());
            }
        }                           

        /* not completely sure, but the methods seem okay */
    }

    /**
     * Verifies characteristics of a method.
     * 
     * @param m is the method to check
     * @param name is the expected name of the method
     * @param expectMods is the expected modifiers (public, static, etc.)
     * @param expectRtnType is the expected return type
     * @param expectParams is a String array describing the parameters
     * 
     * @return true if method matches expectations
     */
    private boolean methodIsProper(Method m, String name, int expectMods, 
    String expectRtnType, String[] expectParams)
    {
        int methodMods = m.getModifiers() & expectMods;
        String rtnType = m.getReturnType().toString();
        Class<?>[] paramTypes = m.getParameterTypes();

        if (!name.equals(m.getName())) 
        {
            return false;
        }
        if (methodMods != expectMods) 
        { 
            return false; 
        }
        if (!expectRtnType.equals(rtnType)) 
        { 
            return false;
        }
        if (paramTypes.length != expectParams.length)
        { 
            return false;
        }
        for (String expectedType : expectParams) 
        {
            if (!expectedType.equals(paramTypes[0].toString()))
            { 
                return false;
            }
        }

        return true;
    }
}


