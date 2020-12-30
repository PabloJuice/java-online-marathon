package Main;

import java.util.*;
import java.util.stream.Stream;

public class MyUtils2 {
    public static Stream<String> nameList(Map<String, Stream<String>> map) {
        if (map != null){
            Set<String> result = new TreeSet<>();
            map.values().stream().filter(Objects::nonNull).forEach(str -> {
                str.filter(Objects::nonNull).forEach(name -> {
                    String string = "";
                    for (Character c: name.toLowerCase().toCharArray()) {
                        if (Character.isAlphabetic(c)){
                            string += c;
                        }
                    }
                    if (!string.isEmpty()){
                        result.add(String.valueOf(string.toCharArray()[0]).toUpperCase() + string.substring(1));
                    }
                });
            });
            return result.stream();
        }
        else throw new NullPointerException();
    }
}
