
import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Field;


/**
 * Test that the Scene object constructs the scene
 * as directed.
 * @author Joel Swanson
 * @version 02.06.2014
 */
public class TestSceneActivity4
{
    private TestHouseActivity2 tha2;
    private TestHouseActivity3 tha3;
    
    private int grade;
    private int shapeFunStyleErrors;
    private int houseStyleErrors;
    private int sceneStyleErrors;
    
    private Scene scene;
    
    private House house1;
    private House house2;

    private Square wall1;
    private Triangle roof1;
    private Square window1;

    private Square wall2;
    private Triangle roof2;
    private Square window2;
    
    private Circle sun;
    private Circle moon;
    
    /**
     * No-arg constructor for test class TestHouseActivity3.
     */
    public TestSceneActivity4()
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
     * Test Scene class from activity 4.
     */
    @Test
    public void testHouseActivity4()
    {
        testStyle();
        testPrevious();
        testSilent();
        printGrade(14);
    }
    
    /**
     * Run all tests.
     */
    public void testSilent()
    {        
        createNewScene();
        testForAnimationInConstructor();
        
        testHouse1();
        testHouse2();
        
        testSunBefore();
        testMoonBefore();
        scene.animate();
        testSunAfter();
        testMoonAfter();
    }
    
    public void testForAnimationInConstructor()
    {
        String fb = "";
        if (sun.getxPosition() > 150 && moon.getxPosition() > 150)
        {
            fb += "The sun and moon are both in the right\n";
            fb += "portion of the screen after constructor has run.\n";
            fb += "Either you incorrectly called the animate method from\n";
            fb += "your constructor or you have both your moon and \n";
            fb += "sun initially set to the wrong positions.\n";
            fb += "Animate should only be called from main.\n";
            printGrade(10);
            fail(fb);            
        }
    }
    
    /**
     * Test that the sun is initialy correct.
     */
    public void testSunBefore()
    {
        int xSun = sun.getxPosition();
        int ySun = sun.getyPosition();
        int size = sun.getDiameter();
        String fb = "";
        
        if (!sun.getColor().equals("yellow"))
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "Sun is wrong color. Should be yellow.\n";
            printGrade(10);
            fail(fb);
        }
        
        if (size > 80)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "Sun is too big.\n";
            printGrade(10);
            fail(fb);
            
        }

        if (size < 30)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "Sun is too small.\n";
            printGrade(10);
            fail(fb);
            
        }
        
        if (xSun < 0)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "Sun is initially too far left.\n";
            printGrade(10);
            fail(fb);
            
        }        
        if (xSun > 60)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "Sun is initially too far right.\n";
            printGrade(10);
            fail(fb);
            
        }        
        
        if (ySun < 0)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "Sun is initially too far up.\n";
            printGrade(10);
            fail(fb);
            
        }
        
        if (ySun > 60)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "Sun is initially too far down.\n";
            printGrade(10);
            fail(fb);
            
        }        
    }
    
    /**
     * Test that the sun correct after animation.
     */
    public void testSunAfter()
    {
        int xSun = sun.getxPosition();
        int ySun = sun.getyPosition();
        int size = sun.getDiameter();
        String fb = "";
        
        if (!sun.getColor().equals("yellow"))
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "After animation.\n";
            fb += "Sun is wrong color. Should be yellow.\n";
            printGrade(12);
            fail(fb);
        }
        
        if (size > 80)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "After animation.\n";
            fb += "Sun is too big.\n";
            printGrade(12);
            fail(fb);
            
        }

        if (size < 30)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "After animation.\n";
            fb += "Sun is too small.\n";
            printGrade(12);
            fail(fb);
            
        }
        
        if (xSun < 300)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "The sun should move completely off the screen\n";
            fb += "to the right during animation.\n";
            printGrade(12);
            fail(fb);
            
        }        
        
        if (ySun < 0)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "After animation.\n";
            fb += "Sun is too far up.\n";
            printGrade(12);
            fail(fb);
            
        }
        
        if (ySun > 60)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "After animation.\n";
            fb += "Sun is too far down.\n";
            printGrade(12);
            fail(fb);
            
        }        
    }
    
    /**
     * Test that the moon is initialy correct.
     */
    public void testMoonBefore()
    {
        int xMoon = moon.getxPosition();
        int yMoon = moon.getyPosition();
        int size = moon.getDiameter();
        
        String fb = "";
        if (!moon.getColor().equals("blue"))
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "Moon is wrong color. It should be Blue.\n";
            printGrade(10);
            fail(fb);
        }

        if (size > 80)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "Moon is too big.\n";
            printGrade(10);
            fail(fb);
            
        }

        if (size < 30)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "Moon is too small.\n";
            printGrade(10);
            fail(fb);     
        }
        
        if (xMoon < -100)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "The moon should initially be"
                + " off the screen to the left.\n";
            fb += "But you have it too far left.\n";
            fb += "Dont start more than 100 pixels off the screen.\n";
            printGrade(10);
            fail(fb);            
        }
        
        if (xMoon > -moon.getDiameter())
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "The moon should initially be"
                + " off the screen to the left.\n";
            printGrade(10);
            fail(fb);
            
        }        
        
        if (yMoon < 0)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "Moon is initially too far up.\n";
            printGrade(10);
            fail(fb);
            
        }
        
        if (yMoon > 60)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "Moon is initially too far down.\n";
            printGrade(10);
            fail(fb);
        }        
    }
    /**
     * Test that the moon is correct after animation.
     */
    public void testMoonAfter()
    {
        int xMoon = moon.getxPosition();
        int yMoon = moon.getyPosition();
        int size = moon.getDiameter();
        
        String fb = "";
        if (!moon.getColor().equals("blue"))
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "After animation.\n";
            fb += "Moon is wrong color. It should be Blue.\n";
            printGrade(12);
            fail(fb);
        }

        if (size > 80)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "After animation.\n";
            fb += "Moon is too big.\n";
            printGrade(12);
            fail(fb);
            
        }

        if (size < 30)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "After animation.\n";
            fb += "Moon is too small.\n";
            printGrade(12);
            fail(fb);
            
        }
        
        if (xMoon < 200)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "After animation, moon should be\n";
            fb += "farther to the right.\n";            
            printGrade(12);
            fail(fb);            
        }
        
        if (xMoon > 300)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "Moon moves too far right during animation.\n";
            fb += "It should stop somewhere above house 2.\n";
            printGrade(12);
            fail(fb);
            
        }        
        
        if (yMoon < 0)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "Moon is initially too far up.\n";
            printGrade(12);
            fail(fb);
            
        }
        
        if (yMoon > 60)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "Moon is initially too far down.\n";
            printGrade(12);
            fail(fb);
        }        
    }
    /**
     * Test that house1 was created according to direction.
     */
    public void testHouse1()
    {
        String fb = "";
        int x = wall1.getxPosition();
        int y = wall1.getyPosition();
        
        if (!wall1.getColor().equals("red") 
            || !roof1.getColor().equals("green")
            || !window1.getColor().equals("blue"))
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "One or more of the colors for house 1 is wrong.\n";
            printGrade(0);
            fail(fb);
        }
                
        if (x < 10)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "House 1 is too far left.\n";
            printGrade(0);
            fail(fb);            
        }
        
        if (x > 50)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "House 1 is too far right.\n";
            printGrade(0);
            fail(fb);            
        }
        
        if (y < 150)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "House 1 is too far from bottom.\n";
            printGrade(0);
            fail(fb);                        
        }
        
        if (y > 200)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "House 1 is too far down.\n";
            printGrade(0);
            fail(fb);                        
        }
    }

    /**
     * Test that house2 was created according to direction.
     */
    public void testHouse2()
    {
        String fb = "";
        int x1 = wall1.getxPosition();
        int y1 = wall1.getyPosition();
        int x2 = wall2.getxPosition();
        int y2 = wall2.getyPosition();
        
        if (!wall2.getColor().equals("blue") 
            || !roof2.getColor().equals("black") 
            || !window2.getColor().equals("yellow"))
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "One or more of the colors for house 2 is wrong.\n";
            printGrade(5);
            fail(fb);
        }
                
        if (x1 + 100  > x2 - 15)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "House 2 is too far left.\n";
            printGrade(5);
            fail(fb);            
        }
        
        if (x2 > 190)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "House 2 is too far right.\n";
            printGrade(5);
            fail(fb);            
        }
        
        //House 2 must be at least halfway down the window.
        if (y2 < 140)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "House 2 is too far from bottom.\n";
            printGrade(5);
            fail(fb);                        
        }
        
        
        //House 2 must be at least 10 above house 1.
        if (y1 < y2 + 10)
        {
            fb += "TestSceneActivity4 failure.\n";
            fb += "House 2 is too far down.\n";
            printGrade(5);
            fail(fb);                        
        }
    }
    
    
    /**
     * Create a new scene object and set up all access variables
     * to the fields.
     */
    public void createNewScene()
    {
        eraseCanvas();
        scene = new Scene();

        Field houseField = null;
        Field wallField = null;
        Field roofField = null;
        Field windowField = null;
        Field sunField = null;
        Field moonField = null;
        
        String fb = "";
        try 
        {
            //This should never normally fail since the existance
            //and the type of the fields are tested previously in
            //testStructure().
            houseField = Scene.class.getDeclaredField("house1");
            sunField = Scene.class.getDeclaredField("sun");
            moonField = Scene.class.getDeclaredField("moon");
            wallField = House.class.getDeclaredField("wall");
            roofField = House.class.getDeclaredField("roof");
            windowField = House.class.getDeclaredField("window");

            houseField.setAccessible(true);
            sunField.setAccessible(true);
            moonField.setAccessible(true);
            wallField.setAccessible(true);
            roofField.setAccessible(true);
            windowField.setAccessible(true);
            
            house1 = (House) houseField.get(scene);
            wall1 = (Square) wallField.get(house1);
            roof1 = (Triangle) roofField.get(house1);
            window1 = (Square) windowField.get(house1);
           
            houseField = Scene.class.getDeclaredField("house2");
            houseField.setAccessible(true);
            
            house2 = (House) houseField.get(scene);
            wall2 = (Square) wallField.get(house2);
            roof2 = (Triangle) roofField.get(house2);
            window2 = (Square) windowField.get(house2);

            sun = (Circle) sunField.get(scene);
            moon = (Circle) moonField.get(scene);
        
        }
        catch (Exception e)
        {            
            fb += "TestSceneActivity4 failure.\n";
            fb += "One or more of your scene fields is missing.\n";
            fb += "Make sure you didn't create house1, house2,\n";
            fb += "sun, or moon objects in a local variable.\n";
            printGrade(0);
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
        tha3 = new TestHouseActivity3();
        tha3.testSilent();  
        eraseCanvas();        
    }   
    
    /**
     * Erase the canvas by making a big white square
     * and making it visible.
     */
    public void eraseCanvas()
    {
        Square s = new Square();
        s.moveHorizontal(-60);
        s.moveVertical(-50);
        s.changeSize(300);
        s.changeColor("white");
        s.makeVisible();        
    }
      
    /**
     * Test for style issues.
     */
    private void testStyle()
    {       
        //makeNewHouse();
        StyleTest styleTest = new StyleTest();        
        shapeFunStyleErrors = styleTest.testCheckStyle("ShapeFun.java");
        houseStyleErrors = styleTest.testCheckStyle("House.java");
        sceneStyleErrors = styleTest.testCheckStyle("Scene.java");
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
        int sceneStyleGrade = Math.max(4 - sceneStyleErrors , 0);
        int totalGrade = 24 + grade + shapeFunStyleGrade 
            + houseStyleGrade + sceneStyleGrade;

        String report = "\n____________________________________________\n";
        report += "GRADE:\n";
        report += "ShapeFun Style......  " + shapeFunStyleGrade  
            + " of  4 (Style Errors = " + shapeFunStyleErrors + ")\n";
        report += "TestHouseActivity2.. 10 of 10\n";
        report += "House Style.........  " + houseStyleGrade  
            + " of  4 (Style Errors = " + houseStyleErrors + ")\n";
        report += "TestHouseActivity3.. 14 of 14\n";
        report += "TestScene........... " + grade + " of 14\n";
        report += "Scene Style.........  " + sceneStyleGrade  
            + " of  4 (Style Errors = " + sceneStyleErrors + ")\n";
        report += "Total Grade......... " + totalGrade + " of 50\n";
        report += "\nNo test will be graded until the previous tests"
            + " are passed.\n";
        report += "\n10 additional points will be added from the prelab \n";
        report += "quizzes on ASULearn for a total of 60.\n";
        return report;
    }   
}
