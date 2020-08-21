package chapter11;

import chapter11.bean.Child;
import chapter11.bean.Parent;
import chapter11.bean.Test;
import chapter11.bean.User;


public class Scala03_Generic_Java2 {
    public static void main(String[] args) {

        // TODO 2.泛型 的使用
        //  泛型的不可变：定义好泛型后，不能随意被改变.

        Test<User> test1 = new Test<User>();
//        Test<User> test2 = new Test<Child>(); // (X)
//        Test<User> test3 = new Test<Parent>(); // (X)


        // TODO 为了在数据处理中，泛型使用起来更加方便，提供了泛型边界进行类型的约束

        // TODO 泛型边界 上限：? extends User  => 当前类和其子类

        Test<User> test4 = new Test<User>();
        Test<Child> test5 = new Test<Child>();
//        Test<Parent> test6 = new Test<Parent>();  // (X)

        // TODO 泛型边界 下限: ? super User
        //  下限不能拥有类型的声明
        //  首先上限：是用于获取数据时约定（限制）数据类型
        //  下限的主要作用：传递数据是的数据类型.

        Consumer<Child> c = new Consumer<Child>();
        Message<? extends User> message = c.consumer();
        final User data = message.data;

        Producer<User> p = new Producer<User>();
        p.produce(new Message<User>());
        p.produce(new Message<Parent>());
//        p.produce(new Message<Child>());  // (X)

    }


}

class Producer<T> {
    public void produce(Message<? super T> message) {

    }
}


class Consumer<T>{
    public Message<? extends T> consumer() {
        return null;
    }
}
class Message<T>{
    public T data;
}