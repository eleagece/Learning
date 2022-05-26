package kafkaproducer;

import java.util.HashMap;
import java.util.Map;

import utils.Constants;

public class Parser {

	private String[] args;
	private Map<String, String> optionsValues;

	/**
	 * Receives the arguments from the program in the array 'args' and stores them
	 * in 'this.args'. Initializes the HashMap 'this.optionsValues' that will be
	 * later filled with the parsed information from 'this.args'.
	 * 
	 * @param args - the arguments from the program.
	 */
	Parser(String[] args) {
		this.args = args;
		this.optionsValues = new HashMap<String, String>();
	}

	/**
	 * Parses the options contained in the array 'this.args' and puts them (if
	 * possible) in the HashMap 'this.optionValues'. Besides that it checks if the
	 * values passed for the options make sense.
	 * 
	 * @return the Map 'this.optionValues'.
	 */
	public Map<String, String> parse() throws IllegalArgumentException {
		Map<String, Boolean> checkedOptions = this.parseOptions();
		this.checkValues(checkedOptions);
		return this.optionsValues;
	}

	/**
	 * Parses the options contained in the array 'this.args' and if their syntax is
	 * correct and they are expected then they are put in 'checkedOptions' and added
	 * to 'optionsValues' with the passed value. If not a exception is thrown.
	 * 
	 * @param checkedOptions - the map that says what options have been included in
	 *                       the command.
	 */
	private Map<String, Boolean> parseOptions() {
		Map<String, Boolean> checkedOptions = new HashMap<String, Boolean>();
		for (int i = 0; i < this.args.length; i++) {
			switch (this.args[i].charAt(0)) {
			case '-':
				if (this.args[i].length() < 2) { // At least one letter for the option.
					throw new IllegalArgumentException("Not a valid argument: " + this.args[i]);
				} else if (this.args.length - 1 == i) { // Last option should have last value.
					throw new IllegalArgumentException("Expected arg after: " + this.args[i]);
				} else if (checkedOptions.containsKey(this.args[i])) { // Options should not be repeated.
					throw new IllegalArgumentException("Repeated arg: " + this.args[i]);
				} else {
					optionsValues.put(this.args[i], this.args[i + 1]);
					checkedOptions.put(this.args[i], true);
					i++;
				}
				break;
			default: // The next option should start by '-'.
				throw new IllegalArgumentException("Expected '-' but obtained: " + this.args[i]);
			}
		}
		return checkedOptions;
	}

	/**
	 * Checks the values for the 'checkedOptions'. If they are somehow incorrect an
	 * exception is thrown. If not this method ends without a warning.
	 * 
	 * @param checkedOptions - the map that says what options have been included in
	 *                       the command.
	 */
	private void checkValues(Map<String, Boolean> checkedOptions) {
		if (checkedOptions != null && checkedOptions.size() == Constants.Parser.optionsMap.size()) {
			for (Map.Entry<String, Boolean> checkedOptionEntry : checkedOptions.entrySet()) {
				// Only allowed options should be passed in the command.
				if (!Constants.Parser.optionsMap.containsKey(checkedOptionEntry.getKey())) {
					throw new IllegalArgumentException(
							"The option value '" + checkedOptionEntry.getKey() + "' is not valid.");
				}
			}
		} else { // A correct number and correct options should be passed in the command.
			throw new IllegalArgumentException("Make sure you have passed the correct number of options");
		}
	}

}
