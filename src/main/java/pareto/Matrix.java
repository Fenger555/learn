package pareto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Fenger
 * @date 2020-12-30 17:15
 */
public class Matrix {

    Defects[] defects;
    Map<String, Integer> indexMap;

    public Matrix(Defects defectList) {
        // todo 第一次分组
        // 1. indexMap初始化
//        defectList.defectList.stream().map(Defects.Defect::getFirstValue).distinct();
        defects = new Defects[indexMap.size()];
        // 2. 开始分组
        Map<String, List<Defects.Defect>> collect = defectList.defectList.stream().collect(Collectors.groupingBy(Defects.Defect::getFirstValue));
        indexMap
                .keySet()
                .stream()
                .forEach(k -> {
                    defects[indexMap.get(k)] = new Defects(collect.get(k));
                });
        
    }


}
