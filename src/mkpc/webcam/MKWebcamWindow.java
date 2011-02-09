package mkpc.webcam;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.format.VideoFormat;
import javax.swing.JComboBox;
import javax.swing.WindowConstants;

import mkpc.app.MKSettings;
import mkpc.log.LogSystem;


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
public class MKWebcamWindow extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WebcamView webcamView;
	private JComboBox deviceBox;
	
	public MKWebcamWindow() 
	{
		super();
		initGUI();
	}
	
	private void initGUI() 
	{
		try 
		{
			this.setLayout(new BorderLayout());
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent evt) {
					thisWindowClosing(evt);
				}
			});
			this.addHierarchyBoundsListener(new HierarchyBoundsAdapter() {
				public void ancestorResized(HierarchyEvent evt) {
					System.out.println("this.ancestorResized, event="+evt);
					//TODO add your code for this.ancestorResized
				}
			});
			
			{
				deviceBox = new JComboBox(CaptureDeviceManager.getDeviceList(new VideoFormat(VideoFormat.RGB)));
				this.add(deviceBox, BorderLayout.NORTH);
				deviceBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						deviceBoxActionPerformed(evt);
					}
				});
			}
			
			// webcam frame
			if(MKSettings.hasValueForKey("webcamPositionX") && MKSettings.hasValueForKey("webcamPositionY"))
			{
				Rectangle frame = this.getBounds();
				frame.x = Integer.parseInt(MKSettings.getValueForKey("webcamPositionX"));
				frame.y = Integer.parseInt(MKSettings.getValueForKey("webcamPositionY"));
				this.setBounds(frame);
			}
			
			pack();
			setSize(400, 300);
			
		} 
		catch (Exception e) 
		{
			LogSystem.addLog(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Stop the playing webcam, give the device free.
	 * @return 
	 */
	public int close()
	{
		webcamView.close();
		return 1;
	}
	
	private void thisWindowClosing(WindowEvent evt) 
	{
		LogSystem.CLog("this.windowClosing, event="+evt);
		if(webcamView != null)
			webcamView.close();
		
		// access to webcam menu item to deselect
		mkpc.app.Application.sharedApplication().getWebcamMenuItem().setSelected(false);
	}
	
	private void deviceBoxActionPerformed(ActionEvent evt) 
	{
		LogSystem.CLog("deviceBox.actionPerformed, event="+evt);
		if(webcamView != null)
		{
			webcamView.close();
			webcamView = null;
		}
		
		CaptureDeviceInfo device = (CaptureDeviceInfo)deviceBox.getSelectedItem();
		webcamView = new WebcamView(device.getName());
    	webcamView.setBounds(0, 0, 400, 300);
    	this.add(webcamView, BorderLayout.CENTER);
    	this.validate();
	}
}
