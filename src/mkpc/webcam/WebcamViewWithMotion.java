package mkpc.webcam;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;

import javax.annotation.processing.Processor;
import javax.media.CannotRealizeException;
import javax.media.CaptureDeviceManager;
import javax.media.DataSink;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.bean.playerbean.MediaPlayer;
import javax.media.format.VideoFormat;
import javax.swing.JPanel;

import mkpc.log.LogSystem;

public class WebcamViewWithMotion extends JPanel {

	/**
	 * This class includes the webcam which u choose!<br />
	 * Standard URL for one webcam/ videostream is "vfw://"
	 */
	private static final long serialVersionUID = 1L;
	private Player mediaPlayer = null;
	private Processor processor = null;
	DataSink fileW = null;
    Object waitSync = new Object();
    boolean stateTransitionOK = true;

	public WebcamViewWithMotion(String camURL) {
		super();
		this.setLayout(new BorderLayout());

		Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, true);
		try {
			LogSystem.CLog("Devices: "
					+ CaptureDeviceManager.getDeviceList(new VideoFormat(null))
							.size());

			MediaLocator ml = new MediaLocator(camURL); // "vfw://" standard URL
			mediaPlayer = Manager.createRealizedPlayer(ml);
			Component video = mediaPlayer.getVisualComponent();

			if (video != null) {
				this.add(video, BorderLayout.CENTER);
			}

			mediaPlayer.start();
		} catch (NoPlayerException noPlayerException) {
			LogSystem.addLog("No webcam foundet!");
		} catch (CannotRealizeException cre) {
			LogSystem.addLog("Could not realize media player.");
		} catch (IOException ie) {
			LogSystem.addLog("Error reading from the source.");
		}
	}
	
	public void open()
	{
		
	}

	public void close() {
		if (this.mediaPlayer != null
				&& this.mediaPlayer.getState() == MediaPlayer.Started) {
			this.mediaPlayer.close();
		}
	}
}
