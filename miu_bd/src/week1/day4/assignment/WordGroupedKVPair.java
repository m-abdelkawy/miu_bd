package week1.day4.assignment;

import java.util.ArrayList;
import java.util.List;

public class WordGroupedKVPair {
	String key;
	List<Integer> value;
	
	public String getKey() {
		return key;
	}
	
	public void add(int val) {
		this.value.add(val);
	}

	public WordGroupedKVPair(String key, Integer value) {
		this.key = key;
		this.value = new ArrayList<Integer>() {{add(value);}};
	}
}
