/**
 * Circle.java
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */

import java.awt.geom.Ellipse2D;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 */
public class Circle
{
    private int diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    
    /**
     * Create a new circle at the default position with the 
     * default diameter and color.
     */
    public Circle()
    {
        diameter = 30;
        xPosition = 20;
        yPosition = 60;
        color = "blue";
        isVisible = false;
    }

    /**
     * Make this circle visible.
     */
    public void makeVisible()
    {
        setIsVisible(true);
    }
    
    /**
     * Make this circle invisible.
     */
    public void makeInvisible()
    {
        setIsVisible(false);
    }
    
    /**
     * Move this circle a few pixels to the right.
     */
    public void moveRight()
    {
        moveHorizontal(20);
    }

    /**
     * Move this circle a few pixels to the left.
     */
    public void moveLeft()
    {
        moveHorizontal(-20);
    }

    /**
     * Move this circle a few pixels up.
     */
    public void moveUp()
    {
        moveVertical(-20);
    }

    /**
     * Move this circle a few pixels down.
     */
    public void moveDown()
    {
        moveVertical(20);
    }

    /**
     * Move this circle horizontally.
     * @param distance The distance in pixels to move this circle horizontally.
     */
    public void moveHorizontal(int distance)
    {
        erase();
        setxPosition(getxPosition() + distance);
        draw();
    }

    /**
     * Move this circle vertically.
     * @param distance The distance in pixels to move this circle vertically.
     */
    public void moveVertical(int distance)
    {
        erase();
        setyPosition(getyPosition() + distance);
        draw();
    }

    /**
     * SLOWLY move this circle horizontally.
     * @param distance The distance in pixels to move this circle horizontally.
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
     * SLOWLY move this circle vertically.
     * @param distance The distance in pixels to move this circle vertically.
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
     * Change the size of this circle. 
     * If the newDiameter is less than zero, the circle will not be visible.
     * @param newDiameter Change this circle's diameter to this given value.
     */
    public void changeSize(int newDiameter)
    {
        erase();
        setDiameter(newDiameter);
        draw();
    }

    /**
     * Change the color of this circle. 
     * Valid colors are "red", "yellow", "blue", "green", "magenta", 
     * "black" and "white".
     * If an unknown color is used the circle will be black.
     * @param newColor The new color of this circle.
     */
    public void changeColor(String newColor)
    {
        setColor(newColor);
    }

    /**
     * Draw this circle with current specifications on screen.
     */
    private void draw()
    {
        if (getIsVisible()) 
        {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, getColor(), 
                new Ellipse2D.Double(getxPosition(), 
                    getyPosition(), getDiameter(), getDiameter()));
            canvas.wait(10);
        }
    }

    /**
     * Erase this circle from the screen.
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
     * The accessor for getting this circle's diameter.
     * @return Returns this circle's diameter as an integer.
     */
    public int getDiameter() 
    {
        return diameter;
    }

    /**
     * The mutator for setting this circle's diameter.
     * @param diameter The new diameter for this circle.
     */
    public void setDiameter(int diameter) 
    {
        this.diameter = diameter;
        draw();
    }

    /**
     * The accessor for getting this circle's horizontal xPosition.
     * @return Returns this circle's xPosition as an integer.
     */
    public int getxPosition() 
    {
        return xPosition;
    }

    /**
     * The mutator for setting this circle's horizontal xPosition.
     * @param xPosition The new xPosition for this circle.
     */
    public void setxPosition(int xPosition) 
    {
        this.xPosition = xPosition;
        draw();
    }

    /**
     * The accessor for getting this circle's vertical yPosition.
     * @return Returns this circle's yPosition as an integer.
     */
    public int getyPosition() 
    {
        return yPosition;
    }

    /**
     * The mutator for setting this circle's vertical yPosition.
     * @param yPosition The new yPosition for this circle.
     */
    public void setyPosition(int yPosition) 
    {
        this.yPosition = yPosition;
        draw();
    }

    /**
     * The accessor for getting this circle's color.
     * @return Returns this circle's color as a String.
     */
    public String getColor() 
    {
        return color;
    }

    /**
     * The mutator for setting this circle's color.
     * Valid colors are "red", "yellow", "blue", "green", "magenta", 
     * "black" and "white".
     * @param color The new color for this circle.
     */
    public void setColor(String color) 
    {
        this.color = color;
        draw();
    }

    /**
     * The accessor for getting this circle's visiblility.
     * @return Returns this circle's isVisible field as a boolean.
     * True means this circle is visible.
     * False means this circle is not visible. 
     */
    public boolean getIsVisible() 
    {
        return isVisible;
    }

    /**
     * The mutator for setting this circle's visiblility.
     * @param isVisible True means draw this circle to the screen.
     * False means erase this circle from the screen.
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
            //You must call erase before modifiying the field.
            erase();
            this.isVisible = false;
        }
    }
}
