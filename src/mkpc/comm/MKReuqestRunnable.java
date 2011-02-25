package mkpc.comm;

/**
 * This class represent a Runnable to send automatic requests to the
 * copter, so not the UI will do this, the u will just wait for answer.
 * 
 * The requester is reading the open window flag, to get the information,
 * which request must send.
 * 
 * Currently we have:
 * <ul>
 * <li>Data3D for 3D-Model</li>
 * <li>GPS Position</li>
 * <li>??</li>
 * </ul>
 * @author Kristof
 *
 */
public class MKReuqestRunnable implements Runnable 
{

	@Override
	public void run() 
	{
		// TODO write down the flags here
		//> Flags
		boolean isData3D = false;
		boolean isGPSRequest = false;
		
		// TODO Set Flags for the Requests (check available features)
		
		
		// TODO Create the Requests and send
	}

}
