package mkpc.maps;

import java.awt.Point;

import mkpc.log.LogSystem;

/**
 * 
 * Represent a GoogleMaps Coordinate
 * Have functions to calculate the Postion of Coordinate in the image.
 * 
 * @author Kristof Friess
 * @version 0.0.1
 * 
 */
public class GCoordinate 
{
	private double longitude;
	private double latitude;
	
	/**
	 * Create a GoogleMaps Coordinate with logitude and latidude and the pixel
	 * where this point can be found on the image.
	 * 
	 * @param longitude
	 * @param latitude
	 */
	public GCoordinate(double longitude, double latitude)
	{
		this.setLongitude(longitude);
		this.setLatitude(latitude);
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLatitude() {
		return latitude;
	}

	// >>> calculation of lat & long
	// >>> calculation reference by http://code.google.com/p/mapsforge/source/browse/trunk/mapsforge/src/org/mapsforge/android/maps/MercatorProjection.java
	// >>> customize for owne use
	public static GCoordinate getGCoordinateFromTilePoint(int tileX, int tileY, byte zoomLevel, GCoordinate mid, int tileWidth, int tileHeight)
	{	
		double pixelX = longitudeToPixelX(mid.longitude, zoomLevel, tileWidth) - tileWidth/2;
		double pixelY = latitudeToPixelY(mid.latitude, zoomLevel, tileHeight) - tileHeight/2;
		LogSystem.CLog("PixelX: " + (pixelX + tileX) + " PixelY: " + (pixelY + tileY));
		return new GCoordinate(pixelXToLongitude(pixelX + tileX, zoomLevel, tileWidth), pixelYToLatitude(pixelY + tileY, zoomLevel, tileHeight));
	}
	
	public static GCoordinate getGCoordinateFromMapPoint(int tileX, int tileY, byte zoomLevel, GCoordinate mid, int tileWidth, int tileHeight)
	{	
		double pixelX = longitudeToPixelX(mid.longitude, zoomLevel, tileWidth);
		double pixelY = latitudeToPixelY(mid.latitude, zoomLevel, tileHeight);
		
		return new GCoordinate(pixelXToLongitude(pixelX, zoomLevel, tileWidth), pixelYToLatitude(pixelY, zoomLevel, tileHeight));
	}
	
	public static Point getTilePixelForGCoordinateWithTileSize(GCoordinate coordinate, byte zoom, int tileWidth, int tileHeight, GCoordinate mid)
	{
		double midPixelX = longitudeToPixelX(mid.longitude, zoom, tileWidth);
		double midPixelY = latitudeToPixelY(mid.latitude, zoom, tileHeight);
		LogSystem.CLog("Mid " + midPixelX + " " + midPixelY);
		double pixelX = longitudeToPixelX(coordinate.longitude, zoom, tileWidth);
		double pixelY = latitudeToPixelY(coordinate.latitude, zoom, tileHeight);
		LogSystem.CLog("NewPix " + pixelX + " " + pixelY);
		int positionX = (int) (pixelX - (midPixelX - tileWidth/2));
		int positionY = (int) (pixelY - (midPixelY - tileHeight/2));
		
		return new Point(positionX, positionY);
	}
	
	public static double longitudeToPixelX(double longitude, byte zoom, int tileWidth)
	{	
		return ((longitude + 180) / 360 *((long) tileWidth << zoom));
	}
	
	public static double latitudeToPixelY(double latitude, byte zoom, int tileHeight)
	{
		double sinLatidude = Math.sin(latitude * Math.PI / 180);
		return ((0.5 - Math.log((1 + sinLatidude) / (1 - sinLatidude)) / (4 * Math.PI)) * ((long) tileHeight << zoom));
	}
	
	public static double pixelXToLongitude(double pixelX, byte zoom, int tileWidth)
	{
		return 360 * ((pixelX / ((long) tileWidth << zoom)) - 0.5);
	}
	
	public static double pixelYToLatitude(double pixelY, byte zoom, int tileHeight)
	{
		double y = 0.5 - (pixelY / ((long)tileHeight << zoom));
		return 90 - 360 * Math.atan(Math.exp(-y * 2 * Math.PI)) / Math.PI;
	}
	
	public static int tilePixelToLongitude(double tileX, GCoordinate tileCenter)
	{
		// get the postion of tile
		return 0;
	}
}

