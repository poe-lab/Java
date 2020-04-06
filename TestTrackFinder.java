import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class TestTrackFinder {
    
  
    public static void main(String[] args) 
    {
        
        IntPoint myPoints[];
		JTrackFinder myTrackFinder;

		// Set up the points
		myPoints = new IntPoint[5];
		myPoints[0] = new IntPoint(3,4);
		myPoints[1] = new IntPoint(20,160);
		myPoints[2] = new IntPoint(50,45);
		myPoints[3] = new IntPoint(150,77);
		myPoints[4] = new IntPoint(75,48);
		
		myTrackFinder = new JTrackFinder(myPoints,600,500);
        
    }
    
}
