package assignments.w1.d1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * The Mapper class is to simulate the Mapper in hadoob
 * 
 * @author Mohammed Abdelkawy
 * @version 1.0
 * @since 2021-09-30
 */
public class Mapper {
	List<Pair<String, Integer>> lstPair;

	public Mapper() {
		this.lstPair = new ArrayList<Pair<String, Integer>>();
	}

	/**
	 * The record is one word from the input file The map method maps it to a Pair
	 * object (word, 1) The map method emits a key-value pair
	 * 
	 * @param record a string (a word from the input file)
	 */
	void map(String record) {
		if (record != null && record != "")
			this.lstPair.add(new Pair<String, Integer>(record, 1));
	}

	/**
	 * Displays the key-value pairs emitted by the map method
	 */
	void display() {
		this.sort();
		for (int i = 0; i < lstPair.size(); i++) {
			System.out.println(lstPair.get(i));
		}
	}

	String getLstPairAsString() {
		this.sort();
		StringBuilder sb = new StringBuilder();
		lstPair.forEach(pair -> {
			sb.append(pair + System.getProperty("line.separator"));
		});
		return sb.toString();
	}
	
	private void sort() {
		Collections.sort(this.lstPair, (p1, p2)-> p1.getKey().compareTo(p2.getKey()));
	}
}
