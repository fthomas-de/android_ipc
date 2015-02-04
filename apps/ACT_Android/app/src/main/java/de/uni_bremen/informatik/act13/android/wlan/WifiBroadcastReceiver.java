/**
 * 
 */
package de.uni_bremen.informatik.act13.android.wlan;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import de.uni_bremen.informatik.act13.android.ACTApplication;
import de.uni_bremen.informatik.act13.android.json.JsonStreamer;
import de.uni_bremen.informatik.act13.android.model.SingleWifiScanResult;
import de.uni_bremen.informatik.act13.android.model.WifiScanResult;
import de.uni_bremen.informatik.act13.android.time.NTPConnector;

/**
 * This receiver gets data from wifiscans and saves them.
 * 
 * @author Roman Gischkowski
 * 
 */
public class WifiBroadcastReceiver extends BroadcastReceiver {

	private JsonStreamer jsonStreamer;
	private WifiManager wifiManager;
	private NTPConnector ntpConnector;

	public WifiBroadcastReceiver(ACTApplication app, WifiManager wifiManager) {
		this.jsonStreamer = app.getJsonStreamer();
		this.wifiManager = wifiManager;
		this.ntpConnector = app.getNtpConnector();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
	 * android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		long time = ntpConnector.getCurrentTime();

		List<ScanResult> results = wifiManager.getScanResults();
		List<SingleWifiScanResult> wifiResults = new ArrayList<SingleWifiScanResult>();
		for (ScanResult result : results) {
			SingleWifiScanResult wifiResult = new SingleWifiScanResult();
			wifiResult.setBSSID(result.BSSID);
			wifiResult.setFrequency(result.frequency);
			wifiResult.setLevel(result.level);
			wifiResult.setSSID(result.SSID);
			wifiResults.add(wifiResult);
		}

		WifiScanResult wifiResult = new WifiScanResult();
		wifiResult.setResults(wifiResults);
		wifiResult.setNetworkTimestamp(time);

		try {
			jsonStreamer.write(wifiResult);
		} catch (Exception e) {
			Log.e(WifiBroadcastReceiver.class.getName(), "Error while saving:", e);
		}
	}

}
