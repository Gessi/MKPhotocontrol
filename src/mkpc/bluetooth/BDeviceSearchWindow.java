package mkpc.bluetooth;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.bluetooth.BluetoothStateException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import javax.swing.WindowConstants;

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
public class BDeviceSearchWindow extends javax.swing.JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList deviceList;
	private JButton takeDevice;
	private JButton abort;
	private JButton searchDevice;
	private JScrollPane scrollPane;
	private JFrame delegate;

	public BDeviceSearchWindow() 
	{
		super();
		initGUI();
		try {
			MKBluetoothDeviceDiscovery keks2 = new MKBluetoothDeviceDiscovery(LogSystem.getTextPane());
			keks2.toString();
		} catch (BluetoothStateException e) {
			// TODO Auto-generated catch block
			LogSystem.addLog("No bluetooht module founded!");
			LogSystem.CLog(e.getMessage());
		}
		//keks.start();
	}
	
	private void initGUI() 
	{
		try 
		{
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(new BorderLayout());
			{
				scrollPane = new JScrollPane();
				getContentPane().add(scrollPane, BorderLayout.CENTER);
				{
					ListModel deviceListModel = 
						new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
					deviceList = new JList();
					scrollPane.setViewportView(deviceList);
					deviceList.setModel(deviceListModel);
					deviceList.setPreferredSize(new java.awt.Dimension(281, 272));
				}
			}
			{
				JPanel buttonPanel = new JPanel(new BorderLayout());
				getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				{
					takeDevice = new JButton();
					buttonPanel.add(takeDevice, BorderLayout.EAST);
					takeDevice.setText("Benutzen");
					takeDevice.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							takeDeviceActionPerformed(evt);
						}
					});
				}
				{
					abort = new JButton();
					buttonPanel.add(abort, BorderLayout.WEST);
					abort.setText("Abbruch");
					abort.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							abortActionPerformed(evt);
						}
					});
				}
				{
					searchDevice = new JButton();
					buttonPanel.add(searchDevice, BorderLayout.CENTER);
					searchDevice.setText("Gerät suchen ...");
					searchDevice.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							searchDeviceActionPerformed(evt);
						}
					});
				}
			}
			
			
			pack();
			this.setSize(300, 400);
		} 
		catch (Exception e) 
		{
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void abortActionPerformed(ActionEvent evt) 
	{
		System.out.println("abort.actionPerformed, event="+evt);
		
		delegate.setEnabled(true);
		this.setVisible(false);
	}
	
	private void takeDeviceActionPerformed(ActionEvent evt) 
	{
		System.out.println("takeDevice.actionPerformed, event="+evt);
		
		delegate.setEnabled(true);
		this.setVisible(false);
	}
	
	private void searchDeviceActionPerformed(ActionEvent evt) 
	{
		System.out.println("takeDevice.actionPerformed, event="+evt);
	}

	public void setDelegate(JFrame delegate) 
	{
		this.delegate = delegate;
	}

	public JFrame getDelegate() {
		return delegate;
	}

}

