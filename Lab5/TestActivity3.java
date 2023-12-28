/**
 * Activity3.java
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
public class TestActivity3
{
    private LemonadeStand ls;
    /**
     * No-arg constructor for test class Activity3.
     */
    public TestActivity3()
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
     * Test method makeLemonade activity 3 part 1.  Make sure makeLemonade will
     * not make anything if there is not enough ingredients.
     */
    @Test
    public void testMakeLemonadeActivity3Part1()
    {
        checkMakeLemonadeActivity3Part1Test1();
        checkMakeLemonadeActivity3Part1Test2();
        checkMakeLemonadeActivity3Part1Test3();
        checkMakeLemonadeActivity3Part1Test4();        
    }
    
    /**
     * Test method makeLemonade activity 3 part 3.  Return the number of
     * glasses made. Should be either 8 if there are enough ingredients 
     * or 0 if there are not enough ingredients.
     */
    @Test
    public void testMakeLemonadeActivity3Part3()
    {
        String fb = "";
        int cupsMade;
        ls = new LemonadeStand(6, 2, 2, 16, 1.1);
        cupsMade = ls.makeLemonade();
        if (cupsMade != 8)        
        {
            fb += "Test of method makeLemonade failed for activty 3 part 3.\n";
            fb += "The following code was executed:\n\n";
            fb += "\t LemonadeStand ls = new LemonadeStand(6, 2," 
                + " 2, 16, 1.1);\n";
            fb += "\t int cupsMade = ls.makeLemonade();\n\n";
            fb += "cupsMade should have been 8 since there was" 
                + " enough ingredients.\n";
            fb += "Your makeLemonade returned " + cupsMade + ".\n";
            fail(fb);
        }      
        
        ls = new LemonadeStand(5, 2, 2, 16, 1.1);
        cupsMade = ls.makeLemonade();
        if (cupsMade != 0)        
        {
            fb += "Test of method makeLemonade failed for activty 3 part 2.\n";
            fb += "The following code was executed:\n\n";
            fb += "\t LemonadeStand ls = new LemonadeStand(5, 2, 2," 
                + " 16, 1.1);\n";
            fb += "\t int cupsMade = ls.makeLemonade();\n\n";
            fb += "cupsMade should have been 0 since there was" 
                + " enough lemons.\n";
            fb += "Your makeLemonade returned " + cupsMade + ".\n";
            fail(fb);
        }          
    } 
 
    /**
     * Test method sellLemonade activity 3 part 3.  Make sure makeLemonade will
     * not make anything if there is not enough ingredients.
     */
    @Test
    public void testSellLemonadeActivity3Part3()
    {
        
        String fb = "";
        int cupsSold1 = 0;
        int cupsSold2 = 0;
        ls = new LemonadeStand(7, 2, 2, 9, 1.0);

        //Sell 9 cups of lemonade. The first sellLemonade should make 8 cups.
        //The last sellLemonade should try to make more, but there should not 
        //enough lemons left.
        cupsSold1 = ls.sellLemonade();       
        ls.sellLemonade();       
        ls.sellLemonade();       
        ls.sellLemonade();       
        ls.sellLemonade();       
        ls.sellLemonade();       
        ls.sellLemonade();       
        ls.sellLemonade();       
        cupsSold2 = ls.sellLemonade();       

        if (cupsSold1 != 1)
        {
            fb += "Test of method sellLemonade failed for activty 3 part 3.\n";
            fb += "The following code was executed:\n\n";
            fb += "\t LemonadeStand ls = new LemonadeStand(7, 2, 2," 
                + " 9, 1.0);\n";
            fb += "\t int cupsSold = ls.sellLemonade();\n\n";              
            fb += "Your sellLemonade should have made 8 cups and sold 1.\n";
            fb += "cupsSold should be 1, yours was " + cupsSold1 + ".\n";
            fail(fb);
        } 
        else if  (cupsSold2 != 0)
        {
            fb += "Test of method sellLemonade failed for activty 3 part 3.\n";
            fb += "The following code was executed:\n\n";
            fb += "\t LemonadeStand ls = new LemonadeStand(7, 2, 2," 
                + " 9, 1.0);\n";
            fb += "\t ls.sellLemonade();\n";              
            fb += "\t ls.sellLemonade();\n";              
            fb += "\t ls.sellLemonade();\n";              
            fb += "\t ls.sellLemonade();\n";              
            fb += "\t ls.sellLemonade();\n";              
            fb += "\t ls.sellLemonade();\n";              
            fb += "\t ls.sellLemonade();\n";              
            fb += "\t ls.sellLemonade();\n";              
            fb += "\t int cupsSold = ls.sellLemonade();\n\n";              
            fb += "Your sellLemonade should have made 8 cups and sold 8.\n";
            fb += "The last sellLemonade should have tried to" 
                + " make 8 more cups\n";
            fb += "and failed since there would not be enough lemons left.\n";
            fb += "cupsSold should be 0, yours was " + cupsSold2 + ".\n";
            fail(fb);            
        }        
        else if (ls.getGlassesOfLemonade() != 0 || ls.getIncome() != 8.0)
        {
            fb += "Test of method sellLemonade failed for activty 3 part 3.\n";
            fb += "The following code was executed:\n\n";
            fb += "\t LemonadeStand ls = new LemonadeStand(7, 2, 2," 
                + " 9, 1.0);\n";
            fb += "\t ls.sellLemonade();\n";
            fb += "\t ls.sellLemonade();\n";
            fb += "\t ls.sellLemonade();\n";
            fb += "\t ls.sellLemonade();\n";
            fb += "\t ls.sellLemonade();\n";
            fb += "\t ls.sellLemonade();\n";
            fb += "\t ls.sellLemonade();\n";
            fb += "\t ls.sellLemonade();\n";
            fb += "\t ls.sellLemonade();\n\n";
            fb += "Your sellLemonade should have made 8 cups and sold 8.\n";
            fb += "The last sellLemonade should have tried to make" 
                + " 8 more cups\n";
            fb += "and failed since there would not be enough lemons left.\n";
            fb += "Glasses of lemonade should be 0 and income should be 8.\n";
            fb += "You show " + ls.getGlassesOfLemonade() 
                + " glasses of lemonad and and income of " 
                + ls.getIncome() + ".\n";
            fail(fb);
        }                 
    }    

    
    
    /** 
     * Test to see that sell lemonade works properls. It should check to see
     * if there is enough lemonade.  If not, make lemonade.  If it couldn't
     * make lemonade because of lack of ingredients, then don't sell any.
     */
    @Test
    public void testSellLemonadeActivity3Part2()
    {
        String fb = "";
        ls = new LemonadeStand(7, 2, 2, 9, 1.25);
        ls.sellLemonade();       
        
        if (ls.getGlassesOfLemonade() != 7 || ls.getIncome() != 1.25)
        {
            fb += "Test of method sellLemonade failed for activty 3 part 2.\n";
            fb += "The following code was executed:\n\n";
            fb += "\t LemonadeStand ls = new LemonadeStand(7, 2, 2," 
                + " 9, 1.25);\n";
            fb += "\t ls.sellLemonade();\n\n";
            fb += "Your sellLemonade method should have called ";
            fb += "makeLemonade and sold a glass, but it didn't.\n";
            fb += "Glasses of lemonade should be 7 and income should ";
            fb += "be 1.25.\n";
            fail(fb);
        }                
    }    
    
    /**
     * Test to see if makeLemonade will make lemonade without enough lemons.
     */    
    public void checkMakeLemonadeActivity3Part1Test1()
    {
        String fb = "";
        ls = new LemonadeStand(5, 2, 2, 16, 1.1);
        ls.makeLemonade();
        if (ls.getGlassesOfLemonade() != 0
            || ls.getLemons() != 5
            || ls.getGallonsOfWater() != 2
            || ls.getCupsOfSugar() != 2
            || ls.getEmptyGlasses() != 16)
        {
            fb += "Test of method makeLemonade failed for activty 3.\n";
            fb += "The following code was executed:\n\n";
            fb += "\t LemonadeStand ls = new LemonadeStand(5, 2, 2," 
                + " 16, 1.1);\n";
            fb += "\t ls.makeLemonade();\n\n";
            fb += "Fields were modified even though there ";
            fb += "was not enough lemons available to make lemonade.\n";
            fail(fb);
        }
    }   
    
    /**
     * Test to see if makeLemonade will make lemonade without enough water.
     */
    public void checkMakeLemonadeActivity3Part1Test2()
    {
        String fb = "";
        ls = new LemonadeStand(6, 0, 2, 16, 1.1);
        ls.makeLemonade();
        if (ls.getGlassesOfLemonade() != 0
            || ls.getLemons() != 6
            || ls.getGallonsOfWater() != 0
            || ls.getCupsOfSugar() != 2
            || ls.getEmptyGlasses() != 16)
        {
            fb += "Test of method makeLemonade failed for activty 3.\n";
            fb += "The following code was executed:\n\n";
            fb += "\t LemonadeStand ls = new LemonadeStand(6, 0, 2," 
                + " 16, 1.1);\n";
            fb += "\t ls.makeLemonade();\n\n";
            fb += "Fields were modified even though there ";
            fb += "was not enough water available to make lemonade.\n";
            fail(fb);
        }
    }    
    
    /**
     * Test to see if makeLemonade will make lemonade without enough sugar.
     */
    public void checkMakeLemonadeActivity3Part1Test3()
    {
        String fb = "";
        ls = new LemonadeStand(6, 2, 0, 16, 1.1);
        ls.makeLemonade();

        if (ls.getGlassesOfLemonade() != 0
            || ls.getLemons() != 6
            || ls.getGallonsOfWater() != 2
            || ls.getCupsOfSugar() != 0
            || ls.getEmptyGlasses() != 16)
        {
            fb += "Test of method makeLemonade failed for activty 3.\n";
            fb += "The following code was executed:\n\n";
            fb += "\t LemonadeStand ls = new LemonadeStand(6, 2, 0,"
                + "16, 1.1);\n";
            fb += "\t ls.makeLemonade();\n\n";
            fb += "Fields were modified even though there ";
            fb += "was not enough sugar to make lemonade.\n";
            fail(fb);
        }
    }    

    /**
     * Test to see if makeLemonade will make lemonade without enough sugar.
     */
    public void checkMakeLemonadeActivity3Part1Test4()
    {
        String fb = "";
        ls = new LemonadeStand(6, 2, 2, 7, 1.1);
        ls.makeLemonade();
        if (ls.getGlassesOfLemonade() != 0
            || ls.getLemons() != 6
            || ls.getGallonsOfWater() != 2
            || ls.getCupsOfSugar() != 2
            || ls.getEmptyGlasses() != 7)
        {
            fb += "Test of method makeLemonade failed for activty 3.\n";
            fb += "The following code was executed:\n\n";
            fb += "\t LemonadeStand ls = new LemonadeStand(6, 2,"
                + "2, 7, 1.1);\n";
            fb += "\t ls.makeLemonade();\n\n";
            fb += "Fields were modified even though there ";
            fb += "was not enough empty glasses to make lemonade.\n";
            fail(fb);
        }
    }     
    
}
