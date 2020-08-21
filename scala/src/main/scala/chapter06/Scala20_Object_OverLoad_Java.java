package chapter06;

public class Scala20_Object_OverLoad_Java {
    public static void main(String[] args) throws InterruptedException, CloneNotSupportedException {

        // TODO 方法的重载,方法中有多个不同的方法，方法名一样
        //  方法名一致，参数列表（参数的个数，参数的顺序，参数的类型）不一样
//        B b = new B();
//        test(b);
        AA a = new B();
        test(a);

    }

    public static void test(AA a) {
        System.out.println("aaa");
    }
    public static void test(B b) {
        System.out.println("bbb");
    }
}

class AA{


}

class B extends AA{

}


