package assignments.w1.d2.partA;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


class FastWriter {
	BufferedWriter bw;
	String filepath;
	
	public FastWriter(String filepath) throws IOException {
		this(filepath, false);
	}
	
	public FastWriter(String filepath, boolean append) throws IOException {
		this.filepath = filepath;
		bw = new BufferedWriter(new FileWriter(filepath, append));
	}
	
	void writeFile(String str) throws IOException {
		clearFileContent();
		if(bw == null)
			bw = new BufferedWriter(new FileWriter(filepath));
		bw.write(str + System.getProperty("line.separator"));
		bw.close();
		bw = null;
	}
	
	void appendNewLine(String str) throws IOException {
		if(bw == null)
			bw = new BufferedWriter(new FileWriter(filepath, true));
		bw.append(System.getProperty("line.separator") + str);
		bw.close();
		bw = null;
	}

	private void clearFileContent() throws IOException {
		new FileWriter(filepath, false).close();
	}
}
