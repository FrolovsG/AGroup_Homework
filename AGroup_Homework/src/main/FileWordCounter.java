package main;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public interface FileWordCounter {
	
	/**
	 * Opens the file for which words will be counted
	 * How it is stored is up to classes that implement the interface
	 * @param fileName	can be absolute or relative path
	 */
	public void openFile(String fileName);
	/**
	 * 
	 * @return true if file is opened, false otherwise
	 */
	public boolean fileOpened();
	/**
	 * Returns unsorted pairs of unique words with their count of occurrence in a file
	 * @return Unsorted map of (word -> count) pairs from the associated file
	 * @throws FileNotFoundException
	 */
	public Map<String, Long> countWords() throws FileNotFoundException;
	/**
	 * Returns sorted pairs of unique words with their count of occurrence in a file
	 * @param comparator	specifies how word count pairs should be sorted
	 * @return				List of (word -> count) pairs sorted by provided comparator
	 * @throws FileNotFoundException
	 */
	public List<Entry<String, Long>> countWordsSorted(Comparator<Entry<String, Long>> comparator) throws FileNotFoundException;
}
