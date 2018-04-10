package hyman.tc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

/**
 * @Desc 展示读锁和写锁的功能和使用方法的简单例子，从这个例子中可以看出读锁之间是共存的
 * 
 * @author yinlongcheng 
 *
 */
public class ReadLockSimpleExample1 {
	public static void main(String[] args) {
		ReadWriteLock lock = new ReentrantReadWriteLock(true);
		
		final ReadLock readLock = (ReadLock) lock.readLock();
		
		for(int i=0;i<10;i++){
			final int j=i;
			new Thread(new Runnable() {
				public void run() {
					readLock.lock();
					System.out.println("thread"+j+"进入读取数据");
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("thread"+j+"释放读锁----------");
					readLock.unlock();
				}
			}).start();
		}
	}
}
