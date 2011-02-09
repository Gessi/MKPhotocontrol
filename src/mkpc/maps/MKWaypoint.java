package mkpc.maps;

import org.jdesktop.beans.AbstractBean;
import org.jdesktop.swingx.mapviewer.GeoPosition;

public class MKWaypoint extends AbstractBean 
{
	private GeoPosition position;
    private WaypointStyle style;
    private boolean isSelected;
    /**
     * Creates a new instance of Waypoint at the specified
     * GeoPosition with a style (= null)
     * @param coord a GeoPosition to initialize the new Waypoint
     */
    public MKWaypoint(GeoPosition coord, WaypointStyle style) 
    {
        this.position = coord;
        
        if(style == null)
        {
        	this.style = WaypointStyle.kWaypointStyleYellow;
        }
        else
        {
        	this.style = style;
        }
    }
    
    /**
     * Get the current GeoPosition of this Waypoint
     * @return the current position
     */
    public GeoPosition getPosition() 
    {
        return position;
    }

    /**
     * Set a new GeoPosition for this Waypoint
     * @param coordinate a new position
     */
    public void setPosition(GeoPosition coordinate) 
    {
        GeoPosition old = getPosition();
        this.position = coordinate;
        firePropertyChange("position", old, getPosition());
    }
    
    /**
     * Get the current WaypointStyle
     * @return the current style
     */
    public WaypointStyle getWaypointStyle()
    {
    	return this.style;
    }
    
    /**
     * Set a new WaypointStyle
     * @param style
     */
    public void setWaypointStyle(WaypointStyle style)
    {
    	this.style = style;
    }

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public boolean isSelected() {
		return isSelected;
	}
}
