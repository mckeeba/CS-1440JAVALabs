/**
 * Activity2.java
 */
 
import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Activity2 tests lab 5 programming steps for this activity.
 * 
 * @author Joel Swanson
 * @version 02.08.2014
 */
public class TestActivity2
{
    private LemonadeStand ls;
    /**
     * No-arg constructor for test class Activity2.
     */
    public TestActivity2()
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
     * Make sure negative values are not allowed to be set by
     * mutators or constructors.
     */
    @Test
    public void testAccessorsMutatorsActivity2()
    {
        checkSetLemonsA2();
        checkSetGallonsOfWaterA2();
        checkSetCupsOfSugarA2();
        checkSetEmptyGlassesA2();
        checkSetGlassesOfLemonadeA2();
        checkSetPriceA2();
        checkSetIncomeA2();
    }

    /**
     * Make sure the argument constructor does not allow negative
     * numbers to be set as per activity 2.
     */
    @Test
    public void testConstructorActivity2()
    {
        String fb = "";
        ls = new LemonadeStand(-1, -1, -1, -1, -1.0);
        if (ls.getLemons() != 0
            || ls.getGallonsOfWater() != 0
            || ls.getCupsOfSugar() != 0
            || ls.getEmptyGlasses() != 0
            || ls.getGlassesOfLemonade() != 0
            || ls.getPrice() != 0.0
            || ls.getIncome() != 0.0)
        {
            fb += "Test for argument constructor failed for activty 2.\n";
            fb += "The following code was executed:\n\n";
            fb += "\tLemonadeStand ls = new LemonadeStand(-1, -1," 
                + " -1, -1, -1.0);\n\n";
            fb += "All fields should have been set to zero.\n";
            fb += "Your constructor should ONLY have calls to mutators.\n";
            fb += "You must correct testAccessorsMutatorsActivity2 before" 
                + " this test will pass.\n";
            
            fail(fb);
        }       
    }    
    /**
     * Test that setLemons does not allow negative values
     * in activity 2.
     */
    public void checkSetLemonsA2()
    {     
        ls = new LemonadeStand();
       
        //Make sure it didn't default to 0.  Set to 1 then
        //setting to -1 should set it back to zero.
        ls.setLemons(1);
        ls.setLemons(-1);
        if (ls.getLemons() != 0)
        {
            failAM2Message("Lemons", ls.getLemons());
        }
    }  

    /**
     * Test that setGallonsOfWater does not allow negative values
     * in activity 2.
     */
    public void checkSetGallonsOfWaterA2()
    {     
        ls = new LemonadeStand();

        //Make sure it didn't default to 0.  Set to 1 then
        //setting to -1 should set it back to zero.
        ls.setGallonsOfWater(1);
        ls.setGallonsOfWater(-1);
        if (ls.getGallonsOfWater() != 0)
        {
            failAM2Message("GallonsOfWater", ls.getGallonsOfWater());
        }
    }  
 
    /**
     * Test that setCupsOfSugar does not allow negative values
     * in activity 2.
     */
    public void checkSetCupsOfSugarA2()
    {     
        ls = new LemonadeStand();

        //Make sure it didn't default to 0.  Set to 1 then
        //setting to -1 should set it back to zero.
        ls.setCupsOfSugar(1);
        ls.setCupsOfSugar(-1);
        if (ls.getCupsOfSugar() != 0)
        {
            failAM2Message("CupsOfSugar", ls.getCupsOfSugar());
        }
    }      

    /**
     * Test that setEmpty does not allow negative values
     * in activity 2.
     */
    public void checkSetEmptyGlassesA2()
    {       
        ls = new LemonadeStand();
       
        //Make sure it didn't default to 0.  Set to 1 then
        //setting to -1 should set it back to zero.
        ls.setEmptyGlasses(1);
        ls.setEmptyGlasses(-1);
        if (ls.getEmptyGlasses() != 0)
        {
            failAM2Message("EmptyGlasses", ls.getEmptyGlasses());
        }
    }      
    
    /**
     * Test that setGlassesOfLemonade does not allow negative values
     * in activity 2.
     */
    public void checkSetGlassesOfLemonadeA2()
    {       
        ls = new LemonadeStand();
       
        //Make sure it didn't default to 0.  Set to 1 then
        //setting to -1 should set it back to zero.
        ls.setGlassesOfLemonade(1);
        ls.setGlassesOfLemonade(-1);
        if (ls.getGlassesOfLemonade() != 0)
        {
            failAM2Message("GlassesOfLemonade", ls.getGlassesOfLemonade());
        }
    }      
    
    /**
     * Test that setPrice does not allow negative values
     * in activity 2.
     */
    public void checkSetPriceA2()
    {        
        ls = new LemonadeStand();
       
        //Make sure it didn't default to 0.  Set to 1 then
        //setting to -1 should set it back to zero.
        ls.setPrice(1);
        ls.setPrice(-1);
        if (ls.getPrice() != 0)
        {
            failAM2Message("Price", ls.getPrice());
        }
    }      
    
    /**
     * Test that setIncome does not allow negative values
     * in activity 2.
     */
    public void checkSetIncomeA2()
    {        
        ls = new LemonadeStand();
       
        //Make sure it didn't default to 0.  Set to 1 then
        //setting to -1 should set it back to zero.
        ls.setIncome(1);
        ls.setIncome(-1);
        if (ls.getIncome() != 0)
        {
            failAM2Message("Income", ls.getIncome());
        }
    }      
    


    /**
     * Message printed from failing of activity 2 mutator
     * incorrectly setting a negative value.
     * @param fieldName Name of the field whose mutator
     * failed this test and set a negative value.
     * @param failValue The incorrect value returned by the 
     * accessor for the failed mutator.
     */
    public void failAM2Message(String fieldName, double failValue)
    {
        String fb = "";
        fb += "Test for mutators failed for activty 2.\n";
        fb += "The following code was executed:\n";
        fb += "\t LemonadeStand ls = new LemonadeStand();\n";
        fb += "\t ls.set" + fieldName + "(1.0);\n";
        fb += "\t ls.set" + fieldName + "(-1.0);\n";
        fb += "ls.get" + fieldName + "() should have returned 0.0, ";
        fb += "but it returned " + failValue + ".\n";
        fb += "Make sure set" + fieldName + " sets the field to ";
        fb += "0.0 if the argument is negative.\n";
        fail(fb);
    }    
        
    /**
     * Message printed from failing of activity 2 mutator
     * incorrectly setting a negative value.
     * @param fieldName Name of the field whose mutator
     * failed this test and set a negative value.
     * @param failValue The incorrect value returned by the 
     * accessor for the failed mutator.
     */    
    public void failAM2Message(String fieldName, int failValue)
    {
        String fb = "";
        fb += "Test for mutators failed for activty 2.\n";
        fb += "The following code was executed:\n\n";
        fb += "\t LemonadeStand ls = new LemonadeStand();\n";
        fb += "\t ls.set" + fieldName + "(1);\n";
        fb += "\t ls.set" + fieldName + "(-1);\n\n";
        fb += "ls.get" + fieldName + "() should have returned 0, ";
        fb += "but it returned " + failValue + ".\n";
        fb += "Make sure set" + fieldName + " sets the field to ";
        fb += "0 if the argument is negative.\n";
        fail(fb);
    }
}
