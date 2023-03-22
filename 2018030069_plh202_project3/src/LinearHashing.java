/**
 * implementation of linear hashing, its a dynamic data structure which
 * implements a hash table,(hash table its a data structure that implements an
 * associative array abstract data type, a structure that can map keys to
 * values.)
 * 
 * @author ip
 */
public class LinearHashing {

	/**
	 * array of hashBuckets
	 */
	private HashBucket[] HashBuckets;

	/**
	 * the max load factor
	 */
	private float maxLoadFactor;
	/**
	 * the minimum load factor
	 */
	private float minLoadFactor;
	/**
	 * the size of HashBuckets
	 */
	private int HashBucketSize;

	/**
	 * the number of keys in all the hashBuckets(at the moment)
	 */
	private int numOfKeys;
	/**
	 * total key space of the hash table
	 */
	private int keySpace;

	/**
	 * pointer that points to the next hashBucket that is going to be split
	 */
	private int p;

	/**
	 * the number of hashBuckets at the moment
	 */
	public int numberOfHashBuckets;

	/**
	 * it is used for the hashFunction
	 */
	private int j;

	/**
	 * the minimum number of hashBuckets the hash table can have
	 */
	private int minHashBuckets;

	public static int counterForComparisons = 0;

	/**
	 * the class constructor
	 * 
	 * @param itsHashBucketSize the hashBucket size
	 * @param initPages         minimum HashBuckets
	 * @param MAXloadFactor     the max load factor
	 */
	public LinearHashing(int itsHashBucketSize, int initPages, float MAXloadFactor) { // Constructor.

		int i;

		HashBucketSize = itsHashBucketSize;
		numOfKeys = 0;
		p = 0;
		numberOfHashBuckets = initPages;
		j = initPages;
		minHashBuckets = initPages;
		keySpace = numberOfHashBuckets * HashBucketSize;
		maxLoadFactor = MAXloadFactor;
		minLoadFactor = (float) 0.5;

		if ((HashBucketSize == 0) || (numberOfHashBuckets == 0)) {
			System.out.println("error: space for the table cannot be 0");
			System.exit(1);
		}
		HashBuckets = new HashBucket[numberOfHashBuckets];
		for (i = 0; i < numberOfHashBuckets; i++) {
			HashBuckets[i] = new HashBucket(HashBucketSize);
		}
	}
	// the getters and setters

	public HashBucket[] getHashBuckets() {
		return HashBuckets;
	}

	public void setHashBuckets(HashBucket[] hashBuckets) {
		HashBuckets = hashBuckets;
	}

	public float getMaxLoadFactor() {
		return maxLoadFactor;
	}

	public void setMaxLoadFactor(float maxLoadFactor) {
		this.maxLoadFactor = maxLoadFactor;
	}

	public float getMinLoadFactor() {
		return minLoadFactor;
	}

	public void setMinLoadFactor(float minLoadFactor) {
		this.minLoadFactor = minLoadFactor;
	}

	public int getHashBucketSize() {
		return HashBucketSize;
	}

	public void setHashBucketSize(int hashBucketSize) {
		HashBucketSize = hashBucketSize;
	}

	public int getNumOfKeys() {
		return numOfKeys;
	}

	public void setNumOfKeys(int numOfKeys) {
		this.numOfKeys = numOfKeys;
	}

	public int getKeySpace() {
		return keySpace;
	}

	public void setKeySpace(int keySpace) {
		this.keySpace = keySpace;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public int getNumberOfHashBuckets() {
		return numberOfHashBuckets;
	}

	public void setNumberOfHashBuckets(int numberOfHashBuckets) {
		this.numberOfHashBuckets = numberOfHashBuckets;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public int getMinHashBuckets() {
		return minHashBuckets;
	}

	public void setMinHashBuckets(int minHashBuckets) {
		this.minHashBuckets = minHashBuckets;
	}

	public static int getCounterForComparisons() {
		return counterForComparisons;
	}

	public static void setCounterForComparisons(int counterForComparisons) {
		LinearHashing.counterForComparisons = counterForComparisons;
	}

	/**
	 * it finds the position of the key
	 * 
	 * @param key the key
	 * @return the position of the hash table that it was found
	 */
	private int hashFunction(int key) {

		int retval;

		retval = key % this.j;
		increaseCompareCounter();
		if (increaseCompareCounter() && retval < 0) {
			retval *= -1;
			increaseCompareCounter();
		}
		if (increaseCompareCounter() && retval >= p) {
			return retval;
		} else {
			increaseCompareCounter();
			retval = key % (2 * this.j);
			if (increaseCompareCounter() && retval < 0) {
				retval *= -1;
				increaseCompareCounter();
			}
			return retval;
		}
	}

	/**
	 * it returns the load factor of the hash table
	 * 
	 * @return the load factor of the hash table
	 */
	private float loadFactor() { // Returns the current load factor of the hash table.

		return ((float) this.numOfKeys) / ((float) this.keySpace);
	}

	/**
	 * it starts the split of the hashBucket that it was pointed by p
	 */
	private void HashBucketSplit() { // Splits the HashBucket pointed by p.

		int i;
		HashBucket[] newHashBuckets;

		newHashBuckets = new HashBucket[numberOfHashBuckets + 1];
		for (i = 0; i < this.numberOfHashBuckets && increaseCompareCounter(); i++) {
			newHashBuckets[i] = this.HashBuckets[i];
			increaseCompareCounter();
		}

		HashBuckets = newHashBuckets;
		increaseCompareCounter();
		HashBuckets[this.numberOfHashBuckets] = new HashBucket(this.HashBucketSize);
		increaseCompareCounter();
		this.keySpace += this.HashBucketSize;
		increaseCompareCounter();
		this.HashBuckets[this.p].splitHashBucket(this, 2 * this.j, this.p, HashBuckets[this.numberOfHashBuckets]);
		increaseCompareCounter();
		this.numberOfHashBuckets++;
		increaseCompareCounter();
		if (increaseCompareCounter() && this.numberOfHashBuckets == 2 * this.j) {
			this.j = 2 * this.j;
			increaseCompareCounter();
			this.p = 0;
			increaseCompareCounter();
		} else {
			this.p++;
			increaseCompareCounter();
		}
	}

	/**
	 * it merges the last hashBucket that it was split
	 */
	private void HashBucketMerge() { // Merges the last HashBucket that was split

		int i;

		HashBucket[] newHashBuckets;
		newHashBuckets = new HashBucket[numberOfHashBuckets - 1];
		for (i = 0; i < this.numberOfHashBuckets - 1 && increaseCompareCounter(); i++) {
			newHashBuckets[i] = this.HashBuckets[i];
			increaseCompareCounter();
		}
		if (increaseCompareCounter() && this.p == 0) {
			this.j = (this.numberOfHashBuckets) / 2;
			increaseCompareCounter();
			this.p = this.j - 1;
			increaseCompareCounter();
		} else {
			this.p--;
			increaseCompareCounter();
		}
		this.numberOfHashBuckets--;
		increaseCompareCounter();
		this.keySpace -= this.HashBucketSize;
		increaseCompareCounter();
		this.HashBuckets[this.p].mergeHashBucket(this, HashBuckets[this.numberOfHashBuckets]);
		increaseCompareCounter();
		HashBuckets = newHashBuckets;
	}

	/**
	 * it increases the counter for comparisons
	 * 
	 * @return always true
	 */
	public boolean increaseCompareCounter() {
		counterForComparisons++;
		return true;
	}

	/**
	 * it inserts a new key
	 * 
	 * @param key the key we want to insert
	 * @return the number of comparisons made during the insertion
	 */
	public int insertKey(int key) { // Insert a new key.

		// System.out.println( "HashBuckets[" + this.hashFunction(key) + "] = " + key);
		this.HashBuckets[this.hashFunction(key)].insertKey(key, this);
		if (increaseCompareCounter() && this.loadFactor() > maxLoadFactor) {
			// System.out.println("loadFactor = " + this.loadFactor() );
			this.HashBucketSplit();
			// System.out.println("HashBucketSplit++++++");
		}
		return counterForComparisons;
	}

	/**
	 * it deletes a key
	 * 
	 * @param key the key we want to delete
	 * @return the number of comparisons made during the deletion
	 */
	public int deleteKey(int key) { // Delete a key.

		this.HashBuckets[this.hashFunction(key)].deleteKey(key, this);
		if (increaseCompareCounter() && this.loadFactor() > maxLoadFactor) {
			this.HashBucketSplit();
		} else if ((increaseCompareCounter() && this.loadFactor() < minLoadFactor)
				&& (increaseCompareCounter() && this.numberOfHashBuckets > this.minHashBuckets)) {
			this.HashBucketMerge();
		}
		return counterForComparisons;
	}

	/**
	 * it searches for a key
	 * 
	 * @param key the key we want to search
	 * @return it returns true if the key was found , or else false in any other
	 *         case
	 */
	public boolean searchKey(int key) { // Search for a key.

		return this.HashBuckets[this.hashFunction(key)].searchKey(key, this);
	}

	/**
	 * it prints all the hash table's informations
	 */
	public void printHash() {

		int i;

		for (i = 0; i < this.numberOfHashBuckets; i++) {
			System.out.println("HashBucket[" + i + "]");
			this.HashBuckets[i].printHashBucket(this.HashBucketSize);
		}
	}

}
