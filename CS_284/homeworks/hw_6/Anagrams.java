package homeworks.hw_6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map; 

public class Anagrams {
	final Integer[] primes =  
			{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
			31, 37, 41, 43, 47, 53, 59, 61, 67, 
			71, 73, 79, 83, 89, 97, 101};

	final Character[] letters = 
			{ 'a', 'b', 'c', 'd', 'e', 'f', 'g',
				'h', 'i', 'j', 'k', 'l', 'm', 'n', 
					'o', 'p', 'q', 'r', 's', 't', 'u', 
					'v', 'w', 'x', 'y', 'z' };
				
	// a hash table that will associate each letter in the alphabet with a prime
	// number
	Map<Character,Integer> letterTable; //{ letter: prime }
	Map<Long,ArrayList<String>> anagramTable; //{ hashCode : [Anagrams: string] }

	/**
	 * This method should be invoked by the constructor for the class Anagrams and
	 * should build the hash table letterTable
	 */
	private void buildLetterTable() {

		System.out.println(letters.length);
		for (int i = 0; i < letters.length; i++) {
			System.out.println(letters[i] + ", " + primes[i]);
			
			Character letter = letters[i];
			Integer prime = primes[i];
			letterTable.put(letter, prime);
			
		}
	}

	Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}

	/**
	 * This method should compute the hash code of the string s passed as argument,
	 * and should add this word to the hash table anagramTable.
	 */
	private void addWord(String s) {
		long hashCode = myHashCode(s);
		ArrayList<String> newString;

		// if an anagram of s has been found before, add s to list
		if (anagramTable.containsKey(hashCode)) {	
			newString = anagramTable.get(hashCode);
			newString.add(s);
			anagramTable.replace(hashCode, newString);
		} else { // if anagram of s has not been found, create new key-value pair 
			newString = new ArrayList<String>();
			newString.add(s);
			anagramTable.put(hashCode, newString);
		}
	}
	
	/**
	 * This method, given a string s, should compute its hash code by utilizing the
	 * unique factorization theorem
	 * 
	 * @param s
	 * @return the hash code for the given string s
	 */
	private long myHashCode(String s) {
		if (s.isEmpty()) {
			throw new IllegalArgumentException("myHashCode(): given string is empty");
		}

		long hashCode = 1;

		for (int i = 0; i < s.length(); i++) {
			char letter = s.charAt(i);

			hashCode = hashCode * letterTable.get(letter);
		}
		
		return hashCode;

	}
	
	/**
	 * receives the name of a text file containing words, one per line, and builds the
	 * hash table anagramTable
	 */
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
		  this.addWord(strLine);
		}
		br.close();
	}
	

	/**
	 * This method should return the entries in the anagramTable that have the
	 * largest number of anagrams
	 * 
	 * @return a list of them since there ay be more than one list of anagrams with
	 *         a maximal size
	 */
	private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
	    ArrayList<Map.Entry<Long, ArrayList<String>>> temp = new ArrayList<Map.Entry<Long, ArrayList<String>>>();
			int max = 0;
			for (Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()) {
				if (entry.getValue().size() > max) {
					temp.clear();
					temp.add(entry);
					max = entry.getValue().size();
				} else {
					if (entry.getValue().size() == max) {
						temp.add(entry);
					}
				}
			}
			return temp;
	}
	
	public static void main(String[] args) {
		Anagrams a = new Anagrams();

		final long startTime = System.nanoTime();    
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
	}
}
