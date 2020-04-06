import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class TestTrackCoords {
    
  
    public static void main(String[] args) 
    {
        TrackCoords myCoords;
        DoubPoint myPoint;
        myCoords = new TrackCoords();
        myPoint = new DoubPoint(2,3);
        System.out.print("\nCoordinates:\n\n");
        myCoords.setPoint(myPoint, 2);
        for(int i = 0; i < 4; i++)
        {
            System.out.print(myCoords.getPoint(i)+"\n");
            
        }
        
    }
    
}
