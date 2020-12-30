package Main;

public class Person1 {
    int age;
    String healthInfo;
    String name;
    String getHealthStatus(){ return name +" " + healthInfo; }
    public Person1(int age, String healthInfo, String name){
        this.age = age;
        this.healthInfo = healthInfo;
        this.name = name;
    }
}

class Child extends Person1{
    String childIDNumber;

    public Child(int age, String healthInfo, String name, String childIDNumber) {
        super(age, healthInfo, name);
        this.childIDNumber = childIDNumber;
    }
}

class Adult extends Person1{
    String passportNumber;

    public Adult(int age, String healthInfo, String name, String passportNumber) {
        super(age, healthInfo, name);
        this.passportNumber = passportNumber;
    }
}