/**
 * This program creates three different shapes and modifies size, direction,
 * and color.
 * 
 * @author Gus Mckee
 * @version something..
 */
public class ShapeFun 
{

    /**  
     * THis is the main method for the different shapes being modified.
     * @param args
     */
    public static void main(String [] args)
    {
        //Square object and different modifications
        Square square = new Square();
        square.makeVisible();
        square.moveHorizontal(-65);
        square.moveVertical(-50);
        square.changeSize(6300);
        square.changeColor("green");
        //Circle object and its different modifications
        Circle circle = new Circle();
        circle.makeVisible();
        circle.moveHorizontal(20);
        circle.moveVertical(35);
        circle.changeSize(57);
        circle.changeColor("blue");
        //Triangle object and its different modifications
        Triangle triangle = new Triangle();
        triangle.makeVisible();
        triangle.moveHorizontal(56);
        triangle.moveVertical(67);
        triangle.setWidth(60);
        triangle.setHeight(40);
        triangle.changeColor("red");
    }
}
