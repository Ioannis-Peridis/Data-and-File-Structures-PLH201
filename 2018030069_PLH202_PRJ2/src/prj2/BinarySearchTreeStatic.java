package prj2;

/**
 * implementation of all Static binary search tree methods
 * 
 * @author ip
 */
public class BinarySearchTreeStatic implements BinarySearchTreeInterface {
	/**
	 * contains the value of the position of keys in the matrix
	 * constant-dosen't change
	 */
	private static final int KEYS = 0;
	/**
	 * contains the value of the position of the left side of the matrix
	 * constant-dosen't change
	 */
	private static final int LEFT = 1;
	/**
	 * contains the value of the position of the right side of the matrix
	 * constant-dosen't change
	 */
	private static final int RIGHT = 2;
	/**
	 * points to the tree root
	 */
	private int root;
	/**
	 * points to the first one available position of the binary search tree
	 */
	private int freeNode;
	/**
	 * number of keys(values) that a binary search tree can contain
	 */
	private int numOfKeys;
	/**
	 * number that counts all the comparisons made during insertion
	 */
	private int insertionNum = 0;
	/**
	 * number that counts all the comparisons made during search
	 */
	private int searchNum = 0;
	/**
	 * number that counts all the comparisons made during range
	 */
	private int rangeNum = 0;
	/**
	 * where the search result is returned
	 */
	private int resultLine = -1;
	/**
	 * matrix that contains the binary search tree
	 */
	private int[][] treeMatrix;

	/**
	 * the class constructor, initialization of a basic size values
	 * 
	 * @param N number of keys that can be contained in a static binary search tree
	 */
	public BinarySearchTreeStatic(int N) {
		root = -1;
		freeNode = 0;
		numOfKeys = N;
		treeMatrix = new int[3][N];
		stackInitialization(N);
	}

	// the getters and setters
	public int getRoot() {
		return root;
	}

	public void setRoot(int root) {
		this.root = root;
	}

	public int getFreeNode() {
		return freeNode;
	}

	public void setFreeNode(int freeNode) {
		this.freeNode = freeNode;
	}

	public int getNumOfKeys() {
		return numOfKeys;
	}

	public void setNumOfKeys(int numOfNodes) {
		this.numOfKeys = numOfNodes;
	}

	public int getInsertionNum() {
		return insertionNum;
	}

	public void setInsertionNum(int insertionNum) {
		this.insertionNum = insertionNum;
	}

	public int getSearchNum() {
		return searchNum;
	}

	public void setSearchNum(int searchNum) {
		this.searchNum = searchNum;
	}

	public int getRangeNum() {
		return rangeNum;
	}

	public void setRangeNum(int rangeNum) {
		this.rangeNum = rangeNum;
	}

	public int getResultLine() {
		return resultLine;
	}

	public void setResultLine(int resultLine) {
		this.resultLine = resultLine;
	}

	public int[][] getTreeMatrix() {
		return treeMatrix;
	}

	public void setTreeMatrix(int[][] treeMatrix) {
		this.treeMatrix = treeMatrix;
	}

	public static int getKeys() {
		return KEYS;
	}

	public static int getLeft() {
		return LEFT;
	}

	public static int getRight() {
		return RIGHT;
	}

	/**
	 * sets the range number counter to zero.Used for executing experiments one
	 * after another
	 */
	public void setRangeNumToZero() {
		rangeNum = 0;
	}

	/**
	 * it increases the number of comparisons made by the insertion
	 * 
	 * @return always return must be true so it can be in conditions
	 */
	public boolean increaseInsertionNum() {
		insertionNum++;
		return true;
	}

	/**
	 * the number of comparisons made by the search
	 * 
	 * @return always return must be true so it can be in conditions
	 */
	public boolean increaseSearchNum() {
		searchNum++;
		return true;
	}

	/**
	 * it increases the number of comparisons made by the range
	 * 
	 * @return always return must be true so it can be in conditions
	 */
	public boolean increaseRangeNum() {
		rangeNum++;
		return true;
	}

	/**
	 * initializes the available positions in the stack
	 * 
	 * @param N the initialized number of positions
	 */
	public void stackInitialization(int N) {
		for (int c = 0; c < N - 1; c++) {
			treeMatrix[RIGHT][c] = c + 1;
		}
		treeMatrix[RIGHT][N - 1] = -1;
	}

	/**
	 * checks if the binary search tree is full
	 * 
	 * @return it returns true if it is full
	 */
	public boolean isFull() {
		if (freeNode == -1)
			return true;
		return false;
	}

	/**
	 * checks if the binary search tree is empty
	 * 
	 * @return it returns true if it is empty
	 */
	public boolean isEmpty() {
		if (root == -1)
			return true;
		return false;
	}

	/**
	 * it returns the smallest key of a tree
	 * 
	 * @param root root of the tree that we are about to search the key
	 * @return the smallest key that we found in the tree
	 */
	public int minKey(int root) {
		int minkey = treeMatrix[KEYS][root];
		while (treeMatrix[LEFT][root] != -1) {
			minkey = treeMatrix[KEYS][treeMatrix[LEFT][root]];
			root = treeMatrix[LEFT][root];
		}
		return minkey;
	}

	/**
	 * recursive method that inserts in the binary search tree a key
	 * 
	 * @param root root of the tree
	 * @param key  key that is inserted
	 * @return the same root of the tree without the changes
	 */
	public int insertRec(int root, int key) {

		// checks if tree is full
		if (increaseInsertionNum() && isFull()) {
			System.out.println("the tree is full");
			return root;
		}

		/* checks if tree is empty */
		if (increaseInsertionNum() && root == -1) {

			treeMatrix[KEYS][freeNode] = key;
			treeMatrix[LEFT][freeNode] = -1;
			int tm = treeMatrix[RIGHT][freeNode];
			treeMatrix[RIGHT][freeNode] = -1;
			root = freeNode;
			freeNode = tm;
			increaseInsertionNum();
			increaseInsertionNum();
			increaseInsertionNum();
			increaseInsertionNum();
			increaseInsertionNum();
			increaseInsertionNum();

			return root;
		}

		// if key less than current key, go to left subtree
		if (increaseInsertionNum() && key < treeMatrix[KEYS][root]) {
			treeMatrix[LEFT][root] = insertRec(treeMatrix[LEFT][root], key);
			increaseInsertionNum();
		}
		// if key bigger than current key, go to right subtree
		else if (increaseInsertionNum() && key > treeMatrix[KEYS][root]) {
			treeMatrix[RIGHT][root] = insertRec(treeMatrix[RIGHT][root], key);
			increaseInsertionNum();
		}

		// return the same root without the changes
		return root;
	}

	/**
	 * calls the insertRec method and executes the insertion with the inserting key
	 * given
	 * 
	 * @param key key that is inserted
	 */
	public void insert(int key) {
		root = insertRec(root, key);

	}

	/**
	 * recursive method that deletes the key we give from a tree we give
	 * 
	 * @param root root of the tree that contains the key
	 * @param key  key we want to remove
	 * @return the root
	 */
	public int deleteRec(int root, int key) {
		// checks if tree is empty
		if (root == -1) {
			return root;
		}

		// recursive part
		if (key > treeMatrix[KEYS][root])
			treeMatrix[RIGHT][root] = deleteRec(treeMatrix[RIGHT][root], key);
		else if (key < treeMatrix[KEYS][root])
			treeMatrix[LEFT][root] = deleteRec(treeMatrix[LEFT][root], key);

		// if the root's key match with the key , we delete this node
		else {
			treeMatrix[KEYS][root] = -1;
			// node with exactly one or zero child
			if (treeMatrix[LEFT][root] == -1) {
				int right = treeMatrix[RIGHT][root];
				treeMatrix[RIGHT][root] = freeNode;
				freeNode = root;
				return right;
			} else if (treeMatrix[RIGHT][root] == -1) {
				int left = treeMatrix[LEFT][root];
				treeMatrix[LEFT][root] = -1;
				treeMatrix[RIGHT][root] = freeNode;
				freeNode = root;
				return left;
			}
			// the node with two children .Take the inorder successor(the smallest one goes
			// to the right subtree)
			else {
				treeMatrix[KEYS][root] = minKey(treeMatrix[RIGHT][root]);

				// delete the in order successor
				treeMatrix[RIGHT][root] = deleteRec(treeMatrix[RIGHT][root], treeMatrix[KEYS][root]);
			}
		}
		return root;
	}

	/**
	 * it calls the deleterRec method to delete one specific key of the tree
	 * 
	 * @param key the key we want to delete
	 */
	public void delete(int key) {
		root = deleteRec(root, key);
	}

	/**
	 * method that searches for the key
	 * 
	 * @param root root of the tree that we will search the key in
	 * @param key  key that we search
	 * @return node that they key was found(null if unsuccessful)
	 */
	private int doSearch(int root, int key) {

		// checks if root is null or if key exists at root
		if ((increaseSearchNum() && root == -1) || (increaseSearchNum() && treeMatrix[KEYS][root] == key)) {
			return root;
		}
		// checks if key is bigger than root's key
		if (increaseSearchNum() && treeMatrix[KEYS][root] > key)
			return doSearch(treeMatrix[LEFT][root], key);

		// checks if key is smaller than root's key
		return doSearch(treeMatrix[RIGHT][root], key);
	}

	/**
	 * it calls the doSearch method with the given key
	 * 
	 * @param key the key that we give
	 */
	public void search(int key) {
		doSearch(this.root, key);
	}

	/**
	 * recursive method that performs an in order traversal of binary search tree
	 * method that does the in
	 * 
	 * @param root Pointer to the root of the tree.
	 */
	public void inorderRec(int root) {
		if (root != -1) {
			inorderRec(treeMatrix[LEFT][root]);
			inorderRec(treeMatrix[RIGHT][root]);
		}
	}

	/**
	 * 
	 * it calls the inorderRec method with the tree given
	 */
	public void inorder() {
		inorderRec(root);
	}

	/**
	 * recursive method that prints the values in the range we give
	 * 
	 * @param node current node(starting from root)
	 * @param k1   minimum range
	 * @param k2   maximum range
	 */
	private void printRange(int root, int k1, int k2) {
		// checks if root is null
		if (increaseRangeNum() && root == -1) {
			return;
		}

		/*
		 * Since the desired o/p is sorted, recurse for LEFT subtree first If root->data
		 * is greater than k1, then only we can get o/p KEYS in LEFT subtree
		 * 
		 */
		if (increaseRangeNum() && k1 < treeMatrix[KEYS][root]) {
			printRange(treeMatrix[LEFT][root], k1, k2);
		}

		/* if root's data lies in range, then prints root's data */
		if ((increaseRangeNum() && k1 <= treeMatrix[KEYS][root])
				&& (increaseRangeNum() && k2 >= treeMatrix[KEYS][root])) {
		}

		/*
		 * If root->data is smaller than k2, then only we can get o/p KEYS in RIGHT
		 * subtree
		 */
		if (increaseRangeNum() && k2 > treeMatrix[KEYS][root]) {
			printRange(treeMatrix[RIGHT][root], k1, k2);
		}
	}

	/**
	 * method that calls the recursive function that prints the values of a tree in
	 * a given range.
	 * 
	 * @param k1 The minimum bound of the range.
	 * @param k2 The maximum bound of the range.
	 */
	public void range(int k1, int k2) {
		printRange(this.root, k1, k2);
	}
}