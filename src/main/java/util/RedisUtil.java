package util;

import redis.clients.jedis.Jedis;

/**
 * @Author gaoxing
 * @Date 2020-11-17 10:22
 */
public class RedisUtil {

    private static volatile Jedis jedis;

    private RedisUtil() {}

//    public static Jedis getInstance() {
//        if (jedis == null) {
//            synchronized (RedisUtil.class) {
//                if (jedis == null) {
//                    jedis = new Jedis("hadoop6.allinabc", 30120);
//                }
//            }
//        }
//        return jedis;
//    }

    public static Jedis getInstance() {
        return new Jedis("hadoop6.allinabc", 30120);
    }

}
