package Main;

import java.lang.annotation.*;
import java.lang.reflect.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface TestSuite{
    String[] value();
}
class TestSuitHandler {
    public static void run(Class<?> clazz){

    }
}

