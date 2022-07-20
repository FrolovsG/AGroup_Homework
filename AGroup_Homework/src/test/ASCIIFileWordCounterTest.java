package test;

import main.ASCIIFileWordCounter;
import main.FileWordCounter;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class ASCIIFileWordCounterTest {
	
	static String[] badFileNames() {
		return new String[] {"nonexistent.file", "..", "dir/", "dir/nonexistent.file", "dir/../", "dir/../nonexistent.file", "//"};
	}
	
	static String[] goodFileNames() {
		return new String[] { "resources/lorem-ipsum.txt", "resources/missile.txt", "resources/special-chars.txt"};
	}
	
	@ParameterizedTest
	@MethodSource(value = "badFileNames")
	void fileOpened_nonExistingFileOrIllegal(String fileName) {
		
		assertFalse(ASCIIFileWordCounter.ofFile(fileName).fileOpened(),
				"Abstract file should have not opened: does the file exist?");
	}

	@ParameterizedTest
	@ValueSource(strings = {"resources/existing.file", "resources/dir/existing.file"})
	void fileOpened_existingFile(String fileName) {
		
		assertTrue(ASCIIFileWordCounter.ofFile(fileName).fileOpened(),
				"Abstract file should have opened: does the file exist?");
	}
	
	@ParameterizedTest
	@MethodSource(value = "badFileNames")
	void wordCount_exceptionThrow(String fileName) {
		
		assertThrows(FileNotFoundException.class, () -> ASCIIFileWordCounter.ofFile(fileName).countWords());
	}
	
	@Test
	void wordCount_emptyFile() {
		
		FileWordCounter fwc = ASCIIFileWordCounter.ofFile("resources/existing.file");
		assertAll("Result should be empty",
			() -> assertTrue(fwc.countWords().isEmpty()),
			() -> assertTrue(fwc.countWordsSorted(Entry.comparingByValue()).isEmpty())
		);
	}
	
	@ParameterizedTest
	@MethodSource(value = "goodFileNames")
	void wordCount_goodFile(String fileName) throws FileNotFoundException{
		
		var wordCount = ASCIIFileWordCounter.ofFile(fileName).countWords();
		assertAll("One of the conditions failed",
			() -> assertFalse(wordCount.isEmpty()),
			() -> assertFalse(wordCount.containsKey("")),
			() -> assertFalse(wordCount.containsValue(0L)),
			() -> assertTrue(wordCount.keySet().stream()
					.noneMatch(word -> word.matches("[.,:;{}()\\\\[\\\\]“—~?&!*+%\\\"=<>\\\\/ \\t#\\n\\r]+|^['-]+|['-]+$")))
		);
	}

}
