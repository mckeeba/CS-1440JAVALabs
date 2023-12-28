/* JUnit testing needs these. */
import static org.junit.Assert.fail; 
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;


/**
 * PostlabDeclTest.java.
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

public class PostlabDeclTest
{
    // Constant fields should be used for: OT_RATE, SS_RATE, TAX_RATE
    private static final int NUM_CONST_FIELDS_EXPECTED = 3;
    // Instance fields needed for data inputs: name, ssn, hrs, ot, pay
    private static final int NUM_FIELDS_EXPECTED = 5;  
    private static final int NUM_STATIC_METHODS_EXPECTED = 1;  
    // Instance methods: getInstance, calculate, printPayStub, 
    //    2 accessors, 2 mutators
    private static final int NUM_METHODS_EXPECTED = 7;  

    // modifier bitfields: public is 0x01, static is 0x08, final is 0x10
    private static final int PUBLIC_STATIC_MODBITS = 0x09; 
    private static final int PUBLIC_STATIC_FINAL_MODBITS = 0x19; 
    private static final int PRIVATE_STATIC_MODBITS = 0x0A; 
    private static final int PRIVATE_MODBITS = 0x02;
    private static final int PUBLIC_MODBITS = 0x01;
    
    private static final String CLASS_TO_TEST = "PayStub";

    private Class c = null;

    /**
     * Default constructor for test class PayStubTest.
     */
    public PostlabDeclTest()
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
     * Tests for declarations of code in postlab.
     * 
     * Student should be declaring three constant fields
     * Verify these properties exist as best as we can.
     */
    @Test
    public void testConstantDeclarations()
    {
        testConstants();
    }
    
    /**
     * Allows other test classes to test this.
     */
    public void testConstants()
    {
        if (c == null)
        {
            setUp();
        }
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
     * Student should be declaring instance fields to hold input data.
     * Verify these properties exist as best as we can.
     */
    @Test
    public void testFieldDeclarations()
    {
        testFields();
    }
    
    /**
     * Allows other test classes to test this.
     */
    public void testFields()
    {
        if (c == null)
        {
            setUp();
        }
        if (c == null) 
        {
            fail("Unable to load class for testing. "
                + "Did you name the class correctly?");
        }
       
        Field[] fields = c.getDeclaredFields();

        if (fields.length < NUM_FIELDS_EXPECTED) 
        {
            fail("PostlabDeclTest: specification requires instance fields "
                    + "(at least for the " + NUM_FIELDS_EXPECTED 
                    + " input data values.");
        }
        
        // check modifiers of fields
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
            fail("PostlabDeclTest: specification requires instance fields "
                    + "(at least for the " + NUM_FIELDS_EXPECTED 
                    + " input data values.");
        }

        // make sure no static (non-constant fields left)
        numFields = 0;
        expectedFieldMods = PRIVATE_STATIC_MODBITS;
        for (Field f : fields) 
        {
            int fieldMods = f.getModifiers();
            if ((fieldMods & expectedFieldMods) == expectedFieldMods) 
            {
                numFields++;
            }
        }    
        if (numFields > 0) 
        {
            fail("PostlabDeclTest: specification requires fields " 
                + "to NOT be static anymore.");
        }
    
        /* Don't know for sure if the fields found are the ones 
         * we expect, but this is best we can do.
         */
    }
    
    /**
     * Test the expected method declarations.  
     * 
     * We are interested in main, getInput, calculate, printPayStub.
     * There should also be two accessors and two mutators.  Complicated
     * somewhat by not requiring specific names though.
     */
    @Test
    public void testMethodDeclarations()
    {
        testMethods();
    }
    
    /**
     * Allows other test classes to test this.
     */
    public void testMethods()
    {
        if (c == null)
        {
            setUp();
        }
        if (c == null) 
        {
            fail("Unable to perform test.  Did you name the class correctly?");
        }

        Method[] methods = c.getDeclaredMethods();        
        checkMain(methods);
        checkSpecificMethods(methods);
        checkAccessorMethods(methods);
        checkMutatorMethods(methods);
        
        checkConstructor(c.getDeclaredConstructors());

    }
    
    /**
     * Test declaration of main method.
     * 
     * @param methods is array of all the class methods
     */
    private void checkMain(Method[] methods)
    {
        String[] paramTypes;
        for (Method m : methods) 
        {
            if ("main".equals(m.getName())) 
            {
                paramTypes = new String[1];
                paramTypes[0] = "class [Ljava.lang.String;";
                if (!methodIsProper(m, "main", PUBLIC_STATIC_MODBITS, 
                                    "void", paramTypes))
                {
                    fail("main method declared improperly.");
                }
                return;
            }
        }
	fail("main method missing.");
    }
    
    /**
     * Test declaration of constructor.
     * 
     * @param constructors is array of all the constructors
     */
    private void checkConstructor(Constructor<?>[] constructors)
    {
        String[] paramTypes;
        for (Constructor m : constructors) 
        {
            if ("PayStub".equals(m.getName())) 
            {
                paramTypes = new String[1];
                paramTypes[0] = "class java.util.Scanner";
                if (!methodIsProper(m, "PayStub", PUBLIC_MODBITS, paramTypes))
                {
                    fail("PayStub(Scanner) constructor missing or "
                        + "declared improperly.");
                }
                return;
            }
        }        
        fail("PayStub(Scanner) constructor missing or "
                        + "declared improperly.");
    }
    
    /**
     * Test declaration of specific methods: getInput, calculate, printPayStub.
     * 
     * @param methods is array of all the class methods
     */
    private void checkSpecificMethods(Method[] methods)
    {
        String[] paramTypes;
        for (Method m : methods) 
        {    
            if ("getInput".equals(m.getName()))
            {
                paramTypes = new String[1];
                paramTypes[0] = "class java.util.Scanner";
                if (!methodIsProper(m, "getInput", PRIVATE_MODBITS,
                                        "void", paramTypes))
                {
                    fail("getInput method missing or declared improperly.");
                }
            }
            else if ("calculate".equals(m.getName())) 
            {
                paramTypes = new String[0];
                if (!methodIsProper(m, "calculate", PRIVATE_MODBITS, 
                                    "void", paramTypes))
                {
                    fail("calculate method missing or declared improperly.");
                }
            }
            else if ("printPayStub".equals(m.getName())) 
            {
                paramTypes = new String[0];
                if (!methodIsProper(m, "printPayStub", PUBLIC_MODBITS, 
                                    "void", paramTypes))
                {
                    fail("printPayStub method missing or declared improperly.");
                }
            }
        }
    }
    
    /**
     * Test declaration of accessors.
     * 
     * @param methods is array of all the class methods
     */
    private void checkAccessorMethods(Method[] methods)
    {
        String[] paramTypes;
        boolean hasIntReturn = false;
        boolean hasDoubleReturn = false;

        for (Method m : methods) 
        {
            if (m.getName().startsWith("get"))
            {
                paramTypes = new String[0];
                if (!hasIntReturn)
                {
                    hasIntReturn = methodIsProper(m, m.getName(), 
                                                    PUBLIC_MODBITS, "int", 
                                                    paramTypes);
                }
                if (!hasDoubleReturn)
                {
                    hasDoubleReturn = methodIsProper(m, m.getName(), 
                                                    PUBLIC_MODBITS, "double", 
                                                    paramTypes);
                }
            }
        }
        if (!hasIntReturn) 
        {
            fail("accessor method for overtime hours "
                + "is missing or declared improperly.");
        }
        if (!hasDoubleReturn) 
        {
            fail("accessor method for gross pay "
                + "is missing or declared improperly.");
        }
    }
    
    /**
     * Test declaration of mutators.
     * 
     * @param methods is array of all the class methods
     */
    private void checkMutatorMethods(Method[] methods)
    {
        String[] paramTypes;
        boolean hasIntParam = false;
        boolean hasDoubleParam = false;

        for (Method m : methods) 
        {
            if (m.getName().startsWith("set"))
            {
                paramTypes = new String[1];
                if (!hasIntParam)
                {
                    paramTypes[0] = "int";
                    hasIntParam = methodIsProper(m, m.getName(), 
                                                    PUBLIC_MODBITS, "void", 
                                                    paramTypes);
                }
                if (!hasDoubleParam)
                {
                    paramTypes[0] = "double";
                    hasDoubleParam = methodIsProper(m, m.getName(), 
                                                    PUBLIC_MODBITS, "void", 
                                                    paramTypes);
                }
            }
        }
        if (!hasIntParam) 
        {
            fail("mutator method for overtime hours "
                + "is missing or declared improperly.");
        }
        if (!hasDoubleParam) 
        {
            fail("mutator method for hourly pay rate "
                + "is missing or declared improperly.");
        }
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
    
    /**
     * Verifies characteristics of a constructor.
     * 
     * @param m is the constructor method to check
     * @param name is the expected name of the method
     * @param expectMods is the expected modifiers (public, static, etc.)
     * @param expectParams is a String array describing the parameters
     * 
     * @return true if method matches expectations
     */
    private boolean methodIsProper(Constructor m, String name, int expectMods, 
                                   String[] expectParams)
    {
        int methodMods = m.getModifiers() & expectMods;
        Class<?>[] paramTypes = m.getParameterTypes();

        if (!name.equals(m.getName())) 
        {
            return false;
        }
        if (methodMods != expectMods) 
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



