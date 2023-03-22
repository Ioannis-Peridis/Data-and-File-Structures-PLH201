package prj1.TXTEditor;

/**
 * Implementation of a tuple(the word and the line that it is written)
 * 
 * @author ip
 *
 */

public class Tuple implements Comparable<Tuple> {
	/**
	 * word
	 */
	String word;
	/**
	 * numberOfLine
	 */
	int numberOfLine;

	/**
	 * Class constructor
	 * 
	 * @param word(the word)
	 * @param numberOfLine( the number of the line)
	 */
	public Tuple(String word, int numberOfLine) {
		this.word = word;
		this.numberOfLine = numberOfLine;
	}

	/**
	 * Method that compares two words and finds the biggest(alphabetically)
	 * 
	 * @param tuple (the word and the line that it is written on)
	 * @return integer(1,0, or -1 , it depends from the if statement)
	 */
	@Override
	public int compareTo(Tuple tuple) {
		/* Checks if the first is bigger */
		if (this.word.compareTo(tuple.word) > 0)
			return 1;
		/* Checks if they are same */
		else if (this.word.compareTo(tuple.word) == 0)
			return 0;
		/* General case, second its bigger */
		else
			return -1;
	}

}
