package assignments.w1.d2.partB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reducer {

	private List<Pair<String, List<Integer>>> lstGroupByPair;
	private List<Pair<String, Integer>> lstReducerOutput;

	public Reducer() {
		this.lstGroupByPair = new ArrayList<Pair<String,List<Integer>>>();
		this.lstReducerOutput = new ArrayList<Pair<String,Integer>>();
	}
	
	public List<Pair<String, List<Integer>>> getLstGroupByPair() {
		return lstGroupByPair;
	}
	
	public List<Pair<String, Integer>> getLstReducerOutput() {
		return lstReducerOutput;
	}
	
	/**
	 * The reduce method is called for each key value pair in the partition from mapper
	 * its signature must match the output key value pair grouped and sorted from the mapper output
	 * @param key
	 * @param lstCount
	 */
	void reduce(String key, List<Integer> lstCount) {
		int sum = 0;
		for (Integer integer : lstCount) {
			sum+= integer;
		}
		
		//Emit(Key, value)
		this.lstReducerOutput.add(new Pair<String, Integer>(key, sum));
	}

	/**
	 * prepares the reducer input i.e. the lstGroupByPair grouping count in one list
	 * @param lstPair the sorted kay-value pair list from the mapper
	 */
	void groupPairs(List<Pair<String, Integer>> lstPair) {
		lstPair.forEach(p->{
			for (Pair<String, List<Integer>> gp : lstGroupByPair) {
				if(gp.getKey().equalsIgnoreCase(p.getKey())) {
					gp.getValue().add(1);
					break;
				}
			}
			lstGroupByPair.add(new Pair<String, List<Integer>>(p.getKey(), new ArrayList<Integer>() {{add(1);}}));
		});
	}
	
	/**
	 * 
	 * @return the reducer input as string
	 */
	String getLstGroupByPairAsString() {
		this.sortLstGroupByPair();
		StringBuilder sb = new StringBuilder();
		lstGroupByPair.forEach(pair -> {
			sb.append(pair + System.getProperty("line.separator"));
		});
		return sb.toString();
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
		Collections.sort(this.lstGroupByPair, (p1, p2)-> p1.getKey().compareTo(p2.getKey()));
	}
	
	private void sortLstReducerOutput() {
		Collections.sort(this.lstReducerOutput, (p1, p2)-> p1.getKey().compareTo(p2.getKey()));
	}
}
