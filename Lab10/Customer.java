/**
 * Customer.java
 * 
 */

//Put any imports below this line.
 
/**
 * Short, one-line description of Customer class here.
 * 
 * Optionally, include a paragraph that provides a more 
 * detailed description.
 *
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Customer
{
    private String name;
    private String ssn;

    /**
     * Copy constructor.
     * @param customer of type customer.
     */
    public Customer(Customer customer)
    {
        name = customer.name;
        ssn = customer.ssn;
    }

    /**
     * constructor containing parameters for  
     */
    public Customer(String name, String ssn)
    {
        setName(name);
        setSsn(ssn);
    }

    public String getName()
    {
        return name;
    }

    public String getSsn()
    {
        return ssn;
    }

    public void setName(String name)
    {
        if(name == null)
        {
            this.name = "";

        }
        else
        {
            this.name = name;
        }
    }

    public void setSsn(String ssn)
    {
        if(ssn == null)
        {
            this.ssn = "";

        }
        else
        {
            this.ssn = ssn;
        }
    }

    public String toString()
    {
        String str = "Name: " + name + " - SSN: " + ssn;
        return str;
    }

}
