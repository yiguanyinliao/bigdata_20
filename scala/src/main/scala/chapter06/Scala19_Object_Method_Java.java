package chapter06;

public class Scala19_Object_Method_Java {
    public static void main(String[] args) throws InterruptedException, CloneNotSupportedException {

        // TODO 方法的重写和重载

        // TODO 方法的重写
        //  1.存在父子类
        //  2.父子类中都存在相同的方法
        //     方法名一致，参数列表一致，异常范围，访问权限

        // TODO 所谓的方法的重写，子类的方法覆盖了父类的方法
        //  在执行是，会首先找到子类的方法，如果找不到对应的方法，才会查找父类的方法
        //  动态绑定机制，在JVM执行时，如果调用对象的成员方法那么将成员方法和当前对象的实际内存进行绑定，然后调用
        //  成员属性没有绑定的概念，在哪里声明，在哪里使用
        AAA aaa = new BBB();
        System.out.println(aaa.sum());

    }
}

class AAA{
    public int i = 10;
//    public int sum() {
//        return i + 10;
//    }
    public int sum() {
        return getI() + 10;
    }

    public int getI() {
        return i;
    }

}
class BBB extends AAA{
    public int i = 20;

//    public int sum() {
//        return i + 20;
//    }
    public int getI() {
        return i;
    }
}


