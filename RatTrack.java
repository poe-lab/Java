import java.awt.*;
import java.io.*;
import java.text.*;
/*
 * RatTrack.java
 *
 * Created on May 10, 2004, 2:20 PM
 */

/**
 *
 * @author  Kevin Ziemer
 */
public class RatTrack {
    private double widthFraction;
    private double lengthFraction;
    private double pathWidthVLengthFraction;
    private double pathWidthVWidthFraction;
    private IntPoint outerCoords[];
    private IntPoint innerCoords[];
    private IntPoint middleCoords[];
    private double longAngle;
    private DecimalFormat twoDigits;
    
    /** Creates a new instance of RatTrack */
    public RatTrack() 
    {
        twoDigits = new DecimalFormat("0.00");
        outerCoords = new IntPoint[4];
        innerCoords = new IntPoint[4];
        middleCoords = new IntPoint[4];
        for(int i = 0; i < 4; i++)
        {
            outerCoords[i] = new IntPoint();
            innerCoords[i] = new IntPoint();
            middleCoords[i] = new IntPoint();
        }
        lengthFraction = 1.0;
        widthFraction = (45.6/91.0);
        pathWidthVLengthFraction = (8.8/91.0);
        pathWidthVWidthFraction = (8.8/45.6);
        longAngle = Math.atan(widthFraction/lengthFraction);
    }
    
    public void setFirstPoint(IntPoint thePoint)
    {
        reset();
        
        outerCoords[0].setX(thePoint.getX());
        outerCoords[0].setY(thePoint.getY());
        for(int i=0;i<4;i++)
        {
            outerCoords[i].setX(thePoint.getX());
            outerCoords[i].setY(thePoint.getY());
            innerCoords[i].setX(thePoint.getX());
            innerCoords[i].setY(thePoint.getY());
            middleCoords[i].setX(thePoint.getX());
            middleCoords[i].setY(thePoint.getY());
            //System.out.print("Point " + i + ": " + outerCoords[i] + "\n");
        }
    }
    
    public void setSecondPoint(IntPoint thePoint)
    {
        outerCoords[2] = thePoint;
           
        IntPoint foundCoordsOuter[] = findCoords(outerCoords[0], outerCoords[2]);
        
        for(int i=1;i<4;i += 2)
        {
            outerCoords[i].setX(foundCoordsOuter[i].getX());
            outerCoords[i].setY(foundCoordsOuter[i].getY());
            
        }
        
        int innerX0 = outerCoords[0].getX() + (int) Math.round((pathWidthVLengthFraction*(outerCoords[1].getX()- outerCoords[0].getX())) - (pathWidthVWidthFraction*(outerCoords[0].getX()- outerCoords[3].getX())));
        int innerX1 = outerCoords[1].getX() + (int) Math.round((pathWidthVLengthFraction*(outerCoords[0].getX()- outerCoords[1].getX())) - (pathWidthVWidthFraction*(outerCoords[1].getX()- outerCoords[2].getX())));
        int innerX2 = outerCoords[2].getX() + (int) Math.round((pathWidthVLengthFraction*(outerCoords[3].getX()- outerCoords[2].getX())) - (pathWidthVWidthFraction*(outerCoords[2].getX()- outerCoords[1].getX())));
        int innerX3 = outerCoords[3].getX() + (int) Math.round((pathWidthVLengthFraction*(outerCoords[2].getX()- outerCoords[3].getX())) - (pathWidthVWidthFraction*(outerCoords[3].getX()- outerCoords[0].getX())));
        
        int innerY0 = outerCoords[0].getY() + (int) Math.round((pathWidthVLengthFraction*(outerCoords[1].getY()- outerCoords[0].getY())) - (pathWidthVWidthFraction*(outerCoords[0].getY()- outerCoords[3].getY())));
        int innerY1 = outerCoords[1].getY() + (int) Math.round((pathWidthVLengthFraction*(outerCoords[0].getY()- outerCoords[1].getY())) - (pathWidthVWidthFraction*(outerCoords[1].getY()- outerCoords[2].getY())));
        int innerY2 = outerCoords[2].getY() + (int) Math.round((pathWidthVLengthFraction*(outerCoords[3].getY()- outerCoords[2].getY())) - (pathWidthVWidthFraction*(outerCoords[2].getY()- outerCoords[1].getY())));
        int innerY3 = outerCoords[3].getY() + (int) Math.round((pathWidthVLengthFraction*(outerCoords[2].getY()- outerCoords[3].getY())) - (pathWidthVWidthFraction*(outerCoords[3].getY()- outerCoords[0].getY())));
        

        innerCoords[0].setX(innerX0);
        innerCoords[1].setX(innerX1);
        innerCoords[2].setX(innerX2);
        innerCoords[3].setX(innerX3);
        
        innerCoords[0].setY(innerY0);
        innerCoords[1].setY(innerY1);
        innerCoords[2].setY(innerY2);
        innerCoords[3].setY(innerY3);
        
        middleCoords[0].setX( (int) Math.round( ( (double) innerCoords[0].getX() + outerCoords[0].getX() )/2.0) );
        middleCoords[1].setX( (int) Math.round( ( (double) innerCoords[1].getX() + outerCoords[1].getX() )/2.0) );
        middleCoords[2].setX( (int) Math.round( ( (double) innerCoords[2].getX() + outerCoords[2].getX() )/2.0) );
        middleCoords[3].setX( (int) Math.round( ( (double) innerCoords[3].getX() + outerCoords[3].getX() )/2.0) );
        
        middleCoords[0].setY( (int) Math.round( ( (double) innerCoords[0].getY() + outerCoords[0].getY() )/2.0) );
        middleCoords[1].setY( (int) Math.round( ( (double) innerCoords[1].getY() + outerCoords[1].getY() )/2.0) );
        middleCoords[2].setY( (int) Math.round( ( (double) innerCoords[2].getY() + outerCoords[2].getY() )/2.0) );
        middleCoords[3].setY( (int) Math.round( ( (double) innerCoords[3].getY() + outerCoords[3].getY() )/2.0) );
        
    }
    
    private IntPoint[] findCoords(IntPoint p1, IntPoint p2)
    {
        double hypAngle;
        IntPoint coords[] = new IntPoint[4];
        for (int i = 0; i < 4; i++)
            coords[i] = new IntPoint();
        
        coords[0].setX(p1.getX());
        coords[0].setY(p1.getY());
        coords[2].setX(p2.getX());
        coords[2].setY(p2.getY());
        
        // Figure out the length of the sides... then the position of the other points
        int xDiff = coords[2].getX() - coords[0].getX();
        int yDiff = coords[2].getY() - coords[0].getY();
        if(xDiff != 0)
        {
            hypAngle = Math.atan((double)yDiff/xDiff);
            
        }
        else
        {
            if(yDiff > 0)
                hypAngle = Math.PI/2;
            else
                hypAngle = 0- Math.PI/2;
        }
        /*System.out.print("Hyp Angle: " + twoDigits.format(hypAngle*180/Math.PI)
                        + " | xDiff: " + twoDigits.format(xDiff)
                        + " | yDiff: " + twoDigits.format(yDiff) + "\n");
        */
        double hypDistSquared = (double) Math.pow(Math.abs(xDiff),2) + Math.pow(Math.abs(yDiff),2) ;
        double longSideLength = Math.sqrt(hypDistSquared/(1+Math.pow(widthFraction,2)));
        double shortSideLength = longSideLength*pathWidthVLengthFraction;
        
        double longSideSlope = hypAngle - longAngle;
        double relativeXDiff = longSideLength * Math.cos(longSideSlope);
        if(xDiff < 1)
            relativeXDiff = - relativeXDiff;
        double relativeYDiff = longSideLength * Math.sin(longSideSlope);
        
        
        if(xDiff >= 0)
        {
            coords[1].setY((int)(coords[0].getY() + Math.round(relativeYDiff)));
            coords[3].setY((int)(coords[0].getY() + Math.round(widthFraction*relativeXDiff)));
            coords[3].setX((int)(coords[0].getX() - Math.round(widthFraction*relativeYDiff)));
        }
        else
        {
            coords[1].setY((int)(coords[0].getY() - Math.round(relativeYDiff)));
            coords[3].setY((int)(coords[0].getY() + Math.round(widthFraction*relativeXDiff)));
            coords[3].setX((int)(coords[0].getX() + Math.round(widthFraction*relativeYDiff)));
        }
        if(xDiff == 0)
        {
            coords[1].setX((int)(coords[0].getX() - Math.round(relativeXDiff)));
            coords[3].setY((int)(coords[0].getY() - Math.round(widthFraction*relativeXDiff)));         
        }
        else
        {
            coords[1].setX((int)(coords[0].getX() + Math.round(relativeXDiff)));
        }
        
        //coords[3].setX((int)(coords[0].getX() - Math.round(widthFraction*relativeYDiff)));
        //coords[3].setY((int)(coords[0].getY() + Math.round(widthFraction*relativeXDiff)));
        
        /*System.out.print("Hyp Angle: " + twoDigits.format(hypAngle*180/Math.PI)
                        + " | Slope: " + twoDigits.format(longSideSlope*180/Math.PI) 
                        + " | xDiff: " + twoDigits.format(xDiff)
                        + " | yDiff: " + twoDigits.format(yDiff) + "\n");
         */
        /*System.out.print("Hyp Dist sqrd: " + twoDigits.format(hypDistSquared) + 
                         " | Long Dist: " + twoDigits.format(longSideLength) + 
                         " | Width Fraction: " + widthFraction + "\n");
        */ 
        return coords;
    }
    public IntPoint[] getMiddleCoords()
    {
        return middleCoords;
    }
    public IntPoint[] getOuterCoords()
    {
        return outerCoords;
    }
    public IntPoint[] getInnerCoords()
    {
        return innerCoords;
    }
    public void drawTrack(Graphics2D g)
    {
        int xPointsOuter[] = new int[4];
        int yPointsOuter[] = new int[4];
        int xPointsInner[] = new int[4];
        int yPointsInner[] = new int[4];
        int xPointsMiddle[] = new int[4];
        int yPointsMiddle[] = new int[4];
        for(int i=0;i<4;i++)
        {
            xPointsOuter[i] = outerCoords[i].getX();
            yPointsOuter[i] = outerCoords[i].getY();
            xPointsInner[i] = innerCoords[i].getX();
            yPointsInner[i] = innerCoords[i].getY();
            xPointsMiddle[i] = middleCoords[i].getX();
            yPointsMiddle[i] = middleCoords[i].getY();
        }
        Color colorStack = g.getColor();
        
        g.setColor(new Color(0,0,0));   // Set it to black
        g.drawPolygon(xPointsOuter,yPointsOuter,4);
        g.drawPolygon(xPointsInner, yPointsInner, 4);
        g.setColor(new Color(255,0,0)); // Set it to red
        g.drawPolygon(xPointsMiddle, yPointsMiddle, 4);
        
        g.setColor(colorStack);
    }
    public void reset()
    {
        for(int i=0;i<4;i++)
        {
            outerCoords[i].setX(0);
            outerCoords[i].setY(0);
            innerCoords[i].setX(0);
            innerCoords[i].setY(0);
            middleCoords[i].setX(0);
            middleCoords[i].setY(0);
        }
        
    }
    
    
}
