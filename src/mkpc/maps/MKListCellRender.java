package mkpc.maps;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import mkpc.log.LogSystem;

public class MKListCellRender extends DefaultListCellRenderer 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private Hashtable iconTable = new Hashtable();
	
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus)
	{
		JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
		
		if (value instanceof MKWaypoint)
		{
			MKWaypoint waypoint = (MKWaypoint)value;
			ImageIcon icon = new ImageIcon(MKWaypointRenderer.getWaypointImage(waypoint.getWaypointStyle()));
			String text = "unknow";
			
			switch(waypoint.getWaypointStyle())
	    	{
	        	case kWaypointStyleStart:
	        		text = LogSystem.getMessage("waypointNameStart");
	        		break;
	        	case kWaypointStyleEnd:
	        		text = LogSystem.getMessage("waypointNameEnd");
	        		break;
	        	case kWaypointStyleP:
	        		text = LogSystem.getMessage("waypointNameP");
	        		break;
	        	case kWaypointStyleW:
	        		text = LogSystem.getMessage("waypointNameW");
	        		break;
	        	case kWaypointStyleBlue:
	        		text = LogSystem.getMessage("waypointNameBlue");
	        		break;
	        	case kWaypointStyleRed:
	        		text = LogSystem.getMessage("waypointNameRed");
	        		break;
	    		default:
	    			text = LogSystem.getMessage("waypointNameYellow");
	    	}
			label.setIcon(icon);
			label.setText(text);
		}
		else
		{
			label.setIcon(null);
		}
		
		return label;
	}
}
