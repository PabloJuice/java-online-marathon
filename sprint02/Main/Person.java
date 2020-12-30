package Main;

import java.util.*;

class Person {
    private String name;

    public String getName() {
        return name;
    }
    public Person(String name){
        this.name = name;
    }
    public Person(){}
}
class Student extends Person {
    private String studyPlace;
    private int studyYears;

    public Student(String name, String studyPlace, int studyYears) {
        super(name);
        this.studyPlace = studyPlace;
        this.studyYears = studyYears;
    }

    public String getStudyPlace() {
        return studyPlace;
    }

    public int getStudyYears() {
        return studyYears;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return studyYears == student.studyYears &&
                Objects.equals(studyPlace, student.studyPlace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studyPlace, studyYears);
    }
}
class Worker extends Person {
    private String workPosition;
    private int experienceYears;

    public Worker(String name, String workPosition, int experienceYears) {
        super(name);
        this.workPosition = workPosition;
        this.experienceYears = experienceYears;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker)) return false;
        Worker worker = (Worker) o;
        return experienceYears == worker.experienceYears &&
                Objects.equals(workPosition, worker.workPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workPosition, experienceYears);
    }
}
//class MyUtils {
//    public List<Person> maxDuration(List<Person> persons) {
//        List<Person> peopleDuration = new ArrayList<>();
//        int maxWork = 0;
//        int maxStudy = 0;
//        for (Person p: persons){
//            if (p != null) {
//                try {
//                    if (((Student)p).getStudyYears() >= maxStudy) {
//                        maxStudy = ((Student) p).getStudyYears();
//                    }
//                } catch (Exception e) {
//                    try {
//                        if (((Worker)p).getExperienceYears() >= maxWork) {
//                            maxWork = ((Worker) p).getExperienceYears();
//                        }
//                    } catch (Exception ignored) {
//                    }
//                }
//            }
//        }
//        for (Person p: persons){
//            if (p != null) {
//                try {
//                    if (((Student)p).getStudyYears() >= maxStudy) {
//                        peopleDuration.add(p);
//                    }
//                } catch (Exception e) {
//                    try {
//                        if (((Worker)p).getExperienceYears() >= maxWork) {
//                            peopleDuration.add(p);
//                        }
//                    } catch (Exception ignored) {
//                    }
//                }
//            }
//        }
//        return peopleDuration;
//    }
//}
//
