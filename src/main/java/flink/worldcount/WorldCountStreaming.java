package flink.worldcount;

import com.google.common.collect.Lists;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.functions.FlatMapIterator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.util.Iterator;

/**
 * @Author gaoxing
 * @Date 2020-11-27 17:07
 */
public class WorldCountStreaming {

    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> dataSource = env.socketTextStream("localhost", 22222, "\n");

        SingleOutputStreamOperator<Tuple2<String, Integer>> worldCuntStream = dataSource
                .flatMap(
                        new FlatMapIterator<String, String>() {
                            @Override
                            public Iterator<String> flatMap(String s) throws Exception {
                                return Lists.newArrayList(s.split(" ")).iterator();
                            }
                        }
                )
                .map(world -> Tuple2.of(world, 1))
                .returns(Types.TUPLE(Types.STRING, Types.INT))
                .keyBy(0)
                .timeWindow(Time.seconds(5))
                .sum(1);

        worldCuntStream.print().setParallelism(1);

        try {
            env.execute("world count");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
