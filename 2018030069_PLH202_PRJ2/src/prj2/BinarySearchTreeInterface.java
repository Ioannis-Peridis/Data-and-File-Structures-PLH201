package prj2;
/**
 * Interface with the binary search tree behavior(methods)
 * @author ip
 *
 */

public interface BinarySearchTreeInterface {
	public void search(int key);
	public void insert(int key);
	public void delete(int key);
	public void range(int k1, int k2);
	public void inorder();
}
