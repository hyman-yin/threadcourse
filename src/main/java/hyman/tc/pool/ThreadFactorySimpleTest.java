package hyman.tc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


/**
 * @Desc 演示ThreadFactory的用法
 *  	ThreadFactory就是一个创建线程的工厂，可以在工厂内定义线程的属性，这样一来，由这个工厂创建的所有线程都具有了这些属性
 * 
 * @author yinlongcheng 
 *
 */
public class ThreadFactorySimpleTest {
	private static ThreadFactory factory = new MyThreadFactory();
	
	public static void main(String[] args) {
		ExecutorService cachedPool = Executors.newCachedThreadPool(factory);
		
		for(int i=0;i<10;i++){
			cachedPool.execute(new Runnable() {
				public void run() {
					System.out.println(Thread.currentThread().getName()+" running...");
				}
			});
		}
		
	}
}

class MyThreadFactory implements ThreadFactory{
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r, "hyman");
		thread.setDaemon(true);
//		thread.setPriority(Thread.MAX_PRIORITY);
		thread.setUncaughtExceptionHandler(new GlobalExceptionHandler());
		return thread;
	}
	
}
