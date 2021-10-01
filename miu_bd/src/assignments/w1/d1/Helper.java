package assignments.w1.d1;

class Helper {
	private static final String ALPHABET_PATTERN = "^[a-zA-Z]*$";

	static boolean isStringOnlyAlphabet(String str) {
		return (!str.isEmpty() && str != null && str.matches(ALPHABET_PATTERN));
	}

	static String trimPeriodQoutes(String str) {
		if (str.substring(str.length() - 1).matches("[.]"))
			str = str.replaceAll("\\.", "");

		if (str.contains("\""))
			str = str.replaceAll("\"", "");

		return str;
	}

	static String[] splitHyphenatedString(String str) {
		String[] res = null;
		if (str.contains("-"))
			res = str.split("-");

		return res;
	}
}
