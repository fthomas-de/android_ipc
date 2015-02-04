package de.uni_bremen.informatik.act13.android.model;

import android.location.Location;

/**
 * POJO containing the data of a location (longitude and latitude) and the
 * corresponding timestamp.
 * 
 * 
 * @author Roman Gischkowski
 * 
 */
public class LocationWithTime {

	private Location location;
	private Long networkTimestamp;
	private String label;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Long getNetworkTimestamp() {
		return networkTimestamp;
	}

	public void setNetworkTimestamp(Long networkTimestamp) {
		this.networkTimestamp = networkTimestamp;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
