package prj1.CounterSingleton;

/**
 * A Singleton that can be used to count. This implementation is not thread
 * safe.
 * 
 * @author ip
 *
 */
public class CounterSingleton {
	/**
	 * static variable single_instance of type CounterSingleton
	 */
	private static CounterSingleton single_instance = null;

	/**
	 * variable holding our counter
	 */
	private int counter;

	/**
	 * Private constructor restricted to this class itself. External code can't
	 * create an instance of this class
	 */
	private CounterSingleton() {
		counter = 0;
	}

	/**
	 * Static method to create instance of CounterSingleton class. External code
	 * must call this method in order to get an instance of this class
	 * 
	 * @return CounterSingleton instance
	 */
	public static CounterSingleton getInstance() {
		if (single_instance == null)
			single_instance = new CounterSingleton();

		return single_instance;
	}

	/**
	 * Resets the internal counter to zero
	 */
	public void resetCounter() {
		counter = 0;
	}

	/**
	 * Returns the current count
	 * 
	 * @return the current count
	 */
	public int getCount() {
		return counter;
	}

	/**
	 * Increases the current count by 1. Returns always true so that it can be used
	 * in boolean statements
	 * 
	 * @return always true
	 */
	public boolean increaseCounter() {
		counter++;
		return true;
	}

	/**
	 * Increases the current count by step. Returns always true so that it can be
	 * used in boolean statements. Step could be negative. It is up to the specific
	 * usage scenario whether this is desirable or not.
	 * 
	 * @param step The amount to increase the counter
	 * @return always true
	 */
	public boolean increaseCounter(int step) {
		counter = counter + step;
		return true;
	}
}
