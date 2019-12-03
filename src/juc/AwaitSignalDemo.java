package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/*Test*/
class LockNumber{

    private int number = 0;

    private ReentrantLock lock=new ReentrantLock();

    private Condition condition=lock.newCondition();

    public void numberUp() throws InterruptedException{
        try {
            lock.lock();
            while (number != 0)
            {
                condition.await();
            }
            ++number;
            System.out.println("操作线程加"+Thread.currentThread().getName()+"number="+number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void numberDown() throws InterruptedException{
        try {
            lock.lock();
            while (number == 0)
            {
                condition.await();
            }
            --number;
            System.out.println("操作线程减"+Thread.currentThread().getName()+"number="+number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

public class AwaitSignalDemo {
    public static void main(String[] args){

        LockNumber lockNumber = new LockNumber();

        new Thread(()->{
            for (int i = 1; i <= 10 ; i++){
                try {
                    Thread.sleep(199);
                    lockNumber.numberUp();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for(int i = 1; i <= 10 ; i++){
                try{
                    Thread.sleep(299);
                    lockNumber.numberDown();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(()->{
            for (int i = 1; i <= 10 ; i++){
                try {
                    Thread.sleep(399);
                    lockNumber.numberUp();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for(int i = 1; i <= 10 ; i++){
                try{
                    Thread.sleep(399);
                    lockNumber.numberDown();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
