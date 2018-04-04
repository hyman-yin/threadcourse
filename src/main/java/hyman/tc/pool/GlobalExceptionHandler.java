package hyman.tc.pool;

import java.lang.Thread.UncaughtExceptionHandler;

public class GlobalExceptionHandler implements UncaughtExceptionHandler {
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("捕获到异常。。。。");
		e.printStackTrace();
	}
}
