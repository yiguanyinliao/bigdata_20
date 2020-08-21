package chapter08;

public class Scala01_Match_Java {

    // Scala 中的模式匹配类似于Java中的switch语法
    // Scala 中没有Switch语法，因为Switch语法有歧义、
    public static void main(String[] args) {


        // switch 多重分支判断
//        int i = 30;  //  把default放在最前面会有Switch的穿透现象
        int i = 5;
        switch (i) {
//            default:
//                System.out.println("other");
            case 5:
            case 10:
                System.out.println("i= 10");
                break;
            case 20:
                System.out.println("i= 20");
                break;
            default:
                System.out.println("other");


        }
        System.out.println("main....");

    }
}
