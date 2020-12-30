package Main;

import java.util.*;

public class MyUtils {
    ///////////        1         //////////////
    public Map<String, List<String>> createNotebook(Map<String, String> phones) {
        Map<String, List<String>> result = new HashMap<>();
        if (!phones.isEmpty()){
            Set<String> uniqueNames = new HashSet<>();
            for (Object s : phones.values()){
                if (uniqueNames.add((String)s)){
                    result.put((String) s, new ArrayList<>());
                }
            }
            for (Object p : phones.keySet()){
                String o = (String)p;
                result.get(phones.get(o)).add(o);
            }
        }
        return result;
    }
    ///////////        2         //////////////
    public static class Student{
        private Integer id;
        private String name;
        public Student(int id, String name){
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Student)) return false;
            Student student = (Student) o;
            return Objects.equals(id, student.id) &&
                    Objects.equals(name, student.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
    public Set<Student> commonStudents(List<Student> list1, List<Student> list2) {
        Set<Student> duplicates = new LinkedHashSet<>();
        Set<Student> uniques = new HashSet<>();
        uniques.addAll(list1);
        for (Student s : list2){
            if (!uniques.add(s)){
                duplicates.add(s);
            }
        }
        return duplicates;
    }
    ///////////        3         //////////////
    public boolean listMapCompare(List<String> list, Map<String, String> map) {
        return list.containsAll(map.values());
    }

}
