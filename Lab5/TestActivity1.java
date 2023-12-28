/**
 * Activity1.java
 */

import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * Activity1 tests lab 5 programming steps for this activity.
 *
 * @author Joel Swanson
 * @version 02.07.2014
 */
public class TestActivity1
{
    private LemonadeStand ls;
    
    /**
     * No-arg constructor for test class Activity1.
     */
    public TestActivity1()
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
     * Simple test of accessors and mutators.  Do they 
     * correctly set values.
     */
    @Test
    public void testAccessorsMutatorsActivity1()
    {
        ls = new LemonadeStand();
        checkLemonAM1();
        checkWaterAM1();
        checkSugarAM1();
        checkEmptyAM1();
        checkGlassesOfLemonadeAM1();
        checkPriceAM1();
        checkIncomeAM1();
    }

    /**
     * Test the argument constructor.
     */
    @Test  
    public void testConstructorActivity1()
    {
        checkNoArg();
        checkCA1();
        checkCA2();
    }
    
    /**
     * Test the makeLemonade method.
     */
    @Test
    public void testMakeLemonadeActivity1()
    {
        checkMakeLemonadeAct1Test1();
        checkMakeLemonadeAct1Test2();
    }
    
    /**
     * Test the sellLemonade method.
     */
    @Test
    public void testSellLemonadeActivity1()
    {
        checkSellLemonadeAct1Test1();
        checkSellLemonadeAct1Test2();
    }
    
    /**
     * First test of the sellLemonade method 
     * for activity 1.
     */
    public void checkSellLemonadeAct1Test1()
    {
        String fb = "";
        ls = new LemonadeStand(10, 2, 4, 10, 1.5);
        ls.makeLemonade();
        ls.sellLemonade();
        
        fb += "Test for sellLemonade failed.\n";
        fb += "The following code was executed:\n";
        fb += "\t LemonadeStand ls = new LemonadeStand(10, 2, 4, 10, 1.5);\n";
        fb += "\t ls.makeLemonade();\n";
        fb += "\t ls.sellLemonade();\n";        
        
        if (ls.getGlassesOfLemonade() != 7)
        {
            fb += "Accessor getGlassesOfLemonade should have ";
            fb += "returned 7 but it returned " 
                + ls.getGlassesOfLemonade() + ".\n";
            fb += "Make sure to subtract 1 from glassesOfLemonade" 
                + " when selling.\n";
            fail(fb);
        }
        else if (ls.getIncome() != 1.5)
        {

            fb += "Accessor getIncome should have ";
            fb += "returned 1.5 but it returned " + ls.getIncome() 
                + ".\n";
            fb += "Make sure to ADD price to income when selling.\n";
            fail(fb);           
        }
    }
    
    /**
     * Second test of the sellLemonade method 
     * for activity 1.
     */
    public void checkSellLemonadeAct1Test2()
    {
        String fb = "";
        ls = new LemonadeStand(10, 2, 4, 10, 1.25);
        ls.makeLemonade();
        ls.sellLemonade();
        ls.sellLemonade();
        
        fb += "Test for sellLemonade failed.\n";
        fb += "The following code was executed:\n";
        fb += "\t LemonadeStand ls = new LemonadeStand(10, 2, 4, 10, 1.25);\n";
        fb += "\t ls.makeLemonade();\n";
        fb += "\t ls.sellLemonade();\n";        
        fb += "\t ls.sellLemonade();\n";        
        
        if (ls.getGlassesOfLemonade() != 6)
        {
            fb += "Accessor getGlassesOfLemonade should have ";
            fb += "returned 6 but it returned " 
                + ls.getGlassesOfLemonade() + ".\n";
            fb += "Make sure to subtract 1 from glassesOfLemonade" 
                + " when selling.\n";            
            fail(fb);
        }
        else if (ls.getIncome() != 2.5)
        {

            fb += "Accessor getIncome should have ";
            fb += "returned 2.5 but it returned " + ls.getIncome() 
                + ".\n";
            fb += "Make sure to ADD price to income when" 
                + " selling.\n";                
            fail(fb);           
        }        
    }    
    
    /**
     * First test of the makeLemonade method 
     * for activity 1. 
     */
    public void checkMakeLemonadeAct1Test1()
    {
        int testValue;
        String fb = "";
        fb += "Test for makeLemonade failed.\n";
        fb += "The following code was executed:\n\n";
        fb += "\t LemonadeStand ls = new LemonadeStand(10,20,30,40,50.5);\n";
        fb += "\t ls.makeLemonade();\n\n";
        
        ls = new LemonadeStand(10, 20, 30, 40, 50.5);
        ls.makeLemonade();
        if (ls.getLemons() != 4)
        {
            fb += "Accessor getLemons should have returned 4 ";
            fb += "but it returned " + ls.getLemons() + ".\n";
            fail(fb);
        }
        else if (ls.getGallonsOfWater() != 19)
        {
            fb += "Accessor getGallonsOfWater should have returned 19 ";
            fb += "but it returned " + ls.getGallonsOfWater() + ".\n";
            fail(fb);
        }
        else if (ls.getCupsOfSugar() != 29)
        {
            fb += "Accessor getCupsOfSugar should have returned 29 ";
            fb += "but it returned " + ls.getCupsOfSugar() + ".\n";
            fail(fb);
        }
        else if (ls.getEmptyGlasses() != 32)
        {
            fb += "Accessor getEmptyGlasses should have returned 32 ";
            fb += "but it returned " + ls.getEmptyGlasses() + ".\n";
            fail(fb);
        }
        else if (ls.getGlassesOfLemonade() != 8)
        {
            fb += "Accessor getGlassesOfLemonade should have returned 8 ";
            fb += "but it returned " + ls.getGlassesOfLemonade() + ".\n";
            fail(fb);
        }
    }
    
    /**
     * Second test of the makeLemonade method 
     * for activity 1.
     */
    public void checkMakeLemonadeAct1Test2()
    {
        int testValue;
        String fb = "";
        fb += "Test for makeLemonade failed.\n";
        fb += "The following code was executed:\n\n";
        fb += "\t LemonadeStand ls = new LemonadeStand(10,20,30,40,50.5);\n";
        fb += "\t ls.makeLemonade();\n";
        fb += "\t ls.makeLemonade();\n\n";
        
        ls = new LemonadeStand(18, 18, 18, 18, 100.1);
        ls.makeLemonade();
        ls.makeLemonade();
        if (ls.getLemons() != 6)
        {
            fb += "Accessor getLemons should have returned 6 ";
            fb += "but it returned " + ls.getLemons() + ".\n";
            fail(fb);
        }
        else if (ls.getGallonsOfWater() != 16)
        {
            fb += "Accessor getGallonsOfWater should have returned 16 ";
            fb += "but it returned " + ls.getGallonsOfWater() + ".\n";
            fail(fb);
        }
        else if (ls.getCupsOfSugar() != 16)
        {
            fb += "Accessor getCupsOfSugar should have returned 16 ";
            fb += "but it returned " + ls.getCupsOfSugar() + ".\n";
            fail(fb);
        }
        else if (ls.getEmptyGlasses() != 2)
        {
            fb += "Accessor getEmptyGlasses should have returned 2 ";
            fb += "but it returned " + ls.getEmptyGlasses() + ".\n";
            fail(fb);
        }
        else if (ls.getGlassesOfLemonade() != 16)
        {
            fb += "Accessor getGlassesOfLemonade should have returned 16 ";
            fb += "but it returned " + ls.getGlassesOfLemonade() + ".\n";
            fail(fb);
        }  
    }
    
    /**
     * Make sure the no-arg constructor doesn't set
     * any values other than 0.
     */
    public void checkNoArg()
    {
        String fb = "";
        ls = new LemonadeStand();
        if (ls.getLemons() != 0
            || ls.getGallonsOfWater() != 0
            || ls.getCupsOfSugar() != 0
            || ls.getEmptyGlasses() != 0
            || ls.getGlassesOfLemonade() != 0
            || ls.getPrice() != 0.0
            || ls.getIncome() != 0.0)
        {
            fb += "No-arg constructor test failed.\n";
            fb += "ALL fields should be set to zero in the" 
                + " no-arg constructor.";
            fail(fb);
        }  
    }
    
    /**
     * Test constructor with one set of data.
     */
    public void checkCA1()
    {
        boolean error = false;
        String fb = "";
        fb += "Argument constructor test failed in activity 1.\n";
        fb += "A LemonStand was created like this:\n\n";
        fb += "\tLemonadeStand ls = new LemonadeStand(1, 2, 3, 4, 5.5)\n\n";

        ls = new LemonadeStand(1, 2, 3, 4, 5.5);
        if (ls.getLemons() != 1)
        {
            fb += "Method getLemons did not return 1.\n";
            error = true;
        }
        else if (ls.getGallonsOfWater() != 2)
        {
            fb += "Method getGallonsOfWater did not return 2.\n";
            error = true;
        }
        else if (ls.getCupsOfSugar() != 3)
        {
            fb += "Method getCupsOfSugar did not return 3.\n";
            error = true;
        }
        else if (ls.getEmptyGlasses() != 4)
        {
            fb += "Method getEmptyGlasses did not return 4.\n";
            error = true;
        }
        else if (ls.getPrice() != 5.5)
        {
            fb += "Method getPrice did not return 5.5.\n";
            error = true;
        }
        else if (ls.getGlassesOfLemonade() != 0)
        {
            fb += "Method getGlassesOfLemonade did not return 0.\n";
            fb += "Field glassesOfLemonade should have been set to 0" 
                + " in constructor.\n";
            error = true;            
        }
        else if (ls.getIncome() != 0.0)
        {
            fb += "Method getIncome did not return 0.\n";
            fb += "Field income should have been set to 0 in constructor.\n";
            error = true;            
        }

        fb += "Correct any accessor or mutator problem first.\n";
        fb += "Make sure parameter order for constructor is exactly\n";
        fb += "the same as the order defined in the UML diagram.\n";

        if (error)
        {
            fail(fb);
        }
    }

    
    /**
     * Test constructor with second set of data.
     */
    public void checkCA2()
    {
        boolean error = false;
        String fb = "";
        fb += "Argument constructor test failed in activity 1.\n";
        fb += "A LemonStand was created like this:\n\n";
        fb += "\tLemonadeStand ls = new LemonadeStand(5,6,7,8,9.9)\n\n";

        ls = new LemonadeStand(7, 8, 9, 10, 12.5);
        if (ls.getLemons() != 7)
        {
            fb += "Method getLemons did not return 7.\n";
            error = true;
        }
        else if (ls.getGallonsOfWater() != 8)
        {
            fb += "Method getGallonsOfWater did not return 8.\n";
            error = true;
        }
        else if (ls.getCupsOfSugar() != 9)
        {
            fb += "Method getCupsOfSugar did not return 9.\n";
            error = true;
        }
        else if (ls.getEmptyGlasses() != 10)
        {
            fb += "Method getEmptyGlasses did not return 10.\n";
            error = true;
        }
        else if (ls.getPrice() != 12.5)
        {
            fb += "Method getPrice did not return 12.5.\n";
            error = true;
        }
        else if (ls.getGlassesOfLemonade() != 0)
        {
            fb += "Method getGlassesOfLemonade did not return 0.\n";
            fb += "Field glassesOfLemonade should have been set to 0" 
                + " in constructor.\n";
            error = true;            
        }
        else if (ls.getIncome() != 0.0)
        {
            fb += "Method getIncome did not return 0.\n";
            fb += "Field income should have been set to 0 in constructor.\n";
            error = true;            
        }

        fb += "Correct any accessor or mutator problem first.\n";
        fb += "Make sure parameter order for constructor is exactly\n";
        fb += "the same as the order defined in the UML diagram.\n";
        if (error)
        {
            fail(fb);
        }
        
    }
    
    /**
     * Test the lemons accessor and mutator for activity 1.
     */
    public void checkLemonAM1()
    {
        //First test
        ls.setLemons(5);
        if (ls.getLemons() != 5)
        {
            failAM1Message("Lemons");
        }
        
        //Second test.  Two tests prevents cheating 
        //by simply returning the tested value.
        ls.setLemons(3);
        if (ls.getLemons() != 3)
        {
            failAM1Message("Lemons");
        }        
    }
    
    /**
     * Test the gallonsOfWater accessor and mutator for activity 1.
     */
    public void checkWaterAM1()
    {
        //First test
        ls.setGallonsOfWater(6);
        if (ls.getGallonsOfWater() != 6)
        {
            failAM1Message("GallonsOfWater");
        }
        
        //Second test.  Two tests prevents cheating 
        //by simply returning the tested value.
        ls.setGallonsOfWater(2);
        if (ls.getGallonsOfWater() != 2)
        {
            failAM1Message("GallonsOfWater");
        }        
    }

    /**
     * Test the cupsOfSugar accessor and mutator for activity 1.
     */
    public void checkSugarAM1()
    {
        //First test
        ls.setCupsOfSugar(4);
        if (ls.getCupsOfSugar() != 4)
        {
            failAM1Message("CupsOfSugar");
        }
        
        //Second test.  Two tests prevents cheating 
        //by simply returning the tested value.
        ls.setCupsOfSugar(8);
        if (ls.getCupsOfSugar() != 8)
        {
            failAM1Message("CupsOfSugar");
        }
    }

    /**
     * Test the emptyGlasses accessor and mutator for activity 1.
     */
    public void checkEmptyAM1()
    {
        //First test
        ls.setEmptyGlasses(3);
        if (ls.getEmptyGlasses() != 3)
        {
            failAM1Message("EmptyGlasses");
        }
        
        //Second test.  Two tests prevents cheating 
        //by simply returning the tested value.
        ls.setEmptyGlasses(5);
        if (ls.getEmptyGlasses() != 5)
        {
            failAM1Message("EmptyGlasses");
        }
    }

    /**
     * Test the glassesOfLemonade accessor and mutator for activity 1.
     */
    public void checkGlassesOfLemonadeAM1()
    {
        //First test
        ls.setGlassesOfLemonade(2);
        if (ls.getGlassesOfLemonade() != 2)
        {
            failAM1Message("GlassesOfLemonade");
        }
        
        //Second test.  Two tests prevents cheating 
        //by simply returning the tested value.
        ls.setGlassesOfLemonade(19);
        if (ls.getGlassesOfLemonade() != 19)
        {
            failAM1Message("GlassesOfLemonade");
        }
    }    

    /**
     * Test the price accessor and mutator for activity 1.
     */
    public void checkPriceAM1()
    {
        //First test
        ls.setPrice(1.2);
        if (ls.getPrice() != 1.2)
        {
            failAM1Message("Price");
        }
        
        //Second test.  Two tests prevents cheating 
        //by simply returning the tested value.
        ls.setPrice(0.75);
        if (ls.getPrice() != 0.75)
        {
            failAM1Message("Price");
        }
    }    
    
    /**
     * Test the income accessor and mutator for activity 1.
     */
    public void checkIncomeAM1()
    {
        //First test
        ls.setIncome(2.1);
        if (ls.getIncome() != 2.1)
        {
            failAM1Message("Income");
        }
        
        //Second test.  Two tests prevents cheating 
        //by simply returning the tested value.
        ls.setIncome(6.5);
        if (ls.getIncome() != 6.5)
        {
            failAM1Message("Income");
        }
    }   
    
    /**
     * Print a fail message for an accessor or mutator in
     * activity 1.
     * @param fieldName The name of the field which failed.
     */
    public void failAM1Message(String fieldName)
    {
        String fb = "";
        fb += "Accessor or mutator failed in activity 1.\n";
        fb += "Either set" + fieldName + " or get" + fieldName + ".\n";
        fail(fb);
    }
    

    
}
