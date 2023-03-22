package prj2;

/**
 * class that implements a sorted by increasing order array of keys and various
 * methods of a binary search tree
 * 
 * @author ip
 */
public class ArraySorted {
	/**
	 * integer the keys
	 */
	private int[] keys;
	/**
	 * counts the range number
	 */
	private int rangeNum = 0;
	/**
	 * counts the search number
	 */
	private int searchNum = 0;

	/**
	 * the class constructor
	 * 
	 * @param arrayOfKeys sorted array contains the binary search tree keys
	 */
	public ArraySorted(int[] arrayOfKeys) {
		// TODO Auto-generated constructor stub
		keys = arrayOfKeys;
	}

	// the getters and setters
	public int[] getKeys() {
		return keys;
	}

	public void setKeys(int[] keys) {
		this.keys = keys;
	}

	public int getRangeNum() {
		return rangeNum;
	}

	public void setRangeNum(int rangeNum) {
		this.rangeNum = rangeNum;
	}

	public int getSearchNum() {
		return searchNum;
	}

	public void setSearchNum(int searchNum) {
		this.searchNum = searchNum;
	}

	/**
	 * increases the comparisons number that are made by search
	 * 
	 * @return always return must be true so it can be in conditions
	 */
	public boolean increaseSearchNum() {
		searchNum++;
		return true;
	}

	/**
	 * 
	 * method that finds the key with binary search
	 * 
	 * @param key key that we are searching for
	 * @return index that the key was found in(-1 if it was unsuccessful)
	 */
	public int binarySearch(int key) {
		int l = 0;
		increaseSearchNum();

		int r = keys.length - 1;
		increaseSearchNum();

		while (increaseSearchNum() && l <= r) {
			int m = l + (r - l) / 2;
			increaseSearchNum();

			// Check if its in mid
			if (increaseSearchNum() && keys[m] == key) {
				return m;
			}
			// If it's smaller and ignores the right
			if (increaseSearchNum() && keys[m] > key) {

				r = m - 1;
				increaseSearchNum();
			}

			// If it's bigger and ignores the left
			else {

				l = m + 1;
				increaseSearchNum();
			}
		}
		// return -1 if unsuccessful, the key was not found
		return -1;
	}

	/**
	 * prints all the keys(values) of the sorted array
	 */
	public void printAll() {
		for (int c = 0; c < keys.length; c++) {
			System.out.println(keys[c] + " ");
		}
	}

	/**
	 * sets the range number counter to zero.Used for executing experiments one
	 * after another
	 */
	public void setRangeNumToZero() {
		rangeNum= 0;
	}

}
