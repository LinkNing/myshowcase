package net.nifoo.tij.timer;

import java.util.Timer;
import java.util.TimerTask;

public class PntTask extends TimerTask {

	@Override
	public void run() {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%-10s => [%tT] %tT \n", Thread.currentThread().getName(), System.currentTimeMillis(), this
				.scheduledExecutionTime());
	}

	public static void main(String[] args) {
		PntTask pntTask1 = new PntTask();
		//		PntTask pntTask2 = new PntTask();

		Timer timer1 = new Timer("Print Task");
		timer1.scheduleAtFixedRate(pntTask1, 0, 5000);

		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer1.cancel();

		//		Timer timer2 = new Timer();
		//		timer2.schedule(pntTask2, 1, 5000);
	}

}
