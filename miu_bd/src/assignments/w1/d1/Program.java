package assignments.w1.d1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Program {

	/**
	 * The file simulates an input-split with one word in each record
	 */
	static final String FILE_PATH = "src\\assignments\\w1\\d1\\inputfiles\\testDataForW1D1.txt";
	static final String OUTPUT_PATH = "src\\assignments\\w1\\d1\\outputfiles\\mapperOutput.txt";

	public static void main(String[] args) throws IOException {

		// read the records from the input-split
		FastScanner scanner = new FastScanner(new FileInputStream(FILE_PATH));

		// a mapper is created for each input-split
		Mapper mapper = new Mapper();

		String token;
		while ((token = scanner.next()) != null) {
			
			token = Helper.trimPeriodQoutes(token);
			
			if(!Helper.isStringOnlyAlphabet(token)) continue;
			
			// split hyphenated words
			String[] splitted = null;
			if (token.contains("-"))
				splitted = Helper.splitHyphenatedString(token);

			// run the map method for every record
			if (splitted != null) {
				for (int i = 0; i < splitted.length; i++) {
					mapper.map(splitted[i].toLowerCase());
				}
			}else {
				mapper.map(token.toLowerCase());
			}

		}

		// display mapper output to the console
		//mapper.display();
		
		//write mapper output to the file
		FastWriter writer = new FastWriter(OUTPUT_PATH);
		writer.writeFile(mapper.getLstPairAsString());
	}
}
