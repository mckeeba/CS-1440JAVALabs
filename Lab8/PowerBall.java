/**
 * PowerBall.java
 * Randomly generated Powerball lottery numbers.
 */
import java.util.Random;

/**
 * This class creates a Powerball, where essentially we access random numbers 
 * from 1 to 59 without duplicates, plus one powerball number between 1 and 35,
 * with no restrictions. We will use the random class and files, along side 
 * loops to create this program.
 *
 * @author Gus Mckee
 * @version 4/5/22
 */
public class PowerBall
{
    //fields for PowerBall class 
    private Random randomGenerator;
    private int number0;
    private int number1;
    private int number2;
    private int number3;
    private int number4;
    private int powerBall;

    public static final int MAX_POWER_BALL_NUMBER = 35;
    public static final int MAX_LOTTERY_NUMBER = 59;

    /**
     * This is a no argument constructor that sets all number values to zero,
     * and initalizes randomGenerator field.
     */
    public PowerBall()
    {
        randomGenerator = new Random();
        number0 = 0;
        number1 = 0;
        number2 = 0;
        number3 = 0;
        number4 = 0;
        powerBall = 0;
    }

    /**
     * method that generates a lottery number.
     * @return lotterNumber .
     */
    public int generateLotteryNumber()
    {
        int lotteryNumber = randomGenerator.nextInt(MAX_LOTTERY_NUMBER) + 1;
        return lotteryNumber;
    }

    /**
     * method that generates a powerball number.
     * @return powerNumber .
     */

    public int generatePowerBallNumber()
    {
        int powerNumber = randomGenerator.nextInt(MAX_POWER_BALL_NUMBER) + 1;
        return powerNumber;
    }

    /**
     * @return randomGenerator .
     */
    public Random getRandomGenerator()
    {
        return randomGenerator;
    }

    /**
     * @param randomGenerator .
     */
    public void setRandomGenerator(Random randomGenerator)
    {
        this.randomGenerator = randomGenerator;
    }

    /**
     *@return number0 . 
     */
    public int getNumber0()
    {
        return number0;
    }

    /**
     * @param number0 .
     */
    public void setNumber0(int number0)
    {
        this.number0 = number0;
    }

    /**
     * @return number1 .
     */
    public int getNumber1()
    {
        return number1;
    }

    /**
     * @param number1 .
     */
    public void setNumber1(int number1)
    {
        this.number1 = number1;
    }

    /**
     * @return number2 .
     */

    public int getNumber2()
    {
        return number2;

    }

    /**
     *@param number2 . 
     */
    public void setNumber2(int number2)
    {
        this.number2 = number2;
    }

    /**
     * @return number3 .
     */
    public int getNumber3()
    {
        return number3;
    }

    /**
     * @param number3 .
     */
    public void setNumber3(int number3)
    {
        this.number3 = number3;
    }

    /**
     * @return number4 .
     */
    public int getNumber4()
    {
        return number4;
    }

    /**
     * @param number4 .
     */
    public void setNumber4(int number4)
    {
        this.number4 = number4;
    }

    /**
     * @return powerBall .
     */
    public int getPowerBall()
    {
        return powerBall;
    }

    /**
     * @param powerBall . 
     */
    public void setPowerBall(int powerBall)
    {
        this.powerBall = powerBall;
    }

    /**
     * Duplicates lottery number method checks if newNumber is equal to
     * any of the numbers stored in number variable.
     * @return newnumber = to numbers .
     * @param newNumber .
     */
    public boolean isDuplicateLotteryNumber(int newNumber)
    {
        return (newNumber == number0 || newNumber == number1 || +
            newNumber == number2 || newNumber == number3 || +
            newNumber == number4);

    }

    /**
     * method that returns a lottery number, but first checks to make sure
     * that number isn't stored in one of the number variables.
     * @return lotNumber for Poweball class.
     */
    public int nextLotteryNumber()
    {
        int lotNumber;
        do
        {
            lotNumber = generateLotteryNumber(); 
        }
        while(isDuplicateLotteryNumber(lotNumber));
        return lotNumber;
    }

    /**
     * This is the reset method which resets the powerball numbers to 0.
     */
    public void reset()
    {
        number0 = 0;
        number1 = 0;
        number2 = 0;
        number3 = 0;
        number4 = 0;
        powerBall = 0;
    }

    /**
     * generate lottery picks method, also makes a call to reset method.
     */
    public void generateLotteryPicks()
    {
        reset();
        number0 = nextLotteryNumber();
        number1 = nextLotteryNumber();
        number2 = nextLotteryNumber();
        number3 = nextLotteryNumber();
        number4 = nextLotteryNumber();

        powerBall = generatePowerBallNumber();
    }

    /**
     * This is the to string method which formats and displays our
     * powerballs in the specified format.
     * @return str .
     */
    public String toString()
    {
        String str = String.format("%02d, %02d, %02d, %02d, %02d," 
                + " Powerball %02d", 
                number0, number1, number2, number3, number4, powerBall);
        return str;
    }

}
