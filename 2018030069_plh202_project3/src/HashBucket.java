/**
 * implementation of hash bucket,contains the parts(with their values) that
 * construct a hash table .  An array of buckets or slots, from which the desired
 * value can be found.
 * 
 * @author ip
 */
public class HashBucket {

	/**
	 * the total number of keys a HashBucket
	 * 
	 */
	public int numOfKeys;

	/**
	 * array of keys of a HashBucket
	 * 
	 */
	public int[] arrayOfKeys;

	/**
	 * overflow HashBucket
	 */
	public HashBucket overflowHashBucket;

	/**
	 * the class constructor
	 * 
	 * @param HashBucketSize size of the HashBucket
	 */
	public HashBucket(int HashBucketSize) {

		numOfKeys = 0;
		arrayOfKeys = new int[HashBucketSize];
		overflowHashBucket = null;
	}

	/**
	 * it returns the number of keys in a hashBucket
	 * 
	 * @return total number of keys in a hashBucket
	 */
	public int numKeys() {
		return numOfKeys;
	}

	/**
	 * it inserts a key in a hashBucket
	 * 
	 * @param key the key we want to insert
	 * @param lh  the hasBucket's hash table
	 */
	public void insertKey(int key, LinearHashing lh) { // inserts a key to the node

		int i;
		int HashBucketSize = lh.getHashBucketSize();
		int keysNum = lh.getNumOfKeys();

		for (i = 0; (lh.increaseCompareCounter() && i < this.numOfKeys)
				&& (lh.increaseCompareCounter() && i < HashBucketSize); i++) {
			if (lh.increaseCompareCounter() && this.arrayOfKeys[i] == key) { // key already here. Ignore the new one
				return;
			}
		}
		if (lh.increaseCompareCounter() && i < HashBucketSize) { // HashBucket not full write the new key
			arrayOfKeys[i] = key;
			lh.increaseCompareCounter();
			this.numOfKeys++;
			lh.increaseCompareCounter();
			keysNum++;
			lh.setNumOfKeys(keysNum);
			lh.increaseCompareCounter();// update linear hashing class.
			// System.out.println("HashBucket.insertKey: KeysNum = " + keysNum );
		} else {
			// System.out.println("Overflow.............");
			if (lh.increaseCompareCounter() && this.overflowHashBucket != null) { // pass key to the overflow
				this.overflowHashBucket.insertKey(key, lh);

			} else { // create a new overflow and write the new key
				this.overflowHashBucket = new HashBucket(HashBucketSize);
				lh.increaseCompareCounter();
				this.overflowHashBucket.insertKey(key, lh);
			}
		}
	}

	/**
	 * it deletes a key from a hashBucket
	 * 
	 * @param key the key we want to delete
	 * @param lh  The hash table that this HashBucket belongs
	 */
	public void deleteKey(int key, LinearHashing lh) { // code not correct

		int i;
		int HashBucketSize = lh.getHashBucketSize();
		int keysNum = lh.getNumOfKeys();

		for (i = 0; (lh.increaseCompareCounter() && i < this.numOfKeys)
				&& (lh.increaseCompareCounter() && i < HashBucketSize); i++) {
			if (lh.increaseCompareCounter() && this.arrayOfKeys[i] == key) {
				if (lh.increaseCompareCounter() && this.overflowHashBucket == null) { // no overflow
					this.arrayOfKeys[i] = removeLastKey(lh);
					lh.increaseCompareCounter();
					this.numOfKeys--;
					lh.increaseCompareCounter();
					keysNum--;
					lh.setNumOfKeys(keysNum); // update linear hashing class.
					lh.increaseCompareCounter();
				} else { // HashBucket has an overflow so remove a key from there and bring it here
					this.arrayOfKeys[i] = this.overflowHashBucket.removeLastKey(lh);
					keysNum--;
					lh.increaseCompareCounter();
					lh.setNumOfKeys(keysNum); // update linear hashing class.
					lh.increaseCompareCounter();
					if (this.overflowHashBucket.numKeys() == 0) { // overflow empty free it
						lh.increaseCompareCounter();
						this.overflowHashBucket = null;
					}
				}
				return;
			}
		}
		if (lh.increaseCompareCounter() && this.overflowHashBucket != null) { // look at the overflow for the key to be
																				// deleted if one exists
			this.overflowHashBucket.deleteKey(key, lh);
			if (lh.increaseCompareCounter() && this.overflowHashBucket.numKeys() == 0) { // overflow empty free it
				this.overflowHashBucket = null;
				lh.increaseCompareCounter();
			}
		}
	}

	/**
	 * it removes the last key of a hashBucket
	 * 
	 * @param lh the hasBucket's hash table
	 * @return the return value
	 */
	private int removeLastKey(LinearHashing lh) {

		int retval;

		if (this.overflowHashBucket == null) {
			if (this.numOfKeys != 0) {
				this.numOfKeys--;
				int a = this.arrayOfKeys[this.numOfKeys];
				this.arrayOfKeys[this.numOfKeys] = 0;
				return a;
			}
			return 0;
		} else {
			retval = this.overflowHashBucket.removeLastKey(lh);
			if (this.overflowHashBucket.numKeys() == 0) { // overflow empty free it
				this.overflowHashBucket = null;
			}
			return retval;
		}
	}

	/**
	 * its searches a key in a hashBucket
	 * 
	 * @param key the key we want to search
	 * @param lh  the hasBucket's hash table
	 * @return if the key was found return true, else return false
	 */
	public boolean searchKey(int key, LinearHashing lh) {

		int i;
		int HashBucketSize = lh.getHashBucketSize();

		for (i = 0; (lh.increaseCompareCounter() && i < this.numOfKeys)
				&& (lh.increaseCompareCounter() && i < HashBucketSize); i++) {
			if (lh.increaseCompareCounter() && this.arrayOfKeys[i] == key) { // key found
				return true;
			}
		}
		if (lh.increaseCompareCounter() && this.overflowHashBucket != null) { // look at the overflow for the key if one
																				// exists
			return this.overflowHashBucket.searchKey(key, lh);
		} else {
			return false;
		}
	}

	/**
	 * its splits a hashBucket
	 * 
	 * @param lh            the hasBucket's hash table
	 * @param n             the n used for the hash function
	 * @param HashBucketPos the hashBucket position
	 * @param newHashBucket the new hasBucket(it may contain some of the
	 *                      hashBuckets's values)
	 * 
	 */
	public void splitHashBucket(LinearHashing lh, int n, int HashBucketPos, HashBucket newHashBucket) { // splits the
																										// current
																										// HashBucket

		int i;
		int HashBucketSize = lh.getHashBucketSize();
		int keysNum = lh.getNumOfKeys();

		for (i = 0; (i < this.numOfKeys) && (i < HashBucketSize);) {
			int key = (this.arrayOfKeys[i]);
			if (key < 0) {
				key *= -1;
			}

			if ((key % n) != HashBucketPos) { // key goes to new HashBucket
				newHashBucket.insertKey(this.arrayOfKeys[i], lh);
				this.numOfKeys--;
				keysNum = lh.getNumOfKeys();
				keysNum--;
				lh.setNumOfKeys(keysNum); // update linear hashing class.
				// System.out.println("HashBucket.splitHashBucket.insertKey: KeysNum = " +
				// keysNum );
				this.arrayOfKeys[i] = this.arrayOfKeys[this.numOfKeys];
			} else { // key stays here
				i++;
			}
		}

		if (this.overflowHashBucket != null) { // split the overflow too if one exists
			this.overflowHashBucket.splitHashBucket(lh, n, HashBucketPos, newHashBucket);
		}
		while (this.numOfKeys != HashBucketSize) {
			if (this.overflowHashBucket == null) {
				return;
			}
			if (this.overflowHashBucket.numKeys() != 0) {
				this.arrayOfKeys[this.numOfKeys] = this.overflowHashBucket.removeLastKey(lh);
				if (this.overflowHashBucket.numKeys() == 0) { // overflow empty free it
					this.overflowHashBucket = null;
				}
				this.numOfKeys++;
			} else { // overflow empty free it
				this.overflowHashBucket = null;
			}
		}
	}

	/**
	 * it merges two hashBuckets to one
	 * 
	 * @param lh            the hasBucket's hash table
	 * @param oldHashBucket the old hashBucket
	 */
	public void mergeHashBucket(LinearHashing lh, HashBucket oldHashBucket) { // merges the current HashBucket

		while (lh.increaseCompareCounter() && oldHashBucket.numKeys() != 0) {
			this.insertKey(oldHashBucket.removeLastKey(lh), lh);
		}
	}

	/**
	 * it prints the hasBucket's informations
	 * 
	 * @param HashBucketSize the size of the hashBucket
	 */
	public void printHashBucket(int HashBucketSize) {

		int i;

		System.out.println("keysNum is: " + this.numOfKeys);
		for (i = 0; (i < this.numOfKeys) && (i < HashBucketSize); i++) {
			System.out.println("key at: " + i + " is: " + this.arrayOfKeys[i]);
		}
		if (this.overflowHashBucket != null) {
			System.out.println("printing overflow---");
			this.overflowHashBucket.printHashBucket(HashBucketSize);
		}
	}

}
