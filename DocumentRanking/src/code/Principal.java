package code;

import java.util.HashMap;


public class Principal {

	/**
	 * The complexity of these exercise is in the finding of the repetitions in the text. The complexity in searching is reduced
	 * by the use of the Boyer-Moore algorithm which can skip checking characters in the text because of a preprocessing on the 
	 * term to be searched: a couple of look-up tables allow to reduce complexity to O(n) in time (being n the length of the string
	 * being searched) but those tables imply a worse space complexity needing O(m+k) space based for both of them depending on the
	 * input term and the alphabet used.
	 * 
	 * There are some quadratic comparisons involving documents and terms in the TfIdf class. Those could have been avoided using
	 * a Binary Search Tree, but time ran out and had to finish
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Parser p = new Parser (args);
			HashMap<String, String> optionsValues = (HashMap<String, String>) p.parse();
			DirectoryService ds = new DirectoryService(optionsValues);
			ds.listen();
		} catch (Exception e) {
			System.err.format(e.getMessage());
		}
		
    }

}