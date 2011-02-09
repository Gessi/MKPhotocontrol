package mkpc.app;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;

import mkpc.log.LogSystem;

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
public class MKSettingsWindow extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lbl_title;
	private JButton btn_abort;
	private JButton btn_save;
	private JTextField jTextField2;
	private JTextField jTextField1;
	private JTextField txt_comPort;
	private JComboBox cb_language;
	private JLabel lbl_comport;
	private JLabel lbl_language;

	/**
	* Auto-generated main method to display this JFrame
	*/
	
	public MKSettingsWindow() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle(LogSystem.getMessage("settingsLabelTitle"));
			{
				lbl_title = new JLabel();
				getContentPane().add(lbl_title);
				lbl_title.setBounds(7, 0, 215, 34);
				lbl_title.setName("lbl_title");
				lbl_title.setText(LogSystem.getMessage("settingsLabelTitle"));
			}
			{
				lbl_language = new JLabel();
				getContentPane().add(lbl_language);
				lbl_language.setBounds(12, 40, 142, 16);
				lbl_language.setName("lbl_language");
				lbl_language.setText(LogSystem.getMessage("settingsLabelLanguage"));
			}
			{
				lbl_comport = new JLabel();
				getContentPane().add(lbl_comport);
				lbl_comport.setName("lbl_comport");
				lbl_comport.setBounds(12, 68, 138, 16);
				lbl_comport.setText(LogSystem.getMessage("settingsLabelComPort"));
			}
			{
				ComboBoxModel cb_languageModel = 
					new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });
				cb_language = new JComboBox();
				getContentPane().add(cb_language);
				cb_language.setModel(cb_languageModel);
				cb_language.setBounds(166, 36, 170, 23);
			}
			{
				txt_comPort = new JTextField();
				getContentPane().add(txt_comPort);
				txt_comPort.setBounds(168, 64, 168, 23);
				txt_comPort.setName("txt_comPort");
			}
			{
				jTextField1 = new JTextField();
				getContentPane().add(jTextField1);
				jTextField1.setName("jTextField1");
				jTextField1.setBounds(168, 93, 168, 23);
			}
			{
				jTextField2 = new JTextField();
				getContentPane().add(jTextField2);
				jTextField2.setName("jTextField2");
				jTextField2.setBounds(168, 122, 168, 23);
			}
			{
				btn_save = new JButton();
				getContentPane().add(btn_save);
				btn_save.setBounds(12, 165, 77, 23);
				btn_save.setName("btn_save");
				btn_save.setText(LogSystem.getMessage("settingsButtonSave"));
				btn_save.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btn_saveActionPerformed(evt);
					}
				});
			}
			{
				btn_abort = new JButton();
				getContentPane().add(btn_abort);
				btn_abort.setName("btn_abort");
				btn_abort.setBounds(100, 165, 77, 23);
				btn_abort.setText(LogSystem.getMessage("settingsButtonAbort"));
			}
			pack();
			this.setSize(600, 237);
			this.loadSettings();
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(getContentPane());
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void loadSettings()
	{
		if(MKSettings.hasValueForKey("comPort"))
		{
			txt_comPort.setText(MKSettings.getValueForKey("comPort"));
		}
		else
		{
			txt_comPort.setText("COM1");
		}
	}
	
	private void btn_saveActionPerformed(ActionEvent evt) 
	{
		LogSystem.CLog("btn_save.actionPerformed, event="+evt);
		MKSettings.setValueForKey(txt_comPort.getText(), "comPort");
	}

}
