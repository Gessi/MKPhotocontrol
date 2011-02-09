package mkpc.maps;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.jdesktop.swingx.JXMapViewer;

import mkpc.log.LogSystem;

public class GMapLabel extends JLabel 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GMapLabel(ImageIcon imageIcon) 
	{
		super(imageIcon);
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		Image img = null;
		JXMapViewer map = mkpc.app.Application.sharedApplication().getMapViewController().getMap().getMainMap();
		MKWaypoint[] wps = mkpc.app.Application.sharedApplication().getMapViewController().getWaypointList();
		for(MKWaypoint wp : wps)
		{
			try 
			{
				if(wp.isSelected())
		    	{
		    		LogSystem.CLog("WaypointRenderer: Draw selected!");
		    		switch(wp.getWaypointStyle())
		        	{
		            	case kWaypointStyleStart:
		            		img = MKWaypointRenderer.getWaypointImage(wp.getWaypointStyle());
		            		break;
		            	case kWaypointStyleEnd:
		            		img = MKWaypointRenderer.getWaypointImage(wp.getWaypointStyle());
		            		break;
		            	case kWaypointStyleP:
		            		img = MKWaypointRenderer.getWaypointImage(WaypointStyle.kWaypointStylePSelected);
		            		break;
		            	case kWaypointStyleW:
		            		img = MKWaypointRenderer.getWaypointImage(WaypointStyle.kWaypointStyleWSelected);
		            		break;
		            	case kWaypointStyleBlue:
		            		img = MKWaypointRenderer.getWaypointImage(wp.getWaypointStyle());
		            		break;
		            	case kWaypointStyleRed:
		            		img = MKWaypointRenderer.getWaypointImage(wp.getWaypointStyle());
		            		break;
		        		default:
		        			img = MKWaypointRenderer.getWaypointImage(wp.getWaypointStyle());
		        	}
		    	}
		    	else
		    	{
		    		img = MKWaypointRenderer.getWaypointImage(wp.getWaypointStyle());
		    	}
				int x = (int) (map.convertGeoPositionToPoint(wp.getPosition()).getX()%this.getSize().width);
				int y = (int) (map.convertGeoPositionToPoint(wp.getPosition()).getY()%this.getSize().height);
				g.drawImage(img, x-(img.getWidth(this)/2), y-img.getHeight(this), img.getWidth(this), img.getHeight(this), this);
			} 
			catch (Exception e) 
			{
				LogSystem.CLog(e.getMessage()); //e.printStackTrace();
			}
		}	
	}
}
