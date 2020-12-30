package Main;

import java.util.*;
import java.util.stream.Stream;

public class MyUtils3 {
    public Map<String, Stream<String>> phoneNumbers(List<Stream<String>> list) {
        Map<String, Stream<String>> result = new TreeMap<>();
        list.stream()
                .filter(Objects::nonNull)
                .forEach(str -> str.filter(s -> (s != null) && (!s.isEmpty()))
                        .forEach(number ->{
                                String currentNumber ="";
                                String code;
                                for (Character c : number.toCharArray()){
                                    if (Character.isDigit(c)){
                                        currentNumber += c;
                                    }
                                }

                                if (currentNumber.length() == 10 && currentNumber.startsWith("0")){
                                    code = currentNumber.substring(0, 3);
                                    currentNumber = currentNumber.substring(3);
                                }else {
                                    if (currentNumber.length() == 7){
                                        code = "loc";
                                    }
                                    else {
                                        code = "err";
                                    }
                                }
                                if (!currentNumber.equals("") && !currentNumber.isEmpty()){
                                    if (!result.containsKey(code)){
                                        result.put(code,Stream.of(currentNumber));
                                    }
                                    else {
                                        result.put(code, Stream.concat(result.get(code), Stream.of(currentNumber)).distinct().sorted());
                                    }
                                }
                        }));
        return result;
    }
}