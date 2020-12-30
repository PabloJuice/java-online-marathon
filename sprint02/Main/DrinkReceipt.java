package Main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

interface DrinkReceipt {
    String getName();
    DrinkReceipt addComponent(String componentName, int componentCount);
}
interface DrinkPreparation {
    Map<String, Integer> makeDrink();
}
interface Rating {
    int getRating();
}
class Caffee implements DrinkReceipt, DrinkPreparation, Rating {
    private Map<String, Integer> ingredients = new HashMap<>();
    private String name;
    private int rating;

    public Caffee(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    @Override
    public String getName() {
        return name;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }
    @Override
    public DrinkReceipt addComponent(String componentName, int componentCount) {
        ingredients.put(componentName,componentCount);
        return this;
    }

    @Override
    public Map<String, Integer> makeDrink() {
        addComponent("Water", 100).addComponent("Arabica", 20);
        return ingredients;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Caffee)) return false;
        Caffee caffee = (Caffee) o;
        return rating == caffee.rating &&
                Objects.equals(ingredients, caffee.ingredients) &&
                Objects.equals(name, caffee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredients, name, rating);
    }
}
class Espresso extends Caffee {


    public Espresso(String name, int rating) {
        super(name, rating);
    }

    @Override
    public Map<String, Integer> makeDrink() {
        addComponent("Water", 50).addComponent("Arabica", 20);
        return getIngredients();
    }

}
class Cappuccino extends Caffee {
    public Cappuccino(String name, int rating) {
        super(name, rating);
    }
    @Override
    public Map<String, Integer> makeDrink() {
        addComponent("Water", 100).addComponent("Arabica", 20).addComponent("Milk", 50);
        return getIngredients();
    }
}
class MyUtils0 {
    public Map<String, Double> averageRating(List<Caffee> coffees) {
        if(coffees != null) {
            if (!coffees.isEmpty()) {
                Map<String, Double> result = new HashMap<>();
                for (Caffee c : coffees) {
                    if (c != null) {
                        if (result.containsKey(c.getName()) && result.get(c.getName()) != 0.0) {
                            result.replace(c.getName(), (((double) c.getRating()) + result.get(c.getName())) / 2);
                        } else {
                            result.put(c.getName(), 0.0);
                            result.replace(c.getName(), (double) c.getRating());
                        }
                    }
                }

                return result;
            } else {
                return new HashMap<>();
            }
        }else {
            return null;
        }
    }
}