package assignments.project;

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
		if(bw == null)
			bw = new BufferedWriter(new FileWriter(filepath));
		bw.write(str);
		bw.close();
		bw = null;
	}
	
	void appendNewLine(String str) throws IOException {
		if(bw == null)
			bw = new BufferedWriter(new FileWriter(filepath, true));
		bw.append("\n" + str);
		bw.close();
		bw = null;
	}
}
