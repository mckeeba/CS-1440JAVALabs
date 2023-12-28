/**
 * Ticket.java
 * 
 */


//Put any imports below this line.
 
 
/**
 * Short, one-line description of Ticket class here.
 * 
 * Optionally, include a paragraph that provides a more 
 * detailed description.
 *
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ticket
{
    private Customer customer;
    private Flight flight;
    
    
    /**
     * No parameter constructor for objects of class Ticket.
     */
    public Ticket(Customer customer, Flight flight)
    {
      this.customer = new Customer(customer);
      this.flight = flight.copy();
      
    }
    
    public Customer getCustomer()
    {
        return new Customer(customer);
    }
    
    public Flight getFlight()
    {
        return flight.copy();
    }
    
    public void setCustomer(Customer customer)
    {
        this.customer = new Customer(customer);
    }
    
    public void setFlight(Flight flight)
    {
        this.flight = flight.copy();
    }
    
    /**
     * An example of a method.  Describe this method here.
     * 
     * @param  y     A sample parameter for this method.
     * @return     Double the parameter and return it. 
     */
    public String toString()
    {
        String str = "******** TICKET ********\n" + "* Name: "
        + customer.getName() + "\n" + "* SSN: " + customer.getSsn() 
        + "\n" + "* Flight: " + flight.getFlightNumber() + "\n"
        + "* Date: " + flight.getDate() + "\n" + "* Destination: " 
        + flight.getDestination() + "\n" + "************************";
        return str;  
    }

}
