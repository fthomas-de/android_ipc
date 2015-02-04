/**
 * 
 */
package de.uni_bremen.informatik.act13.android.model;

/**
 * POJO containing the data of a single entry in a {@link WifiScanResult}.
 * 
 * @author Roman Gischkowski
 * 
 */
public class SingleWifiScanResult {

	private String BSSID;
	private String SSID;
	private int frequency;
	private int level;

	/**
	 * Getter for {@link #bSSID}.
	 * 
	 * @return the {@link #bSSID}
	 */
	public String getBSSID() {
		return BSSID;
	}

	/**
	 * Setter for {@link #bSSID}.
	 * 
	 * @param bSSID
	 *            The {@link #bSSID} to set
	 */
	public void setBSSID(String bSSID) {
		BSSID = bSSID;
	}

	/**
	 * Getter for {@link #sSID}.
	 * 
	 * @return the {@link #sSID}
	 */
	public String getSSID() {
		return SSID;
	}

	/**
	 * Setter for {@link #sSID}.
	 * 
	 * @param sSID
	 *            The {@link #sSID} to set
	 */
	public void setSSID(String sSID) {
		SSID = sSID;
	}

	/**
	 * Getter for {@link #frequency}.
	 * 
	 * @return the {@link #frequency}
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * Setter for {@link #frequency}.
	 * 
	 * @param frequency
	 *            The {@link #frequency} to set
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * Getter for {@link #level}.
	 * 
	 * @return the {@link #level}
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Setter for {@link #level}.
	 * 
	 * @param level
	 *            The {@link #level} to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
}
