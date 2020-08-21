package chapter06;

public class Scala43_Object_Super_Java {
    public static void main(String[] args){
        User40 user40 = new User40();
        user40.test();
    }


}


class Person40{
    public int age = 20;
}

class User40 extends Person40{
    public int age = 30;

    public void test() {
        System.out.println(super.age);
        System.out.println(this.age);
    }

}