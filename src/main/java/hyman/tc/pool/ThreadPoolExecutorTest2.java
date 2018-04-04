package hyman.tc.pool;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @Desc 手动创建线程池
 * 
 * @author yinlongcheng 
 *
 */
public class ThreadPoolExecutorTest2 {
	public static void main(String[] args) {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 10, 10, TimeUnit.SECONDS,null);
		
		pool.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread is running......");
			}
		});
	}
}
