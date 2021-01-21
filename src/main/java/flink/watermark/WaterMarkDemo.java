package flink.watermark;

import cn.hutool.core.date.DateUtil;
import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * @author Fenger
 * @date 2021-01-21 10:25
 */
public class WaterMarkDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> dataSource = env.socketTextStream("localhost", 22222, "\n");

//        env.setParallelism(1);
        env.getConfig().setAutoWatermarkInterval(10000L);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        dataSource
                .map(
                        line -> {
                            String[] ss = line.split(",");
                            return Tuple2.of(ss[0], Long.valueOf(ss[1]));
                        }
                )
                .returns(Types.TUPLE(Types.STRING, Types.LONG))
                .assignTimestampsAndWatermarks(
                        new AssignerWithPeriodicWatermarks<Tuple2<String, Long>>() {
                            private Long currentMaxTimestamp = 0L;
                            private Watermark watermark;

                            @Nullable
                            @Override
                            public Watermark getCurrentWatermark() {
                                System.out.println("wall clock is " + System.currentTimeMillis() + " new watermark " + (currentMaxTimestamp - 10000L));
                                watermark = new Watermark(currentMaxTimestamp - 10000L);
                                return watermark;
                            }

                            @Override
                            public long extractTimestamp(Tuple2<String, Long> element, long recordTimestamp) {
                                currentMaxTimestamp = Math.max(element.f1, currentMaxTimestamp);
                                System.out.println("timestamp:" + element.f0 + "," + element.f1 + "|" + DateUtil.formatDateTime(new Date(element.f1)) + "," + currentMaxTimestamp + "|" + DateUtil.formatDateTime(new Date(currentMaxTimestamp)) + "," + watermark.toString());
                                return element.f1;
                            }
                        }
                )
                .keyBy(tuple -> tuple.f0)
                .window(TumblingEventTimeWindows.of(Time.seconds(3)))
                .apply(
                        new WindowFunction<Tuple2<String, Long>, Object, String, TimeWindow>() {
                            @Override
                            public void apply(String s, TimeWindow window, Iterable<Tuple2<String, Long>> input, Collector<Object> out) throws Exception {


                            }
                        }
                )
                .print();

        env.execute("WaterMarkDemo");
    }
}
