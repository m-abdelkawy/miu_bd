package week1.day4.assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reducer {
	List<WordKeyValPair> lstWord;
	List<WordGroupedKVPair> lstWordGrouped;
	int reducerid;

	static int reducerCount = -1;

	public Reducer(List<WordKeyValPair> lstWord) {
		this.lstWord = new ArrayList<WordKeyValPair>(lstWord);
		this.lstWordGrouped = new ArrayList<WordGroupedKVPair>();
		groupInputList();
		reducerCount++;
		reducerid = reducerCount;
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
				// System.out.println(lstWordGrouped.get(j).getKey());
				// System.out.println(lstWord.get(i).getKey());
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

	void displayReduceInput() {
		System.out.println(String.format("%s %d %s", "Reducer", reducerid, "input"));
		for (int i = 0; i < lstWordGrouped.size(); i++) {
			System.out.println(String.format("< %s, %s >", lstWordGrouped.get(i).key,
					Arrays.toString(lstWordGrouped.get(i).value.stream().mapToInt(x -> x).toArray())));
		}
		System.out.println("*****************************************");
	}
}
