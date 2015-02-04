/**
 * 
 */
package de.uni_bremen.informatik.act13.android.location;

import android.util.Log;
import android.widget.Toast;
import de.uni_bremen.informatik.act13.android.ACTApplication;
import de.uni_bremen.informatik.act13.android.Sensors;
import de.uni_bremen.informatik.act13.android.model.LocationWithTime;

/**
 * Activity for displaying GPS location.
 * 
 * @author Roman Gischkowski
 * 
 */
public class GPSActivity extends AbstractLocationActivity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uni_bremen.informatik.act13.android.location.AbstractLocationActivity
	 * #saveButtonClicked()
	 */
	@Override
	public void saveButtonClicked(String label) {
		ACTApplication app = (ACTApplication) getApplication();
		LocationWithTime location = app.getGpsLocationListener().getLastLocation();

		if (location == null) {
			Toast.makeText(GPSActivity.this, "No location to save!", Toast.LENGTH_SHORT).show();
		} else {
			location.setLabel(label);

			try {
				app.getJsonStreamer().write(location, Sensors.GPS.toString());
			} catch (Exception e) {
				Log.e(GPSActivity.class.getName(), "Error saving location:", e);
				Toast.makeText(GPSActivity.this, "Error saving gps location! See log for details.", Toast.LENGTH_LONG)
						.show();
				return;
			}

			Toast.makeText(GPSActivity.this, "GPS-location saved...", Toast.LENGTH_SHORT).show();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		ACTApplication app = (ACTApplication) getApplication();
		app.getGpsLocationListener().setLocationView(this);
		update(app.getGpsLocationListener().getLastLocation());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		ACTApplication app = (ACTApplication) getApplication();
		app.getGpsLocationListener().setLocationView(null);
	}

}
