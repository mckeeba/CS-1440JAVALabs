
import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Field;


/**
 * Test that the House object constructs a house
 * as directed.
 * @author Joel Swanson
 * @version 01.30.2014
 */
public class TestHouseActivity3
{
    private House house;
    private TestHouseActivity2 tha2;
    private int grade;
    private int shapeFunStyleErrors;
    private int houseStyleErrors;
    
    private Square wall;
    private Triangle roof;
    private Square window;
    
    /**
     * No-arg constructor for test class TestHouseActivity3.
     */
    public TestHouseActivity3()
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
        house = null;
    }
    
    /**
     * Test House class from activity 3.
     */
    @Test
    public void testHouseActivity3()
    {
        testPrevious();
        testSilent();
        printGrade(14);
        
    }
    
    /**
     * Run all tests.
     */
    public void testSilent()
    {
        testStyle();
        testChangeRoofColor1();
        testChangeRoofColor2();
        testChangeWallColor1();
        testChangeWallColor2();
        testChangeWindowColor1();
        testChangeWindowColor2();
        testMoveHorizontal1();
        testMoveHorizontal2();
        testMoveVertical1();
        testMoveVertical2();
        //testMakeInvisible();
        //testMakeVisible();
    }
    
    /**
     * Make sure method moveHorizontal in the house class moves
     * ALL pieces of the house.
     */
    public void testMoveHorizontal1()
    {
        String fb = "";
        makeNewHouse();
        int xWallOld = wall.getxPosition();
        int xRoofOld = roof.getxPosition();
        int xWindowOld = window.getxPosition();
        house.moveHorizontal(20);
        int xWallNew = wall.getxPosition();
        int xRoofNew = roof.getxPosition();
        int xWindowNew = window.getxPosition();
        if (xWallNew != xWallOld + 20)
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running house.moveHorizontal(20) did not move " 
                + "the wall object +20 pixels.\n";
            printGrade(6);
            fail(fb);
        }
        if (xRoofNew != xRoofOld + 20)
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running house.moveHorizontal(20) did not move " 
                + "the roof object +20 pixels.\n";
            printGrade(6);
            fail(fb);
        }
        if (xWindowNew != xWindowOld + 20)
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running house.moveHorizontal(20) did not move " 
                + "the window object +20 pixels.\n";
            printGrade(6);
            fail(fb);
        }        
    }
    
    /**
     * Make sure method moveHorizontal in the house class moves
     * ALL pieces of the house.
     */
    public void testMoveHorizontal2()
    {        
        String fb = "";
        makeNewHouse();
        int xWallOld = wall.getxPosition();
        int xRoofOld = roof.getxPosition();
        int xWindowOld = window.getxPosition();
        house.moveHorizontal(-20);
        int xWallNew = wall.getxPosition();
        int xRoofNew = roof.getxPosition();
        int xWindowNew = window.getxPosition();

        if (xWallNew != xWallOld - 20)
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running house.moveHorizontal(-20) did not move " 
                + "the wall object -20 pixels.\n";
            printGrade(6);
            fail(fb);
        }
        if (xRoofNew != xRoofOld - 20)
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running house.moveHorizontal(-20) did not move " 
                + "the roof object -20 pixels.\n";
            printGrade(6);
            fail(fb);
        }
        if (xWindowNew != xWindowOld - 20)
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running house.moveHorizontal(-20) did not move " 
                + "the window object -20 pixels.\n";
            printGrade(6);
            fail(fb);
        }  
    
    }
    
    /**
     * Make sure method moveVertical in the house class moves
     * ALL pieces of the house.
     */
    public void testMoveVertical1()
    {
        String fb = "";
        makeNewHouse();
        int yWallOld = wall.getyPosition();
        int yRoofOld = roof.getyPosition();
        int yWindowOld = window.getyPosition();
        house.moveVertical(20);
        int yWallNew = wall.getyPosition();
        int yRoofNew = roof.getyPosition();
        int yWindowNew = window.getyPosition();
        if (yWallNew != yWallOld + 20)
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running house.moveVertical(20) did not move " 
                + "the wall object +20 pixels.\n";
            printGrade(8);
            fail(fb);
        }
        if (yRoofNew != yRoofOld + 20)
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running house.moveVertical(20) did not move " 
                + "the roof object +20 pixels.\n";
            printGrade(8);
            fail(fb);
        }
        if (yWindowNew != yWindowOld + 20)
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running house.moveVertical(20) did not move " 
                + "the window object +20 pixels.\n";
            printGrade(8);
            fail(fb);
        }        
    }
    
    /**
     * Make sure method moveVertical in the house class moves
     * ALL pieces of the house.
     */
    public void testMoveVertical2()
    {        
        String fb = "";
        makeNewHouse();
        int yWallOld = wall.getyPosition();
        int yRoofOld = roof.getyPosition();
        int yWindowOld = window.getyPosition();
        house.moveVertical(-20);
        int yWallNew = wall.getyPosition();
        int yRoofNew = roof.getyPosition();
        int yWindowNew = window.getyPosition();

        if (yWallNew != yWallOld - 20)
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running house.moveVertical(-20) did not move " 
                + "the wall object -20 pixels.\n";
            printGrade(8);
            fail(fb);
        }
        if (yRoofNew != yRoofOld - 20)
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running house.moveVertical(-20) did not move " 
                + "the roof object -20 pixels.\n";
            printGrade(8);
            fail(fb);
        }
        if (yWindowNew != yWindowOld - 20)
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running house.moveVertical(-20) did not move " 
                + "the window object -20 pixels.\n";
            printGrade(8);
            fail(fb);
        }  
    
    }
    
    /**
     * Change wall, roof, and window to a single color for testing.
     * @param color The color to change all parts of this house.
     */   
    public void changeHouseColor(String color)
    {        
        wall.changeColor(color);
        roof.changeColor(color);
        window.changeColor(color);
    }
    
    /**
     * Make sure changeRoofColor() actually changes the color of the roof.
     * Also make sure it does not change any other object's color.
     */
    public void testChangeRoofColor1()
    {
        String fb = "";
        makeNewHouse();
        changeHouseColor("white");

        house.changeRoofColor("green");
        if (!roof.getColor().equals("green"))
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running changeRoofColor(\"green\") in your House class\n";
            fb += "did not change the color of the roof to green.\n";
            printGrade(0);
            fail(fb);
        }
                
        if (!window.getColor().equals("white"))
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running changeRoofColor(\"green\") in your House class\n";
            fb += "incorrectly changed the color of the window.\n";
            fb += "Method changeRoofColor should ONLY change the ";
            fb += "color of the roof.\n";
            printGrade(0);
            fail(fb);            
        }
        
        if (!wall.getColor().equals("white"))
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running changeRoofColor(\"green\") in your House class\n";
            fb += "incorrectly changed the color of the wall.\n";
            fb += "Method changeRoofColor should ONLY change the ";
            fb += "color of the roof.\n";
            printGrade(0);
            fail(fb);            
        }
    }

    /**
     * Make sure changeRoofColor() actually changes the color of the roof.
     * Also make sure it does not change any other object's color.
     */
    public void testChangeRoofColor2()
    {
        String fb = "";
        makeNewHouse();
        changeHouseColor("black");
        house.changeRoofColor("yellow");
        
        if (!roof.getColor().equals("yellow"))
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running changeRoofColor(\"yellow\") in your House class\n";
            fb += "did not change the color of the roof to yellow.\n";
            printGrade(0);
            fail(fb);
        }
        
        if (!window.getColor().equals("black"))
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running changeRoofColor(\"yellow\") in your House class\n";
            fb += "incorrectly changed the color of the window.\n";
            fb += "Method changeRoofColor should ONLY change the ";
            fb += "color of the roof.\n";
            printGrade(0);
            fail(fb);            
        }
        
        if (!wall.getColor().equals("black"))
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running changeRoofColor(\"yellow\") in your House class\n";
            fb += "incorrectly changed the color of the wall.\n";
            fb += "Method changeRoofColor should ONLY change the ";
            fb += "color of the roof.\n";
            printGrade(0);
            fail(fb);            
        }
    }

    /**
     * Make sure changeWallColor() actually changes the color of the wall.
     * Also make sure it does not change any other object's color.
     */
    public void testChangeWallColor1()
    {
        String fb = "";
        makeNewHouse();
        changeHouseColor("white");

        house.changeWallColor("green");
        if (!wall.getColor().equals("green"))
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running changeWallColor(\"green\") in your House class\n";
            fb += "did not change the color of the wall to green.\n";
            printGrade(2);
            fail(fb);
        }
                
        if (!window.getColor().equals("white"))
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running changeWallColor(\"green\") in your House class\n";
            fb += "incorrectly changed the color of the window.\n";
            fb += "Method changeWallColor should ONLY change the ";
            fb += "color of the wall.\n";
            printGrade(2);
            fail(fb);            
        }
        
        if (!roof.getColor().equals("white"))
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running changeWallColor(\"green\") in your House class\n";
            fb += "incorrectly changed the color of the roof.\n";
            fb += "Method changeWallColor should ONLY change the ";
            fb += "color of the wall.\n";
            printGrade(2);
            fail(fb);            
        }        
    }
    
    /**
     * Make sure changeWallColor() actually changes the color of the wall.
     * Also make sure it does not change any other object's color.
     */
    public void testChangeWallColor2()
    {
        String fb = "";
        makeNewHouse();
        changeHouseColor("black");
        
        house.changeWallColor("yellow");
        if (!wall.getColor().equals("yellow"))
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running changeWallColor(\"yellow\") in your House class\n";
            fb += "did not change the color of the wall to yellow.\n";
            printGrade(2);
            fail(fb);
        }
        
        if (!window.getColor().equals("black"))
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running changeWallColor(\"yellow\") in your House class\n";
            fb += "incorrectly changed the color of the window.\n";
            fb += "Method changeWallColor should ONLY change the ";
            fb += "color of the wall.\n";
            printGrade(2);
            fail(fb);            
        }
        
        if (!roof.getColor().equals("black"))
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running changeWallColor(\"yellow\") in your House class\n";
            fb += "incorrectly changed the color of the roof.\n";
            fb += "Method changeWallColor should ONLY change the ";
            fb += "color of the wall.\n";
            printGrade(2);
            fail(fb);            
        }        
    }
    
    /**
     * Make sure changeWindowColor() actually changes the color of the window.
     * Also make sure it does not change any other object's color.
     */
    public void testChangeWindowColor1()
    {
        String fb = "";
        makeNewHouse();
        changeHouseColor("white");

        house.changeWindowColor("green");
        if (!window.getColor().equals("green"))
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running changeWindowColor(\"green\") in your House class\n";
            fb += "did not change the color of the window to green.\n";
            printGrade(4);
            fail(fb);
        }
                
        if (!wall.getColor().equals("white"))
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running changeWindowColor(\"green\") in your House class\n";
            fb += "incorrectly changed the color of the wall.\n";
            fb += "Method changeWindowColor should ONLY change the ";
            fb += "color of the window.\n";
            printGrade(4);
            fail(fb);            
        }
        
        if (!roof.getColor().equals("white"))
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running changeWindowColor(\"green\") in your House class\n";
            fb += "incorrectly changed the color of the roof.\n";
            fb += "Method changeWidownColor should ONLY change the ";
            fb += "color of the window.\n";
            printGrade(4);
            fail(fb);            
        }        
    }
    
    /**
     * Make sure changeWindowColor() actually changes the color of the window.
     * Also make sure it does not change any other object's color.
     */
    public void testChangeWindowColor2()
    {
        String fb = "";
        makeNewHouse();
        changeHouseColor("black");

        house.changeWindowColor("yellow");
        if (!window.getColor().equals("yellow"))
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running changeWindowColor(\"yellow\") in your House class\n";
            fb += "did not change the color of the window to yellow.\n";
            printGrade(4);
            fail(fb);
        }
        
        if (!wall.getColor().equals("black"))
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running changeWindowColor(\"yellow\") in your House class\n";
            fb += "incorrectly changed the color of the wall.\n";
            fb += "Method changeWindowColor should ONLY change the ";
            fb += "color of the window.\n";
            printGrade(4);
            fail(fb);            
        }
        
        if (!roof.getColor().equals("black"))
        {
            fb += "TestHouseActivity3 Failed.\n";
            fb += "Running changeWindowColor(\"yellow\") in your House class\n";
            fb += "incorrectly changed the color of the roof.\n";
            fb += "Method changeWidownColor should ONLY change the ";
            fb += "color of the window.\n";
            printGrade(4);
            fail(fb);            
        }        
    }
    
    /**
     * Makes a new house object and looks up and sets
     * the wall, window, and roof fields properly.
     */
    public void makeNewHouse()
    {
        if (wall != null)
        {
            wall.makeInvisible();
        }
        
        if (window != null)
        {
            window.makeInvisible();
        }
        
        if (roof != null)
        {
            roof.makeInvisible();
        }
        
        house = new House();
        getHouseFields();        
    }
    
    /**
     * Use reflection to get a public copy of the field objects
     * for wall, roof, and window.
     */
    public void getHouseFields()
    {
        Field field = null;
        String fb = "";
        try 
        {
            //This should never normally fail since the existance
            //and the type of the fields are tested previously in
            //testStructure().
            field = House.class.getDeclaredField("wall");
            field.setAccessible(true);
            wall = (Square) field.get(house);
                        
            field = House.class.getDeclaredField("roof");
            field.setAccessible(true);
            roof = (Triangle) field.get(house);
            
            field = House.class.getDeclaredField("window");
            field.setAccessible(true);
            window = (Square) field.get(house);
        }
        catch (Exception e)
        {
            fb += "MAJOR TESTING ERROR";
            fb += "Error in getting fields in TestHouseActivity3\n";
            fb += "Compress your BlueJ project and email to your" 
                + " lecture instructor desribing\n";
            fb += "the problem and await further instructions.";
            fail(fb);
        }        
    }
    
    /**
     * Run previous tests; TestHouseActivity2.
     */
    public void testPrevious()
    {
        tha2 = new TestHouseActivity2();
        tha2.testSilent();        
    }      
      
    /**
     * Test for style issues.
     */
    private void testStyle()
    {       
        makeNewHouse();
        StyleTest styleTest = new StyleTest();        
        shapeFunStyleErrors = styleTest.testCheckStyle("ShapeFun.java");
        houseStyleErrors = styleTest.testCheckStyle("House.java");
    }
    
    /**
     * Store the grade then print the report.
     * @param grade The grade at the point of failure.
     */
    private void printGrade(int grade)    
    {
        this.grade = grade;
        System.out.println(gradeReport());

        //Restore the default house colors to reduce confusion.
        makeNewHouse();
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
        int totalGrade = 10 + grade + shapeFunStyleGrade + houseStyleGrade;

        String report = "\n____________________________________________\n";
        report += "GRADE:\n";
        report += "ShapeFun Style......  " + shapeFunStyleGrade  
            + " of  4 (Style Errors = " + shapeFunStyleErrors + ")\n";
        report += "TestHouseActivity2.. 10 of 10 \n";
        report += "House Style.........  " + houseStyleGrade  
            + " of  4 (Style Errors = " + houseStyleErrors + ")\n";
        report += "TestHouseActivity3.. " + grade + " of 14\n";
        report += "TestScene...........  0 of 14 (Not graded)\n";
        report += "Scene Style.........  0 of  4 (Not graded)\n";
        report += "Total Grade......... " + totalGrade + " of 50\n";
        report += "\nNo test will be graded until the previous tests"
            + " are passed.\n";
        report += "\n10 additional points will be added from the prelab \n";
        report += "quizzes on ASULearn for a total of 60.\n";
        return report;
    }   
}
