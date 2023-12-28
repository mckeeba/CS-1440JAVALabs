/**
 * TestPrelabB.java
 */
import static org.junit.Assert.fail;
import org.junit.Test;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
/**
 * Test WeatherMonth operations.
 *
 * @author Joel Swanson
 * @version 03.13.2014
 */
public class TestPrelabB
{
    /**
     * Check mutators to make sure 
     * properly.
     */
    @Test
    public void checkAccessorsMutators001()
    {
        checkStringAMMonthName("June");
        checkStringAMMonthName("July");
        checkIntAMDaysInMonth(5);
        checkIntAMDaysInMonth(7);
        checkIntArrayAMMaxTemperature(new int[]{1, 2});
        checkIntArrayAMMaxTemperature(new int[]{3, 4, 5});
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
    public void checkSetDayMaxTemp003()
    {
        checkSetDayMaxTemp(2, 85);
        checkSetDayMaxTemp(5, 37);
        checkSetDayMaxTempMinMax();
        checkSetDayMaxTempOOB();
    }    

    /**
     * Make sure displayWeatherData prints properly.
     */
    @Test
    public void checkDisplayWeatherMonth004()
    {
        
        String answer1 = "";
        answer1 += "June\n";
        answer1 += "Day\tMax\n";
        answer1 += "1\t-999\n";
        answer1 += "2\t-999\n";

        String answer2 = "";
        answer2 += "June\n";
        answer2 += "Day\tMax\tMin\n";
        answer2 += "1\t-999\t-999\n";
        answer2 += "2\t-999\t-999\n";
        
        checkDisplayWeatherMonth("June", 2, answer1, answer2);

        answer1  = "August\n";
        answer1 += "Day\tMax\n";
        answer1 += "1\t-999\n";
        answer1 += "2\t-999\n";
        answer1 += "3\t-999\n";

        answer2  = "August\n";
        answer2 += "Day\tMax\tMin\n";
        answer2 += "1\t-999\t-999\n";
        answer2 += "2\t-999\t-999\n";
        answer2 += "3\t-999\t-999\n";

        checkDisplayWeatherMonth("August", 3, answer1, answer2);
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
        fb += "Fail in TestPrelabB.\n";
        fb += "2-arg constructor is incorrect.\n";        
        WeatherMonth weatherMonth = new WeatherMonth(monthName,
            daysInMonth);

        int[] temps = null;
        try
        {
            temps = weatherMonth.getMaxTemperature();
        }
        catch (NullPointerException e)
        {
            temps = null;
        }
            
        if (!weatherMonth.getMonthName().equals(monthName))
        {
            fb += "Field monthName was not set correctly.";
            fail(fb);
        }
        else if (temps == null) 
        {
            fb += "Field maxTemperature was left null.";
            fail(fb);
        }
        else if (temps.length != daysInMonth)
        {
            fb += "Field maxTemperature does not have the ";
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
                fb += "Not All values in maxTemperature were "; 
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
        fb += "Fail in TestPrelabB.\n";
        fb += "No-arg constructor is incorrect.\n";        

        WeatherMonth weatherMonth = new WeatherMonth();
       
        int[] temps = null;
        try
        {
            temps = weatherMonth.getMaxTemperature();
        }
        catch (NullPointerException e)
        {
            temps = null;
        }
            
        if (!weatherMonth.getMonthName().equals("January"))
        {
            fb += "Field monthName was not set correctly.";
            fail(fb);
        }
        else if (temps == null) 
        {
            fb += "Field maxTemperature was left null.";
            fail(fb);
        }
        else if (temps.length != 31)
        {
            fb += "Field maxTemperature does not have the ";
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
                fb += "Not All values in maxTemperature were "; 
                fb += "initialized to -999.";
                fail(fb);                
            }
        }
    }
    
    /** 
     * Check display weather data with data.
     * 
     * Activity 3 requires modification of this method.  both answers
     * must be allowed here to keep the modifications in activity 3 
     * from causing a failure here.  As long as the answer matches one
     * of the two posibilities it is given a pass here.
     * 
     * @param name The name of this WeatherMonth for testing.
     * @param days The number of days in this month for testing.
     * @param answer1 The correct, expected result of the students output
     * after completing the prelab.
     * @param answer2 The correct, expected result of the students output
     * after the student has completed activity 3.  
     */
    public void checkDisplayWeatherMonth(String name, int days,
        String answer1, String answer2)
    {
        String fb = "";
        fb += "Fail in TestPrelabB.\n";
        fb += "Method displayWeatherMonth is incorrect.\n";         
        String output = captureDisplayWeatherData(name, days);
        
        if (!output.equals(answer1) && !output.equals(answer2))
        {
            fb += "Your output is not formatted exactly as specified.\n";
            fb += "Name of month is " + name + " and number of days\n";
            fb += "is " + days + ".\n";
            fb += "This is the expected output:\n";
            fb += answer1;
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
     * Check the setDayMaxTemp method with data.
     * @param day The day to set the given temperature.
     * @param temp The temperature to set.
     */
    public void checkSetDayMaxTemp(int day, int temp)
    {
        String fb = "";
        fb += "Fail in TestPrelabB.\n";
        fb += "Method setDayMaxTemp is incorrect.\n";           
        
        WeatherMonth weatherMonth = new WeatherMonth("June",
            day + 2);
        weatherMonth.setDayMaxTemp(day, temp);
        
        int[] temps = weatherMonth.getMaxTemperature();
        
        //Check all locations EXCEPT for the correct one.
        for (int i = 0; i < temps.length; i++)
        {
            if (i != day - 1)
            {
                if (temps[i] == temp)
                {
                    fb += "Temperature is stored in the wrong position.\n";
                    fb += "Ran setDayMaxTemp(" + day + "," + temp + ");\n";
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
            fb += "Ran setDayMaxTemp(" + day + "," + temp + ");\n";
            fb += temp + " should have been stored at location ";
            fb += (day - 1) + "\n";
            fail(fb);            
        }
    }
    
    /**
     * Check the setDayMaxTempMinMax method with data.
     * This method is here simply because the boundaries were not
     * being tested in the prelab test, where it should have been.
     * 
     */
    public void checkSetDayMaxTempMinMax()
    {
        String fb = "";
        fb += "Fail in TestPrelabB.\n";
        fb += "Method setDayMaxTemp is incorrect.\n";           
        
        WeatherMonth weatherMonth = new WeatherMonth("Test", 31);
        weatherMonth.setDayMaxTemp(1, 85);
        weatherMonth.setDayMaxTemp(31, 95);
        
        int[] temps = weatherMonth.getMaxTemperature();
              
        //Check to see if the correct temp is stored at the correct
        //location.
        if (temps[0] != 85)
        {
            fb += "You did not allow temperature setting on day 1.\n";
            fb += "Ran setDayMaxTemp(1, 85);\n";
            fb += "85 should have been stored at location 0.\n";
            fb += "You had " + temps[0] + " at location 0.\n";
            fail(fb);            
        }
        
        if (temps[30] != 95)
        {
            fb += "You did not allow temperature setting on last day of month.\n";
            fb += "Ran setDayMaxTemp(31, 95); on a month with 31 days.\n";
            fb += "95 should have been stored at location 30.\n";
            fb += "You had " + temps[30] + " at location 30.\n";
            fail(fb);            
        }
    }

    /**
     * Check the setDayMaxTemp method with out of bounds data.
     */
    public void checkSetDayMaxTempOOB()
    {
        String fb = "";
        fb += "Fail in TestPrelabB.\n";
        fb += "Method setDayMaxTemp is incorrect.\n";           
        
        WeatherMonth weatherMonth = new WeatherMonth("June", 2);
        try 
        {
            weatherMonth.setDayMaxTemp(0, 95);
            weatherMonth.setDayMaxTemp(3, 95);
            weatherMonth.setDayMaxTemp(10, 95);
        
            int[] temps = weatherMonth.getMaxTemperature();
        
            //Check all locations to make sure none changed.
            for (int i = 0; i < temps.length; i++)
            {
                if (temps[i] != -999)
                {
                    fb += "This method should not allow setting of data\n";
                    fb += "for days less than 1 or days greater than the\n";
                    fb += "number of days in the month.\n";
                    fb += "Your method incorrectly changed the temperature\n";
                    fb += "in the maxTemperature array when given a day\n";
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
            fb += "in the maxTemperature array when given a day\n";
            fb += "outside of the allowed range.\n";
            fb += "You should ignore out of bounds days.\n";
            fail(fb);           
        }
    }
        
    /**
     * Check the monthName accessor and mutator with normal valid data.
     * @param testData The monthName to set for testing.
     */
    public void checkStringAMMonthName(String testData)
    {
        String fb = "";
        fb += "Fail in TestPrelabB.\n";
        fb += "Accessor or mutator for monthName field is incorrect.\n";

        WeatherMonth weatherMonth = new WeatherMonth("", 1);
        weatherMonth.setMonthName(testData + "X");                       
        String setData = weatherMonth.getMonthName();
        
        if (setData == null)
        {
            fb += "Field is null after setting valid data.";
            fail(fb);                
        }            
        else if (!setData.equals(testData + "X"))
        {
            fb += "Field value is incorrect after setting valid data.";
            fail(fb);                
        }       
    } 
    
    /**
     * Determine if two int arrays are equal in size
     * and each element matches.
     * @param a The first array for equality checking.
     * @param b The second array for equality checking.
     * @return Returns true if a and b are both the same
     * length and each element matches.
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
     * Check the monthName accessor and mutator with normal valid data.
     * @param testData The monthName to set for testing.
     */
    public void checkIntArrayAMMaxTemperature(int[] testData)
    {
        String fb = "";
        fb += "Fail in TestPrelabB.\n";
        fb += "Accessor or mutator for maxTemperature field is incorrect.\n";

        WeatherMonth weatherMonth = new WeatherMonth("", 1);
        weatherMonth.setMaxTemperature(testData);                       
        int[] setData = weatherMonth.getMaxTemperature();
        
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
     * Check the daysInMonth accessor and mutator with normal valid data.
     * @param testData The monthName to set for testing.
     */
    public void checkIntAMDaysInMonth(int testData)
    {
        String fb = "";
        fb += "Fail in TestPrelabB.\n";
        fb += "Accessor or mutator for daysInMonth field is incorrect.";

        int initData = 1;
        if (testData == 1)
        {
            initData = 2;
        }

        WeatherMonth weatherMonth = new WeatherMonth("", initData);
        weatherMonth.setDaysInMonth(testData);                       
        int setData = weatherMonth.getDaysInMonth();
        
        if (setData != testData)
        {
            fail(fb);
        }       
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
