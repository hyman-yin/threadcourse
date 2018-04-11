package hyman.tc.pool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.print.attribute.standard.MediaName;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Desc ThreadPoolTaskExecutor是spring提供的一个线程池
 * 
 * @author yinlongcheng 
 *
 */
public class ThreadPoolTaskExecutorTest4 {
	public static void main(String[] args) {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(4);
		pool.setKeepAliveSeconds(10);
		pool.setMaxPoolSize(10);
		pool.setRejectedExecutionHandler(new MyRejectHandler());
		pool.setQueueCapacity(5);
		
		for(int i=0;i<100;i++){
			final int j=i;
			pool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("thread"+j+" is running...");
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		
	}
	
	
	static class MyRejectHandler implements RejectedExecutionHandler {
		@Override
		public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadpoolexecutor) {
			System.out.println("好烦，请求被拒绝了！！！");
		}
		
	}
}
