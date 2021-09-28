package week1.day1;

public class Word {
	
	static final String WORD_PATTERN = "^[a-zA-Z]*$";
	//private String WORD_PATTERN = "^[a-zA-Z]*$";
	//private String WORD_PATTERN = "[a-zA-Z\\\\s\\'\\\"]+";
	
	String key;
	int value;

	public Word(String key, int value) {
		if (isStringOnlylphabet(key))
			this.key = key;
		else
			this.key = null;
		this.value = value;
	}

	boolean isStringOnlylphabet(String str) {
		return (!str.equals("")) && (str != null) && (str.matches(WORD_PATTERN));
	}
	
	boolean isStringOnlylphabet2(String str) {
		return false;
	}
}
