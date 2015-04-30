package ba_parse;

import java.util.ArrayList;

public class StringParser {

	public ArrayList<String> getParts(String input, String regex) {
		ArrayList<String> result = new ArrayList<String>();
		String[] lst = input.split(regex);
		int size = lst.length;
		
		String last = lst[size-1];

		int inputLenght = input.length();
		int lastLenght = last.length();
		String first = input.substring(0, inputLenght - lastLenght);
		
		result.add(first);
		result.add(last);
		
		return result;
	}
}
