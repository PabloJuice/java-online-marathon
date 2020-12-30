package Main;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Review {
    String reviewer();
    String date() default "today";
}
@SuppressWarnings("unchecked")
class Util{
    public static void review(String className){
        try{
            Class myClass = Class.forName(className);
            if (myClass.isAnnotationPresent(Review.class)){
                LocalDate date = myClass.getAnnotation(Review.class).toString().split("\"")[1].equals("today") ? LocalDate.now():LocalDate.parse(myClass.getAnnotation(Review.class).toString().split("\"")[1]);
                System.out.println("Class " + className + " was reviewed "+ date + " by " + myClass.getAnnotation(Review.class).toString().split("\"")[3] + ".");
            }
            else {
                System.out.println("Class "+ className +" isn't marked as Reviewed");
            }
        }catch (ClassNotFoundException classNotFoundException){
            System.out.println("Class "+ className +" was not found");
        }
    }
}