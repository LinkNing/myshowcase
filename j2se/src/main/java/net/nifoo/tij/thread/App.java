package net.nifoo.tij.thread;

/**
 * Hello world!
 * 
 * @author nifoo
 * @version $Revision: 1.0 $
 */
public class App {

	/**
	 * Method main.
	 * 
	 * @param args
	 *            String[]
	 */
	public static void main(String[] args) {
		Queue<Item> queue = new Queue<Item>(5);

		new Producer("生产者1", queue);

		new Consumer("消费者1", queue);
		new Consumer("消费者2", queue);
		new Consumer("消费者3", queue);
	}
}
