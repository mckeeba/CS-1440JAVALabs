import java.util.Scanner;
/**
 * Activity1PayStub class is part of Lab 3 and
 * creates a simple pay stub.
 *
 * @author (your name)
 * @version (date)
 */
public class Activity1PayStub
{
    public static final double OVERTIME_RATE = 1.5;
    public static final double WITHOLDING_PERCENT = 0.10;
    public static final double FEDERALTAX_PERCENT = 0.20;
    /**
     * It all starts with the main method.
     *
     * @param args command-line arguments (not used)
     */

    public static void main(String[] args)
    {
        /**
         * The code below creates a scanner object which is then used
         * to allow the user to input what is prompted in the code.
         * In this case the following inputs are what the user must enter
         * (employee name, SSN with hyphens, number of regular hours worked,
         * overtime hours worked, and hourly pay rate)
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter employee name: ");
        String employeeName = keyboard.nextLine();
        System.out.print("Enter employee SSN (incl. hyphens): ");
        String socialSecurityNumber = keyboard.nextLine();
        System.out.print("Enter number of regular hours worked: ");
        int regularHours = keyboard.nextInt();
        System.out.print("Enter number of overtime hours worked: ");
        int overtimeHours = keyboard.nextInt();
        System.out.print("Enter hourly pay rate: ");
        double hourlyPayRate = keyboard.nextDouble();
        /**
         * The code below puts together the arithmetic necessary to calculate the 
         * inputs the user gave us to later display a proper paystub.
         */

        double regularPay = regularHours * hourlyPayRate;
        double overtimeRate = (hourlyPayRate * OVERTIME_RATE);
        double overtimePay = overtimeHours * overtimeRate;
        double grossPay = regularPay + overtimePay;
        double socialSecurityWitholding = grossPay * WITHOLDING_PERCENT;
        double federalTax = (grossPay - grossPay * WITHOLDING_PERCENT) 
            * FEDERALTAX_PERCENT;
        double netPay = grossPay - federalTax - socialSecurityWitholding;
        /**
         * The following code below formats the following print statements to display
         * a paystub in the form instructed.
         * Using %-37s,%-8d,%-11s, and %-8.2f is to display prices and rates using a certain
         * amount of spacing, proper data type assignment and up to 2 decimal places.
         * The underscores are the borders of the paystubs.
         */
        String format = "Name: %-37s SSN: %-11s\n";
        System.out.printf("__________________________________" 
            + "__________________________\n");
        System.out.printf(format, employeeName, socialSecurityNumber);
        String format2 = "Regular Hours: %-8d " 
            + "Reg Rate: $%-8.2f Reg Pay: $%-8.2f\n";
        System.out.printf(format2, regularHours, hourlyPayRate, regularPay);
        String format3 = "Overtime Hours: %-7d " 
            + "OT Rate: $%-9.2f OT Pay: $%-8.2f\n";
        System.out.printf(format3, overtimeHours, overtimeRate, overtimePay);
        String format4 = "Gross Pay: $%-8.2f\n";
        System.out.printf(format4, grossPay);
        String format5 = "SS Withholding: $%-8.2f\n";
        System.out.printf(format5, socialSecurityWitholding);
        String format6 = "Federal Tax: $%-8.2f\n";
        System.out.printf(format6, federalTax);
        String format7 = "Net Pay: $%-8.2f\n";
        System.out.printf(format7, netPay);
        System.out.printf("_______________________________" 
            + "_________________________________\n");

        
        
        
        
        
        
    }
}
