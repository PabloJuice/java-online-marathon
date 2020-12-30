package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();
//        map.put("0967654321", "Petro");
//        map.put("0677654321", "Petro");
//        map.put("0501234567","Ivan");
//        map.put("0970011223", "Stepan");
//        map.put("0631234567", "Ivan");
//        System.out.println(map.keySet());
//        System.out.println(new MyUtils().createNotebook(map));

        List<MyUtils.Student> list1  = new ArrayList<>();
        list1.add(new MyUtils.Student(1,"AB"));
        list1.add(new MyUtils.Student(1,"A"));
        list1.add(new MyUtils.Student(3,"B"));
        list1.add(new MyUtils.Student(1,"Aa"));
//
        List<MyUtils.Student> list2  = new ArrayList<>();
        list2.add(new MyUtils.Student(1,"AB"));
        list2.add(new MyUtils.Student(2,"A"));
        list2.add(new MyUtils.Student(3,"B"));
        list2.add(new MyUtils.Student(1,"Aa"));
//
        System.out.println(new MyUtils().commonStudents(list1, list2));

//        Integer[] numbers = new Integer[3];
//        System.out.println(ArrayUtil.<Integer>setAndReturn(numbers,22,0));

        Array<Integer> set1 = new Array<>(new Integer[]{1,2,3,4,5});
        double averageValue = ArrayUtil.averageValue(set1);
        System.out.println(averageValue);
    }
}
