package ba_parse;

import java.util.ArrayList;

public class Communication {
	
	private boolean explicite = false;
	private int values;
	private String src;
	private String method;
	private String methodClass;
	private ArrayList<ArrayList<String>> icc; // package/class or action/category/extras (idx = 0..2)
	
	public Communication(String src) {
		this.src = src;
	}
	
	public ArrayList<ArrayList<String>> getIcc() {
		return icc;
	}

	public void setIcc(ArrayList<ArrayList<String>> icc) {
		this.icc = icc;
	}

	public void addIcc(ArrayList<String> lst) {
		if (icc == null) {
			icc = new ArrayList<ArrayList<String>>();
		}
		icc.add(lst);
	}

	public String getMethod() {
		String[] lst = method.split("/");
		int size = lst.length;
		String m = lst[size-1];
		
		return m;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public boolean isExplicite() {
		return explicite;
	}

	public void setExplicite(boolean explicite) {
		this.explicite = explicite;
	}

	public String getMethodClass() {
		return methodClass;
	}

	public void setMethodClass(String methodClass) {
		this.methodClass = methodClass;
	}

	public int getValues() {
		return values;
	}

	public void setValues(int values) {
		this.values = values;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	@Override
	public String toString() {
		return method + "(" + values + ", " + explicite + ")" + ", " + icc;
	}
}
