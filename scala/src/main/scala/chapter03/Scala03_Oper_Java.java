package chapter03;

public class Scala03_Oper_Java {

    public static void main(String[] args) {


        // Scala 中没有 ++ ， --
        // 因为 ++ ，-- 运算有歧义，Scala语法中不希望出现有歧义的语法
//        int i = 10;
//        int j = i++;
//        System.out.println("i=" + i + ",j=" + j);


        System.out.println("=======================");

        // 等号的作用： 将等号右边的计算结果复制给等号的左边
        // 先赋值再累加

        // 1 => 将i赋值给一个临时变量 =10
        // 2 => 将临时变量作为右边的计算结果赋值给等号的左边


        int i = 10;
        i = i++;
        System.out.println("i=" + i );

    }
}
