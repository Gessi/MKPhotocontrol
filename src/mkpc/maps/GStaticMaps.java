package mkpc.maps;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mkpc.log.LogSystem;
import org.jdesktop.application.Application;

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

public class GStaticMaps extends javax.swing.JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int ZOOM_MAX = 18;
	private static final int ZOOM_MIN = 2;
	
	// gui variables
	private GMapPanel maptiles[];
	private int numbersOfMaptiles = 9;
	private JSlider zoomSlider;
	private JButton zoomInButton;
	private JButton zoomOutButton;

	// movement variables
	private Point lastPoint;
	private byte zoomFactor;
	private GCoordinate mapCenter;
	
	
	public GStaticMaps() 
	{
		super();
		
		// set up variables
		zoomFactor = ZOOM_MIN;
		mapCenter = new GCoordinate(-50.0, 50.0);
		// >> Testing
		{
			Point p = GCoordinate.getTilePixelForGCoordinateWithTileSize(new GCoordinate(-180.0, 80.0), (byte)4, 400, 300, new GCoordinate(-180.0, 85.0));
			LogSystem.CLog("POINT X: " + p.x + " Y: " + p.y);
			
			GCoordinate gc = GCoordinate.getGCoordinateFromTilePoint(200, 680, (byte)4, new GCoordinate(-180.0, 85.0), 400, 300);
			LogSystem.CLog("COORDINATE Long: " + gc.getLongitude() + " LAT: " + gc.getLatitude());
		}
		// >> Testing END
		
		// set up gui
		initGUI();
	}
	
	private void initGUI() 
	{
		try 
		{
			this.setPreferredSize(new Dimension(300, 300));
			this.setSize(300, 300);
			this.setLayout(null);
			this.addMouseWheelListener(new MouseWheelListener() {
				public void mouseWheelMoved(MouseWheelEvent evt) {
					thisMouseWheelMoved(evt);
				}
			});
			this.addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent evt) {
					thisMouseDragged(evt);
				}
			});
			this.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					thisMouseClicked(evt);
				}
				
				public void mouseReleased(MouseEvent evt) {
					thisMouseReleased(evt);
				}
			});
			
			{
				zoomInButton = new JButton();
				this.add(zoomInButton);
				zoomInButton.setBounds(8, 25, 24, 24);
				zoomInButton.setName("zoomInButton");
				zoomInButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						zoomInButtonActionPerformed(evt);
					}
				});
			}
			{
				zoomOutButton = new JButton();
				this.add(zoomOutButton);
				zoomOutButton.setName("zoomOutButton");
				zoomOutButton.setBounds(8, 253, 24, 24);
				zoomOutButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						zoomOutButtonActionPerformed(evt);
					}
				});
			}
			{
				zoomSlider = new JSlider(JSlider.VERTICAL, ZOOM_MIN, ZOOM_MAX, ZOOM_MIN);
				this.add(zoomSlider);
				zoomSlider.setBounds(8, 50, 26, 200);
				zoomSlider.setName("zoomSlider");
				zoomSlider.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent evt) {
						zoomSliderStateChanged(evt);
					}
				});
			}
			
			if(this.maptiles == null)
			{
				// load the map tile to place
				// move from left to right
				this.maptiles = new GMapPanel[this.numbersOfMaptiles];
				int contentWidth = this.getSize().width;
				int contentHeight = this.getSize().height;
				int posX = -contentWidth;
				int posY = -contentHeight;
				int index = 0;
				double pixelX = GCoordinate.longitudeToPixelX(mapCenter.getLongitude(), zoomFactor, contentWidth);
				double pixelY = GCoordinate.latitudeToPixelY(mapCenter.getLatitude(), zoomFactor, contentHeight);
				
				
				for (int i = 0; i < 3; i++)
				{
					for (int j = 0; j < 3; j++)
					{
						GCoordinate gc = new GCoordinate(GCoordinate.pixelXToLongitude(pixelX+posX, zoomFactor, contentWidth), GCoordinate.pixelYToLatitude(pixelY+posY, zoomFactor, contentHeight));
						LogSystem.CLog("COORDINATE Long: " + gc.getLongitude() + " LAT: " + gc.getLatitude());
						LogSystem.CLog("PosX:" + posX + " PosY:" + posY + " Width:" + contentWidth + " Height:" + contentHeight);
						Rectangle frame = new Rectangle(posX, posY, contentWidth, contentHeight);
						GMapPanel mapTile = new GMapPanel(frame, this.URLForGCoordinate(gc));
						mapTile.setBackground(new Color(0.2f*j, 0.1f*i, 1.0f, 1.0f));
						this.add(mapTile);
						this.maptiles[index] = mapTile;
						posX += contentWidth;
						index++;
					}
					posY += this.getSize().height;
					posX = -contentWidth;
				}
			}
			
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
// >>> URL creation functions
	
	private String server = "http://maps.google.com/";
	private String apiPath = "maps/api/staticmap?";
	private String coordinateStartTage = "center=";
	private String zoomTag = "zoom=";
	private String maptypeTag = "maptype=roadmap";
	private String sizeTag = "size=400x300";
	private String sensorTag = "sensor=false";
	private String closeTag = "";
	
	private String URLForGCoordinate(GCoordinate coordinate)
	{
		// example URL 
		// http://maps.google.com/maps/api/staticmap?center=50.0,-50.0&zoom=2&size=400x300&maptype=roadmap&sensor=false
		
		return server + apiPath + coordinateStartTage + coordinate.getLatitude() + "," + coordinate.getLongitude() + 
				"&" + zoomTag + zoomFactor + "&" + maptypeTag + "&" + sizeTag + "&" + sensorTag + closeTag;
	}
// >>> MapTile draw function  / caching
	private void redrawMapTile()
	{
		for(GMapPanel mapTile : this.maptiles)
		{
			mapTile.setImageURL(this.URLForGCoordinate(mapCenter));
		}
	}
// >>> Calculation/ help functions
	private void setZoomFactorTo(byte i)
	{
		// leave function if zoom range ends
		if(i < ZOOM_MIN || i > ZOOM_MAX)
		{
			LogSystem.addLog("Zoom range ends!");
			return;
		}
		
		this.zoomFactor = i;
		this.zoomSlider.setValue(i);
		this.redrawMapTile();
	}
	
	
// >>> ActionListner/ MouseListner/ etc.
	// >> mouse actions
	private void thisMouseClicked(MouseEvent evt) 
	{
		LogSystem.CLog("this.mouseClicked, event="+evt);
	}
	
	private void thisMouseReleased(MouseEvent evt)
	{
		LogSystem.CLog("this.mouseClicked, event="+evt);
		this.lastPoint = null;
	}
	
	// mouse dragged
	private void thisMouseDragged(MouseEvent evt) 
	{
		if(this.lastPoint != null)
		{
			this.thisMouseMovedToPoint(evt.getPoint());
		}

		this.lastPoint = evt.getPoint();
	}

	// move map tile with mouse
	private void thisMouseMovedToPoint(Point point) 
	{
		LogSystem.CLog("Mouse move to " + point.x + ", " + point.y);
		int x = (point.x - this.lastPoint.x);
		int y = (point.y - this.lastPoint.y);
		LogSystem.CLog("Move with " + x + ", " + y);
		
		for(GMapPanel mapTile : this.maptiles)
		{
			Rectangle newFrame = mapTile.getBounds();
			newFrame.x += x;
			newFrame.y += y;
			
			mapTile.setBounds(newFrame);
		}
	}
	
	// >> zoom actions
	private void zoomInButtonActionPerformed(ActionEvent evt) 
	{
		LogSystem.CLog("zoomInButton.actionPerformed, event="+evt);
		this.setZoomFactorTo((byte) (this.zoomFactor + 1));
	}
	
	private void zoomOutButtonActionPerformed(ActionEvent evt) 
	{
		LogSystem.CLog("zoomOutButton.actionPerformed, event="+evt);
		this.setZoomFactorTo((byte) (this.zoomFactor - 1));
	}
	
	private void zoomSliderStateChanged(ChangeEvent evt)
	{
		LogSystem.CLog("zoomSlider.stateChanged, event="+evt);
		this.setZoomFactorTo((byte) this.zoomSlider.getValue());
	}
	
	private void thisMouseWheelMoved(MouseWheelEvent evt) {
		System.out.println("this.mouseWheelMoved, event="+evt);
		int notches = evt.getWheelRotation();
		if(notches < 0)
		{
			// mouse wheel move up
			this.setZoomFactorTo((byte) (this.zoomFactor + 1));
		}
		else
		{
			// mouse wheel move down
			this.setZoomFactorTo((byte) (this.zoomFactor - 1));
		}
	}
}
