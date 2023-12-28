import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.Test;

/**
 * Tests the functionality of Activity 1.
 * 
 * @author Julia
 * @version 2014-03-18
 */
public class Activity1Test1
{

    /**
     * Tests the no-arg constructor.
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
    }

    /**
     * Tests the values of the constants.
     */
    @Test
    public void testConstants()
    {
        String fb = "";
        boolean failed = false;

        if (PowerBall.MAX_LOTTERY_NUMBER == 0)
        {
            fb += "The MAX_LOTTERY_NUMBER constant was not set.\n";
            failed = true;
        }

        else if (PowerBall.MAX_LOTTERY_NUMBER != 59)
        {
            fb += "The MAX_LOTTERY_NUMBER constant was set incorrectly.\n";
            failed = true;
        } 

        if (PowerBall.MAX_POWER_BALL_NUMBER == 0)
        {
            fb += "The MAX_POWER_BALL_NUMBER constant was not set.\n";
            failed = true;
        }
        else if (PowerBall.MAX_POWER_BALL_NUMBER != 35)
        {
            fb += "The MAX_POWER_BALL_NUMBER constant was set incorrectly.\n";
            failed = true;
        }

        if (failed)
        {
            fail(fb);
        }
    }
    
    /**
     * Tests the getRandomGenerator and setRandomGenerator methods.
     */
    @Test
    public void testRandomGeneratorAM()
    {
        PowerBall pb = new PowerBall();
        
        Random[] testVals = {new Random(), new Random(), new Random()};
        
        for (Random t : testVals)
        {
            pb.setRandomGenerator(t);
            // Am intentionally checking object identity, not
            // object equality.
            if (pb.getRandomGenerator() != t)
            {
                fail("randomGenerator accessor/mutator failed when testing"
                    + "with the value " + t);
            }
        }
    }

    /**
     * Tests that the generateLotteryNumber method produces numbers
     * between 1 and max number, inclusive.
     */
    @Test
    public void testGenerateLotteryNumber()
    {
        PowerBall pb = new PowerBall();

        boolean generatedZero = false;
        boolean generatedNegative = false;
        boolean generatedMaxPlusOne = false;
        boolean generatedTooLarge = false;

        boolean generatedOne = false;
        boolean generatedMaxNumber = false;

        int numTests = 4 * PowerBall.MAX_LOTTERY_NUMBER;
        int testLotteryNum;
        for (int i = 0; i < numTests; i++)
        {
            testLotteryNum = pb.generateLotteryNumber();

            if (testLotteryNum == 0)
            {
                generatedZero = true;
            }
            else if (testLotteryNum == 1)
            {
                generatedOne = true;
            }
            else if (testLotteryNum == PowerBall.MAX_LOTTERY_NUMBER)
            {
                generatedMaxNumber = true;
            }
            else if (testLotteryNum == (PowerBall.MAX_LOTTERY_NUMBER + 1))
            {
                generatedMaxPlusOne = true;
            }
            else if (testLotteryNum < 0)
            {
                generatedNegative = true;
            }
            else if (testLotteryNum > PowerBall.MAX_LOTTERY_NUMBER + 1)
            {
                generatedTooLarge = true;
            }
        }

        if (generatedNegative || generatedTooLarge)
        {
            fail("generateLotteryNumber generated numbers out of bounds.");
        }

        if (generatedZero || generatedMaxPlusOne)
        {
            fail("generatedLotteryNumber generated a number that was "
                + "one outside of bounds.");
        }

        if (!(generatedOne && generatedMaxNumber))
        {
            fail("generateLotteryNumber never generated 1 or max.");
        }
    }
    
    /**
     * Tests that the generatePowerBallNumber method produces numbers
     * between 1 and max number, inclusive.
     */
    @Test
    public void testGeneratePowerBallNumber()
    {
        PowerBall pb = new PowerBall();

        boolean generatedZero = false;
        boolean generatedNegative = false;
        boolean generatedMaxPlusOne = false;
        boolean generatedTooLarge = false;

        boolean generatedOne = false;
        boolean generatedMaxNumber = false;

        int numTests = 4 * PowerBall.MAX_POWER_BALL_NUMBER;
        int testLotteryNum;
        for (int i = 0; i < numTests; i++)
        {
            testLotteryNum = pb.generatePowerBallNumber();

            if (testLotteryNum == 0)
            {
                generatedZero = true;
            }
            else if (testLotteryNum == 1)
            {
                generatedOne = true;
            }
            else if (testLotteryNum == PowerBall.MAX_POWER_BALL_NUMBER)
            {
                generatedMaxNumber = true;
            }
            else if (testLotteryNum == (PowerBall.MAX_POWER_BALL_NUMBER + 1))
            {
                generatedMaxPlusOne = true;
            }
            else if (testLotteryNum < 0)
            {
                generatedNegative = true;
            }
            else if (testLotteryNum > PowerBall.MAX_POWER_BALL_NUMBER + 1)
            {
                generatedTooLarge = true;
            }
        }

        if (generatedNegative || generatedTooLarge)
        {
            fail("generatePowerBallNumber generated numbers out of bounds.");
        }

        if (generatedZero || generatedMaxPlusOne)
        {
            fail("generatePowerBallNumber generated a number that was "
                + "one outside of bounds.");
        }

        if (!(generatedOne && generatedMaxNumber))
        {
            fail("generatePowerBallNumber never generated 1 or max.");
        }
    }
}
