package prj1.TXTEditor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Implementation of a double linked list and all of its functions
 * 
 * @author ip
 *
 */
public class DoubleLinkedList {
	/**
	 * Head node, it represents the 1st line of the file
	 */
	private DLLnode head;

	/**
	 * Current node, it represents the line that has the cursor
	 */
	private DLLnode current;

	/**
	 * Max_characters_line,it represents the max number of characters that a line
	 * can have
	 */
	static int max_characters_line = 80;

	/**
	 * Class constructor (without parameters)
	 */
	public DoubleLinkedList() {
		head = null;
		current = null;
	}

	/**
	 * Method that adds a line(node)
	 * 
	 * @param line (a line represents a node)
	 */
	public void add(String line) {
		this.addEnd(line);
	}

	/**
	 *
	 * Method that adds a line(node) before the current line(node)
	 * 
	 * @param line(a line represents a node)
	 */
	public void addBeforeCurrent(String line) {
		/* Checks for null */
		DLLnode newNode = new DLLnode(line);

		if (head == null) {
			head = newNode;
			current = head;
			return;
		}
		/* Checks if head node is the current node */
		if (head == current) {
			newNode.next = current;
			current.before = newNode;
			head = newNode;
			return;
		}
		/* General case */
		DLLnode beforeNode = current.before;
		beforeNode.next = newNode;
		newNode.next = current;
		current.before = newNode;
		newNode.before = beforeNode;
	}

	/**
	 * 
	 * Method that adds a line(node) after the current line(node)
	 * 
	 * @param line(a line represents a node)
	 */

	public void addAfterCurrent(String line) {

		DLLnode newNode = new DLLnode(line);

		/* Checks for null */
		if (head == null) {
			head = newNode;
			current = head;
			return;
		}
		/* Checks if head node is the current node */
		if (current.next == null) {
			current.next = newNode;
			newNode.before = current;
			return;
		}
		/* General case */
		DLLnode nextNode = current.next;
		current.next = newNode;
		newNode.next = nextNode;
		nextNode.before = newNode;
		newNode.before = current;
	}

	/**
	 * Method that adds a line(node) after the last line(node)
	 * 
	 * @param line(a line represents a node)
	 */
	public void addEnd(String line) {
		DLLnode newNode = new DLLnode(line);

		/* Checks for null */
		if (head == null) {
			head = newNode;
			current = head;
			return;
		}

		DLLnode lastNode = head;

		newNode.next = null;

		while (lastNode.next != null)
			lastNode = lastNode.next;

		lastNode.next = newNode;
		newNode.before = lastNode;
	}

	/**
	 * Method that deletes the current line(node)
	 * 
	 */
	public void delete() {
		DLLnode beforeNode = current.before;
		DLLnode nodeNext = current.next;

		/* Checks for null */
		if (head == null) {
			System.out.println("There is not any nodes to delete");
			return;
		}

		/* Checks if head node is the current node */
		if (head == current) {
			current.next = null;
			nodeNext.before = null;
			current = nodeNext;
			head = current;
			return;
		}
		/* Checks if current node is the last node */
		if (current.next == null) {
			beforeNode.next = null;
			current.before = null;
			current = beforeNode;
			return;
		}
		/* General case */
		beforeNode.next = nodeNext;
		nodeNext.before = beforeNode;
		current.next = null;
		current.before = null;
		current = nodeNext;
	}

	/**
	 * 
	 * Method that prints all lines (without line number)
	 * 
	 */

	public void printLines() {
		DLLnode node = head;

		while (node != null) {
			System.out.println(node.getKey());
			node = node.next;
		}
	}

	/**
	 * Method that prints all lines (with line number first)
	 * 
	 */

	public void printLinesAndToggle() {
		int i = 0;
		DLLnode node = head;

		while (node != null) {
			i++;
			System.out.println(i + ") " + node.getKey());
			node = node.next;
		}
	}

	/**
	 * 
	 * Method that prints current's line(node) data
	 * 
	 */

	public void printCurrentNode() {
		System.out.println(current.getKey());
	}

	/**
	 * 
	 * Method that moves the cursor to the 1st line(node)
	 * 
	 */
	public void moveFirstLine() {
		this.current = head;
	}

	/**
	 * 
	 * Method that moves the cursor to the line(node) before the current one
	 * 
	 */

	public void moveLineBefore() {

		/* Checks if head node is the current node */
		if (current == head) {
			System.out.println("Cursor can't go in line before , because its already in the first line");
			return;
		}
		current = current.before;
	}

	/**
	 * 
	 * Method that moves the cursor to the line(node) after the current one
	 * 
	 * 
	 */

	public void moveLineAfter() {

		/* Checks if last node is the current node */
		if (current.next == null) {
			System.out.println("Cursor can't go in line after , because its already in the last line");
			return;
		}
		current = current.next;
	}

	/**
	 * 
	 * Method that moves the cursor to the last line(node)
	 * 
	 */

	public void moveLastLine() {
		while (current.next != null) {
			current = current.next;
		}
	}

	/**
	 * 
	 * Method that creates and saves the file
	 * 
	 * @return boolean(it represents if file was saved or not)
	 */

	public boolean storeFile() {
		File rFile = new File("files" + File.separator + "XXX");
		BufferedWriter writer = null;

		try {

			writer = new BufferedWriter(new FileWriter(rFile));
			writer.write(this.toString());

		} catch (IOException e) {
			System.out.println("Could not store recipe ");
			return false;

		} finally {
			try {

				writer.close();

			} catch (Exception e) {

			}
		}
		return true;
	}

	/**
	 * 
	 * Method that makes one string with all the list's informations
	 * 
	 * @return a string with all the list information
	 */

	public String toString() {
		StringBuilder sb = new StringBuilder("");
		DLLnode node = head;

		while (node != null) {
			sb.append(node.getKey()).append("\n");
			node = node.next;
		}
		return sb.toString();
	}

	/**
	 * 
	 * Method that finds and prints the current line(node) number
	 * 
	 */

	public void printLineNumber() {
		int x = 1;
		DLLnode node = head;

		/* Checks for null */
		if (node == null) {
			System.out.println("Empty list");
			return;
		}

		while (node != current) {
			node = node.next;
			x++;
		}
		System.out.println("Current line number is " + x);
	}

	/**
	 * 
	 * Method that finds the total number of lines(nodes) and characters
	 */

	public void totalNumbers() {
		int j = 1;
		int characters = 0;
		DLLnode node = head;

		/* Checks for null */
		if (node == null) {
			System.out.println("Empty list");
			return;
		}

		while (node != null) {
			characters += node.getKey().length();
			node = node.next;
			j++;
		}

		System.out.println(j + " lines , " + characters + " Characters ");
	}

	/**
	 * 
	 * Method that makes a list for all our file's words( with line number)
	 * 
	 * @return tuples(array list of tuples)
	 */

	public ArrayList<Tuple> makeNewTuples() {
		int i = 0;
		/* parameter that counts */
		int j = 0;
		ArrayList<Tuple> tuples = new ArrayList<Tuple>();
		DLLnode node = head;
		String string = "\\P{Alpha}+";

		while (node != null) {
			String[] result = node.getKey().split(string);
			j++;

			for (int c = 0; c < result.length; c++) {
				/* Checks if length is less than 5 */
				if (result[c].length() < 5) {
					System.out.println(result[c] + " is not considered");
				} else {
					/* Checks if length is more than 80 */
					if (result[c].length() > 80) {
						result[c] = result[c].substring(0, 80);
					}
					tuples.add(new Tuple(result[c], j));
					System.out.println(tuples.get(i).word);
					i++;
				}
			}
			node = node.next;
		}

		return tuples;
	}

	/**
	 * Method that prints the tuple list
	 * @param listOfTuples ( an array list of tuples)
	 */

	public void printTuples(ArrayList<Tuple> listOfTuples) {

		for (int c = 0; c < listOfTuples.size(); c++) {
			System.out.println(listOfTuples.get(c).word + ", line " + listOfTuples.get(c).numberOfLine);
		}
	}

	/**
	 * Method that sorts the tuples with alphabetical order
	 * 
	 * @param listOfTuples(normal-random array list of tuples)
	 * @return listOfTuples(sorted array list of tuples)
	 */

	public ArrayList<Tuple> sortTuples(ArrayList<Tuple> listOfTuples) {
		Collections.sort(listOfTuples);
		return listOfTuples;
	}

}
