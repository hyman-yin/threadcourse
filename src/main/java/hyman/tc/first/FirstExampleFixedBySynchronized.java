package hyman.tc.first;

import java.util.concurrent.TimeUnit;

/**
 * @Desc 利用synchronized关键字修正第一个例子程序，展现synchronized的功能和使用方法
 * 		synchronized原理：可见性，原子性
 * 
 * @author yinlongcheng 
 *
 */
public class FirstExampleFixedBySynchronized {
	private static int i=0;
	private static Object object = new Object();
	
	public static void main(String[] args) throws InterruptedException {
		for(int j=0;j<10;j++){
			final int t=j;
			new Thread(new Runnable() {
				public void run() {
					synchronized (object) {
						for(int k=0;k<1000;k++){
							i++;
						}
					}
					System.out.println("thread "+t+" runned over...");
				}
			}).start();
		}
		
		TimeUnit.SECONDS.sleep(5);
		
		System.out.println(i);
	}
}
