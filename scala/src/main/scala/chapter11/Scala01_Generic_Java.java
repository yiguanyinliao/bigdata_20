package chapter11;


public class Scala01_Generic_Java {
    public static void main(String[] args) {

        // TODO 1.泛型介绍
        //  JDK1.5后引入（马丁-Pizza语言）
        //  对象类型和泛型不是一个层面上的东西
        //  类型主要修饰对象、变量、参数、方法返回值类型
        //  泛型主要修饰（约束）指定类型内部数据的类型。

        // 类声明了泛型，那么泛型可以理解为类型参数
        // 如果不传参数，那么参数就自动作为Object来使用。
//        Test test = new Test();
//        test.t // Object

        // 如果传递这个参数，参数会改变指定类的属性类型
//        Test<User> test = new Test<User>();
//        test.t // User
    }
}
