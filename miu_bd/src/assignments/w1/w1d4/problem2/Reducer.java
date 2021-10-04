package assignments.w1.w1d4.problem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Reducer {

	private TreeMap<String, List<Pair<Integer, Integer>>> lstGroupByPair;
	private List<Pair<String, Double>> lstReducerOutput;

	public Reducer() {
		this.lstGroupByPair = new TreeMap<String, List<Pair<Integer, Integer>>>();
		this.lstReducerOutput = new ArrayList<Pair<String, Double>>();
	}

	public TreeMap<String, List<Pair<Integer, Integer>>> getLstGroupByPair() {
		return lstGroupByPair;
	}

	public List<Pair<String, Double>> getLstReducerOutput() {
		return lstReducerOutput;
	}

	/**
	 * The reduce method is called for each key value pair in the partition from
	 * mapper its signature must match the output key value pair grouped and sorted
	 * from the mapper output
	 * 
	 * @param key
	 * @param lstCount
	 */
	void reduce(String key, List<Pair<Integer, Integer>> lstLenCountPair) {
		double sum = 0.0;
		int count = 0;
		for (Pair<Integer, Integer> pair : lstLenCountPair) {
			sum += pair.getKey();
			count += pair.getValue();
		}
		
		

		// Emit(Key, value)
		this.lstReducerOutput.add(new Pair<String, Double>(key, sum/count));
	}

	/**
	 * prepares the reducer input i.e. the lstGroupByPair grouping count in one list
	 * 
	 * @param lstPair the sorted kay-value pair list from the mapper
	 */
	void groupPairs(TreeMap<String, List<Pair<Integer, Integer>>> lstPair) {
		lstGroupByPair = lstPair;
		/*
		 * for (Map.Entry<String, List<Pair<Integer, Integer>>> entry :
		 * lstPair.entrySet()) {
		 * 
		 * int sumLength = 0; int totalCount = 0;
		 * 
		 * List<Pair<Integer, Integer>> lstKeyValPair = entry.getValue();
		 * 
		 * for (int i = 0; i < lstKeyValPair.size(); i++) { sumLength +=
		 * lstKeyValPair.get(i).getKey(); totalCount += lstKeyValPair.get(i).getValue();
		 * }
		 * 
		 * if (lstGroupByPair.containsKey(entry.getKey())) {
		 * lstGroupByPair.get(entry.getKey()).setKey(sumLength);
		 * lstGroupByPair.get(entry.getKey()).setValue(totalCount); } else {
		 * Pair<Integer, Integer> kvPair = new Pair<Integer, Integer>(sumLength,
		 * totalCount); lstGroupByPair.put(entry.getKey(), kvPair);
		 * 
		 * }
		 * 
		 * }
		 */

		/*
		 * lstPair.forEach(p -> { boolean exists = false; for (Pair<String,
		 * List<Integer>> gp : lstGroupByPair) { if
		 * (gp.getKey().equalsIgnoreCase(p.getKey())) { gp.getValue().add(p.getValue());
		 * exists = true; break; } } if (!exists) lstGroupByPair.add(new Pair<String,
		 * List<Integer>>(p.getKey(), new ArrayList<Integer>() { { add(p.getValue()); }
		 * })); });
		 */
	}

	/**
	 * 
	 * @return the reducer input as string
	 */
	String getLstGroupByPairAsString() {
		/*
		 * this.sortLstGroupByPair(); StringBuilder sb = new StringBuilder();
		 * lstGroupByPair.forEach(pair -> { sb.append(pair +
		 * System.getProperty("line.separator")); }); return sb.toString();
		 */
		return null;
	}

	/**
	 * 
	 * @return the reducer output as string
	 */
	String getLstReducerOutputAsString() {
		this.sortLstReducerOutput();
		StringBuilder sb = new StringBuilder();
		lstReducerOutput.forEach(pair -> {
			sb.append(pair + System.getProperty("line.separator"));
		});
		return sb.toString();
	}

	private void sortLstGroupByPair() {
		// Collections.sort(this.lstGroupByPair, (p1, p2) ->
		// p1.getKey().compareTo(p2.getKey()));
	}

	private void sortLstReducerOutput() {
		// Collections.sort(this.lstReducerOutput, (p1, p2) ->
		// p1.getKey().compareTo(p2.getKey()));
	}
}
