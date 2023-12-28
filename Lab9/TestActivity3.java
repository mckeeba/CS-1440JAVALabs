/**
 * TestActivity3.java
 */

//Put any imports below this line.
import static org.junit.Assert.fail;
import org.junit.Test; 
import java.io.IOException; 
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
/**
 * Test operation of activity 3.
 * 
 * @author Joel Swanson
 * @version 03.15.2014
 */
public class TestActivity3
{
    //Put instance variables below this line.  
     

    /**
     * Check mutators to make sure 
     * properly.
     */
    @Test
    public void checkAccessorsMutators001()
    {
        checkIntArrayAMMinTemperature(new int[]{1, 2});
        checkIntArrayAMMinTemperature(new int[]{3, 4, 5});
    }  
    
    /**
     * Check constructors.
     */
    @Test
    public void checkConstructors002()
    {
        checkNoArgConstructor();
        check2ArgConstructor("June", 2);
        check2ArgConstructor("July", 3);
    } 
    
    /**
     * Make sure setDayMaxTemp functions properly with
     * in range data.
     */
    @Test
    public void checkSetDayMinTemp003()
    {
        checkSetDayMinTemp(2, 85);
        checkSetDayMinTemp(5, 37);
        checkSetDayMinTempOOB();
    }    
    
    /**
     * Check reading of file data in readMinTempFile.
     * @throws IOException if the file cant be found.
     */
    @Test
    public void checkReadMinTempFile004() throws IOException
    {        
        int[] expected;
        
        expected = new int[]{72, 74, 73, 79, 76, 72, -999, 60, 67, 64,
            68, 63, 67, 62, 65, 67, 66, 65, 60,   61, 66, 60, 51,
            42, 34, 52, 60, 58, 63, 66, 63};
        checkReadMinTempFile("MaxTemperature.txt", expected);
        
        expected = new int[] {50, 52, 54, 56, 56, 56, 49, 41, 38, 45,
            53, -999, 57, 56, 51, 48, 52, 47, 48, 39, 35, 48, 33,
            31,   26, 22, 42, 49, 47, 52, 50};
              
        checkReadMinTempFile("MinTemperature.txt", expected);
    }
    
    /**
     * Check the toString method for correctness.
     */
    @Test
    public void checkToString005()
    {
        String[] expected;
        expected = new String[4];
        expected[0] = "June";
        expected[1] = "Day      Max       Min";
        expected[2] = " 1       N/A       N/A";
        expected[3] = " 2       N/A       N/A";
        checkToString(expected);

        expected = new String[5];
        expected[0] = "August";
        expected[1] = "Day      Max       Min";
        expected[2] = " 1       N/A       N/A";
        expected[3] = " 2       N/A       N/A";
        expected[4] = " 3       N/A       N/A";
        checkToString(expected);
    }     

    /**
     * Make sure displayWeatherData prints properly.
     */
    @Test
    public void checkDisplayWeatherData004()
    {
        
        String answer = "";
        answer += "June\n";
        answer += "Day\tMax\tMin\n";
        answer += "1\t-999\t-999\n";
        answer += "2\t-999\t-999\n";
        
        checkDisplayWeatherMonth("June", 2, answer);

        answer  = "August\n";
        answer += "Day\tMax\tMin\n";
        answer += "1\t-999\t-999\n";
        answer += "2\t-999\t-999\n";
        answer += "3\t-999\t-999\n";

        checkDisplayWeatherMonth("August", 3, answer);
    }
    
    /** 
     * Check display weather data with data.
     * @param name The name of this WeatherMonth for testing.
     * @param days The number of days in this month for testing.
     * @param answer The correct, expected result of the students output.
     */
    public void checkDisplayWeatherMonth(String name, int days, String answer)
    {
        String fb = "";
        fb += "Fail in TestPrelabB.\n";
        fb += "Method displayWeatherMonth is incorrect.\n";         
        String output = captureDisplayWeatherData(name, days);
        
        if (!output.equals(answer))
        {
            fb += "Your output is not formatted exaclty as specified.\n";
            fb += "Name of month is " + name + " and number of days\n";
            fb += "is " + days + ".\n";
            fb += "This is the expected output:\n";
            fb += answer;
            fb += "\nThis is your output:\n";
            fb += output;
            fb += "\nMake sure you printed the month name first.\n";
            fb += "Make sure you printed the header line.\n";
            fb += "Make sure each line has a number and a temperature.\n";
            fb += "Make sure tabs separate data items in each row\n";
            fb += "including the header row.\n";
            fail(fb);
        }
    }
    
    /**
     * Check readMinTempFile with data.
     * @param filename The filename to open and check.
     * @param expected An array of integers which is the
     * correct solution.
     * @throws IOException Throws this if the file
     * cannot be found.
     */
    public void checkReadMinTempFile(String filename, 
        int[] expected) throws IOException
    {
        String fb = "";
        fb += "Fail in TestActivity3.\n";
        fb += "Method readMaxTempFile is incorrect.\n";        

        try
        {
            WeatherMonth weatherMonth = new WeatherMonth("January", 31);
            weatherMonth.readMinTempFile(filename);            
            
            int[] temps = weatherMonth.getMinTemperature();
        
            if (!intArrayEquals(temps, expected))
            {
                fb += "Data in minTemperature array does not match\n";
                fb += "data from the file.";
                fail(fb);
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            fb += "You tried to insert data into a day which\n";
            fb += "is less than 1 or greater than 31.\n";
            fb += "You should use setDayMinTemp to set temperatures.\n";
            fb += "Method setDayMinTemp prevents setting data\n";
            fb += "outside of the allowed range.\n";
            fail(fb);            
        }
    }
    
    /**
     * Check the setDayMinTemp method with data.
     * @param day The day to set the given temperature.
     * @param temp The temperature to set.
     */
    public void checkSetDayMinTemp(int day, int temp)
    {
        String fb = "";
        fb += "Fail in TestActivity3.\n";
        fb += "Method setDayMinTemp is incorrect.\n";           
        
        WeatherMonth weatherMonth = new WeatherMonth("June",
            day + 2);
        weatherMonth.setDayMinTemp(day, temp);
        
        int[] temps = weatherMonth.getMinTemperature();
        
        //Check all locations EXCEPT for the correct one.
        for (int i = 0; i < temps.length; i++)
        {
            if (i != day - 1)
            {
                if (temps[i] == temp)
                {
                    fb += "Temperature is stored in the wrong position.\n";
                    fb += "Ran setDayMinTemp(" + day + "," + temp + ");\n";
                    fb += temp + " should have been stored at location ";
                    fb += (day - 1) + "\n";
                    fb += temp + " was found at location " + i + ".\n";
                    fail(fb);
                }
            }
        }
        
        //Check to see if the correct temp is stored at the correct
        //location.
        if (temps[day - 1] != temp)
        {
            fb += "Temperature is not stored in the array.\n";
            fb += "Ran setDayMinTemp(" + day + "," + temp + ");\n";
            fb += temp + " should have been stored at location ";
            fb += (day - 1) + "\n";
            fail(fb);            
        }
    }
    
    /**
     * Check the setDayMinTemp method with out of bounds data.
     */
    public void checkSetDayMinTempOOB()
    {
        String fb = "";
        fb += "Fail in TestActivity3.\n";
        fb += "Method setDayMinTemp is incorrect.\n";           
        
        WeatherMonth weatherMonth = new WeatherMonth("June", 2);
        try 
        {
            weatherMonth.setDayMinTemp(0, 95);
            weatherMonth.setDayMinTemp(3, 95);
            weatherMonth.setDayMinTemp(10, 95);
        
            int[] temps = weatherMonth.getMinTemperature();
        
            //Check all locations to make sure none changed.
            for (int i = 0; i < temps.length; i++)
            {
                if (temps[i] != -999)
                {
                    fb += "This method should not allow setting of data\n";
                    fb += "for days less than 1 or days greater than the\n";
                    fb += "number of days in the month.\n";
                    fb += "Your method incorrectly changed the temperature\n";
                    fb += "in the minTemperature array when given a day\n";
                    fb += "outside of the allowed range.\n";
                    fb += "You should ignore out of bounds days.\n";
                    fail(fb);
                }
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            fb += "This method should not allow setting of data\n";
            fb += "for days less than 1 or days greater than the\n";
            fb += "number of days in the month.\n";
            fb += "Your method attempted to change the temperature\n";
            fb += "in the minTemperature array when given a day\n";
            fb += "outside of the allowed range.\n";
            fb += "You should ignore out of bounds days.\n";
            fail(fb);           
        }
    }
    
    /**
     * Check 2-arg constructor.
     * @param monthName The initial name of this WeatherMonth.
     * @param daysInMonth The initial number of days in this WeatherMonth.
     */
    public void check2ArgConstructor(String monthName,
        int daysInMonth)
    {
        String fb = "";
        fb += "Fail in TestActivity3.\n";
        fb += "2-arg constructor is incorrect.\n";        
        WeatherMonth weatherMonth = new WeatherMonth(monthName,
            daysInMonth);

        int[] temps = null;
        try
        {
            temps = weatherMonth.getMinTemperature();
        }
        catch (NullPointerException e)
        {
            temps = null;
        }
            
        if (temps == null) 
        {
            fb += "Field minTemperature was left null.";
            fail(fb);
        }
        else if (temps.length != daysInMonth)
        {
            fb += "Field minTemperature does not have the ";
            fb += "correct length.";
            fail(fb);
        }
        else 
        {
            boolean fail = false;
            for (int i = 0; i < temps.length; i++)
            {
                if (temps[i] != -999)
                {
                    fail = true;
                }
            }
            if (fail)
            {
                fb += "Not All values in minTemperature were "; 
                fb += "initialized to -999.";
                fail(fb);                
            }
        }
    }
    
    
    /**
     * Check 2-arg constructor.
     */
    public void checkNoArgConstructor()
    {
        String fb = "";
        fb += "Fail in TestActivity3.\n";
        fb += "No-arg constructor is incorrect.\n";        

        WeatherMonth weatherMonth = new WeatherMonth();
       
        int[] temps = null;
        try
        {
            temps = weatherMonth.getMinTemperature();
        }
        catch (NullPointerException e)
        {
            temps = null;
        }
            

        if (temps == null) 
        {
            fb += "Field minTemperature was left null.";
            fail(fb);
        }
        else if (temps.length != 31)
        {
            fb += "Field minTemperature does not have the ";
            fb += "correct length.";
            fail(fb);
        }
        else 
        {
            boolean fail = false;
            for (int i = 0; i < temps.length; i++)
            {
                if (temps[i] != -999)
                {
                    fail = true;
                }
            }
            if (fail)
            {
                fb += "Not All values in minTemperature were "; 
                fb += "initialized to -999.";
                fail(fb);                
            }
        }
    }
    
    /**
     * Check the minTemperature accessor and mutator with normal valid data.
     * @param testData The temperature array to use for testing.
     */
    public void checkIntArrayAMMinTemperature(int[] testData)
    {
        String fb = "";
        fb += "Fail in TestActivity3.\n";
        fb += "Accessor or mutator for minTemperature field is incorrect.\n";

        WeatherMonth weatherMonth = new WeatherMonth("", 1);
        weatherMonth.setMinTemperature(testData);                       
        int[] setData = weatherMonth.getMinTemperature();
        
        if (setData == null)
        {
            fb += "Field is null after setting valid data.";
            fail(fb);                
        }            
        else if (!intArrayEquals(testData, setData))
        {
            fb += "Field value is incorrect after setting valid data.\n";
            fb += "Set array and get array differ in size or\n";
            fb += "one or more elements are different.\n";
            fail(fb);                
        }       
    }   
    
    /**
     * Check the toString method with data.
     * @param expected The string array which represents
     * the correct output of the toString method.
     */
    public void checkToString(String[] expected)
    {
        String fb = "";
        fb += "Fail in TestActivity3.\n";
        fb += "Method toString is incorrect.\n";         
        
        WeatherMonth weatherMonth = new WeatherMonth(expected[0],
            expected.length - 2);

        String[] output = weatherMonth.toString().split("\n");
        
        if (output.length < expected.length)
        {
            fb += "Your toString output does have enough lines.\n";
            fail(fb);
        }
        else if (output.length > expected.length)
        {
            fb += "Your toString output has too many lines.\n";
            fail(fb);
        }
        
        if (!output[0].equals(expected[0]))
        {
            fb += "Your first line should be the month name.\n";
            fb += "\nExpected output:\n";
            fb += stringArrayToString(expected);
            fb += "\nYour toString output:\n";
            fb += stringArrayToString(output);
        }
        else
        {
            for (int i = 1; i < output.length; i++)
            {
                if (!output[i].equals(expected[i]))
                {
                    fb += "Your line " + (i + 1) + " does not ";
                    fb += "match the expected line " + (i + 1);
                    fb += ".\n\nExpected output:\n";
                    fb += stringArrayToString(expected);
                    fb += "\nYour toString output:\n";
                    fb += stringArrayToString(output);
                    fail(fb);
                }
            }
        }        
    }

    /**
     * Convert a string array into a simple string with
     * each element on a separate line.
     * @param strings The string array.
     * @return Returns a single string built from the
     *  strings array parameter
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
    
    /**
     *Determine if two int arrays are equal in size
     *and each element matches.
     *@param a The first array to check for equality.
     *@param b The second array to check for equality.
     *
     *@return Returns true if the two arrays are the same length
     *AND each element matches.
     */
    private boolean intArrayEquals(int[] a, int[] b)
    {        
        if (a == b)
        {
            return true;
        }
        else if (a.length != b.length)
        {
            return false;
        }
        else 
        {
            //Check each element.  Arrays are unequal if even one
            //is different.
            for (int i = 0; i < a.length; i++)
            {
                if (a[i] != b[i])
                {
                    return false;
                }
            }
        }
        
        //Same length and all elements are the same.
        return true;
    }

    /**
     * Capture student output.
     * @param name The name of this WeatherMonth for testing.
     * @param days The number of days in this WeatherMonth.
     * @return Returns the output of the student output data as an
     * array of strings.
     */
    public String captureDisplayWeatherData(String name, int days)
    {
        String fb = "";
        //Scanner testInput = new Scanner(testData);
        
        //terminal now prints to the Terminal Window like this
        //      terminal.println("Hello");
        PrintStream terminal = System.out;

        //Set up System to print to a byte array
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        
        //Run the students method and capture the results.

        WeatherMonth weatherMonth = new WeatherMonth(name, days);
        weatherMonth.displayWeatherMonth();
        System.out.flush();

        //Print user output to terminal
        //terminal.println(output);
        
        //Restore printing
        System.setOut(terminal);

        //Replace all crnl with nl
        //Replace all cr with nl
        //Only nl should remain
        String studentData = output.toString();
        studentData = studentData.replaceAll("\r\n", "\n");
        studentData = studentData.replaceAll("\r", "\n");

        //Split the lines into an array of strings
        //return studentData.split("\n");          
        return studentData;

    }
    
    
}
