/**
 * 
 */
package de.uni_bremen.informatik.act13.android.json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;

import de.uni_bremen.informatik.act13.android.ACTApplication;
import de.uni_bremen.informatik.act13.android.Sensors;
import de.uni_bremen.informatik.act13.android.filestorage.FileHandler;
import de.uni_bremen.informatik.act13.android.model.LocationWithTime;
import de.uni_bremen.informatik.act13.android.model.Session;
import de.uni_bremen.informatik.act13.android.model.SingleWifiScanResult;
import de.uni_bremen.informatik.act13.android.model.TripleValue;
import de.uni_bremen.informatik.act13.android.model.WifiScanResult;

/**
 * This class provides methods for interaction with big json files. For creating
 * a new file, first call {@link #createStream(Session, OutputStream)}. Then you
 * can repeatly call {@link #write(WifiScanResult)},
 * {@link JsonStreamer#write(LocationWithTime, String)} and
 * {@link #write(TripleValue, String)} to write new entries to the file. Finally
 * you should call {@link JsonStreamer#closeStream()} the end the file.
 * 
 * <p>
 * The method {@link JsonStreamer#readStreamHeader(InputStream)} can be used to
 * read the head of a file, for getting the metadata, but not requiring to read
 * the hole file.
 * </p>
 * <p>
 * The method {@link #copyAndModifySessionId(InputStream, int, OutputStream)}
 * can be used to change the sessionID of a file. Therefore it is required to
 * copy the hole file.
 * </p>
 * 
 * @author Roman Gischkowski
 * 
 */
public class JsonStreamer {

	private final static String SESSION_ID = "session_id";
	private final static String START_AT = "startAt";
	private final static String NOTES = "notes";
	private final static String SUBJECT = "subject";
	private final static String POSITION = "position";
	private final static String DATA_SETS = "dataSets";
	private final static String TIMESTAMP = "timestamp";
	private final static String LABEL = "label";
	private final static String DEVICE = "device";
	private final static String RELEVANT_DATA = "relevantData";

	private final static String NEXUS = "nexus";
	private final static String BSSID = "BSSID";
	private final static String SSID = "SSID";
	private final static String LEVEL = "level";
	private final static String FREQUENCY = "frequency";

	private JsonFactory factory;
	private JsonGenerator generator;
	private int entryCounter = 0;
	private FileHandler fileHandler;
	private Session currentSession;

	public JsonStreamer(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
		factory = new JsonFactory();
	}

	/**
	 * Copies the session and modifies its ID. Both streams will be closed.
	 * 
	 * @param in
	 * @param sessionId
	 * @param out
	 * @throws JsonParseException
	 * @throws IOException
	 */
	public void copyAndModifySessionId(InputStream in, int sessionId, OutputStream out) throws JsonParseException,
			IOException {
		JsonGenerator gen = null;
		JsonParser parser = null;
		try {
			gen = factory.createGenerator(out);
			parser = factory.createParser(in);
			// TODO: Funktioniert noch nicht ganz wenn die id schon angegeben ist.
			parser.nextToken(); // start object
			gen.copyCurrentEvent(parser);

			gen.writeNumberField(SESSION_ID, sessionId);

			while (parser.nextToken() != null) {
				gen.copyCurrentEvent(parser);
			}
			gen.flush();
		} finally {
			try {
				gen.close();
			} catch (Exception e) {
			}
			try {
				parser.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Cann be used to close all streams and reseting internal state. (may be
	 * usefull in case of an error)
	 */
	public void reset() {
		if (generator != null) {
			try {
				generator.close();
			} catch (IOException e) {
				Log.w(JsonStreamer.class.toString(), "Error while reseting:", e);
			}
			generator = null;
		}
	}

	/**
	 * Reads the beginning of a file to get the metadata. The stream will be
	 * closed.
	 * 
	 * @param in
	 * @return
	 * @throws JsonParseException
	 * @throws IOException
	 */
	public Session readStreamHeader(InputStream in) throws JsonParseException, IOException {
		JsonParser parser = null;
		try {
			parser = factory.createParser(in);

			Session session = new Session();
			parser.nextToken(); // Start object

			parser.nextToken(); // session id
			if (parser.getCurrentName().equals(SESSION_ID)) {
				parser.nextToken(); // value
				session.setSession_id(parser.getIntValue());

				parser.nextToken(); // start at
			}

			if (!parser.getCurrentName().equals(START_AT)) {
				throw new IllegalStateException("Invalid JSON: Expected start at, but was " + parser.getCurrentName());
			}
			parser.nextToken(); // value
			session.setStartAt(parser.getLongValue());

			parser.nextToken(); // notes
			if (!parser.getCurrentName().equals(NOTES)) {
				throw new IllegalStateException("Invalid JSON: Expected notes, but was " + parser.getCurrentName());
			}
			parser.nextToken(); // value
			session.setNotes(parser.getText());

			parser.nextToken(); // subject
			if (!parser.getCurrentName().equals(SUBJECT)) {
				throw new IllegalStateException("Invalid JSON: Expected subject, but was " + parser.getCurrentName());
			}
			parser.nextToken(); // value
			session.setSubject(parser.getText());

			parser.nextToken(); // position
			if (!parser.getCurrentName().equals(POSITION)) {
				throw new IllegalStateException("Invalid JSON: Expected position, but was " + parser.getCurrentName());
			}
			parser.nextToken(); // value
			session.setPosition(parser.getText());

			return session;
		} finally {
			try {
				parser.close();
			} catch (Exception e) {
			}
		}

	}

	/**
	 * Creates the beginning of a new file.
	 * 
	 * @param session
	 * @param out
	 * @throws IOException
	 */
	public void createStream(Session session) throws IOException {
		currentSession = session;
		entryCounter = 0;

		File sessionFile = fileHandler.getNewFileForSession(session);
		generator = factory.createGenerator(new FileOutputStream(sessionFile));

		generator.writeStartObject();

		if (session.getSession_id() != null) {
			generator.writeNumberField(SESSION_ID, session.getSession_id());
		}

		generator.writeNumberField(START_AT, session.getStartAt());
		generator.writeStringField(NOTES, session.getNotes());
		generator.writeStringField(SUBJECT, session.getSubject());
		generator.writeStringField(POSITION, session.getPosition());
		generator.writeFieldName(DATA_SETS);
		generator.writeStartArray();
		generator.writeStartObject();
		generator.writeStringField(DEVICE, NEXUS);
		generator.writeFieldName(RELEVANT_DATA);
		generator.writeStartArray();
	}

	private void splitFileIfNeeded() throws IOException {
		entryCounter++;
		if (entryCounter >= ACTApplication.MAX_NUMBER_ENTRY_COUNT_IN_JSON_FILE) {
			entryCounter = 0;
			closeStream();
			createStream(currentSession);
		}
	}

	/**
	 * Writes an entry to the file created by
	 * {@link #createStream(Session, OutputStream)}.
	 * 
	 * @param relevantDataEntry
	 * @param identifier
	 * @throws JsonGenerationException
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public synchronized void write(LocationWithTime relevantDataEntry, String identifier)
			throws JsonGenerationException, IOException, IllegalStateException {
		if (generator == null) {
			throw new IllegalStateException("No session opened!");
		}

		splitFileIfNeeded();

		generator.writeStartObject();
		generator.writeNumberField(TIMESTAMP, relevantDataEntry.getNetworkTimestamp());
		generator.writeFieldName(identifier);

		generator.writeStartArray();
		generator.writeNumber(relevantDataEntry.getLocation().getLongitude());
		generator.writeNumber(relevantDataEntry.getLocation().getLatitude());
		generator.writeNumber(relevantDataEntry.getLocation().getSpeed());
		generator.writeNumber(relevantDataEntry.getLocation().getAccuracy());
		generator.writeEndArray();

		if (relevantDataEntry.getLabel() != null) {
			generator.writeStringField(LABEL, relevantDataEntry.getLabel());
		}

		generator.writeEndObject();
	}

	/**
	 * Writes an entry to the file created by
	 * {@link #createStream(Session, OutputStream)}.
	 * 
	 * @param relevantDataEntry
	 * @param identifier
	 * @throws JsonGenerationException
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public synchronized void write(TripleValue relevantDataEntry, String identifier) throws JsonGenerationException,
			IOException, IllegalStateException {
		if (generator == null) {
			throw new IllegalStateException("No session opened!");
		}

		splitFileIfNeeded();

		generator.writeStartObject();
		if (relevantDataEntry.getNetworkTimestamp() == null) {
			generator.writeNumberField(TIMESTAMP, -1);
		} else {
			generator.writeNumberField(TIMESTAMP, relevantDataEntry.getNetworkTimestamp());
		}

		generator.writeFieldName(identifier);

		generator.writeStartArray();
		generator.writeNumber(relevantDataEntry.getX());
		generator.writeNumber(relevantDataEntry.getY());
		generator.writeNumber(relevantDataEntry.getZ());
		generator.writeEndArray();

		generator.writeNumberField("sensorTime", relevantDataEntry.getSensorTime());
		generator.writeNumberField("timeSinceStartup", relevantDataEntry.getTimeSinceStartup());

		generator.writeEndObject();
	}

	/**
	 * Writes an entry to the file created by
	 * {@link #createStream(Session, OutputStream)}.
	 * 
	 * @param relevantDataEntry
	 * @throws JsonGenerationException
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public synchronized void write(WifiScanResult relevantDataEntry) throws JsonGenerationException, IOException,
			IllegalStateException {
		if (generator == null) {
			throw new IllegalStateException("No session opened!");
		}

		splitFileIfNeeded();

		generator.writeStartObject();
		generator.writeNumberField(TIMESTAMP, relevantDataEntry.getNetworkTimestamp());

		JSONArray wifiJson = new JSONArray();
		for (SingleWifiScanResult singleWifiScanResult : relevantDataEntry.getResults()) {
			try {
				JSONObject singleWifiJson = new JSONObject();
				singleWifiJson.put(BSSID, singleWifiScanResult.getBSSID());
				singleWifiJson.put(SSID, singleWifiScanResult.getSSID());
				singleWifiJson.put(LEVEL, singleWifiScanResult.getLevel());
				singleWifiJson.put(FREQUENCY, singleWifiScanResult.getFrequency());
				wifiJson.put(singleWifiJson);
			} catch (JSONException e) {
				Log.e(JsonStreamer.class.toString(), "Unable to generate wifiScanJsonString:", e);
			}
		}

		generator.writeStringField(Sensors.WIFI_SCAN.toString(), wifiJson.toString().replace(",",";"));

		generator.writeEndObject();
	}

	/**
	 * Writes the end of the file created by
	 * {@link #createStream(Session, OutputStream)} and closes the stream.
	 * 
	 * @throws JsonGenerationException
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public void closeStream() throws JsonGenerationException, IOException, IllegalStateException {
		if (generator == null) {
			throw new IllegalStateException("No session opened!");
		}
		generator.writeEndArray(); // End relevantData array
		generator.writeEndObject(); // End dataSet object
		generator.writeEndArray(); // End dataSets array
		generator.writeEndObject(); // End main object
		generator.flush();
		generator.close();
		generator = null;
	}
}
