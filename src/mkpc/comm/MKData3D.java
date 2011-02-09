package mkpc.comm;

public class MKData3D 
{
	private short[]  angel = new short[3]; // nick, roll, compass in 0,1°
	private char[] centroid = new char[3];
	private char[] reserve = new char[5];
	
	public void setNick(short value)
	{
		angel[0] = value;
	}
	
	public short nick()
	{
		return angel[0];
	}
	
	public void setRoll(short value)
	{
		angel[1] = value;
	}
	
	public short roll()
	{
		return angel[1];
	}
	
	public void setCompass(short value)
	{
		angel[2] = value;
	}
	
	public short compass()
	{
		return angel[2];
	}

	public void reserve(char[] reserve) 
	{
		this.reserve = reserve;
	}

	public char[] getReserve() 
	{
		return reserve;
	}

	public void centroid(char[] centroid) 
	{
		this.centroid = centroid;
	}

	public char[] getCentroid() 
	{
		return centroid;
	}
}
