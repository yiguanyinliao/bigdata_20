package chapter06;

import java.lang.Object;

//public class Scala16_Object_Field_Access_Java extends Object{
public class Scala16_Object_Field_Access_Java extends Object{
    public static void main(String[] args) throws InterruptedException, CloneNotSupportedException {

        // TODO 访问权限：方法的提供者和方法的调用者之间的关系
        //  方法的提供者：java.lang.Object
        //  方法的调用者：chapter06.A
//        Scala16_Object_Field_Access_Java s = new Scala16_Object_Field_Access_Java();
//        s.clone();

        // 同类（非）、同包（非）、父子类（是）

        // Java中.的作用不是调用关系，是从属关系，clone方法时属于a对象的
        A a = new A();
        a.clone(); // 调用了a对象中的clone方法

    }
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }


}

class A extends Object{
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    }

