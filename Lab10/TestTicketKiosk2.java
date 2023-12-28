/**
 * TestTicketKiosk2.java
 */
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;    
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

/**
 * Describe TestTicketKiosk2 here.
 *
 * @author Joel Swanson
 * @version 03.09.2014
 */
public class TestTicketKiosk2
{    
    @Rule
    public Timeout timeout = new Timeout(30000);    
    
    private String[] result;
    private String[] expected;
    
    /**
     * Check readOneFlight method.
     */
    @Test
    public void checkReadOneFlight001()
    {
        checkReadOneFlightA();
        checkReadOneFlightB();
    }
    
    /**
     * Make sure exaxtly three schedules are printed with
     * the sample input.
     * 
     * Input
     *  Customer Name: Al Bundy
     *  Customer ssn : 222-22-2222
     *  Flight Number: 885
     *  Customer Name: Peg Bundy
     *  Customer ssn : 111-11-1111
     *  Flight Number: 100
     *  Extra enter for error pause
     *  Customer Name : exit     * 
     */
    @Test
    public void checkSchedulesPrinted002()
    {
        String fb = "";
        fb += "Failure in TestTicketKiosk2.\n";
        fb += "\nEntering input in the following order:\n";
        fb += "\tAl Bundy, 222-22-2222, 885,\n";
        fb += "\tPeg Bundy, 111-11-1111, 100,\n";
        fb += "\textra enter for error, exit\n\n";        
        
        String input = "Al Bundy\n222-22-2222\n885\nPeg" 
            + " Bundy\n111-11-1111\n100\n\nexit\n";

        //Result is all of the output of the student data with the given input.
        result = capture(input);        
        
        int dashCount = 0;
        int fltCount = 0;
        
        for (int i = 0; i < result.length; i++)
        {

            if (result[i].startsWith("Flt#:"))
            {
                fltCount++;
            }
        }
        
        if (fltCount != 18)
        {
            fb += "When running the sample input, the schedule\n";
            fb += "should have been printed 3 times.\n";
            fb += "You printed " + (fltCount / 6) + " schedules.\n";
            fail(fb);
        }        
    }   
    
    /**
     * Check the purchase tickets method exits properly
     * if exit is typed initially.
     */    
    @Test
    public void checkPurchaseTicketsInitialExit003()
    {
        String fb = "";
        String input = "exit\n";

        //Result is all of the output of the student data with the given input.
        String[] result = capture(input);
        String[] expected = getExpected002();
        
        if (result.length != expected.length)
        {
            fb += "Failure in TestTicketKiosk2.\n";
            fb += "When exit is the first input to your kiosk\n";  
            fb += "The number of lines returned from your output does\n";  
            fb += "not match the expected output.\n";  
            fb += "Make sure you printed the schedule first.\n";  
            fb += "Careful with newlines and tabs.\n\n";  
            fb += "1. Run your main method.\n";
            fb += "2. Type exit and hit enter.\n";
            fb += "3. Make sure your output looks EXACLTY" 
                + " like the following.\n";
            fb += "-------------------------------------------" 
                + "------------------\n";
            fb += "Flt#: 885  Dest: Boston    Plt: Bill Jones Date: 06/21/14\n";
            fb += "Flt#: 602  Dest: London    Plt: Tim Jones  Date: 06/21/14\n";
            fb += "Flt#: 212  Dest: Berlin    Plt: Tim Jones  Date: 06/24/14\n";
            fb += "Flt#: 650  Dest: Chicago   Plt: Tim Jones  Date: 06/26/14\n";
            fb += "Flt#: 111  Dest: New York  Plt: Bill Jones Date: 06/26/14\n";
            fb += "Flt#: 190  Dest: Tokyo     Plt: Tim Jones  Date: 06/28/14\n";
            fb += "---------------------------------------------" 
                + "----------------\n";
            fb += "Enter your name (or type exit):\n";
            fb += "exit\n";
            fb += "Goodbye\n";    
            fb += "\nYour output with errors marked:\n";    
            fb += getErrorLines(result, expected);            
            fail(fb);               
        }        
    } 
       
    /**
     * Check to make sure the output EXACTLY follows the sample output.
     * 
     * Input
     *  Customer Name: Al Bundy
     *  Customer ssn : 222-22-2222
     *  Flight Number: 885
     *  Customer Name: Peg Bundy
     *  Customer ssn : 111-11-1111
     *  Flight Number: 100
     *  Extra enter for error pause
     *  Customer Name : exit     * 
     */
    @Test
    public void checkExactLines004()
    {
        String fb = "";
        fb += "Failure in TestTicketKiosk2.\n";
        fb += "\nEntering input in the following order:\n";
        fb += "\tAl Bundy, 222-22-2222, 885,\n";
        fb += "\tPeg Bundy, 111-11-1111, 100,\n";
        fb += "\textra enter for error, exit\n\n";        
        fb += "Your output should match EXACTLY line for line\n";        
        
        String input = "Al Bundy\n222-22-2222\n885\nPeg" 
            + " Bundy\n111-11-1111\n100\n\nexit\n";

        //Result is all of the output of the student data with the given input.
        result = capture(input);        
        expected = getExpectedA();
        
        if (result.length == 0)
        {

            fb += "You have no output. Do not print anything in main.\n";
            fb += "All printing should be in purchaseTickets or from\n";
            fb += "methods called from purchaseTickets.\n";
        }
        else if (result.length < 27)
        {
            fb += "Your output does not have enough lines.\n";
            fb += "Make sure you used a while loop in purchaseTickets.\n";
            fb += "Make sure your loop exits properly.\n";
            fb += "Make sure you print a schedule before getting" 
                + " customer name.\n";
            fb += "Your output should match the sample exactly.\n";
            fail(fb);        
        }
        else if (result.length < expected.length)
        {

            fb += "Your output does not have enough lines. Make sure\n";
            fb += "you included all blank lines as shown" 
                + " in the sample output.\n";
            fb += "Make sure you used printlns for prompts and NOT prints.\n";
            fb += "Your output should match the sample exactly.\n";
            fail(fb);
           
        }
        else if (result.length > expected.length)
        {
  
            fb += "Your output has too many lines.\n";
            fb += "Make sure you did not print extra blank lines.\n";
            fb += "Make sure you did not print an extra schedule.\n";
            fb += "Your output should match the sample exactly.\n";
            fb += getErrorLines(result, expected);
            fail(fb);
        }
        checkEachLine();
    }
    
   
    /**
     * Change up the input a bit.  Check that a Peg Bundy 
     * ticket exists in the output. The output should
     * look exactly the same with different ticket data.
     * 
     * Input
     *  Customer Name: Peg Bundy
     *  Customer ssn : 111-11-1111
     *  Flight Number: 190
     *  Customer Name: Al Bundy
     *  Customer ssn : 222-22-2222
     *  Flight Number: 200
     *  Extra enter for error pause
     *  Customer Name : exit     * 
     */
    @Test
    public void checkTicketPegBundy005()    
    {
        String fb = ""; 
        fb += "Failure in TestTicketKiosk2.\n";
        fb += "Entering input in the following order:\n";
        fb += "\tPeg Bundy, 111-11-1111, 190,\n";
        fb += "\tAl Bundy, 222-22-2222, 200,\n";
        fb += "\textra enter for error, exit\n";        
        fb += "Your output should look exaclty the same\n";        
        fb += "as the sample output except the ticket\n";        
        fb += "information should be different.\n";        

        String input = "Peg Bundy\n111-11-1111\n190\nAl" 
            + " Bundy\n222-22-2222\n200\n\nexit\n";

        //Result is all of the output of the student data with the given input.
        result = capture(input);  
                
        if (result.length < 22)
        {
            fb += "Ticket should be on lines 16 - 22.\n";
            fb += "Your output ended before line 22.\n";
            fail(fb);                   
            
        }
        if (result[13].toUpperCase().contains("TICKET"))
        {
            fb += "Missing ticket header on line 16.\n";
            fail(fb);                   
        }
        
        if (result.length < 19)
        {
            fb += "One ticket should have been printed in the\n";
            fb += "output.  It appears you started printing a\n";
            fb += "ticket but did not print all of the lines.\n";
            fb += "The output ends in mid ticket.\n";
            fail(fb);               
        }
        
        boolean error = false;
        fb += "Found the following errors with your ticket:\n";
        if (!result[13].startsWith("* Name: Peg Bundy"))
        {            
            fb += "\tTicket name line is incorrect on line 17.\n";
            error = true;
        }
        if (!result[14].startsWith("* SSN: 111-11-1111"))
        {            
            fb += "\tTicket ssn line is incorrect on line 18.\n";
            error = true;
        }
        if (!result[15].startsWith("* Flight: 190"))
        {            
            fb += "\tTicket flight line is incorrect on line 19.\n";
            error = true;
        }
        if (!result[16].startsWith("* Date: 06/28/14"))
        {            
            fb += "\tTicket date line is incorrect on line 20.\n";
            error = true;
        }
        if (!result[17].startsWith("* Destination: Tokyo"))
        {            
            fb += "\tTicket destination line is incorrect on line 21.\n";
            error = true;
        }        
       
        if (error)
        {
            fb += "Your ticket output (lines 16 - 22) follows:\n";
            fb += result[12] + "\n";
            fb += result[13] + "\n";
            fb += result[14] + "\n";
            fb += result[15] + "\n";
            fb += result[16] + "\n";
            fb += result[17] + "\n";
            fb += result[18] + "\n";
            fail(fb);
        }
    }    

    /**
     * Returns the expected output of a simple exit.
     * @return Returns the expected output in array format.
     */
    public String[] getExpected002()
    {
        String[] expected = new String[10];
        expected[0] = "---------------------------------------------" 
            + "----------------";
        expected[1] = "Flt#: 885\tDest: Boston\tPlt: Bill Jones\tDate:" 
            + " 06/21/14";
        expected[2] = "Flt#: 602\tDest: London\tPlt: Tim Jones\tDate: 06/21/14";
        expected[3] = "Flt#: 212\tDest: Berlin\tPlt: Tim Jones\tDate: 06/24/14";
        expected[4] = "Flt#: 650\tDest: Chicago\tPlt: Tim Jones\tDate:" 
            + " 06/26/14";
        expected[5] = "Flt#: 111\tDest: New York\t" 
            + "Plt: Bill Jones\tDate: 06/26/14";
        expected[6] = "Flt#: 190\tDest: Tokyo\tPlt: Tim Jones\tDate: 06/28/14";
        expected[7] = "----------------------------------------------" 
            + "---------------";
        expected[8] = "Enter your name (or type exit): "; 
        expected[9] = "Goodbye";    
        return expected;
    }    
    
    /**
     * Look at each line of the output and compare to expected.
     */
    public void checkEachLine()
    {
        boolean error = false;
        String fb = "";
        String match = "";
        int i = 0;        
        while (!error && i < result.length)
        {
            if (!expected[i].equals(result[i++].trim()))
            {
                error = true;
            }
        }            
            
        if (error)
        {
            fb += "Failure in TestTicketKiosk2.\n";
            fb += "Entering input in the following order:\n";
            fb += "\tAl Bundy, 222-22-2222, 885,\n";
            fb += "\tPeg Bundy, 111-11-1111, 100,\n";
            fb += "\textra enter for error, exit\n\n"; 
            fb += "You had one or more incorrect lines.\n";
            fb += "Careful with spelling, case, and spacing.\n";
            fb += "Here is your output with error lines marked.\n";
            fb += getErrorLines(result, expected);
            fail(fb);            
        }
        
    }
    
    /**
     * Print each line in the result as an error or not.
     * @param result The result as captured from student output.
     * @param expected The correct output.  The result data 
     * should match this data exactly.
     * @return The student result as a string with each error
     * line marked.
     */
    public String getErrorLines(String[] result, String[] expected)
    {   
        String match = "";
        
        for (int i = 0; i < result.length; i++)
        {
            if (expected.length <= i)
            {
                match += "Error " + result[i] + "\n";
            }
            else if (expected[i].equals(result[i].trim()))
            {
                match += "Good  " + result[i] + "\n";
            }
            else
            {
                match += "Error " + result[i] + "\n";
            }
            if (result[i].toLowerCase().startsWith("hit enter"))
            {
                match += "\n";
            }
        }
        if (result.length < expected.length)
        {
            match += "Error Your output should have had more lines.\n";
        }
        else if (result.length > expected.length)
        {
            match += "Error Your output had too many lines.\n";
        }
        
        return match;
    }
    
    /**
     * Capture student output.
     * @param testData String which will be the input data to a scanner.
     * @return Returns the output of the student data as an
     * array of strings.
     */
    public String[] capture(String testData)
    {
        String fb = "";
        Scanner testInput = new Scanner(testData);
        
        //terminal now prints to the Terminal Window like this
        //      terminal.println("Hello");
        PrintStream terminal = System.out;

        //Set up System to print to a byte array
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        
        //Run the students method and capture the results.
        try
        {
            TicketKiosk kiosk = new TicketKiosk();
            kiosk.purchaseTickets(testInput);
            System.out.flush();

            //Print user output to terminal
            terminal.println(output);
            
            //Restore printing
            System.setOut(terminal);
    
            //Replace all crnl with nl
            //Replace all cr with nl
            //Only nl should remain
            String studentData = output.toString();
            studentData = studentData.replaceAll("\r\n", "\n");
            studentData = studentData.replaceAll("\r", "\n");
    
            //Split the lines into an array of strings
            return studentData.split("\n");          
        }
        catch (IOException e)
        {
            //Restore printing
            System.setOut(terminal);

            fb += "Failure in TestTicketKiosk2.\n";
            fb += "File IO error.  Make sure FlightInfo.txt is in" 
                + " the project directory.";  
            fail(fb);            
        }
        catch (NoSuchElementException e)
        {
            //Restore printing
            System.setOut(terminal);

            fb += "Failure in TestTicketKiosk2.\n";
            fb += "There was an error while running purchaseTickets().\n";
            fb += "Your loop is not reading data in the correct order.\n"; 
            fb += "Run TicketKisok's main method and enter the sample\n";
            fb += "data as shown in the test case at the end of the\n";
            fb += "lab documentation.  Your output should match EXACTLY\n";
            fb += "line for line with the sample output.  All prompts\n";
            fb += "MUST match exactly.  You MUST pause and wait for a\n";
            fb += "key press if a flight number is not found.\n";
            fail(fb);                        
        }
        
        
        return null;
    }
    
    /**
     * Perform a check of readOneFlight with the first data set.
     */
    public void checkReadOneFlightA()
    {
        String fb = "";
        String input = "885\nBill Jones\n112233445566\n06/21/14\nBoston";
        Scanner s = new Scanner(input);
        try
        {
            TicketKiosk kiosk = new TicketKiosk();
            Flight flight = kiosk.readOneFlight(s);
            if (flight.getFlightNumber() != 885)
            {
                fb += "Failure in TestTicketKiosk2.\n";
                fb += "Method readOneFlight did not set the flight " 
                    + "number correctly."; 
                fail(fb);               
            }            
            else if (!flight.getDate().equals("06/21/14"))
            {
                fb += "Failure in TestTicketKiosk2.\n";
                fb += "Method readOneFlight did not set the " 
                    + "date correctly."; 
                fail(fb);               
            }            
            else if (!flight.getDestination().equals("Boston"))
            {
                fb += "Failure in TestTicketKiosk2.\n";
                fb += "Method readOneFlight did not set the " 
                    + "destination correctly."; 
                fail(fb);               
            }   
            else if (!flight.getPilot().getName().equals("Bill Jones"))
            {
                fb += "Failure in TestTicketKiosk2.\n";
                fb += "Method readOneFlight did not create the " 
                    + "pilot correctly. The name is wrong."; 
                fail(fb);               
            }   
            else if (!flight.getPilot().getLicense().equals("112233445566"))
            {
                fb += "Failure in TestTicketKiosk2.\n";
                fb += "Method readOneFlight did not create the " 
                    + "pilot correctly. The license is wrong."; 
                fail(fb);               
            }             
        }
        catch (IOException e)
        {
            fb += "Failure in TestTicketKiosk2.\n";
            fb += "File IO error.  Make sure FlightInfo.txt is in " 
                + "the project directory.";  
            fail(fb);
        }
        catch (NoSuchElementException e)
        {
            fb += "Failure in TestTicketKiosk2.\n";
            fb += "Error reading FlightInfo.txt data.\n";  
            fb += "You are possibly reading too much data.\n";
            fb += "Make sure FlightInfo.txt was correctly copied\n";
            fb += "and has not been changed from it's original content.\n";
            fb += "If FlightInfo.txt was correctly copied, then your\n";
            fb += "readOneFlight method is incorrect.  Go back and\n";
            fb += "read the directions. Pay attention to all notes.\n";
            fail(fb);            
        }    
    }
    
    /**
     * Perform a check of readOneFlight with the second data set.
     */
    public void checkReadOneFlightB()
    {
        String fb = "";
        String input = "602\nTim Jones\n113344556677\n06/22/14\nLondon";
        Scanner s = new Scanner(input);
        try
        {
            TicketKiosk kiosk = new TicketKiosk();
            Flight flight = kiosk.readOneFlight(s);
            if (flight.getFlightNumber() != 602)
            {
                fb += "Failure in TestTicketKiosk2.\n";
                fb += "Method readOneFlight did not set the "
                    + "flightNumber correctly."; 
                fail(fb);               
            }            
            else if (!flight.getDate().equals("06/22/14"))
            {
                fb += "Failure in TestTicketKiosk2.\n";
                fb += "Method readOneFlight did not set the " 
                    + "date correctly."; 
                fail(fb);               
            }            
            else if (!flight.getDestination().equals("London"))
            {
                fb += "Failure in TestTicketKiosk2.\n";
                fb += "Method readOneFlight did not set the " 
                    + "destination correctly."; 
                fail(fb);               
            }   
            else if (!flight.getPilot().getName().equals("Tim Jones"))
            {
                fb += "Failure in TestTicketKiosk2.\n";
                fb += "Method readOneFlight did not create the " 
                    + "pilot correctly. The name is wrong."; 
                fail(fb);               
            }   
            else if (!flight.getPilot().getLicense().equals("113344556677"))
            {
                fb += "Failure in TestTicketKiosk2.\n";
                fb += "Method readOneFlight did not create the " 
                    + "pilot correctly. The license is wrong."; 
                fail(fb);               
            }             
        }
        catch (IOException e)
        {
            fb += "Failure in TestTicketKiosk2.\n";
            fb += "File IO error.  Make sure FlightInfo.txt is in " 
                + "the project directory.";  
            fail(fb);
        }
        catch (NoSuchElementException e)
        {
            fb += "Failure in TestTicketKiosk2.\n";
            fb += "Error reading FlightInfo.txt data.\n";  
            fb += "You are possibly reading too much data.\n";
            fb += "Make sure FlightInfo.txt was correctly copied\n";
            fb += "and has not been changed from it's original content.\n";
            fb += "If FlightInfo.txt was correctly copied, then your\n";
            fb += "readOneFlight method is incorrect.  Go back and\n";
            fb += "read the directions. Pay attention to all notes.\n";
            fail(fb);            
        }
        
    }
    
    /**
     * Return the correct output from the sample data.
     * 
     * Customer Name: Al Bundy
     * Customer ssn : 222-22-2222
     * Flight Number: 885
     * Customer Name: Peg Bundy
     * Customer ssn : 111-11-1111
     * Flight Number: 100
     * Extra enter for error pause
     * Customer Name : exit
     * 
     * @return The expected output of the given test data
     * in array format.
     */
    public String[] getExpectedA()
    {
        String[] expected = new String[] {
            "-------------------------------------------------------------",
            "Flt#: 885\tDest: Boston\tPlt: Bill Jones\tDate: 06/21/14",
            "Flt#: 602\tDest: London\tPlt: Tim Jones\tDate: 06/21/14",
            "Flt#: 212\tDest: Berlin\tPlt: Tim Jones\tDate: 06/24/14",
            "Flt#: 650\tDest: Chicago\tPlt: Tim Jones\tDate: 06/26/14",
            "Flt#: 111\tDest: New York\tPlt: Bill Jones\tDate: 06/26/14",
            "Flt#: 190\tDest: Tokyo\tPlt: Tim Jones\tDate: 06/28/14",
            "-------------------------------------------------------------",
            "Enter your name (or type exit):",
            "Enter your social security number:", 
            "Enter your flight number:",
            "",
            "******** TICKET ********",
            "* Name: Al Bundy",
            "* SSN: 222-22-2222",
            "* Flight: 885",
            "* Date: 06/21/14",
            "* Destination: Boston",
            "************************",
            "",
            "-------------------------------------------------------------",
            "Flt#: 885\tDest: Boston\tPlt: Bill Jones\tDate: 06/21/14",
            "Flt#: 602\tDest: London\tPlt: Tim Jones\tDate: 06/21/14",
            "Flt#: 212\tDest: Berlin\tPlt: Tim Jones\tDate: 06/24/14",
            "Flt#: 650\tDest: Chicago\tPlt: Tim Jones\tDate: 06/26/14",
            "Flt#: 111\tDest: New York\tPlt: Bill Jones\tDate: 06/26/14",
            "Flt#: 190\tDest: Tokyo\tPlt: Tim Jones\tDate: 06/28/14",
            "-------------------------------------------------------------",
            "Enter your name (or type exit):",
            "Enter your social security number:",
            "Enter your flight number:",
            "Bad flight number",
            "Hit enter to continue.",
            "-------------------------------------------------------------",
            "Flt#: 885\tDest: Boston\tPlt: Bill Jones\tDate: 06/21/14",
            "Flt#: 602\tDest: London\tPlt: Tim Jones\tDate: 06/21/14",
            "Flt#: 212\tDest: Berlin\tPlt: Tim Jones\tDate: 06/24/14",
            "Flt#: 650\tDest: Chicago\tPlt: Tim Jones\tDate: 06/26/14",
            "Flt#: 111\tDest: New York\tPlt: Bill Jones\tDate: 06/26/14",
            "Flt#: 190\tDest: Tokyo\tPlt: Tim Jones\tDate: 06/28/14",
            "-------------------------------------------------------------",
            "Enter your name (or type exit):",
            "Goodbye"};

        return expected;
    }    
        
    /**
     * Takes a string array and returns it as one
     * massive string with newlines.
     * @param strings The strings to concatenate.
     * @return Returns the string array as a string with
     * each element separated by a newline.
     */
    public String stringArrayToString(String[] strings)
    {
        String result = "";
        for (int i = 0; i < strings.length; i++)
        {
            result += strings[i] + "\n";
        }
        return result;
    }    
}
