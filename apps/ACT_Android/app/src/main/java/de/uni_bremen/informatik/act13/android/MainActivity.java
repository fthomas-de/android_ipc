package de.uni_bremen.informatik.act13.android;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import de.uni_bremen.informatik.act13.android.location.GPSActivity;
import de.uni_bremen.informatik.act13.android.location.WLANActivity;
import de.uni_bremen.informatik.act13.android.time.NTPConnector.Callback;
import de.uni_bremen.informatik.act13.android.upload.UploadActivity;

/**
 * Activity for the main menu. Contains the buttons for starting a session,
 * stopping it, and for switching to other views.
 * 
 * @author Roman Gischkowski
 * 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		displayMaxValues();

		final EditText labeling = (EditText) findViewById(R.id.editText_labeling);
		final ToggleButton labelingButton = (ToggleButton) findViewById(R.id.button_labeling);
		final Spinner positionLabel = (Spinner) findViewById(R.id.editText_position_label);
		final EditText subjectLabel = (EditText) findViewById(R.id.editText_subject_label);
		final EditText sessionId = (EditText) findViewById(R.id.editText_session_id_label);
		final ToggleButton toggleSession = (ToggleButton) findViewById(R.id.button_toggle_all);
		final EditText sessionLabel = (EditText) findViewById(R.id.editText_session_label);
		Button showGPS = (Button) findViewById(R.id.button_show_gps);
		Button showWLAN = (Button) findViewById(R.id.button_show_wlan);
		final Button showUpdate = (Button) findViewById(R.id.button_show_upload);
		final Button syncTime = (Button) findViewById(R.id.button_sync_time);
		final TextView syncTimeText = (TextView) findViewById(R.id.textView_sync_time);

		toggleSession.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ACTApplication app = (ACTApplication) getApplication();
				if (toggleSession.isChecked()) {
					String subject = subjectLabel.getText() == null ? null : subjectLabel.getText().toString();
					if (subject == null || subject.isEmpty()) {
						toggleSession.setChecked(false);
						Toast.makeText(MainActivity.this, "Enter a subject!", Toast.LENGTH_LONG).show();
						return;
					}
					String position = positionLabel.getSelectedItem() == null ? null : positionLabel.getSelectedItem().toString();
					if (position == null || position.isEmpty()) {
						toggleSession.setChecked(false);
						Toast.makeText(MainActivity.this, "Enter a position!", Toast.LENGTH_SHORT).show();
						return;
					}
					String label = sessionLabel.getText() == null ? null : sessionLabel.getText().toString();
					if (label != null && !label.isEmpty()) {
						app.startSession(sessionId.getText().toString(), label, subject, position);
						sessionLabel.setEnabled(false);
						sessionId.setEnabled(false);
						subjectLabel.setEnabled(false);
						showUpdate.setEnabled(false);
						positionLabel.setEnabled(false);
					} else {
						toggleSession.setChecked(false);
						Toast.makeText(MainActivity.this, "Enter a name for this session!", Toast.LENGTH_LONG).show();
					}
				} else {
					app.stopSession();
					sessionLabel.setEnabled(true);
					sessionId.setEnabled(true);
					sessionId.setText("");
					sessionLabel.setText("");
					subjectLabel.setEnabled(true);
					showUpdate.setEnabled(true);
					positionLabel.setEnabled(true);
					labelingButton.setChecked(false);
				}
			}
		});

		showGPS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, GPSActivity.class);
				startActivity(intent);
			}
		});

		showWLAN.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, WLANActivity.class);
				startActivity(intent);
			}
		});

		showUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, UploadActivity.class);
				startActivity(intent);
			}
		});
		
		labelingButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ACTApplication app = (ACTApplication) getApplication();
				if (!app.isRunning()) {
					Toast.makeText(MainActivity.this, "You can only add labels during a session!", Toast.LENGTH_SHORT).show();
					labelingButton.setChecked(false);
					return;
				}
				
				long time = app.getNtpConnector().getCurrentTime();
				String label = labeling.getText().toString();
				if (labelingButton.isChecked()) {
					label = "start " + label;
				} else {
					label = "stop " + label;
					labeling.setText("");
				}
				app.addLabel(time, label);
			}
		});
		
		syncTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				syncTime.setEnabled(false);
				ACTApplication app = (ACTApplication) getApplication();
				app.getNtpConnector().synchronizeTime(new Callback() {
					
					@Override
					public void call() {
						syncTime.setEnabled(true);
						syncTimeText.setText(R.string.sync_time_done);
					}
				});
			}
		});
	}

	/**
	 * Displays the maximum values the sensors can recognize.
	 */
	private void displayMaxValues() {
		SensorManager sensorManager = ((ACTApplication) getApplication()).getSensorManager();
		StringBuilder maxValues = new StringBuilder("MaxValues:\n");
		maxValues.append(Sensors.ACCELERATION.toString() + ": ");
		maxValues.append(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER).getMaximumRange());
		maxValues.append("m/s^2\n" + Sensors.GYRO.toString() + ": ");
		sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE).getMaximumRange();
		maxValues.append("radians/second\n" + Sensors.MAGNET.toString() + ": ");
		sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD).getMaximumRange();
		maxValues.append("uT (micro-Tesla)\n" + Sensors.GRAVITY.toString() + ": ");
		sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY).getMaximumRange();
		maxValues.append("m/s^2\n" + Sensors.ROTATION.toString() + ": ");
		sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR).getMaximumRange();
		TextView maxValuesView = (TextView) findViewById(R.id.textView_max_values);
		maxValuesView.setText(maxValues);
	}

}
