package chapter06;

public class Scala21_Object_OverLoad_Java {
    public static void main(String[] args){

        // TODO 方法的重载,方法中有多个不同的方法，方法名一样
        //  方法名一致，参数列表（参数的个数，参数的顺序，参数的类型）不一样
        //  调用方法，首先匹配类型
        //  如果类型不匹配，那么基本类型会看精度
        byte b = 10;
        test(b);

    }

//    public static void test(byte b) {
//        System.out.println("bbb");
//    }
//
//    public static void test(short s) {
//        System.out.println("sss");
//    }
    public static void test(char c) {
        System.out.println("ccc");
    }
    public static void test(int i) {
        System.out.println("iii");
    }
}




