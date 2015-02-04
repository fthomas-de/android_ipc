/**
 * 
 */
package de.uni_bremen.informatik.act13.android.json;

import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * 
 * @author Roman Gischkowski
 * 
 */
public class LabelsGenerator {

	public String toJson(Map<Long, String> labels, Integer sessionId) {
		if (sessionId == null) {
			sessionId = -1;
		}
		try {
			JSONObject json = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			for (Entry<Long, String> label : labels.entrySet()) {
				JSONObject jsonLabel = new JSONObject();
				jsonLabel.put("start_at", label.getKey());
				jsonLabel.put("name", label.getValue());
				jsonLabel.put("session_id", sessionId);
				jsonArray.put(jsonLabel);
			}
			json.put("labels", jsonArray);
			return json.toString();
		} catch (JSONException e) {
			throw new IllegalStateException(e);
		}
	}
}
