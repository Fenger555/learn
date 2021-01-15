package pareto;

import java.util.List;

/**
 * @author Fenger
 * @date 2020-12-30 11:13
 */
public class Defects {

    List<Defect> defectList;
    Number v;

    public Defects() {
    }

    public Defects(List<Defect> defectList) {
        this.defectList = defectList;
    }

    public Number getV() {
        return v;
    }

    public void setV(Number v) {
        this.v = v;
    }

    class Defect {
        String defectId;

        String firstValue;
        String secondValue;
        String normByValue;

        String adderFlag;

        public String getDefectId() {
            return defectId;
        }

        public void setDefectId(String defectId) {
            this.defectId = defectId;
        }

        public String getFirstValue() {
            return firstValue;
        }

        public void setFirstValue(String firstValue) {
            this.firstValue = firstValue;
        }

        public String getSecondValue() {
            return secondValue;
        }

        public void setSecondValue(String secondValue) {
            this.secondValue = secondValue;
        }

        public String getNormByValue() {
            return normByValue;
        }

        public void setNormByValue(String normByValue) {
            this.normByValue = normByValue;
        }

        public String getAdderFlag() {
            return adderFlag;
        }

        public void setAdderFlag(String adderFlag) {
            this.adderFlag = adderFlag;
        }
    }




}
