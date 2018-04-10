package hyman.tc.pool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Desc 简单线程池的用法介绍，在java8中
 * 
 * @author yinlongcheng 
 *
 */
public class ExecutorServiceTest1 {
	public static void main(String[] args) {
		//三个方法都有重载的方法，可以传入ThreadFactory类型参数
		ExecutorService singlePool = Executors.newSingleThreadExecutor();//单线程
		ExecutorService fixedPool = Executors.newFixedThreadPool(2);//固定数量线程
		ExecutorService cachedPool = Executors.newCachedThreadPool();//弹性线程
		
		for(int i=0;i<10;i++){
			final int j=i;
			singlePool.execute(new Runnable() {
//			fixedPool.execute(new Runnable() {
//			cachedPool.execute(new Runnable() {
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
		
		//下面是线程池的三个常用关闭方法
		
		//shutdown方法让线程池继续运行当前已经运行的线程，不再开启新的线程
		singlePool.shutdown();
		
		/**
		 * 先停止接收外部提交的任务,忽略队列里等待的任务,尝试将正在跑的任务interrupt中断,返回未执行的任务列表
		 * 通过调用Thread.interrupt()方法来实现终止线程，但是大家知道，这种方法的作用有限，
		 * 如果线程中没有sleep 、wait、Condition、定时锁等应用,interrupt()方法是无法中断当前的线程的。
		 * 
		 * 返回未完成任务列表
		 */
//		List<Runnable> list = singlePool.shutdownNow();
		
		/**
		 * 当前线程阻塞，直到等所有已提交的任务（包括正在跑的和队列中等待的）执行完或者等超时时间到或者线程被中断，抛出InterruptedException，
		 * 然后返回true（所有任务执行完毕）或false（已超时）
		 */
//		try {
//			boolean flag = singlePool.awaitTermination(5, TimeUnit.SECONDS);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		//判断线程是否已经执行完毕，当所有线程都执行完毕，会打印true
		try {
			TimeUnit.SECONDS.sleep(12);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("线程池已关闭？ -- "+singlePool.isShutdown());
		
	}
}
