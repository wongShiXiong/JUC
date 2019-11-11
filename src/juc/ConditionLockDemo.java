package juc;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource{

    private int flag = 1;

    private ReentrantLock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();

    private Condition condition2 = lock.newCondition();

    private Condition condition3 = lock.newCondition();

    //ctrl+alt+t是try catch

    public void print5(int totalLoop){
        lock.lock();
        try {
            //1.判断
            while (flag != 1){
                condition1.await();
            }
            //干活

            for (int i = 1;i <= 5; i++) {
                System.out.println("线程名字:"+Thread.currentThread().getName()+"\t次数:"+i+"\t轮数:"+totalLoop);
            }

            //唤醒+通知
            flag = 2;
            condition2.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10(int totalLoop){
        lock.lock();
        try {
            //1.判断
            while (flag != 2){
                condition2.await();
            }
            //干活

            for (int i = 1;i <= 10; i++) {
                System.out.println("线程名字:"+Thread.currentThread().getName()+"\t次数:"+i+"\t轮数:"+totalLoop);
            }

            //唤醒+通知
            flag = 3;
            condition3.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(int totalLoop){
        lock.lock();
        try {
            //1.判断
            while (flag != 3){
                condition3.await();
            }
            //干活

            for (int i = 1;i <= 15; i++) {
                System.out.println("线程名字:"+Thread.currentThread().getName()+"\t次数:"+i+"\t轮数:"+totalLoop);
            }

            //唤醒+通知
            flag = 1;
            condition1.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }



}

public class ConditionLockDemo {
    public static void main(String[] args) {

        ShareResource sr = new ShareResource();

        new Thread(()->{
            for(int i = 1 ; i<=10 ; i++){
                sr.print5(i);
            }
        },"A").start();

        new Thread(()->{
            for(int i = 1 ; i<=10 ; i++){
                sr.print10(i);
            }
        },"B").start();

        new Thread(()->{
            for(int i = 1 ; i<=10 ; i++){
                sr.print15(i);
            }
        },"C").start();
    }
}
