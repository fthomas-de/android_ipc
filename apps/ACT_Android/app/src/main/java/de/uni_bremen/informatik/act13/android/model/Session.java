/**
 * 
 */
package de.uni_bremen.informatik.act13.android.model;

/**
 * POJO for session-metadata
 * 
 * @author Roman Gischkowski
 * 
 */
public class Session {

	private Integer session_id;
	private long startAt;
	private String notes;
	private String subject;
	private String device;
	private String position;

	/**
	 * Getter for {@link #session_id}.
	 * 
	 * @return the {@link #session_id}
	 */
	public Integer getSession_id() {
		return session_id;
	}

	/**
	 * Setter for {@link #session_id}.
	 * 
	 * @param session_id
	 *            The {@link #session_id} to set
	 */
	public void setSession_id(Integer session_id) {
		this.session_id = session_id;
	}

	/**
	 * Getter for {@link #startAt}.
	 * 
	 * @return the {@link #startAt}
	 */
	public long getStartAt() {
		return startAt;
	}

	/**
	 * Setter for {@link #startAt}.
	 * 
	 * @param startAt
	 *            The {@link #startAt} to set
	 */
	public void setStartAt(long startAt) {
		this.startAt = startAt;
	}

	/**
	 * Getter for {@link #notes}.
	 * 
	 * @return the {@link #notes}
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * Setter for {@link #notes}.
	 * 
	 * @param notes
	 *            The {@link #notes} to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * Getter for {@link #subject}.
	 * 
	 * @return the {@link #subject}
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Setter for {@link #subject}.
	 * 
	 * @param subject
	 *            The {@link #subject} to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Getter for {@link #device}.
	 * 
	 * @return the {@link #device}
	 */
	public String getDevice() {
		return device;
	}

	/**
	 * Setter for {@link #device}.
	 * 
	 * @param device
	 *            The {@link #device} to set
	 */
	public void setDevice(String device) {
		this.device = device;
	}

	/**
	 * Getter for {@link #position}.
	 *
	 * @return the {@link #position}
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * Setter for {@link #position}.
	 *
	 * @param position The {@link #position} to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
}
