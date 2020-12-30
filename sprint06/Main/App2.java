package Main;

import java.util.function.BinaryOperator;
import java.util.ArrayList;
import java.util.List;

public class App2 {

    static BinaryOperator<String> greetingOperator = (String p1, String p2)->{
        return "Hello " + p1 + " " + p2 + "!!!";
    };

    public static List<String> createGreetings(List<Person1> people, BinaryOperator<String> binaryOperator){
        List<String> result = new ArrayList<>();
        for (Person1 p : people){
            result.add(binaryOperator.apply(p.name, p.surname));
        }
        return result;

    }
}
class Person1{
    String name;
    String surname;
    Person1(String name, String surname){
        this.name = name;
        this.surname = surname;
    }
}
