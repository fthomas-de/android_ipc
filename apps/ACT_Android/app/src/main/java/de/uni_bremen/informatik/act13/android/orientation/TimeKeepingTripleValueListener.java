/**
 * 
 */
package de.uni_bremen.informatik.act13.android.orientation;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;
import de.uni_bremen.informatik.act13.android.ACTApplication;
import de.uni_bremen.informatik.act13.android.Sensors;
import de.uni_bremen.informatik.act13.android.json.JsonStreamer;
import de.uni_bremen.informatik.act13.android.model.TripleValue;
import de.uni_bremen.informatik.act13.android.time.NTPConnector;

/**
 * Listens for sensorEvents (containing three values) and saves them.
 * 
 * @author Roman Gischkowski
 * 
 */
public class TimeKeepingTripleValueListener implements SensorEventListener {

	private Context appContext;
	private JsonStreamer jsonStreamer;
	private NTPConnector ntpConnector;
	private Sensors sensor;

	public TimeKeepingTripleValueListener(ACTApplication app, Sensors sensor) {
		this.appContext = app.getApplicationContext();
		this.jsonStreamer = app.getJsonStreamer();
		this.ntpConnector = app.getNtpConnector();
		this.sensor = sensor;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// Ignore
	}

	// SensorEvent may be reused!!!
	@Override
	public void onSensorChanged(final SensorEvent event) {
		long time = ntpConnector.getCurrentTime();

		TripleValue tripleValue = new TripleValue();
		tripleValue.setTimeSinceStartup(SystemClock.elapsedRealtime());
		tripleValue.setX(event.values[0]);
		tripleValue.setY(event.values[1]);
		tripleValue.setZ(event.values[2]);
		tripleValue.setSensorTime(event.timestamp);
		tripleValue.setNetworkTimestamp(time);

		try {
			jsonStreamer.write(tripleValue, sensor.toString());
		} catch (Exception e) {
			Log.e(TimeKeepingTripleValueListener.class.getName(), "Error saving location: ", e);
			Toast.makeText(appContext, "Error savong location. See log for details.", Toast.LENGTH_SHORT).show();
		}
	}

}
