package prj1.SearchDataStructure;

import prj1.Node.Node;

/**
 * An interface describing a data structure for inserting nodes and searching
 * for nodes based on a key
 * 
 * @author ip
 *
 *
 */
public interface SearchDataStructure {
	/**
	 * Inserts a new Node with key the given key
	 * 
	 * @param key (the node's key )
	 */
	public void insert(int key);

	/**
	 * Search for a node with given key and returns it if found.
	 * 
	 * @param key (the node's key)
	 * @return the node with given key
	 */
	public Node search(int key);
}
