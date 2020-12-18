package flink.worldcount;

import com.google.common.collect.Lists;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.functions.FlatMapIterator;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import scala.Tuple2;

import java.util.Iterator;

/**
 * @Author gaoxing
 * @Date 2020-11-27 17:07
 */
public class WorldCountBatch {

    public static void main(String[] args) {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        DataSource<String> dataSource = env.readTextFile("/Users/gaoxing/IdeaProjects/LearnProject/src/main/java/flink/worldcount/WorldCountBatch.java");

        AggregateOperator<Tuple2> rs = dataSource
                .flatMap(
                        new FlatMapIterator<String, String>() {
                            @Override
                            public Iterator<String> flatMap(String s) throws Exception {
                                return Lists.newArrayList(s.split(" ")).iterator();
                            }
                        }
                )
                .map(world -> new Tuple2(world, 1))
                .groupBy(0)
                .sum(1);

        try {
            rs.print();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
