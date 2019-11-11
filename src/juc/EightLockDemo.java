package juc;

import java.util.concurrent.TimeUnit;

class GetPhone{
    public static synchronized void getIOS() throws InterruptedException{
        TimeUnit.SECONDS.sleep(3);
        System.out.println("得到IOS手机");
    }
    public static synchronized void getAndroid(){
        System.out.println("得到Android手机");
    }
    public void getHello(){
        System.out.println("hello");
    }
}
/*
标准访问，先打印苹果还是android ? 先IOS再Android
睡觉4秒钟，先打印苹果还是android? 先IOS再Android
新增he11o方法，先打印苹果还是hello? 先hello再IOS
有两部手机，先打印苹果还是android? 先Android再IOS
两个静态同步方法，有一部手机,先打印苹果还是android? 先IOS再Android
两个静态同步方法，有2部手机，先打印苹果还是android? 先IOS再Android
一个静态同步方法，一个普通同步方法，有一部手机，先打印苹果还是android? 先Android再IOS
一个静态同步方法，一个普通同步方法，有2部手机，先打印苹果还是android? 先Android再IOS
 */

public class EightLockDemo {
    public static void main(String[] args){

        GetPhone phone = new GetPhone();
        GetPhone phone1= new GetPhone();

        new Thread(()->{
            try {
                phone.getIOS();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"AA").start();

        new Thread(()->{
                phone.getAndroid();
                //phone.getHello();
                //phone1.getAndroid();
        },"BB").start();
    }

}
