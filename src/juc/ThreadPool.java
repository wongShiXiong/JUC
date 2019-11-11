package juc;

import java.util.Random;
import java.util.concurrent.*;

class Pool{
    public void poolThread(){
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//五个线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//一个线程
        ExecutorService threadPool = Executors.newCachedThreadPool();//系统看你需要几个线程，智能调用

        Future result = null;

        try {
            for (int i = 1;i <= 10000;i++){
                    result = threadPool.submit(()->{
                    System.out.print(Thread.currentThread().getName());
                    return new Random().nextInt(50);
                });
                System.out.println("######result:"

                        +result.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }
}
class Pool1{
    public void poolThread(){
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);

        ScheduledFuture<Integer> result = null;

        try {
            for (int i = 1;i <= 20;i++){
                    result = threadPool.schedule(()->{
                    System.out.println(Thread.currentThread().getName());
                    return new Random().nextInt(100);
                },2, TimeUnit.SECONDS);
                System.out.println("必须用返回值的get()方法才能真正使用时间调度线程:"+result.get());//不用get会直接全部打印
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }
}

public class ThreadPool {
    public static void main(String[] args) {
        /*Pool pool = new Pool();
        pool.poolThread();*/
        Pool1 pool1 = new Pool1();
        pool1.poolThread();

        //Pool1.poolThread();方法是static可以直接通过类名调用
    }
}
