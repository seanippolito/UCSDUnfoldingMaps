package guimodule;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class LifeExpectancy extends PApplet{
	
	UnfoldingMap map;
	
	public void setup(){
		size(1000, 600, OPENGL);
		map = new UnfoldingMap(this, 200, 50, 700, 500, new Microsoft.AerialProvider());
		map.zoomLevel(2);
		MapUtils.createDefaultEventDispatcher(this, map);
	}
	public void draw(){
		background(10);
		map.draw();
	}
}
