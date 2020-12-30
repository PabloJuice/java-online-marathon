package Main;

import java.math.BigDecimal;
import java.util.*;

class Employee {
    private String name;
    private int experience;
    private BigDecimal basePayment;

    public Employee(String name, int experience, BigDecimal basePayment) {
        this.name = name;
        this.experience = experience;
        this.basePayment = basePayment.setScale(2);
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public BigDecimal getPayment() {
        return basePayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return experience == employee.experience &&
                Objects.equals(name, employee.name) &&
                Objects.equals(basePayment, employee.basePayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, experience, basePayment);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", experience=" + experience +
                ", basePayment=" + basePayment +
                '}';
    }
}
class Manager extends Employee {
    private double coefficient;
    public Manager(String name, int experience, BigDecimal basePayment, double coefficient) {
        super(name, experience,basePayment);
        this.coefficient = coefficient;
    }
    public BigDecimal getPayment() {
        return super.getPayment().multiply(BigDecimal.valueOf(getCoefficient())).setScale(2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager)) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return Double.compare(manager.coefficient, coefficient) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coefficient);
    }

    public double getCoefficient() {
        return coefficient;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + super.getName() + '\'' +
                ", experience=" + super.getExperience() +
                ", basePayment=" + super.getPayment() +
                "coefficient=" + coefficient +
                '}';
    }
}
class MyUtils {
    public List<Employee> largestEmployees(List<Employee> workers) {
        List<Employee> largestEmployees = new ArrayList<>();

        if (workers!=null && !workers.isEmpty()){
            Employee emaxE = new Employee("0",0,new BigDecimal(0));
            Employee emaxP = new Employee("0",0,new BigDecimal(0));
            Manager mmaxE = new Manager("0",0,new BigDecimal(0),0);
            Manager mmaxP = new Manager("0",0,new BigDecimal(0),0);
            for (Employee e : workers) {

                if (e != null){
                    try {
                        Manager m = (Manager) e;
                        if (m.getExperience() > mmaxE.getExperience()) {
                            mmaxE = m;
                        } else if (m.getPayment().compareTo(mmaxP.getPayment()) >= 0) {
                            mmaxP = m;
                        }
                    } catch (Exception exception) {
                        if (e.getExperience() >= emaxE.getExperience()) {
                            emaxE = e;
                        } else if (e.getPayment().compareTo(emaxP.getPayment()) >= 0) {
                            emaxE = e;
                        }
                    }
                }
            }
            for (Employee e : workers){
                if (e != null) {
                    try {
                        Manager m = (Manager) e;
                        if (m.getExperience() == mmaxE.getExperience() || m.getPayment().compareTo(mmaxP.getPayment()) == 0) {
                            largestEmployees.add(e);
                        }
                    } catch (Exception exception) {
                        if (e.getExperience() == emaxE.getExperience() || e.getPayment().compareTo(emaxP.getPayment()) == 0) {
                            largestEmployees.add(e);
                        }
                    }
                }
            }
        }return largestEmployees;
    }
}
