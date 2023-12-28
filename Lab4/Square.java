/**
 * Square.java
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */

import java.awt.Rectangle;

/**
 * A square that can be manipulated and that draws itself on a canvas.
 * 
 */

public class Square
{
    private int size;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;

    /**
     * Create a new square at the default position with the 
     * default size and color.
     */
    public Square()
    {
        size = 30;
        xPosition = 60;
        yPosition = 50;
        color = "red";
        isVisible = false;
    }

    /**
     * Make this square visible.
     */
    public void makeVisible()
    {
        setIsVisible(true);
    }
    
    /**
     * Make this square invisible.
     */
    public void makeInvisible()
    {
        setIsVisible(false);
    }
    
    /**
     * Move this square a few pixels to the right.
     */
    public void moveRight()
    {
        moveHorizontal(20);
    }

    /**
     * Move this square a few pixels to the left.
     */
    public void moveLeft()
    {
        moveHorizontal(-20);
    }

    /**
     * Move this square a few pixels up.
     */
    public void moveUp()
    {
        moveVertical(-20);
    }

    /**
     * Move this square a few pixels down.
     */
    public void moveDown()
    {
        moveVertical(20);
    }

    /**
     * Move this square horizontally.
     * @param distance The distance in pixels to move this square horizontally.
     */
    public void moveHorizontal(int distance)
    {
        erase();
        setxPosition(getxPosition() + distance);
        draw();
    }

    /**
     * Move this square vertically.
     * @param distance The distance in pixels to move this square vertically.
     */
    public void moveVertical(int distance)
    {
        erase();
        setyPosition(getyPosition() + distance);
        draw();
    }

    /**
     * SLOWLY move this square horizontally.
     * @param distance The distance in pixels to move this square horizontally.
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
     * Slowly move this square vertically.
     * @param distance The distance in pixels to move this square vertically.
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
     * Change the size of this square. 
     * If the newSize is less than zero, the square will not be visible.
     * @param newSize Change this square's diameter to this given value.
     */
    public void changeSize(int newSize)
    {
        erase();
        setSize(newSize);
        draw();
    }

    /**
     * Change the color of this square. 
     * Valid colors are "red", "yellow", "blue", "green", "magenta", 
     * "black" and "white".
     * If an unknown color is used the square will be black.
     * @param newColor The new color of this square.
     */
    public void changeColor(String newColor)
    {
        setColor(newColor);
    }

    /**
     * Draw this square with current specifications on screen.
     */
    private void draw()
    {
        if (getIsVisible()) 
        {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, getColor(),
                new Rectangle(getxPosition(), 
                        getyPosition(), getSize(), getSize()));
            canvas.wait(10);
        }
    }

    /**
     * Erase this square from screen.
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
     * The accessor for getting this squares's size.
     * @return Returns this squares's size as an integer.
     */
    public int getSize() 
    {
        return size;
    }

    /**
     * The mutator for setting this squares's size.
     * @param size The new size for this square.
     */
    public void setSize(int size) 
    {
        this.size = size;
        draw();
    }

    /**
     * The accessor for getting this square's horizontal xPosition.
     * @return Returns this square's xPosition as an integer.
     */
    public int getxPosition() 
    {
        return xPosition;
    }

    /**
     * The mutator for setting this square's horizontal xPosition.
     * @param xPosition The new xPosition for this square.
     */
    public void setxPosition(int xPosition) 
    {
        this.xPosition = xPosition;
        draw();
    }

    /**
     * The accessor for getting this square's vertical yPosition.
     * @return Returns this square's yPosition as an integer.
     */
    public int getyPosition() 
    {
        return yPosition;
    }

    /**
     * The mutator for setting this square's vertical yPosition.
     * @param yPosition The new yPosition for this square.
     */
    public void setyPosition(int yPosition) 
    {
        this.yPosition = yPosition;
        draw();
    }

    /**
     * The accessor for getting this square's color.
     * @return Returns this square's color as a String.
     */
    public String getColor() 
    {
        return color;
    }

    /**
     * The mutator for setting this square's color.
     * Valid colors are "red", "yellow", "blue", "green", "magenta", 
     * "black" and "white".
     * @param color The new color for this square.
     */
    public void setColor(String color) 
    {
        this.color = color;
        draw();
    }

    /**
     * The accessor for getting this square's visiblility.
     * @return Returns this square's isVisible field as a boolean.
     * True means this sqaure is visible.
     * False means this square is not visible. 
     */
    public boolean getIsVisible() 
    {
        return isVisible;
    }

    /**
     * The mutator for setting this square's visiblility.
     * @param isVisible True means draw this square on the screen.
     * False means erase this square from the screen.
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
            erase();
            this.isVisible = false;
        }
    }
}
