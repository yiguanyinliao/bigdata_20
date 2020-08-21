package chapter07;

public class Scala42_Collection_ThreadSafe1 {


    public static void main(String[] args) {

        new Thread(new Runnable() {
            public void run() {
                test();
            }
        }).start();

    }

    public static void test() {

        // StringBuider, StringBuffer
        StringBuffer buffer = new StringBuffer();
        StringBuilder builder = new StringBuilder();
        String s = "";
        for (int i = 0; i < 1000; i++) {
            s = s + i;
            buffer.append(i);   // 线程安全  如果数据不共享，可以不考虑安全，使用builder会提高性能
            builder.append(i);  // 在这里使用builder比较好，因为不涉及线程安全问题
        }
        System.out.println("s = " + s);
    }


}

