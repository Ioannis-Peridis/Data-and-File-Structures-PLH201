package prj2;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

/**
 * 
 * Class that contains main method, runnable class, contains the control panel
 * 
 * @author ip
 */
public class Main {
	/**
	 * number of searches constant-dosen't change
	 */
	static final int SERACHES_NUM = 100;
	/**
	 * number of ranges constant-dosen't change
	 */
	static final int RANGES_NUM = 100;
	/**
	 * minimum range number constant-dosen't change
	 */
	static final int K1 = 100;
	/**
	 * maximum range number constant-dosen't change
	 */
	static final int K2 = 1000;
	/**
	 * the number of keys of the file, if you want to run another example you have
	 * to change it constant-dosen't change
	 */
	static final int N = 1000000;

	public static void main(String[] args) {
		/**
		 * Run as->Run configurations->Arguments->data path(location name of the file
		 * with data)
		 */
		String fileName = args[0];

		System.out.println("*****Binary Search Tree Dynamic*****\n");
		BinarySearchTreeDynamic bstd = new BinarySearchTreeDynamic();

		// INSERTION
		System.out.println("-------INSERTION-------");
		// timer starts
		long start = System.nanoTime();
		bstd = readBinaryDynamic(fileName);
		// timer ends
		long end = System.nanoTime();
		// time made in ms
		float time = (end - start) / 1000000F;

		System.out.println("the time needed for insertion for  " + N + " keys is: " + time + " mili seconds.");

		/**
		 * average number of comparisons per insertion
		 */
		float avgCompInsertion = (float) bstd.getInsertionNum() / (N);

		System.out.println("the average number of comparisons from the insertion is : " + avgCompInsertion + "\n\n");

		// SEARCH
		System.out.println("-------SEARCH-------");
		start = System.nanoTime();
		for (int c = 0; c < SERACHES_NUM; c++) {
			bstd.search(randomIntGenerator());
		}
		end = System.nanoTime();
		// time made in ms
		time = (end - start) / 1000000F;

		System.out.println("the time needed for search for   " + SERACHES_NUM + " keys is: " + time + " mili seconds.");

		/**
		 * average number of comparisons per search
		 */
		float avgCompSearch = (float) bstd.getSearchNum() / SERACHES_NUM;

		System.out.println("the average number of comparisons from the search is : " + avgCompSearch + "\n\n");

		// RANGE
		System.out.println("-------RANGE-------");
		for (int i = 0; i < RANGES_NUM; i++) {
			int num = randomIntGenerator();
			bstd.range(num, num + K1);
		}

		/**
		 * average number of comparisons per range
		 */
		float avgCompRange = (float) bstd.getRangeNum() / RANGES_NUM;

		System.out.println("for range distance" + K1 + " the average number of comparisons from the range is: "+ avgCompRange);

		// setting range number to zero so we can perform the next experiment in the row
		// with different range
		bstd.setRangeNumToZero();

		for (int c = 0; c < RANGES_NUM; c++) {
			int r = randomIntGenerator();
			bstd.range(r, r + K2);
		}
		avgCompRange = (float) bstd.getRangeNum() / RANGES_NUM;

		System.out.println("for range distance " + K2 + " the average number of comparisons from the range is:"+ avgCompRange+"\n\n");
		// setting range number to zero so we can perform the next experiment in the row
		// with different range
		bstd.setRangeNumToZero();

		bstd.inorder();
		System.out.println("***********************************\n\n");


		System.out.println("*****Binary Search Tree Static*****\n");
		BinarySearchTreeStatic sbst = null;

		// INSERTION
		System.out.println("-------INSERTION-------");
		// timer starts
		start = System.nanoTime();
		sbst = readBinaryStatic(fileName, N);
		// timer ends
		end = System.nanoTime();

		// time needed in ms
		time = (end - start) / 1000000F;

		System.out.println(
				"the time needed for insertion for " + sbst.getNumOfKeys() + " keys is: " + time + " mili seconds.");
		/**
		 * average number of comparisons per insertion
		 */
		avgCompInsertion = (float) sbst.getInsertionNum() / sbst.getNumOfKeys();

		System.out.println("the average number of comparisons from the insertion is: " + avgCompInsertion + "\n\n");

		// SEARCH
		System.out.println("-------SEARCH-------");

		start = System.nanoTime();
		for (int c = 0; c < SERACHES_NUM; c++) {
			sbst.search(randomIntGenerator());
		}
		end = System.nanoTime();
		time = (end - start) / 1000000F;

		System.out.println("the time needed for search for " + SERACHES_NUM + " keys is: " + time + " mili seconds");
		System.out.println("the average number of comparisons from the search is: " + avgCompSearch + "\n\n");

		System.out.println("-------RANGE-------");
		for (int c = 0; c < RANGES_NUM; c++) {
			int r = randomIntGenerator();
			sbst.range(r, r + K1);
		}

		/**
		 * average number of comparisons per range
		 */
		avgCompRange = (float) sbst.getRangeNum() / RANGES_NUM;
		System.out.println("for range distance " + K1 + " the average number of comparisons from the range is: "+ avgCompRange);

		// setting range number to zero so we can perform the next experiment in the row
		// with different range
		sbst.setRangeNumToZero();

		for (int c = 0; c < RANGES_NUM; c++) {
			int r = randomIntGenerator();
			sbst.range(r, r + K2);
		}

		avgCompRange = (float) sbst.getRangeNum() / RANGES_NUM;

		System.out.println("for range distance " + K2 + " the average number of comparisons from the range is: "+ avgCompRange);
		// setting range number to zero so we can perform the next experiment in the row
		// with different range
		sbst.setRangeNumToZero();

		/**
		 * average number of comparisons per search
		 */
		avgCompSearch = (float) sbst.getSearchNum() / SERACHES_NUM;

		System.out.println("**********************************************\n\n");
		

		System.out.println("*****sorted array filled with tree's keys*****");

		ArraySorted arraySorted = new ArraySorted(bstd.getArrayOfKeys());

		System.out.println("-------SEARCH-------");
		start = System.nanoTime();
		for (int c = 0; c < SERACHES_NUM; c++) {
			arraySorted.binarySearch(randomIntGenerator());
		}
		end = System.nanoTime();
		time = (end - start) / 1000000F;

		System.out.println("the time needed for search for  " + SERACHES_NUM + " keys is: " + time + " mili seconds\n");
		avgCompSearch = (float) arraySorted.getSearchNum() / SERACHES_NUM;

		System.out.println("the average number of comparisons from the search is: " + avgCompSearch + "\n\n");

	}

	/**
	 * Method that generates a random integer.
	 * 
	 * @return A random integer.
	 */
	public static int randomIntGenerator() {
		Random r = new Random();
		return r.nextInt();
	}

	/**
	 * 
	 * it initializes the binary search tree dynamic from the location given
	 * 
	 * @param fileName data path that the file was saved
	 * @return binary search tree dynamic with the given data
	 */
	public static BinarySearchTreeDynamic readBinaryDynamic(String fileName) {
		BinarySearchTreeDynamic bstd = new BinarySearchTreeDynamic();
		DataInputStream in = null;
		try {
			in = new DataInputStream(new FileInputStream(fileName)); //
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (true) {
				bstd.insert(in.readInt());
			}
		} catch (EOFException ignored) {
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in.close(); //
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bstd;
	}

	/**
	 * it initializes the binary search tree dynamic from the location given
	 * 
	 * @param fileName data path that the file was saved
	 * @param N        the size of static binary tree
	 * @return binary search tree dynamic with the given data
	 */
	public static BinarySearchTreeStatic readBinaryStatic(String fileName, int N) {
		BinarySearchTreeStatic bsts = new BinarySearchTreeStatic(N);
		DataInputStream in = null;
		try {
			in = new DataInputStream(new FileInputStream(fileName)); //
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (true) {
				bsts.insert(in.readInt());
			}
		} catch (EOFException ignored) {
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in.close(); //
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bsts;
	}

}
