package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class myThread2 implements Runnable{

    @Override
    public void run() {
        System.out.println("run");
    }
}

class myThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("callable接口");
        return 1000;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws Exception {

        FutureTask<Integer> futureTask = new FutureTask(new myThread());

        new Thread(futureTask,"AA").start();

        System.out.println(futureTask.get());

    }
}
