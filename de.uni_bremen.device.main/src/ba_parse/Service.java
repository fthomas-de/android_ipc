package ba_parse;

import java.util.ArrayList;

public class Service {

	private String name;
	private ArrayList<ArrayList<String>> intentfilter;

	public Service(String name) {
		this.name = name;
	}

	public ArrayList<ArrayList<String>> getIntentfilter() {
		return intentfilter;
	}

	public void setIntentfilter(ArrayList<ArrayList<String>> intentfilter) {
		this.intentfilter = intentfilter;
	}

	public void addIntentFilter(ArrayList<String> lst) {
		if (intentfilter == null) {
			intentfilter = new ArrayList<ArrayList<String>>();
		}
		intentfilter.add(lst);
	}

	@Override
	public String toString() {
		int len = name.split("\\.").length - 1;
		String s = name.split("\\.")[len];

		if (intentfilter != null) {
			s += intentfilter;
		}

		return s;
	}
}
