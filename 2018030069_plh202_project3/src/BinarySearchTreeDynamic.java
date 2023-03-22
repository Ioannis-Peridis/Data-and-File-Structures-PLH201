
/**
 * implementation of all Dynamic binary search tree methods
 * 
 * @author ip
 */
public class BinarySearchTreeDynamic {

	/**
	 * class inside class contains right,left child and key of the current node
	 */
	class Node {
		int key;
		Node left, right;

		public Node(int item) {
			key = item;
			left = right = null;
		}
	}

	/**
	 * points to the tree root
	 */
	private Node root;
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
	 * 
	 */
	private int deletionNum=0;
	/**
	 * holds the results of the searches
	 */
	private Node returnNode = null;
	/**
	 * counter used for helping to create the sorted array of the dynamic binary
	 * search tree keys (points at the index of the array)
	 */
	private int counter;
	/**
	 * an sorted array of the saved keys
	 */
	private int[] arrayOfKeys;

	/**
	 * the class constructor, initialization of a basic size values
	 */
	BinarySearchTreeDynamic() {
		numOfKeys = 0;
		counter = 0;
		root = null;
	}

	// the getters and setters
	
	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public int getNumOfKeys() {
		return numOfKeys;
	}

	public void setNumOfKeys(int numOfKeys) {
		this.numOfKeys = numOfKeys;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
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

	public Node getReturnNode() {
		return returnNode;
	}

	public void setReturnNode(Node returnNode) {
		this.returnNode = returnNode;
	}

	public int[] getArrayOfKeys() {
		return arrayOfKeys;
	}

	public void setArrayOfKeys(int[] arrayOfKeys) {
		this.arrayOfKeys = arrayOfKeys;
	}
	public int getDeletionNum() {
		return deletionNum;
	}

	public void setDeletionNum(int deletionNum) {
		this.deletionNum = deletionNum;
	}

	/**
	 * sets the range number counter to zero.Used for executing experiments one
	 * after another
	 */

	public void setRangeNumToZero() {
		rangeNum= 0;
	}
	/**
	 * 
	 */
	public void setDeletionNumToZero() {
		deletionNum= 0;
	}
	
	public void setInsertionNumToZero() {
		insertionNum = 0; 
	}
	
	public void setSearchNumToZero() {
		searchNum = 0;
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
	
	public boolean increaseDeletionNum() {
		deletionNum++;
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
	 * it returns the smallest key of a tree
	 * 
	 * @param root root of the tree that we are about to search the key
	 * @return the smallest key that we found in the tree
	 */
	private int minKey(Node root) {
		int minkey = root.key;
		while (root.left != null) {
			minkey = root.left.key;
			root = root.left;
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
	private Node insertRec(Node root, int key) {
		// checks if tree is empty and if it is returns a new node
		if (increaseInsertionNum() && root == null) {
			numOfKeys++;
			increaseInsertionNum();
			root = new Node(key);
			increaseInsertionNum();
			return root;
		}

		// recursion down to the tree
		if (increaseInsertionNum() && key > root.key) {
			root.right = insertRec(root.right, key);
			increaseInsertionNum();
		} else if (increaseInsertionNum() && key < root.key) {
			root.left = insertRec(root.left, key);
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
	
	private Node deleteRec(Node root, int key) 
    { 
        /* Base Case: If the tree is empty */
        if (increaseDeletionNum() && root == null)  return root; 
  
        /* Otherwise, recur down the tree */
        if (increaseDeletionNum() && key < root.key) { 
            root.left = deleteRec(root.left, key); 
        	increaseDeletionNum();}
        else if (increaseDeletionNum() && key > root.key) {
            root.right = deleteRec(root.right, key);
            increaseDeletionNum();
        }
        // if key is same as root's key, then This is the node 
        // to be deleted 
        else
        { 
        
            // node with only one child or no child 
            if (increaseDeletionNum() && root.left == null) {
            	increaseDeletionNum();
                return root.right; 
                }
            else if (increaseDeletionNum() && root.right == null) {
            	increaseDeletionNum();
                return root.left; 
            }
            // node with two children: Get the in order successor (smallest 
            // in the right subtree) 
            root.key = minKey(root.right);
            increaseDeletionNum();
  
            // Delete the inorder successor 
            root.right = deleteRec(root.right, root.key);
            increaseDeletionNum();
        } 
  
        return root; 
    } 
	/*private Node deleteRec(Node root, int key) {
		// checks if tree is empty
		if (root == null)
			return root;

		// recursive part
		if (key > root.key)
			root.right = deleteRec(root.right, key);

		else if (key < root.key)
			root.left = deleteRec(root.left, key);
		// if the root's key match with the key , we delete this node
		else {
			numOfKeys--;
			// node with only one child or no child
			if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;

			// the node with two children .Take the inorder successor(the smallest one goes
			// to the right subtree)
			root.key = minKey(root.right);

			// delete the in order successor
			root.right = deleteRec(root.right, root.key);
		}

		return root;
	}*/

	/**
	 * it calls the deleterRec method to delete one specific key of the tree
	 * 
	 * @param key the key we want to delete
	 */
	public void delete(int key) {
		root = deleteRec(root, key);
		increaseDeletionNum();
	}

	/**
	 * method that searches for the key
	 * 
	 * @param root root of the tree that we will search the key in
	 * @param key  key that we search
	 * @return node that they key was found(null if unsuccessful)
	 */
	private Node doSearch(Node root, int key) {
		// checks if root is null or if key exists at root
		if ((increaseSearchNum() && root == null) || (increaseSearchNum() && root.key == key)) {
			return root;
		}
		// checks if key is bigger than root's key
		if (increaseSearchNum() && root.key > key)
			return doSearch(root.left, key);

		// checks if key is smaller than root's key
		return doSearch(root.right, key);
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
	private void inorderRec(Node root) {

		if (root != null) {
			// If the tree with this root is not empty.
			inorderRec(root.left);
			arrayOfKeys[counter++] = root.key;
			// System.out.println(root.key + " ");
			inorderRec(root.right);
		}
	}

	/**
	 * it calls the inorderRec method with the tree given and initializing the array
	 * of keys
	 */
	public void inorder() {
		arrayOfKeys = new int[numOfKeys];
		inorderRec(root);
		counter = 0;
	}

	/**
	 * recursive method that prints the values in the range we give
	 * 
	 * @param node current node(starting from root)
	 * @param k1   minimum range
	 * @param k2   maximum range
	 */
	private void printRange(Node node, int k1, int k2) {

		// checks if root is null
		if (increaseRangeNum() && node == null) {
			return;
		}

		/*
		 * Since the desired o/p is sorted, recurse for left subtree first If root->data
		 * is greater than k1, then only we can get o/p keys in left subtree
		 */
		if (increaseRangeNum() && k1 < node.key) {
			printRange(node.left, k1, k2);
		}

		/* if root's data lies in range, then prints root's data */
		if ((increaseRangeNum() && k1 <= node.key) && (increaseRangeNum() && k2 >= node.key)) {
			// System.out.print(node.key + " ");
		}

		/*
		 * If root->data is smaller than k2, then only we can get o/p keys in right
		 * subtree
		 */
		if (increaseRangeNum() && k2 > node.key) {
			printRange(node.right, k1, k2);
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