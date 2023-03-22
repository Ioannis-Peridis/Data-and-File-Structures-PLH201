package prj1.TXTEditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that contains main method, runnable class, contains the control panel
 * 
 * @author ip
 *
 */
public class MyMain {
	/**
	 * Button , protected so it can not change
	 */
	protected boolean button;
	/**
	 * Converter type parameter
	 */
	Converter converter;
	/**
	 * Array list of tuples
	 */
	ArrayList<Tuple> tuples;

	/**
	 * Class constructor(with no parameters)
	 */
	public MyMain() {
		tuples = new ArrayList<Tuple>();
		button = false;
		converter = new Converter();
	}

	/**
	 * Main method,method that implements the control panel for the user's options
	 * 
	 * @param args (auto generated main method)
	 */
	public static void main(String[] args) {
		MyMain panel = new MyMain();
		DoubleLinkedList dll = new DoubleLinkedList();
		String userOption = "";
		String input = "";
		discCommunicationAccess dca = new discCommunicationAccess("XXX.txt.ndx", 128);
		StandardInputRead reader = new StandardInputRead();
		/* Checks for exception */
		try {

			panel.readFromAsciFile(dll, args[0]);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		while (!(userOption.equals("q")) && !(userOption.equals("x"))) {

			userOption = reader.readString("What do you want to do? ");

			switch (userOption) {

			case "^":
				dll.moveFirstLine();
				System.out.println("OK");
				break;
			case "$":
				dll.moveLastLine();
				System.out.println("OK");
				break;
			case "-":
				dll.moveLineBefore();
				System.out.println("OK");
				break;
			case "+":
				dll.moveLineAfter();
				System.out.println("OK");
				break;
			case "a":
				input = reader.readString("Enter the line to add :");
				dll.addAfterCurrent(input);
				break;
			case "t":
				input = reader.readString("Enter the line to add :");
				dll.addBeforeCurrent(input);
				break;
			case "d":
				dll.delete();
				break;
			case "l":
				if (panel.button)
					dll.printLinesAndToggle();
				else
					dll.printLines();
				break;
			case "p":
				dll.printCurrentNode();
				break;
			case "q":
				System.out.println("Exit Without Save");
				break;
			case "x":
				dll.storeFile();
				System.out.println("Save and exit");
				break;
			case "w":
				dll.storeFile();
				System.out.println("File saved");
				break;
			case "=":
				dll.printLineNumber();
				break;
			case "#":
				dll.totalNumbers();
				break;
			case "n":
				panel.button = !panel.button;
				break;
			case "c":
				panel.tuples = dll.makeNewTuples();
				panel.tuples = dll.sortTuples(panel.tuples);
				dll.printTuples(panel.tuples);
				/* Checks for exception */
				try {

					dca.writeToDisk(panel.tuples);
					// dca.readFromDisc(10);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			default:
				System.out.println("giannis");
				dll.add("nikos");
				dll.add("kostas ");
				dll.add("xristoforos");
				break;
			}
		}
		System.out.println("Programm terminated");
	}

	/**
	 * Method that its used to read an ASCII file
	 * 
	 * @param name (file's  name)
	 * @param dll (double linked list type parameter)
	 * @throws IOException (type of exception)
	 */
	public void readFromAsciFile(DoubleLinkedList dll, String name) throws IOException {
		String line = "";
		File f = new File(name);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);

		while ((line = br.readLine()) != null) {

			System.out.println(line);
			dll.add(line);
		}
		fr.close();
		br.close();

		return;
	}

}
