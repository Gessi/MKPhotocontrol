package mkpc.log;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.swing.JComponent;
import javax.swing.JTextPane;

// This class is used to log date into the textPane
public class LogSystem 
{
	static private JTextPane textPane;
	static private String logPath;
	static private boolean debugModus = true;
	static private Properties properties = null;
	public static void addLog(String text)
	{
		if(textPane != null)
		{
			Date dt = new Date();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss: ");
			String date = dateFormatter.format(dt);
			textPane.setText(textPane.getText() + date + text + "\n");
			scrollToBottom(textPane);
		}
		else
		{
			if(debugModus)
				System.out.println("No TextPane is set jet, so it will not log in a window screen!");
		}
		writeToLogFile(text);
	}
	
	public static void CLog(String text)
	{
		if(LogSystem.debugModus)
		{
			System.out.println(Thread.currentThread().getStackTrace()[2].getClassName() + "-" + Thread.currentThread().getStackTrace()[2].getMethodName() + ": " + text);
			//LogSystem.addLog(text);
		}
	}
	
	public static void scrollToBottom(JComponent component) {
        Rectangle visibleRect = component.getVisibleRect();
        visibleRect.y = component.getHeight() - visibleRect.height;
        component.scrollRectToVisible(visibleRect);
    }


	public static void writeToLogFile(String text)
	{
		FileWriter out=null;
		Date dt = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String filename = "log-" + dateFormatter.format(dt) + ".txt";
		File logDir = new File(LogSystem.logPath);
		if(!logDir.exists())
		{
			logDir.mkdirs();
		}
		String filepath = LogSystem.logPath + filename;
		dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss (z): ");
		text = dateFormatter.format(dt) + text + "\n";
		try
		{
			out=new FileWriter(filepath, true);
			out.write(text);
		}
		catch(IOException e)
		{
			if(LogSystem.textPane != null)
			{
				addLog("Dateischreibfehler: "+e.getMessage());
			}

			if(debugModus)
				System.out.println("Can not log to file: " + filepath);
		}
		finally
		{
			try
			{
				out.close();
			}
			catch(Exception e)
			{
				/* ignor */
			}
		}
	}
	
	// getter and setter
	public static void setLogPath(String newLogPath) 
	{
		if(newLogPath.charAt(newLogPath.length()-1) != '/')
		{
			newLogPath = newLogPath + "/";
		}
		logPath = newLogPath;
	}

	public static String getLogPath() 
	{
		return logPath;
	}
	
	public static void setTextPane(JTextPane newTextPane) 
	{
		textPane = newTextPane;
	}

	public static JTextPane getTextPane() 
	{
		return textPane;
	}
	
	public static String getMessage(String key)
	{
		if(properties == null)
		{
			properties = new Properties();
			try {
				properties.loadFromXML(new FileInputStream("language/ger.xml"));
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
		String text = properties.getProperty(key);
		return text != null?text:key;
	}
}
