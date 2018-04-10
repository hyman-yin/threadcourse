package hyman.tc.pool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 演示Schedule线程池的资源抢占
 *
 */
public class ExecutorServiceSchedulePoolTest3 {
	public static void main(String[] args) {
		ScheduledExecutorService schedulePool = Executors.newScheduledThreadPool(2);
		/*
		 * 因为schedulepool的大小设置为2，因此任务1，2，3并不是严格按照schedle来运行的,而是存在资源抢占的情况
		 * 如果把大小设置为3或大于3，那么结果就很有规律性
		 */
		schedulePool.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("定时任务1运行： "+new Date()+" ---");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 0, 1, TimeUnit.SECONDS);
		
		
		/**
		 * 固定频率运行
		 */
		schedulePool.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("定时任务2运行： "+new Date()+" ++++++++");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 0, 2, TimeUnit.SECONDS);
		
		schedulePool.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("定时任务3运行： "+new Date()+" ************");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 0, 3, TimeUnit.SECONDS);
	}
}
