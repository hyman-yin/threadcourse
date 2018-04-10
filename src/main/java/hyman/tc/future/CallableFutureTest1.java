package hyman.tc.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


/**
 * 演示如何通过Future和Callable获取子线程的返回结果
 * @author hyman
 *
 */
public class CallableFutureTest1 {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		Task task = new Task();
		System.out.println("启动子线程。。。");
		Future<Integer> result = pool.submit(task);
		System.out.println("主线程继续向下运行。。。");
		System.out.println("主线程继续向下运行。。。");
		System.out.println("主线程继续向下运行。。。");
		System.out.println("主线程继续向下运行。。。");
		try {
			//get方法有一个重载的方法，可以指定等待多久get(long,timeunit)
			System.out.println("子线程的结果是："+result.get());
			System.out.println("主线程获取结果后继续运行。。。");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			pool.shutdown();
		}
	}
	
	static class Task implements Callable<Integer>{

		@Override
		public Integer call() throws Exception {
			System.out.println("子线程操作");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("子线程操作完成");
			return 1;
		}
		
	}
}
