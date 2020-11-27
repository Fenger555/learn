package flink.worldcount;

import com.google.common.collect.Lists;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.functions.FlatMapIterator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import scala.Tuple2;

import java.util.Iterator;

/**
 * @Author gaoxing
 * @Date 2020-11-27 17:07
 */
public class WorldCountStreaming {

    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> dataSource = env.socketTextStream("localhost", 22222);

        dataSource
                .flatMap(
                        new FlatMapIterator<String, String>() {
                            @Override
                            public Iterator<String> flatMap(String s) throws Exception {
                                return Lists.newArrayList(s.split(" ")).iterator();
                            }
                        }
                )
                .map(world -> new Tuple2(world, 1));

    }


}
