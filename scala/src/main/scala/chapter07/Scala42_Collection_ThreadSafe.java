package chapter07;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Scala42_Collection_ThreadSafe {


    public static void main(String[] args) {



        // TODO 1.有几个线程？
        //  t1、t2、main 3个线程
        // TODO 2.哪个线程先执行
        //  main线程先执行
        //  t1.start并不能保证一定优于t2执行
        //  main -> t1 -> t2
        // TODO 3.执行后打印的内容是什么
        //  main方法执行完毕 -> zhangsan -> lisi


        // TODO 所谓的线程安全问题：其实是多线程并发执行时，对共享内存中的共享对象的属性
        //  进行修改所导致的数据冲突问题  -- 导致产生线程安全问题
        //  解决方案：
        //      1.串行：性能太低（可以，不推荐）
        //      2.对象不保存到共享内存中，不放到堆里面，放到栈里面，栈上分配，逃逸分析(对象可不可以在其作用域外使用，把对象当做
        //      返回值返回出去)
        //      3.如果多线程访问的不是共享对象，各自创建一个独享对象
        //      4.如果多线程不修改属性，只是访问属性。


        final User user = new User();

        Thread t1 = new Thread(
                new Runnable() {
                    public void run() {
//                        User user = new User();
                        user.name = "zhangsan";
                        try {
                            Thread.sleep(1000);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println(user.name);
                    }
                }
        );

        Thread t2 = new Thread(
                new Runnable() {
                    public void run() {
//                        User user = new User();
                        user.name = "lisi";
                        try {
                            Thread.sleep(1000);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println(user.name);
                    }
                }
        );

        t1.start();
        t2.start();

        System.out.println("main方法执行完毕");


    }
}


class User{
    public String name;
}
