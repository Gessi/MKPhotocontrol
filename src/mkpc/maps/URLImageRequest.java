package mkpc.maps;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import mkpc.log.LogSystem;

/**
 * 
 * @author Kristof Friess
 * @version 0.0.1
 * 
 * Loading images from web and add them to JLabel
 * call interface function if the download is finished
 * Loading in a thread, so the gui will not lag.
 */
public class URLImageRequest extends Thread
{
	private URLImageRequestInterface delegate;
	private String urlString;
	private Image image;
	
	public URLImageRequest(URLImageRequestInterface delegate, String url)
	{
		if(!(delegate == null || url == null || url == ""))
		{
			this.delegate = delegate;
			this.urlString = url;
			
			// start this thread
			this.start();
		}
		else
		{
			LogSystem.addLog("URLImageRequest: Cann't download requested Image from URL: " + url);
		}
		
	}
	
	public void run()
	{
		try 
		{
			URL url = new URL(this.urlString);
			this.image = ImageIO.read(url);
			this.delegate.downloadDidFinished(this);
		} 
		catch (IOException e) 
		{
			LogSystem.CLog("URLImageRequest: " + e.getMessage());
			LogSystem.addLog("URLImageRequest: Cann't download requested Image from URL: " + this.urlString);
		}
	}
	
	public Image getImage()
	{
		if(this.image == null)
		{
			LogSystem.addLog("URLImageRequest problem to load Image cause image is null!");
		}
		return this.image;
	}
}
