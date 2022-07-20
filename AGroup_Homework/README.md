Eclipse project of ASCIIFileWordCounter, including JUnit tests

Requirements:

Below you can see the task:
There is a file somewhere on the disk with ASCII text (any book in English).
JAVA program should take the name of the file as a program argument.
It should parse file text and identify all separate words.
Then it should count every word occurrences and print out in System.out all identified words and number of their occurrences in specific order: more popular words come first.
Solution could not use JAVA streams. Or, even better, you may provide two solutions: with JAVA streams and without it.

Possible implementation of methods using Stream API is provided as comments in applicable methods' bodies

# USAGE 

Count number of occurrence of each unique word in an ASCII file specified by the argument

A demo .jar built in openjdk-11 with some example files is provided in 'demo/' folder. Example of running the demo (assuming that it is run from 'demo/':

java -jar WordCounter.jar resources/lorem-ipsum.txt
