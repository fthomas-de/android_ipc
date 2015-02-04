/**
 * 
 */
package de.uni_bremen.informatik.act13.android.location;

import android.util.Log;
import android.widget.Toast;
import de.uni_bremen.informatik.act13.android.ACTApplication;
import de.uni_bremen.informatik.act13.android.Sensors;
import de.uni_bremen.informatik.act13.android.model.LocationWithTime;
import de.uni_bremen.informatik.act13.android.orientation.TimeKeepingTripleValueListener;

/**
 * Activity for displaying the WLAN location.
 * 
 * @author Roman Gischkowski
 * 
 */
public class WLANActivity extends AbstractLocationActivity {

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
		LocationWithTime location = app.getWlanLocationListener().getLastLocation();

		if (location == null) {
			Toast.makeText(WLANActivity.this, "No location to save!", Toast.LENGTH_SHORT).show();
		} else {
			location.setLabel(label);

			try {
				app.getJsonStreamer().write(location, Sensors.WLAN.toString());
			} catch (Exception e) {
				Log.e(TimeKeepingTripleValueListener.class.getName(), "Error saving location: ", e);
				Toast.makeText(WLANActivity.this, "Error savong location. See log for details.", Toast.LENGTH_SHORT)
						.show();
			}

			Toast.makeText(WLANActivity.this, "WLAN-location saved...", Toast.LENGTH_SHORT).show();
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
		app.getWlanLocationListener().setLocationView(this);
		update(app.getWlanLocationListener().getLastLocation());
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
		app.getWlanLocationListener().setLocationView(null);
	}

}
