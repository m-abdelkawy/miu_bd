package week1.day2.assignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Program {
	
	static final String FILE_PATH = "src\\week1\\day1\\testDataForW1D1.txt";

	public static void main(String[] args) throws FileNotFoundException {
		
		
		
		FastScanner scanner = new FastScanner(new FileInputStream(FILE_PATH));
		Mapper mapper = new Mapper();

		String token;
		while ((token = scanner.next()) != null) {
			
			token = trimToken(token);
			
			String[] arr = null;
			
			//split hyphen words
			if (token.contains("-"))
				arr = token.split("-");

			if (arr != null) {
				for (int i = 0; i < arr.length; i++) {
					mapper.add(new WordKeyValPair(arr[i].toLowerCase(), 1));
				}
			} else
				mapper.add(new WordKeyValPair(token.toLowerCase(), 1));
		}

		mapper.display();
		
		//reducer
		Reducer reducer = new Reducer(mapper.lstWord);
		for (int i = 0; i < reducer.lstWordGrouped.size(); i++) {
			WordGroupedKVPair reducerInput = reducer.lstWordGrouped.get(i);
			reducer.reduce(reducerInput.key, reducerInput.value);
		}
	}

	static String trimToken(String token) {
		if (token.contains("."))
			token = token.replaceAll("\\.", "");

		if (token.contains("\""))
			token = token.replaceAll("\"", "");
		
		return token;
	}
	
	
}
