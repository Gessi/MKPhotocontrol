package mkpc.comm;

import mkpc.app.Application;

/**
 * This class take all settings of the and will save it, it will be use for sending commands
 * and to set some GUI elements with the right settings.
 * This class will only initial at one time, also global share.
 * @author bk
 *
 */
public class MKParameter
{
	public static MKParameter shardParameter = null;
	private char channelConfiguration[] = new char[8];       // Kanalbelegung GAS[0], GIER[1], NICK[2], ROLL[3], POTI1, POTI2, POTI3, POTI4
	private char globalConfiguration;           // Höhenregler aktiv, HR-Schalter, Heading Hold aktiv, Kompass aktiv, Kompass Ausrichtung fest, GPS aktiv,
	private char heightMinGas;           // Wert : 0-100
	private char airPressureD;            // Wert : 0-250
	private char maxHeight;               // Wert : 0-32
	private char heightP;                // Wert : 0-32
	private char heightBoost;     // Wert : 0-50
	private char heightACCEffect;      // Wert : 0-250
	private char stickP;                // Wert : 1-6
	private char stickD;                // Wert : 0-64
	private char yawP;                 // Wert : 1-20
	private char gasMin;                // Wert : 0-32
	private char gasMax;                // Wert : 33-250
	private char gyroAccFactor;          // Wert : 1-64
	private char compassImpression;         // Wert : 0-32
	private char gyroP;                 // Wert : 10-250
	private char gyroI;                 // Wert : 0-250
	private char undervoltageWarning;  // Wert : 0-250
	private char emergencyGas;                 // Wert : 0-250     // Gaswert bei Empängsverlust
	private char emergencyGasTime;             // Wert : 0-250     // Zeit bis auf NotGas geschaltet wird, wg. Rx-Problemen
	private char ufoOrientation;         // X oder + Formation
	private char iFactor;               // Wert : 0-250
	private char userParam1;             // Wert : 0-250
	private char userParam2;             // Wert : 0-250
	private char userParam3;             // Wert : 0-250   Default: LED
	private char userParam4;             // Wert : 0-250   Default: LED
	private char pitchServoControl;       // Wert : 0-250
	private char pitchServo;          // Wert : 0-250
	private char pitchServoMin;           // Wert : 0-250
	private char pitchServoMax;           // Wert : 0-250
	private char pitchServoRefresh;       //
	private char pitchServoCompInvert;    // Wert : 0-250   0 oder 1  // WICHTIG!!! am Ende lassen
	private char reserved[] = new char[7];
	private char name[] = new char[12];
	
	// current new Data
	private char[] serialPoti = new char[12];	// 0 = camera, 1 = camera nick movement, 3 - 12 nothing
	
	private MKData3D data3D = null;
	
	public MKParameter()
	{
		setData3D(new MKData3D());
		
		for(int i = 0; i < this.serialPoti.length; i++)
		{
			serialPoti[i] = 0;
		}
	}
	
	public static MKParameter shardParameter()
	{
		if(shardParameter == null)
		{
			shardParameter = new MKParameter();
		}
		return shardParameter;
	}
	
	public void setChannelConfiguration(char channelConfiguration[]) {
		this.channelConfiguration = channelConfiguration;
	}
	public char[] getChannelConfiguration() {
		return channelConfiguration;
	}
	public void setGlobalConfiguration(char globalConfiguration) {
		this.globalConfiguration = globalConfiguration;
	}
	public char getGlobalConfiguration() {
		return globalConfiguration;
	}
	public void setYawP(char yawP) {
		this.yawP = yawP;
	}
	public char getYawP() {
		return yawP;
	}
	
	public char getHeightMinGas() {
		return heightMinGas;
	}
	public void setHeightMinGas(char heightMinGas) {
		this.heightMinGas = heightMinGas;
	}
	public char getAirPressureD() {
		return airPressureD;
	}
	public void setAirPressureD(char airPressureD) {
		this.airPressureD = airPressureD;
	}
	public char getMaxHeight() {
		return maxHeight;
	}
	public void setMaxHeight(char maxHeight) {
		this.maxHeight = maxHeight;
	}
	public char getHeightP() {
		return heightP;
	}
	public void setHeightP(char heightP) {
		this.heightP = heightP;
	}
	public char getHeightBoost() {
		return heightBoost;
	}
	public void setHeightBoost(char heightBoost) {
		this.heightBoost = heightBoost;
	}
	public char getHeightACCEffect() {
		return heightACCEffect;
	}
	public void setHeightACCEffect(char heightACCEffect) {
		this.heightACCEffect = heightACCEffect;
	}
	public char getStickP() {
		return stickP;
	}
	public void setStickP(char stickP) {
		this.stickP = stickP;
	}
	public char getStickD() {
		return stickD;
	}
	public void setStickD(char stickD) {
		this.stickD = stickD;
	}
	public char getGasMin() {
		return gasMin;
	}
	public void setGasMin(char gasMin) {
		this.gasMin = gasMin;
	}
	public char getGasMax() {
		return gasMax;
	}
	public void setGasMax(char gasMax) {
		this.gasMax = gasMax;
	}
	public char getGyroAccFactor() {
		return gyroAccFactor;
	}
	public void setGyroAccFactor(char gyroAccFactor) {
		this.gyroAccFactor = gyroAccFactor;
	}
	public char getCompassImpression() {
		return compassImpression;
	}
	public void setCompassImpression(char compassImpression) {
		this.compassImpression = compassImpression;
	}
	public char getGyroP() {
		return gyroP;
	}
	public void setGyroP(char gyroP) {
		this.gyroP = gyroP;
	}
	public char getGyroI() {
		return gyroI;
	}
	public void setGyroI(char gyroI) {
		this.gyroI = gyroI;
	}
	public char getUndervoltageWarning() {
		return undervoltageWarning;
	}
	public void setUndervoltageWarning(char undervoltageWarning) {
		this.undervoltageWarning = undervoltageWarning;
	}
	public char getEmergencyGas() {
		return emergencyGas;
	}
	public void setEmergencyGas(char emergencyGas) {
		this.emergencyGas = emergencyGas;
	}
	public char getEmergencyGasTime() {
		return emergencyGasTime;
	}
	public void setEmergencyGasTime(char emergencyGasTime) {
		this.emergencyGasTime = emergencyGasTime;
	}
	public char getUfoOrientation() {
		return ufoOrientation;
	}
	public void setUfoOrientation(char ufoOrientation) {
		this.ufoOrientation = ufoOrientation;
	}
	public char getiFactor() {
		return iFactor;
	}
	public void setiFactor(char iFactor) {
		this.iFactor = iFactor;
	}
	public char getUserParam1() {
		return userParam1;
	}
	public void setUserParam1(char userParam1) {
		this.userParam1 = userParam1;
	}
	public char getUserParam2() {
		return userParam2;
	}
	public void setUserParam2(char userParam2) {
		this.userParam2 = userParam2;
	}
	public char getUserParam3() {
		return userParam3;
	}
	public void setUserParam3(char userParam3) {
		this.userParam3 = userParam3;
	}
	public char getUserParam4() {
		return userParam4;
	}
	public void setUserParam4(char userParam4) {
		this.userParam4 = userParam4;
	}
	public char getPitchServoControl() {
		return pitchServoControl;
	}
	public void setPitchServoControl(char pitchServoControl) {
		this.pitchServoControl = pitchServoControl;
	}
	public char getPitchServo() {
		return pitchServo;
	}
	public void setPitchServo(char pitchServo) {
		this.pitchServo = pitchServo;
	}
	public char getPitchServoMin() {
		return pitchServoMin;
	}
	public void setPitchServoMin(char pitchServoMin) {
		this.pitchServoMin = pitchServoMin;
	}
	public char getPitchServoMax() {
		return pitchServoMax;
	}
	public void setPitchServoMax(char pitchServoMax) {
		this.pitchServoMax = pitchServoMax;
	}
	public char getPitchServoRefresh() {
		return pitchServoRefresh;
	}
	public void setPitchServoRefresh(char pitchServoRefresh) {
		this.pitchServoRefresh = pitchServoRefresh;
	}
	public char getPitchServoCompInvert() {
		return pitchServoCompInvert;
	}
	public void setPitchServoCompInvert(char pitchServoCompInvert) {
		this.pitchServoCompInvert = pitchServoCompInvert;
	}
	public char[] getReserved() {
		return reserved;
	}
	public void setReserved(char[] reserved) {
		this.reserved = reserved;
	}
	public char[] getName() {
		return name;
	}
	public void setName(char[] name) {
		this.name = name;
	}
	
	public void setNickForCamera(char value)
	{
		this.serialPoti[1] = value;
		if(Application.sharedApplication().serialComm != null)
		{
			Application.sharedApplication().serialComm.sendCommand('b', 'y', this.serialPoti);
		}
	}
	
	public char getNickForCamera()
	{
		return this.serialPoti[1];
	}

	public void setCameraShootValue(char value)
	{
		this.serialPoti[0] = value;
		if(Application.sharedApplication().serialComm != null)
		{
			Application.sharedApplication().serialComm.sendCommand('b', 'y', this.serialPoti);
		}
	}

	public void setData3D(MKData3D data3D) {
		this.data3D = data3D;
	}

	public MKData3D getData3D() {
		return data3D;
	}
}