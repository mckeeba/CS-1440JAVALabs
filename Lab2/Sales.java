public class Sales{
    public static void main(String [] args) 
    {
        System.out.println("Enter a purchase amount: ");
        double purchaseAmount = Given.getDouble();

        double stateTax = 0.05;
        double stateTaxPaid = purchaseAmount * stateTax;

        double countyTax = 0.03;
        double countyTaxPaid = purchaseAmount * countyTax;

        double totalTax = stateTaxPaid + countyTaxPaid;

        double totalPrice =  purchaseAmount + totalTax;

        System.out.println("Amount of Purchase:    "+"\t" +"$"+ purchaseAmount+
            "\n" + "State Sales Tax Paid: "+"\t" +"$" + stateTaxPaid + "\n" + 
            "County Sales Tax Paid: "+"\t" + "$" + countyTaxPaid + "\n" + 
            "Total Sales Tax Paid: "+"\t" + "$" + totalTax + "\n" + 
            "Total Sales Price: "+"\t" + "$" + totalPrice);

    }
}
