package net.nifoo.tij.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {

	private static final int THREAD_SLEEP_MILLIS = 6000;

	/** 旅游小数的个数 */
	private static final int NUMBER_OF_GROUPS = 6;

	/** 观光是否结束的标识 */
	private static boolean tourOver = false;

	public static void main(String[] args) {

		ExecutorService service = Executors.newFixedThreadPool(NUMBER_OF_GROUPS);

		CyclicBarrier cb = new CyclicBarrier(NUMBER_OF_GROUPS, new Runnable() {

			public void run() {
				/*
				 * 如果一天的游玩结束了，大家可以坐大巴回去了... ...
				 */
				if (isTourOver()) {
					System.out.println("各个小组都集合到大巴上，准备回家.. ...");
				}

			}
		});

		System.out.println("用CyclicBarrier辅助工具类模拟旅游过程中小组集合:：");

		/*
		 * 上午各个小组自由活动，然后在某个点，比如11点半集合到大巴上。
		 */
		tourInTheMorning(service, cb);
		sleep(THREAD_SLEEP_MILLIS);

		/*
		 * 调用reset方法，将barrier设置到初始化状态。
		 * 
		 * TODO://不知道这样的调用是否是合理的？
		 */
		cb.reset();

		/*
		 * 下午各个小组自由活动，然后在某个点，比如11点半集合到大巴上。
		 */
		tourInTheAfternoon(service, cb);
		
		tourOver = true;

		/*
		 * 下午小组集合完毕后，一天的观光就结束了，将标志位记为true;
		 */
		sleep(THREAD_SLEEP_MILLIS);
		service.shutdown();

	}

	public static boolean isTourOver() {
		return tourOver;
	}

	public static void setTourOver(boolean tourOver) {
		CyclicBarrierDemo.tourOver = tourOver;
	}

	private static void tourInTheMorning(ExecutorService service, final CyclicBarrier cb) {
		System.out.println("早上自由玩... ... ");
		for (int groupNumber = 1; groupNumber <= NUMBER_OF_GROUPS; groupNumber++) {
			service.execute(new TeamGroup(cb, groupNumber));
		}
	}

	private static void tourInTheAfternoon(ExecutorService service, final CyclicBarrier cb) {
		System.out.println("下午自由玩... ... ");
		for (int groupNumber = 1; groupNumber <= NUMBER_OF_GROUPS; groupNumber++) {
			service.execute(new TeamGroup(cb, groupNumber));
		}
	}

	private static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class TeamGroup implements Runnable {

	private final CyclicBarrier barrier;

	private int groupNumber;

	public TeamGroup(CyclicBarrier barrier, int groupNumber) {
		this.barrier = barrier;
		this.groupNumber = groupNumber;
	}

	public void run() {

		try {
			visit();
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	private void visit() {
		System.out.println(String.format("第%d组完成该地景点浏览，并回到集合点", groupNumber));
	}

}