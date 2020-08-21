package chapter03;

public class Scala05_Oper_Java {

    public static void main(String[] args) {

        // 判断字符串是否为空，如果不为空，返回true，吐过为空，返回false
        // 字符从如果为null，表示空，如果是空字符串，也为空

        //& 逻辑与，所有条件都会判断，&无论左边的结果是否为真,都将继续运算右边的逻辑表达式
        // && 短路与，如果&&前面的条件判断出现false，&&后面的逻辑将不再进行,结果为false

//        String s = "aa";
        String s = null;
        boolean flg = isNotEmpty(s);
        System.out.println(flg);

    }


    private static boolean isNotEmpty(String s) {
        return s != null && !s.equals("");
    }
}
