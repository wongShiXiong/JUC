package juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 资源类
 */
class Ticket{

    private int number=30;

    private Lock lock = new ReentrantLock();

    public void sale(){
        lock.lock();//上锁
        try {
            //int i= 1/0;
            if(number > 0) {
                System.out.println("名称：" + Thread.currentThread().getName() + "-----卖出第：-----" + (number--) +"剩余票数"+ number);
            }
        } catch (Exception e) {
            System.out.println("抛异常。。。。");
            e.printStackTrace();
        } finally {
            //System.out.println("finally 执行。。。。。");
            lock.unlock();//释放锁
        }
    }
}

// 线程 操作 资源类

// 高内聚 低耦合

public class JucDemo {

    //主函数 一切程序入口
    public static void main(String[] args){

        //final在jdk8 里面可加可不加，因为在8之前无法确定你的匿名内部类里面只有一个方法所以要加final防止你再加其他方法
        final Ticket ticket = new Ticket();

        new Thread(() -> {for(int i = 1; i <=40 ; i++) {ticket.sale();}},"AA").start();
        new Thread(() -> {for(int i = 1; i <=40 ; i++) {ticket.sale();}},"BB").start();
        new Thread(() -> {for(int i = 1; i <=40 ; i++) {ticket.sale();}},"CC").start();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i <=40 ; i++){
                    ticket.sale();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i <=40 ; i++){
                    ticket.sale();
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i <=40 ; i++){
                    ticket.sale();
                }
            }
        }, "C").start();*/
    }
}
