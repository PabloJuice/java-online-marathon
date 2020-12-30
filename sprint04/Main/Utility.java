package Main;

import java.util.Arrays;
import java.util.Comparator;

class PersonComparator<T extends Comparable<T>> implements Comparator<T>{

    @Override
    public int compare(T o1, T o2) {
        return 0;
    }
}
class EmployeeComparator<T extends Comparable<T>> implements Comparator<T>{

    @Override
    public int compare(T o1, T o2) {
        return 0;
    }
}
class DeveloperComparator<T extends Comparable<T>> implements Comparator<T>{

    @Override
    public int compare(T o1, T o2) {
        return 0;
    }
}

public class Utility {
    public static <T extends Person> void sortPeople(T[] people, Comparator<? super T> comparator){
        Arrays.sort(people, comparator);
    }
}
