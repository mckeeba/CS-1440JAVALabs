/**
 * TestActivity1B.java
 */

//Put any imports below this line.
import static org.junit.Assert.fail;
import org.junit.Test;
import java.util.Scanner;
import java.io.File; 
import java.io.IOException; 


/**
 * Test WeatherMonth activity 1.
 * 
 * @author Joel Swanson
 * @version 03.14.2014
 */
public class TestActivity1B
{
    /**
     * Check that the two sample input files have not changed.
     */
    @Test
    public void checkFilesNotChanged001()
    {   
        int[] expected;
        
        expected = new int[]{1, 72, 2, 74, 3, 73, 4, 79, 5, 76, 6,
            72, 8, 60, 9, 67, 10, 64, 11, 68, 12, 63, 13, 67, 14, 62,
            15, 65, 16, 67, 17, 66, 18, 65, 19, 60, 20, 61, 21, 66, 22,
            60, 23, 51, 24, 42, 25, 34, 26, 52, 27, 60, 28, 58, 29, 63,
            30, 66, 31, 63, 32, 64, 38, 50};
        checkFileNotChanged("MaxTemperature.txt", expected);
        
        expected = new int[]{1, 50, 2, 52, 3, 54, 4, 56, 5, 56, 6, 56,
            7, 49, 8, 41, 9, 38, 10, 45, 11, 53, 13, 57, 14, 56, 
            15, 51, 16, 48, 17, 52, 18, 47, 19, 48, 20, 39, 21, 35,
            22, 48, 23, 33, 24, 31, 25, 26, 26, 22, 27, 42, 28, 49,
            29, 47, 30, 52, 31, 50, 38, 50};
        checkFileNotChanged("MinTemperature.txt", expected);
            
    }  
    /**
     * Check the setDayMaxTempMinMax method with data.
     */
    @Test
    public void checkSetDayMaxTempMinMax002()
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
            fb += "You should use <= or >=.\n";
            fail(fb);            
        }
        
        if (temps[30] != 95)
        {
            fb += "You did not allow temperature setting on" 
                + " last day of month.\n";
            fb += "Ran setDayMaxTemp(31, 95); on a month with 31 days.\n";
            fb += "95 should have been stored at location 30.\n";
            fb += "You had " + temps[30] + " at location 30.\n";
            fb += "You should use <= or >=.\n";
            fail(fb);            
        }
    }
    
    /**
     * Check reading of file data in readMaxTempFile.
     * @throws IOException if the file cant be found.
     */
    @Test
    public void checkReadMaxTempFile003() throws IOException
    {        
        int[] expected;
        
        expected = new int[]{72, 74, 73, 79, 76, 72, -999, 60, 67, 64,
            68, 63, 67, 62, 65, 67, 66, 65, 60,   61, 66, 60, 51,
            42, 34, 52, 60, 58, 63, 66, 63};
        checkReadMaxTempFile("MaxTemperature.txt", expected);
        
        expected = new int[] {50, 52, 54, 56, 56, 56, 49, 41, 38, 45,
            53, -999, 57, 56, 51, 48, 52, 47, 48, 39, 35, 48, 33,
            31,   26, 22, 42, 49, 47, 52, 50};
              
        checkReadMaxTempFile("MinTemperature.txt", expected);
    }
    
    /**
     * Check to see if an int array file has changed.
     * @param filename The name of the file to check.
     * @param expected An int array of ehe expected 
     * values in the file
     */
    public void checkFileNotChanged(String filename, int[] expected)
    {
        String fb = "";
        fb += "Fail in TestActivity1B.\n";
        fb += "Text files have been modified.\n";
        fb += filename + " has been changed.\n";
        fb += "Changing this file is OK for testing\n";
        fb += "however it must be changed back to pass\n";
        fb += "the test.\n";        
        
        try
        {
            File inputFile  = new File(filename);
            Scanner scanner = new Scanner(inputFile);  
            int i = 0;
            while (scanner.hasNextInt())
            {
                if (i >= expected.length)
                {
                    fail(fb);
                }
                else if (scanner.nextInt() != expected[i++])
                {
                    fail(fb);
                }
    
            }
            if (i != expected.length)
            {
                fail(fb);
            } 
        }
        catch (IOException e)
        {
            fail(filename + " file not found.");            
        }
    }
    
    /**
     * Check the toString method for correctness.
     */
    @Test
    public void checkToString004()
    {
        String[] expected;
        expected = new String[4];
        expected[0] = "June";
        expected[1] = "Day      Max";
        expected[2] = " 1       N/A";
        expected[3] = " 2       N/A";
        checkToString(expected);

        expected = new String[5];
        expected[0] = "August";
        expected[1] = "Day      Max";
        expected[2] = " 1       N/A";
        expected[3] = " 2       N/A";
        expected[4] = " 3       N/A";
        checkToString(expected);
    }    
    
    /**
     * Check the toString method with data.
     * @param expected The string array which represents
     * the correct output of the toString method.
     */
    public void checkToString(String[] expected)
    {
        String fb = "";
        fb += "Fail in TestActivity1B.\n";
        fb += "Method toString is incorrect.\n";         
        
        WeatherMonth weatherMonth = new WeatherMonth(expected[0],
            expected.length - 2);

        String[] output = weatherMonth.toString().split("\n");
        
        if (output.length < expected.length)
        {
            fb += "Your toString output does not have enough lines.\n";
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
                if (!output[i].startsWith(expected[i]))
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
     * Check readMaxTempFile with data.
     * @param filename The filename to open and check.
     * @param expected An array of integers which is the
     * correct solution.
     * @throws IOException Throws this if the file
     * cannot be found.
     */
    public void checkReadMaxTempFile(String filename, 
        int[] expected) throws IOException
    {
        String fb = "";
        fb += "Fail in TestActivity1B.\n";
        fb += "Method readMaxTempFile is incorrect.\n";        


        try
        {
            WeatherMonth weatherMonth = new WeatherMonth("January", 31);
            weatherMonth.readMaxTempFile(filename);            
            
            int[] temps = weatherMonth.getMaxTemperature();
        
            if (!intArrayEquals(temps, expected))
            {
                fb += "Data in maxTemperature array does not match\n";
                fb += "data from the file.";
                fail(fb);
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            fb += "You tried to insert data into a day which\n";
            fb += "is less than 1 or greater than 31.\n";
            fb += "You should use setDayMaxTemp to set temperatures.\n";
            fb += "Method setDayMaxTemp prevents setting data\n";
            fb += "outside of the allowed range.\n";
            fail(fb);            
        }
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
    
}
