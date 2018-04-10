package hyman.tc.queue;

import java.util.concurrent.PriorityBlockingQueue;

public class QueueTest {
	public static void main(String[] args) throws InterruptedException {
//		ArrayBlockingQueue：利用数组实现的队列
//		DelayQueue: 只有当延迟时间到了，才可以渠道队列中的数据，大小无限制
//		LinkedBlockingQueue： 利用链表实现的队列，如果没有指定大小，则大小无限制
//		PriorityBlockingQueue:排序队列
//		SynchronousQueue: 同步队列，用在多线程中
		
		
		/**
		 * 该实现类需要自己实现一个继承了 Comparator 接口的类， 在插入资源时会按照自定义的排序规则来对资源数组进行排序。 
		 * 其中值大的排在数组后面 ，取值时从数组投开始取.
		 * 还有其他的三个构造函数
		 * PriorityBlockingQueue(int i)
		 * public PriorityBlockingQueue(int i, Comparator comparator1)
		 * public PriorityBlockingQueue(Collection collection)
		 */
		PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
		queue.add(1);
		queue.add(3);
		queue.add(2);
		while(queue.size()>0){
			System.out.println(queue.take());
		}
	}
	
}
