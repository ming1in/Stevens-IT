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
	Map<Character,Integer> letterTable = new HashMap<Character, Integer>(); //{ letter: prime }
	Map<Long,ArrayList<String>> anagramTable; //{ hashCode : [Anagrams: string] }

	/**
	 * This method should be invoked by the constructor for the class Anagrams and
	 * should build the hash table letterTable
	 */
	private void buildLetterTable() {		
		for (int i = 0; i < letters.length; i++) {			
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
	 * @throws IllegalArgumentException if the string s is empty.
	 * @return the hash code for the given string s
	 */
	private long myHashCode(String s) {
		if (s.isEmpty()) {
			throw new IllegalArgumentException("myHashCode(): given string is empty");
		}

		long hashCode = 1;

		//iterate through char in s and create hashCode using letterTable
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
	protected ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
			ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntry = new ArrayList<Map.Entry<Long, ArrayList<String>>>();
			int largestEntry = Integer.MIN_VALUE; // track the largest sets of anagrams

			for (Map.Entry<Long, ArrayList<String>> anagram : anagramTable.entrySet()) {
				int sizeAnagram = anagram.getValue().size();

				//if larger set of anagram is found, reset maxEntry
				if (sizeAnagram > largestEntry) {
					maxEntry.clear();
					maxEntry.add(anagram);
					largestEntry = sizeAnagram;
				} else { //is set of anagram with same size, add the set
					if (sizeAnagram == largestEntry) {
						maxEntry.add(anagram);
					}
				}
			}

			return maxEntry;

	}
	
	public static void main(String[] args) {
		Anagrams a = new Anagrams();

		final long startTime = System.nanoTime();    
		try {
			a.processFile("/Users/minglin/Stevens-IT/CS_284/homeworks/hw_6/words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime / 1000000000);
		
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: " + maxEntries); //  [236204078=[ alerts , alters , artels , estral , laster , lastre , rastle , ratels , relast , resalt , salter , slater , staler , stelar , talers ]]
		System.out.println("HashCode: " + maxEntries.get(0).getKey()); //236204078
		System.out.println("Size: " + maxEntries.get(0).getValue().size()); //15
	}
}
