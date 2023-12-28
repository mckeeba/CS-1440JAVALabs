import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests the functionality of Activity 2.
 * 
 * @author Julia
 * @version 2014-03-18
 */
public class Activity1Test2
{
/**
     * Tests the no-arg constructor again since we added fields.
     */
    @Test
    public void testNoArg()
    {
        String fb = "";

        PowerBall pb = new PowerBall();

        if (pb.getRandomGenerator() == null)
        {
            fb += "No-arg constructor test failed.\n";
            fb += "The randomGenerator field should be set in the "
                + "no-arg constructor.\n";
            fail(fb);
        }
        
        assertTrue("Expected all fields to be zero.",
            0 == pb.getNumber0()
            && 0 == pb.getNumber1()
            && 0 == pb.getNumber2()
            && 0 == pb.getNumber3()
            && 0 == pb.getNumber4()
            && 0 == pb.getPowerBall());
    }

    /**
     * Test that isDuplicateLotteryNumber works correctly.
     */
    @Test
    public void testIsDuplicateLotteryNumber()
    {
        PowerBall pb = new PowerBall();
        
        pb.setNumber0(1);
        pb.setNumber1(2);
        pb.setNumber2(3);
        pb.setNumber3(4);
        pb.setNumber4(5);
        pb.setPowerBall(35);
        
        assertFalse(String.format("Did not expect %d to be a duplicate for "
            + "the ticket '01 02 03 04 05 Powerball 35'\n", 6),
            pb.isDuplicateLotteryNumber(6));
        assertFalse(String.format("Did not expect %d to be a duplicate for "
            + "the ticket '01 02 03 04 05 Powerball 35'\n", 35),
            pb.isDuplicateLotteryNumber(35));
        
        for (int i = 1; i <= 5; i++)
        {
            System.out.println(pb);
            assertTrue(String.format("Expected %d to be a duplicate for "
                + "the ticket '01 02 03 04 05 Powerball 35'\n", i),
                pb.isDuplicateLotteryNumber(i));
        }

        // Check a second set of numbers
        pb.setNumber0(6);
        pb.setNumber1(7);
        pb.setNumber2(8);
        pb.setNumber3(9);
        pb.setNumber4(10);
        pb.setPowerBall(25);
        
        assertFalse(String.format("Did not expect %d to be a duplicate for "
            + "the ticket '06 07 08 09 10 Powerball 25'\n", 1),
            pb.isDuplicateLotteryNumber(1));
        assertFalse(String.format("Did not expect %d to be a duplicate for "
            + "the ticket '06 07 08 09 10 Powerball 25'\n", 25),
            pb.isDuplicateLotteryNumber(25));
        
        for (int i = 6; i <= 10; i++)
        {
            assertTrue(String.format("Expected %d to be a duplicate for "
                + "the ticket '06 07 08 09 10 Powerball 25'\n", i),
                pb.isDuplicateLotteryNumber(i));
        }
    }

    /**
     * Test that nextLotteryNumber gives a valid lottery number
     * and that it is not a duplicate.
     */
    @Test
    public void testNextLotteryNumber()
    {
        PowerBall pb = new PowerBall();
        
        pb.setNumber0(1);
        pb.setNumber1(2);
        pb.setNumber2(3);
        pb.setNumber3(4);
        pb.setNumber4(5);
        pb.setPowerBall(35);
        
        boolean generatedPowerBall = false;
        
        int numTests = 4 * PowerBall.MAX_LOTTERY_NUMBER;
        int testLotteryNum;
        for (int i = 0; i < numTests; i++)
        {
            testLotteryNum = pb.nextLotteryNumber();
            
            assertFalse("nextLotteryNumber gave a duplicate value.",
                pb.isDuplicateLotteryNumber(testLotteryNum));
            assertTrue("nextLotteryNumber number was too small.",
                testLotteryNum >= 1);
            assertTrue("nextLotteryNumber number was too large.",
                testLotteryNum <= PowerBall.MAX_LOTTERY_NUMBER);
            
            if (testLotteryNum == pb.getPowerBall())
            {
                generatedPowerBall = true;
            }
        }
        
        if (!generatedPowerBall)
        {
            fail("nextLotteryNumber never generated the powerball number.");
        }
    }
    
    /**
     * Tests the getPowerBall and setPowerBall methods.
     */
    @Test
    public void testPowerBallAM()
    {
        PowerBall pb = new PowerBall();
        
        int[] testVals = {1, 2, 3};
        
        for (int t : testVals)
        {
            pb.setPowerBall(t);
            if (pb.getPowerBall() != t)
            {
                fail("PowerBall accessor/mutator failed when testing"
                    + "with the value " + t);
            }
        }
    }
    
    /**
     * Tests the getNumber0 and setNumber0 methods.
     */
    @Test
    public void testNumber0AM()
    {
        PowerBall pb = new PowerBall();
        
        int[] testVals = {1, 2, 3};
        
        for (int t : testVals)
        {
            pb.setNumber0(t);
            if (pb.getNumber0() != t)
            {
                fail("number0 accessor/mutator failed when testing"
                    + "with the value " + t);
            }
        }
    }
    
    /**
     * Tests the getNumber1 and setNumber1 methods.
     */
    @Test
    public void testNumber1AM()
    {
        PowerBall pb = new PowerBall();
        
        int[] testVals = {1, 2, 3};
        
        for (int t : testVals)
        {
            pb.setNumber1(t);
            if (pb.getNumber1() != t)
            {
                fail("number1 accessor/mutator failed when testing"
                    + "with the value " + t);
            }
        }
    }
    
    /**
     * Tests the getNumber2 and setNumber2 methods.
     */
    @Test
    public void testNumber2AM()
    {
        PowerBall pb = new PowerBall();
        
        int[] testVals = {1, 2, 3};
        
        for (int t : testVals)
        {
            pb.setNumber2(t);
            if (pb.getNumber2() != t)
            {
                fail("number2 accessor/mutator failed when testing"
                    + "with the value " + t);
            }
        }
    }
    
    /**
     * Tests the getNumber3 and setNumber3 methods.
     */
    @Test
    public void testNumber3AM()
    {
        PowerBall pb = new PowerBall();
        
        int[] testVals = {1, 2, 3};
        
        for (int t : testVals)
        {
            pb.setNumber3(t);
            if (pb.getNumber3() != t)
            {
                fail("number3 accessor/mutator failed when testing"
                    + "with the value " + t);
            }
        }
    }
    
    /**
     * Tests the getNumber4 and setNumber4 methods.
     */
    @Test
    public void testNumber4AM()
    {
        PowerBall pb = new PowerBall();
        
        int[] testVals = {1, 2, 3};
        
        for (int t : testVals)
        {
            pb.setNumber4(t);
            if (pb.getNumber4() != t)
            {
                fail("number4 accessor/mutator failed when testing"
                    + "with the value " + t);
            }
        }
    }
}
