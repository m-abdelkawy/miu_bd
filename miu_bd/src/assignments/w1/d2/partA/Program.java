package assignments.w1.d2.partA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Program {

	/**
	 * The file simulates an input-split with one word in each record
	 */
	static final String FILE_PATH = "src\\assignments\\w1\\d2\\partA\\inputfiles\\testDataForW1D1.txt";
	static final String OUTPUT_PATH = "src\\assignments\\w1\\d2\\partA\\outputfiles\\outputDisplayFile.txt";

	public static void main(String[] args) throws IOException {

		// read the records from the input-split
		FastScanner scanner = new FastScanner(new FileInputStream(FILE_PATH));

		// a mapper is created for each input-split
		Mapper mapper = new Mapper();

		// setting the mapper input in a local field inside the mapper class
		byte[] mapperInputAsBytes = Files.readAllBytes(Paths.get(FILE_PATH));
		String mapperInput = new String(mapperInputAsBytes);
		mapper.setMapperInput(mapperInput);

		// simulating reading records from input-split
		String token;
		while ((token = scanner.next()) != null) {

			token = Helper.trimPeriodQoutes(token);

			if (!Helper.isStringOnlyAlphabet(token))
				continue;

			// split hyphenated words
			String[] splitted = null;
			if (token.contains("-"))
				splitted = Helper.splitHyphenatedString(token);

			// run the map method for every record
			if (splitted != null) {
				for (int i = 0; i < splitted.length; i++) {
					mapper.map(splitted[i].toLowerCase());
				}
			} else {
				mapper.map(token.toLowerCase());
			}

		}

		// display mapper output to the console
		// mapper.display();

		Reducer reducer = new Reducer();
		// prepare reducer input directly from the mapper output
		// This should be from the Shuffle sort intermediate step done by the Hadoob
		// framework
		reducer.groupPairs(mapper.getLstPair());

		// run the reduce for each key-value GroupByPair
		// Emits Pair<String, Integer> to the lstReducerOutput
		// where the integer is the sum
		for (Pair<String, List<Integer>> gp : reducer.getLstGroupByPair()) {
			reducer.reduce(gp.getKey(), gp.getValue());
		}

		// Write to the file
		FastWriter writer = new FastWriter(OUTPUT_PATH, true);
		// 01. the mapper input
		writer.writeFile("Mapper Input: " + System.getProperty("line.separator") + mapper.getMapperInput());

		// 02. Mapper output
		writer.appendNewLine("Mapper Output: " + System.getProperty("line.separator") + mapper.getLstPairAsString());

		// 03. Reducer Input
		writer.appendNewLine(
				"Reducer Input: " + System.getProperty("line.separator") + reducer.getLstGroupByPairAsString());

		// 03. Reducer Output
		writer.appendNewLine(
				"Reducer Output: " + System.getProperty("line.separator") + reducer.getLstReducerOutputAsString());
	}
}
