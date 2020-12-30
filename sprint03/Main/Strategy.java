package Main;

public interface Strategy {
    double doOperation(int a, int b);
}
class Strateg{
    public static void execute(int a, int b, Strategy strategy){
        double result = strategy.doOperation(a,b);
    }
    public static void addAtoB(int a, int b) {
        execute(a,b, new Strategy(){
            @Override
            public double doOperation(int a, int b){
                return a+b;
            }
        });

    }

    public static void subtractBfromA(int a, int b) {
        execute(a,b, new Strategy(){
            @Override
            public double doOperation(int a, int b){
                return a-b;
            }
        });

    }

    public static void multiplyAbyB(int a, int b) {
        execute(a,b, new Strategy(){
            @Override
            public double doOperation(int a, int b){
                return a*b;
            }
        });

    }

    public static void divideAbyB(int a, int b) {
        execute(a,b, new Strategy(){
            @Override
            public double doOperation(int a, int b){
                return a/b;
            }
        });

    }
}

