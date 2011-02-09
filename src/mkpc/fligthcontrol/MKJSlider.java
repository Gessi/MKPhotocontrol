package mkpc.fligthcontrol;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
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
public class MKJSlider extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbl_currentValue;
	private JLabel lbl_minValue;
	private JLabel lbl_maxValue;
	
	private Integer currentValue;
	private Integer minValue;
	private Integer maxValue;
	
	private JSlider slider;
	
	public MKJSlider()
	{
		super();
		
		this.currentValue = 50;
		this.maxValue = 100;
		this.minValue = 0;
		
		initGUI();
	}
	
	public MKJSlider(Integer max, Integer min, Integer current) 
	{
		super();
		
		this.currentValue = current;
		this.maxValue = max;
		this.minValue = min;
		
		initGUI();
	}
	
	private void initGUI() 
	{
		try 
		{
			this.setLayout(new BorderLayout());
			{
				JPanel topSplit = new JPanel(new BorderLayout());
				this.add(topSplit, BorderLayout.NORTH);
				{
					this.lbl_currentValue = new JLabel(this.currentValue.toString());
					topSplit.add(this.lbl_currentValue, BorderLayout.NORTH);
					this.lbl_currentValue.setHorizontalAlignment(JLabel.CENTER);
					lbl_currentValue.setName("lbl_currentValue");
				}
				{
					this.lbl_maxValue = new JLabel(this.maxValue.toString());
					topSplit.add(this.lbl_maxValue, BorderLayout.SOUTH);
					this.lbl_maxValue.setHorizontalAlignment(JLabel.CENTER);
				}
			}
			
			{
				
				this.slider = new JSlider(JSlider.VERTICAL);
				this.slider.setMaximum(this.maxValue);
				this.slider.setMinimum(this.minValue);
				this.slider.setValue(this.currentValue);
				this.add(this.slider, BorderLayout.CENTER);
				slider.setName("slider");
			}
			
			{
				this.lbl_minValue = new JLabel(this.minValue.toString());
				this.add(this.lbl_minValue, BorderLayout.SOUTH);
				this.lbl_minValue.setHorizontalAlignment(JLabel.CENTER);
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} 
		catch (Exception e) 
		{
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	public Integer getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Integer currentValue) {
		this.currentValue = currentValue;
		this.slider.setValue(this.currentValue);
		this.lbl_currentValue.setText(this.currentValue.toString());
	}

	public Integer getMinValue() {
		return minValue;
	}

	public void setMinValue(Integer minValue) {
		this.minValue = minValue;
		this.slider.setMinimum(this.currentValue);
		this.lbl_minValue.setText(this.minValue.toString());
	}

	public Integer getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
		this.slider.setMaximum(this.currentValue);
		this.lbl_maxValue.setText(this.maxValue.toString());
	}
}
