/**
 * TestTicket2.java
 */
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;    
    
/**
 * Describe TestTicket2 here.
 *
 * @author Joel Swanson
 * @version 03.09.2014
 */
public class TestTicket2
{
    @Rule
    public Timeout timeout = new Timeout(30000);    
    
    /**
     * Check that the accessors and mutators actually set
     * the customer field and return a customer object with
     * the same name and ssn.  
     * 
     * DOES NOT check for proper copy operations.
     */
    @Test
    public void checkCustomerAM001()
    {
        checkCustomerAM(new Customer("Bill", "888-88-8888"));
        checkCustomerAM(new Customer("Alice", "333-33-3333"));
    }      
    
    /**
     * Check mutator to make sure a copy is of the argument is set.
     */
    @Test
    public void checkCustomerMutatorCopy002()
    {
        checkCustomerMutatorCopy(new Customer("Bill", "888-88-8888"));
        checkCustomerMutatorCopy(new Customer("Alice", "333-33-3333"));
    }      

    /**
     * Check accessor to make sure a copy of the field is returned.
     */
    @Test
    public void checkCustomerAccessorCopy003()
    {
        checkCustomerAccessorCopy(new Customer("Bill", "888-88-8888"));
        checkCustomerAccessorCopy(new Customer("Alice", "333-33-3333"));
    }          

    /**
     * Check that the accessors and mutators actually set
     * the flight field and return a flight object with
     * the the same data.  
     * 
     * DOES NOT check for proper copy operations.
     */
    @Test
    public void checkFlightAM004()
    {
        Pilot pilot;
        Flight flight;
        
        pilot = new Pilot("Bill Jones", "112233445566");
        flight = new Flight(885, "Boston", pilot, "06/21/14");
        checkFlightAM(flight);

        pilot = new Pilot("Tim Jones", "113344556677");
        flight = new Flight(602, "London", pilot, "06/21/14");
        checkFlightAM(flight);
    }      
    
    /**
     * Check mutators to make sure copying of argument is
     * performed.
     */
    @Test
    public void checkFlightMutatorCopy005()
    {
        Pilot pilot;
        Flight flight;
        
        pilot = new Pilot("Bill Jones", "112233445566");
        flight = new Flight(885, "Boston", pilot, "06/21/14");
        checkFlightMutatorCopy(flight);

        pilot = new Pilot("Tim Jones", "113344556677");
        flight = new Flight(602, "London", pilot, "06/21/14");
        checkFlightMutatorCopy(flight);              
    }      

    /**
     * Check mutators to make sure setName and setSsn work
     * properly.
     */
    @Test
    public void checkFlightAccessorCopy006()
    {
        Pilot pilot;
        Flight flight;
        
        pilot = new Pilot("Bill Jones", "112233445566");
        flight = new Flight(885, "Boston", pilot, "06/21/14");
        checkFlightAccessorCopy(flight);

        pilot = new Pilot("Tim Jones", "113344556677");
        flight = new Flight(602, "London", pilot, "06/21/14");
        checkFlightAccessorCopy(flight);  
    }          
    
    /**
     * Check the constructors.
     */
    @Test
    public void checkConstructors007()
    {
        
        Pilot pilot;
        Flight flight;
        Customer customer;
        
        pilot = new Pilot("Bill Jones", "112233445566");
        flight = new Flight(885, "Boston", pilot, "06/21/14");
        customer = new Customer("Alice", "333-33-3333");
        checkArgConstructorNull(customer, flight);        
        
        pilot = new Pilot("Bill Jones", "112233445566");
        flight = new Flight(885, "Boston", pilot, "06/21/14");
        customer = new Customer("Alice", "333-33-3333");
        checkArgConstructor(customer, flight);

        pilot = new Pilot("Tim Jones", "113344556677");
        flight = new Flight(602, "London", pilot, "06/21/14");
        customer = new Customer("Bill", "888-88-8888");
        checkArgConstructor(customer, flight);
        
        
    }  
    
    /**
     * Check the toString method.
     */
    @Test
    public void checkToString008()
    {
        Pilot pilot;
        Flight flight;
        Customer customer;
        Ticket ticket;
        String answer;
        
        pilot = new Pilot("Bill Jones", "112233445566");
        flight = new Flight(885, "Boston", pilot, "06/21/14");
        customer = new Customer("Alice", "333-33-3333");
        ticket = new Ticket(customer, flight);
        
        answer  = "******** TICKET ********\n";
        answer += "* Name: Alice\n";
        answer += "* SSN: 333-33-3333\n";
        answer += "* Flight: 885\n";
        answer += "* Date: 06/21/14\n";
        answer += "* Destination: Boston\n";
        answer += "************************\n";
        checkToString(ticket, answer);        

        pilot = new Pilot("Tim Jones", "113344556677");
        flight = new Flight(602, "London", pilot, "06/21/14");
        customer = new Customer("Bill", "888-88-8888");
        ticket = new Ticket(customer, flight);        
        answer  = "******** TICKET ********\n";
        answer += "* Name: Bill\n";
        answer += "* SSN: 888-88-8888\n";
        answer += "* Flight: 602\n";
        answer += "* Date: 06/21/14\n";
        answer += "* Destination: London\n";
        answer += "************************\n";
        checkToString(ticket, answer);   
    }
    
    /**
     * Test toString with given parameters.
     * @param ticket The ticket to test with.
     * @param answer The correct answer for comparison.
     */
    public void checkToString(Ticket ticket, String answer)
    {
        String fb = "";
        int pos = 0;
        
        
        if (ticket.toString() == null)
        {
            fb += "Fail in TestTicket2.\n";
            fb += "The toString method returned a null value.\n";
            fail(fb);            
        }
        else if (ticket.toString().trim().equals(""))
        {
            fb += "Fail in TestTicket2.\n";
            fb += "The toString method returned an empty string.\n";
            fail(fb);             
        }
        else
        {

            String[] answerArray = answer.split("\n");
            String[] toStringArray = ticket.toString().split("\n");        
                
            if (toStringArray.length != 7)
            {
                fb += "Fail in TestTicket2.\n";
                fb += "The toString method returned an incorrect string.\n";
                fb += "There should have been seven lines.\n";
                fb += "\nThe correct ticket:\n" + answer;
                fb += "\nThe result of your toString method:\n" 
                    + ticket.toString();
                fail(fb);                
            }
            else
            {
                boolean fail = false;
                fb += "Fail in TestTicket2.\n";
                fb += "One or more of your lines don't match.\n";
                for (int i = 0; i < answerArray.length; i++)
                {
                    if (toStringArray[i].trim().equals(answerArray[i]))
                    {
                        fb += "GOOD  " + toStringArray[i] + "\n";
                    }
                    else
                    {
                        fb += "ERROR " + toStringArray[i] + "\n";
                        fail = true;
                    }
                }
                if (fail)
                {
                    fb += "\nThe correct ticket:\n" + answer;
                    fb += "\nMake sure you have a single space after" 
                        + " the asterisk\n";
                    fb += "and a single space after the colon with NO"
                        + " other spaces.\n";
                    fail(fb);
                }
            }
        }
    }
        
    /**
     * Check the customer accessor mutator. This is a simple test
     * to make sure that the customer that is set in the mutator
     * and the customer that is returned from the accessor have
     * the same name and ssn.
     * 
     * @param customer The customer object to use for testing.
     */   
    public void checkCustomerAM(Customer customer)
    {
        String fb = "";
        Customer localCustomer; 
        Customer returnCustomer;
        Ticket ticket;

        Flight flight = new Flight(0, "", new Pilot(), "");
        Customer blankCustomer = new Customer("", "");
        String testName = customer.getName() + "XYZ";
        
        //Test the accessor for copy.
        //Create a local copy of the customer.
        localCustomer = new Customer(customer);        
        
        //Create a ticket using the local copy.  The
        //ticket object should make a copy of the pilot.
        ticket = new Ticket(blankCustomer, flight);
        ticket.setCustomer(localCustomer);
        
        //Get the customer from the ticket object.
        returnCustomer = ticket.getCustomer();
                
        //Make sure the set customer matches the argument.  
        if (!customer.getName().equals(returnCustomer.getName())
            || !customer.getSsn().equals(returnCustomer.getSsn()))
        {
            fb += "Fail in TestTicket2.\n";
            fb += "An error occured while modifying the customer\n";
            fb += "field.  The error could have been in the\n";
            fb += "accessor or mutator.  I set a customer with\n";
            fb += "a specific name and ssn using your setCustomer.\n";
            fb += "Your getCustomer returned a customer object with\n";
            fb += "a different name or ssn.  Make sure you are\n";
            fb += "setting and returning FIELDS and are NOT\n";
            fb += "incorrectly using local variables or parameters.\n";
            fail(fb);            
        }      
    }         

    /**
     * Check the flight accessor mutator. This is a simple test
     * to make sure that the flight that is set in the mutator
     * and the flight that is returned from the accessor have
     * the same data.
     * 
     * @param flight The flight object to use for testing.
     */   
    public void checkFlightAM(Flight flight)
    {
        String fb = "";
        Flight localFlight; 
        Flight returnFlight;
        Ticket ticket;
        
        Customer customer = new Customer("Alice", "333-33-3333");
        Flight blankFlight = new Flight(000, "", new Pilot("", ""), "");
        
        //Test the accessor for copy.
        //Create a local copy of the customer.
        localFlight = flight.copy();
        
        //Create a ticket using the local copy.  The
        //ticket object should make a copy of the pilot.
        ticket = new Ticket(customer, blankFlight);
        ticket.setFlight(flight);
        
        //Get the customer from the ticket object.
        returnFlight = ticket.getFlight();
                
        //Make sure the set customer matches the argument.
        if (localFlight.getFlightNumber() 
            != returnFlight.getFlightNumber())
        {
            fb += "Fail in TestTicket2.\n";
            fb += "An error occured while modifying the fight\n";
            fb += "field.  The error could have been in the\n";
            fb += "accessor or mutator.  I set a flight with\n";
            fb += "specific fields values using your setFlight.\n";
            fb += "Your getFlight returned a flight object with\n";
            fb += "different field values.  Make sure you are\n";
            fb += "setting and returning FIELDS and are NOT\n";
            fb += "incorrectly using local variables or parameters.\n";
            fail(fb);            
        }      
    }         
    
    /**
     * Check the customer mutator. This test is ensuring that
     * the mutator makes a copy of the argument and sets the 
     * field from the copy.
     * 
     * @param customer The customer object to use for testing.
     */
    public void checkCustomerMutatorCopy(Customer customer)
    {
        String fb = "";
        Customer localCustomer; 
        Customer returnCustomer;
        Ticket ticket;

        Flight flight = new Flight(0, "", new Pilot(), "");
        Customer blankCustomer = new Customer("", "");
        String testName = customer.getName() + "XYZ";
        
        //Test the mutator for copy.
        //Create a local copy of the customer.
        localCustomer = new Customer(customer);        
        
        //Create a ticket using the local copy.  The
        //ticket ojbect should make a copy of the pilot.
        ticket = new Ticket(blankCustomer, flight);
        ticket.setCustomer(localCustomer);
        
        //Change the local customer name.
        localCustomer.setName(testName);
        
        //Get the customer from the ticket object.
        returnCustomer = ticket.getCustomer();
          
        //The customer name in ticket should not have changed.
        if (testName.equals(returnCustomer.getName()))
        {
            fb += "Fail in TestTicket2.\n";
            fb += "Mutator setCustomer did not make a copy" 
                + " of the argument.\n";            
            fb += "Mutators for object types should make" 
                + " copies of\n";            
            fb += "arguments rather than setting the field directly\n";
            fb += "from the argument.\n";            
            fail(fb);
        }    
    }

    /**
     * Check the flight mutator. This test is ensuring that
     * the mutator makes a copy of the argument and sets the 
     * field from the copy.
     * 
     * @param flight The flight object to use for testing.
     */
    public void checkFlightMutatorCopy(Flight flight)
    {
        String fb = "";
        Flight localFlight; 
        Flight returnFlight;
        Ticket ticket;
        
        Customer customer = new Customer("Alice", "333-33-3333");
        Flight blankFlight = new Flight(000, "", new Pilot("", ""), "");
        int testFlightNumber = flight.getFlightNumber() + 10;
        
        //Test the accessor for copy.
        //Create a local copy of the customer.
        localFlight = flight.copy();
        
        //Create a ticket using the local copy.  The
        //ticket object should make a copy of the pilot.
        ticket = new Ticket(customer, blankFlight);
        ticket.setFlight(localFlight);
        
        //Change the flight number on the local copy.
        localFlight.setFlightNumber(testFlightNumber);      
        
        //Get the flight from the ticket object.
        returnFlight = ticket.getFlight();
          
        //The flight number from the returned copy should not have 
        //changed.
        if (testFlightNumber == returnFlight.getFlightNumber())
        {
            fb += "Fail in TestTicket2.\n";
            fb += "Mutator setFlight did not make a copy" 
                + " of the argument.\n";            
            fb += "Mutators for object types should make" 
                + " copies of\n";            
            fb += "arguments rather than setting the field directly\n";
            fb += "from the argument.\n";            
            fail(fb);
        }    
    }
    
    /**
     * Check the customer accessor. This test is ensuring that
     * the accessor makes a copy of the field and returns the
     * copy instead of directly returning the field.
     * 
     * @param customer The customer object to use for testing.
     */   
    public void checkCustomerAccessorCopy(Customer customer)
    {
        String fb = "";
        Customer localCustomer; 
        Customer returnCustomer;
        Ticket ticket;

        Flight flight = new Flight(0, "", new Pilot(), "");
        Customer blankCustomer = new Customer("", "");
        String testName = customer.getName() + "XYZ";
        
        //Test the accessor for copy.
        //Create a local copy of the customer.
        localCustomer = new Customer(customer);        
        
        //Create a ticket using the local copy.  The
        //ticket object should make a copy of the pilot.
        ticket = new Ticket(blankCustomer, flight);
        ticket.setCustomer(localCustomer);
        
        //Get the customer from the ticket object.
        returnCustomer = ticket.getCustomer();
        
        //Change the customer returned from ticket.  
        returnCustomer.setName(testName);
        
        //Get the customer object again and the name should 
        //not have changed.
        returnCustomer = ticket.getCustomer();
              
        //The pilot name in flight should not have changed.
        if (testName.equals(returnCustomer.getName()))
        {
            fb += "Fail in TestTicket2.\n";
            fb += "Accessor getCustomer did not return a copy of the field.\n";
            fb += "Accessors for object types should return copies of\n";
            fb += "fields rather than the fields themselves.\n";
            fail(fb);
        } 
    }  

    /**
     * Check the flight accessor. This test is ensuring that
     * the accessor makes a copy of the field and returns the
     * copy instead of directly returning the field.
     * 
     * @param flight The flight object to use for testing.
     */   
    public void checkFlightAccessorCopy(Flight flight)
    {
        String fb = "";
        Flight localFlight; 
        Flight returnFlight;
        Ticket ticket;
        
        Customer customer = new Customer("Alice", "333-33-3333");
        Flight blankFlight = new Flight(000, "", new Pilot("", ""), "");
        int testFlightNumber = flight.getFlightNumber() + 10;
        
        //Test the accessor for copy.
        //Create a local copy of the customer.
        localFlight = flight.copy();
        
        //Create a ticket using the local copy.  The
        //ticket object should make a copy of the pilot.
        ticket = new Ticket(customer, blankFlight);
        ticket.setFlight(localFlight);
        
        //Get the flight from the ticket object.
        returnFlight = ticket.getFlight();
        
        //Change the flight returned from ticket.  
        returnFlight.setFlightNumber(testFlightNumber);
        
        //Get the customer object again and the name should 
        //not have changed.
        returnFlight = ticket.getFlight();
              
        //The flight number in last flight should not have changed.
        if (testFlightNumber == returnFlight.getFlightNumber())
        {
            fb += "Fail in TestTicket2.\n";
            fb += "Accessor getFlight did not return a copy of the field.\n";
            fb += "Accessors for object types should return copies of\n";
            fb += "fields rather than the fields themselves.\n";
            fail(fb);
        } 
    }      
    
    /**
     * Test argument constructor.
     * 
     * @param customer The customer object to test with.
     * @param flight The flight object to test with.
     */
    public void checkArgConstructor(Customer customer, Flight flight)
    {
        String fb = "";
        Customer localCustomer = new Customer(customer);
        Flight localFlight = flight.copy();
        
        Ticket ticket = new Ticket(localCustomer, localFlight);
        
        Customer returnCustomer = ticket.getCustomer();
        Flight returnFlight = ticket.getFlight();
        
        if (!isCustomerEqual(localCustomer, returnCustomer))
        {
            fb += "Fail in TestTicket2.\n";
            fb += "Argument constructor improperly set customer field.\n";
            fb += "The set customer and the get customer objects\n";
            fb += "have different field values.\n";
            fail(fb);
        }        
        else if (!isFlightEqual(localFlight, returnFlight))
        {
            fb += "Fail in TestTicket2.\n";
            fb += "Argument constructor improperly set flight field.\n";
            fb += "The set flight and the get flight objects\n";
            fb += "have different field values.\n";
            fail(fb);
        }
        else 
        {
            String testCustomerName = localCustomer.getName() + "XYZ";
            int testFlightNumber = localFlight.getFlightNumber() + 10;
            
            localCustomer.setName(testCustomerName);
            localFlight.setFlightNumber(testFlightNumber);
            
            returnCustomer = ticket.getCustomer();
            returnFlight = ticket.getFlight();
            
            if (testCustomerName.equals(returnCustomer.getName()))
            {
                fb += "Fail in TestTicket2.\n";
                fb += "Argument constructor improperly set customer field.\n";
                fb += "Constructor did not use a copy of the argument.\n";
                fail(fb);               
            }
            else if (testFlightNumber == returnFlight.getFlightNumber())
            {
                fb += "Fail in TestTicket2.\n";
                fb += "Argument constructor improperly set flight field.\n";
                fb += "Constructor did not use a copy of the argument.\n";
                fail(fb);                               
            }
        }
    }  
    
    /**
     * Test argument constructor for null values.
     * 
     * @param customer The customer object to test with.
     * @param flight The flight object to test with.
     */
    public void checkArgConstructorNull(Customer customer, Flight flight)
    {
        String fb = "";
        Customer localCustomer = new Customer(customer);
        Flight localFlight = flight.copy();
        
        Ticket ticket = new Ticket(localCustomer, localFlight);
        
        try
        {
            Customer returnCustomer = ticket.getCustomer();
        }
        catch (Exception e)
        {
            fb += "Fail in TestTicket2.\n";
            fb += "Argument constructor improperly set customer field.\n";
            fb += "Check for improper use of local variables and parameters.\n";
            fb += "Check for improper use of THIS.\n";
            fail(fb);       
        }
        
        try
        {
            Flight returnFlight = ticket.getFlight();
        }
        catch (Exception e)
        {
            fb += "Fail in TestTicket2.\n";
            fb += "Argument constructor improperly set flight field.\n";
            fb += "Check for improper use of local variables and parameters.\n";
            fb += "Check for improper use of THIS.\n";
            fail(fb);       
        }
    }  
    
    /**
     * Check to see if two customers are equal in all field values.
     * @param c1 The first customer for equality check.
     * @param c2 The second customer to check against the first.
     * @return Returns true if the two customer parameters have the
     * same name and ssn.
     * 
     */
    public boolean isCustomerEqual(Customer c1, Customer c2)
    {
        return c1.getName().equals(c2.getName()) 
            && c1.getSsn().equals(c2.getSsn());
    }

    /**
     * Check to see if two flights are equal in all field values.
     * @param f1 The first flight for equality check.
     * @param f2 The second flight to check against the first.
     * @return Returns true if the two flight parameters have the
     * same data.
     */
    public boolean isFlightEqual(Flight f1, Flight f2)
    {
        Pilot p1 = f1.getPilot();
        Pilot p2 = f2.getPilot();
        
        return f1.getFlightNumber() == f2.getFlightNumber()
            && f1.getDestination().equals(f2.getDestination())
            && f1.getDate().equals(f2.getDate())
            && p1.getName().equals(p2.getName())
            && p1.getLicense().equals(p2.getLicense());

    }
}
