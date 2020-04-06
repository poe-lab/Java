/*
 * TrackCoords.java
 *
 * Created on May 7, 2004, 12:32 PM
 */

/**
 *
 * @author  Kevin Ziemer
 */
public class TrackCoords {
    private IntPoint corners[];
    
    
    /** Creates a new instance of TrackCoords */
    public TrackCoords() 
    {
        corners = new IntPoint[4];
        reset();
        
    }
    public void setPoint(IntPoint inPoint, int pointID)
    {
        if((pointID >= 0) && (pointID < 4))
        {
            corners[pointID] = inPoint;
        }
    }

    public IntPoint getPoint(int pointID)
    {
        if((pointID >= 0) && (pointID < 4))
        {
            return corners[pointID];
        }
        else
        {
            return corners[0];
        }
    }
    public void reset()
    {
        for(int i=0; i < 4; i++)
        {
            corners[i] = new IntPoint();
        }
    }
    
}
