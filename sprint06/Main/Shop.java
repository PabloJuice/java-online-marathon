package Main;

import java.util.ArrayList;
import java.util.List;

class Person{
    String name;

    Person(String name){
        this.name = name;
    }

    DecisionMethod goShopping = (String name, int discount) ->{
        if (name.equals("product1") && discount > 10){
            return true;
        }else {
            return false;
        }
    };
}
@FunctionalInterface
interface DecisionMethod{
    public boolean decide(String name, int discount);
}

class Shop{
    public List<DecisionMethod> clients = new ArrayList<>();

    public int sale(String product, int percent) {
        int result = 0;
        for (DecisionMethod d : clients){
            if (d.decide(product, percent)){
                result++;
            }
        }
        return result;

    }
}
