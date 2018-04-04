package hyman.tc.first;

import java.util.concurrent.TimeUnit;

/**
 * @Desc 第一个例子程序，演示多线程的不安全性
 * 
 * @author yinlongcheng 
 *
 */
public class FirstExample {
	private static int i=0;
	
	public static void main(String[] args) throws InterruptedException {
		for(int j=0;j<10;j++){
			final int t=j;
			new Thread(new Runnable() {
				public void run() {
					for(int k=0;k<1000;k++){
						i++;
					}
					System.out.println("thread "+t+" runned over...");
				}
			}).start();
		}
		
		TimeUnit.SECONDS.sleep(5);
		
		System.out.println(i);
	}
}
