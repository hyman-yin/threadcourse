package hyman.tc.latch;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Desc 展示CyclicBarrier的功能和写法
 * 
 * @author yinlongcheng 
 *
 */
public class CyclicBarrierTest1 {
	private static CyclicBarrier cyclicBarrier = new CyclicBarrier(4,new Runnable() {
		@Override
		public void run() {
			System.out.println("开始往下运行");
		}
	});
	
	public static void main(String[] args) {
		for(int i=0;i<20;i++){
			new Thread(new MyThread(String.valueOf(i), cyclicBarrier)).start();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	static class MyThread implements Runnable {
		private CyclicBarrier cyclicBarrier;
		private String name;
		public MyThread(String name, CyclicBarrier cyclicBarrier) {
			this.name = name;
			this.cyclicBarrier = cyclicBarrier;
		}
		public void run() {
			System.out.println("Thread"+name+"准备就绪...");
			try {
				cyclicBarrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println("Thread"+name+"开始运行==============================");
		}
		
	}
}