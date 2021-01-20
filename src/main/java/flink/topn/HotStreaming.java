package flink.topn;

import cn.hutool.core.date.DateUtil;
import com.clearspring.analytics.util.Lists;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.io.InputFormat;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.java.io.PojoCsvInputFormat;
import org.apache.flink.api.java.typeutils.PojoTypeInfo;
import org.apache.flink.api.java.typeutils.TypeExtractor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.io.File;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fenger
 * @date 2021-01-20 10:37
 */
public class HotStreaming {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        PojoCsvInputFormat<UserBehavior> inputFormat = new PojoCsvInputFormat(
                Path.fromLocalFile(new File(HotStreaming.class.getClassLoader().getResource("UserBehavior.csv").toURI())),
                "\n",
                ",",
                (PojoTypeInfo) TypeExtractor.createTypeInfo(UserBehavior.class),
                new String[] {"user", "item", "category", "behavior", "timestamp"}
        );
        DataStreamSource<UserBehavior> sourceStream = env.setParallelism(1).createInput(inputFormat, TypeExtractor.createTypeInfo(UserBehavior.class));

        // flink默认以ProcessingTime作为事件处理事件
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        sourceStream
                // 需要手动指定事件发生时间
                .assignTimestampsAndWatermarks(
                        new AscendingTimestampExtractor<UserBehavior>() {
                            @Override
                            public long extractAscendingTimestamp(UserBehavior element) {
                                return element.getTimestamp()*1000;
                            }
                        }
                )
                .filter(
                        userBehavior -> "pv".equals(userBehavior.getBehavior())
                )
                .keyBy(UserBehavior::getItem)
                .timeWindow(Time.minutes(60), Time.minutes(1))
                .aggregate(
                        new AggregateFunction<UserBehavior, Long, Long>() {
                            @Override
                            public Long createAccumulator() {
                                return 0L;
                            }

                            @Override
                            public Long add(UserBehavior userBehavior, Long aLong) {
                                return aLong + 1;
                            }

                            @Override
                            public Long getResult(Long aLong) {
                                return aLong;
                            }

                            @Override
                            public Long merge(Long aLong, Long acc1) {
                                return aLong + acc1;
                            }
                        },
                        new WindowFunction<Long, ItemCounter, String, TimeWindow>() {
                            @Override
                            public void apply(String s, TimeWindow window, Iterable<Long> input, Collector<ItemCounter> out) throws Exception {
                                out.collect(new ItemCounter(s, window.getEnd(), input.iterator().next()));
                            }
                        }
                )
                .keyBy(ItemCounter::getWindowEnd)
                .process(
                        new KeyedProcessFunction<Long, ItemCounter, String>() {
                            private ListState<ItemCounter> itemStateCounters;

                            @Override
                            public void onTimer(long timestamp, OnTimerContext ctx, Collector<String> out) throws Exception {
                                List<ItemCounter> items = Lists.newArrayList(itemStateCounters.get());
                                String record = items
                                        .stream()
                                        .sorted(Comparator.comparingLong(ItemCounter::getCount).reversed())
                                        .limit(3)
                                        .map(ItemCounter::toString)
                                        .collect(Collectors.joining("\n", "======"+ DateUtil.format(new Date(timestamp), "yyyy-MM-dd HH:ss") + "======\n", "\n"));
                                out.collect(record);
                            }

                            @Override
                            public void open(Configuration parameters) throws Exception {
                                super.open(parameters);
                                itemStateCounters = getRuntimeContext().getListState(new ListStateDescriptor<ItemCounter>("item-state", ItemCounter.class));
                            }

                            @Override
                            public void processElement(ItemCounter value, Context ctx, Collector<String> out) throws Exception {
                                itemStateCounters.add(value);
                                ctx.timerService().registerEventTimeTimer(value.getWindowEnd()+1);
                            }
                        }
                )
                .print();

        env.execute("topN");

    }
}
