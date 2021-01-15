package pareto;

/**
 * @author Fenger
 * @date 2020-12-30 11:15
 */
public class ThreeDimenMatrix {

    private Defects[][][] matrix;

    public Defects[][] normBy() {
        Defects[][] defects = new Defects[1][1];

        for (int i = 0; i < matrix.length; i++) {
            Defects[][] matrix = this.matrix[i];
//            TwoDimenMatrix twoDimenMatrix = new TwoDimenMatrix(matrix);
//            Defects[][] rs = twoDimenMatrix.compute();

            // todo: merge
        }

        return defects;
    }

}
