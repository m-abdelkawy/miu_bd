package assignments.w1.w1d4.problem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class WordLength {
	private InputSplit[] inputSplitArr;
	private Mapper[] mapperArr;
	private Reducer[] reducerArr;

	private StringBuilder shufflingLog;

	public WordLength(int numInputSplit, int numReducer, String[] inputArr) {
		this.inputSplitArr = new InputSplit[numInputSplit];
		this.mapperArr = new Mapper[numInputSplit];
		this.reducerArr = new Reducer[numReducer];

		initializeInputSplits(inputArr);

		operateMappers();

		operateReducers();
	}

	public String getShufflingLog() {
		return shufflingLog.toString();
	}

	// Should return a copy of the mutuable object - to modify later
	public InputSplit[] getInputSplitArr() {
		return inputSplitArr;
	}

	public Mapper[] getMapperArr() {
		return mapperArr;
	}

	public Reducer[] getReducerArr() {
		return reducerArr;
	}

	void initializeInputSplits(String[] inputArr) {
		if (mapperArr == null)
			return;
		for (int i = 0; i < inputArr.length; i++) {
			inputSplitArr[i] = new InputSplit(inputArr[i]);
		}
	}

	void operateMappers() {
		for (int i = 0; i < mapperArr.length; i++) {
			mapperArr[i] = new Mapper(inputSplitArr[i]);

			// simulate reading records from input split
			String[] records = mapperArr[i].getMapperInput().split(" ");
			for (String record : records) {
				record = Helper.trimPeriodQoutes(record);

				if (!Helper.isStringOnlyAlphabet(record))
					continue;

				// split hyphenated words
				String[] splitted = null;
				if (record.contains("-"))
					splitted = Helper.splitHyphenatedString(record);

				// run the map method for every record
				if (splitted != null) {
					for (int j = 0; j < splitted.length; j++) {
						mapperArr[i].map(splitted[j].toLowerCase());
					}
				} else {
					mapperArr[i].map(record.toLowerCase());
				}
			}
		}
	}

	int getPartition(String key) {
		return Math.abs(key.hashCode()) % reducerArr.length;
	}

	void operateReducers() {
		for (int i = 0; i < reducerArr.length; i++) {
			reducerArr[i] = new Reducer();
		}

		List<TreeMap<String, List<Pair<Integer, Integer>>>> shuffledList = shuffleSort();

		// prepare reduce method input (i.e. lstGroupByPair in the reducer class)
		int reducerindex = 0;

		for (TreeMap<String, List<Pair<Integer, Integer>>> lstPair : shuffledList) {
			reducerArr[reducerindex++].groupPairs(lstPair);
		}

		// reduce (prepare lstReducerOutput i.e. Pair<key, count>)
		for (int i = 0; i < reducerArr.length; i++) {

			for (Map.Entry<String, List<Pair<Integer, Integer>>> entry : reducerArr[i].getLstGroupByPair().entrySet()) {
				reducerArr[i].reduce(entry.getKey(), entry.getValue());
			}
		}
	}

	private List<TreeMap<String, List<Pair<Integer, Integer>>>> shuffleSort() {
		shufflingLog = new StringBuilder();

		// 01. shuffling
		// TreeMap<Integer, TreeMap<String, Pair<Integer, Integer>>> shuffledList = new
		// TreeMap<Integer, TreeMap<String, Pair<Integer, Integer>>>();
		List<TreeMap<String, List<Pair<Integer, Integer>>>> shuffledList = new ArrayList<TreeMap<String, List<Pair<Integer, Integer>>>>();
		for (int i = 0; i < reducerArr.length; i++) {
			shuffledList.add(new TreeMap<String, List<Pair<Integer, Integer>>>());
		}

		/*
		 * for (int i = 0; i < mapperArr.length; i++) { Mapper mapper = mapperArr[i];
		 * for (int j = 0; j < mapper.getLstPair().size(); j++) { int reducerIndex =
		 * this.getPartition(mapper.getLstPair().get(j).getKey());
		 * shuffledList.get(reducerIndex).add(mapper.getLstPair().get(j)); } }
		 */

		// modification for the above for displaying purpose(there are computation
		// overlaps in this code - revise later)

		for (int i = 0; i < mapperArr.length; i++) {
			for (int k = 0; k < reducerArr.length; k++) {

				shufflingLog.append(System.getProperty("line.separator")
						+ String.format("Pairs send from Mapper %d Reducer %d", i, k));

				Mapper mapper = mapperArr[i];

				for (Map.Entry<String, Pair<Integer, Integer>> entry : mapper.getMap().entrySet()) {
					int reducerIndex = this.getPartition(entry.getKey());
					if (reducerIndex == k) {
						// if it does not contain the key - add it
						// if not accumulate
						if (!shuffledList.get(reducerIndex).containsKey(entry.getKey())) {
							shuffledList.get(reducerIndex).put(entry.getKey(), new ArrayList<Pair<Integer, Integer>>());
						}
						shuffledList.get(reducerIndex).get(entry.getKey()).add(
								new Pair<Integer, Integer>(entry.getValue().getKey(), entry.getValue().getValue()));

						shufflingLog.append(System.getProperty("line.separator")
								+ String.format("< %s, %s >", entry.getKey(), entry.getValue()));
					}
				}
			}
		}

		return shuffledList;
	}

	private void sortLstPair(List<Pair<String, Integer>> lstPair) {
		Collections.sort(lstPair, (p1, p2) -> p1.getKey().compareTo(p2.getKey()));
	}
}
