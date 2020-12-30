package Main;

import java.util.function.Predicate;
import java.util.Set;

class MyUtils2{

    public static Predicate<Integer> getPredicateFromSet(Set<Predicate<Integer>> s){
        Predicate<Integer> result = (Integer i) -> true;
        for (Predicate<Integer> p: s){
            result = result.and(p);
        }
        return result;
    }

}

