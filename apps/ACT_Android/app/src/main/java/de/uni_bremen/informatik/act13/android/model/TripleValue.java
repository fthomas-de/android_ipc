/**
 * 
 */
package de.uni_bremen.informatik.act13.android.model;

/**
 * POJO. Represents a triple of values from the same sensor at one point of
 * time. Sensor are for example accelerometer or gyroscope.
 * 
 * @author Roman Gischkowski
 * 
 */
public class TripleValue {

	private Long networkTimestamp;
	private float x;
	private float y;
	private float z;
	private long sensorTime;
	private long timeSinceStartup;

	/**
	 * Getter for {@link #networkTimestamp}.
	 * 
	 * @return the {@link #networkTimestamp}
	 */
	public Long getNetworkTimestamp() {
		return networkTimestamp;
	}

	/**
	 * Setter for {@link #networkTimestamp}.
	 * 
	 * @param networkTimestamp
	 *            The {@link #networkTimestamp} to set
	 */
	public void setNetworkTimestamp(Long networkTimestamp) {
		this.networkTimestamp = networkTimestamp;
	}

	/**
	 * Getter for {@link #x}.
	 * 
	 * @return the {@link #x}
	 */
	public float getX() {
		return x;
	}

	/**
	 * Setter for {@link #x}.
	 * 
	 * @param x
	 *            The {@link #x} to set
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Getter for {@link #y}.
	 * 
	 * @return the {@link #y}
	 */
	public float getY() {
		return y;
	}

	/**
	 * Setter for {@link #y}.
	 * 
	 * @param y
	 *            The {@link #y} to set
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Getter for {@link #z}.
	 * 
	 * @return the {@link #z}
	 */
	public float getZ() {
		return z;
	}

	/**
	 * Setter for {@link #z}.
	 * 
	 * @param z
	 *            The {@link #z} to set
	 */
	public void setZ(float z) {
		this.z = z;
	}

	/**
	 * Getter for {@link #sensorTime}.
	 * 
	 * @return the {@link #sensorTime}
	 */
	public long getSensorTime() {
		return sensorTime;
	}

	/**
	 * Setter for {@link #sensorTime}.
	 * 
	 * @param sensorTime
	 *            The {@link #sensorTime} to set
	 */
	public void setSensorTime(long sensorTime) {
		this.sensorTime = sensorTime;
	}

	/**
	 * Getter for {@link #timeSinceStartup}.
	 * 
	 * @return the {@link #timeSinceStartup}
	 */
	public long getTimeSinceStartup() {
		return timeSinceStartup;
	}

	/**
	 * Setter for {@link #timeSinceStartup}.
	 * 
	 * @param timeSinceStartup
	 *            The {@link #timeSinceStartup} to set
	 */
	public void setTimeSinceStartup(long timeSinceStartup) {
		this.timeSinceStartup = timeSinceStartup;
	}

}
