/**
 * 
 */
package de.uni_bremen.informatik.act13.android.upload;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;
import de.uni_bremen.informatik.act13.android.ACTApplication;

/**
 * Loads a json file to the server.
 * 
 * @author Timo Stabbert, Roman Gischkowski
 * 
 */
public class Uploader {

	/**
	 * Loads the given file containing json to the server. Returns the response
	 * as a string. If an error occured, {@code null} will be returned and the
	 * error will be logged.
	 * 
	 * @param json
	 *            The file containing json to upload.
	 * @return The response from the server, or {@code null} in case of an
	 *         error.
	 */
	public String loadToServer(File json) {
		return loadToServer(json, null);
	}

	/**
	 * Loads the given file containing json to the server. Returns the response
	 * as a string. If an error occured, {@code null} will be returned and the
	 * error will be logged.
	 * 
	 * @param json
	 *            The file containing json to upload.
	 * @param progressUpdater
	 *            Callback will be called when the upload is complete and the
	 *            uploader is waiting for the response.
	 * @return The response from the server, or {@code null} in case of an
	 *         error.
	 */
	public String loadToServer(File json, ProgressUpdater progressUpdater) {
		HttpURLConnection conn = null;
		try {
			URL url = new URL(ACTApplication.DB_SERVER);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "*/*");
			conn.setConnectTimeout(ACTApplication.CONNECTION_TIMEOUT);
			conn.setReadTimeout(ACTApplication.READ_TIMEOUT);
			try {
				// Senden des Requests
				InputStream in = new FileInputStream(json);
				OutputStream out = conn.getOutputStream();
				byte[] buf = new byte[8192];
				int c = 0;

				while ((c = in.read(buf, 0, buf.length)) > 0) {
					out.write(buf, 0, c);
					out.flush();
				}

				out.close();
				in.close();

			} catch (Exception e) {
				Log.e(Uploader.class.getName(), "Exception while streaming output", e);
				return null;
			}

			if (progressUpdater != null) {
				progressUpdater.waitForRespone();
			}

			// Verarbeiten des InputStreams ( Antwort des Servers )
			try {
				InputStream in = new BufferedInputStream(conn.getInputStream());
				BufferedReader br = new BufferedReader(new InputStreamReader(in));

				StringBuilder sb = new StringBuilder();
				String ln = null;

				while ((ln = br.readLine()) != null) {
					sb.append(ln);
				}

				try {
					br.close();
				} catch (IOException e) {
					// ignore
				}

				Log.d(Uploader.class.getName(), "received: >" + sb.toString() + "<");
				return sb.toString();
			} catch (Exception e) {
				Log.e(Uploader.class.getName(), "Exception while streaming input", e);
				return null;
			}

		} catch (Exception e) {
			Log.e(Uploader.class.getName(), "Exception while setting up connection", e);
			return null;
		} finally {
			if (conn != null)
				conn.disconnect();
		}
	}

	/**
	 * Can be used to get a callback when the upload is done and the uploader is
	 * waiting for the response.
	 * 
	 * @author Roman Gischkowski
	 */
	public interface ProgressUpdater {
		public void waitForRespone();
	}
}
