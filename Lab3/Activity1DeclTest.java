
/* JUnit testing needs these. */
import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * The test class Activity1DeclTest
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

public class Activity1DeclTest
{
    // Constant fields should be used for: OT_RATE, SS_RATE, TAX_RATE
    private static final int NUM_CONST_FIELDS_EXPECTED = 3;
    // modifier bitfields: public is 0x01, static is 0x08, final is 0x10
    private static final int PUBLIC_STATIC_MODBITS = 0x09; 
    private static final int PUBLIC_STATIC_FINAL_MODBITS = 0x19; 

    private static final String CLASS_TO_TEST = "Activity1PayStub";
    
    private Class c = null;
    
    /**
     * Default constructor for test class PayStubTest.
     */
    public Activity1DeclTest()
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
     * Tests for declarations of code in Activity 1.
     * 
     * Student should be declaring three constant fields
     * Verify these properties exist as best as we can.
     */
    @Test
    public void testConstantDeclarations()
    {
        if (c == null)   
        {
            fail("Unable to perform test.  Did you name the class correctly?");
        }
        
        Field[] fields = c.getDeclaredFields();
        if (fields.length < NUM_CONST_FIELDS_EXPECTED) 
        {
            fail("Activity1DeclTest: specification "
                + "requires constant fields for "
                + "OVERTIME_RATE, SS_RATE, and TAX_RATE.");
        }
        
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
            fail("Activity1DeclTest.: specification "
                + "requires constant fields for "
                + "OVERTIME_RATE, SS_RATE, and TAX_RATE.");
        }
        
        /* Don't know for sure if the constant fields found are the ones 
         * we expect, but this is best we can do.
         */
    }
    
    /**
     * Test the expected method declarations.  
     * 
     * We are only interested in the main method for now.
     */
    @Test
    public void testMethodDeclarations()
    {
        if (c == null)           
        {
            fail("Unable to perform test.  Did you name the class correctly?");
        }
        
        Method[] methods = c.getDeclaredMethods();
        
        // main method
        boolean proper = false;
        int expectedMods = PUBLIC_STATIC_MODBITS;
        for (Method m : methods) 
        {
            int methodMods = m.getModifiers() & expectedMods;
            String rtnType = m.getReturnType().toString();
            Class<?>[] parmTyps = m.getParameterTypes();
            if (methodMods == expectedMods 
                && "main".equals(m.getName()) 
                && "void".equals(rtnType) 
                && parmTyps.length == 1 
                && "class [Ljava.lang.String;".equals(parmTyps[0].toString()))
            {
                proper = true;
            }
        }
        if (!proper)
        {
            fail("Activity1DeclTest: "
                + "main method missing or declared improperly.");
        }
    }
}



