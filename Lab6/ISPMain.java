import java.util.Scanner;
/**
 * This is the ISP main class and in this class contains our main method, 
 * which will perform much of the basic keyboard input from user and use 
 * objects we create in this project.
 * @author a
 * @version v
 * 
 */
public class ISPMain
{
    /**
     * This main method gets user input for package of their choice, 
     * and also the hours they use based off of
     * package selection. Also in this method we use .charAt(0) to get the 
     * first letter in the users input.
     * And finally towards the bottom of our main method we print our 
     * calculations and savings, in reference
     * to our ISP class.
     * @param args
     */
    public static void main(String [] args)
    {
        System.out.println("Package A- Plan costs $9.95 per month customer gets 10 hours of" 
        + "\n" + "access, but if customer wants more hours,$2.00 per extra hr");
        System.out.println("Package B- Plan costs $14.95 per month customer gets 20 hour of" 
        + "\n" + "access, but if customer wants more hours,$1.00 per extra hr");
        System.out.println("Package C- Plan costs $19.95 per month customer gets unlimited " 
        + "\n" + "hours of access at no additional cost per hour.");

        String usersChoice;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please select a package: ");
        double usersHours = 0;
        usersChoice = keyboard.nextLine();

        
        if (usersChoice.charAt(0) == 'A' || usersChoice.charAt(0) == 'B')
        {
            System.out.println("Please enter hours used: ");
            usersHours = keyboard.nextDouble();

        }
        ISP ispObj = new ISP(usersChoice.charAt(0), usersHours);

        System.out.println(ispObj.calculateCharges());
        System.out.println(ispObj.printSavings());
    }
}
