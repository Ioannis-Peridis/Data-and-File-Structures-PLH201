package prj1.TXTEditor;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Class that converts the tuples to bytes array and reverse
 * 
 * @author ip
 *
 */
public class Converter {
	/**
	 * The size of a page
	 */
	static int size = 128;
	/**
	 * The maximum size of a word
	 */
	static int sizeOfWord = 20;
	/**
	 * The size of a tuple,basically the size of the word plus 4
	 */
	static int sizeOfTuple = 4 + sizeOfWord;
	/**
	 * The maximum number of tuples that can fit into a page
	 */
	static int maxTuplesInPage = size / sizeOfTuple;

	/**
	 * Method that converts an array of bytes to a tuple
	 * 
	 * @param arrayOfBytes (the byte array)
	 */
	public void fromBytesToTuple(byte[] arrayOfBytes) {

		ByteBuffer b = ByteBuffer.wrap(arrayOfBytes);
		int i = b.getInt();
		byte byteArray[] = new byte[100];
		// gets 20 bytes into the byteArray from the ByteBuffer , with 10 offset
		b.get(byteArray, 10, 20);

		for (int c = 0; c < size; c++) {
			String string = new String(byteArray, java.nio.charset.StandardCharsets.US_ASCII);

			System.out.println(string + i);
		}
	}

	/**
	 * Method that converts a tuple to an array of bytes
	 * 
	 * @param listOfTuples (array list of tuples)
	 * @return bytesOfPage (the number of bytes in a page)
	 */

	public byte[] fromTupleToBytes(ArrayList<Tuple> listOfTuples) {
		byte bytesOfPage[] = null;
		java.nio.ByteBuffer b = java.nio.ByteBuffer.allocate(size);

		for (int c = 0; c < listOfTuples.size(); c++) {
			if (b.position() != c * sizeOfTuple) {
				this.alignToWord(c, b, b.position());
			}
			b.putInt(listOfTuples.get(c).numberOfLine);
			b.put(listOfTuples.get(c).word.getBytes(java.nio.charset.StandardCharsets.US_ASCII));
		}
		bytesOfPage = b.array();
		return bytesOfPage;

	}

	/**
	 * Method that aligns to every word to the maximum word size,by filling it out
	 * with spaces
	 * 
	 * @param i (integer)
	 * @param position (the position)
	 * @param b (byteBuffer parameter)
	 */
	public void alignToWord(int i, ByteBuffer b, int position) {

		for (int c = position; c < i * sizeOfTuple; c++) {
			b.put(" ".getBytes(java.nio.charset.StandardCharsets.US_ASCII));
		}
	}

	/**
	 * Method that gives you the number of tuples that will be written in every page
	 * 
	 * @param listOfTuples(an array list of tuples)
	 * @param current(the current 
	 * @return t ( the size of the list of tuples)
	 */
	public ArrayList<Tuple> giveTuples(ArrayList<Tuple> listOfTuples, int current) {
		ArrayList<Tuple> t = new ArrayList<Tuple>();

		for (int c = current; c < current + maxTuplesInPage; c++) {
			t.add(listOfTuples.get(c));

			if (c == listOfTuples.size() - 1) {
				break;
			}
		}
		return t;
	}

	/**
	 * Method that gives you the total number of pages
	 * 
	 * @param listOfTuples(an array list of tuples)
	 * @return totalPageNum(the total pages number)
	 */
	public int findTotalPageNum(ArrayList<Tuple> listOfTuples) {

		int totalPageNum = (listOfTuples.size() / maxTuplesInPage) + 1;
		return totalPageNum;
	}

}
