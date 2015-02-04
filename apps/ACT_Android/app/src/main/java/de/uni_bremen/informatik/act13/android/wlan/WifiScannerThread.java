/**
 * 
 */
package de.uni_bremen.informatik.act13.android.wlan;

import de.uni_bremen.informatik.act13.android.ACTApplication;
import android.net.wifi.WifiManager;
import android.util.Log;

/**
 * Custom thread that forces the wifimanager to scan for wifis while it is
 * running.
 * 
 * @author Roman Gischkowski
 * 
 */
public class WifiScannerThread extends Thread {

	private WifiManager wifiManager;
	private boolean run = false;

	public WifiScannerThread(WifiManager wifiManager) {
		this.wifiManager = wifiManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#start()
	 */
	@Override
	public synchronized void start() {
		setRun(true);
		super.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (isRun()) {
			boolean started = wifiManager.startScan();
			if (!started) {
				Log.e(WifiScannerThread.class.getName(), "Unable to scan wifi!");
			}
			try {
				Thread.sleep(ACTApplication.WIFI_SCAN_RATE_MILIS);
			} catch (Exception e) {
				Log.e(WifiScannerThread.class.getName(), "Error while waiting for next wifi scan:", e);
			}
		}
	}

	public void stopScanning() {
		setRun(false);
	}

	/**
	 * Getter for {@link #run}.
	 * 
	 * @return the {@link #run}
	 */
	private synchronized boolean isRun() {
		return run;
	}

	/**
	 * Setter for {@link #run}.
	 * 
	 * @param run
	 *            The {@link #run} to set
	 */
	private synchronized void setRun(boolean run) {
		this.run = run;
	}

}
