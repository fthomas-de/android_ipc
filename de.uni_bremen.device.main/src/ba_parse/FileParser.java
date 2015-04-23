package ba_parse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileParser {

	private String filepath;

	public FileParser(String string) {
		this.filepath = string;
	}

	public ArrayList<Activity> getActivities() {
		final int START = -1;
		final int INIT = 0;

		BufferedReader br;
		ArrayList<Activity> result = new ArrayList<Activity>();

		try {
			br = new BufferedReader(new FileReader(filepath));
			String line;
			int state = START;
			Activity curr_activity = null;
			ArrayList<String> intentfilter = null;

			while ((line = br.readLine()) != null) {
				line = line.trim();

				// get activity block begin
				if (line.startsWith("Activities:")) { // start condition
					state = INIT;
					continue;

				} else if (line.startsWith("Activity Aliases:")) { // end
																	// condition
					break;
				}

				// activity block
				if (state == INIT) {
					if (line.startsWith("Intent filter") || line.equals("")) {
						continue;

					} else if (line.startsWith("Actions:")) {
						String action = line.split(" ")[1]; // TODO was wenn
															// leerzeichen im
															// namen? -> ändern
						intentfilter = new ArrayList<String>();
						intentfilter.add(action);
						curr_activity.addIntentFilter(intentfilter);
						
					} else if (line.startsWith("Categories:")) { // TODO was
																	// wenn
																	// keine cat
						String cat = line.split(" ")[1];
						intentfilter.add(cat);
						
					} else {
						curr_activity = new Activity(line);
						result.add(curr_activity);
					}
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Service> getServices() {
		final int START = -1;
		final int INIT = 0;

		BufferedReader br;
		ArrayList<Service> result = new ArrayList<Service>();

		try {
			br = new BufferedReader(new FileReader(filepath));
			String line;
			int state = START;
			Service curr_service = null;
			ArrayList<String> intentfilter = null;
			
			while ((line = br.readLine()) != null) {
				line = line.trim();

				// get activity block begin
				if (line.startsWith("Services:")) { // start condition
					state = INIT;
					continue;

				} else if (line.startsWith("Receivers:")) { // end condition
					break;
				}

				// activity block
				if (state == INIT) {
					if (line.startsWith("Intent filter") || line.equals("")) {
						continue;

					} else if (line.startsWith("Actions:")) {
						String action = line.split(" ")[1]; // TODO was wenn
															// leerzeichen im
															// namen? -> ändern
						intentfilter = new ArrayList<String>();
						intentfilter.add(action);
						curr_service.addIntentFilter(intentfilter);
						
					} else if (line.startsWith("Categories:")) { // TODO was
																	// wenn
																	// keine cat
						String cat = line.split(" ")[1];
						intentfilter.add(cat);
						
					} else {
						curr_service = new Service(line);
						result.add(curr_service);
					}
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Receiver> getReceiver() {
		final int START = -1;
		final int INIT = 0;

		BufferedReader br;
		ArrayList<Receiver> result = new ArrayList<Receiver>();

		try {
			br = new BufferedReader(new FileReader(filepath));
			String line;
			int state = START;
			Receiver curr_receiver = null;

			while ((line = br.readLine()) != null) {
				line = line.trim();

				// get activity block begin
				if (line.startsWith("Receivers:")) { // start condition
					state = INIT;
					continue;

				} else if (line.startsWith("Providers:")) { // end condition
					break;
				}

				// activity block
				if (state == INIT) {
					if (line.equals("Intent filter:")) {
						continue;
					} else if (line.startsWith("Actions:")) {
						String action = line.split(" ")[1].trim();
						curr_receiver.setAction(action);

					} else if (line.startsWith("Categories:")) { //TODO was wenn keine cat
						String category = line.split(" ")[1].trim();
						curr_receiver.setCategory(category);

					} else {
						curr_receiver = new Receiver(line);
						result.add(curr_receiver);
					}
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Communication> getCommunications() {
		final int START = -1;
		final int INIT = 0;
		boolean running = false;
		boolean first = true;

		BufferedReader br;
		ArrayList<Communication> result = new ArrayList<Communication>();

		try {
			br = new BufferedReader(new FileReader(filepath));
			String line;
			int state = START;
			Communication curr_communication = null;
			ArrayList<String> icc = null;

			while ((line = br.readLine()) != null) {
				line = line.trim();

				// get activity block begin
				if (line.startsWith("The following ICC values were found:")) { // start
																				// condition
					state = INIT;
					continue;
				} // end condition = EOF

				// activity block
				if (state == INIT) {
					if (line.startsWith("-")) {
						if (!line.contains("android/support")) { // icc found
							line = line.substring(1).trim();
							curr_communication = new Communication(line);

							String method;
							// String[] s = line.split("\\(")[0].split("/");
							// int len = s.length - 1;
							// method = s[len];
							method = line.split("\\(")[0];
							curr_communication.setMethod(method);

							String methodClass = line.split("\\(")[0];
							String[] s = methodClass.split("/");
							int size = s.length;
							methodClass = s[size-2];
							curr_communication.setMethodClass(methodClass);
							
							running = true;
							first = true;
						} else {
							running = false;
						}
					} else if (running) {
						icc = new ArrayList<String>();

						if (line.contains("No permission")) {
							continue;

						} else if (line.contains("Type:")) {
							continue;

						} else if (line.trim().equals("") && first) {
							result.add(curr_communication);
							first = false;
							continue;

						} else if (line.contains("Intent Filter value:")) {
							line = line.substring("Intent Filter value: "
									.length());
							line = line.split(" ")[0];
							curr_communication
									.setValues(Integer.parseInt(line));
							continue;
						} else if (line.contains("Intent value:")) {
							line = line.substring("Intent value:".length())
									.trim();
							line = line.split(" ")[0];
							curr_communication
									.setValues(Integer.parseInt(line));
							continue;

						} else if (line.contains("Actions: ")) {
							curr_communication.setExplicite(false);
							// was wenn die action ein komma enthält
							String action = line
									.substring("Actions: ".length()).split(",")[0];
							action = action.replace("[", "");
							action = action.replace("]", "");
							icc.add(action);

							if (line.contains("Categories:")) { //TODO was wenn keine cat
								String cat = line.split("Categories:")[1]
										.trim();
								cat = cat.split(",")[0];
								cat = cat.replace("[", "");
								cat = cat.replace("]", "");
								icc.add(cat);

							} else {
								icc.add(null);
							}

							if (line.contains("Extras:")) {
								String extras = line.split("Extras:")[1].trim();
								extras = extras.split(",")[0];
								extras = extras.replace("[", "");
								extras = extras.replace("]", "");
								icc.add(extras);

							} else {
								icc.add(null);
							}

							curr_communication.addIcc(icc);

						} else if (line.contains("Action: ")) {
							curr_communication.setExplicite(false);
							// was wenn die action ein komma enthält
							String action = line.substring("Action: ".length())
									.split(",")[0];
							action = action.replace("[", "");
							action = action.replace("]", "");
							icc.add(action);

							if (line.contains("Categories:")) { //TODO was wenn keine cat
								String cat = line.split("Categories:")[1]
										.trim();
								cat = cat.split(",")[0];
								cat = cat.replace("[", "");
								cat = cat.replace("]", "");
								icc.add(cat);

							} else {
								icc.add(null);
							}

							if (line.contains("Extras:")) {
								String extras = line.split("Extras:")[1].trim();
								extras = extras.split(",")[0];
								extras = extras.replace("[", "");
								extras = extras.replace("]", "");
								icc.add(extras);

							} else {
								icc.add(null);
							}

							curr_communication.addIcc(icc);

						} else if (line.startsWith("Package:")) {
							curr_communication.setExplicite(true);
							String pkg = line.substring("Package:".length())
									.trim();
							pkg = pkg.split(",")[0];
							icc.add(pkg);

							if (line.contains("Class:")) {
								String cls = line.split("Class:")[1];
								cls = cls.replace(",", "");
								icc.add(cls);
							}

							curr_communication.addIcc(icc);
						}
					}

				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}
