package Main;

import java.util.*;

abstract class Shape {
    private String name;

    public String getName() {
        return name;
    }

    public Shape(String name) {
        this.name = name;
    }

    public Shape(){}

    abstract double getArea();
}
class Circle extends Shape {
    private double radius;

    public double getRadius() {
        return radius;
    }

    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double getArea() {
        return radius*radius* Math.PI;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circle)) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius);
    }
}
class Rectangle extends Shape {
    private double height;
    private double width;

    public Rectangle(String name, double height, double width) {
        super(name);
        this.height = height;
        this.width = width;
    }

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle)) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.height, height) == 0 &&
                Double.compare(rectangle.width, width) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width);
    }

    @Override
    double getArea() {
        return height*width;
    }
}
//class MyUtils {
//    public List<Shape> maxAreas(List<Shape> shapes) {
//        List<Shape> result = new ArrayList<>();
//        if (shapes != null && !shapes.isEmpty()){
//            Circle maxCircle = new Circle(0);
//            Rectangle maxRectangle = new Rectangle(0,0);
//            for (Shape s : shapes){
//                try {
//                    if (s.getArea() >= maxCircle.getArea()){
//                        maxCircle = (Circle)s;
//                        result.add(s);
//                    }
//                }catch (Exception e){
//                    if (s.getArea() >= maxRectangle.getArea()){
//                        maxRectangle = (Rectangle)s;
//                        result.add(s);
//                    }
//                }
//            }
//        }
//        return result;
//    }
//}

