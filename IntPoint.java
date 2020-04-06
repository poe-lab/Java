/*
 * Point.java
 *
 * Created on May 7, 2004, 11:54 AM
 */

/**
 *
 * @author  Kevin Ziemer
 */
public class IntPoint {
    private int x;
    private int y;
    
    /** Creates a new instance of Point */
    public IntPoint() {
        x = 0;
        y = 0;
    }
    
    public IntPoint(int xVal, int yVal)
    {
     x = xVal;
     y = yVal;
    }
    public void setX(int xVal)
    {
        x = xVal;
    }
    public void setY(int yVal)
    {
        y = yVal;
    }
    public int getX()
    {
     return x;   
    }
    
    public int getY()
    {
        return y;
    }
    public int[] getPoint()
    {
        int toReturn[] = {x,y};
        return toReturn;
    }
    public String toString()
    {
        return "[" + x + ", " + y + "]";
    }
}
