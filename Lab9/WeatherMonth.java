/**
 * WeatherMonth.java
 * 
 */

//Put any imports below this line.
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * Short, one-line description of WeatherMonth class here.
 * 
 * Optionally, include a paragraph that provides a more 
 * detailed description.
 *
 * @author Gus M. 
 * @version 4/11/22
 */
public class WeatherMonth
{
    //fields for weather month class
    private int[] maxTemperature;
    private String monthName;
    private int daysInMonth;
    private int[] minTemperature;

    /**
     * no args constructor.
     */
    public WeatherMonth()
    {
        monthName = "January";
        daysInMonth = 31;
        maxTemperature = new int[daysInMonth];
        minTemperature = new int[daysInMonth];

        /**
         * this portion of code is to store -999 in every element 
         * from maxTemp0 to maxTemp30  *for loop ?
         */
        for (int i = 0; i < 31; i++)
        {
            maxTemperature[i] = -999;
            minTemperature[i] = -999;
        }

    }

    /**
     * two args constructor for weathermonth class.
     * @param monthName 
     * @param daysInMonth . 
     */
    public WeatherMonth(String monthName, int daysInMonth)
    {
        this.monthName = monthName;
        this.daysInMonth = daysInMonth;
        maxTemperature = new int[daysInMonth];
        minTemperature = new int[daysInMonth];
        for (int i = 0; i < daysInMonth; i++)
        {
            maxTemperature[i] = -999;
            minTemperature[i] = -999;
        }

    }

    /**
     *accessor for max temp array.
     *@return maxTemperature .
     */
    public int[] getMaxTemperature()
    {
        return maxTemperature;
    }

    /**
     * Max temp mutator.
     *@param maxTemperature array . 
     */
    public void setMaxTemperature(int[] maxTemperature)
    {
        this.maxTemperature = maxTemperature; 
    }

    /**
     * acessor for month name.
     * @return monthName .
     */
    public String getMonthName()
    {
        return monthName;
    }

    /**
     * mutator for month name.
     * @param monthName .
     */
    public void setMonthName(String monthName)
    {
        this.monthName = monthName;
    }

    /**
     * accessor for daysIn month.
     * @return daysInMonth .
     */
    public int getDaysInMonth()
    {
        return daysInMonth;
    }

    /**
     * setdaysinmonth method .
     * @param daysInMonth .
     */
    public void setDaysInMonth(int daysInMonth)
    {
        this.daysInMonth = daysInMonth;
    }

    /**
     *days max temp method.
     *@param dayOfMonth 
     *@param temperature . 
     */
    public void setDayMaxTemp(int dayOfMonth, int temperature)
    {
        if (dayOfMonth < 1 || dayOfMonth >= daysInMonth)
        {
            int x = 0;
        }
        if (dayOfMonth >= 1 && dayOfMonth <= daysInMonth)
        {

            maxTemperature[dayOfMonth - 1] = temperature;  
        }
    }

    /**
     *method that displays Weather for the month, it formats
     *and prints the month and max temperature for the day.
     * 
     */
    public void displayWeatherMonth()
    {

        String form = "%s\n%s\t%s\t%s\n";
        System.out.printf(form, monthName, "Day", "Max", "Min");
        for (int i = 0; i < daysInMonth; i++)
        {

            String format = "%d\t%d\t%d\n";
            System.out.printf(format, i + 1, maxTemperature[i],
                minTemperature[i]);

        }
    }

    /**
     * method that reads weather data.
     * @param filename filename
     * @throws IOException .
     */
    public void readMaxTempFile(String filename) throws IOException
    {

        File myFile = new File(filename);
        Scanner weatherDataScanner = new Scanner(myFile);

        while (weatherDataScanner.hasNext())
        {
            int n = weatherDataScanner.nextInt();
            int t = weatherDataScanner.nextInt();
            setDayMaxTemp(n, t);
        }
    }

    /**
     * the to string method returns a formatted string that
     * will display the month,
     * the days, and the max temperature for the given month 
     * in specific format.
     * @return String.format .
     */
    public String toString()
    {
        String s = String.format("%s\n%3s%9s%10s\n", monthName, "Day", "Max",
                "Min");

        for (int i = 0; i < daysInMonth; i++)
        {
            if (maxTemperature[i] == -999 && minTemperature[i] == -999)
            {
                s += String.format("%2d%10s%10s\n", i + 1, "N/A", "N/A");
            }
            else
            {
                s += String.format("%2d%10d%10d\n", i + 1, 
                    maxTemperature[i], minTemperature[i]);
            }

        }
        return s;  

    }

    /**
     * accessor for minTemperature.
     * @return minTemperature .
     */
    public int[] getMinTemperature()
    {
        return minTemperature;
    }

    /**
     * mutator for minTemp.
     * @param minTemperature array . 
     */
    public void setMinTemperature(int[] minTemperature)
    {
        this.minTemperature = minTemperature;
    }

    /**
     * days min temp method.
     * @param dayOfMonth .
     * @param temperature . 
     */
    public void setDayMinTemp(int dayOfMonth, int temperature)
    {
        if (dayOfMonth < 1 || dayOfMonth >= daysInMonth)
        {
            int x = 0;
        }
        if (dayOfMonth >= 1 && dayOfMonth <= daysInMonth)
        {

            minTemperature[dayOfMonth - 1] = temperature;  
        }
    }

    /**
     * read min temperature file method.
     * @param filename .
     * @throws IOException .
     */
    public void readMinTempFile(String filename) throws IOException
    {
        File myFile = new File(filename);
        Scanner weatherDataScanner = new Scanner(myFile);
        while (weatherDataScanner.hasNext())
        {
            int n = weatherDataScanner.nextInt();
            int t = weatherDataScanner.nextInt();
            setDayMinTemp(n, t);
        }
    }
}
