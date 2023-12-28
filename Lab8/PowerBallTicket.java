
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException; 
/**
 *PowerBall ticket containing main method. 
 * @author Gus M.
 * @version 4/7/22
 */
public class PowerBallTicket
{

    /**
     * This is the main method for the powerBall ticket class.
     * Allows user to input a file name and how many tickets they want.
     * @param args for main method.
     * @throws IOException if need be.
     */
    public static void main(String [] args) throws IOException
    {
        //Asks user for file name, and user input is taken into the keyboard.
        System.out.println(" What is the file name?");
        Scanner file = new Scanner(System.in);
        String input = file.nextLine();

        //Asks how many powerball tickets, and get user input.
        System.out.println(" How many Powerball tickets?");
        int number = file.nextInt();
        file.nextLine();

        //creates a new powerball object from powerball class
        PowerBall powerB = new PowerBall();

        File user = new File(input);
        //Scanner userInput = new Scanner(user);

        PrintWriter pwb = new PrintWriter(user);

        /**loop will iterate amount of times based off amount of tickets
         * user inputs. Then it will generate lottery numbers, using 
         * generateLotteryPicks method. Next it will display the
         * powerball tickets in the format specified by the toString method.
         * And finally it will display the powerball tickets in the file, in 
         * format specified by toString method.
         */
        for (int i = 0; i < number; i++)
        {
            powerB.generateLotteryPicks();
            System.out.println(powerB.toString());
            pwb.println(powerB.toString());
        }
        //pwb.println(powerB.toString());
        pwb.close();

        //userInput.close();
    } 

}
