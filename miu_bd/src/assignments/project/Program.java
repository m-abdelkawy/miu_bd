package assignments.project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class Program {

	/**
	 * The file simulates an input-split with one word in each record
	 */
	static final String FILE_PATH = "src\\assignments\\project\\inputfiles\\testDataForW1D1.txt";
	static final String OUTPUT_PATH1 = "src\\assignments\\project\\output\\data1000.txt";
	static final String OUTPUT_PATH2 = "src\\assignments\\project\\output\\data2000.txt";
	static final String OUTPUT_PATH3 = "src\\assignments\\project\\output\\data3000.txt";
	static final String OUTPUT_PATH4 = "src\\assignments\\project\\output\\data4000.txt";
	static final String OUTPUT_PATH5 = "src\\assignments\\project\\output\\data5000.txt";
	static final String OUTPUT_PATH6 = "src\\assignments\\project\\output\\data6000.txt";
	static final String OUTPUT_PATH7 = "src\\assignments\\project\\output\\data7000.txt";
	static final String OUTPUT_PATH8 = "src\\assignments\\project\\output\\data8000.txt";
	static final String OUTPUT_PATH9 = "src\\assignments\\project\\output\\data9000.txt";
	static final String OUTPUT_PATH10 = "src\\assignments\\project\\output\\data10000.txt";

	public static void main(String[] args) throws IOException {

		String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z" }; // 26

		Integer[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
				26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51,
				52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77,
				78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100 }; // 100

		Random random = new Random();

		for (int k = 1; k < 11; k++) {
			FastWriter writer = new FastWriter("src\\assignments\\project\\output\\data" + k + "000.txt");

			for (int i = 0; i < 1000 * k; i++) {
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < 17; j++) {
					int letterIndex = random.nextInt(25 - 0 + 1) + 0;

					int numIndex = random.nextInt(99 - 0 + 1) + 0;

					sb.append(letters[letterIndex] + nums[numIndex] + " ");
				}

				// write mapper output to the file
				writer.appendNewLine(sb.toString());
			}
		}
	}
}
