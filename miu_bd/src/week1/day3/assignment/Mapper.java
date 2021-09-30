package week1.day3.assignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Mapper {
	static int mapperCount = -1;
	int mapperid;
	FastScanner scanner;
	private StringBuilder mapperInput;

	List<WordKeyValPair> lstWord;

	public Mapper(String filepath) throws FileNotFoundException {
		this.lstWord = new ArrayList<WordKeyValPair>();
		mapperCount++;
		mapperid = mapperCount;
		mapperInput = new StringBuilder();

		scanner = new FastScanner(new FileInputStream(filepath));
		scanFilePopulateLstWord();
	}

	private void scanFilePopulateLstWord() {
		String token;
		while ((token = scanner.next()) != null) {
			mapperInput.append(token + " ");
			
			if (token.substring(token.length() - 1).equals("."))
				token = trimToken(token);

			String[] arr1 = null;
			//String[] arr2 = null;

			// split hyphen words
			if (token.contains("-"))
				arr1 = token.split("-");

			//if (token.contains("."))
				//arr2 = token.split(".");

			if (arr1 != null) {
				for (int i = 0; i < arr1.length; i++) {
					this.add(new WordKeyValPair(arr1[i].toLowerCase(), 1));
				}
			}
			/*if (arr2 != null) {
				for (int i = 0; i < arr2.length; i++) {
					this.add(new WordKeyValPair(arr2[i].toLowerCase(), 1));
				}
			} */
			else	
				this.add(new WordKeyValPair(token.toLowerCase(), 1));
		}
	}

	void add(WordKeyValPair word) {
		if (word.key != null && word.key != "")
			this.lstWord.add(word);
	}

	void display() {
		this.sort();
		for (int i = 0; i < lstWord.size(); i++) {
			WordKeyValPair wordElm = lstWord.get(i);
			System.out.println("Key: " + wordElm.key + ",   Value: " + wordElm.value);
		}
	}

	void displayMapperOutput() {
		System.out.println(String.format("%s %d %s", "Mapper", mapperid, "output"));
		for (int i = 0; i < lstWord.size(); i++) {
			System.out.println(String.format("< %s, %s >", lstWord.get(i).key, lstWord.get(i).value));
		}
		System.out.println("*****************************************");
	}

	void displayMapperInput() {
		System.out.println(String.format("Mapper %d Input", mapperid));
		System.out.println(this.mapperInput.toString());
		System.out.println("*****************************************");
	}

	int getPartition(String key, int numReducers) {
		return Math.abs(key.hashCode()) % numReducers;
	}

	private String trimToken(String token) {
		if (token.contains("."))
			token = token.replaceAll("\\.", "");

		if (token.contains("\""))
			token = token.replaceAll("\"", "");

		return token;
	}

	private void sort() {
		Collections.sort(this.lstWord);
	}
}
