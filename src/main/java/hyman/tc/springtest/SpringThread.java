package hyman.tc.springtest;

import java.util.concurrent.TimeUnit;

public class SpringThread extends Thread{

    private int parameter;

    public SpringThread(int parameter){
        this.parameter = parameter;
    }
    @Override
    public void run() {
        System.out.println("thread"+parameter + " is running...");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}