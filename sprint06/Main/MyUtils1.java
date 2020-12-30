package Main;

import java.util.function.Predicate;
public class MyUtils1 {

    public static int getCount( int integers[], Predicate<Integer> predicate) {
        int result = 0;
        for(int i : integers){
            if (predicate.test(i)){
                result++;
            }
        }
        return result;

    }
}
