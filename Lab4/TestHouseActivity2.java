import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Field;

/**
 * Test that the House object constructs a house
 * as directed.
 * @author Joel Swanson
 * @version 01.26.201
 */
public class TestHouseActivity2
{
    private House house;
    private int grade;
    private int shapeFunStyleErrors;
    private int houseStyleErrors;
    
    /**
     * No-arg constructor for test class TestHouseActivity2.
     */
    public TestHouseActivity2()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    { 
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
        
    /**
     * Test House class from activity 2.
     */
    @Test
    public void testHouseActivity2()
    {
        testSilent();
        printGrade(10);
        
    }       
    
    /**
     * Run all tests.
     */
    public void testSilent()
    {
        house = new House();
        testStyle();
        testStructure();
        testWall();
        testRoof();
        testWindow();
 
    }    
   
    /**
     * Test for style issues.
     */
    private void testStyle()
    {       
        StyleTest styleTest = new StyleTest();       
        shapeFunStyleErrors = styleTest.testCheckStyle("ShapeFun.java");
        houseStyleErrors = styleTest.testCheckStyle("House.java");
    }
    
    /**
     * Test correct size and placement of wall square.
     */    
    public void testWall()
    {
        Field field = null;
        Square wall = null;
        String fb = "";
        try 
        {
            //This should never normally fail since the existance
            //and the type of the fields are tested previously in
            //testStructure().
            field = House.class.getDeclaredField("wall");
            field.setAccessible(true);
            wall = (Square) field.get(house);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        if (wall == null) 
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "Field wall does not exist or is not of type square.\n";
            fail(fb);
        }
        else if (!wall.getColor().equals("red"))
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "The color of the wall should be red.\n";
            fail(fb);            
        }
        else if (wall.getSize() != 100)
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "The size of the wall should be exactly 100.\n";
            fail(fb);                        
        }
        else if (wall.getyPosition() > 200)
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "The wall should be exactly touching the bottom " 
                + " of the canvas window.\n";
            fb += "Your wall is too far down.\n";
            fail(fb);                        
        }
        else if (wall.getyPosition() < 200)
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "The wall should be exactly touching the bottom " 
                + " of the canvas window.\n";
            fb += "Your wall too far above the bottom of the window.\n";
            fail(fb);                        
        }
        else if (wall.getxPosition() < 100)
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "The wall should be exactly centered in the canvas window.\n";
            fb += "Your wall too far to the left.\n";
            fail(fb);                        
        }
        else if (wall.getxPosition() > 100)
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "The wall should be exactly centerd in the canvas window.\n";
            fb += "Your wall too far to the right.\n";
            fail(fb);                        
        }
    }
   
    /**
     * Test correct size and placement of roof triangle.
     */
    public void testRoof()
    {
        Field field = null;
        Triangle roof = null;
        String fb = "";
        try 
        {
            //This should never normally fail since the existance
            //and the type of the fields are tested previously in
            //testStructure().
            field = House.class.getDeclaredField("roof");
            field.setAccessible(true);
            roof = (Triangle) field.get(house);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //Make sure the roof is black and slightly bigger than the wall.
        testRoofAttributes(roof);
        
        //Make sure the roof is centered on the wall and just touching
        //the wall.
        testRoofPosition(roof);
    }
    
    /**
     * Test the roof color and size. It should be black
     * and slightly wider than the wall.
     * @param roof Test this roof object's color and size.
     */
    public void testRoofAttributes(Triangle roof)
    {
        String fb = "";
        if (roof == null) 
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "Field roof does not exist or is not of type Triangle.\n";
            fail(fb);
        }
        else if (!roof.getColor().equals("black"))
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "The color of the roof should be black.\n";
            fail(fb);            
        }
        else if (roof.getHeight() > 60)
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "The height of the triangle for your roof is too big.\n";
            fail(fb);                        
        }
        else if (roof.getHeight() < 40)
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "The height of the triangle for your roof is too small.\n";
            fail(fb);                        
        }
        else if (roof.getWidth() > 150)
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "Your roof is too wide.\n";
            fail(fb);                        
        }
        else if (roof.getWidth() < 120)
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "Your roof is not wide enough.\n";
            fail(fb);                        
        }
    }

    /**
     * Test the roof x and y positioning.
     * @param roof Test this roof object's position
     * to make sure it is neatly placed on the wall.
     */    
    public void testRoofPosition(Triangle roof)
    {
        String fb = "";
        if (roof.getxPosition() < 150)
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "Your roof is not centered on your house.\n";
            fb += "Your roof is too far left.\n";
            fail(fb);                                    
        }
        else if (roof.getxPosition() > 150)
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "Your roof is not centered on your house.\n";
            fb += "Your roof is too far right.\n";
            fail(fb);                                    
        }
        else if (roof.getyPosition() + roof.getHeight() < 200)
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "Your roof is too far above the wall of your house.\n";
            fb += "Move your roof triangle down until it just barely touches" 
                + " the wall.\n";
            fail(fb);                                                
        }
        else if (roof.getyPosition() + roof.getHeight() > 200)
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "Your roof is too far below the top of" 
                + " the wall of your house.\n";
            fb += "Move your roof triangle up until it just barely touches" 
                + " the wall.\n";
            fail(fb);                                                
        }
    }    

    /**
     * Test correct size and placement of window square.
     */    
    public void testWindow()
    {
        Field field = null;
        Square window = null;
        String fb = "";
        try 
        {
            //This should never normally fail since the existance
            //and the type of the fields are tested previously in
            //testStructure().
            field = House.class.getDeclaredField("window");
            field.setAccessible(true);
            window = (Square) field.get(house);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        if (window == null) 
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "Field window does not exist or is not of type square.\n";
            fail(fb);
        }
        else if (!window.getColor().equals("blue"))
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "The color of the window should be blue.\n";
            fail(fb);            
        }
        else if (window.getSize() < 25)
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "The window is too small.\n";
            fail(fb);                        
        }
        else if (window.getSize() > 35)
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "The window is too big.\n";
            fail(fb);                        
        }
        else if (window.getyPosition() > 230)
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "The window should be higher.\n";
            fail(fb);                        
        }
        else if (window.getyPosition() < 210)
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "The window should be lower.\n";
            fail(fb);                        
        }
        else if (window.getxPosition() < 105)
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "The window should be farther right.\n";
            fail(fb);                        
        }
        else if (window.getxPosition() > 110)
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "The window should be farther left.\n";
            fail(fb);                        
        }
    }    
    
    /**
     * Run a series of tests verifying the accuracy of
     * the structure of the house class.  Test for 
     * proper constructor creation, proper fields, etc...
     */
    public void testStructure()
    {
        Class house = House.class;
        testFields(house);
    }
    
    /**
     * Make sure there are the correct number of fields and 
     * that they are the correct type.
     * @param house Make sure the given house object has
     * properly defined fields.
     */
    public void testFields(Class house)
    {
        testField(house, "wall", "Square");
        testField(house, "roof", "Triangle");
        testField(house, "window", "Square");
    }
    
    /**
     * Test a specific field for existance and correct type.
     * @param house Test this house.
     * @param name Make sure the given house has a field with this name.
     * @param type Make sure the given house with the given name is also
     * of this type.
     */
    public void testField(Class house, String name, String type)
    {
        String fb = "";
        Field field = null;
        try
        {
            field = house.getDeclaredField(name);
        }
        catch (Exception e)
        {
            fb += "TestHouseActivity2 Failed.\n";
            fb += "Cannot find a field named " + name 
                + " in your House class.\n";
            printGrade(0);
            fail(fb);
        }

        if (field != null)
        {
            if (!field.getType().getCanonicalName().equals(type))
            {
                fb += "TestHouseActivity2 Failed.\n";
                fb += "Your " + name + " field is not a " + type + ".\n";
                printGrade(0);
                fail(fb);            
            }
        }
    }
    
    
    /**
     * Store the grade then print the report.
     * @param grade The grade at the point of failure.
     */
    private void printGrade(int grade)    
    {
        this.grade = grade;
        System.out.println(gradeReport());
    }    
    
    /**
     * Creates a string with the grades for output.
     * @return Returns a report of the grade for the student
     * at the point of the failure.
     */
    private String gradeReport()
    {
        int shapeFunStyleGrade = Math.max(4 - shapeFunStyleErrors , 0);
        int houseStyleGrade = Math.max(4 - houseStyleErrors , 0);
        int totalGrade = grade + shapeFunStyleGrade + houseStyleGrade;

        String report = "\n____________________________________________\n";
        report += "GRADE:\n";
        report += "ShapeFun Style......  " + shapeFunStyleGrade  
            + " of  4 (Style Errors = " + shapeFunStyleErrors + ")\n";
        report += "TestHouseActivity2.. " + grade + " of 10\n";
        report += "House Style.........  " + houseStyleGrade  
            + " of  4 (Style Errors = " + houseStyleErrors + ")\n";
        report += "TestHouseActivity3..  0 of 14 (Not graded)\n";
        report += "TestScene...........  0 of 14 (Not graded)\n";
        report += "Scene Style.........  0 of  4 (Not graded)\n";
        report += "Total Grade......... " + totalGrade + " of 50\n";
        report += "\nNo test will be graded until the previous"
            + " tests are passed.\n";
        report += "\n10 additional points will be added from the prelab \n";
        report += "quizzes on ASULearn for a total of 60.\n";
                
        return report;
    }   
}
