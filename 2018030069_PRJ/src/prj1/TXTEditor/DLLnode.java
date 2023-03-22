package prj1.TXTEditor;

/**
 * Implementation of a double linked list's node, represents a line of a file
 * 
 * @author ip
 *
 */
public class DLLnode {
	/**
	 * Key, it represents the key of a node
	 */
	private String key;

	/**
	 * Before,it represents the line(node) before 
	 * After,it represents the line(node)after
	 */
	DLLnode before, next;

	/**
	 * Class constructor
	 * 
	 * @param value(line's information)
	 */

	public DLLnode(String value) {
		key = value;
		before = null;
		next = null;
	}

	/**
	 * Method that returns the key
	 * 
	 * @return key (the node's key)
	 */
	public String getKey() {
		return key;
	}
}
