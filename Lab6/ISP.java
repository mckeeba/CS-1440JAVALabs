
/**
 * This ISP class creates a program that can calculate charges depending on 
 * a customer's package 
 * selection for internet service. There are three different package 
 * selections: A,B, and C. Each
 * package has its own price, and hours used. We also print and calculate 
 * how much the customer 
 * could save if they had chosen a different package.  
 *
 * @author Gus Mckee
 * @version 3/15/22
 */
public class ISP
{
    /**
     * Fields for the ISP class.
     */
    private char pkg;
    private double hoursUsed;

    /**
     * This is an accessor for pkg field.
     * @return pkg
     */
    public char getPkg()
    {
        return pkg;
    }

    /**
     * This is a mutator for pkg field.
     * @param 
     */
    public void setPkg(char c)
    {
        pkg = c;
    }

    /**
     * This is an accessor for hoursUsed field. 
     * @return hoursUsed
     */
    public double getHoursUsed()
    {
        return hoursUsed;
    }

    /**
     * This is a mutator for hoursUsed field.
     * @param 
     */
    public void setHoursUsed(double d)
    {
        hoursUsed = d;
    }

    /**
     * This is a no args constructor for ISP.
     */
    public ISP()
    {
        pkg = 'A';
        hoursUsed = 0;

    }

    /**
     * This is a constructor for ISP
     * @param pack 
     * @param hours
     */
    public ISP(char pack, double hours)
    {
        pkg = pack;
        hoursUsed = hours;
    }

    /**
     * This is a method to calculate the charges for the different 
     * packages the customer selects using if statements. We use a 
     * combination of if statements and else if statements to also 
     * check for the price if they go over the given amount of hours and make
     * the calculation accordingly.
     * @return price
     */
    public double calculateCharges()
    {
        double price = 0D;
        if (pkg == 'A')
        {
            price = 9.95;
            if (hoursUsed > 10)
            {
                price += (hoursUsed - 10) * 2.00;
            }
        } 
        else if (pkg == 'B')
        {
            price = 14.95;
            if (hoursUsed > 20)
            {
                price += (hoursUsed - 20) * 1.00;
            }
        }
        else if (pkg == 'C')
        {
            price = 19.95;
        }
        return price;
    }

    /**
     * This is a method to print and calculate savings based on the package
     * the customer selected and hours they used with that package, which we 
     * use if and else if statements to find out the difference and print how 
     * much customer could have saved if they had selected and different 
     * package.
     * @return savings
     */
    public double printSavings()
    {
        double savings = 0;
        if (pkg == 'A')
        {
            ISP pckB = new ISP('B', hoursUsed);
            savings = calculateCharges() - pckB.calculateCharges();
            if (savings > 0)
            {
                String f = "You would have saved $%.2f by choosing package B";
                System.out.printf(f, savings);
            }
            ISP pckB2 = new ISP('C', hoursUsed);
            savings = calculateCharges() - pckB2.calculateCharges();
            if (savings > 0)
            {
                String f2 = "You would have saved $%.2f by choosing package C";
                System.out.printf(f2, savings);
            }

        }
        else if (pkg == 'B')
        {
            ISP pckC = new ISP('C', hoursUsed);
            savings = calculateCharges() - pckC.calculateCharges();
            if (savings > 0)
            {
                String f3 = "You would have saved $%.2f by choosing package C"; 
                System.out.printf(f3, savings);
            }

        }
        return savings;
    }
}
