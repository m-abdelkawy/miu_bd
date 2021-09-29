package week1.day2.assignment;

public class WordKeyValPair implements Comparable<WordKeyValPair>{
	
	static final String WORD_PATTERN = "^[a-zA-Z]*$";
	//private String WORD_PATTERN = "^[a-zA-Z]*$";
	//private String WORD_PATTERN = "[a-zA-Z\\\\s\\'\\\"]+";
	
	String key;
	int value;
	
	public String getKey() {
		return key;
	}

	public WordKeyValPair(String key, int value) {
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
	
	@Override
    public int compareTo(WordKeyValPair w) {
        return this.key.compareTo(w.key);
    }
}
