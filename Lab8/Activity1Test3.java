import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests the functionality of Activity 3.
 * 
 * @author Julia
 * @version 2014-03-18
 */
public class Activity1Test3
{
    /**
     * Tests the reset method.
     */
    @Test
    public void testReset()
    {
        PowerBall pb = new PowerBall();
        
        pb.setNumber0(1);
        pb.setNumber1(2);
        pb.setNumber2(3);
        pb.setNumber3(4);
        pb.setNumber4(5);
        pb.setPowerBall(35);
        
        pb.reset();
        
        assertEquals("Expected reset to reset all fields to 0",
            0, pb.getNumber0());
        assertEquals("Expected reset to reset all fields to 0",
            0, pb.getNumber1());
        assertEquals("Expected reset to reset all fields to 0",
            0, pb.getNumber2());
        assertEquals("Expected reset to reset all fields to 0",
            0, pb.getNumber3());
        assertEquals("Expected reset to reset all fields to 0",
            0, pb.getNumber4());
        assertEquals("Expected reset to reset all fields to 0",
            0, pb.getPowerBall());
    }
    
    /**
     * Test the generateLotteryPicks method.
     */
    @Test
    public void testGenerateLotteryPicks()
    {
        PowerBall pb = new PowerBall();
        
        assertTrue("Expected all fields to be zero at initialization.",
            0 == pb.getNumber0()
            && 0 == pb.getNumber1()
            && 0 == pb.getNumber2()
            && 0 == pb.getNumber3()
            && 0 == pb.getNumber4()
            && 0 == pb.getPowerBall());
        
        int numTests = 4 * PowerBall.MAX_LOTTERY_NUMBER;
        for (int i = 0; i < numTests; i++)
        {
            pb.generateLotteryPicks();
            assertTrue("Expected no fields to be zero after generating picks.",
                0 != pb.getNumber0()
                && 0 != pb.getNumber1()
                && 0 != pb.getNumber2()
                && 0 != pb.getNumber3()
                && 0 != pb.getNumber4()
                && 0 != pb.getPowerBall());

            // Check that there are no duplicates.
            assertTrue("Lottery numbers contained duplicates.",
                pb.getNumber0() != pb.getNumber1()
                && pb.getNumber0() != pb.getNumber2()
                && pb.getNumber0() != pb.getNumber3()
                && pb.getNumber0() != pb.getNumber4()
                && pb.getNumber1() != pb.getNumber2()
                && pb.getNumber1() != pb.getNumber3()
                && pb.getNumber1() != pb.getNumber4()
                && pb.getNumber2() != pb.getNumber3()
                && pb.getNumber2() != pb.getNumber4()
                && pb.getNumber3() != pb.getNumber4());

            // Check that lottery numbers are within range.
            assertTrue("number0 was too small.",
                pb.getNumber0() >= 1);
            assertTrue("number0 was too large.",
                pb.getNumber0() <= PowerBall.MAX_LOTTERY_NUMBER);
            assertTrue("number1 was too small.",
                pb.getNumber1() >= 1);
            assertTrue("number1 was too large.",
                pb.getNumber1() <= PowerBall.MAX_LOTTERY_NUMBER);
            assertTrue("number2 was too small.",
                pb.getNumber2() >= 1);
            assertTrue("number2 was too large.",
                pb.getNumber2() <= PowerBall.MAX_LOTTERY_NUMBER);
            assertTrue("number3 was too small.",
                pb.getNumber3() >= 1);
            assertTrue("number3 was too large.",
                pb.getNumber3() <= PowerBall.MAX_LOTTERY_NUMBER);
            assertTrue("number4 was too small.",
                pb.getNumber4() >= 1);
            assertTrue("number4 was too large.",
                pb.getNumber4() <= PowerBall.MAX_LOTTERY_NUMBER);

            // Check that powerball is within range.
            assertTrue("powerBall number was too small.",
                pb.getPowerBall() >= 1);
            assertTrue("powerBall number was too large.",
                pb.getPowerBall() <= PowerBall.MAX_POWER_BALL_NUMBER);

        }
    }
    
    /**
     * Test the toString method.
     */
    @Test
    public void testToString()
    {
        PowerBall pb = new PowerBall();
        
        pb.setNumber0(1);
        pb.setNumber1(2);
        pb.setNumber2(3);
        pb.setNumber3(4);
        pb.setNumber4(5);
        pb.setPowerBall(35);
        
        assertEquals("01, 02, 03, 04, 05, Powerball 35", pb.toString());
        
        // Check a second set of numbers
        pb.setNumber0(6);
        pb.setNumber1(7);
        pb.setNumber2(8);
        pb.setNumber3(9);
        pb.setNumber4(10);
        pb.setPowerBall(25);
        
        assertEquals("06, 07, 08, 09, 10, Powerball 25", pb.toString());
    }

}
