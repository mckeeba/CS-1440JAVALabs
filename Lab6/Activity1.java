

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * Checks the functionality of the constructors, accessors, mutators,
 * and methods described in activity 1.
 * 
 * @author Julia Dana
 * @version 02.24.2014
 */
public class Activity1
{
    private static final double EPSILON = 0.001;
    
    ISP isp;

    /**
     * Sets up the test fixture.
     * 
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        isp = new ISP();
    }
  
    /**
     * Tests the no-arg constructor. Expects to default to package 'A'.
     * Requires accessors to be working correctly.
     */
    @Test
    public void testNoArg()
    {
        String fb = "";
        isp = new ISP();
        
        if (isp.getPkg() != 'A' || isp.getHoursUsed() != 0)
        {
            fb += "No-arg constructor test failed.\n";
            fb += "Package should be 'A' and hours used should "
                + "be set to zero in the no-arg constructor.";
            fail(fb);
        }
    }
    
    /**
     * Tests the parameter constructor. 
     * Requires accessors to be working correctly.
     */
    @Test
    public void testParamConstructor()
    {
        isp = new ISP('A', 5);
        assertEquals("Expected package to be 'A', got " + isp.getPkg(), 
            'A', isp.getPkg());
        assertEquals(5, isp.getHoursUsed(), EPSILON);
        
        isp = new ISP('A', 11);
        assertEquals("Expected package to be 'A', got " + isp.getPkg(), 
            'A', isp.getPkg());
        assertEquals('A', isp.getPkg());
        assertEquals(11, isp.getHoursUsed(), EPSILON);
        
        isp = new ISP('B', 19);
        assertEquals("Expected package to be 'B', got " + isp.getPkg(), 
            'B', isp.getPkg());
        assertEquals('B', isp.getPkg());
        assertEquals(19, isp.getHoursUsed(), EPSILON);
        
        isp = new ISP('B', 21);
        assertEquals("Expected package to be 'B', got " + isp.getPkg(), 
            'B', isp.getPkg());
        assertEquals('B', isp.getPkg());
        assertEquals(21, isp.getHoursUsed(), EPSILON);

        isp = new ISP('C', 31);
        assertEquals("Expected package to be 'C', got " + isp.getPkg(), 
            'C', isp.getPkg());
        assertEquals('C', isp.getPkg());
        assertEquals(31, isp.getHoursUsed(), EPSILON);

        isp = new ISP('C', 41);
        assertEquals("Expected package to be 'C', got " + isp.getPkg(), 
            'C', isp.getPkg());
        assertEquals(41, isp.getHoursUsed(), EPSILON);
    }
    
    /**
     * Tests the getPkg and setPkg methods.
     */
    @Test
    public void testPackageAM()
    {
        char[] testVals = {'A', 'B', 'C'};
        
        for (char t : testVals)
        {
            isp.setPkg(t);
            if (isp.getPkg() != t)
            {
                fail("Package accessor/mutator failed when testing"
                    + "with the value " + t);
            }
        }
    }
    
    /**
     * Tests the getHoursUsed and setHoursUsed methods.
     */
    @Test
    public void testHoursUsedAM()
    {
        double[] testVals = {0, 1, -1, 1.5};
        
        for (double t : testVals)
        {
            isp.setHoursUsed(t);
            if (isp.getHoursUsed() != t)
            {
                fail("HoursUsed accessor/mutator failed when testing "
                    + "with the value " + t);
            }
        }
    }
    
    /**
     * Tests calculating total charges when the selected package is 'A'.
     */
    @Test
    public void testCalcTotalChargesPkgA() 
    {
        
        testPackageCharges('A', 0, 9.95);
        testPackageCharges('A', 9.9, 9.95);
        testPackageCharges('A', 10, 9.95);
        testPackageCharges('A', 11, 11.95);
        testPackageCharges('A', 21, 31.95);
    }
    
    
    /**
     * Tests calculating total charges when the selected package is 'B'.
     */
    @Test
    public void testCalcTotalChargesPkgB() 
    {
        testPackageCharges('B', 0, 14.95);
        testPackageCharges('B', 9.9, 14.95);
        testPackageCharges('B', 10, 14.95);
        testPackageCharges('B', 11, 14.95);
        testPackageCharges('B', 19.9, 14.95);
        testPackageCharges('B', 20.0, 14.95);
        testPackageCharges('B', 21.0, 15.95);
    }
    
    /**
     * Tests calculating total charges when the selected package is 'C'.
     */
    @Test
    public void testCalcTotalChargesPkgC() 
    {
        testPackageCharges('C', 0, 19.95);
        testPackageCharges('C', 9.9, 19.95);
        testPackageCharges('C', 10.0, 19.95);
        testPackageCharges('C', 11.0, 19.95);
        testPackageCharges('C', 21.0, 19.95);
        
    }
    
    /**
     * Test packages for specific hours and specific charges.
     * 
     * @param pkg The package to test.
     * @param expectedHours The number of hours to set for this test.
     * @param expectedCharges The amount the student's total charge 
     * calculation should return.
     */
    private void testPackageCharges(char pkg, 
        double expectedHours, double expectedCharges)
    {
        isp.setPkg(pkg);
        isp.setHoursUsed(expectedHours);
        //Calculate the charges.
        double studentCharges = isp.calculateCharges();
        
        //Get the hours used after calculating to make
        //sure they were not changed.
        double studentHours = isp.getHoursUsed();

        //Check that they returned the correct charge for the given package and
        //number of hours used.
        assertEquals("Failed calculating charges for package '" + pkg + "' " 
            + "at " + expectedHours + " hours used. Expected " 
            + expectedCharges + ", got " + studentCharges,
            expectedCharges, studentCharges, EPSILON);
            
        //Check that they DID NOT change the stored hours when calculating.
        assertEquals("Hours changed while calculating charges for package '" 
            + pkg + "'.\n" + "Hours were " + expectedHours 
            + ", but changed to " + studentHours
            + " after running calculateCharges().\n"
            + "Use local variables instead of changing the hours used field.\n",
            expectedHours, studentHours, EPSILON);        
        
    }

}
