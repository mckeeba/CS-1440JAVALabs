/**
 * Activity4.java
 */
 
import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Activity4 tests lab 5 programming steps for this activity.
 * 
 * @author Joel Swanson
 * @version 02.08.2014
 */
public class TestActivity4
{
    private LemonadeStand ls;
    /**
     * No-arg constructor for test class TestLab05Activity4.
     */
    public TestActivity4()
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
     * Test method sellMoreLemonade.  
     * Sell 6.  8 currently made, so sell 6.
     * Glasses of lemonade remaining should be 2. 
     * Income should be 6.0.
     * Glasses sold the last time should be 6.
     */   
    @Test
    public void sellMoreLemonadeTest1()
    {
        
        String fb = "";
        int cupsSold = 0;
        ls = new LemonadeStand(15, 2, 2, 9, 1.0);
        ls.makeLemonade();
        cupsSold = ls.sellMoreLemonade(6);       

        if (cupsSold != 6 || ls.getGlassesOfLemonade() != 2  
            || ls.getIncome() != 6.0)
        {
            fb += "Test of method sellMoreLemonade failed for activty 4.\n";
            fb += "The following code was executed:\n\n";
            fb += "\t LemonadeStand ls = new LemonadeStand(15,2,2,9,1.0);\n";
            fb += "\t ls.makeLemonade();\n\n";              
            fb += "\t int cupsSold = ls.sellMoreLemonade(6);\n\n";              
            fb += "Your sellMoreLemonade should have sold 6 glasses.\n";
            fb += "cupsSold should be 6, yours was " + cupsSold + ".\n";
            fb += "glassesOfLemonade should be 2, yours was " 
                + ls.getGlassesOfLemonade() + ".\n";
            fb += "income should be 6.0, yours was " 
                + ls.getIncome() + ".\n";                
            fail(fb);
        } 
    }        

    /**
     * Test method sellMoreLemonade.  
     * Sell 6.  0 currently made, make 8 and sell 6.
     * Glasses of lemonade remaining should be 2. 
     * Income should be 6.0.
     * Glasses sold the last time should be 6.
     */   
    @Test
    public void sellMoreLemonadeTest2()
    {    
        String fb = "";
        int cupsSold = 0;
        ls = new LemonadeStand(15, 2, 2, 9, 1.0);

        cupsSold = ls.sellMoreLemonade(6);       

        if (cupsSold != 6 || ls.getGlassesOfLemonade() != 2  
            || ls.getIncome() != 6.0)
        {
            fb += "Test of method sellMoreLemonade failed for activty 4.\n";
            fb += "The following code was executed:\n\n";
            fb += "\t LemonadeStand ls = new LemonadeStand(15,2,2,9,1.0);\n";
            fb += "\t int cupsSold = ls.sellMoreLemonade(6);\n\n";              
            fb += "Your sellMoreLemonade should have made 8 cups and sold 6.\n";
            fb += "cupsSold should be 6, yours was " + cupsSold + ".\n";
            fb += "glassesOfLemonade should be 2, yours was " 
                + ls.getGlassesOfLemonade() + ".\n";
            fb += "income should be 6.0, yours was " 
                + ls.getIncome() + ".\n";                
            fail(fb);
        } 
    }  
    
    /**
     * Test method sellMoreLemonade.  
     * Sell 6.  0 currently made, make 8 and sell 6.
     * Sell 6.  2 currently made, make 8 and sell 6.
     * Glasses of lemonade remaining should be 4. 
     * Income should be 12.0.
     * Glasses sold the last time should be 6.
     */    
    @Test
    public void sellMoreLemonadeTest3()
    {
        String fb = "";
        int cupsSold = 0;
        ls = new LemonadeStand(15, 2, 2, 16, 1.0);

        ls.sellMoreLemonade(6);
        cupsSold = ls.sellMoreLemonade(6);       

        if (cupsSold != 6 || ls.getGlassesOfLemonade() != 4  
            || ls.getIncome() != 12.0)
        {
            fb += "Test of method sellMoreLemonade failed for activty 4.\n";
            fb += "The following code was executed:\n\n";
            fb += "\t LemonadeStand ls = new LemonadeStand(15,2,2,16,1.0);\n";
            fb += "\t ls.sellMoreLemonade(6);\n";              
            fb += "\t int cupsSold = ls.sellMoreLemonade(6);\n\n";              
            fb += "The first sellMoreLemonade should have made 8 cups" 
                + " and sold 6.\n";
            fb += "The second sellMoreLemonade should have made 8 cups" 
                + " and sold 6.\n";
            fb += "cupsSold should be 6, yours was " + cupsSold + ".\n";
            fb += "glassesOfLemonade should be 4, yours was " 
                + ls.getGlassesOfLemonade() + ".\n";
            fb += "income should be 12.0, yours was " 
                + ls.getIncome() + ".\n";                
            fail(fb);
        } 
    }
    
    /**
     * Test method sellMoreLemonade.  
     * Sell 6.  0 currently made, make 8 and sell 6.
     * Sell 6.  2 currently made, make 8 and sell 6.
     * Sell 6.  4 currently made, cant make more so sell 4.
     * Glasses of lemonade remaining should be 0. 
     * Income should be 16.0
     * Glasses sold the last time should be 4.
     */    
    @Test
    public void sellMoreLemonadeTest4()
    {
        String fb = "";
        int cupsSold = 0;
        ls = new LemonadeStand(15, 2, 2, 16, 1.0);

        ls.sellMoreLemonade(6);
        ls.sellMoreLemonade(6);       
        cupsSold = ls.sellMoreLemonade(6);       

        if (cupsSold != 4 || ls.getGlassesOfLemonade() != 0  
            || ls.getIncome() != 16.0)
        {
            fb += "Test of method sellMoreLemonade failed for activty 4.\n";
            fb += "The following code was executed:\n\n";
            fb += "\t LemonadeStand ls = new LemonadeStand(15,2,2,16,1.0);\n";
            fb += "\t ls.sellMoreLemonade(6);\n";              
            fb += "\t int cupsSold = ls.sellMoreLemonade(6);\n\n";              
            fb += "The first sellMoreLemonade should have made 8 cups" 
                + " and sold 6.\n";
            fb += "The second sellMoreLemonade should have made 8 cups" 
                + " and sold 6.\n";
            fb += "The third sellMoreLemonade sold the" 
                + " remaining 4 glasses\n";                
            fb += "since there are not enough lemons left" 
                + " to make more.\n";                
            fb += "cupsSold should be 4, yours was " + cupsSold + ".\n";
            fb += "glassesOfLemonade should be 0, yours was " 
                + ls.getGlassesOfLemonade() + ".\n";
            fb += "income should be 16.0, yours was " 
                + ls.getIncome() + ".\n";                
            fail(fb);
        } 
    }       

}
