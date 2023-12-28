/**
 * WeatherComputation.java
 */

//Put any imports below this line.

/**
 * Static methods library which compute averages and other
 * computations on integer arrays of temperatures.
 * 
 * @author Joel Swanson 
 * @version 03.29.2014
 */
public class WeatherComputation
{    
    /**
     * Average an array of temperatures.
     * @param temperatures An array storing temperatures as ints.
     * @return average Returns the average of the array of ints.
     */
    public static double averageTemperature(int[] temperatures)
    {   
        int validTemps = 0;
        double total = 0;

        for (int index = 0; index < temperatures.length; index++)
        {
            if (temperatures[index] != -999)
            {
                total += temperatures[index];
                validTemps++;
            }
        }
        double average = total / validTemps;

        return average;

    }

    /**
     * Find the highest in an array of temperatures.
     * @param temperatures An array storing temperatures as ints.
     * @return max The largest value from the the array of ints.
     */
    public static int highestTemperature(int[] temperatures)
    {      
        int max = temperatures[0]; 
        for (int i = 1; i < temperatures.length; i++)
        {
            if (temperatures[i] > max && temperatures[i] != -999)
            {
                max = temperatures[i];
            }

        }

        return max;
    }  

    /**
     * Find the lowest in an array of temperatures.
     * @param temperatures An array storing temperatures as ints.
     * @return min The lowest value from the the array of ints.
     */
    public static int lowestTemperature(int[] temperatures)
    {
        int min = temperatures[0]; 
        for (int j = 1; j < temperatures.length; j++)
        {
            if (temperatures[j] < min && temperatures[j] != -999)
            {
                min = temperatures[j];
            }
        }

        return min;
    }    

    /**
     * Return the total number of missing days.  That is days with
     * -999 recorded as the temperatures.
     * @param temperatures An array storing temperatures as ints.
     * @return count The number of -999s found.
     */
    public static int numberMissing(int[] temperatures)
    {       
        int count = 0;
        for (int c = 1; c < temperatures.length; c++)
        {
            if (temperatures[c] == -999)
            {
                count++;
            }
        }

        return count;
    } 

    /**
     * Calculate heating degree day.
     * @param max The highest temperature for a given day.
     * @param min The lowest temperature for a given day.
     * @return calculation heating degree day data for this day.
     */
    public static double hdd(int max, int min)
    {
        //double hdd = 0.0;
        double taverage = (double) (max + min) / 2;

        if (max == -999 || min == -999)
        {
            return 0.0;
        }

        if (max < min)
        {
            return 0.0;
        }

        if (taverage < 65.0)
        {
            return 65.0 - taverage;
        }
        else
        {
            return 0.0;
        }
    }

    /**
     * Calculate cooling degree day.
     * @param max The highest temperature for a given day.
     * @param min The lowest temperature for a given day.
     * @return calculation cooling degree day data for this day.
     */
    public static double cdd(int max, int min)
    {
        double taverage = (double) (max + min) / 2;

        if (max == -999 || min == -999)
        {
            return 0.0;
        }

        if (max < min)
        {
            return 0.0;
        }

        if (taverage > 65.0)
        {
            return taverage - 65.0;
        }
        else 
        {
            return 0.0;
        }

    }    

    /**
     * Sum monthly heating degree days.
     * @param maxTemp An array with the highest temperatures for each day
     * in a given month.
     * @param minTemp An array with the lowest temperatures for each day
     * in a given month.
     * @return sum  The sum of the heating degree days.
     */
    public static double monthHdd(int[] maxTemp, int[] minTemp)
    {
        double sum = 0.0;
        for (int i = 0; i < maxTemp.length; i++)
        {
            sum += hdd(maxTemp[i], minTemp[i]);
        }
        return sum;        
    }

    /**
     * Sum monthly cooling degree days.
     * @param maxTemp An array with the highest temperatures for each day
     * in a given month.
     * @param minTemp An array with the lowest temperatures for each day
     * in a given month.
     * @return sum The sum of the cooling degree days.
     */
    public static double monthCdd(int[] maxTemp, int[] minTemp)
    {
        double sum = 0.0;
        for (int j = 0; j < maxTemp.length; j++)
        {
            sum += cdd(maxTemp[j], minTemp[j]);
        }

        return sum;        
    }     
}
