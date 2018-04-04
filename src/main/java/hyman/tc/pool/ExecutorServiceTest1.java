package hyman.tc.pool;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Desc 简单线程池的用法介绍，在java8中
 * 
 * @author yinlongcheng 
 *
 */
public class ExecutorServiceTest1 {
	public static void main(String[] args) {
		ExecutorService serviceSingle = Executors.newSingleThreadExecutor();//单线程
		ExecutorService serviceFixed = Executors.newFixedThreadPool(2);//固定数量线程
		ExecutorService serviceCached = Executors.newCachedThreadPool();//弹性线程
		ScheduledExecutorService serviceSchedule = Executors.newScheduledThreadPool(2);//带定时功能线程
		
		for(int i=0;i<10;i++){
			final int j=i;
			serviceSingle.execute(new Runnable() {
//			serviceFixed.execute(new Runnable() {
//			serviceCached.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("thread"+j+"begin running...");
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("thread"+j+"end.");
				}
			});
		}
		
//		serviceFixed.shutdown();//shutdown方法让线程池继续运行当前已经运行的线程，不再开启新的线程
//		service.isShutdown();//判断线程是否已经执行完毕，当所有线程都执行完毕，会打印true
		
		//因为schedulepool的大小设置为2，因此任务1，2，3并不是严格按照schedle来运行的,而是存在资源抢占的情况
		//如果把大小设置为3或大于3，那么结果就很有规律性，线程池性能调优其中一个重点就是调这个参数的大小
		serviceSchedule.scheduleAtFixedRate(new Runnable() {
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
		
		serviceSchedule.scheduleAtFixedRate(new Runnable() {
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
		
		serviceSchedule.scheduleAtFixedRate(new Runnable() {
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
