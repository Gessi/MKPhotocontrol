package mkpc.comm;

public interface MKCommunicationDelegate 
{
	public void communicationDidOpenCOMPort();
	public void communicationDidFailOpenCOMPort(String error);
	public void communicationDidOpenCopterConnection();
	public void communicationDidFailOpenCopterConnection(String error);
}
