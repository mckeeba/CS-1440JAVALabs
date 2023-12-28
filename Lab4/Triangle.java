/**
 * Triangle.java
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
import java.awt.Polygon;

/**
 * A triangle that can be manipulated and that draws itself on a canvas.
 * 
 */
public class Triangle
{
    private int height;
    private int width;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;

    /**
     * Create a new triangle at the default position with the 
     * default height, width, and color.
     */
    public Triangle()
    {
        height = 30;
        width = 40;
        xPosition = 50;
        yPosition = 15;
        color = "green";
        isVisible = false;
    }

    /**
     * Make this triangle visible.
     */
    public void makeVisible()
    {
        setIsVisible(true);
    }
    
    /**
     * Make this triangle invisible.
     */
    public void makeInvisible()
    {
        setIsVisible(false);
    }
    
    /**
     * Move this triangle a few pixels to the right.
     */
    public void moveRight()
    {
        moveHorizontal(20);
    }

    /**
     * Move this triangle a few pixels to the left.
     */
    public void moveLeft()
    {
        moveHorizontal(-20);
    }

    /**
     * Move this triangle a few pixels up.
     */
    public void moveUp()
    {
        moveVertical(-20);
    }

    /**
     * Move this triangle a few pixels down.
     */
    public void moveDown()
    {
        moveVertical(20);
    }

    /**
     * Move this triangle horizontally.
     * @param distance The distance in pixels to move this triangle horizontally.
     */
    public void moveHorizontal(int distance)
    {
        erase();
        setxPosition(getxPosition() + distance);
        draw();
    }

    /**
     * Move this triangle vertically.
     * @param distance The distance in pixels to move this triangle vertically.
     */
    public void moveVertical(int distance)
    {
        erase();
        setyPosition(getyPosition() + distance);
        draw();
    }

    /**
     * SLOWLY move this triangle horizontally.
     * @param distance The distance in pixels to move this triangle horizontally.
     */
    public void slowMoveHorizontal(int distance)
    {
        int delta;

        if (distance < 0) 
        {
            delta = -1;
            distance = -distance;
        }
        else 
        {
            delta = 1;
        }

        for (int i = 0; i < distance; i++)
        {
            setxPosition(getxPosition() + delta);
            draw();
        }
    }

    /**
     * SLOWLY move this triangle vertically.
     * @param distance The distance in pixels to move this triangle vertically.
     */
    public void slowMoveVertical(int distance)
    {
        int delta;

        if (distance < 0) 
        {
            delta = -1;
            distance = -distance;
        }
        else 
        {
            delta = 1;
        }

        for (int i = 0; i < distance; i++)
        {
            setyPosition(getyPosition() + delta);
            draw();
        }
    }

    /**
     * Change the height and width of this triangle. 
     * If the height is less than zero, the triangle will be inverted vertically.
     * @param newHeight Change this triangle's height to this given value.
     * @param newWidth The new width specification for this triangle.
     */
    public void changeSize(int newHeight, int newWidth)
    {
        erase();
        setHeight(newHeight);
        setWidth(newWidth);
        draw();
    }

    /**
     * Change the color of this triangle. 
     * Valid colors are "red", "yellow", "blue", "green", "magenta", 
     * "black" and "white".
     * If an unknown color is used the triangle will be black.
     * @param newColor The new color of this triangle.
     */
    public void changeColor(String newColor)
    {
        setColor(newColor);
    }

    /**
     * Draw this triangle with current specifications on screen.
     */
    private void draw()
    {
        if (getIsVisible()) 
        {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = {getxPosition(), getxPosition() + (getWidth() / 2),
                getxPosition() - (getWidth() / 2) };
            int[] ypoints = {getyPosition(), getyPosition() + getHeight(),
                getyPosition() + getHeight() };
            canvas.draw(this, getColor(), new Polygon(xpoints, ypoints, 3));
            canvas.wait(10);
        }
    }

    /**
     * Erase this triangle from screen.
     */
    private void erase()
    {
        if (getIsVisible())
        {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }

    /**
     * The accessor for getting this triangle's height.
     * @return Returns this triangle's height as an integer.
     */
    public int getHeight() 
    {
        return height;
    }

    /**
     * The mutator for setting this triangle's height.
     * @param height The new height for this triangle.
     */
    public void setHeight(int height) 
    {
        this.height = height;
        draw();
    }

    /**
     * The accessor for getting this triangle's width.
     * @return Returns this triangle's width as an integer.
     */
    public int getWidth() 
    {
        return width;
    }

    /**
     * The mutator for setting this triangle's width.
     * @param width The new width for this triangle.
     */
    public void setWidth(int width) 
    {
        this.width = width;
        draw();
    }

    /**
     * The accessor for getting this triangle's horizontal xPosition.
     * @return Returns this triangle's xPosition as an integer.
     */
    public int getxPosition() 
    {
        return xPosition;
    }

    /**
     * The mutator for setting this triangle's horizontal xPosition.
     * @param xPosition The new xPosition for this triangle.
     */
    public void setxPosition(int xPosition) 
    {
        this.xPosition = xPosition;
        draw();
    }

    /**
     * The accessor for getting this triangle's vertical yPosition.
     * @return Returns this triangle's yPosition as an integer.
     */
    public int getyPosition() 
    {
        return yPosition;
    }

    /**
     * The mutator for setting this triangle's vertical yPosition.
     * @param yPosition The new yPosition for this triangle.
     */
    public void setyPosition(int yPosition) 
    {
        this.yPosition = yPosition;
        draw();
    }

    /**
     * The accessor for getting this triangle's color.
     * @return Returns this triangle's color as a String.
     */
    public String getColor() 
    {
        return color;
    }

    /**
     * The mutator for setting this triangle's color.
     * Valid colors are "red", "yellow", "blue", "green", "magenta", 
     * "black" and "white".
     * @param color The new color for this triangle.
     */
    public void setColor(String color) 
    {
        this.color = color;
        draw();
    }

    /**
     * The accessor for getting this triangle's visiblility.
     * @return Returns this triangle's isVisible field as a boolean.
     * True means this triangle is visible.  
     * False means this triangle is not visible.
     */
    public boolean getIsVisible() 
    {
        return isVisible;
    }

    /**
     * The mutator for setting this triangle's visiblility.
     * @param isVisible True means draw this triangle to the screen.
     * False means erase this triangle from the screen.
     */
    public void setIsVisible(boolean isVisible) 
    {
        if (isVisible)
        {
            this.isVisible = true;
            draw();
        }
        else
        {
            //Must call erase BEFORE setting field.
            erase();
            this.isVisible = false;
        }
    }
}
