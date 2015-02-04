/**
 * 
 */
package de.uni_bremen.informatik.act13.android.time;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

/**
 * Util for the connection to the NTP server. Wrapps the connection to the
 * server and returns the current time.
 * 
 * @author Roman Gischkowski
 * 
 */
public class NTPConnector {

	private Context context;
	private String timeServer;
	private NTPUDPClient timeClient;
	private InetAddress inetAddress;

	public NTPConnector(Context context, String timeServer) {
		this.context = context;
		this.timeServer = timeServer;
		setUpTime();
	}

	private void setUpTime() {
		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				setUpTimeAsync();
				return null;
			}
		};
		task.execute();
	}

	private void setUpTimeAsync() {
		timeClient = new NTPUDPClient();
		try {
			inetAddress = InetAddress.getByName(timeServer);
		} catch (UnknownHostException e) {
			Log.e(NTPConnector.class.toString(), "Host for time server is unknown: ", e);
		}
	}

	/**
	 * Containes the point in time, when the system was bootet, in miliseconds.
	 * By adding the time of {@code SystemClock.elapsedRealtime()} to any given
	 * point in the future, the exact current time is the result.
	 */
	private Long systemStartTime;

	private Object lock = new Object();

	private Boolean synchronizeTimeAsync() {
		try {
			TimeInfo timeInfo = timeClient.getTime(inetAddress);
			long systemUpTime = SystemClock.elapsedRealtime();
			long netTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
			synchronized (lock) {
				systemStartTime = netTime - systemUpTime;
			}
		} catch (Throwable e) {
			Log.e(NTPConnector.class.toString(), "Exception while getting network time: ", e);
			return false;
		}
		return true;
	}
	
	public boolean isSynchronizedTime() {
		synchronized (lock) {
			return systemStartTime != null;
		}
	}

	/**
	 * Gets the current time from the ntp server. Does not really uses the time
	 * from server and does not perfom a network request. It calculates the time
	 * with the SystemClock and the time saved when
	 * {@link #synchronizeTime(Callback)} was called. So it is required to call
	 * {@link #synchronizeTime(Callback)} before.
	 * 
	 * @return
	 */
	public long getCurrentTime() {
		long systemUpTime = SystemClock.elapsedRealtime(); // First statement!!!

		synchronized (lock) {
			if (systemStartTime == null) {
				return -systemUpTime;
			} else {
				return systemStartTime + systemUpTime;
			}
		}
	}

	/**
	 * Synchonizes the systems time with the time from the server. Therefore it
	 * performs an asyncTask which performs the request. The result is saved for
	 * later calculation of the current time. The callback.call-method is
	 * invoked, as soon as the current time from the ntp server is returned, and
	 * the {@code getCurrentTime()}-method is ready-to-call.
	 * 
	 * @param callback
	 */
	public void synchronizeTime(final Callback callback) {
		AsyncTask<Void, Void, Boolean> task = new AsyncTask<Void, Void, Boolean>() {
			@Override
			protected Boolean doInBackground(Void... params) {
				return synchronizeTimeAsync();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
			 */
			@Override
			protected void onPostExecute(Boolean result) {
				if (result != null && result) {
					Toast.makeText(context, "Synchronized time!", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(context, "Unable to synchronized time! See log for details.", Toast.LENGTH_SHORT)
							.show();
				}
				callback.call();
			}
		};
		task.execute();
	}

	public interface Callback {
		public void call();
	}
}
