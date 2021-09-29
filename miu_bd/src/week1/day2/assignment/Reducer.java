package week1.day2.assignment;

import java.util.ArrayList;
import java.util.List;

public class Reducer {
	List<WordKeyValPair> lstWord;
	List<WordGroupedKVPair> lstWordGrouped;

	public Reducer(List<WordKeyValPair> lstWord) {
		this.lstWord = new ArrayList<WordKeyValPair>(lstWord);
		this.lstWordGrouped = new ArrayList<WordGroupedKVPair>();
		groupInputList();
	}

	void reduce(String key, List<Integer> lstCount) {
		int sum = 0;
		for (int i = 0; i < lstCount.size(); i++) {
			sum += lstCount.get(i);
		}

		String output = String.format("< %s, %d >", key, sum);
		System.out.println(output);
	}

	private void groupInputList() {
		for (int i = 0; i < lstWord.size(); i++) {
			boolean added = false;
			for (int j = 0; j < lstWordGrouped.size(); j++) {
				System.out.println(lstWordGrouped.get(j).getKey());
				System.out.println(lstWord.get(i).getKey());
				if (lstWordGrouped.get(j).getKey().equalsIgnoreCase(lstWord.get(i).getKey())) {
					lstWordGrouped.get(j).value.add(1);
					added = true;
				}
			}
			if (!added) {
				lstWordGrouped.add(new WordGroupedKVPair(lstWord.get(i).key, 1));
			}
		}
	}
}
