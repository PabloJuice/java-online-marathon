package Main;

import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.ArrayList;
import java.util.List;

class MyUtils{
    public static int findMaxByCondition(List<Integer> numbers, Predicate<Integer> pr) {
        int maxValue= 0;
        for (Integer i : numbers){
            if (pr.test(i)){
                if (i > maxValue){
                    maxValue = i;
                }
            }
        }

        return maxValue;

    }
}

class User {
    public final List<Integer> values = new ArrayList<Integer>();

    int getFilterdValue( BiFunction b,Predicate<Integer> pr){

        return 0;

    }

    int getMaxValueByCondition(Predicate<Integer> predicate) {

        return 0;

    }
}
