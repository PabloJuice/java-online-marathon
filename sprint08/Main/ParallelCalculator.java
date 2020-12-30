package Main;

import java.util.function.BinaryOperator;

public class ParallelCalculator implements Runnable{
    public int result;
    BinaryOperator<Integer> byOp;
    int operand1, operand2;
    public ParallelCalculator(BinaryOperator<Integer> byOp, int operand1, int operand2){
        this.byOp = byOp;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public void run() {
        result = byOp.apply(operand1, operand2);
    }
}
class Accountant {
    public static int sum(int x, int y) {
        ParallelCalculator pC = new ParallelCalculator( (a,b)-> a + b,x,y);
        Thread thread = new Thread(pC);
        thread.run();
        return pC.result;
    }
}
