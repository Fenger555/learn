package flink.topn;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Fenger
 * @date 2021-01-20 13:58
 */
public class ItemCounter {

    private String item;
    private long windowEnd;
    private long count;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public long getWindowEnd() {
        return windowEnd;
    }

    public String getWindowEndFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:ss").format(new Date(windowEnd));
    }

    public void setWindowEnd(long windowEnd) {
        this.windowEnd = windowEnd;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public ItemCounter(String item, long windowEnd, long count) {
        this.item = item;
        this.windowEnd = windowEnd;
        this.count = count;
    }

    @Override
    public String toString() {
        return "ItemCounter{" +
                "item='" + item + '\'' +
                ", count=" + count +
                '}';
    }
}
