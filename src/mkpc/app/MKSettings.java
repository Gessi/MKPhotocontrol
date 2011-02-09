package mkpc.app;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import mkpc.log.LogSystem;

/**
 * 
 * @author bk
 * @version 0.0.1
 */
public class MKSettings 
{
	private static Properties settings;
	private static File settingsFile;
	
	/**
	 * This function is called on application start
	 * and loads the settings file for window positions,
	 * and log path, and so on
	 * 
	 * After loading this function is depreciated and no new
	 * file can be loaded.
	 * @param url
	 */
	public static void loadSettingsFile(String path)
	{
		if(settings != null)
		{
			LogSystem.addLog("Settings file already loaded.");
			return;
		}
		
		settings = new Properties();
		// check if exists
		settingsFile = new File(path);
		if(!settingsFile.exists())
		{
			try 
			{
				settingsFile.createNewFile();
				settings.storeToXML(new FileOutputStream(settingsFile), "Application settings!");
				settings.loadFromXML(new FileInputStream(settingsFile));
			} 
			catch (IOException e) 
			{
				LogSystem.addLog(LogSystem.getMessage("settingsError001"));
				LogSystem.CLog(e.getMessage());
			}
		}
		else
		{
			try {
				settings.loadFromXML(new FileInputStream(settingsFile));
			} catch (InvalidPropertiesFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static boolean hasValueForKey(String key)
	{
		if(!MKSettings.getValueForKey(key).equals("##error##"))
		{
			return true;
		}
		return false;
	}
	
	public static String getValueForKey(String key)
	{
		return settings.getProperty(key, "##error##");
	}
	
	public static void showListToConsole()
	{
		settings.list(System.out);
	}
	
	/**
	 * Save Value for a Setting Key
	 */
	public static void setValueForKey(String value, String key)
	{
		settings.setProperty(key, value);
	}
	
	/**
	 * Load the application settings like position, logpath, and so on
	 * and write it to the application.	
	 */
	public static void loadApplicationSettings()
	{
		mkpc.app.Application app = mkpc.app.Application.sharedApplication();
		
		Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		// main frame position
		if(MKSettings.hasValueForKey("appPositionX") && MKSettings.hasValueForKey("appPositionY"))
		{
			Rectangle frame = app.getMainFrame().getBounds();
			frame.x = Integer.parseInt(MKSettings.getValueForKey("appPositionX"));
			frame.y = Integer.parseInt(MKSettings.getValueForKey("appPositionY"));
			
			if(screen.getWidth()-app.getMainFrame().getWidth() < frame.x)
			{
				frame.x = (int) (screen.getWidth()-app.getMainFrame().getWidth());
			}

			if((screen.getHeight()-app.getMainFrame().getHeight()) < frame.y)
			{
				frame.y = 0;
			}
			app.getMainFrame().setBounds(frame);
		}
		
		// waypoint editor
		if(MKSettings.hasValueForKey("waypointEditorPositionX") && MKSettings.hasValueForKey("waypointEditorPositionY"))
		{
			if(MKSettings.hasValueForKey("waypointEditorVisible") && MKSettings.getValueForKey("waypointEditorVisible").equals("1"))
			{
				app.showWaypointEditor();
				
				Rectangle frame = app.getWaypointEditor().getBounds();
				frame.x = Integer.parseInt(MKSettings.getValueForKey("waypointEditorPositionX"));
				frame.y = Integer.parseInt(MKSettings.getValueForKey("waypointEditorPositionY"));
				
				if(screen.getWidth()-app.getMainFrame().getWidth() < frame.x)
				{
					frame.x = 0;
				}
				
				if((screen.getHeight()-app.getMainFrame().getHeight()) < frame.y)
				{
					frame.y = 0;
				}
				app.getWaypointEditor().setBounds(frame);
			}
		}
		
		// viewController3D
		if(MKSettings.hasValueForKey("viewController3DVisible") && MKSettings.getValueForKey("viewController3DVisible").equals("1"))
		{
			app.show3DViewController();
		}
	}
	
	/**
	 * Saves all application settings and properties, so the same settings
	 * can be load on app start in the next time.
	 */
	public static void saveApplicationSettings()
	{
		mkpc.app.Application app = mkpc.app.Application.sharedApplication();
		
		// main frame position
		{
			Rectangle frame = app.getMainFrame().getBounds();
			Integer x = frame.x;
			Integer y = frame.y;
			
			settings.setProperty("appPositionX", x.toString());
			settings.setProperty("appPositionY", y.toString());
		}
		
		// webcam frame position
		{
			if(app.getWebcamFrame() != null)
			{
				Rectangle frame = app.getWebcamFrame().getBounds();
				Integer x = frame.x;
				Integer y = frame.y;
				
				settings.setProperty("webcamPositionX", x.toString());
				settings.setProperty("webcamPositionY", y.toString());
			}
		}
		
		// waypoint editor frame position
		{
			if(app.getWaypointEditor() != null)
			{
				Rectangle frame = app.getWaypointEditor().getBounds();
				Integer x = frame.x;
				Integer y = frame.y;
				
				settings.setProperty("waypointEditorPositionX", x.toString());
				settings.setProperty("waypointEditorPositionY", y.toString());
				
				if(app.getWaypointEditor().isVisible())
				{
					settings.setProperty("waypointEditorVisible", "1");
				}
				else
				{
					settings.setProperty("waypointEditorVisible", "0");
				}
			}
		}
		
		// viewController3D
		{
			if(app.getViewController3D() != null && app.getViewController3D().isVisible())
			{
				settings.setProperty("viewController3DVisible", "1");
			}
			else
			{
				settings.setProperty("viewController3DVisible", "0");
			}
		}
		
		// other settings will write here
		
		
		// store to file
		try {
			settings.storeToXML(new FileOutputStream(settingsFile), "System settings");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
