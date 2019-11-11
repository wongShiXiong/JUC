package juc;

import java.util.Arrays;

@FunctionalInterface
interface Print{
    //public void print();
    public int update(int x,int y);
    public static void hello(){
        System.out.println("hello");
    }
}

public class LambdaDemo {
    public static void main(String[] args) {
/*        Print print= new Print() {
            @Override
            public void print() {
                System.out.println("匿名内部类，实现new接口编程");
            }
        };
        print.print();*/


/*        Print print1=()-> {
            System.out.println("lambda表达式面向函数式接口编程");
        };
        print1.print();*/

            Print print=(x,y)->{
                return x+y;
            };
        System.out.println(print.update(2,3));

        Print.hello();//静态方法引用

        Arrays.asList(1,2,3,4,5).forEach(System.out::println);//方法引用

    }
}
