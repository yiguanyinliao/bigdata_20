package chapter06;

public class Scala34_Object_Interface_Java {
    public static void main(String[] args){

//        CC cc = new AAAA();
//        AA aa = new BB();
//        System.out.println(cc);
//        System.out.println(aa);

        // 多态的传递
//        CC cc = new BB();  // 接口不能直接构建对象，需要依赖实现类构建对象
//        System.out.println(cc);
        // BB 没有实现CC的接口，因为接口和类是两个不同的体系
        System.out.println(BB.class.getInterfaces().length);
    }


}

interface CC{

}

class TT {

}

class AAAA extends TT implements CC {

}

class BB extends AAAA{

}