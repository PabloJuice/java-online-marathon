package Main;

import java.util.Arrays;
import java.util.List;

public class ArrayUtil {
    public static <T> T setAndReturn(T[] list, T value, int position){
        list[position] = value;
        return (T)list[position];
    }
    public static double averageValue(Array array){
        double result = 0;
        for (int i = 0; i < array.length(); i++) {
            result += Double.parseDouble(array.get(i).toString());
            if (i+1 == array.length()){
                result/=i+1;
            }

        }
        return result;
    }
}
class Array<T>{
    private T[] array;

    public Array(T[] array){
        this.array = array;
    }
    public T get(int index){
        return array[index];
    }
    public int length(){
        return array.length;
    }
}