package code;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class TfIdf {

	private String dir;
	private int topN;
	private int period;
	private String[] terms;
	// Key represents a document name in the directory and key represents a new map which contains
	// key representing a word from 'this.terms' and value representing the tf value which is defined
	// by (number of appareances of the word in the document / number of words in the document)
	private HashMap<String, HashMap<String, Double>> filesTf;
	// Key represents a word from 'this.terms' and value represents the number of documents in which
	// the word is present
	private HashMap<String, Integer> appareancesInCorpus;
	// Key represents a word form 'this.terms' and value represents the idf value for the word across
	// the set of documents
	private HashMap<String, Double> termsIdf;
	private Utils utils;
	private BoyerMoore boyerMoore;

	/**
	 * Stores the options passed (dir, topN, period and terms) in order to make the
	 * tf-idf calculation, starts the utilities needed to read the files and search
	 * for the words inside them, and initializes data structures for the tf-idf info
	 * @param optionsValues
	 */
	public TfIdf(Map<String, String> optionsValues) {
		this.dir = optionsValues.get(Parser.OPTION_D);
		this.topN = Integer.parseInt(optionsValues.get(Parser.OPTION_N));
		this.period = Integer.parseInt(optionsValues.get(Parser.OPTION_P));
		this.terms = optionsValues.get(Parser.OPTION_T).split("\\s+");
		this.utils = new Utils();
		this.boyerMoore = new BoyerMoore();
		this.filesTf = new HashMap<String, HashMap<String, Double>>();
		this.appareancesInCorpus = new HashMap<String, Integer>();
		this.termsIdf = new HashMap<String, Double>();
		for (int i = 0; i < this.terms.length; i++) {
			this.appareancesInCorpus.put(terms[i], 0);
			this.termsIdf.put(terms[i], 0.0);
		}
		
	}

	/**
	 * Adds new information to calculate the tf-idf with the addition of a new document
	 * named 'filename'
	 * @param filename - the name of the file
	 */
	public void addNewFile(Path filename) {
		int wordsCount = 0;
		if (!this.filesTf.containsKey(filename.toString())) {
			String text = this.utils.readFileToString(dir, filename.toString());
			wordsCount = this.utils.countWords(text);
			this.calculateTf(filename.toString(), text, wordsCount);
			this.calculateIdf(this.filesTf.size());
			HashMap<String, HashMap<String, TfIdfObject>> filesTfIdf = null;
			filesTfIdf = this.calculateTfIdf();
			this.showTop(filesTfIdf);
		}
	}
	
	/**
	 * Calculates the tf for each word in 'this.terms' for a given document 'filename'. To do this 
	 * the 'text' of the file is needed, in which the 'this.terms' will be searched via Boyer-Moore 
	 * algorithm. The tf will be then calculated and stored in the 'this.filesTf' HashMap
	 * @param filename - the name of the file
	 * @param text - the whole text of the file
	 * @param wordsCount - the number of words in the file
	 */
	private void calculateTf(String filename, String text, int wordsCount) {
		HashMap<String, Double> tfValues = new HashMap<String, Double>();
		for (int i = 0; i < this.terms.length; i++) {
			int appareancesCount = this.boyerMoore.boyerMooreSearch(text, terms[i]).size();
			if (appareancesCount > 0) { 
				this.appareancesInCorpus.put(terms[i], appareancesInCorpus.get(terms[i]) + 1);
			}
			double tf = Math.round(((double)appareancesCount / (double)wordsCount) * 100.0) / 100.0;
			tfValues.put(terms[i], tf);
		}
		this.filesTf.put(filename, tfValues);
	}
	
	/**
	 * Calculates the idf for each word in 'this.terms' across the whole set of documents
	 * @param totalDocumentsCount - the total number of documents that have been added
	 */
	private void calculateIdf(int totalDocumentsCount) {
		for (int i = 0; i < this.terms.length; i++) {
			double idf = Math.abs(Math.round(Math.log((double)totalDocumentsCount / (double)(1 + this.appareancesInCorpus.get(terms[i]))) * 100.0) / 100.0);
			this.termsIdf.put(terms[i], idf);
		}
	}
	
	/**
	 * Calculates the averaged tf-idf for all words across the whole set of documents and
	 * stores it in 'filesTfIdf'
	 */
	private HashMap<String, HashMap<String, TfIdfObject>> calculateTfIdf() {
		// Key represents a document name in the directory and value represents a list of words
		// with their tf-idf value
		HashMap<String, HashMap<String, TfIdfObject>> filesTfIdf = new HashMap<String, HashMap<String, TfIdfObject>>();
		// Iterate documents
		for (Entry<String, HashMap<String, Double>> entry: this.filesTf.entrySet()) {
			HashMap<String, TfIdfObject> tfIdfObjectsMap = new HashMap<String, TfIdfObject>();
			// Iterate terms
			for (int i = 0; i < this.terms.length; i++) {			
				double tf = entry.getValue().get(terms[i]);
				double idf = termsIdf.get(terms[i]);
				double tfIdf = Math.round((tf * idf ) * 100.0) / 100.0;
				tfIdfObjectsMap.put(terms[i], new TfIdfObject(entry.getKey(), terms[i], tfIdf));
			}
			filesTfIdf.put(entry.getKey(), tfIdfObjectsMap);
		}
		return filesTfIdf;
		
	}
	
	/**
	 * Shows the top N (this.topN) most relevant words per file
	 * @param filesTfIdf
	 */
	private void showTop(HashMap<String, HashMap<String, TfIdfObject>> filesTfIdf) {
		HashMap<String, ArrayList<TfIdfObject>> wordsTfIdf = new HashMap<String, ArrayList<TfIdfObject>>();
		// Iterate documents
		for (Entry<String, HashMap<String, TfIdfObject>> entry: filesTfIdf.entrySet()) {
			// Iterate TfIdfObjects
			for (Entry<String, TfIdfObject> entry2 : entry.getValue().entrySet()) {
				if (!wordsTfIdf.containsKey(entry2.getValue().word)) {
					ArrayList<TfIdfObject> al = new ArrayList<TfIdfObject>();
					al.add(entry2.getValue());
					wordsTfIdf.put(entry2.getValue().word, al);				
				} else {
					ArrayList<TfIdfObject> al = new ArrayList<TfIdfObject>();
					al = wordsTfIdf.get(entry2.getValue().word);
					al.add(entry2.getValue());
					wordsTfIdf.put(entry2.getValue().word, al);
				}
			}
		}
		System.out.println("------------------------------");
		for (Entry<String, ArrayList<TfIdfObject>> entry: wordsTfIdf.entrySet()) {
			ArrayList<TfIdfObject> tfIdfObjArray = entry.getValue();
			Collections.sort(tfIdfObjArray);
			System.out.println("Word: " + tfIdfObjArray.get(0).word);
			for (int i=0 ; i < tfIdfObjArray.size(); i++) {
				if (i == this.topN) { 
					break;
				} else {
					System.out.println("  " + tfIdfObjArray.get(i).doc + ", " + tfIdfObjArray.get(i).tfIdf);
				}
			}
		}
	}

	private class TfIdfObject implements Comparable<TfIdfObject> {

		public String doc;
		public String word;
		public double tfIdf;

		TfIdfObject(String doc, String word, double tfIdf) {
			this.doc = doc;
			this.word = word;
			this.tfIdf = tfIdf;
		}

		@Override
		public int compareTo(TfIdfObject o) {
			double diff = Math.round((o.tfIdf - this.tfIdf) * 100.0) / 100.0;
			if (o.tfIdf - this.tfIdf > 0)
				return 1;
			else if (o.tfIdf - this.tfIdf < 0)
				return -1;
			else 
				return 0;
		}
		
	}

}
