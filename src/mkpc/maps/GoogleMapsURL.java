package mkpc.maps;

import org.jdesktop.swingx.mapviewer.GeoPosition;

public class GoogleMapsURL 
{
	private static String server = "http://maps.google.com/";
	private static String apiPath = "maps/api/staticmap?";
	private static String coordinateStartTage = "center=";
	private static String zoomTag = "zoom=";
	private static String maptypeTag = "maptype=";
	private static String sizeTag = "size=";
	private static String sensorTag = "sensor=false";
	private static String closeTag = "";
	
	public static String getGoogleStaticMapsURL(GeoPosition geo, byte zoom, String maptype, int width, int height)
	{
		return server + apiPath + coordinateStartTage + geo.getLatitude() + "," + geo.getLongitude() + 
		"&" + zoomTag + zoom + "&" + maptypeTag + maptype + "&" + sizeTag + width + "x" + height + "&" + sensorTag + closeTag;
	}
	
	public static String getGoogleStaticSatelliteMapsURL(GeoPosition geo, byte zoom, int width, int height)
	{
		return server + apiPath + coordinateStartTage + geo.getLatitude() + "," + geo.getLongitude() + 
		"&" + zoomTag + zoom + "&" + maptypeTag + "satellite" + "&" + sizeTag + width + "x" + height + "&" + sensorTag + closeTag;
	}
	
	public static String getGoogleStaticRoadmapMapsURL(GeoPosition geo, byte zoom, int width, int height)
	{
		return server + apiPath + coordinateStartTage + geo.getLatitude() + "," + geo.getLongitude() + 
		"&" + zoomTag + zoom + "&" + maptypeTag + "roadmap" + "&" + sizeTag + width + "x" + height + "&" + sensorTag + closeTag;
	}
}
