import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @Author gaoxing
 * @Date 2020-07-11 14:59
 */
public class Test {

    public static void main(String[] args) {

        List<String> arr1 = Lists.newArrayList();
        List<String> arr2 = Lists.newArrayList();

        arr1.add("a");
        arr2.add("a");

        arr1.removeAll(arr2);

        System.out.println(arr1);



    }
}
