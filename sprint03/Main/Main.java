package Main;

public class Main {

    public static void main(String[] args) {

        System.out.println(drawLine(LineType.DASHED));
    }

    public static String drawLine(LineType lineType) {

        return ("The line is " + lineType.name().toLowerCase() + " type");

    }

}
