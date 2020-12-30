package Main;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyUtils4 {
    public Stream<Integer> duplicateElements(Stream<Integer> stream) {
        if (stream != null){
            Set<Integer> items = new HashSet<>();
            return stream.filter(Objects::nonNull).filter(n -> !items.add(n)).collect(Collectors.toSet()).stream().sorted();
        }
        return null;
    }
}
