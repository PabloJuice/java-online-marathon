package Main;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
//        writeFile("G:\\JvMrthn\\sprint07\\src\\Main\\str.txt", "Hellooooa");
//        System.out.println(readFile("G:\\JvMrthn\\sprint07\\src\\Main\\str.txt"));
//        Map<String, Stream<String>> map = new HashMap<String, Stream<String>>();
//        map.put("Desktop", Stream.of(" iVan", "PeTro ", " Ira "));
//        map.put("Web", Stream.of("STepan", "ira ", " Andriy ", "an na"));
//        map.put("Spring", Stream.of("Ivan", "Anna"));
//        System.out.println(new MyUtils2().nameList(map));

//        Map<String, Stream<String>> streamMap = new HashMap<>();
//        streamMap.put("TEST", Stream.of("   ", "  ", "      ", ""));
//        streamMap.put("TEST2", Stream.of("", "", "", ""));
//        streamMap.put("Desktop", Stream.of("iVan", "PeTro", " Ira"));
//        streamMap.put("Web", Stream.of("STepan", "ira", "Andriy", "an na"));
//        streamMap.put("Spring", Stream.of("iVan", "Anna"));
//        streamMap.put(null, Stream.of(null, "Anna999999999", "Anna"));
//        streamMap.put("test", null);
//
//        MyUtils2.nameList(streamMap).forEach(System.out::println);
        List<Stream<String>> list = new ArrayList<>();
//        list.add(Stream.of("093 987 65 43", "(050)1234567", "12-345"));
        list.add(Stream.of("093 987 65 43",  "0939182736", "(093)-11-22-334", "(093)-11-22-334", " "));
        new MyUtils3().phoneNumbers(list).forEach((lizt, lol) -> lol.forEach(System.out::println));

//        new MyUtils4().duplicateElements(Stream.of(-2, 1, null, -12, -3, -8, -2, -3, null, -2, -1, 1)).forEach(System.out::println);

    }
    public static boolean isLeapYear(int year) {
        if (java.time.Year.of(year).isLeap()){
            return true;
        }
        else{
            return false;
        }
    }
    public static void writeFile(String filename, String text) {
        try {
            FileWriter fw = new FileWriter(filename);
            for (char c : text.toCharArray()){
                try {
                    String str = Integer.toBinaryString(c);
                    while (str.length() < 7){
                        str = "0"+ str;
                    }
                    fw.write(str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String readFile(String filename) {
        String result = "";
        try {
            FileReader fr = new FileReader(filename);
            String code = "";
            int c = 0;
            while ((c = fr.read()) != -1){
                code+=(char)c;
            }
            String[] codeArr = new String[code.toCharArray().length/7];
            for (int i = 0; i < code.toCharArray().length; i++) {
                if (codeArr[i/7] == null){
                    codeArr[i/7] = String.valueOf(code.toCharArray()[i]);
                }
                else {
                    codeArr[i/7] += code.toCharArray()[i];
                }
            }
            for (String s:codeArr) {
                result += Character.toString((char) Integer.parseInt("0"+s,2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
