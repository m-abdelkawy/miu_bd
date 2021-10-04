package assignments.w1.w1d4.problem2;


import java.util.TreeMap;

/**
 * The Mapper class is to simulate the Mapper in hadoob
 * 
 * @author Mohammed Abdelkawy
 * @version 1.0
 * @since 2021-09-30
 */
public class Mapper {
	TreeMap<String, Pair<Integer, Integer>> hCharLenCount;

	// private List<Pair<String, Integer>> lstPair;
	private String mapperInput;

	public Mapper() {
		//this.lstPair = new ArrayList<Pair<String, Integer>>();
	}

	public Mapper(InputSplit inputSplit) {
		this();
		this.mapperInput = inputSplit.getInput();
		initialize();
	}

	void setMapperInput(String mapperInput) {
		this.mapperInput = mapperInput;
	}

	String getMapperInput() {
		return mapperInput;
	}

	TreeMap<String, Pair<Integer, Integer>> getMap() {
		return hCharLenCount;
	}

	void initialize() {
		hCharLenCount = new TreeMap<String, Pair<Integer, Integer>>();
	}

	/**
	 * The record is one word from the input file The map method maps it to a Pair
	 * object (word, 1) The map method emits a key-value pair
	 * 
	 * @param record a string (a word from the input file)
	 */
	void map(String record) {
		if (record != null && record != "") {
			if (inMapperCombine(record))
				return;
			String key = String.valueOf(record.charAt(0));
			Pair<Integer, Integer> value = new Pair<Integer, Integer>(record.length(), 1);
			hCharLenCount.put(key, value);
		}
	}

	private boolean inMapperCombine(String record) {
		String key = String.valueOf(record.charAt(0));
		if (hCharLenCount.containsKey(key)) {
			Pair<Integer, Integer> pair = hCharLenCount.get(key);
			// accumulate length
			pair.setKey(pair.getKey() + record.length());
			// accumulate count
			pair.setValue(pair.getValue() + 1);
			
			return true;
		}
		return false;
	}

}
