package mkpc.maps;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import mkpc.log.LogSystem;

import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointRenderer;

enum WaypointStyle
{
	kWaypointStyleStart, 	// 0
	kWaypointStyleEnd, 		// 1
	kWaypointStyleP,		// 2
	kWaypointStyleW,		// 3
	kWaypointStyleRed,		// 4
	kWaypointStyleBlue,		// 5
	kWaypointStyleYellow,	// 6
	kWaypointStyleWSelected,// 7
	kWaypointStylePSelected	// 8
};

public class MKWaypointRenderer implements WaypointRenderer 
{	
	static BufferedImage start = null;
	static BufferedImage end = null;
	static BufferedImage p = null;
	static BufferedImage w = null;
	static BufferedImage blue = null;
	static BufferedImage yellow = null;
	static BufferedImage red = null;
	static BufferedImage wSelected = null;
	static BufferedImage pSelected = null;
	
    public MKWaypointRenderer() 
    {
        try 
		{
		    start = ImageIO.read(getClass().getResource("resources/waypoint.start.png"));
			end = ImageIO.read(getClass().getResource("resources/waypoint.end.png"));
			p = ImageIO.read(getClass().getResource("resources/waypoint.p.png"));
			w = ImageIO.read(getClass().getResource("resources/waypoint.w.png"));
			blue = ImageIO.read(getClass().getResource("resources/waypoint.blue.png"));
			red = ImageIO.read(getClass().getResource("resources/waypoint.red.png"));
			yellow = ImageIO.read(getClass().getResource("resources/waypoint.yellow.png"));
			
			pSelected = ImageIO.read(getClass().getResource("resources/waypoint.p.selected.png"));
			wSelected = ImageIO.read(getClass().getResource("resources/waypoint.w.selected.png"));
        } 
        catch (Exception ex) 
        {
            LogSystem.CLog("Couldn't read waypoint image.");
            LogSystem.CLog(ex.getMessage());
            //ex.printStackTrace();
        }
    }
    
    public static BufferedImage getWaypointImage(WaypointStyle style)
    {
    	switch(style)
    	{
        	case kWaypointStyleStart:
        		return start;
        	case kWaypointStyleEnd:
        		return end;
        	case kWaypointStyleP:
        		return p;
        	case kWaypointStylePSelected:
        		return pSelected;
        	case kWaypointStyleW:
        		return w;
        	case kWaypointStyleWSelected:
        		return wSelected;
        	case kWaypointStyleBlue:
        		return blue;
        	case kWaypointStyleRed:
        		return red;
    		default:
    			return yellow;
    	}
    }

    public boolean paintWaypoint(Graphics2D g, JXMapViewer map, MKWaypoint waypoint) 
    {
    	BufferedImage img = null;
    	if(waypoint.isSelected())
    	{
    		switch(waypoint.getWaypointStyle())
        	{
            	case kWaypointStyleStart:
            		img = MKWaypointRenderer.getWaypointImage(waypoint.getWaypointStyle());
            		break;
            	case kWaypointStyleEnd:
            		img = MKWaypointRenderer.getWaypointImage(waypoint.getWaypointStyle());
            		break;
            	case kWaypointStyleP:
            		img = MKWaypointRenderer.getWaypointImage(WaypointStyle.kWaypointStylePSelected);
            		break;
            	case kWaypointStyleW:
            		img = MKWaypointRenderer.getWaypointImage(WaypointStyle.kWaypointStyleWSelected);
            		break;
            	case kWaypointStyleBlue:
            		img = MKWaypointRenderer.getWaypointImage(waypoint.getWaypointStyle());
            		break;
            	case kWaypointStyleRed:
            		img = MKWaypointRenderer.getWaypointImage(waypoint.getWaypointStyle());
            		break;
        		default:
        			img = MKWaypointRenderer.getWaypointImage(waypoint.getWaypointStyle());
        	}
    	}
    	else
    	{
    		img = MKWaypointRenderer.getWaypointImage(waypoint.getWaypointStyle());
    	}
        
    	if(img != null) {
            g.drawImage(img,-img.getWidth()/2,-img.getHeight(),null);
        } else {
            g.setStroke(new BasicStroke(3f));
            g.setColor(Color.BLUE);
            g.drawOval(-10,-10,20,20);
            g.setStroke(new BasicStroke(1f));
            g.drawLine(-10,0,10,0);
            g.drawLine(0,-10,0,10);
        }
        return false;
    }

	@Override
	public boolean paintWaypoint(Graphics2D g, JXMapViewer map,Waypoint waypoint) 
	{
		BufferedImage img = yellow;
		if(img != null) 
		{
            g.drawImage(img,-img.getWidth()/2,-img.getHeight(),null);
        } 
		else 
		{
            g.setStroke(new BasicStroke(3f));
            g.setColor(Color.BLUE);
            g.drawOval(-10,-10,20,20);
            g.setStroke(new BasicStroke(1f));
            g.drawLine(-10,0,10,0);
            g.drawLine(0,-10,0,10);
        }
		return false;
	}
}
