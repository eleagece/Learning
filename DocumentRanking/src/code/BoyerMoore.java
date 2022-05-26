package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoyerMoore {
	
	public class SearchHeuristics {
		public int[] badCharactLookUp;
		public int[] goodSuffixLookUp;
		SearchHeuristics(int[] badCharactLookUp, int[] goodSuffixLookUp) {
			this.badCharactLookUp = badCharactLookUp;
			this.goodSuffixLookUp = goodSuffixLookUp;
		}
	}
	private Map<String, SearchHeuristics> wordsHeuristics;
	private static final int ALPHABET_SIZE = 256;
	
	BoyerMoore() {
		wordsHeuristics = new HashMap<String, SearchHeuristics>();
	}
	
	/**
	 * Boyer-Moore algorithm to search for substrings in a given string. It uses the bad characters and the good
	 * suffix heuristics to find the match faster. These heuristics are based in the creation of look-up tables 
	 * for the given word 'needle' that is going to be searched for in the 'haystack'
	 * @see https://es.wikipedia.org/wiki/Algoritmo_de_b%C3%BAsqueda_de_cadenas_Boyer-Moore
	 * @see https://youtu.be/4Xyhb72LCX4
	 * @see https://youtu.be/Wj606N0IAsw
	 * @see https://www.tutorialandexample.com/boyer-moore-algorithm/
	 * @see https://leetcode.com/problems/implement-strstr/discuss/744624/boyer-moore-substring-search-using-both-bad-character-and-good-suffix-heuristics-explained 
	 * @see https://stackoverflow.com/questions/19345263/boyer-moore-good-suffix-heuristics
	 * @param haystack - the text to be searched
	 * @param needle - the word to find in the text
	 * @return the position(s) of the needle in the haystack
	 */
	public List<Integer> boyerMooreSearch(String haystack, String needle) {
		ArrayList<Integer> resultArray = new ArrayList<Integer>(); 
		if (haystack == null || needle == null) {
			return null;
		} else if (needle.isEmpty() || haystack.length() < needle.length()) {
			return resultArray;
		}		
		char[] text = haystack.toCharArray();
		char[] pattern = needle.toCharArray();
		
		// Heuristics calculation
		int[] lastOccurence = preprocessBadCharacters(pattern, ALPHABET_SIZE); // Bad characters heuristic
		int[] shift = preprocessGoodSuffix(pattern); // Good suffix heuristic calculation
		
		// Search for 'pattern' in 'text'
		int i=0; // 'i' is start index of current window in 'text'
		int j=0; // 'j' is the position inside the 'pattern'
		while (i <= text.length-pattern.length) {
			j = pattern.length-1; // We process pattern backwards
			while (j >= 0 && pattern[j] == text[i+j]) { // Go left backwards till characters are matching in current window
				j--;
			}
			if (j < 0) { // Will be true only when there was a complete match found for pattern in text
				resultArray.add(i);
				i = i+shift[0];
			} else { // Mismatch found in current window comparison
				// Taking look-up table heuristics into account, the pattern is shifted by the maximum entry of any of the tables
				i = i + Math.max(shift[j+1], j-lastOccurence[text[i+j]]);
			}
		}		
		return resultArray;
	}
	
	/**
	 * Creates the look-up table for the bad characters heuristic. This table contains the last occurrence 
	 * index of each character in pattern
	 * @param pattern - the array of chars for the word that has to be preprocessed
	 * @param alphabetSize - the alphabet size
	 * @return the look-up table
	 */
	public int[] preprocessBadCharacters(char[] pattern, int alphabetSize) {
		if (this.wordsHeuristics.containsKey(Arrays.toString(pattern))) {
			return this.wordsHeuristics.get(Arrays.toString(pattern)).badCharactLookUp;
		} else {
			int[] lastOccurence = new int[alphabetSize]; // contains last occurrence index of each character in pattern
			Arrays.fill(lastOccurence, -1);			
			for (int i = 0; i < pattern.length; i++) {
				lastOccurence[pattern[i]] = i;
			}
			this.wordsHeuristics.put(Arrays.toString(pattern), new SearchHeuristics(lastOccurence, null));
		return lastOccurence;
		}
	}

	/**
	 * Creates the look-up table for the good suffix heuristic. Each entry shift[i] in this table contains the shift 
	 * distance of the pattern if a mismatch at position i – 1 occurs, i.e. if the suffix of the pattern starting at 
	 * position i has matched
	 * @param pattern - the array of chars for the word that has to be preprocessed
	 * @param lastOccurrence - the look-up table
	 * @return the look-up table
	 */
	private int[] preprocessGoodSuffix(char[] pattern) {
		// borderPosition[i] contains the starting position of the widest border of the suffix of the pattern beginning at position i
		int[] borderPosition = new int[pattern.length + 1];
		int[] shift = new int[pattern.length+1];
		if (this.wordsHeuristics.containsKey(Arrays.toString(pattern)) &&
			this.wordsHeuristics.get(Arrays.toString(pattern)).goodSuffixLookUp	!= null) {
			return this.wordsHeuristics.get(Arrays.toString(pattern)).goodSuffixLookUp;
		} else {
			preprocessStrongSuffix(pattern, borderPosition, shift);
			preprocessPartialSuffix(borderPosition, shift, pattern.length);
			int[] lastOccurence = this.wordsHeuristics.get(Arrays.toString(pattern)).badCharactLookUp;
			this.wordsHeuristics.remove(Arrays.toString(pattern));
			this.wordsHeuristics.put(Arrays.toString(pattern), new SearchHeuristics(lastOccurence, shift));
			return shift;
		}
	}

	private void preprocessStrongSuffix(char[] pattern, int[] borderPosition, int[] shift) {
		int m = pattern.length;		
		int i = m, j = m + 1;
		borderPosition[i] = j; // The suffix ε beginning at position m has no border, therefore f[m] is set to m+1
		while (i > 0) {
			/* at this line, we know f[i], f[i+1], ... f[m]; If character at position i-1 is not  
               equal to character at j-1, then continue searching to right of the pattern for border */
			while (j <= m && pattern[i - 1] != pattern[j - 1]) {
				/* the character preceding the occurrence of t in pattern P is different than the mismatching  
	               character in P, we stop skipping the occurrences and shift the pattern from i to j */
				if (shift[j] == 0) {
					shift[j] = j - i;
				}
				j = borderPosition[j]; // get the start position of the border of suffix pattern[j] ... pattern[m-1]
			}			
			// pattern[i-1] matched with pattern[j-1], border is found. store the beginning position of border 
			i--;
			j--;
			borderPosition[i] = j;
		}
	}
		
	private void preprocessPartialSuffix(int[] borderPosition, int[] shift, int m) {
		int i, j;
		j = borderPosition[0];
		for (i = 0; i <= m; i++) {
			/* set the border position of the first character of the pattern to all indices 
			   in array shift having shift[i] = 0 */
			if (shift[i] == 0) {
				shift[i] = j;
			}			
			/* suffix becomes shorter than borderPosition[0], use the position of next widest border as value of j */
			if (i == j) {
				j = borderPosition[j];
			}
		}
	}

}
