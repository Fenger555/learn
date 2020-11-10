package function;

import com.google.common.collect.Lists;
import org.apache.spark.api.java.function.Function3;

import java.util.List;
import java.util.function.Function;

/**
 * @Author gaoxing
 * @Date 2020-08-14 15:26
 */
public class MyFuction {

    public static void main(String[] args) {
        Function3<List<String>, Integer, Integer, String> func = (initVal, x, y) -> {

            return "";
        };

        Function<List, Integer> size = l -> l.size();

        size.apply(Lists.newArrayList());

    }

    class Statistic implements Function3<List<String>, Integer, Integer, String> {

        @Override
        public String call(List<String> v1, Integer v2, Integer v3) throws Exception {
            return null;
        }


    }
}
