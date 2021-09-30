package week1.day3.assignment;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Program {

	static final String FILE_PATH = "src\\week1\\day1\\testDataForW1D1.txt";

	static final String FILE_PATH1 = "src\\week1\\day3\\assignment\\testDataForW1D1_0.txt";
	static final String FILE_PATH2 = "src\\week1\\day3\\assignment\\testDataForW1D1_1.txt";
	static final String FILE_PATH3 = "src\\week1\\day3\\assignment\\testDataForW1D1_2.txt";

	static final int NUM_MAPPERS = 3;
	static final int NUM_REDUCERS = 4;

	public static void main(String[] args) throws FileNotFoundException {

		Mapper[] mapperArr = new Mapper[NUM_MAPPERS];
		mapperArr[0] = new Mapper(FILE_PATH1);
		mapperArr[1] = new Mapper(FILE_PATH2);
		mapperArr[2] = new Mapper(FILE_PATH3);
		
		//display basic info
		System.out.println(String.format("Number of Input-Splits: %d", NUM_MAPPERS));
		System.out.println(String.format("Number of Reducers: %d", NUM_REDUCERS));
		
		//display mapper input
		for (int i = 0; i < NUM_MAPPERS; i++) {
			mapperArr[i].displayMapperInput();
		}
		
		//display mapper output
		for (int i = 0; i < NUM_MAPPERS; i++) {
			mapperArr[i].displayMapperOutput();
		}

		// shuffling
		ArrayList<ArrayList<WordKeyValPair>> shuffledList = new ArrayList<ArrayList<WordKeyValPair>>();
		for (int i = 0; i < NUM_REDUCERS; i++) {
			shuffledList.add(new ArrayList<WordKeyValPair>());
		}

		/*for (int i = 0; i < NUM_MAPPERS; i++) {
			Mapper mapper = mapperArr[i];
			for (int j = 0; j < mapper.lstWord.size(); j++) {
				int reducerIndex = mapper.getPartition(mapper.lstWord.get(j).key, NUM_REDUCERS);
				shuffledList.get(reducerIndex).add(mapper.lstWord.get(j));
			}
		}*/
		
		//modification for the above for displaying purpose(there are computation overlaps in this code - revise later)
		for (int i = 0; i < NUM_MAPPERS; i++) {
			for (int k = 0; k < NUM_REDUCERS; k++) {
				System.out.println(String.format("Pairs send from Mapper %d Reducer %d", i, k));
				Mapper mapper = mapperArr[i];
				for (int j = 0; j < mapper.lstWord.size(); j++) {
					int reducerIndex = mapper.getPartition(mapper.lstWord.get(j).key, NUM_REDUCERS);
					if(reducerIndex == k) {
						shuffledList.get(reducerIndex).add(mapper.lstWord.get(j));
						System.out.println(String.format("< %s, %s >", mapper.lstWord.get(j).key, mapper.lstWord.get(j).value));
					}
				}
				System.out.println("***********************************************");
			}
		}

		// reducers
		// grouping and sorting to prepare reduce method input
		Reducer[] reducerArr = new Reducer[NUM_REDUCERS];
		for (int i = 0; i < NUM_REDUCERS; i++) {
			reducerArr[i] = new Reducer(shuffledList.get(i));
		}
		
		//display reducer input
		for (int i = 0; i < reducerArr.length; i++) {
			reducerArr[i].displayReduceInput();
		}

		// reducer output
		for (int i = 0; i < NUM_REDUCERS; i++) {
			System.out.println(String.format("%s %d %s", "Reducer", i, "output"));
			for (int j = 0; j < reducerArr[i].lstWordGrouped.size(); j++) {

				WordGroupedKVPair reducerInput = reducerArr[i].lstWordGrouped.get(j);
				reducerArr[i].reduce(reducerInput.key, reducerInput.value);

			}
			System.out.println("************************************");
		}
	}

}
