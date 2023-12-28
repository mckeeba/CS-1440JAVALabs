
/**
 * This is the house class, which creates objects to make a House.
 * In this class are several methods which allow us to later create a scene,
 * and we can make houses in other classes without having to call the roof,wall,
 * and window, wecan just have the whole house.
 * 
 *
 * @author (Gus Mckee) 
 * @version (a version number or a date)
 */
public class House
{
    /**
     * These are the declared fields for my house object.
     */
    private Square wall;
    private Triangle roof;
    private Square window;
    /**
     * modify's the color of the wall.
     * @param color
     */
    public void changeWallColor(String color)
    {
        wall.changeColor(color);

    }
    /**
     * modify's the color of the roof.
     * @param color
     */
   
    public void changeRoofColor(String color)
    {
        roof.changeColor(color);
    }
    /**
     * modify's the color of the window.
     * @param color
     */
    public void changeWindowColor(String color)
    {
        window.changeColor(color);
    }

    /**
     * Moves the objects for our house horizontal.
     * @param horizontal
     */
    public void moveHorizontal(int horizontal)
    {
        wall.moveHorizontal(horizontal);
        window.moveHorizontal(horizontal);
        roof.moveHorizontal(horizontal);

    }
    /**
     * Moves the objects for our house vertical.
     * @param vertical 
     */
    public void moveVertical(int vertical)
    {
        wall.moveVertical(vertical);
        window.moveVertical(vertical);
        roof.moveVertical(vertical);
    }

    /**
     * The following code makes the house objects invisible.
     */
    public void makeInvisible()
    {
        wall.makeInvisible();
        window.makeInvisible();
        roof.makeInvisible();

    }
    /**
     * The following code makes the house objects visible.
     */
    public void makeVisible()
    {
        wall.makeVisible();
        window.makeVisible();
        roof.makeVisible();
    }

    /**
     * Constructor, which holds the objects we made to build a house.
     */
    public House()
    {

        //wall object with methods to modify it.
        wall = new Square();
        wall.makeVisible();
        wall.changeSize(100);
        wall.moveVertical(150);
        wall.moveHorizontal(40);
        wall.changeColor("red");

        //window object with methods to modify it.
        window = new Square();
        window.makeVisible();
        window.changeColor("blue");
        window.changeSize(30);
        window.moveVertical(170);
        window.moveHorizontal(50);

        //roof object with methods to modify it.
        roof = new Triangle();
        roof.makeVisible();
        roof.changeColor("black");
        roof.setHeight(50);
        roof.setWidth(150);
        roof.moveVertical(135);
        roof.moveHorizontal(100);

    }

    /**
     * Main method.
     * Creates a new house object in house class, which we will use to create a
     * scene later on.
     * @param args.
     */

    public static void main(String [] args)
    {
        House house = new House();
    }
}
