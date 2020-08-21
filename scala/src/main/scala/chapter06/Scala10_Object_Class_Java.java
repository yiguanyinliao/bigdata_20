package chapter06;


public class Scala10_Object_Class_Java {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread();
        Thread t2 = new Thread();


        t1.start();
        t2.start();

        // TODO Thread 线程中sleep方法和wait的区别？
        //  核心区别在于两个方法的字体不同
        //  在IDEA中如果识别出方法时静态的时候，会以斜体的方式展示

        // TODO sleep方法时静态方法，wait方法时成员方法
        //  静态方法通过类名访问，成员方法通过对象访问
        //  1.静态方法和类型相关，和对象无关，所以静态方法sleep和对象t1没有任何关系
        //      当前休眠的线程不是t1，哪个线程中调用了sleep方法，哪个线程休眠
        //      当前休眠的线程其实是main线程
        //  2.wait方法时成员方法，和对象相关，所以成员方法wait和对象t2相关
        //      当前等待的线程就是t2线程
        //  3.sleep和类型相关，和对象无关，是从哪里来的对象锁，所以不可能释放对象锁
        //      wait方法和对象相关，所以可以释放获取和释放对象锁
        t1.sleep(1000);
        t2.wait();

    }


}
