package hyman.tc.pool;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * 演示Schedule线程池的使用方法，它有四个方法：
 * schedule(Runnable runnable, long delay, TimeUnit timeunit): 创建并执行在给定延迟后启用的一次性操作。
   schedule(Callable callable, long delay, TimeUnit timeunit): 创建并执行在给定延迟后启用的 ScheduledFuture。
   scheduleAtFixedRate(Runnable runnable, long initialDelay, long period, TimeUnit timeunit):  在初始delay之后以固定频率运行线程
   scheduleWithFixedDelay(Runnable runnable, long initialDelay, long delay, TimeUnit timeunit):  创建并执行一个在给定初始延迟后首次启用的定期操作，随后，在每一次执行终止和下一次执行开始之间都存在给定的延迟。
 * @author hyman
 *
 */
public class ExecutorServiceSchedulePoolTest2 {
	public static void main(String[] args) {
		ScheduledExecutorService schedulePool = Executors.newScheduledThreadPool(20);//带定时功能线程
		
//		创建并执行在给定延迟后启用的一次性操作
		schedulePool.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("hello world!  "+new Date());
			}
		}, 2, TimeUnit.SECONDS);
		
		
//		创建并执行在给定延迟后启用的 ScheduledFuture 也是一次性操作
		Future<Integer> future = schedulePool.schedule(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return 11;
			}
		}, 4, TimeUnit.SECONDS);
		try {
			System.out.println("子线程运行结果是：  "+future.get()+"   "+new Date());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		
//		以固定频率运行线程
		schedulePool.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("定时任务1运行： "+new Date()+" ---");
			}
		}, 0, 1, TimeUnit.SECONDS);
		
//		以固定频率运行线程，当程序需要运行的时间超过间隔时，结束运行后会立即再次启动
		schedulePool.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("****** 定时任务2开始运行： "+new Date());
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("定时任务2结束运行： "+new Date()+" ******");
			}
		}, 0, 3, TimeUnit.SECONDS);
		
//		创建并执行一个在给定初始延迟后首次启用的定期操作，随后，在每一次执行终止和下一次执行开始之间都存在给定的延迟。当运行时间需要5秒时，每次运行间隔是5+3=8秒
		schedulePool.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				System.out.println("++++++++++++ 定时任务3开始： "+new Date());
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("定时任务3结束： "+new Date()+" ++++++++++++");
			}
		}, 2, 3, TimeUnit.SECONDS);
		
		
		
		
		
	}
}
