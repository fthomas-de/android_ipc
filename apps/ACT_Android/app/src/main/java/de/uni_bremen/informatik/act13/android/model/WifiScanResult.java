/**
 * 
 */
package de.uni_bremen.informatik.act13.android.model;

import java.util.ArrayList;
import java.util.List;

/**
 * POJO containing the data of a wifiscan.
 * 
 * @author Roman Gischkowski
 * 
 */
public class WifiScanResult {

	private List<SingleWifiScanResult> results = new ArrayList<SingleWifiScanResult>();
	private long networkTimestamp;

	/**
	 * Getter for {@link #results}.
	 * 
	 * @return the {@link #results}
	 */
	public List<SingleWifiScanResult> getResults() {
		return results;
	}

	/**
	 * Setter for {@link #results}.
	 * 
	 * @param results
	 *            The {@link #results} to set
	 */
	public void setResults(List<SingleWifiScanResult> results) {
		this.results = results;
	}

	/**
	 * Getter for {@link #networkTimestamp}.
	 * 
	 * @return the {@link #networkTimestamp}
	 */
	public long getNetworkTimestamp() {
		return networkTimestamp;
	}

	/**
	 * Setter for {@link #networkTimestamp}.
	 * 
	 * @param networkTimestamp
	 *            The {@link #networkTimestamp} to set
	 */
	public void setNetworkTimestamp(long networkTimestamp) {
		this.networkTimestamp = networkTimestamp;
	}
}
