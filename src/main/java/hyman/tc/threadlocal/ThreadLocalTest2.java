package hyman.tc.threadlocal;

public class ThreadLocalTest2 {
	private static ThreadLocal<Integer> local = new ThreadLocal<Integer>(){
		@Override
		protected Integer initialValue() {
			return 0;
		};
	};
	
	public static void main(String[] args) {
		MyThread thread = new MyThread();
		for(int i=0;i<10;i++){
			new Thread(thread).start();
		}
	}
	
	static class MyThread implements Runnable {
		@Override
		public void run() {
			for(int i=0;i<10000;i++){
				local.set(local.get()+1);
			}
			
			System.out.println(Thread.currentThread().getName()+", value is : "+local.get());
		}
	}
}
