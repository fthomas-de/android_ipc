package ba_parse;

public class Receiver {

	private String name;
	private String action;
	private String category;

	public Receiver(String name) {
		this.name = name;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		int len = name.split("\\.").length - 1;
		String s =  name.split("\\.")[len];
		
		s += "(" + action + ", " + category + ")";
		
		s = s.replace("[", "");
		s = s.replace("]", "");
		
		return s;
	}

}
