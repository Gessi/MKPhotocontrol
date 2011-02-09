package mkpc.maps;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import mkpc.log.LogSystem;
/**
 * 
 * @author bk
 *
 * This class represent a ActivityIndicator, so it show an image animation,
 * for the time where some activities are active. 
 * User can start and stop this activity indicator.
 */
public class ActivityIndicator extends JLabel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image imageArray[];
	private Boolean isAnimation;
	private AnimationThread animation;
	
	public ActivityIndicator(Point position) 
	{
		super();
		initGUI(position);
	}
	
	private void initGUI(Point position) 
	{
		try 
		{
			this.setBounds(position.x, position.y, 25, 25);
			this.setOpaque(false);
			imageArray = new Image[21];
			for(int i = 1; i < 10; i++)
			{
				imageArray[i] = ImageIO.read(new File("images/indicator/torte2_s0" + i + ".png"));
				
			}
			
			for(int i = 10; i < 21; i++)
			{
				imageArray[i] = ImageIO.read(new File("images/indicator/torte2_s" + i + ".png"));
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g, Image img)
	{
		if(img != null)
		{
			g.clearRect(0, 0, 25, 25);
			g.setColor(new Color(0, 0, 0, 0));
			g.drawImage(img, 0, 0, 25, 25, this);
		}
	}
	
	public Boolean isAnimation()
	{
		return this.isAnimation;
	}
	
	public void startAnimation()
	{
		this.isAnimation = true;
		this.animation = new AnimationThread(this, 70, this.imageArray);
		this.animation.start();
	}
	
	public void stopAnimation()
	{
		this.animation.stopAnimation(true);
		this.isAnimation = false;
	}
	
	/**
	 * Control the animation for the activityIndicator.
	 */
	private class AnimationThread extends Thread
	{
		private ActivityIndicator indicator;
		private Integer animationTime;
		private Image imageArray[];
		private Boolean stopAnimation;
		private Integer index;
		
		public AnimationThread(ActivityIndicator indicator, Integer animationTime, Image imageArray[])
		{
			this.indicator = indicator;
			this.animationTime = animationTime;
			this.imageArray = imageArray;
			this.stopAnimation = false;
			this.index = 0;
		}
		
		public void run()
		{
			while(!stopAnimation)
			{
				index++;
				if(index >= imageArray.length)
				{
					index = 0;
				}
				
				try 
				{
					indicator.paint(indicator.getGraphics(), imageArray[index]);
					Thread.sleep(animationTime);
				} 
				catch (InterruptedException e) 
				{
					LogSystem.CLog(e.getMessage());
				}
			}
		}
		
		public void stopAnimation(Boolean stopAnimation)
		{
			this.stopAnimation = stopAnimation;
		}
	}
}