package mkpc.fligthcontrol;
import java.awt.GridLayout;
import javax.swing.JSlider;

import javax.swing.WindowConstants;
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
public class MKParamSettingsWindow extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MKJSlider jsld_heightMinGas;
	private MKJSlider jsld_maxHeight;
	
	/**
	* Auto-generated main method to display this JFrame
	*/
	
	public MKParamSettingsWindow() {
		super();
		initGUI();
		
	}
	
	private void initGUI() {
		try {
			GridLayout thisLayout = new GridLayout(1, 1);
			thisLayout.setHgap(0);
			thisLayout.setVgap(0);
			thisLayout.setColumns(34);
			thisLayout.setRows(1);
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jsld_heightMinGas = new MKJSlider();
				this.add(jsld_heightMinGas);
			}
			{
				jsld_maxHeight = new MKJSlider();
				this.add(jsld_maxHeight);
				jsld_maxHeight.setMaxValue(255);
				jsld_maxHeight.setMinValue(0);
				jsld_maxHeight.setCurrentValue(50);
			}
			pack();
			this.setSize(600, 200);
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(getContentPane());
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
