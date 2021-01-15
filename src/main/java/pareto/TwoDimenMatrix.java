package pareto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Fenger
 * @date 2020-12-30 11:21
 */
public class TwoDimenMatrix {

    private Defects[][] twoDimenMatrix;

    private ComputeFunction computeFunction;

    Map<String, Integer> secondIndex;


    public TwoDimenMatrix(Defects[] matrix) {

        twoDimenMatrix = new Defects[matrix.length][secondIndex.size()];
        for (int i = 0; i < matrix.length; i++) {
            // 2. 开始分组
            Map<String, List<Defects.Defect>> collect = matrix[i].defectList.stream().collect(Collectors.groupingBy(Defects.Defect::getSecondValue));
            int index = i;
            secondIndex
                    .keySet()
                    .stream()
                    .forEach(k -> {
                        twoDimenMatrix[index][secondIndex.get(k)] = new Defects(collect.get(k));
                    });
        }

    }

    public Defects[][] compute() {

        return null;
    }

}
