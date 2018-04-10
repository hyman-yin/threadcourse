package hyman.tc.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 演示如何通过FutureTask和Callable获取子线程的返回结果
 * FutureTask是Future的子类，因为Future是接口无法创建对象
 * @author hyman
 *
 */
public class CallableFutureTaskTest2 {
	public static void main(String[] args) {
		Task task = new Task();
		FutureTask<Integer> futureTask = new FutureTask<>(task);
		System.out.println("启动子线程");
//		ExecutorService pool = Executors.newCachedThreadPool();
//		pool.submit(futureTask);
		Thread thread = new Thread(futureTask);
		thread.start();
		
		System.out.println("主线程继续执行。。。。。。");
		System.out.println("主线程继续执行。。。。。。");
		System.out.println("主线程继续执行。。。。。。");
		System.out.println("主线程继续执行。。。。。。");
		try {
			//get有一个重载的方法，指定等待的时间， get(long,timeunit)
			System.out.println(futureTask.get());
			System.out.println("主线程获取到返回值后继续执行+++++++++++");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
//			pool.shutdown();
		}
	}
	
	static class Task implements Callable<Integer> {
		@Override
		public Integer call() throws Exception {
			System.out.println("子线程计算中");
			TimeUnit.SECONDS.sleep(5);
			System.out.println("子线程计算完成。。。");
			return 1;
		}
	}
}
