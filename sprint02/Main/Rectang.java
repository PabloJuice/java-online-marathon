package Main;

import java.util.ArrayList;
import java.util.List;

class Square {
    double width;
    double height;
    public Square(double width){
        this.width = width;
        this.height = width;
    }
    public Square(){}
    public double getPerimeter(){
        return (width+height)*2;
    }
}

class Rectang extends Square{

    public Rectang(double width, double height){
        this.width = width;
        this.height = height;
    }
    public double getPerimeter(){
        return (width+height)*2;
    }
}

//class MyUtils {
//    public double sumPerimeter(List<?> firures) {
//        double result = 0.0;
//        for (Object s:firures) {
//            if (s != null){
//                result+=((Square)s).getPerimeter();
//            }
//        }
//        return result;
//    }
//}
