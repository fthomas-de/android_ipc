/**
 * 
 */
package de.uni_bremen.informatik.act13.android.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import de.uni_bremen.informatik.act13.android.ACTApplication;
import de.uni_bremen.informatik.act13.android.R;
import de.uni_bremen.informatik.act13.android.model.Session;
import de.uni_bremen.informatik.act13.android.upload.Uploader.ProgressUpdater;

/**
 * Activity for the list of locally stored sessions and the upload of these. It
 * is also possible to change the sessionId or delete session.
 * 
 * @author Roman Gischkowski
 * 
 */
public class UploadActivity extends Activity {

	private ACTApplication app;
	private FileArrayAdapter adapter;
	private Button modifyId;
	private TextView progressText;
	private Button delete;
	private Button upload;
	private Button toggleChecked;
	private ListView listView;
	private WakeLock wakeLock;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		wakeLock.acquire();
		super.onResume();
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		wakeLock.release();
		super.onPause();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload);
		
		PowerManager pm = (PowerManager)UploadActivity.this.getSystemService(Context.POWER_SERVICE);
		wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, ModifyIdAsync.class.toString());

		toggleChecked = (Button) findViewById(R.id.button_toggle_checked);
		progressText = (TextView) findViewById(R.id.progress_upload_text);
		modifyId = (Button) findViewById(R.id.button_modify_id);
		delete = (Button) findViewById(R.id.button_delete_data);
		upload = (Button) findViewById(R.id.button_upload_data);
		listView = (ListView) findViewById(R.id.listview_upload);

		app = (ACTApplication) getApplication();
		adapter = new FileArrayAdapter(this, android.R.layout.simple_list_item_1);
		listView.setAdapter(adapter);
		adapter.addAll(app.getFileHandler().getAllSessions());

		toggleChecked.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				adapter.toggleCheckAll();
				adapter.notifyDataSetChanged();
			}
		});

		delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				new DeleteAsnyc().execute(adapter.getCheckedEntries());
			}
		});

		modifyId.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new ModifyIdAsync().execute(adapter.getCheckedEntries());
			}
		});

		upload.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				new UploadAsync().execute(adapter.getCheckedEntries());
			}
		});
	}

	/**
	 * Reloads the files. (needed when a file was removed or added)
	 */
	public void reloadFiles() {
		List<List<File>> files = app.getFileHandler().getAllSessions();
		adapter.clear();
		adapter.addAll(files);
	}

	private void displayMessage(final String message) {
		UploadActivity.this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				progressText.setText(message);
			}
		});
	}

	/**
	 * Wrapper class for AsyncTask. Just exists for more readable code.
	 * 
	 * @author Roman Gischkowski
	 */
	private class UploadAsync {
		private AsyncTask<List<File>, String, String> task;

		private int uploadSingleFile(File file, final int numCurrent, final int numMax, boolean firstTry) {
			displayMessage("uploading - uploading to server(" + numCurrent + " von " + numMax + " | Retry: " + !firstTry + "). PLEASE WAIT!");
			String answer = app.getUploader().loadToServer(file, new ProgressUpdater() {
				@Override
				public void waitForRespone() {
					displayMessage("uploading - waiting for respone from server(" + numCurrent + " von " + numMax
							+ "). PLEASE WAIT!");
				}
			});
			if (answer == null) {
				if (firstTry) {
					return uploadSingleFile(file, numCurrent, numMax, false);
				}
				displayMessage("Error: answer from server was null (" + numCurrent + " von " + numMax + ")");
				return -1;
			}
			try {
				return Integer.parseInt(answer);
			} catch (Exception e) {
				displayMessage("Error: answer from server was not a number but " + answer);
				return -1;
			}

		}

		private File modifySingleFile(File file, int id, int numCurrent, int numMax) {
			File tempFile = app.getFileHandler().getTempFile();
			try {
				app.getJsonStreamer().copyAndModifySessionId(new FileInputStream(file), id,
						new FileOutputStream(tempFile));
			} catch (Exception e) {
				displayMessage("Error: unable to modify session id for file " + file.getName() + " error message was: "
						+ e.getMessage() + " (" + e.getClass() + ") See log for details");
				Log.e(UploadActivity.class.toString(), "Error while modify id:", e);
				return null;
			}
			displayMessage("uploading - modified id (" + numCurrent + " von " + numMax + "). PLEASE WAIT!");
			return tempFile;
		}

		public UploadAsync() {
			task = new AsyncTask<List<File>, String, String>() {
				@Override
				protected String doInBackground(List<File>... params) {
					try {
						List<File> sessionFiles = params[0];
//						Session session = app.getJsonStreamer().readStreamHeader(
//								new FileInputStream(sessionFiles.get(0)));
//						if (session.getSession_id() == null) {
//							displayMessage("Aborted because of missing session ID");
//							return null;
//						}
						StringBuilder result = new StringBuilder();
						int id = -1;
						for (int i = 0; i < sessionFiles.size(); i++) {
							File sessionFile = sessionFiles.get(i);

							if (id != -1) {
								File tempFile = modifySingleFile(sessionFile, id, i + 1, sessionFiles.size());
								if (tempFile == null) {
									return null;
								}
								sessionFile = tempFile;
							}

							id = uploadSingleFile(sessionFile, i + 1, sessionFiles.size(), true);
							if (id == -1) {
								return null;
							}

							result.append(id);
							result.append("|");
						}
						return result.toString();
					} catch (Exception e) {
						displayMessage("Exception while uploading: " + e.getMessage() + " (" + e.getClass() + ") See log for details");
						Log.e(UploadActivity.this.toString(), "Error while uploading:", e);
						return null;
					}
				}

				@Override
				protected void onProgressUpdate(String... values) {
					progressText.setText(values[0]);
				}

				@Override
				protected void onPostExecute(String result) {
					if (result != null) {
						progressText.setText("upload successfull! Answer was: " + result);
					}
				}
			};
		}

		@SuppressWarnings("unchecked")
		public void execute(List<List<File>> files) {
			progressText.setText("uploading...");
			if (files.size() != 1) {
				Toast.makeText(UploadActivity.this, "Only upload of single session!", Toast.LENGTH_SHORT).show();
				progressText.setText("upload aborted");
				return;
			}
			task.execute(files.get(0));

		}

	}

	/**
	 * Wrapper class for AsyncTask. Just exists for more readable code.
	 * 
	 * @author Roman Gischkowski
	 */
	private class DeleteAsnyc {

		private AsyncTask<List<List<File>>, String, List<File>> task;

		public DeleteAsnyc() {
			progressText.setText("deleting...");

			task = new AsyncTask<List<List<File>>, String, List<File>>() {

				@Override
				protected List<File> doInBackground(List<List<File>>... params) {
					int i = 0;
					List<File> unableToDelete = new ArrayList<File>();
					for (List<File> files : params[0]) {
						for (File file : files) {
							i++;
							if (!file.delete()) {
								unableToDelete.add(file);
							}
							publishProgress("deleted " + i + " files");
						}
					}
					return unableToDelete;
				}

				@Override
				protected void onProgressUpdate(String... values) {
					progressText.setText(values[0]);
				}

				@Override
				public void onPostExecute(List<File> unableToDelete) {
					if (unableToDelete.size() == 0) {
						progressText.setText("deleted all files");
					} else {
						StringBuilder str = new StringBuilder("Unable to delete: \n");
						for (File entry : unableToDelete) {
							str.append(entry.getName());
							str.append("\n");
						}
						progressText.setTag("unable to delete: ");
					}
					reloadFiles();
				}

			};
		}

		@SuppressWarnings("unchecked")
		public void execute(List<List<File>> files) {
			task.execute(files);

		}
	}

	/**
	 * Wrapper class for AsyncTask. Just exists for more readable code.
	 * 
	 * @author Roman Gischkowski
	 */
	private class ModifyIdAsync {

		private AsyncTask<List<File>, String, Void> task;

		public ModifyIdAsync() {
			task = new AsyncTask<List<File>, String, Void>() {

				private Session session;
				private List<File> oldFiles;

				@Override
				protected Void doInBackground(List<File>... params) {
					publishProgress("modify id - reading current session id");
					oldFiles = params[0];
					try {
						session = app.getJsonStreamer().readStreamHeader(new FileInputStream(oldFiles.get(0)));
					} catch (IOException e) {
						Log.e(UploadActivity.this.toString(), "Error while reading session header!", e);
						publishProgress("modify id canceled! Error while reading session header. See log for details.");
						return null;
					}

					UploadActivity.this.runOnUiThread(new Runnable() {
						public void run() {
							AlertDialog.Builder alert = new AlertDialog.Builder(UploadActivity.this);

							alert.setTitle("Modify session ID");
							alert.setMessage("Old id was: " + session.getSession_id());

							// Set an EditText view to get user input
							final EditText input = new EditText(UploadActivity.this);
							input.setRawInputType(InputType.TYPE_CLASS_NUMBER);
							alert.setView(input);

							alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int whichButton) {

									AsyncTask<String, String, Boolean> task = new AsyncTask<String, String, Boolean>() {

										@Override
										protected Boolean doInBackground(String... params) {
											session.setSession_id(Integer.parseInt(params[0]));
											publishProgress("modifing id - PLEASE WAIT!");
											for (File oldFile : oldFiles) {
												File newFile = app.getFileHandler().getNewFileForSession(session);
												try {
													app.getJsonStreamer().copyAndModifySessionId(
															new FileInputStream(oldFile), session.getSession_id(),
															new FileOutputStream(newFile));
												} catch (IOException e) {
													Log.e(UploadActivity.this.toString(),
															"Error while modify and copy session!", e);
													return false;
												}
											}
											return true;
										}

										@Override
										protected void onPostExecute(Boolean result) {
											if (result == null || !result) {
												progressText
														.setText("modify id canceled! Error while modify and copy session. See log for details.");
											} else {
												progressText.setText("modified id successfully");
											}
											reloadFiles();

										}

										@Override
										protected void onProgressUpdate(String... values) {
											progressText.setText(values[0]);
										}
									};
									task.execute(input.getText().toString());
								}
							});

							alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int whichButton) {
									progressText.setText("modify id canceled");
									// Canceled.
								}
							});

							alert.show();
						}
					});

					return null;
				}

				@Override
				protected void onProgressUpdate(String... values) {
					progressText.setTag(values[0]);
				}

			};
		}

		@SuppressWarnings("unchecked")
		public void execute(List<List<File>> files) {
			progressText.setText("modify id...");
			if (files.size() != 1) {
				Toast.makeText(UploadActivity.this, "Only modification of single session!", Toast.LENGTH_SHORT).show();
				progressText.setText("modify id aborted");
				return;
			}

			task.execute(files.get(0));
		}
	}
}
