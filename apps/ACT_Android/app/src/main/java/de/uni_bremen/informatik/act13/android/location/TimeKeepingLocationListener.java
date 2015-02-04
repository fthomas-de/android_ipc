package de.uni_bremen.informatik.act13.android.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import de.uni_bremen.informatik.act13.android.ACTApplication;
import de.uni_bremen.informatik.act13.android.Sensors;
import de.uni_bremen.informatik.act13.android.json.JsonStreamer;
import de.uni_bremen.informatik.act13.android.model.LocationWithTime;
import de.uni_bremen.informatik.act13.android.orientation.TimeKeepingTripleValueListener;
import de.uni_bremen.informatik.act13.android.time.NTPConnector;

/**
 * Listens for locations and saves them (and their time). Will also update a
 * view if one is set.
 * 
 * 
 * @author Roman Gischkowski
 * 
 */
public class TimeKeepingLocationListener implements LocationListener {

	private Context appContext;
	private JsonStreamer jsonStreamer;
	private NTPConnector ntpConnector;
	private Sensors sensor;
	private UpdateableLocationView locationView;
	private LocationWithTime lastLocation;

	public TimeKeepingLocationListener(ACTApplication app, Sensors sensor) {
		this.appContext = app.getApplicationContext();
		this.jsonStreamer = app.getJsonStreamer();
		this.ntpConnector = app.getNtpConnector();
		this.sensor = sensor;
	}

	@Override
	public void onLocationChanged(final Location location) {
		long time = ntpConnector.getCurrentTime();

		LocationWithTime currentLocation = new LocationWithTime();
		currentLocation.setLocation(location);
		currentLocation.setNetworkTimestamp(time);

		if (jsonStreamer != null) {
			try {
				jsonStreamer.write(currentLocation, sensor.toString());
			} catch (Exception e) {
				Log.e(TimeKeepingTripleValueListener.class.getName(), "Error saving location: ", e);
				Toast.makeText(appContext, "Error savong location. See log for details.", Toast.LENGTH_SHORT).show();
			}
		}
		if (locationView != null) {
			locationView.update(currentLocation);
		}

		lastLocation = currentLocation;
	}

	public LocationWithTime getLastLocation() {
		return lastLocation;
	}

	@Override
	public void onProviderDisabled(String provider) {

	}

	@Override
	public void onProviderEnabled(String provider) {

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

	/**
	 * @return the {@link #locationView}
	 */
	public UpdateableLocationView getLocationView() {
		return locationView;
	}

	/**
	 * @param locationView
	 *            the {@link #locationView} to set
	 */
	public void setLocationView(UpdateableLocationView locationView) {
		this.locationView = locationView;
	}

	/**
	 * Represents a view which displays an {@link LocationWithTime} and can be
	 * updated.
	 * 
	 * @author Roman Gischkowski
	 * 
	 */
	public interface UpdateableLocationView {
		/**
		 * Updates the representation of the location on the view.
		 * 
		 * @param location
		 */
		public void update(LocationWithTime location);
	}

}
