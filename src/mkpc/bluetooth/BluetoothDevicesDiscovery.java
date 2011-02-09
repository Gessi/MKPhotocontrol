package mkpc.bluetooth;

import mkpc.log.LogSystem;

public class BluetoothDevicesDiscovery extends Thread 
{
	public void run()
	{
		try
		{
			while(true)
			{
				LogSystem.CLog("Testnachricht");
			}
		}
		catch(Exception e)
		{
			LogSystem.CLog(e.getMessage());
		}
	}
}
