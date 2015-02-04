package de.uni_bremen.informatik.act13.android;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.widget.Toast;
import de.uni_bremen.informatik.act13.android.filestorage.FileHandler;
import de.uni_bremen.informatik.act13.android.json.JsonStreamer;
import de.uni_bremen.informatik.act13.android.json.LabelsGenerator;
import de.uni_bremen.informatik.act13.android.location.TimeKeepingLocationListener;
import de.uni_bremen.informatik.act13.android.model.Session;
import de.uni_bremen.informatik.act13.android.orientation.TimeKeepingTripleValueListener;
import de.uni_bremen.informatik.act13.android.time.NTPConnector;
import de.uni_bremen.informatik.act13.android.time.NTPConnector.Callback;
import de.uni_bremen.informatik.act13.android.upload.Uploader;
import de.uni_bremen.informatik.act13.android.wlan.WifiBroadcastReceiver;
import de.uni_bremen.informatik.act13.android.wlan.WifiScannerThread;

/**
 * This {@link Application} class holds the state while the app is running. It
 * has getters and setters for all needed object. Everything should be initiated
 * in the {@link #onCreate()} method.
 * 
 * @author Roman Gischkowski
 * 
 */
public class ACTApplication extends android.app.Application {

	/**
	 * The Android-LocationManager.
	 */
	private LocationManager locationManager;
	/**
	 * The Android-WifiManager
	 */
	private WifiManager wifiManager;
	/**
	 * The Android SensorManager
	 */
	private SensorManager sensorManager;
	/**
	 * Rate for receiving updates from sensors.
	 */
	private static final Integer RATE_US = 10000;
	/**
	 * The time to wait between two wifi scans. (miliseconds)
	 */
	public static final long WIFI_SCAN_RATE_MILIS = 1000;
	/**
	 * Timeout for connection with db-server.
	 */
	public static final Integer CONNECTION_TIMEOUT = 10000;
	/**
	 * Read-timeout for connection with db-server.
	 */
	public static final Integer READ_TIMEOUT = 0;
	/**
	 * The URL of the server to save the data to.
	 */
	public static final String DB_SERVER = "http://act2.informatik.uni-bremen.de:3000/sessions/json_parse";
	/**
	 * The NTP-Server to user for synchronization.
	 */
	// private final static String NTP_SERVER = "134.102.222.114";
	private final static String NTP_SERVER = "2.de.pool.ntp.org";
	/**
	 * Amount of entries in json files before splitted
	 */
	public final static int MAX_NUMBER_ENTRY_COUNT_IN_JSON_FILE = 10000;
	/**
	 * The NTP-Connector to use for getting the current time.
	 */
	private NTPConnector ntpConnector;

	private JsonStreamer jsonStreamer;
	private LabelsGenerator labelsGenerator;
	private FileHandler fileHandler;

	// Listener
	private TimeKeepingLocationListener wlanLocationListener;
	private TimeKeepingLocationListener gpsLocationListener;
	private TimeKeepingTripleValueListener linearAccelerationListener;
	private TimeKeepingTripleValueListener accelerationListener;
	private TimeKeepingTripleValueListener gyroListener;
	private TimeKeepingTripleValueListener magnetListener;
	private TimeKeepingTripleValueListener gravityListener;
	private TimeKeepingTripleValueListener rotationListener;

	/**
	 * Loads the data to the server.
	 */
	private Uploader uploader;

	// WIFI
	private WifiLock wifiLock;
	private WifiBroadcastReceiver wifiReceiver;
	private IntentFilter wifiFilter;
	private WifiScannerThread wifiScannerThread;

	private WakeLock wakeLock;

	/**
	 * If currently a session is running.
	 */
	private boolean running;
	/**
	 * The label of the current session. {@code null} if no session running.
	 */
	private String currentSessionLabel;
	private String currentSessionId;
	private Session currentSession;
	/**
	 * The subject of the current session.
	 */
	private String currentSubjectLabel;
	/**
	 * The position of the smartphone (arm, body, etc)
	 */
	private String currentPosition;
	/**
	 * The time when the current session was started.
	 */
	private Long sessionStartedTime;

	private Map<Long, String> labels;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();

		ntpConnector = new NTPConnector(getApplicationContext(), NTP_SERVER);

		fileHandler = new FileHandler(getApplicationContext());
		jsonStreamer = new JsonStreamer(fileHandler);
		labelsGenerator = new LabelsGenerator();

		wlanLocationListener = new TimeKeepingLocationListener(this, Sensors.WLAN);
		gpsLocationListener = new TimeKeepingLocationListener(this, Sensors.GPS);
		linearAccelerationListener = new TimeKeepingTripleValueListener(this, Sensors.LINEAR_ACCELERATION);
		accelerationListener = new TimeKeepingTripleValueListener(this, Sensors.ACCELERATION);
		gyroListener = new TimeKeepingTripleValueListener(this, Sensors.GYRO);
		magnetListener = new TimeKeepingTripleValueListener(this, Sensors.MAGNET);
		gravityListener = new TimeKeepingTripleValueListener(this, Sensors.GRAVITY);
		rotationListener = new TimeKeepingTripleValueListener(this, Sensors.ROTATION);

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		uploader = new Uploader();

		wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		wifiLock = wifiManager.createWifiLock(WifiManager.WIFI_MODE_SCAN_ONLY, ACTApplication.class.getName());

		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, ACTApplication.class.toString());

		wifiReceiver = new WifiBroadcastReceiver(this, wifiManager);
		wifiFilter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
	}

	/**
	 * Starts recording for all sensors. Saves the entries in multiple files
	 * (one for each sensor) in a directory. The label is used to name the
	 * directory.
	 * 
	 * @param label
	 * @param subject
	 */
	public void startSession(String sessionId, String label, String subject, String position) {
		if (label != null && !label.isEmpty() && !running) {

			if (!ntpConnector.isSynchronizedTime()) {
				Toast.makeText(getApplicationContext(), "Please synchronize the time first!", Toast.LENGTH_LONG).show();
				return;
			}

			currentSessionLabel = label;
			currentSessionId = sessionId;
			currentSubjectLabel = subject;
			currentPosition = position;

			wifiLock.acquire();
			wakeLock.acquire();

			sessionStartedTime = ntpConnector.getCurrentTime();

			labels = new HashMap<Long, String>();

			currentSession = new Session();
			currentSession.setDevice("nexus");
			currentSession.setNotes(currentSessionLabel);
			currentSession.setSubject(currentSubjectLabel);
			currentSession.setPosition(currentPosition);
			currentSession.setStartAt(sessionStartedTime);
			if (currentSessionId != null && !currentSessionId.isEmpty()) {
				currentSession.setSession_id(Integer.parseInt(currentSessionId));
			}

			try {
				jsonStreamer.createStream(currentSession);
			} catch (IOException e) {
				Log.e(ACTApplication.class.getName(), "Error while creating file: ", e);
				Toast.makeText(getApplicationContext(), "Error while creating file! See log for details.",
						Toast.LENGTH_SHORT).show();
				wakeLock.release();
				wifiLock.release();
				return;
			}

			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, wlanLocationListener);

			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, gpsLocationListener);

			sensorManager.registerListener(linearAccelerationListener,
					sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), RATE_US);

			sensorManager.registerListener(accelerationListener,
					sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), RATE_US);

			sensorManager
					.registerListener(gyroListener, sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), RATE_US);

			sensorManager.registerListener(magnetListener, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
					RATE_US);

			sensorManager.registerListener(gravityListener, sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
					RATE_US);

			sensorManager.registerListener(rotationListener,
					sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR), RATE_US);

			registerReceiver(wifiReceiver, wifiFilter);

			wifiScannerThread = new WifiScannerThread(wifiManager);
			wifiScannerThread.start();

			running = true;

		}
	}

	/**
	 * Stops the currently running session.
	 */
	public void stopSession() {
		if (running) {
			running = false;
			currentSessionLabel = null;
			currentSessionId = null;
			currentSubjectLabel = null;

			saveLabelsToFile(currentSession);
			currentSession = null;

			locationManager.removeUpdates(wlanLocationListener);
			locationManager.removeUpdates(gpsLocationListener);

			sensorManager.unregisterListener(linearAccelerationListener);
			sensorManager.unregisterListener(accelerationListener);
			sensorManager.unregisterListener(gyroListener);
			sensorManager.unregisterListener(magnetListener);
			sensorManager.unregisterListener(gravityListener);
			sensorManager.unregisterListener(rotationListener);
			// sensorManager.unregisterListener(orientationListener);

			wifiScannerThread.stopScanning();
			unregisterReceiver(wifiReceiver);

			wakeLock.release();
			wifiLock.release();

			try {
				jsonStreamer.closeStream();
			} catch (Exception e) {
				Log.e(ACTApplication.class.getName(), "Error while closing file: ", e);
				Toast.makeText(getApplicationContext(), "Error while closing file! See log for details.",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	public void addLabel(long time, String label) {
		labels.put(time, label);
	}

	private void saveLabelsToFile(Session session) {
		if (labels.isEmpty()) {
			return;
		}
		String jsonLabels = labelsGenerator.toJson(labels, session.getSession_id());
		File file = fileHandler.getLabelsFileForSession(session);
		FileWriter writer;
		try {
			writer = new FileWriter(file);
			writer.write(jsonLabels);
			writer.close();
		} catch (IOException e) {
			Toast.makeText(this, "Unable to save labels! See log for details.", Toast.LENGTH_SHORT).show();
			Log.e(ACTApplication.class.toString(), "Unable to save labels! Was: " + jsonLabels, e);
		}
	}

	// ###########################
	// ### Getters and Setters ###
	// ###########################

	/**
	 * @return the {@link #currentSessionLabel}
	 */
	public String getCurrentSessionLabel() {
		return currentSessionLabel;
	}

	public String getCurrentSessionId() {
		return currentSessionId;
	}

	/**
	 * Getter for {@link #currentSession}.
	 * 
	 * @return the {@link #currentSession}
	 */
	public Session getCurrentSession() {
		return currentSession;
	}

	/**
	 * Getter for {@link #currentSubjectLabel}.
	 * 
	 * @return the {@link #currentSubjectLabel}
	 */
	public String getCurrentSubjectLabel() {
		return currentSubjectLabel;
	}

	/**
	 * @return the {@link #running}
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Getter for {@link #sensorManager}.
	 * 
	 * @return the {@link #sensorManager}
	 */
	public SensorManager getSensorManager() {
		return sensorManager;
	}

	/**
	 * Setter for {@link #sensorManager}.
	 * 
	 * @param sensorManager
	 *            The {@link #sensorManager} to set
	 */
	public void setSensorManager(SensorManager sensorManager) {
		this.sensorManager = sensorManager;
	}

	/**
	 * Getter for {@link #ntpConnector}.
	 * 
	 * @return the {@link #ntpConnector}
	 */
	public NTPConnector getNtpConnector() {
		return ntpConnector;
	}

	/**
	 * Setter for {@link #ntpConnector}.
	 * 
	 * @param ntpConnector
	 *            The {@link #ntpConnector} to set
	 */
	public void setNtpConnector(NTPConnector ntpConnector) {
		this.ntpConnector = ntpConnector;
	}

	/**
	 * Getter for {@link #jsonStreamer}.
	 * 
	 * @return the {@link #jsonStreamer}
	 */
	public JsonStreamer getJsonStreamer() {
		return jsonStreamer;
	}

	/**
	 * Setter for {@link #jsonStreamer}.
	 * 
	 * @param jsonStreamer
	 *            The {@link #jsonStreamer} to set
	 */
	public void setJsonStreamer(JsonStreamer jsonStreamer) {
		this.jsonStreamer = jsonStreamer;
	}

	/**
	 * Getter for {@link #fileHandler}.
	 * 
	 * @return the {@link #fileHandler}
	 */
	public FileHandler getFileHandler() {
		return fileHandler;
	}

	/**
	 * Setter for {@link #fileHandler}.
	 * 
	 * @param fileHandler
	 *            The {@link #fileHandler} to set
	 */
	public void setFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
	}

	/**
	 * @return the {@link #wlanLocationListener}
	 */
	public TimeKeepingLocationListener getWlanLocationListener() {
		return wlanLocationListener;
	}

	/**
	 * @param wlanLocationListener
	 *            the {@link #wlanLocationListener} to set
	 */
	public void setWlanLocationListener(TimeKeepingLocationListener wlanLocationListener) {
		this.wlanLocationListener = wlanLocationListener;
	}

	/**
	 * @return the {@link #gpsLocationListener}
	 */
	public TimeKeepingLocationListener getGpsLocationListener() {
		return gpsLocationListener;
	}

	/**
	 * @param gpsLocationListener
	 *            the {@link #gpsLocationListener} to set
	 */
	public void setGpsLocationListener(TimeKeepingLocationListener gpsLocationListener) {
		this.gpsLocationListener = gpsLocationListener;
	}

	/**
	 * Getter for {@link #uploader}.
	 * 
	 * @return the {@link #uploader}
	 */
	public Uploader getUploader() {
		return uploader;
	}

	/**
	 * Setter for {@link #uploader}.
	 * 
	 * @param uploader
	 *            The {@link #uploader} to set
	 */
	public void setUploader(Uploader uploader) {
		this.uploader = uploader;
	}

	/**
	 * Getter for {@link #sessionStartedTime}.
	 * 
	 * @return the {@link #sessionStartedTime}
	 */
	public Long getSessionStartedTime() {
		return sessionStartedTime;
	}

	/**
	 * Setter for {@link #sessionStartedTime}.
	 * 
	 * @param sessionStartedTime
	 *            The {@link #sessionStartedTime} to set
	 */
	public void setSessionStartedTime(Long sessionStartedTime) {
		this.sessionStartedTime = sessionStartedTime;
	}

}
