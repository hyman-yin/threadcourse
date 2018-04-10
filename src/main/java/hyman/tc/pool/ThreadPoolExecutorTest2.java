package hyman.tc.pool;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @Desc 手动创建线程池  ThreadPoolExecutor
 * 
 * @author yinlongcheng 
 *
 */
public class ThreadPoolExecutorTest2 {
	public static void main(String[] args) {
		/**
		 * 主要有下面4个构造方法，各参数的含义如下
		 * corePoolSize:核心线程数，默认情况下核心线程会一直存活，即使处于闲置状态也不会受存keepAliveTime限制。除非将allowCoreThreadTimeOut设置为true。
		 * maximumPoolSize: 线程池所能容纳的最大线程数。超过这个数的线程将被阻塞。当任务队列为没有设置大小的LinkedBlockingDeque时，这个值无效。
		 * keepAliveTime:非核心线程的闲置超时时间，超过这个时间就会被回收。
		 * timeunit:指定keepAliveTime的单位，如TimeUnit.SECONDS。当将allowCoreThreadTimeOut设置为true时对corePoolSize生效。
		 * workQueue:工作队列。常用的有三种队列，SynchronousQueue,LinkedBlockingDeque,ArrayBlockingQueue。
		 * threadfactory: threadFactory
		 * rejectedexecutionhandler: 请求创建线程被拒绝后的操作,需要我们实现
		 */
//		public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeunit, BlockingQueue workQueue);
//
//	    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeunit, BlockingQueue workQueue, 
//	    		ThreadFactory threadfactory);
//
//	    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeunit, BlockingQueue workQueue, 
//	    		RejectedExecutionHandler rejectedexecutionhandler);
//
//	    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeunit, BlockingQueue workQueue, 
//	    		ThreadFactory threadfactory, RejectedExecutionHandler rejectedexecutionhandler);
		
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(4);
		BlockingQueue<Runnable> queue2 = new LinkedBlockingQueue<>(20); 
		
		ThreadPoolExecutor pool1 = new ThreadPoolExecutor(5, 10, 20, TimeUnit.SECONDS, queue,new MyRejectHandler());
		ThreadPoolExecutor pool2 = new ThreadPoolExecutor(2, 10, 20, TimeUnit.SECONDS, queue2,new MyRejectHandler());
		
		
		
		for(int i=0;i<1000;i++){
			final int j=i;
			
			pool1.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("thread"+j+" is running......");
					try {
						TimeUnit.SECONDS.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			/*pool2.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("thread"+j+" is running......");
					try {
						TimeUnit.SECONDS.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});*/
			
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("核心线程数： "+pool1.getCorePoolSize()+"，最大线程数： "+pool1.getMaximumPoolSize()
				+"，活动线程数： "+pool1.getActiveCount()+"，完成线程数： "+pool1.getCompletedTaskCount());
//			System.out.println("核心线程数： "+pool2.getCorePoolSize()+"，最大线程数： "+pool2.getMaximumPoolSize()
//			+"，活动线程数： "+pool2.getActiveCount()+"，完成线程数： "+pool2.getCompletedTaskCount());
		}
		
		
	}
	
	
	static class MyRejectHandler implements RejectedExecutionHandler {
		@Override
		public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadpoolexecutor) {
			System.out.println("好烦，请求被拒绝了！！！");
		}
		
	}
}
