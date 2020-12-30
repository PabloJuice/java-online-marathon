package Main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        List<Caffee> originList = new ArrayList<>();
//        originList.add(new Espresso("Espresso", 8));
//        originList.add(new Cappuccino("Cappuccino", 10));
//        originList.add(new Caffee("Caffee", 6));
//        Map<String, Double> expected = new HashMap<>();
//        expected.put("Espresso", 8.0);
//        expected.put("Cappuccino", 10.0);
//        expected.put("Caffee", 6.0);
//        Map<String, Double> actual = new MyUtils().averageRating(originList);
//
//        boolean result = expected.equals(actual);

//        List<Caffee> originList = new ArrayList<>();
//        originList.add(new Espresso("Espresso", 8));
//        Map<String, Double> expected = new HashMap<>();
//        expected.put("Espresso", 8.0);
//        Map<String, Double> actual = new MyUtils().averageRating(originList);
//        boolean result = expected.equals(actual);
//        System.out.println(new MyUtils().averageRating(originList));


//        List<Person> people = new ArrayList<>();
//        people.add(new Person("Ivan"));
//        people.add(new Student("Petro", "it",4));
//        people.add(new Worker("Vasia", "it",3));
//        System.out.println(new MyUtils().maxDuration(people));
//        people.add(new Worker("Vasia", "it",4));
//        System.out.println(new MyUtils().maxDuration(people));


        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("pablo",5,new BigDecimal(300.0)));
        employees.add(new Employee("hah",2,new BigDecimal(200.0)));
        employees.add(new Employee("naahh",10,new BigDecimal(100.0)));
        employees.add(new Manager("naahh",11,new BigDecimal(100.0),1.5));
        employees.add(new Manager("naahh",10,new BigDecimal(100.0),1));
        System.out.println(new MyUtils().largestEmployees(employees));

    }
}
