package hyman.tc.springtest;

import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.TaskExecutor;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		TaskExecutor executor = (TaskExecutor) appContext.getBean("taskExecutor");
		for (int i = 0; i < 100; i++) {
			SpringThread t = new SpringThread(i);
			executor.execute(t);
			TimeUnit.MILLISECONDS.sleep(200);
		}
	}
}
