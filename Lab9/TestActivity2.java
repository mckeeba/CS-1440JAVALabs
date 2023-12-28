/**
 * TestActivity2.java
 */

//Put any imports below this line.
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * Test WeatherComputation activity 2 operations.
 * 
 * @author Joel Swanson
 * @version 03.15.2014
 */
public class TestActivity2
{
    /**
     * Test the averageTemperature method.
     */
    @Test
    public void checkAverageTemperature001()
    {   
        String fb = "";
        fb += "Fail in TestActivity2.\n";
        fb += "Method averageTemperature is incorrect.\n";            

        int[] testArray;
        double average;
        
        testArray = new int[]{20, -999, -10, 50, -999, 40, 30};
        average = WeatherComputation.averageTemperature(testArray);
        if (average < 25.999 || average > 26.001)
        {
            fb += "Returned incorrect average with the following values:\n";
            fb += "\t20, -999, -10, 50, -999, 40, 30\n";
            fb += "Should have returned 26.0.\n";
            fb += "Your value was " + average + ".";            
            fail(fb);
        }
        
        testArray = new int[]{10, -999, -999, -10, 21, -999, 10, 20};
        average = WeatherComputation.averageTemperature(testArray);
        if (average < 10.1999 || average > 10.2001)
        {
            fb += "Returned incorrect average with the following values:\n";
            fb += "\t10, -999, -999, -10, 21, -999, 10, 20\n";
            fb += "Should have returned 10.2.\n";
            fb += "Your value was " + average + ".";
            fail(fb);
        }
    } 
    
    /**
     * Test the highestTemperature method.
     */
    @Test
    public void checkHighestTemperature002()
    {   
        String fb = "";
        fb += "Fail in TestActivity2.\n";
        fb += "Method highestTemperature is incorrect.\n";            
        
        int[] testArray;
        int high;
        
        testArray = new int[]{20, -999, -10, 50, -999, 40, 30};
        high = WeatherComputation.highestTemperature(testArray);
        if (high != 50)
        {
            fb += "Returned incorrect high temp with the following values:\n";
            fb += "\t20, -999, -10, 50, -999, 40, 30\n";
            fb += "Should have returned 50.0.\n";
            fb += "Your value was " + high + "."; 
            fail(fb);
        }
        
        testArray = new int[]{10, -999, -999, 20, 10, -999, -20, 10};
        high = WeatherComputation.highestTemperature(testArray);
        if (high != 20)
        {
            fb += "Returned incorrect high temp with the following values:\n";
            fb += "\t10, -999, -999, 20, 10, -999, -20, 10\n";
            fb += "Should have returned 20.0.\n";
            fb += "Your value was " + high + ".";             fail(fb);
        }
    }      
    
    /**
     * Test the lowestTemperature method.
     */
    @Test
    public void checkLowestTemperature003()
    {   
        String fb = "";
        fb += "Fail in TestActivity2.\n";
        fb += "Method lowestTemperature is incorrect.\n";            
        
        int[] testArray;
        int low;
        
        testArray = new int[]{20, -999, 10, 50, -999, -40, 30};
        low = WeatherComputation.lowestTemperature(testArray);
        if (low != -40)
        {
            fb += "Returned incorrect low temp with the following values:\n";
            fb += "\t20, -999, 10, 50, -999, -40, 30\n";
            fb += "Should have returned -40.0.\n";
            fb += "Your value was " + low + ".";
            fail(fb);
        }
        
        testArray = new int[]{-8, -999, -999, 20, 10, -999, 8, 10};
        low = WeatherComputation.lowestTemperature(testArray);
        if (low != -8)
        {
            fb += "Returned incorrect low temp with the following values:\n";
            fb += "\t-8, -999, -999, 20, 10, -999, 8, 10\n";
            fb += "Should have returned -8.0.\n";
            fb += "Your value was " + low + ".";
            fail(fb);
        }
    }      
  
    /**
     * Test the numberMissing method.
     */
    @Test
    public void checkNumberMissing004()
    {   
        String fb = "";
        fb += "Fail in TestActivity2.\n";
        fb += "Method numberMissing is incorrect.\n";            
        fb += "Returned incorrect number of missing data items" 
            + " with the following values:\n";
        
        int[] testArray;
        int miss;
        
        testArray = new int[]{20, -999, 10, -50, -999, 40, 30};
        miss = WeatherComputation.numberMissing(testArray);
        if (miss != 2)
        {
            fb += "\t20, -999, 10, -50, -999, 40, 30\n";
            fb += "Should have returned 2.\n";
            fb += "Your value was " + miss + ".";
            fail(fb);
        }
        
        testArray = new int[]{10, 15, -8, 20, 10, -999, 8, 10};
        miss = WeatherComputation.numberMissing(testArray);
        if (miss != 1)
        {
            fb += "\t10, 15, -8, 20, 10, -999, 8, 10\n";
            fb += "Should have returned 1.\n";
            fb += "Your value was " + miss + ".";            
            fail(fb);
        }
        
        testArray = new int[]{10, -15, 8, 20, 10, -55, 8, 10, 99, -1};
        miss = WeatherComputation.numberMissing(testArray);
        if (miss != 0)
        {
            fb += "\t10, -15, 8, 20, 10, -55, 8, 10, 99, -1\n";
            fb += "Should have returned 0.\n";
            fb += "Your value was " + miss + ".";            
            fail(fb);
        }        
    }      
    
}
