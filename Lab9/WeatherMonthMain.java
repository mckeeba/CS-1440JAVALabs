/**
 * WeatherMonthMain.java
 * 
 */

//Put any imports below this line.
 
/**
 * Short, one-line description of WeatherMonthMain class here.
 * 
 * Optionally, include a paragraph that provides a more 
 * detailed description.
 *
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WeatherMonthMain
{
    /**
     * Main method for executing weather data and testing both classes.
     * @param args for main method
     */
    public static void main(String[] args)
    {
        WeatherMonth weatherMonth = new WeatherMonth("June", 5);
        weatherMonth.setDayMaxTemp(3, 85);
        //System.out.println(weatherMonth);

        int[] testArray = {20, -999, 10, 50, -999, 40, 30};
        double average = WeatherComputation.averageTemperature(testArray);
        int highest = WeatherComputation.highestTemperature(testArray);
        int lowest = WeatherComputation.lowestTemperature(testArray);
        int first = WeatherComputation.numberMissing(testArray);

    }
}
