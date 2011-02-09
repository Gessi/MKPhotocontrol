package mkpc.maps;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mkpc.app.MKSettings;
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
public class MKWaypointEditorWindow extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static MKWaypointEditorWindow sharedInstance;
	
	// variables from the application
	private MapViewController mapViewController;
	private mkpc.app.Application app;
	private JButton wps_end;
	private static JList wayPointList;
	private DefaultComboBoxModel wayPointListModel;
	private JScrollPane wayPointListScrollView;
	private JButton wps_waypoint;
	private JButton wps_photocontrol;
	private JButton wps_start;
	private JButton wps_blue;
	private JButton wps_red;
	private JButton wps_yellow;
	private JButton lastSelectedButton = null;
	private JPanel panelWaypointStyle;
	
	private JButton btn_deleteWaypoint;
	private JButton btn_waypointUp;
	private JButton btn_waypointDown;
	
	private MKWaypoint lastSelectedWaypoint = null;

	public MKWaypointEditorWindow() 
	{
		super();
		
		// application variables
		app = mkpc.app.Application.sharedApplication();
		mapViewController = app.getMapViewController();
		
		initGUI();
	}
	
	private void initGUI() 
	{
		try 
		{
			BorderLayout thisLayout = new BorderLayout();
			this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			getContentPane().setLayout(thisLayout);
			{
				panelWaypointStyle = new JPanel();
				GridLayout panelWaypointStyleLayout = new GridLayout(7, 1);
				panelWaypointStyleLayout.setHgap(1);
				panelWaypointStyleLayout.setVgap(1);
				panelWaypointStyleLayout.setColumns(1);
				panelWaypointStyleLayout.setRows(7);
				panelWaypointStyle.setLayout(panelWaypointStyleLayout);
				getContentPane().add(panelWaypointStyle, BorderLayout.EAST);
				panelWaypointStyle.setPreferredSize(new java.awt.Dimension(50, 360));
				{
					wps_yellow = new JButton();
					//panelWaypointStyle.add(wps_yellow);
					wps_yellow.setName("wps_yellow");
					wps_yellow.setEnabled(false);
					wps_yellow.setIcon(new ImageIcon(getClass().getResource("resources/waypoint.yellow.png")));
					wps_yellow.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							wps_yellowActionPerformed(evt);
						}
					});
				}
				{
					wps_red = new JButton();
					//panelWaypointStyle.add(wps_red);
					wps_red.setName("wps_red");
					wps_red.setEnabled(false);
					wps_red.setIcon(new ImageIcon(getClass().getResource("resources/waypoint.red.png")));
					wps_red.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							wps_redActionPerformed(evt);
						}
					});
				}
				{
					wps_blue = new JButton();
					//panelWaypointStyle.add(wps_blue);
					wps_blue.setName("wps_blue");
					wps_blue.setEnabled(false);
					wps_blue.setIcon(new ImageIcon(getClass().getResource("resources/waypoint.blue.png")));
					wps_blue.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							wps_blueActionPerformed(evt);
						}
					});
				}
				{
					wps_start = new JButton();
					panelWaypointStyle.add(wps_start);
					wps_start.setName("wps_start");
					wps_start.setIcon(new ImageIcon(getClass().getResource("resources/waypoint.start.png")));
					wps_start.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							wps_startActionPerformed(evt);
						}
					});
				}
				{
					wps_end = new JButton();
					panelWaypointStyle.add(wps_end);
					wps_end.setName("wps_end");
					wps_end.setEnabled(false);
					wps_end.setIcon(new ImageIcon(getClass().getResource("resources/waypoint.end.png")));
					wps_end.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							wps_endActionPerformed(evt);
						}
					});
				}
				{
					wps_photocontrol = new JButton();
					panelWaypointStyle.add(wps_photocontrol);
					wps_photocontrol.setName("wps_photocontrol");
					wps_photocontrol.setIcon(new ImageIcon(getClass().getResource("resources/waypoint.p.png")));
					wps_photocontrol.setSize(65, 40);
					wps_photocontrol.setPreferredSize(new java.awt.Dimension(65, 40));
					wps_photocontrol.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							wps_photocontrolActionPerformed(evt);
						}
					});
				}
				{
					wps_waypoint = new JButton();
					panelWaypointStyle.add(wps_waypoint);
					wps_waypoint.setName("wps_waypoint");
					wps_waypoint.setIcon(new ImageIcon(getClass().getResource("resources/waypoint.w.png")));
					wps_waypoint.setSize(65, 40);
					wps_waypoint.setEnabled(false);
					wps_waypoint.setPreferredSize(new java.awt.Dimension(65, 40));
					wps_waypoint.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							wps_waypointActionPerformed(evt);
						}
					});
				}
				lastSelectedButton = wps_waypoint;
				
				{
					btn_deleteWaypoint = new JButton();
					panelWaypointStyle.add(btn_deleteWaypoint);
					btn_deleteWaypoint.setName("btn_deleteWaypoint");
					btn_deleteWaypoint.setIcon(new ImageIcon(getClass().getResource("resources/waypoint.delete.png")));
					btn_deleteWaypoint.setSize(65, 40);
					btn_deleteWaypoint.setPreferredSize(new java.awt.Dimension(65, 40));
					btn_deleteWaypoint.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btn_deleteWaypointActionPerformed(evt);
						}
					});
				}
				{
					btn_waypointUp = new JButton();
					panelWaypointStyle.add(btn_waypointUp);
					btn_waypointUp.setName("btn_waypointUp");
					btn_waypointUp.setIcon(new ImageIcon(getClass().getResource("resources/waypoint.up.png")));
					btn_waypointUp.setSize(65, 40);
					btn_waypointUp.setPreferredSize(new java.awt.Dimension(65, 40));
					btn_waypointUp.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btn_waypointUpActionPerformed(evt);
						}
					});
				}
				{
					btn_waypointDown = new JButton();
					panelWaypointStyle.add(btn_waypointDown);
					btn_waypointDown.setName("btn_waypointDown");
					btn_waypointDown.setIcon(new ImageIcon(getClass().getResource("resources/waypoint.down.png")));
					btn_waypointDown.setSize(65, 40);
					btn_waypointDown.setPreferredSize(new java.awt.Dimension(65, 40));
					btn_waypointDown.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btn_waypointDownActionPerformed(evt);
						}
					});
				}
			}
			{
				wayPointListScrollView = new JScrollPane();
				this.add(wayPointListScrollView, BorderLayout.CENTER);
				wayPointListScrollView.setAutoscrolls(false);
				{
					wayPointListModel = new DefaultComboBoxModel();
					wayPointList = new JList();
					wayPointListScrollView.setViewportView(wayPointList);
					wayPointList.setModel(wayPointListModel);
					ListSelectionModel wayPointListSelectionModel = new DefaultListSelectionModel();
					wayPointListSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					wayPointList.setSelectionModel(wayPointListSelectionModel);
					wayPointList.setCellRenderer(new MKListCellRender());
					wayPointList.addKeyListener(new KeyAdapter() {
						public void keyReleased(KeyEvent evt) {
							wayPointListKeyReleased(evt);
						}
					});
					wayPointList.addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent evt) {
							wayPointListValueChanged(evt);
						}
					});
					wayPointList.setListData(mapViewController.getWaypointList());
				}
			}
			
			if(MKSettings.hasValueForKey("waypointEditorPositionX") && MKSettings.hasValueForKey("waypointEditorPositionY"))
			{
				Rectangle frame = this.getBounds();
				frame.x = Integer.parseInt(MKSettings.getValueForKey("waypointEditorPositionX"));
				frame.y = Integer.parseInt(MKSettings.getValueForKey("waypointEditorPositionY"));
				this.setBounds(frame);
			}
			this.pack();
			this.setSize(210, 400);
			
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(getContentPane());
			
			if(sharedInstance == null)
			{
				sharedInstance = this;
				this.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent evt) {
						thisWindowClosing(evt);
					}
				});
			}
		} 
		catch (Exception e) 
		{
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	public static MKWaypointEditorWindow sharedInstanz()
	{
		return sharedInstance;
	}

// >> button click events
	private void wps_yellowActionPerformed(ActionEvent evt) 
	{
		LogSystem.CLog("wps_yellow.actionPerformed, event="+evt);
		if(lastSelectedButton != null)
		{
			lastSelectedButton.setEnabled(true);
		}
		wps_yellow.setEnabled(false);
		mapViewController.setWaypointStyle(WaypointStyle.kWaypointStyleYellow);
		lastSelectedButton = wps_yellow;
	}
	
	private void wps_redActionPerformed(ActionEvent evt) 
	{
		LogSystem.CLog("wps_red.actionPerformed, event="+evt);
		if(lastSelectedButton != null)
		{
			lastSelectedButton.setEnabled(true);
		}
		wps_red.setEnabled(false);
		mapViewController.setWaypointStyle(WaypointStyle.kWaypointStyleRed);
		lastSelectedButton = wps_red;
	}
	
	private void wps_blueActionPerformed(ActionEvent evt) 
	{
		LogSystem.CLog("wps_blue.actionPerformed, event="+evt);
		if(lastSelectedButton != null)
		{
			lastSelectedButton.setEnabled(true);
		}
		wps_blue.setEnabled(false);
		mapViewController.setWaypointStyle(WaypointStyle.kWaypointStyleBlue);
		lastSelectedButton = wps_blue;
	}
	
	private void wps_startActionPerformed(ActionEvent evt) 
	{
		LogSystem.CLog("wps_start.actionPerformed, event="+evt);
		if(lastSelectedButton != null)
		{
			lastSelectedButton.setEnabled(true);
		}
		wps_start.setEnabled(false);
		mapViewController.setWaypointStyle(WaypointStyle.kWaypointStyleStart);
		lastSelectedButton = wps_start;
	}
	
	private void wps_endActionPerformed(ActionEvent evt) 
	{
		LogSystem.CLog("wps_end.actionPerformed, event="+evt);
		if(lastSelectedButton != null)
		{
			lastSelectedButton.setEnabled(true);
		}
		wps_end.setEnabled(false);
		mapViewController.setWaypointStyle(WaypointStyle.kWaypointStyleEnd);
		lastSelectedButton = wps_end;
	}
	
	private void wps_photocontrolActionPerformed(ActionEvent evt) 
	{
		LogSystem.CLog("wps_photocontrol.actionPerformed, event="+evt);
		if(lastSelectedButton != null)
		{
			lastSelectedButton.setEnabled(true);
		}
		wps_photocontrol.setEnabled(false);
		mapViewController.setWaypointStyle(WaypointStyle.kWaypointStyleP);
		lastSelectedButton = wps_photocontrol;
	}
	
	private void wps_waypointActionPerformed(ActionEvent evt) 
	{
		LogSystem.CLog("wps_waypoint.actionPerformed, event="+evt);
		if(lastSelectedButton != null)
		{
			lastSelectedButton.setEnabled(true);
		}
		wps_waypoint.setEnabled(false);
		mapViewController.setWaypointStyle(WaypointStyle.kWaypointStyleW);
		lastSelectedButton = wps_waypoint;
	}
	
	private void btn_deleteWaypointActionPerformed(ActionEvent evt) 
	{
		LogSystem.CLog("btn_deleteWaypoint.actionPerformed, event="+evt);
		this.deleteSelcetedItemFromList();
	}

	public static void setWaypointList(MKWaypoint[] waypoints)
	{
		if(wayPointList != null)
			MKWaypointEditorWindow.wayPointList.setListData(waypoints);
	}
	
// >> waypoint control add 
	public void startWaypointWasAdded()
	{
		wps_start.setEnabled(false);
		if(wps_start == lastSelectedButton)
		{
			wps_waypoint.setEnabled(false);
			mapViewController.setWaypointStyle(WaypointStyle.kWaypointStyleW);
			lastSelectedButton = wps_waypoint;
		}
		
		MKWaypoint[] wps = mapViewController.getWaypointList();
	
		if(wps[wps.length - 1].getWaypointStyle() != WaypointStyle.kWaypointStyleEnd)
		{
			wps_end.setEnabled(true);
		}
	}
	
	public void startWaypointWasRemoved()
	{
		wps_start.setEnabled(true);
	}
	
	public void endWaypointDidAdded()
	{
		wps_end.setEnabled(false);
		
		if(wps_end == lastSelectedButton)
		{
			wps_waypoint.setEnabled(false);
			mapViewController.setWaypointStyle(WaypointStyle.kWaypointStyleW);
			lastSelectedButton = wps_waypoint;
		}
	}
	
	public void endWaypointDidRemoved()
	{
		wps_end.setEnabled(true);
	}

	public void deleteSelcetedItemFromList()
	{
		MKWaypoint wp = null;
		if( (wp = (MKWaypoint) wayPointList.getSelectedValue()) != null)
		{
			int index = wayPointList.getSelectedIndex();
			mapViewController.removeWaypoint(wp);
			mapViewController.repaint();
			wayPointList.setListData(mapViewController.getWaypointList());
			
			if(index + 1 >= wayPointList.getModel().getSize())
			{
				wayPointList.setSelectedIndex(wayPointList.getModel().getSize()-1);
			}
			else
			{
				wayPointList.setSelectedIndex(index);
			}
		}
		else
		{
			LogSystem.addLog(LogSystem.getMessage("waypointEditorError001"));
		}
	}
// >> list control events
	private void wayPointListValueChanged(ListSelectionEvent evt) 
	{
		LogSystem.CLog("wayPointList.valueChanged, event="+evt);
		
		if(!evt.getValueIsAdjusting())
		{
			MKWaypoint wp = (MKWaypoint)wayPointList.getSelectedValue();
			
			if(lastSelectedWaypoint != null)
			{
				lastSelectedWaypoint.setSelected(false);
			}
			lastSelectedWaypoint = wp;
			if(wp != null)
			{
				wp.setSelected(true);
			}
			
			mapViewController.repaint();
		}
	}

	private void wayPointListKeyReleased(KeyEvent evt) 
	{
		LogSystem.CLog("wayPointList.keyReleased, event="+evt);
		if(evt.getKeyCode() == 127 && wayPointList.getSelectedIndex() > -1)	// 127 = DEL = ENTF
		{
			this.deleteSelcetedItemFromList();
		}
	}
	
	private void thisWindowClosing(WindowEvent evt) 
	{
		LogSystem.CLog("this.windowClosing, event="+evt);
		mkpc.app.Application.sharedApplication().getWaypointMenuItem().setSelected(false);
	}

// >> up and down sort of waypoints in the list
	private void btn_waypointUpActionPerformed(ActionEvent evt) 
	{
		LogSystem.CLog("btn_waypointUp.actionPerformed, event="+evt);
		this.moveSelectedWaypointUp();
	}
	
	private void btn_waypointDownActionPerformed(ActionEvent evt) 
	{
		LogSystem.CLog("btn_waypointDown.actionPerformed, event="+evt);
		this.moveSelectedWaypointDown();
	}
	
	public void moveSelectedWaypointUp()
	{
		int index = wayPointList.getSelectedIndex();
		if(index > -1)
		{
			MKWaypoint wp = (MKWaypoint) wayPointList.getSelectedValue();
			
			if(wp.getWaypointStyle() != WaypointStyle.kWaypointStyleStart && wp.getWaypointStyle() != WaypointStyle.kWaypointStyleEnd)
			{
				if(index > 0 && ((MKWaypoint)wayPointList.getModel().getElementAt(index-1)).getWaypointStyle() != WaypointStyle.kWaypointStyleStart)
				{
					mapViewController.moveWaypointToIndex(wp, index - 1 );
					
					wayPointList.setListData(mapViewController.getWaypointList());

					wayPointList.setSelectedIndex(index-1);

				}
			}
		}
	}
	
	public void moveSelectedWaypointDown()
	{
		int index = wayPointList.getSelectedIndex();
		if(index > -1)
		{
			MKWaypoint wp = (MKWaypoint) wayPointList.getSelectedValue();
			
			if(wp.getWaypointStyle() != WaypointStyle.kWaypointStyleStart && wp.getWaypointStyle() != WaypointStyle.kWaypointStyleEnd)
			{
				if( index < wayPointList.getModel().getSize() - 1 && 
						((MKWaypoint)wayPointList.getModel().getElementAt(index+1)).getWaypointStyle() != WaypointStyle.kWaypointStyleEnd)
				{
					mapViewController.moveWaypointToIndex(wp, index + 1 );
					
					wayPointList.setListData(mapViewController.getWaypointList());

					wayPointList.setSelectedIndex(index+1);
				}
			}
		}
	}
}
