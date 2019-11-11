package juc;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;




//题目要求 一百个线程读 一个线程写

class RwLock{
    private Object obj;

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void writeLock(Object obj){
        try {
            //写
            rwLock.writeLock().lock();
            this.obj=obj;
            System.out.println("写的线程:"+Thread.currentThread().getName()+"写的对象："+obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }
    public void readLock(){
        try {
            //读
            rwLock.readLock().lock();
            System.out.println("读的线程:"+Thread.currentThread().getName()+"读写的obj："+obj);//这个是写完之后，线程再去读的写对象
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }
}

public class RwLockDemo {
    public static void main(String[] args){
        RwLock rwLock = new RwLock();

        new Thread(()->{
            try {
                Thread.sleep(10);//这句一加可能为null因为写延迟了，有些读已经开始读对象了，导致读到空值
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rwLock.writeLock("内容对象");
        },"writeThread").start();



        for(int i = 1; i <= 100 ; i++){
            new Thread(()->{
                rwLock.readLock();
            },String.valueOf(i)).start();
        }
    }
}
