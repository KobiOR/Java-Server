package Boot;

import java.io.Serializable;

/**
 * Created by orrko_000 on 14/10/2016.
 */

public class MathExercise implements Serializable {
    private int num1;
    private int num2;
    private String op;

    public int getNum1() {
        return num1;
    }
    public void setNum1(int num1) {
        this.num1 = num1;
    }
    public int getNum2() {
        return num2;
    }
    public void setNum2(int num2) {
        this.num2 = num2;
    }
    public String getOp() {
        return op;
    }
    public void setOp(String op) {
        this.op = op;
    }


}
