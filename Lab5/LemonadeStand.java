/**
 * LemonadeStand.java
 * 
 */

//Put any imports below this line.

/**
 * Short, one-line description of LemonadeStand class here.
 * 
 * Optionally, include a paragraph that provides a more 
 * detailed description.
 *
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LemonadeStand
{
    private int lemons;
    private int gallonsOfWater;
    private int cupsOfSugar;
    private int emptyGlasses;
    private int glassesOfLemonade;
    private double price;
    private double income;

    /**
     * No parameter constructor for objects of class LemonadeStand.
     */
    public LemonadeStand()
    {
        lemons = 0;
        gallonsOfWater = 0;
        cupsOfSugar = 0;
        emptyGlasses = 0;
        glassesOfLemonade = 0;
        price = 0;
        income = 0;
    }

    /**
     * @param initLemons initial lemons
     * @param initCupsOfSugar inital cup of sugar
     * @param initGallonsOfWater inital gallons of water
     * @param initEmptyGlasses inital empty glasses of water
     * @param initPrice inital price
     * 
     * 
     */
    public LemonadeStand(int initLemons, int initGallonsOfWater,
        int initCupsOfSugar, int initEmptyGlasses, double initPrice)
    {
        setLemons(initLemons);
        setGallonsOfWater(initGallonsOfWater);
        setCupsOfSugar(initCupsOfSugar);
        setEmptyGlasses(initEmptyGlasses);

        setPrice(initPrice);
        income = 0;
        glassesOfLemonade = 0;

    }

    /**
     *@return y 
     */
    public int getLemons()
    {

        return lemons;
    }

    /**
     * @return x
     */
    public int getGallonsOfWater()
    {

        return gallonsOfWater;
    }

    /**
     * @return b
     */
    public int getCupsOfSugar()
    {

        return cupsOfSugar;
    }

    /**
     * @return a
     */
    public int getEmptyGlasses()
    {

        return emptyGlasses;
    }

    /**
     * @return c
     */
    public int getGlassesOfLemonade()
    {

        return glassesOfLemonade;
    }

    /**
     * @return a 
     */
    public double getPrice()
    {

        return price;

    }

    /**
     * @return b
     */
    public double getIncome()
    {

        return income;
    }

    /**
     * @param newLemons new set of lemons
     */
    public void setLemons(int newLemons)
    {
        if (newLemons < 0)
        {
            lemons = 0;
        }
        else 
        {
            lemons = newLemons;
        }
    }

    /**
     * @param newGallonsOfWater new gallons of water
     */
    public void setGallonsOfWater(int newGallonsOfWater) 
    {
        if (newGallonsOfWater < 0)
        {
            gallonsOfWater = 0;
        }
        else
        {
            gallonsOfWater = newGallonsOfWater; 
        }
    }

    /**
     * @param newCupsOfSugar new cups of sugar
     */
    public void setCupsOfSugar(int newCupsOfSugar)
    {
        if (newCupsOfSugar < 0)
        {
            cupsOfSugar = 0;
        }
        else
        {
            cupsOfSugar = newCupsOfSugar; 
        } 
    }

    /**
     * @param newEmptyGlasses new set of empty glasses
     */
    public void setEmptyGlasses(int newEmptyGlasses)
    {
        if (newEmptyGlasses < 0)
        {
            emptyGlasses = 0;
        }
        else
        {
            emptyGlasses = newEmptyGlasses;
        }
    }

    /**
     * @param newGlassesOfLemonade new glasses of Lemonade
     */
    public void setGlassesOfLemonade(int newGlassesOfLemonade)
    {
        if (newGlassesOfLemonade < 0)
        {
            glassesOfLemonade = 0;
        }
        else
        {
            glassesOfLemonade = newGlassesOfLemonade; 
        }
    }

    /**
     * @param newPrice a new price
     */
    public void setPrice(double newPrice)
    {
        if (newPrice < 0)
        {
            price = 0;
        }
        else
        {
            price = newPrice;
        }
    }

    /**
     * @param newIncome a new income
     */
    public void setIncome(double newIncome)
    {
        if (newIncome < 0)
        {
            income = 0;
        }
        else
        {
            income = newIncome;
        }
    }

    /**
     * This method and its if statement check to make sure we can make 
     * lemonade. 
     * So as long as we have the corresponding ingredients, we can make lemonade
     * (return 8). Else if not we return 0, and therefore we cannot 
     * make lemonade.
     * @return 8 or 0
     */
    public int makeLemonade()
    {
        if (emptyGlasses >= 8 && lemons >= 6 && gallonsOfWater >= 1 
            && cupsOfSugar >= 1)
        {
            lemons = lemons - 6;
            gallonsOfWater = gallonsOfWater - 1;
            cupsOfSugar = cupsOfSugar - 1;
            emptyGlasses = emptyGlasses - 8;
            glassesOfLemonade = glassesOfLemonade + 8;
            return 8;
        }
        else
        {
            return 0;
        }
    }

    /**
     * This method and its corresponding if statements tell us 
     * that if glasses of 
     * lemonade is equal to zero, and we can't make lemonade because the method 
     * makeLemonade does not return 8, then we can't sell Lemonade(return 0). 
     * Else 
     * if neither of the above are true we can sell Lemonade therefore return 1.
     * @return 0 or 1
     */
    public int sellLemonade()
    {
        if (glassesOfLemonade == 0)
        {

            if (makeLemonade() != 8)
            {

                return 0;
            }
        }
        glassesOfLemonade = glassesOfLemonade - 1;
        income = income + price;
        return 1;
    }

    /**
     * This method has a while loop and an if statement. The while loop will run
     * until soldGlasses is equal to the requested glasses. Then our if
     * statement checks to make sure we have lemonade to sell, if we don't
     * the loop will break out. If we do sold glasses will continue to go up.
     * @return soldGlasses
     * @param requestedGlasses a request for glasses
     */
    public int sellMoreLemonade(int requestedGlasses)
    {
        int soldGlasses = 0;
        while (soldGlasses < requestedGlasses)
        {
            if (sellLemonade() == 0)
            {
                break;
            }
            soldGlasses++;
        }
        
        return soldGlasses;
    }

    /**
     * Main method for testing LemonadeStand.
     * @param args For the lemonade stand methods
     */
    public static void main(String[] args)
    {
        LemonadeStand lemonadeStand = new LemonadeStand(1, 2, 2, 4,
                1.5);
        lemonadeStand.makeLemonade();

        System.out.println(lemonadeStand.getLemons());
        System.out.println("GallonsOfWater:" + lemonadeStand.getGallonsOfWater()
            + "\n" + "CupsOfSugar:" + lemonadeStand.getCupsOfSugar() + "\n"
            + "GlassesOfLemonade:"  + lemonadeStand.getGlassesOfLemonade());

        lemonadeStand.sellLemonade();
        lemonadeStand.sellLemonade();
        lemonadeStand.sellLemonade();
        System.out.println("3 Glasses:" + lemonadeStand.getGlassesOfLemonade());

    }
}
