package hyman.tc.first;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Desc 利用原子类修正第一个例子程序，展现原子类的功能和使用方法
 * 	Atomic类原理：原子性
 * @author yinlongcheng 
 *
 */
public class FirstExampleFixedByAtomic {
	private static AtomicInteger i=new AtomicInteger(0);
	
	public static void main(String[] args) throws InterruptedException {
		for(int j=0;j<10;j++){
			final int t=j;
			new Thread(new Runnable() {
				public void run() {
					for(int k=0;k<1000;k++){
						i.incrementAndGet();
					}
					System.out.println("thread "+t+" runned over...");
				}
			}).start();
		}
		
		TimeUnit.SECONDS.sleep(5);
		
		System.out.println(i);
	}
}
