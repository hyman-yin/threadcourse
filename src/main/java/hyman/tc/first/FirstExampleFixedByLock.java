package hyman.tc.first;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Desc 修正第一个例子程序，展现lock的使用方法
 * 
 * @author yinlongcheng 
 *
 */
public class FirstExampleFixedByLock {
	private static int i=0;
	private static Lock lock = new ReentrantLock();
	
	public static void main(String[] args) throws InterruptedException {
		for(int j=0;j<10;j++){
			final int t=j;
			new Thread(new Runnable() {
				public void run() {
					for(int k=0;k<1000;k++){
						lock.lock();
						i++;
						lock.unlock();
					}
					System.out.println("thread "+t+" runned over...");
				}
			}).start();
		}
		
		TimeUnit.SECONDS.sleep(5);
		
		System.out.println(i);
	}
}
