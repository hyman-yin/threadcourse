package hyman.tc.threadlocal;

public class ThreadLocalTest {
	private static volatile ThreadLocal<Integer> local=new ThreadLocal<Integer>(){
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
		public void run() {
			local.set(0);
			for(int i=0;i<1000;i++){
				local.set(local.get()+1);
			}
			System.out.println(local.get());
		}
	}
}
