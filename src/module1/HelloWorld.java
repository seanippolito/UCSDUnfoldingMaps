package module1;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.Calendar;
import java.util.Locale;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

/** HelloWorld
  * An application with two maps side-by-side zoomed in on different locations.
  * Author: UC San Diego Coursera Intermediate Programming team
  * @author Your name here
  * Date: July 17, 2015
  * */
public class HelloWorld extends PApplet
{
	/** Your goal: add code to display second map, zoom in, and customize the background.
	 * Feel free to copy and use this code, adding to it, modifying it, etc.  
	 * Don't forget the import lines above. */

	// You can ignore this.  It's to keep eclipse from reporting a warning
	private static final long serialVersionUID = 1L;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// IF YOU ARE WORKING OFFLINE: Change the value of this variable to true
	private static final boolean offline = false;
	private PImage backgroundImg;
	private String URL = "http://www.rantlifestyle.com/wp-content/uploads/2014/08/703.-More-Than-One-Dream.jpg";
	
	/** The map we use to display our home town: La Jolla, CA */
	UnfoldingMap map1;
	
	/** The map you will use to display any town */ 
	UnfoldingMap map2;
	UnfoldingMap map3;

	public void setup() {
		size(1250, 625, P2D);  // Set up the Applet window to be 800x600
		                      // The OPENGL argument indicates to use the 
		                      // Processing library's 2D drawing
		                      // You'll learn more about processing in Module 3
		background(255);
		stroke(0);

		// This sets the background color for the Applet.  
		// Play around with these numbers and see what happens!
		//this.background(200, 200, 200);
		backgroundImg = loadImage(URL, "jpg");
		backgroundImg.resize(0, height);
		image(backgroundImg, 0, 0);
		// Select a map provider
		AbstractMapProvider provider = new Google.GoogleTerrainProvider();
		// Set a zoom level
		int zoomLevel = 12;
		
		if (offline) {
			// If you are working offline, you need to use this provider 
			// to work with the maps that are local on your computer.  
			provider = new MBTilesMapProvider(mbTilesString);
			// 3 is the maximum zoom level for working offline
			zoomLevel = 3;
		}
		
		// Create a new UnfoldingMap to be displayed in this window.  
		// The 2nd-5th arguments give the map's x, y, width and height
		// When you create your map we want you to play around with these 
		// arguments to get your second map in the right place.
		// The 6th argument specifies the map provider.  
		// There are several providers built-in.
		// Note if you are working offline you must use the MBTilesMapProvider
		map1 = new UnfoldingMap(this, 50, 50, 350, 500, provider);
		map2 = new UnfoldingMap(this, 450, 50, 350, 500, provider);
		map3 = new UnfoldingMap(this, 850, 50, 350, 500, provider);

		// The next line zooms in and centers the map at 
	    // 32.9 (latitude) and -117.2 (longitude)
	    map1.zoomAndPanTo(zoomLevel, new Location(32.9f, -117.2f));
	    map2.zoomAndPanTo(zoomLevel, new Location(40.136719f, -74.2351907f));
	    map3.zoomAndPanTo(zoomLevel, new Location(33.8253183f, -117.9580328f));

		// This line makes the map interactive
		MapUtils.createDefaultEventDispatcher(this, map1);
		MapUtils.createDefaultEventDispatcher(this, map2);
		MapUtils.createDefaultEventDispatcher(this, map3);

	}

	/** Draw the Applet window.  */
	public void draw() {
		// So far we only draw map1...
		// TODO: Add code so that both maps are displayed
		int[] color = sunColorSec(second());
		fill(color[0], color[1], color[2]);
		ellipse(width/8, height/4, width/8, height/4);
		
//		map1.draw();
//		map2.draw();
//		map3.draw();
	}
	
	public int[] sunColorSec(float seconds) {
		int[] rgb = new int[3];
		float diffFrom30 = Math.abs(30-seconds);
		float ratio = diffFrom30/30;
		rgb[0] = (int)(ratio*255);
		rgb[1] = (int)(ratio*255);
		rgb[2] = 0;
		
		return rgb;
	}

	
}
