package chapter07;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Scala37_Collection_Method {



    // TODO 使用java实现两个map集合合并
    public static void main(String[] args) {
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        map1.put("a", 1);
        map1.put("b", 2);
        map1.put("c", 3);

        Map<String, Integer> map2 = new HashMap<String, Integer>();
        map2.put("a", 4);
        map2.put("b", 5);
        map2.put("c", 6);

        // TODO 以一个集合（A）作为中心，保持不变，另外一个集合（B）循环变量每一个元素（KV）
        //  1.判读B中的KV中K在A中是否存在
        //  2.如果A中不存在对应的K，那么在A中添加对应的KV
        //  3.如果A中存在对应K，那么从A中取出对应的V，然后增加后在更新到A中。


        final Iterator<String> iterator = map2.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Integer b_val = map2.get(key);


            final Integer a_val = map1.get(key);
            if (a_val == null) {
                map1.put(key, b_val);
            }else {
                map1.put(key,a_val + b_val);
            }
        }
        System.out.println(map1);
    }
}
