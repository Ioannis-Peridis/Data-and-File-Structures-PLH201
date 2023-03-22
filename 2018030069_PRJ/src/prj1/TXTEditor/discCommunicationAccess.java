package prj1.TXTEditor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Class that provides communication from/to the disk(writes and reads from the
 * disk by converting bytes to tuples and reverse)
 * 
 * @author ip
 *
 */
public class discCommunicationAccess {
	/**
	 * File's name
	 */
	private String name;
	/**
	 * Current page
	 *
	 */
	protected int current = 0;
	/**
	 * Page's size
	 */
	private int size;
	/**
	 * Converter type parameter
	 */
	Converter converter;

	/**
	 * Class constructor
	 * 
	 * @param name (the file's name)
	 * @param size (the page's size)
	 */
	public discCommunicationAccess(String name, int size) {
		this.name = name;
		this.size = 128;
		converter = new Converter();
	}

	/**
	 * Method that converts tuples into bytes and writes them(to the disk) in pages
	 * 
	 * @param listOfTuples (an array list of tuples)
	 * @throws IOException (the type of the exception)
	 */
	public void writeToDisk(ArrayList<Tuple> listOfTuples) throws IOException {
		/* Check's for exception */
		try {

			RandomAccessFile f = new RandomAccessFile(name, "rw");
			f.setLength(0);
			int totalPages = converter.findTotalPageNum(listOfTuples);
			ArrayList<Tuple> t = new ArrayList<Tuple>();

			for (int c = 0; c < totalPages - 1; c++) {
				t = converter.giveTuples(listOfTuples, c * 5);
				f.write((converter.fromTupleToBytes(t)));
			}

			f.close();

		} catch (FileNotFoundException x) {
			// TODO Auto-generated catch block
			x.printStackTrace();
		}
	}

	/**
	 * Method that reads bytes(from the disk) and converts them into tuples
	 * 
	 * @param numOfPage (the number of the page)
	 * @throws IOException (the type of the exception0
	 */

	public void readFromDisc(int numOfPage) throws IOException {
		/* Check's for exception */
		try {
			
			byte[] b = new byte[100];
			RandomAccessFile f = new RandomAccessFile(name, "r");
			f.seek(0);
			
			for (int c = 0; c < 100; c++) {
				b[c] = f.readByte();
				System.out.println(b[c]);
			}
			this.converter.fromBytesToTuple(b);

			f.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
