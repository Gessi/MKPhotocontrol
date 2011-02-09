package mkpc.maps;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import mkpc.log.LogSystem;

public class GMapPanel extends JPanel implements URLImageRequestInterface
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GMapLabel imageLabel;

	public GMapPanel(Rectangle frame, String urlString) 
	{
		super();
		initGUI(frame, urlString);
	}
	
	private void initGUI(Rectangle frame, String urlString) 
	{
		try 
		{
			this.setSize(frame.width, frame.height);
			this.setLayout(null);
			this.setPreferredSize(new Dimension(frame.width, frame.height));
			this.setBounds(frame);
			this.setOpaque(false);
			
			if(urlString != null)
			{
				new URLImageRequest(this, urlString);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void setImageURL(String url)
	{
		new URLImageRequest(this, url);
	}
	
	@Override
	public void downloadDidFinished(URLImageRequest imageRequest) 
	{
		LogSystem.CLog("Image has been loaded.");
		try
		{
			Image newImage = imageRequest.getImage();
			
			if(this.imageLabel == null)
			{
				this.imageLabel = new GMapLabel(new ImageIcon(newImage));
				this.add(this.imageLabel);
				this.imageLabel.setPreferredSize(this.getPreferredSize());
				this.imageLabel.setBounds(0,0,this.getSize().width, this.getSize().height);
				this.imageLabel.setSize(this.getSize());
			}
			else
			{
				this.imageLabel.setIcon(new ImageIcon(newImage));
			}
			
			this.repaint();
			mkpc.app.Application.sharedApplication().getMainFrame().repaint();
		}
		catch(Exception ioe)
		{
			LogSystem.CLog("Error MapTile: "+ioe.getMessage());
		}
	}
}
