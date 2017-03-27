package guimodule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class LifeExpectancy extends PApplet {

	UnfoldingMap map;
	Map<String, Float> lifeExpByCountry;

	List<Feature> countries;
	List<Marker> countryMarkers;

	public void setup() {
		size(1000, 600, OPENGL);
		lifeExpByCountry = loadLifeExpFromCVS(
				"/Users/seanippolito/Documents/workspace/UCSDUnfoldingMaps/data/LifeExpectancyWorldBankModule3.csv");
		map = new UnfoldingMap(this, 200, 50, 700, 500, new Microsoft.AerialProvider());
		map.zoomLevel(2);
		MapUtils.createDefaultEventDispatcher(this, map);

		countries = GeoJSONReader.loadData(this,
				"/Users/seanippolito/Documents/workspace/UCSDUnfoldingMaps/data/countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		map.addMarkers(countryMarkers);
		shadeCountries();
	}

	public void draw() {
		background(10);
		map.draw();
	}

	private Map<String, Float> loadLifeExpFromCVS(String file) {
		Map<String, Float> lifeExpMap = new HashMap<String, Float>();

		String[] rows = loadStrings(file);
		for (String row : rows) {
			String[] columns = row.split(",");
			if (columns[5] != null && columns[3] != null) {
				float value = Float.parseFloat(columns[5]);
				lifeExpMap.put(columns[3], value);
			}
		}

		return lifeExpMap;
	}

	private void shadeCountries() {
		for (Marker mk : countryMarkers) {
			String countryId = mk.getId();

			if (lifeExpByCountry.containsKey(countryId)) {
				float lifeExp = lifeExpByCountry.get(countryId);
				int colorLevel = (int) map(lifeExp, 40, 90, 10, 255);
				mk.setColor(color(255 - colorLevel, 100, colorLevel));
			} else {
				mk.setColor(color(150, 150, 150));
			}
		}
	}
}
