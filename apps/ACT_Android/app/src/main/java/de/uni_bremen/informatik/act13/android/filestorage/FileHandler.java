/**
 * 
 */
package de.uni_bremen.informatik.act13.android.filestorage;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import de.uni_bremen.informatik.act13.android.model.Session;

/**
 * Main class for this application to interact with the filesystem.
 * 
 * @author Roman Gischkowski
 * 
 */
public class FileHandler {

	private final static String ACTDATA_DIR = "actdata";
	private final static String ACTDATA_DIVIDER = "_";
	private final static String ACTDATA_JSON = ".json";

	private Context context;

	public FileHandler(Context context) {
		this.context = context;
	}
	
	private List<File> getAllFilesForSession(File firstFile) {
		return getFileList(new SingleSessionFilterByFile(firstFile));
	}
	
	public List<List<File>> getAllSessions() {
		List<File> firstFiles = getFileList(new FirstFileFilter());
		List<List<File>> allSessions = new ArrayList<List<File>>();
		for (File firstFile : firstFiles) {
			allSessions.add(getAllFilesForSession(firstFile));
		}
		return allSessions;
	}
	
	public File getTempFile() {
		File actDataDir = context.getExternalFilesDir(ACTDATA_DIR);
		return new File(actDataDir, "temp");
	}
	
	public File getLabelsFileForSession(Session session) {
		File actDataDir = context.getExternalFilesDir(ACTDATA_DIR);
		return new File(actDataDir, "labeling-for-" + session.getNotes() + "-from-" + session.getStartAt() + ACTDATA_JSON);
	}
	
	public File getNewFileForSession(Session session) {
		List<File> existingFiles = getFileList(new SingleSessionFilter(session));
		File actDataDir = context.getExternalFilesDir(ACTDATA_DIR);
		String filename = getIdentifierString(session) + existingFiles.size() + ACTDATA_JSON;
		return new File(actDataDir, filename);
	}
	
	private String getIdentifierString(Session session) {
		StringBuilder identifier = new StringBuilder();
		identifier.append(session.getNotes());
		identifier.append(ACTDATA_DIVIDER);
		identifier.append(session.getSubject());
		identifier.append(ACTDATA_DIVIDER);
		identifier.append(session.getStartAt());
		identifier.append(ACTDATA_DIVIDER);
		identifier.append((session.getSession_id() == null ? "null" : session.getSession_id()));
		identifier.append(ACTDATA_DIVIDER);
		return identifier.toString();
	}

	private List<File> getFileList(FilenameFilter filter) {
		File actDataDir = context.getExternalFilesDir(ACTDATA_DIR);
		List<File> files = new ArrayList<File>();
		for (File file : actDataDir.listFiles(filter)) {
			files.add(file);
		}
		return files;
	}
	
	private class SingleSessionFilterByFile implements FilenameFilter {
		private File file;
		public SingleSessionFilterByFile(File file) {
			this.file = file;
		}
		@Override
		public boolean accept(File dir, String filename) {
			String prefix = file.getName();
			prefix = prefix.substring(0, prefix.lastIndexOf(ACTDATA_DIVIDER));
			return filename.startsWith(prefix);
		}
	}
	
	private class SingleSessionFilter implements FilenameFilter {
		private Session session;
		public SingleSessionFilter(Session session) {
			this.session = session;
		}
		@Override
		public boolean accept(File dir, String filename) {
			return filename.startsWith(getIdentifierString(session));
		}
	}

	private class FirstFileFilter implements FilenameFilter {
		@Override
		public boolean accept(File arg0, String arg1) {
			return arg1.endsWith(ACTDATA_DIVIDER + 0 + ACTDATA_JSON);
		}
	}
}
