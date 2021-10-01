package assignments.w1.d2.partB;

import java.io.File;
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
	static final String INPUT_DIR = "src\\assignments\\w1\\d2\\partB\\inputfiles";
	static final String OUTPUT_PATH = "src\\assignments\\w1\\d2\\partB\\outputfiles\\outputDisplayFile.txt";

	public static void main(String[] args) throws IOException {

		int numInputSplit = 0;
		int numReducer = 4;

		String[] inputFilePaths;
		File f = new File(INPUT_DIR);
		inputFilePaths = f.list();

		numInputSplit = inputFilePaths.length;

		// setting the input in a local field inside the InputSplit class
		String[] inputArr = new String[numInputSplit];
		for (int i = 0; i < numInputSplit; i++) {
			/*byte[] inputsplitInputAsBytes = Files.readAllBytes(Paths.get(INPUT_DIR,inputFilePaths[i]));
			String InputsplitInput = new String(inputsplitInputAsBytes);*/
			
			StringBuilder sbInput = new StringBuilder();
			FastScanner scanner = new FastScanner(new FileInputStream(Paths.get(INPUT_DIR, inputFilePaths[i]).toString()));
			String token;
			while ((token = scanner.next()) != null) {
				sbInput.append(token + " ");
			}
			inputArr[i] = sbInput.toString();
		}

		WordCount wordCount = new WordCount(numInputSplit, numReducer, inputArr);

		// Write to the file
		FastWriter writer = new FastWriter(OUTPUT_PATH, true);
		
		//01. basic info
		StringBuilder basicInfo = new StringBuilder();
		basicInfo.append("Number of Input-Splits: " + numInputSplit);
		basicInfo.append("\nNumber of Reducers: " + numReducer);
		for (int i = 0; i < wordCount.getMapperArr().length; i++) {
			basicInfo.append("\nMapper " + i + " Input");
			basicInfo.append("\n" + wordCount.getMapperArr()[i].getMapperInput());
		}
		writer.writeFile(basicInfo.toString());
		
		//02. Mappers output
		StringBuilder sbMappersOutput = new StringBuilder();
		for (int i = 0; i <  wordCount.getMapperArr().length; i++) {
			sbMappersOutput.append("\nMapper " + i + " output");
			for (int j = 0; j < wordCount.getMapperArr()[i].getLstPair().size(); j++) {
				sbMappersOutput.append("\n" + wordCount.getMapperArr()[i].getLstPair().get(j));	
			}
		}
		writer.appendNewLine(sbMappersOutput.toString());
		
		//03. shuffling log
		writer.appendNewLine(wordCount.getShufflingLog());
		
		//04. reducers inputs
		StringBuilder sbReducersInputs = new StringBuilder();
		for (int i = 0; i < wordCount.getReducerArr().length; i++) {
			sbReducersInputs.append("\nReducer " + i +" Input");
			for (int j = 0; j < wordCount.getReducerArr()[i].getLstGroupByPair().size(); j++) {
				sbReducersInputs.append("\n" + wordCount.getReducerArr()[i].getLstGroupByPair().get(j));
			}
		}
		writer.appendNewLine(sbReducersInputs.toString());
		
		//05. reducers outputs
		StringBuilder sbReducersOutputs = new StringBuilder();
		for (int i = 0; i < wordCount.getReducerArr().length; i++) {
			sbReducersOutputs.append("\nReducer " + i +" Input");
			for (int j = 0; j < wordCount.getReducerArr()[i].getLstReducerOutput().size(); j++) {
				sbReducersOutputs.append("\n" + wordCount.getReducerArr()[i].getLstReducerOutput().get(j));
			}
		}
		writer.appendNewLine(sbReducersOutputs.toString());
	}
}
