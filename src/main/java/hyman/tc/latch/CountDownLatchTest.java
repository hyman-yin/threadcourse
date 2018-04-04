package hyman.tc.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Desc 展示CountdownLatch的功能和写法
 * 注意：必须在await方法调用之前需要手动调用countdown方法，这个方法会把latch中的计数器减1，
 * 	        当减到0时，代表条件满足，执行await后面的语句
 * 
 * 它和CyclicBarrier的区别在于CyclicBarrier是循环计数的，而CountdownLatch只有第一次会计数
 * 
 * @author yinlongcheng 
 *
 */
public class CountDownLatchTest {
	private static CountDownLatch latch = new CountDownLatch(4);
	
	public static void main(String[] args) {
		for(int i=0;i<20;i++){
			new Thread(new MyThread(String.valueOf(i), latch)).start();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	static class MyThread implements Runnable {
		private String name;
		private CountDownLatch latch;
		
		public MyThread(String name,CountDownLatch latch) {
			this.name = name;
			this.latch = latch;
		}
		
		public void run() {
			System.out.println("Thread"+name+"准备就绪...");
			
			//需要手动调用countdown方法计数，如果注释掉这行会一直卡在await上
			latch.countDown();
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Thread"+name+"开始运行==============================");
		}
	}
}
