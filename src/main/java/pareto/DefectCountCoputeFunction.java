package pareto;

/**
 * @author Fenger
 * @date 2020-12-30 17:44
 */
public class DefectCountCoputeFunction implements ComputeFunction{

    @Override
    public void compute(Defects defects) {
        defects.setV(defects.defectList.size());
    }

}
