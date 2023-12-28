/**
 * Flight.java
 * 
 */

//Put any imports below this line.

/**
 * Short, one-line description of Flight class here.
 * 
 * Optionally, include a paragraph that provides a more 
 * detailed description.
 *
 * @author (Gus Mckee) 
 * @version (a version number or a date)
 */
public class Flight
{
    private int flightNumber;
    private String destination;
    private Pilot pilot;
    private String date;

    /**
     * No args constructor for objects of class Flight.
     */
    public Flight()
    {
        flightNumber = 0;
        destination = "";
        pilot = new Pilot();
        date = "";

    }

    /**
     * Argument constructor for flight class.
     */
    public Flight(int flightNumber, String destination, Pilot pilot, String date)
    {
        this.flightNumber = flightNumber;
        this.destination = destination;
        setPilot(pilot);
        this.date = date;
    }

    /**
     * accessor for flightNumber.
     * @return flightNumber.
     */
    public int getFlightNumber()
    {
        return flightNumber;
    }

    /**
     * accessor for destination.
     * @return destination
     */
    public String getDestination()
    {
        return destination;
    }

    /**
     * accessor for pilot.
     * @return copy of pilot
     */
    public Pilot getPilot()
    {
        return pilot.copy();
    }

    /**
     * accessor for date.
     * @return date
     */
    public String getDate()
    {
        return date;
    }

    /**
     * mutator for flight number.
     * @param flightNumber of type int.
     */
    public void setFlightNumber(int flightNumber)
    {
        this.flightNumber = flightNumber;
    }

    /**
     * mutator for destination.
     * @param destination of type String.
     */
    public void setDestination(String destination)
    {
        this.destination = destination;
    }

    /**
     * mutator for pilot.
     * @param pilot of type Pilot
     */
    public void setPilot(Pilot pilot)
    {
        this.pilot = pilot.copy();
    }

    /**
     * mutator for date.
     * @param date of type String
     */
    public void setDate(String date)
    {
        this.date = date;
    }

    /**
     * This is a to String method which puts together a string that we can
     * return that gives us the values for our flight class fields.
     * @return str
     */
    public String toString()
    {
        String str = "Flt#: " + flightNumber +  "\tDest: " + destination 
            + "\tPlt: " + pilot.getName() +  "\tDate: " + date;
        return str;
    }

    /**
     * This is the copy method for flight class.
     * This method lets us create a copy of flight,
     * @return copyFlight.
     */

    public Flight copy()
    {
        Flight copyFlight = new Flight(flightNumber, destination, pilot.copy(), date);

        return copyFlight;
    }

}
