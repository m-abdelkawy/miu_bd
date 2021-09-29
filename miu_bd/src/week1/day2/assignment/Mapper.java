package week1.day2.assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mapper<T> {
	List<WordKeyValPair> lstWord;
	public Mapper() {
		this.lstWord = new ArrayList<WordKeyValPair>();
	}
	void add(WordKeyValPair word) {
		if(word.key != null && word.key != "")
			this.lstWord.add(word);
	}
	
	void display() {
		this.sort();
		for (int i = 0; i < lstWord.size(); i++) {
			WordKeyValPair wordElm = lstWord.get(i);
			System.out.println("Key: " + wordElm.key + ",   Value: " + wordElm.value);
		}
	}
	
	private void sort() {
		Collections.sort(this.lstWord);
	}
}
