import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author gaoxing
 * @Date 2020-08-25 14:14
 */
public class Test1 {

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("a", "b", "c");
        List<String> list1 = Lists.newArrayList("aa", "cc", "ee");
        List<List<String>> list3 = Lists.newArrayList(list, list1);

        list3.get(1).set(1,"x");
        System.out.println(list3);
    }
}
