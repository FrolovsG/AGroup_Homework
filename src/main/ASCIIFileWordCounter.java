package main;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
//import java.util.stream.Collectors;

public final class ASCIIFileWordCounter implements FileWordCounter {
	
	private File file;
	
	private ASCIIFileWordCounter() {}
	
	/**
	 * Factory method for creating a new ASCIIFileWordCounter instance
	 * @param fileName	can be absolute or relative path
	 * @return			Instance of ASCIIFileWordCounter through FileWordCounter interface
	 */
	public static FileWordCounter ofFile(String fileName){
		
		var fwc = new ASCIIFileWordCounter();
		fwc.openFile(fileName);
		return fwc;
	}
	
	public void openFile(String fileName) {
		
		file = new File(fileName);
	}
	
	public boolean fileOpened() {
		return file.isFile();
	}
	
	public Map<String, Long> countWords() throws FileNotFoundException { 
		
		Map<String, Long> wordCount = new HashMap<>();
		try(Scanner sc = new Scanner(file).useDelimiter("[.,:;{}()\\[\\]“—~?&!*+%\"=<>\\\\/ \t#\n\r]+")) {
			
			while(sc.hasNext()) {
				String word = sc.next().replaceAll("^['-]+|['-]+$", "").toLowerCase();
				if(word.isEmpty())
					continue;
				
				wordCount.putIfAbsent(word, 0L);
				wordCount.compute(word, (k, v) -> v + 1L);	
			}
			
			// would go through entire file? is it sane if file is big?
			/*wordCount = sc.tokens()
				.map(t -> t.replaceAll("^['-]+|['-]+$", "").toLowerCase())
				.filter(t -> !t.isEmpty())
				.collect(Collectors.toMap((t) -> t, (t) -> 1L, (l, r) -> l + 1));*/
		}
		
		return wordCount;
	}
	
	public List<Entry<String, Long>> countWordsSorted(Comparator<Entry<String, Long>> comparator) throws FileNotFoundException { 
		List<Entry<String, Long>> wordCountByOccurence = new ArrayList<> (countWords().entrySet());
		wordCountByOccurence.sort(comparator);
			
		return wordCountByOccurence;
		
		/*return countWords().entrySet().stream()
					.sorted(comparator)
					.collect(Collectors.toList());*/
	}
}
