import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import java.io.EOFException;

/**
 * class that contains main method, runnable class, contains the control panel
 * 
 * FUNCTIONALITY:
 * -reads file and each time we insert 100 numbers
 * -searches 50 random numbers
 * -deletes 50 random numbers
 * 
 * @author ip
 */

public class Main {

	/**
	 * The size of all buckets that are going to be used
	 */
	public static final int HASH_BUCKET_SIZE = 10;
	// To run the class and initialize it, you have to go to Run as->Run
	// configurations->Arguments ->and insert there the data path of the given input
	// files

	/**
	 * main method
	 * 
	 * @param args the file's name
	 */
	public static void main(String[] args) {
		Main main = new Main();
		LinearHashing lh = new LinearHashing(Main.HASH_BUCKET_SIZE, 100, (float) 0.8);
		LinearHashing lh2 = new LinearHashing(Main.HASH_BUCKET_SIZE, 100, (float) 0.5);
		BinarySearchTreeDynamic bstd = new BinarySearchTreeDynamic();
		try {
			FileInputStream fis = new FileInputStream(args[0]);
			DataInputStream dis = new DataInputStream(fis);
			FileInputStream fis2 = new FileInputStream(args[0]);
			DataInputStream dis2 = new DataInputStream(fis2);
			FileInputStream fis3 = new FileInputStream(args[0]);
			DataInputStream dis3 = new DataInputStream(fis3);
			int counter = 0;
			System.out.println(
					"Input size(N)   \t LH u>50%   \t LH u>50%   \t LH u>50%   \t LH u>80%   \t LH u>80%   \t LH u>80%   \t   BST      \t   BST      \t   BST    ");
			System.out.println(
					"        \t\t   avg#       \t   avg#       \t   avg#       \t   avg#       \t   avg#       \t   avg#       \t   avg#      \t   avg#      \t   avg#    ");
			System.out.println("        \t\tcomparisons" + "    \tcomparisons" + "    \tcomparisons"
					+ "    \tcomparisons" + "    \tcomparisons" + "    \tcomparisons" + "    \tcomparisons"
					+ "    \tcomparisons" + "    \tcomparisons");
			System.out.println(
					"        \t\tper insertion \tper search \tper delete \tper insertion \tper search \tper delete \tper insertion \tper search \tper delete");
			try {
				for (int c = 0; c < 100; c++) {
					ArrayList<Integer> arrayList = new ArrayList<Integer>();
					ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
					int counterInsertion = 0;
					int counterDelete = 0;
					int counterSearch = 0;
					Random r = new Random();

					for (int i = 0; i < 100; i++) {
						counterInsertion += lh2.insertKey(counter = dis3.readInt());
						lh2.setKeySpace(10 * lh2.numberOfHashBuckets);
						arrayList2.add(counter);
						lh2.counterForComparisons = 0;
					}
					System.out
							.print(((c + 1) * 100) + "         \t\t " + (float) counterInsertion / (float) 100 + "\t");
					int g;
					for (int i = 0; i < 50; i++) {
						boolean b = lh2.searchKey(arrayList2.get((g = r.nextInt(arrayList2.size() - 1))));
						counterSearch += lh2.counterForComparisons;
						lh2.counterForComparisons = 0;
					}

					System.out.print(" \t " + (float) counterSearch / (float) 50 + "\t");

					for (int i = 0; i < 50; i++) {
						counterDelete += lh2.deleteKey(r.nextInt((g = arrayList2.size() - 1)));
						arrayList2.remove(g);
						lh2.setKeySpace(10 * lh2.numberOfHashBuckets);
						lh2.counterForComparisons = 0;
					}
					System.out.print("\t" + (float) counterDelete / (float) 50 + "\t");

					counterDelete = 0;
					counterInsertion = 0;
					counterSearch = 0;

					for (int i = 0; i < 100; i++) {
						counterInsertion += lh.insertKey(counter = dis.readInt());
						lh.setKeySpace(10 * lh.numberOfHashBuckets);
						arrayList.add(counter);
						lh.counterForComparisons = 0;
					}
					System.out.print("\t" + (float) counterInsertion / (float) 100 + "\t");

					for (int i = 0; i < 50; i++) {
						lh.searchKey(r.nextInt(arrayList.size() - 1));
						counterSearch += lh.counterForComparisons;
						lh.counterForComparisons = 0;
					}

					System.out.print("\t" + (float) counterSearch / (float) 50 + "\t");

					for (int i = 0; i < 50; i++) {
						counterDelete += lh.deleteKey(r.nextInt((g = arrayList.size() - 1)));
						arrayList.remove(g);
						lh.setKeySpace(10 * lh.numberOfHashBuckets);
						lh.counterForComparisons = 0;
					}
					System.out.print("\t" + (float) counterDelete / (float) 50 + "\t");

					counterInsertion = 0;
					counterSearch = 0;
					counterDelete = 0;
					bstd.setInsertionNumToZero();
					for (int i = 0; i < 100; i++) {
						bstd.insert(dis2.readInt());
					}
					counterInsertion += bstd.getInsertionNum();
					System.out.print("\t" + (float) counterInsertion / (float) 100 + "\t");

					bstd.setSearchNumToZero();
					for (int i = 0; i < 50; i++) {
						bstd.search(arrayList.get(i));
					}
					counterSearch += bstd.getSearchNum();
					System.out.print("\t" + (float) counterSearch / (float) 50 + "\t");

					bstd.setDeletionNumToZero();
					for (int i = 0; i < 50; i++) {
						bstd.delete(arrayList.get(i));
					}
					counterDelete += bstd.getDeletionNum();
					System.out.println("\t " + (float) counterDelete / (float) 50 + "\t");

				}

			} catch (EOFException ignored) {
				System.out.println("[EOF]");
			}

			dis.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
