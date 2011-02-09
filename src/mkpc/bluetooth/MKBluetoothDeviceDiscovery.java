package mkpc.bluetooth;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.swing.JTextPane;

import mkpc.log.LogSystem;

public class MKBluetoothDeviceDiscovery implements DiscoveryListener
{
	private JTextPane txtp_copterLog;
	private LocalDevice localDevice;
    private DiscoveryAgent agent;
    
	public MKBluetoothDeviceDiscovery(JTextPane txtp_copterLog ) throws BluetoothStateException
	{
		this.txtp_copterLog = txtp_copterLog;
		localDevice = LocalDevice.getLocalDevice();
        agent = localDevice.getDiscoveryAgent();
        LogSystem.addLog("Start Discovery");
	}

	
	// getter and setter for private variables
	public void setTxtp_copterLog(JTextPane txtp_copterLog) 
	{
		this.txtp_copterLog = txtp_copterLog;
	}

	public JTextPane getTxtp_copterLog() 
	{
		return this.txtp_copterLog;
	}

	public void inquiry() throws BluetoothStateException 
	{
		LogSystem.CLog("inquiry");
        agent.startInquiry(DiscoveryAgent.GIAC, this);
    }
	
	@Override
	public void deviceDiscovered(RemoteDevice device, DeviceClass devClass) 
	{
        try 
        {
        	LogSystem.addLog("Device discovered: " + device.getFriendlyName(false));
        } catch (IOException ex) {
        	LogSystem.addLog("FEHLER ");
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }


	@Override
	public void inquiryCompleted(int arg0) 
	{
		LogSystem.addLog("Inquiry completed\n");
	}


	@Override
	public void serviceSearchCompleted(int arg0, int arg1) 
	{
		LogSystem.addLog("Service search completed\n");
	}


	@Override
	public void servicesDiscovered(int arg0, ServiceRecord[] arg1) 
	{
		LogSystem.addLog("Service discovered\n");
	}
}
