/**
 * This is the scene class. Essentially you create 2 houses, a moon, and 
 * a sun using the other classes in the project. 
 * @author Gus
 * @version something..
 */
public class Scene
{
    private House house1;
    private House house2;
    private Circle moon;
    private Circle sun;

    /**
     * This is the constructor which puts together our Scene. For house1 and
     * house2 it modifies the color and position. Then for our sun and moon
     * it modifies the size, color, and position.
     */
    public Scene()
    {
        house1 = new House(); 
        house1.changeWallColor("red");
        house1.changeRoofColor("green");
        house1.changeWindowColor("blue");
        house1.moveHorizontal(-70);
        house1.moveVertical(-10);

        house2 = new House();
        house2.changeWallColor("blue");
        house2.changeRoofColor("black");
        house2.changeWindowColor("yellow");
        house2.moveHorizontal(70);
        house2.moveVertical(-45);

        sun = new Circle();
        sun.makeVisible();
        sun.setDiameter(50);
        sun.setColor("yellow");
        sun.setxPosition(20);
        sun.setyPosition(20);

        moon = new Circle();
        moon.makeVisible();
        moon.setDiameter(50);
        moon.setColor("blue");
        moon.setxPosition(-60);
        moon.setyPosition(20);

    
    }
    /**
     * This is our main method which makes a call for the animate method.
     * Also holds our scene.
     * @param args
     */
    public static void main(String [] args)
    {
        Scene scene = new Scene();
        scene.animate();
    }
    /**
     * this method creates an animation for our sun and moon.
     */
    public void animate()
    {
        sun.slowMoveHorizontal(290);
        moon.slowMoveHorizontal(260);
    }

}
