package Main;

import java.util.function.Consumer;
public class App {

    public static Consumer<double[]> cons = ((double[] arr) -> {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 2){
                arr[i] *= 0.8;
            }
            else {
                arr[i] *= 0.9;
            }
        }
    });

    public static double[] getChanged(double[] initialArray, Consumer<double[]> consumer) {
        double[] result = new double[initialArray.length];
        for (int i = 0; i < initialArray.length; i++) {
            result[i] = initialArray[i];
        }
        consumer.accept(result);
        return result;
    }
}
