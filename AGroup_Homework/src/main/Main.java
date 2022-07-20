package main;

import java.io.FileNotFoundException;

import java.util.Comparator;
import java.util.Map.Entry;

public class Main {
	
	public static void main(String[] args) {
		try {
			ASCIIFileWordCounter.ofFile(args[0])
				.countWordsSorted(Entry.comparingByValue(Comparator.reverseOrder()))
				.forEach((e) -> System.out.println(e.getKey() + ": " + e.getValue()));
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ERROR: no file specified.\n");
		}
		catch (FileNotFoundException e) {
			System.out.println("ERROR: file not found.\n");
		}
	}
}
