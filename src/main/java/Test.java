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

        System.out.println(DateTimeFormatter.ofPattern("E L  dd HH:mm:ss yyyy").format(LocalDateTime.now()));
        System.out.println(DateTimeFormatter.ofPattern("E M  dd HH:mm:ss yyyy").parse("星期三 2  03 17:00:36 2021"));

        SimpleDateFormat sdf = new SimpleDateFormat("E L  dd HH:mm:ss yyyy");
        try {
            Date parse = sdf.parse("Fri Dec  4 12:48:46 2020");
            System.out.println(parse.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
