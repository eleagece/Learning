package code;

import java.util.HashMap;
import java.util.Map;

public class Parser {
	
	private String[] args;
	private HashMap<String, String> optionsValues;
	private static final int NUMBER_OF_OPTIONS = 4;
	public static final String OPTION_D = "-d";
	public static final String OPTION_N = "-n";
	public static final String OPTION_P = "-p";
	public static final String OPTION_T = "-t";
	
	/**
	 * Receives the arguments from the program in the array 'args' and stores them in 'this.args'.
	 * Initializes the HashMap 'this.optionsValues' that will be later filled with the parsed
	 * information from 'this.args'
	 * @param args - the arguments from the program
	 */
	Parser(String[] args) {
		this.args = args;
		this.optionsValues = new HashMap<String, String>();
	}
	
	/**
	 * Parses the options contained in the array 'this.args' and puts them (if possible) in the
	 * HashMap 'this.optionValues'
	 * @return the HashMap 'this.optionValues'
	 */
	public Map<String, String> parse() {
		HashMap<String, Boolean> checkedOptions = new HashMap<String, Boolean>();
		for (int i = 0; i < this.args.length; i++) {
			switch (this.args[i].charAt(0)) {
			case '-':
				if (this.args[i].length() < 2) {
					throw new IllegalArgumentException("Not a valid argument: "+ this.args[i]);
				} else {
					if (this.args.length-1 == i) {
						throw new IllegalArgumentException("Expected arg after: "+ this.args[i]);
										}
					if (checkedOptions.containsKey(this.args[i])) {
						throw new IllegalArgumentException("Repeated arg: "+ this.args[i]);
					} else {
						optionsValues.put(this.args[i], this.args[i+1]);
						checkedOptions.put(this.args[i], true);
						i++;
					}
				}
				break;
			default:
				throw new IllegalArgumentException("Expected '-' but obtained: "+ this.args[i]);
			}
		}		
		if (checkedOptions.size() == NUMBER_OF_OPTIONS &&
			checkedOptions.containsKey(OPTION_D) &&	checkedOptions.containsKey(OPTION_N) &&
			checkedOptions.containsKey(OPTION_P) &&	checkedOptions.containsKey(OPTION_T)) {
			return this.optionsValues;
		} else {
			throw new IllegalArgumentException("Make sure you have passed all options: (-d, -n, -p, -t)");
		}
	}

}
