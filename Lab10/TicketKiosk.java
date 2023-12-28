/**
 * TicketKiosk.java
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Simulate ticket sales at Boone International Airport.
 * DO NOT ADD ANY OTHER FIELDS TO THIS CLASS!!!
 * 
 * @author Joel Swanson
 * @version 03/11/2014
 */
public class TicketKiosk
{
    //DO NOT ADD ANY OTHER FIELDS TO THIS CLASS!!!

    //Since we dont know about arrays, these will be the
    //only six flights on the schedule.  The data will be
    //read from a data file.
    private Flight f1;
    private Flight f2;
    private Flight f3;
    private Flight f4;
    private Flight f5;
    private Flight f6;    

    /**
     * DO NOT CHANGE THIS METHOD
     * Constructor for objects of class TicketKiosk.
     * @throws IOException Throws for input/output error.
     */
    public TicketKiosk() throws IOException
    {
        //Read in the six flights on the schedule.
        readSchedule();
    }

    /**
     * You may change this method for testing purposes, but it
     * should be changed back when done.
     * 
     * Create a new TicketKiosk and start it dispensing tickets.
     * @param args Command line arguments.
     * @throws IOException Throws for input/output error.
     */
    public static void main(String[] args) throws IOException
    {
        TicketKiosk kiosk = new TicketKiosk();
        Scanner keyboard = new Scanner(System.in);        
        kiosk.purchaseTickets(keyboard);        
    }

    /**
     * Create a ticket based on user input.  
     * Loop until the user enters exit as a name.
     * Note that a keyboard scanner is provided.
     * @param keyboard Allows keyboard input from the
     * customer.
     */
    public void purchaseTickets(Scanner keyboard)
    {
        //Print the schedule using printSchedule().        
        //Read in a customer name
        //If the customer name is not "exit" then process
        //  Read in customer ssn and create a customer object
        //  Read in flight number
        //  Figure out which flight belongs to that flightNumber.
        //  Use something like this:
        //          flight = findFlight(flightNumber)
        //
        //  If the flight is not null
        //      Create a ticket object and print a ticket
        //  If the flight is null
        //      print an error message.
        //Repeat these steps, only stop if exit was entered as the name.

        printSchedule();
        System.out.println("Enter your name (or type exit): ");
        String username = keyboard.nextLine();
        while(!username.equals("exit")){
            
            if(!username.equals("exit"))
            {
                System.out.println("Enter your social security number: ");
                String socialNumber = keyboard.nextLine();
                Customer customer = new Customer(username, socialNumber);
                System.out.println("Enter your flight number: ");
                int flyNumber = keyboard.nextInt();
                keyboard.nextLine();
                Flight flight = findFlight(flyNumber);

                if(flight != null)
                {
                    Ticket ticket = new Ticket(customer, flight);
                    System.out.println("\n" + ticket + "\n");
                } 
                if(flight == null)
                {
                    System.out.println("Bad flight number");
                    System.out.println("Hit enter to continue.");
                    keyboard.nextLine();
                    
                }

            }
            printSchedule();
            System.out.println("Enter your name (or type exit):");
            username = keyboard.nextLine();
        }
        System.out.println("Goodbye");
    }

    /**
     * DO NOT CHANGE THIS METHOD
     * All of the flights are created in methods readSchedule and
     * readOneFlight.  Your program simply need to find the correct 
     * flight based on the flight number. The customer sees the 
     * schedule, picks out a flight number and enters it.  This
     * method takes that flight number and returns the correct
     * flight object.  This method will return null if the use
     * enters a bad flight number (i.e. a flight number that does
     * not correspond to one of the flights on the schedule)
     * 
     * @param flightNumber The flightNumber to find a flight object.
     * @return The flight object corresponding to the flight number
     * or null if a bad flight number was given.
     */
    public Flight findFlight(int flightNumber)
    {
        if (f1.getFlightNumber() == flightNumber) 
        {
            return f1;
        }
        else if (f2.getFlightNumber() == flightNumber) 
        {
            return f2;
        }
        else if (f3.getFlightNumber() == flightNumber) 
        {
            return f3;
        }
        else if (f4.getFlightNumber() == flightNumber) 
        {
            return f4;
        }
        else if (f5.getFlightNumber() == flightNumber) 
        {
            return f5;
        }
        else if (f6.getFlightNumber() == flightNumber) 
        {
            return f6;
        }
        else
        {
            //This will help with error detection.
            //You must check the return every time you 
            //use this method.
            return null;
        }
    }

    /**
     * DO NOT CHANGE THIS METHOD
     * Print the schedule of flights.
     */
    public void printSchedule()
    {

        System.out.print("-------------------------------");
        System.out.println("------------------------------");
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f3);
        System.out.println(f4);
        System.out.println(f5);
        System.out.println(f6);
        System.out.print("-------------------------------");
        System.out.println("------------------------------");
    }

    /**
     * DO NOT CHANGE THIS METHOD.
     * Read the schedule of flights from the input file.
     * @throws IOException Throws for input/output error.
     */
    private void readSchedule()throws IOException
    {
        File flightInfo = new File("FlightInfo.txt");
        Scanner inFile = new Scanner(flightInfo);
        f1 = readOneFlight(inFile);
        f2 = readOneFlight(inFile);
        f3 = readOneFlight(inFile);
        f4 = readOneFlight(inFile);
        f5 = readOneFlight(inFile);
        f6 = readOneFlight(inFile);
        inFile.close();
    }

    /**
     * Create a flight object from a file. 
     * @param inFile Scanner object for reading the file.
     * @throws IOException Throws for input/output error.
     * @return A flight object created from file input.
     */
    public Flight readOneFlight(Scanner inFile) throws IOException
    {
        //NOTE THAT YOU ARE GIVEN A SCANNER OBJECT AS A PARAMETER.
        //DO NOT use a loop here.  DO NOT open a file.  
        int flightNumber = inFile.nextInt();
        inFile.nextLine();
        String pilotName = inFile.nextLine();
        String pilotLicense = inFile.nextLine();
        Pilot pilot = new Pilot(pilotName, pilotLicense);
        String date = inFile.nextLine();
        String destination = inFile.nextLine();

        return new Flight(flightNumber,destination, pilot, date );
    }   
}
