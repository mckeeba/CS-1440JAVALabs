/**
 * TestPostlab.java
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
public class TestPostlab
{
    /**
     * Test the heating degree day method (hdd).
     */
    @Test
    public void checkHdd001()
    {   
        String fb = "";
        fb += "Fail in TestPostlab.\n";
        fb += "Method hdd is incorrect.\n";            
        double result = 0.0;
        
        result = WeatherComputation.hdd(70, 60);
        if (result < -0.9999 || result > 0.0001)
        {
            fb += "Max 70 and min 60 should return 0.0.\n";
            fb += "Yours returned " + result + ".\n";
            fail(fb);
        }
        
        result = WeatherComputation.hdd(80, 70);
        if (result < -0.9999 || result > 0.0001)
        {
            fb += "Max 80 and min 70 should return 0.0.\n";
            fb += "Yours returned " + result + ".\n";
            fail(fb);
        }

        result = WeatherComputation.hdd(70, 59);
        if (result < 0.4999 || result > 0.5001)
        {
            fb += "Max 70 and min 59 should return 0.5.\n";
            fb += "Yours returned " + result + ".\n";
            fail(fb);
        }  
        
        result = WeatherComputation.hdd(50, 60);
        if (result < -0.9999 || result > 0.0001)
        {
            fb += "Max 50 and min 60 should return 0.0.\n";
            fb += "Yours returned " + result + ".\n";
            fail(fb);
        }        
        
        result = WeatherComputation.hdd(-999, 100);
        if (result < -0.9999 || result > 0.0001)
        {
            fb += "Max -999 and min 100 should return 0.0.\n";
            fb += "Yours returned " + result + ".\n";
            fail(fb);
        }  
        
        result = WeatherComputation.hdd(100, -999);
        if (result < -0.9999 || result > 0.0001)
        {
            fb += "Max 100 and min -999 should return 0.0.\n";
            fb += "Yours returned " + result + ".\n";
            fail(fb);
        }  
    } 
    
    /**
     * Test the cooling degree day method (cdd).
     */
    @Test
    public void checkCdd002()
    {   
        String fb = "";
        fb += "Fail in TestPostlab.\n";
        fb += "Method cdd is incorrect.\n";            
        double result = 0.0;
        
        result = WeatherComputation.cdd(70, 60);
        if (result < -0.9999 || result > 0.0001)
        {
            fb += "Max 70 and min 60 should return 0.0.\n";
            fb += "Yours returned " + result + ".\n";
            fail(fb);
        }
        
        result = WeatherComputation.cdd(80, 70);
        if (result < 9.9999 || result > 10.0001)
        {
            fb += "Max 80 and min 70 should return 10.0.\n";
            fb += "Yours returned " + result + ".\n";
            fail(fb);
        }
        
        result = WeatherComputation.cdd(71, 60);
        if (result < 0.4999 || result > 0.5001)
        {
            fb += "Max 71 and min 60 should return 0.5.\n";
            fb += "Yours returned " + result + ".\n";
            fail(fb);
        }  
        
        result = WeatherComputation.cdd(50, 60);
        if (result < -0.9999 || result > 0.0001)
        {
            fb += "Max 50 and min 60 should return 0.0.\n";
            fb += "Yours returned " + result + ".\n";
            fail(fb);
        }
        
        result = WeatherComputation.hdd(-999, 2000);
        if (result < -0.9999 || result > 0.0001)
        {
            fb += "Max -999 and min 2000 should return 0.0.\n";
            fb += "Yours returned " + result + ".\n";
            fail(fb);
        }  
        
        result = WeatherComputation.hdd(2000, -999);
        if (result < -0.9999 || result > 0.0001)
        {
            fb += "Max 2000 and min -999 should return 0.0.\n";
            fb += "Yours returned " + result + ".\n";
            fail(fb);
        }         
        
    } 
    
    /**
     * Test the monthHdd method.
     */
    @Test
    public void checkMonthHdd003()
    {   
        String fb = "";
        fb += "Fail in TestPostlab.\n";
        fb += "Method monthHdd is incorrect.\n";            
        int[] max;
        int[] min;
        double hddMonth = 0;
        
        max = new int[]{100, 65, 70, 85, -999, 90};
        min = new int[]{65, 45, 10, 65, 45, -999};        
        hddMonth = WeatherComputation.monthHdd(max, min);        
        
        if (hddMonth < 34.9999 || hddMonth > 35.0001)
        {
            fb += "Max: 100, 65, 70, 85, -999, 90\n";
            fb += "Min: 65, 45, 10, 65, 45, -999\n";
            fb += "Method monthHdd should have returned 35.0.\n";
            fb += "Yours returned " + hddMonth + ".\n";
            fail(fb);
        }
        
        max = new int[]{25, 70, 80, 65, -999, -999};
        min = new int[]{15, 60, 70, 50, -999, 45};        
        hddMonth = WeatherComputation.monthHdd(max, min);        
        
        if (hddMonth < 52.4999 || hddMonth > 52.5001)
        {
            fb += "Max: 25, 70, 80, 65, -999, -999\n";
            fb += "Min: 15, 60, 70, 50, -999, 45\n";
            fb += "Method monthHdd should have returned 52.5.\n";
            fb += "Yours returned " + hddMonth + ".\n";
            fail(fb);
        }        
    }      
    
    /**
     * Test the monthCdd method.
     */
    @Test
    public void checkMonthCdd004()
    {   
        String fb = "";
        fb += "Fail in TestPostlab.\n";
        fb += "Method monthCdd is incorrect.\n";            
        int[] max;
        int[] min;
        double cddMonth = 0;
        
        max = new int[]{100, 65, 70, 85, -999, 90};
        min = new int[]{65, 45, 10, 65, 45, -999};        
        cddMonth = WeatherComputation.monthCdd(max, min);        
        
        if (cddMonth < 27.4999 || cddMonth > 27.5001)
        {
            fb += "Max: 100, 65, 70, 85, -999, 90\n";
            fb += "Min: 65, 45, 10, 65, 45, -999\n";
            fb += "Method monthCdd should have returned 27.5.\n";
            fb += "Yours returned " + cddMonth + ".\n";
            fail(fb);
        }
        
        max = new int[]{25, 70, 80, 65, -999, -999};
        min = new int[]{15, 60, 70, 50, -999, 45};        
        cddMonth = WeatherComputation.monthCdd(max, min);        
        
        if (cddMonth < 9.9999 || cddMonth > 10.0001)
        {
            fb += "Max: 25, 70, 80, 65, -999, -999\n";
            fb += "Min: 15, 60, 70, 50, -999, 45\n";
            fb += "Method monthCdd should have returned 10.0.\n";
            fb += "Yours returned " + cddMonth + ".\n";
            fail(fb);
        }        
    }      
    
}
