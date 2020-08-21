package chapter09;

public class Scala01_Exception_Java {


    public static void main(String[] args) {

        try {
            // 编译时异常：提示性异常，一般必须进行处理，try catch语法主要是解决问题
            //      不是为了调试错误。
            // 运行时异常：可以通过编码的方式解决
            // java中的异常可以捕捉多个,捕捉顺序是从上到下，如果异常已经捕捉，那么后面的捕捉就执行不到
            // 捕捉顺序是将异常范围小的先捕捉，然后在捕捉范围大的异常。
            int i = 0;
            int j = 10 / i;
        } catch (ArithmeticException e) {
            System.out.println("算术异常。。。。");;
            throw e;
        } catch (Exception e) {
            // 异常：为了保证程序的健壮性，可以顺利执行完成
            e.printStackTrace();
        }
        System.out.println("main 方法执行完毕。。");
    }
}