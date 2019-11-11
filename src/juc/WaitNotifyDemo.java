package juc;


class NumberUpAndDown{
    private int number = 0;
    public synchronized void numberUp() throws InterruptedException{
        while (number != 0)
        {
            this.wait();
        }
        ++number;
        System.out.println("线程名字"+Thread.currentThread().getName()+"number="+number);
        this.notifyAll();
    }
    public synchronized void numberDown() throws InterruptedException{
        while (number == 0)
        {
            this.wait();
        }
        --number;
        System.out.println("线程名字"+Thread.currentThread().getName()+"number="+number);
        this.notifyAll();
    }
}

public class WaitNotifyDemo {
    public static void main(String[] args){

        NumberUpAndDown numberUpAndDown = new NumberUpAndDown();

        new Thread(()->{
            for (int i = 1; i <= 10 ; i++){
                try {
                    Thread.sleep(200);
                    numberUpAndDown.numberUp();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for(int i = 1; i <= 10 ; i++){
                try{
                    Thread.sleep(300);
                    numberUpAndDown.numberDown();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, "BB").start();

        new Thread(()->{
            for (int i = 1; i <= 10 ; i++){
                try {
                    Thread.sleep(400);
                    numberUpAndDown.numberUp();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();

        new Thread(()->{
            for(int i = 1; i <= 10 ; i++){
                try{
                    Thread.sleep(400);
                    numberUpAndDown.numberDown();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, "DD").start();
    }
}
