/**
 * TestFlight2.java
 */
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;    
    
/**
 * Describe TestFlight2 here.
 *
 * @author Joel Swanson
 * @version 03.08.2014
 */
public class TestFlight2
{
    @Rule
    public Timeout timeout = new Timeout(30000);    
    
    /**
     * Check mutators to make sure setName and setSsn work
     * properly.
     */
    @Test
    public void checkAccessorsMutators001()
    {
        checkFlightNumberAM(111);
        checkFlightNumberAM(222);
        checkDestinationAM("ABC");
        checkDestinationAM("CDE");
        checkPilotAM(new Pilot("Bill", "12345"));
        checkPilotAM(new Pilot("Alice", "34567"));
        checkDateAM("01/01/01");
        checkDateAM("12/12/12");
    }      

    /**
     * Check mutators to make sure setName and setSsn work
     * properly.
     */
    @Test
    public void checkPilotAccessorMutatorCopy002()
    {
        checkPilotAMCopy(new Pilot("Bill", "12345"));
        checkPilotAMCopy(new Pilot("Alice", "34567"));
    }      
    
    /**
     * Check the constructors.
     */
    @Test
    public void checkConstructors003()
    {
        checkNoArgConstructor();
        checkArgConstructor(111, "Berlin", new Pilot(), "01/01/01");
        checkArgConstructor(222, "Paris", new Pilot(), "02/02/02");
    }  
    
    /**
     * Test the copy method.
     */
    @Test
    public void checkCopy004()
    {
        Pilot pilot;
        Flight flight;
        String answer;
        
        pilot = new Pilot("Alice", "12345");
        flight = new Flight(111, "Berlin", pilot, "11/11/11");        
        checkCopy(flight);
        
        pilot = new Pilot("Bill", "67890");
        flight = new Flight(222, "Moscow", pilot, "22/22/22");        
        checkCopy(flight);
    }
    
    /**
     * Check the toString method.
     */
    @Test
    public void checkToString005()
    {
        Pilot pilot;
        Flight flight;
        String answer;
        
        pilot = new Pilot("Alice", "12345");
        flight = new Flight(111, "Berlin", pilot, "11/11/11");
        answer = "Flt#: 111\tDest: Berlin\tPlt:" 
            + " Alice\tDate: 11/11/11";
        checkToString(flight, answer);

        
        pilot = new Pilot("Bill", "67890");
        flight = new Flight(222, "Moscow", pilot, "22/22/22");
        answer = "Flt#: 222\tDest: Moscow\tPlt:" 
            + " Bill\tDate: 22/22/22";
        checkToString(flight, answer);
    }

    /**
     * Test the copy method with data.
     * @param original The original flight to make a copy of.
     */
    public void checkCopy(Flight original)
    {
        String fb = "";
        Flight copy = original.copy();
        
        if (copy == null)
        {
            fb += "Fail in TestFlight2.\n";
            fb += "The copy method returned a null value.\n";
            fail(fb);                 
        }
        else if (original == copy)
        {
            fb += "Fail in TestFlight2.\n";
            fb += "The copy method returned the actual flight object\n" 
                + "instead of making and returning a copy of the object.\n";
            fail(fb);                                 
        }
        else if (original.getFlightNumber() != copy.getFlightNumber())
        {
            fb += "Fail in TestFlight2.\n";
            fb += "The copy method returned a flight object with a\n";
            fb += "flight number that is different than the original\n";
            fb += "flight object.\n";
            fail(fb);                                 
        }
        else if (!original.getDestination().equals(copy.getDestination()))
        {
            fb += "Fail in TestFlight2.\n";
            fb += "The copy method returned a flight object with a\n";
            fb += "destination that is different than the original\n";
            fb += "flight object.\n";
            fail(fb);                                 
        }
        else if (!original.getDate().equals(copy.getDate()))
        {
            fb += "Fail in TestFlight2.\n";
            fb += "The copy method returned a flight object with a\n";
            fb += "date that is different than the original\n";
            fb += "flight object.\n";
            fail(fb);                                 
        }
        else if (!original.getPilot().getName().equals(
            copy.getPilot().getName()))
        {
            fb += "Fail in TestFlight2.\n";
            fb += "The copy method returned a flight object with a\n";
            fb += "pilot that is different than the original\n";
            fb += "flight object.  The pilot names are different.\n";
            fail(fb);                                 
        }
        else if (!original.getPilot().getLicense().equals(
            copy.getPilot().getLicense()))
        {
            fb += "Fail in TestFlight2.\n";
            fb += "The copy method returned a flight object with a\n";
            fb += "pilot that is different than the original\n";
            fb += "flight object.  The pilot licenses are different.\n";
            fail(fb);                                 
        }
    }

    /**
     * Test toString with given parameters.
     * @param flight The flight to test with.
     * @param answer The correct answer for comparison.
     */
    public void checkToString(Flight flight, String answer)
    {
        String fb = "";

        String data = flight.toString();

        if (data == null)
        {
            fb += "Fail in TestFlight2.\n";
            fb += "The toString method returned a null value.\n";
            fail(fb);                 
        }
        else if (data.contains("\n"))
        {
            fb += "Fail in TestFlight2.\n";
            fb += "The toString method returned incorrect data.\n";
            fb += "The value from toString should not contain " 
                + "newline characters.\n";
            fail(fb);                                                 
        }            
        else if (!data.equals(answer))
        {
            fb += "Fail in TestFlight2.\n";
            fb += "The toString method returned incorrect data.\n";
            fb += "Expected:   " + answer + "\n";
            fb += "Your value: " + data + "\n";
            fb += "Careful with spacing and tabs.\n";
            fail(fb);                                 
        }        
    }
       
    /**
     * Check the name accessor and mutator with normal valid data.
     * @param testData The name to set for testing.
     */
    public void checkFlightNumberAM(int testData)
    {
        String fb = "";

        Flight flight = null;
        if (testData == 0)
        {
            flight = new Flight(1, "", new Pilot(), "");
        }
        else
        {
            flight = new Flight(0, "", new Pilot(), "");
        }

        flight.setFlightNumber(testData);                       
        int setData = flight.getFlightNumber();
        
        if (setData != testData)
        {
            fb += "Fail in TestFlight2.\n";
            fb += "Accessor or mutator for flightNumber" 
                + " is incorrect.\n";
            fail(fb);                
        }            
    }  
    
    /**
     * Check the pilot accessor and mutator with normal valid data.
     * Makes sure the name and license of the pilot when set with the
     * mutator is that same as the name and license of the pilot
     * returned from the mutator.  
     * 
     * DOES NOT check for proper copying of arguments.
     * 
     * @param pilot The pilot to set for testing.
     */
    public void checkPilotAM(Pilot pilot)
    {
        String fb = "";
        Pilot localPilot; 
        Pilot flightPilot = null;
        Flight flight;
        String testName = pilot.getName() + "XYZ";   
        localPilot = pilot.copy();
        localPilot.setName(testName);
        
        //Create a flight using the local copy.  The
        //flight ojbect should make a copy of the pilot.
        flight = new Flight(1, "", new Pilot(), "");
        flight.setPilot(localPilot);
          
        try
        {
            //Get the pilot from the flight object.
            flightPilot = flight.getPilot();
        }
        catch (NullPointerException e)
        {
            fb += "Fail in TestFlightet2.\n";
            fb += "An error occured while setting the pilot object.\n";
            fb += "The error could have been in the accessor or\n";
            fb += "mutator.  I set a pilot with a specific name and\n";
            fb += "license using your setPilot.  Your getPilot returned\n";
            fb += "a pilot object that had never been initialized.\n";
            fb += "You must correct this error before any other test\n";
            fb += "will pass.\n";
            fb += "Make sure you are using fields properly and are NOT\n";
            fb += "incorrectly using local variables or parameters.\n";
            fb += "Make sure you didn't forget to use THIS.\n";
            fail(fb);                        
        }

        //The pilot name in flight should match the name as set.
        if (flightPilot == null || !testName.equals(flightPilot.getName()))
        {
            fb += "Fail in TestFlightet2.\n";
            fb += "An error occured while setting the pilot object.\n";
            fb += "The error could have been in the accessor or\n";
            fb += "mutator.  I set a pilot with a specific name and\n";
            fb += "license using your setPilot.  Your getPilot returned\n";
            fb += "a pilot object with a different name or license. Make\n";
            fb += "sure you are using fields properly and are NOT\n";
            fb += "incorrectly using local variables or parameters.\n";
            fb += "Make sure you are using pilot.copy().\n";
            fb += "Do not create a new blank pilot object.\n";
            fail(fb); 
        }            
    }  
    
    /**
     * Check the pilot accessor and mutator with normal valid data.
     * Make sure copies are used instead of the actual fields.
     * @param pilot The pilot to set for testing.
     */
    public void checkPilotAMCopy(Pilot pilot)
    {
        String fb = "";
        Pilot localPilot; 
        Pilot flightPilot;
        Flight flight;
        String testName = pilot.getName() + "XYZ";
        
        //Test the mutator for copy.
        //Create a local copy of the pilot.
        localPilot = pilot.copy();        
        
        //Create a flight using the local copy.  The
        //flight ojbect should make a copy of the pilot.
        flight = new Flight(1, "", new Pilot(), "");
        flight.setPilot(localPilot);
        
        //Change the local copy pilot name.
        localPilot.setName(testName);
        
        //Get the pilot from the flight object.
        flightPilot = flight.getPilot();

        //The pilot name in flight should not have changed.
        if (testName.equals(flightPilot.getName()))
        {
            fb += "Fail in TestFlight2.\n";
            fb += "Mutator setPilot did not make a copy" 
                + " of the argument.\n";            
            fb += "Mutators for object types should make" 
                + " copies of\n";            
            fb += "arguments rather than setting the field directly\n";
            fb += "from the argument.\n";            
            fail(fb);
        }    
        
        //Test the accessor for copy.
        //Create a local copy of the pilot.
        localPilot = pilot.copy();        
        
        //Create a flight using the local copy.  The
        //flight ojbect should make a copy of the pilot.
        flight = new Flight(1, "", new Pilot(), "");
        flight.setPilot(localPilot);
        
        //Get the pilot from the flight object.
        flightPilot = flight.getPilot();
        
        //Change the pilot returned from flight.  
        flightPilot.setName(testName);
        
        //Get the pilot object again and the name should not have changed.
        flightPilot = flight.getPilot();
        
        //The pilot name in flight should not have changed.
        if (flightPilot.getName().equals(testName))
        {
            fb += "Fail in TestFlight2.\n";
            fb += "Accessor getPilot did not return a copy of the field.\n";
            fb += "Accessors for object types should return copies of\n";
            fb += "fields rather than the fields themselves.\n";
            fail(fb);
        } 
    }  
    
    /**
     * Check the destination accessor and mutator with normal valid data.
     * @param testData The destination to set for testing.
     */
    public void checkDestinationAM(String testData)
    {
        String fb = "";

        Flight flight = new Flight(1, "", new Pilot(), "");
        flight.setDestination(testData);                       
        String setData = flight.getDestination();
        if (setData == null)
        {
            fb += "Fail in TestFlight2.\n";
            fb += "Accessor or mutator for destination is incorrect.\n";
            fb += "Probably improper use of local variables or THIS\n";
            fb += "in your mutator.\n";                
            fail(fb);             
        }        
        if (!setData.equals(testData))
        {
            fb += "Fail in TestFlight2.\n";
            fb += "Accessor or mutator for destination" 
                + " is incorrect.\n";
            fail(fb);                
        }            
    } 
    
    /**
     * Check the date accessor and mutator with normal valid data.
     * @param testData The destination to set for testing.
     */
    public void checkDateAM(String testData)
    {
        String fb = "";

        Flight flight = new Flight(1, "", new Pilot(), testData);
        flight.setDate(testData);                       
        String setData = flight.getDate();

        if (setData == null)
        {
            fb += "Fail in TestFlight2.\n";
            fb += "Accessor or mutator for date is incorrect.\n";
            fb += "Probably improper use of local variables or THIS\n";
            fb += "in your mutator.\n";                
            fail(fb);             
        }
        else if (!setData.equals(testData))
        {
            fb += "Fail in TestFlight2.\n";
            fb += "Accessor or mutator for date" 
                + " is incorrect.\n";
            fail(fb);                
        }            
    }      
    /**
     * Test the no argument constructor.
     */
    public void checkNoArgConstructor()
    {
        String fb = "";
        Flight flight = new Flight();
        if (flight.getDestination() == null 
            || flight.getDate() == null
            || flight.getPilot() == null)
        {
            fb += "Fail in TestFlight2.\n";            
            fb += "No-arg constructor should make sure all object\n";
            fb += "fields, including strings, are not null.\n";
            fail(fb);
        }
    }

    /**
     * Test argument constructor.
     * 
     * @param flightNumber The flight number to test with.
     * @param destination The destination to test with.
     * @param pilot The pilot object to test with.
     * @param date The date to test with.
     */
    public void checkArgConstructor(int flightNumber, 
        String destination, Pilot pilot, String date)
    {
        String fb = "";
        Pilot localPilot = pilot.copy();
        Flight flight = new Flight(flightNumber, destination, 
            localPilot, date);
        
        if (flight.getFlightNumber() != flightNumber)
        {
            fb += "Fail in TestFlight2.\n";
            fb += "Argument constructor improperly set flight number.\n";
            fail(fb);
        }        
        else if (!flight.getDestination().equals(destination))
        {
            fb += "Fail in TestFlight2.\n";
            fb += "Argument constructor improperly set destination.\n";
            fail(fb);
        }
        else if (!flight.getDate().equals(date))
        {
            fb += "Fail in TestFlight2.\n";
            fb += "Argument constructor improperly set date.\n";
            fail(fb);
        }
        else 
        {
            //Change the local copy.  The copy in the flight object's
            //name should not change.
            String testName = localPilot.getName() + "XYZ";
            localPilot.setName(testName);
            Pilot flightPilot = flight.getPilot();
            if (flightPilot.getName().equals(testName))
            {
                fb += "Fail in TestFlight2.\n";
                fb += "Argument constructor improperly set pilot.\n";
                fb += "Constructor did not make a copy of the pilot\n";
                fb += "argument.\n";
                fail(fb);             
            }           
        }
    }  
}
