
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;


/**
 * Tests the rabbit population model described in activity 3.
 * 
 * @author Julia Dana
 * @version 02.24.2014
 *
 */
public class Activity3
{
    private static final double EPSILON = 0.001;
    
    RabbitPopulation rp;

    
    /**
     * Sets up the test fixture.
     * 
     * Called before every test case method. Sets up new I/O streams. Creates
     * test object.
     */
    @Before
    public void setUp()
    {
        rp = new RabbitPopulation();
    }
    
    /**
     * Tests the no-arg constructor. Expects to default to zero.
     * Requires accessors to be working correctly.
     */
    @Test
    public void testNoArg()
    {
        String fb = "";
        rp = new RabbitPopulation();
        
        if (rp.getInitialPopulation() != 0 || rp.getSixMonthPopulation() != 0)
        {
            fb += "No-arg constructor test failed.\n";
            fb += "ALL fields should be set to zero in the" 
                + " no-arg constructor.";
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
        rp = new RabbitPopulation(1, 2);
        assertEquals(1, rp.getInitialPopulation());
        assertEquals(2, rp.getSixMonthPopulation());
        

        rp = new RabbitPopulation(-1, 2);
        assertEquals(0, rp.getInitialPopulation());
        assertEquals(2, rp.getSixMonthPopulation());
        

        rp = new RabbitPopulation(1, -2);
        assertEquals(1, rp.getInitialPopulation());
        assertEquals(0, rp.getSixMonthPopulation());
    }

    /**
     * Tests the getInitialPopulation and setInitialPopulation methods.
     */
    @Test
    public void testInitialPopulationAM()
    {
        rp.setInitialPopulation(0);
        if (rp.getInitialPopulation() != 0)
        {
            fail("InitialPopulation accessor/mutator failed when testing "
                + "with the value 0");
        }
        
        rp.setInitialPopulation(1);
        if (rp.getInitialPopulation() != 1)
        {
            fail("InitialPopulation accessor/mutator failed when testing "
                + "with the value 1");
        }
        
        rp.setInitialPopulation(-1);
        if (rp.getInitialPopulation() != 0)
        {
            fail("InitialPopulation accessor/mutator failed when testing "
                + "with the value -1 (should set to 0).");
        }
    }


    /**
     * Tests the getSixMonthPopulation and setSixMonthPopulation methods.
     */
    @Test
    public void testSixMonthPopulationAM()
    {
        rp.setSixMonthPopulation(0);
        if (rp.getSixMonthPopulation() != 0)
        {
            fail("SixMonthPopulation accessor/mutator failed when testing "
                + "with the value 0");
        }
        
        rp.setSixMonthPopulation(1);
        if (rp.getSixMonthPopulation() != 1)
        {
            fail("SixMonthPopulation accessor/mutator failed when testing "
                + "with the value 1");
        }
        
        rp.setSixMonthPopulation(-1);
        if (rp.getSixMonthPopulation() != 0)
        {
            fail("SixMonthPopulation accessor/mutator failed when testing "
                + "with the value -1 (should set to 0).");
        }
    }

    /**
     * Tests the growth rate calculation.
     */
    @Test
    public void testCalculateGrowthRate()
    {
        rp.setInitialPopulation(2);
        rp.setSixMonthPopulation(4);
        assertEquals("Failed calculating growth rate with initial population"
            + "of 2 and a six-month population of 4. Should be 1 (100% growth)",
            1, rp.calculateGrowthRate(), EPSILON);
        
        rp.setInitialPopulation(1);
        rp.setSixMonthPopulation(3);
        assertEquals("Failed calculating growth rate with initial population"
            + "of 1 and a six-month population of 3. Should be 2 (200% growth)",
            2, rp.calculateGrowthRate(), EPSILON);
        
        rp.setInitialPopulation(2);
        rp.setSixMonthPopulation(3);
        assertEquals("Failed calculating growth rate with initial population"
            + "of 2 and a six-month population of 3. Should be .5 (50% growth)",
            0.5, rp.calculateGrowthRate(), EPSILON);
    }

    /**
     * Tests the calculation of the population at 12 months.
     */
    @Test
    public void testCalculate12MonthPopulation()
    {
        rp.setInitialPopulation(2);
        rp.setSixMonthPopulation(4);
        assertEquals("Failed calculating twelve-month population with initial"
            + "population of 2 and six-month population of 4. \n"
            + "Got: " + rp.calculate12MonthPopulation() + "\n"
            + "Expected: 8",
            8, rp.calculate12MonthPopulation());
        
        rp.setInitialPopulation(1);
        rp.setSixMonthPopulation(3);
        assertEquals("Failed calculating twelve-month population with initial"
            + "population of 1 and six-month population of 3. \n"
            + "Got: " + rp.calculate12MonthPopulation() + "\n"
            + "Expected: 9",
            9, rp.calculate12MonthPopulation());
        
        rp.setInitialPopulation(2);
        rp.setSixMonthPopulation(3);
        assertEquals("Failed calculating twelve-month population with initial"
            + "population of 2 and six-month population of 3. \n"
            + "Got: " + rp.calculate12MonthPopulation() + "\n"
            + "Expected: 5 \n"
            + " Are you rounding correctly?",
            5, rp.calculate12MonthPopulation());
    }

}
