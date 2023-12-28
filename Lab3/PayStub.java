import java.util.Scanner;
/**
 * PayStub class is part of Lab 3 and
 * creates a pay stub, but uses fields, a constructor, as well as multiple methods to do
 * so.
 *
 * @author (Gus Mckee)
 * @version (2/8/22)
 */
public class PayStub
{
    /**
     * Constructor that calls the following three methods.
     */
    public PayStub(Scanner keyboard){
        getInput(keyboard);
        calculate();
        keyboard.nextLine();
    }
    /**This is the accessor for overtime hours, which is retrieved from 
     * one of our methods.
     * 
     */
    public int getOvertimeHours()
    {
        return overtimeHours;
    }
    /**This is the mutator for overtime hours, it also has an if statement
     * which is used to make sure the right value is input and then makes 
     * the calculation for it.
     */
    public void setOvertimeHours(int newValue){
        if (newValue >= 0) {
            overtimeHours = newValue;
            calculate();
        }
     }
    //A method for calculating the hourly pay rate and getting the right value input
    public void hourlyPayRate(double newValue){
        if (newValue >= 0){
            hourlyPayRate = newValue;
            calculate();
        }
    }
    //A mutator for hourly pay rate
    public void sethourlyPayRate(double newValue){
        if (newValue >=0) {
            hourlyPayRate = newValue;
            calculate();
        }   
    }
    //An accessor for Gross pay
    public double getGrossPay(){
        return grossPay;
     }

    public static final double OVERTIME_RATE = 1.5;
    public static final double WITHOLDING_PERCENT = 0.10;
    public static final double FEDERALTAX_PERCENT = 0.20;
    //Fields declared for my getInput method.
    private String employeeName;
    private String socialSecurityNumber;
    private int regularHours;
    private int overtimeHours;
    private double hourlyPayRate;

    //Fields declared for my calculation method.

    private double regularPay;
    private double overtimeRate;
    private double overtimePay; 
    private double grossPay;
    private double socialSecurityWitholding;
    private double federalTax; 
    private double netPay;

    /**
     * It all starts with the main method.
     *This main method creates the Scanner object to allow for user input.
     *The main method also has the following 3 Paystub instances created and then
     *we display the full paystub for luke.
     *Also we make a print statement below to indicate the new Gross Pay after the
     *adjusted hourly rate mutator is called.
     *
     *@param args command-line arguments (not used)
     */

    public static void main(String[] args)
    {

        Scanner keyboard = new Scanner(System.in);
        PayStub darthVader = new PayStub(keyboard);
        PayStub luke = new PayStub(keyboard);
        PayStub grogu = new PayStub(keyboard);
        
        luke.printPayStub();
        luke.sethourlyPayRate(20);
        
        
        
        System.out.printf("New gross pay: $%-8.2f\n", luke.getGrossPay());
        
        

        
    }
    private void getInput(Scanner keyboard){
        /**
         * The code below creates a scanner object which is then used
         * to allow the user to input what is prompted in the code.
         * In this case the following inputs are what the user must enter
         * (employee name, SSN with hyphens, number of regular hours worked,
         * overtime hours worked, and hourly pay rate)
         */
        System.out.print("Enter employee name: ");
        employeeName = keyboard.nextLine();
        System.out.print("Enter employee SSN (incl. hyphens): ");
        socialSecurityNumber = keyboard.nextLine();
        System.out.print("Enter number of regular hours worked: ");
        regularHours = keyboard.nextInt();
        System.out.print("Enter number of overtime hours worked: ");
        overtimeHours = keyboard.nextInt();
        System.out.print("Enter hourly pay rate: ");
        hourlyPayRate = keyboard.nextDouble();

    }
    private void calculate(){
        /**
         * The code below puts together the arithmetic necessary to calculate the 
         * inputs the user gave us to later display a proper paystub.
         */

        regularPay = regularHours * hourlyPayRate;
        overtimeRate = (hourlyPayRate * OVERTIME_RATE);
        overtimePay = overtimeHours * overtimeRate;
        grossPay = regularPay + overtimePay;
        socialSecurityWitholding = grossPay * WITHOLDING_PERCENT;
        federalTax = (grossPay - grossPay * WITHOLDING_PERCENT) 
        * FEDERALTAX_PERCENT;
        netPay = grossPay - federalTax - socialSecurityWitholding;

    }
    public void printPayStub(){
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
