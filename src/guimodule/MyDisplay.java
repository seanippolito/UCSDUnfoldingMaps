package guimodule;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class MyDisplay extends PApplet {
	private UnfoldingMap map;
	
	public void setup(){
//		size(400, 400);
//		background(200,200,200);
		size(950,600, OPENGL);
		
		map = new UnfoldingMap(this, 200, 50, 700, 500, new Microsoft.AerialProvider());
		map.zoomToLevel(1);
	    MapUtils.createDefaultEventDispatcher(this, map);
	    
	    List<PointFeature> bigEqs = new ArrayList<PointFeature>();

	    Location loc1 = new Location(-38.14f, -73.03f);
	    Feature chileEq = new PointFeature(loc1);
	    chileEq.addProperty("title", "Valdivia, Chile");
	    chileEq.addProperty("magnitude", "9.5");
	    chileEq.addProperty("date", "May 22, 1960");
	    chileEq.addProperty("year", "1960");
	    
	    Location loc2 = new Location(60.5889665f, -148.4087828f);
	    Feature alaskaEq = new PointFeature(loc2);
	    alaskaEq.addProperty("title", "Prince William Sound, Alaska, United States");
	    alaskaEq.addProperty("magnitude", "9.2");
	    alaskaEq.addProperty("date", "March 27, 1964");
	    alaskaEq.addProperty("year", "1964");
	    
	    Location loc3 = new Location(-0.142881f, 96.1472207f);
	    Feature sumatraEq = new PointFeature(loc3);
	    sumatraEq.addProperty("title", "Indian Ocean, Sumatra, Indonesia");
	    sumatraEq.addProperty("magnitude", "9.2");
	    sumatraEq.addProperty("date", "December 26, 2004");
	    sumatraEq.addProperty("year", "2004");
	    
	    Location loc4 = new Location(40.7971899f, 141.0469321f);
	    Feature japanEq = new PointFeature(loc4);
	    japanEq.addProperty("title", "Pacific Ocean, Tōhoku region, Japan");
	    japanEq.addProperty("magnitude", "9.1");
	    japanEq.addProperty("date", "March 11, 2011");
	    japanEq.addProperty("year", "2011");
	    
	    Location loc5 = new Location(56.1327377f, 159.5292511f);
	    Feature russiaEq = new PointFeature(loc5);
	    russiaEq.addProperty("title", "Kamchatka, Russian SFSR, Soviet Union");
	    russiaEq.addProperty("magnitude", "9.0");
	    russiaEq.addProperty("date", "November 4, 1952");
	    russiaEq.addProperty("year", "1952");
	    
	    bigEqs.add((PointFeature) chileEq);
	    bigEqs.add((PointFeature) alaskaEq);
	    bigEqs.add((PointFeature) sumatraEq);
	    bigEqs.add((PointFeature) japanEq);
	    bigEqs.add((PointFeature) russiaEq);	
	    
	    //bigEqs.add((PointFeature) valEq);	 
	    List<Marker> markers = new ArrayList<Marker>();
	    
	    for(PointFeature eq : bigEqs ){
	    	markers.add(new SimplePointMarker(eq.getLocation(), eq.getProperties()));
	    }
	    
	    int yellow = color(255,255,0);
	    int gray = color(200,200,200);
	    
	    for(Marker mk : markers){
	    	if(Integer.parseInt((String) mk.getProperty("year")) > 2000){
	    		mk.setColor(yellow);
	    	} else {
	    		mk.setColor(gray);
	    	}
	    }
	    
	    map.addMarkers(markers);    
	}
	public void draw(){
		background(10);
		map.draw();
//		fill(255, 255, 0);
//		ellipse(200, 200, 390, 390);
//		fill(0, 0, 0);
//		ellipse(130, 130, 50, 70);
//		ellipse(330, 130, 50, 70);
//		arc(250,280,75, 75, 0, PI);
	}
}
