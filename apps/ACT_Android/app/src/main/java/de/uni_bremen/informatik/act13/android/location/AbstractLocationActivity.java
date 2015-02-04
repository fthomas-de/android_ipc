/**
 * 
 */
package de.uni_bremen.informatik.act13.android.location;

import java.text.DateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import de.uni_bremen.informatik.act13.android.ACTApplication;
import de.uni_bremen.informatik.act13.android.R;
import de.uni_bremen.informatik.act13.android.location.TimeKeepingLocationListener.UpdateableLocationView;
import de.uni_bremen.informatik.act13.android.model.LocationWithTime;

/**
 * 
 * This abstract activity is for the GPS location and the WLAN location. It
 * bundles all the functionalities which are the same for both types of
 * location.
 * 
 * @author Roman Gischkowski
 * 
 */
public abstract class AbstractLocationActivity extends Activity implements
		UpdateableLocationView {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);

		Button saveLocation = (Button) findViewById(R.id.button_save);
		saveLocation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (((ACTApplication) getApplication()).isRunning()) {
					EditText label = (EditText) findViewById(R.id.editText_label);
					saveButtonClicked(label.getText() == null ? "" : label
							.getText().toString());
					label.setText("");
				} else {
					Toast.makeText(AbstractLocationActivity.this,
							"Start a session to save location!",
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	/**
	 * This method is called, when the save button was clicked. The given label
	 * is the label currently in the textbox. It is cleared after calling this
	 * method. The implementation should get the current location displayed and
	 * save it. Doing so it should use the matching locationListener and
	 * locationSaver.
	 * 
	 * @param label
	 */
	public abstract void saveButtonClicked(String label);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uni_bremen.informatik.act13.android.location.UpdateableLocationView
	 * #update(de.uni_bremen.informatik.act13.android.location.ACTLocation)
	 */
	@Override
	public void update(LocationWithTime location) {
		EditText latitudeView = (EditText) findViewById(R.id.editText_latitude);
		EditText longitudeView = (EditText) findViewById(R.id.editText_longitude);
		EditText timestampView = (EditText) findViewById(R.id.editText_timestamp);
		EditText networkTimestampView = (EditText) findViewById(R.id.editText_networkTimestamp);

		if (location == null) {
			latitudeView.setText("");
			longitudeView.setText("");
			timestampView.setText("");
			networkTimestampView.setText("");
		} else {
			latitudeView.setText(String.valueOf(location.getLocation()
					.getLatitude()));
			longitudeView.setText(String.valueOf(location.getLocation()
					.getLongitude()));

			timestampView.setText(timeToString(location.getLocation()
					.getTime()));
			networkTimestampView.setText(timeToString(location
					.getNetworkTimestamp()));
		}
	}
	
	/**
	 * Returns a string representation of the time to display it on the GUI.
	 * 
	 * @param time
	 * @return
	 */
	private String timeToString(Long time) {
		if (time == null) {
			return "null";
		}
		DateFormat dateFormat = DateFormat.getDateTimeInstance();
		return dateFormat.format(new Date(time)) + " (" + time + ")";
	}

}
