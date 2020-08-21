package rdd;

public class Spark17_RDD_Exception_Java {
    public static void main(String[] args) {

        // TODO 空指针异常，调用一个为空（null）对象的属性和方法，会发生空指针异常
        //  1.属性是成员，出现空指针异常
        //      对象为null，调用成员属性，就会发生空指针异常。
        //  2.属性是静态的，也出现了空指针异常
        //      对象为null，调用静态属性，就不会发生空指针异常
        //      属性为包装类型，但是方法参数是基本类型，那么属性为null时，进行拆箱操作就会发生空指针异常

        // sleep ,wait 的区别
        // sleep静态方法
        // wait成员方法。

        User user = null;
        test(user.age);

    }

    public static void test(int age) {
        System.out.println("年龄=：" + age);
    }
}

class User{
//    public Integer age;   // 发生空指针异常
//    public static Integer age; // 发生空指针异常
    public static Integer age =10;  // 不发生空指针异常
}