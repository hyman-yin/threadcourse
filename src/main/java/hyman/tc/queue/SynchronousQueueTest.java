package hyman.tc.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * Java 6的并发编程包中的SynchronousQueue是一个没有数据缓冲的BlockingQueue，
 * 生产者线程对其的插入操作offer必须等待消费者的移除操作take，反过来也一样，消费者移除数据操作必须等待生产者的插入。
 * 还有其他的1个构造函数,表示创建的队列是否公平
 * SynchronousQueue(boolean flag)
 * @author hyman
 *
 */
public class SynchronousQueueTest {
	public static SynchronousQueue<Integer> queue=new SynchronousQueue<>();
	
	public static void main(String[] args) {
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		
		new Thread(producer).start();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(consumer).start();
	}
	
	static class Producer implements Runnable {
		SynchronousQueue<Integer> queue;
		
		public Producer(SynchronousQueue<Integer> queue) {
			this.queue = queue;
		}
		
		@Override
		public void run() {
			while(true){
				int i=(int)(Math.random()*1000);
				System.out.println("放数据，值为： "+i);
				try {
					queue.put(i);
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	static class Consumer implements Runnable {
		SynchronousQueue<Integer> queue;
		
		public Consumer(SynchronousQueue<Integer> queue) {
			this.queue = queue;
		}
		
		@Override
		public void run() {
			while(true){
				try {
					Integer i = queue.take();
					System.out.println("++++++++++++ 取数据，值为：  "+i);
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
