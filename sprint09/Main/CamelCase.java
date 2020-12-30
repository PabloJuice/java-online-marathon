package Main;

import java.lang.annotation.ElementType;
import java.lang.reflect.Field;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CamelCase{
}
class CheckCamelCase {
    static boolean isValid = true;
    public static final String CAMELCASE_PATTERN = "([a-z]+[A-Z]?+\\w+)+";
    public static boolean checkAndPrint(Class clazz){
        Arrays.stream(clazz.getDeclaredMethods()).forEach(method -> {
            if (!method.getName().matches(CAMELCASE_PATTERN)) {
                System.out.println("method "+ clazz.getName()+"."+ method.getName() + " doesn't satisfy camelCase naming convention");
                isValid = false;
            }
        });
        return isValid;
    }
}
