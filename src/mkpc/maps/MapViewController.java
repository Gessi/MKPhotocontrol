package mkpc.maps;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JPanel;

import mkpc.log.LogSystem;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.TileFactory;
import org.jdesktop.swingx.mapviewer.TileFactoryInfo;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class MapViewController extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// var
	private boolean isSatellite;
	private HashSet<MKWaypoint> waypoints;
	private ArrayList<MKWaypoint> sortedWaypoints;
	private MKWaypointPainter<?> waypointPainter;
	private WaypointStyle selectedWaypointStyle;

	// gui
	private Rectangle frame;
	private JXMapKit mapView;
	private JButton showSatellite;
	private JButton unshowSatellite;
	private GMapPanel satelliteMapView;
	
	@SuppressWarnings("rawtypes")
	public MapViewController(Rectangle frame)
	{
		super();
		waypoints = new HashSet<MKWaypoint>();
		waypointPainter = new MKWaypointPainter();
		waypointPainter.setWaypoints(waypoints);
		sortedWaypoints =  new ArrayList<MKWaypoint>();
		this.frame = frame;
		
		initVariables();
		initGUI();
	}
	
	private void initVariables()
	{
		isSatellite = false;
	}
	
	private void initGUI()
	{
		try
		{
			this.setLayout(null);
			this.setPreferredSize(new java.awt.Dimension(500, 360));
			this.setPreferredSize(new java.awt.Dimension(this.frame.width, this.frame.height));
			this.setSize(this.frame.width, this.frame.height);
			{
				mapView = new JXMapKit();
        		this.add(mapView);
				mapView.setBounds(0, 0, frame.width, frame.height);
				mapView.setPreferredSize(new java.awt.Dimension(this.frame.width, this.frame.height));
				mapView.setSize(this.frame.width, this.frame.height);
        		//mapView.setDefaultProvider(org.jdesktop.swingx.JXMapKit.DefaultProviders.OpenStreetMaps);
				TileFactoryInfo info = new TileFactoryInfo(1,19-2,19,
	                    256, true, true, // tile size is 256 and x/y orientation is normal
	                    "http://tile.openstreetmap.org",//5/15/10.png",
	                    "x","y","z") {
	                public String getTileUrl(int x, int y, int zoom) {
	                    zoom = 19-zoom;
	                    String url = this.baseURL +"/"+zoom+"/"+x+"/"+y+".png";
	                    return url;
	                }
	                
	            };
	            TileFactory tf = new DefaultTileFactory(info);
	            mapView.setTileFactory(tf);
				mapView.setCenterPosition(new GeoPosition(50.973735,11.022399));
        		mapView.getMainMap().setOverlayPainter(waypointPainter);
        		{
    				java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
    				gridBagConstraints.gridx = 0;
    			    gridBagConstraints.gridy = 0;
    			    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    			    gridBagConstraints.weightx = 1.0;
    			    gridBagConstraints.weighty = 1.0;
    			    gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
                	showSatellite = new JButton();
                	mapView.getMainMap().add(showSatellite, gridBagConstraints);
                	showSatellite.setBounds(0, 0, 150, 26);
                	showSatellite.setOpaque(false);
                	showSatellite.setName("showSatellite");
                	showSatellite.setText(LogSystem.getMessage("showSatellite"));
                	showSatellite.addActionListener(new ActionListener() {
                		public void actionPerformed(ActionEvent evt) {
                			showSatelliteActionPerformed(evt);
                		}
                	});
                }
        		mapView.getMainMap().addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent evt) {
						mapViewMouseClicked(evt);
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
			}
			{
				satelliteMapView = new GMapPanel(frame, null);
				this.add(satelliteMapView, 0);
				satelliteMapView.setBounds(mapView.getBounds());
				satelliteMapView.setVisible(false);
				satelliteMapView.setEnabled(false);
				{
					unshowSatellite = new JButton();
					satelliteMapView.add(unshowSatellite);
					unshowSatellite.setText(LogSystem.getMessage("unshowSatellite"));
					unshowSatellite.setOpaque(false);
					unshowSatellite.setBounds(5, 5, 150, 26);
					unshowSatellite.addActionListener(new ActionListener() {
                		public void actionPerformed(ActionEvent evt) {
                			unshowSatelliteActionPerformed(evt);
                		}
                	});
				}
				satelliteMapView.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent evt) {
						satelliteMapViewMouseClicked(evt);
					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
			}
			{
				selectedWaypointStyle = WaypointStyle.kWaypointStyleW;
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	private void showSatelliteActionPerformed(ActionEvent evt) 
    {
    	LogSystem.CLog("showSatellite.actionPerformed, event="+evt);
    	if(!isSatellite) 
    	{
    		isSatellite = true;
    		
    		byte zoom = (byte) (mapView.getZoomSlider().getMaximum() + 2 - mapView.getZoomSlider().getValue());
    		String url = GoogleMapsURL.getGoogleStaticSatelliteMapsURL(mapView.getCenterPosition(), zoom, mapView.getSize().width, mapView.getSize().height);
    		LogSystem.CLog(url);
    		mapView.setEnabled(false);
    		mapView.setVisible(false);
    		satelliteMapView.setImageURL(url);
    		satelliteMapView.setVisible(true);
			satelliteMapView.setEnabled(true);
			
			MKWaypointRenderer mkpr = new MKWaypointRenderer();
			
			for(MKWaypoint wp : this.waypoints)
			{
				mkpr.paintWaypoint((Graphics2D) satelliteMapView.getGraphics(), mapView.getMainMap(), wp);
			}
    	}
    	else
    	{
    		isSatellite = false;
    	}
    }
	
	private void unshowSatelliteActionPerformed(ActionEvent evt) 
    {
    	LogSystem.CLog("showSatellite.actionPerformed, event="+evt);
    	if(isSatellite) 
    	{
    		isSatellite = false;
    		
    		satelliteMapView.setVisible(false);
			satelliteMapView.setEnabled(false);
			
			mapView.setEnabled(true);
    		mapView.setVisible(true);
    		
    	}
    	else
    	{
    		isSatellite = true;
    	}
    }
	
	public WaypointStyle getWaypointStyle() {
		return selectedWaypointStyle;
	}

	public void setWaypointStyle(WaypointStyle selectedWaypointStyle) {
		this.selectedWaypointStyle = selectedWaypointStyle;
	}

	public JXMapKit getMap()
	{
		return mapView;
	}
	
// >> waypoint function
	public void addWaypointWithStyle(GeoPosition geopos, WaypointStyle style)
	{
		MKWaypoint wp = new MKWaypoint(geopos, style);
		waypoints.add(wp);
		if(wp.getWaypointStyle() == WaypointStyle.kWaypointStyleStart)
		{
			sortedWaypoints.add(0, wp);
			MKWaypointEditorWindow.sharedInstanz().startWaypointWasAdded();
		}
		else if(wp.getWaypointStyle() == WaypointStyle.kWaypointStyleEnd)
		{
			sortedWaypoints.add(wp);
			MKWaypointEditorWindow.sharedInstanz().endWaypointDidAdded();
		}
		else if(sortedWaypoints.size() > 1 && sortedWaypoints.get(sortedWaypoints.size()-1).getWaypointStyle() == WaypointStyle.kWaypointStyleEnd)
		{
			sortedWaypoints.add(sortedWaypoints.size()-1, wp);
		}
		else
		{
			sortedWaypoints.add(wp);
		}
		mapView.getMainMap().setOverlayPainter(waypointPainter);
		mapView.getMainMap().repaint();
		if(this.satelliteMapView != null)
		{
			this.satelliteMapView.repaint();
		}
		LogSystem.addLog(LogSystem.getMessage("waypointCreated") + "Lat:" + geopos.getLatitude() + " Long:" + geopos.getLongitude());
	}
	
	public void removeWaypoint(MKWaypoint wp)
	{
		waypoints.remove(wp);
		sortedWaypoints.remove(wp);
		if(wp.getWaypointStyle() == WaypointStyle.kWaypointStyleStart)
		{
			MKWaypointEditorWindow.sharedInstanz().startWaypointWasRemoved();
		}
		
		if(wp.getWaypointStyle() == WaypointStyle.kWaypointStyleEnd)
		{
			MKWaypointEditorWindow.sharedInstanz().endWaypointDidRemoved();
		}
		
		LogSystem.CLog("ArrayList: " + sortedWaypoints.toString());
	}
	
	public MKWaypoint[] getWaypointList()
	{
		MKWaypoint[] waypoints = new MKWaypoint[this.sortedWaypoints.size()];
		int index = 0;
		for(MKWaypoint wp : sortedWaypoints)
		{
			waypoints[index] = wp;
			index++;
		}
		
		return waypoints;
	}
	
	public void moveWaypointToIndex(MKWaypoint wp, int index)
	{
		if(index >= 0)
		{
			sortedWaypoints.remove(wp);
			LogSystem.CLog("Move Opject to index: " + index );
			sortedWaypoints.add(index, wp);
		}
	}

// >> mouse/ key control events
	private void mapViewMouseClicked(MouseEvent evt) 
    {
    	LogSystem.CLog("showSatellite.actionPerformed, event="+evt);
    	switch(evt.getClickCount())
    	{
	    	case 1:
	    		LogSystem.CLog("single tap");
	    		break;
	    	case 2:
	    		dispatchMouseDoubleClick(evt);
	    		break;
	    	default:
	    		break;
    	}
    }
	
	private void satelliteMapViewMouseClicked(MouseEvent evt) 
    {
    	LogSystem.CLog("showSatellite.actionPerformed, event="+evt);
    	switch(evt.getClickCount())
    	{
	    	case 1:
	    		break;
	    	case 2:
	    		dispatchMouseDoubleClick(evt);
	    		break;
	    	default:
	    		break;
    	}
    }
	
	private void dispatchMouseDoubleClick(MouseEvent evt)
	{
		GeoPosition geopos = mapView.getMainMap().convertPointToGeoPosition( new Point2D.Double(evt.getX(),evt.getY()));
		this.addWaypointWithStyle(geopos, selectedWaypointStyle);
		MKWaypointEditorWindow.setWaypointList(this.getWaypointList());
	}
}