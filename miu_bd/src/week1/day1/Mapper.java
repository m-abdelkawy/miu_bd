package week1.day1;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
	List<Word> lstWord;
	public Mapper() {
		this.lstWord = new ArrayList<Word>();
	}
	void add(Word word) {
		if(word.key != null && word.key != "")
			this.lstWord.add(word);
	}
	
	void display() {
		for (int i = 0; i < lstWord.size(); i++) {
			Word wordElm = lstWord.get(i);
			System.out.println("Key: " + wordElm.key + ",   Value: " + wordElm.value);
		}
	}
}
