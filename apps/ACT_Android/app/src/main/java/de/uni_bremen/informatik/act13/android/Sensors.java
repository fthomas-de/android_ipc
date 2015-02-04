/**
 * 
 */
package de.uni_bremen.informatik.act13.android;

/**
 * This enum contains all sensors used by this application. The entries
 * {@code toString()} will be used in the json output.
 * 
 * @author Roman Gischkowski
 * 
 */
public enum Sensors {

	INFO {
		@Override
		public String toString() {
			return "info";
		}
	},
	GPS {
		@Override
		public String toString() {
			return "gps";
		}
	},
	WLAN {
		@Override
		public String toString() {
			return "wlan";
		}
	},
	LINEAR_ACCELERATION {
		@Override
		public String toString() {
			return "lac";
		}
	},
	ACCELERATION {
		@Override
		public String toString() {
			return "acc";
		}
	},
	GYRO {
		@Override
		public String toString() {
			return "gyro";
		}
	},
	MAGNET {
		@Override
		public String toString() {
			return "magnet";
		}
	},
	GRAVITY {
		@Override
		public String toString() {
			return "gravity";
		}
	},
	ROTATION {
		@Override
		public String toString() {
			return "rotation";
		}
	},
	WIFI_SCAN {
		@Override
		public String toString() {
			return "wifiscan";
		}
	}/*
	 * , ORIENTATION {
	 * 
	 * @Override public String toString() { return "orient"; } }
	 */
}
