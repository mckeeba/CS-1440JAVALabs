/**
 * Short, one-line description of RabbitPopulation class here.
 * 
 * Optionally, include a paragraph that provides a more 
 * detailed description.
 *
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RabbitPopulation
{
    private int initialPopulation;
    private int sixMonthPopulation; 
    
    public RabbitPopulation()
    {
        initialPopulation = 0;
        sixMonthPopulation = 0;
    }
    
    public RabbitPopulation(int initialP, int sixMonthP)
    {
        setInitialPopulation(initialP);
        sixMonthPopulation = sixMonthP;
        
        if (initialP >= 0)
        {
          initialPopulation = initialP;
        }
        else
        {
            initialPopulation = 0;
        }
        
        if (sixMonthP >= 0)
        {
          sixMonthPopulation = sixMonthP;
        }
        else 
        {
          sixMonthPopulation = 0;        
        }
    }
    
    public void setInitialPopulation(int iP)
    {
        if (iP < 0)
        {
            initialPopulation = 0;  
        }
        else
        {
            initialPopulation = iP;
        }
    }

    public int getInitialPopulation()
    {
        return initialPopulation;
    }

    public void setSixMonthPopulation(int sixmP)
    {
        if (sixmP < 0 )
        {
            sixMonthPopulation = 0; 
        }
        else
        {
            sixMonthPopulation = sixmP;
        }

    }

    public int getSixMonthPopulation()
    {
        return sixMonthPopulation;
    }

    
    

    public double calculateGrowthRate()
    {
        double ratePercent = 0;
        double differenceOfPop = sixMonthPopulation - initialPopulation;
        ratePercent = differenceOfPop / initialPopulation;
        return ratePercent;
    }
    
    /**
     * This method provides the prediction for the number of rabbits after 
     * another 6 months.
     */
    public int calculate12MonthPopulation()
    {
        double rP = 0;
        rP = calculateGrowthRate();
        int month12Pop = 0;
        double calc = 0;
        
        calc = sixMonthPopulation * rP;
        month12Pop = (int) (Math.round(calc) + sixMonthPopulation);
        return month12Pop;
    }
}
