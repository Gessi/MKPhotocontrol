package mkpc.fligthcontrol;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import mkpc.log.LogSystem;

public class MKSerialCommunication implements Runnable {
	static Enumeration<?> enumComm;
	static CommPortIdentifier serialPortId;
	static String messageString = "Hello, world!\r";
	static SerialPort serialPort;
	static OutputStream outputStream;
	InputStream inputStream;
	Boolean serialPortGeoeffnet = false;
	
	Character CRC1 = '0';
	Character CRC2 = '0';

	int baudrate = 57600;
	int dataBits = SerialPort.DATABITS_8;
	int stopBits = SerialPort.STOPBITS_1;
	int parity = SerialPort.PARITY_NONE;
	String portName = "COM13";

	String buffer = "";
	String packageInput = "";
	
	public void run() 
	{
		if (oeffneSerialPort(portName) != true)
			return;
		while(true)
		{
			try 
			{
	        	createCRC("#av");
	        	messageString = "#av" + CRC1.toString() + CRC2.toString() + '\r';
	        	LogSystem.CLog("Send: " + messageString);
	            outputStream.write(messageString.getBytes());
	            Thread.sleep(100);
	        } catch (IOException e) {
	        	LogSystem.CLog(e.getMessage());
	        } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//this.schliesseSerialPort();

	}

	boolean oeffneSerialPort(String portName) {
		Boolean foundPort = false;
		if (serialPortGeoeffnet != false) {
			LogSystem.CLog("Serialport bereits geöffnet");
			return false;
		}
		LogSystem.CLog("Öffne Serialport");
		enumComm = CommPortIdentifier.getPortIdentifiers();
		while (enumComm.hasMoreElements()) {
			serialPortId = (CommPortIdentifier) enumComm.nextElement();
			LogSystem.CLog("serial Portname: " + serialPortId.getName());
			if (portName.contentEquals(serialPortId.getName())) {
				foundPort = true;
				break;
			}
		}
		if (foundPort != true) {
			LogSystem.CLog("Serialport nicht gefunden: " + portName);
			return false;
		}

		try {
			serialPort = (SerialPort) serialPortId.open("Öffnen und Senden",
					500);
		} catch (PortInUseException e) {
			LogSystem.CLog("Port belegt");
		}
		
		try {
			outputStream = serialPort.getOutputStream();
		} catch (IOException e) {
			LogSystem.CLog("Keinen Zugriff auf OutputStream");
		}

		try {
			inputStream = serialPort.getInputStream();
		} catch (IOException e) {
			LogSystem.CLog("Keinen Zugriff auf InputStream");
		}

		try {
			serialPort.addEventListener(new serialPortEventListener());
		} catch (TooManyListenersException e) {
			LogSystem.CLog("TooManyListenersException für Serialport");
		}
		serialPort.notifyOnDataAvailable(true);
		try {
			serialPort
					.setSerialPortParams(baudrate, dataBits, stopBits, parity);
		} catch (UnsupportedCommOperationException e) {
			LogSystem.CLog("Konnte Schnittstellen-Paramter nicht setzen");
		}
		
		
		serialPortGeoeffnet = true;

		return true;
	}

	public void schliesseSerialPort() {
		if (serialPortGeoeffnet == true) {
			LogSystem.CLog("Schließe Serialport");
			serialPort.close();
			serialPortGeoeffnet = false;
		} else {
			LogSystem.CLog("Serialport bereits geschlossen");
		}
	}

	public void serialPortDatenVerfuegbar() {
		try {
			byte[] data = new byte[150];
			int num;
			while (inputStream.available() > 0) {
				num = inputStream.read(data, 0, data.length);
				LogSystem.CLog(new String(data, 0, num));

				dispatchInputString(new String(data, 0, num));
			}
		} catch (IOException e) {
			LogSystem.CLog("Fehler beim Lesen empfangener Daten");
		}
	}

	void dispatchInputString(String str) {
		if (str.charAt(0) == '#') {
			packageInput = str;
		} else {
			packageInput = packageInput + str;
		}

		if (packageInput.length() == 18) {
			buffer = buffer + str.substring(3, str.length() - 4);
			LogSystem.CLog("Buffer " + buffer);
		}

		if (buffer.length() >= 84) {
			char[] result = decode64(buffer);
			LogSystem.CLog(" Length: " + concat2Bytes(result).length
					+ "Decode64: " + Arrays.toString(concat2Bytes(result)));
			buffer = "";
		}
	}

	class serialPortEventListener implements SerialPortEventListener {
		public void serialEvent(SerialPortEvent event) {
			// LogSystem.CLog("serialPortEventlistener");
			switch (event.getEventType()) {
			case SerialPortEvent.DATA_AVAILABLE:
				serialPortDatenVerfuegbar();
				break;
			case SerialPortEvent.BI:
			case SerialPortEvent.CD:
			case SerialPortEvent.CTS:
			case SerialPortEvent.DSR:
			case SerialPortEvent.FE:
			case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			case SerialPortEvent.PE:
			case SerialPortEvent.RI:
			default:
			}
		}
	}

	public static short[] concat2Bytes(char[] decoded64) {
		final short[] result = new short[decoded64.length / 2];
		for (int i = 2; i + 1 < decoded64.length; i += 2) {
			/*
			 * bit-shift every second byte 8 bits to left and "or" it with first
			 * byte cast to short so it's interpreted to 16-bit signed
			 */
			result[(i - 2) / 2] = ((short) (decoded64[i + 1] << 8 | decoded64[i]));
		}
		return result;
	}

	public static char[] decode64(String in) {
		return decode64(in.toCharArray());
	}

	public static char[] decode64(char[] in_arr) {
		// substract '='
		for (int i = 0; i < in_arr.length; i++)
			in_arr[i] -= '=';
		// calculate length of out_arr, for every 4 bytes we get 3 bytes
		int len = in_arr.length * 3 / 4;
		char[] out_arr = new char[len];
		// counting the blocks (4 bytes for in_arr, 3 bytes for out_arr)
		for (int i = 0; (i + 1) * 4 < in_arr.length; i++) {
			// placeholder for four bytes of in_arr
			int fourbytes = 0;
			for (int j = 0; j < 4; j++) {
				/*
				 * "or" 4 bytes togehter (i*4): 1. Byte of the block in in_arr
				 * 3-j: position in a block from right to left <<(j*6):
				 * bit-shift left 6 bits "j"-times it's then
				 * 012345|012345|012345|012345
				 */
				fourbytes |= (in_arr[(i * 4) + 3 - j]) << (j * 6);
			}
			for (int j = 0; j < 3; j++) {
				/*
				 * seperated new to 01234501|23450123|45012345 (i*3): 1. Byte of
				 * a block in out_arr 2-j: 3-j: position in a block from right
				 * to left >>(j*8): bit-shift right 8 bits "j"-times &0x00ff:
				 * use bit-mask, so that only rightest 8 bits are taken
				 */
				out_arr[(i * 3) + 2 - j] = (char) ((fourbytes >> (j * 8)) & 0x00ff);
			}
		}
		return out_arr;
	}

	public void createCRC(String dataBuffer) {
		long tmpCRC = 0;
		for (int i = 0; i < dataBuffer.length(); i++) {
			tmpCRC += (long) dataBuffer.charAt(i);
		}
		tmpCRC %= 4096;
		CRC1 = (char) ((long) '=' + tmpCRC / 64);
		CRC2 = (char) ((long) '=' + tmpCRC % 64);
	}
}
