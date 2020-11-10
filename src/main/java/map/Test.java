package map;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author gaoxing
 * @Date 2020-10-07 11:36
 */
public class Test {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();

        for (int i = 0; i < 11; i++) {
            map.put("a" + i, "c");
        }

        map.put("1", "");
        map.put("2", "");
        map.put("3", "");




    }
}
