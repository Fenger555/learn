import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author gaoxing
 * @Date 2020-07-11 14:59
 */
public class Test {

    public static void main(String[] args) {

        Map<Integer, Integer> groupingBinCntMap = Maps.newHashMap();
        groupingBinCntMap.put(0,10);
        groupingBinCntMap.put(1,20);
        groupingBinCntMap.put(2,30);
        groupingBinCntMap.put(6,5);
        groupingBinCntMap.put(8,30);
        groupingBinCntMap.put(7,30);

        List<Integer> rs = groupingBinCntMap
                .keySet()
                .stream()
                .collect(Collectors.toList())
                .stream()
                .sorted(
                        new Comparator<Integer>() {
                            @Override
                            public int compare(Integer o1, Integer o2) {
                                int rs = groupingBinCntMap.get(o2) - groupingBinCntMap.get(o1);
                                return rs == 0 ? o1 - o2 : rs;
                            }
                        }
                )
                .collect(Collectors.toList());

        System.out.println(rs);


    }
}
