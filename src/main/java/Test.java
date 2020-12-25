import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.BitSet;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @Author gaoxing
 * @Date 2020-07-11 14:59
 */
public class Test {

    public static void main(String[] args) {

        String collect = Lists.newArrayList().stream().map(v -> v.toString()).collect(Collectors.joining(","));
        System.out.println(collect);

        int a=1,b=2,c=3;
        a=b=c;

        Random random = new Random();

        retry:
        for (;;) {
            System.out.println("-----------------");
            for (;;) {
                int i = random.nextInt();
                System.out.println(i);
                if (i%13==0) {
                    System.out.println("***" +i);
                    continue retry;
                }
                break retry;
            }
        }

        System.out.println("s");



    }
}
