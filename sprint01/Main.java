package Main;

public class Main {
    public static void main(String[] args) {
       Employee emp1 = new Employee();
       Employee emp2 = new Employee();
       emp1.fullName = "Andrew Ivanov";
       emp1.salary = 10f;
       emp2.fullName = "Ivan Stepanov";
       emp2.salary = 20f;
       Employee[] employees = {emp1, emp2};
       String employeesInfo = "[";
       for (Employee e: employees) {
           employeesInfo += ("{fullName: \"" + e.fullName + "\", salary: " + e.salary + "}");
           if (e == employees[employees.length-1]){
               employeesInfo += "]";
           }else{
               employeesInfo += ", ";
           }
       }
       System.out.println(employeesInfo);

}
