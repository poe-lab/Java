/*
 * Point.java
 *
 * Created on May 7, 2004, 11:54 AM
 */

/**
 *
 * @author  Kevin Ziemer
 */
public class DoubPoint {
    private double x;
    private double y;
    
    /** Creates a new instance of Point */
    public DoubPoint() {
        x = 0.0;
        y = 0.0;
    }
    
    public DoubPoint(double xVal, double yVal)
    {
     x = xVal;
     y = yVal;
    }
    public void setX(double xVal)
    {
        x = xVal;
    }
    public void setY(double yVal)
    {
        y = yVal;
    }
    public double getx()
    {
     return x;   
    }
    
    public double gety()
    {
        return y;
    }
    public String toString()
    {
        return "[" + x + ", " + y + "]";
    }
}
