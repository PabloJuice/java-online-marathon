package Main;

import java.util.Objects;

class NameException extends RuntimeException{
    public NameException(String message){
        super(message);
    }
}
class CodeException extends RuntimeException{
    public CodeException(String message){
        super(message);
    }
}
public class Person {
    private String firstName;
    private String lastName;
    private String idCode;
    private Person(String firstName, String lastName, String idCode){
        if (firstName != null && lastName != null && idCode != null) {
            String message = "";
            try{
                this.setFirstName(firstName);
                this.setLastName(lastName);
            }catch (NameException e){
                message+=e.getMessage()+"; ";
            }
            try{
                this.setIdCode(idCode);
            }catch (CodeException e){
                message+=e.getMessage();
            }finally {
                if(message.length()>0){
                    throw new IllegalArgumentException(message);
                }
            }

        }
    }
    public Person(){}
    public static Person buildPerson(String firstName, String lastName, String idCode){
        return new Person(firstName, lastName, idCode);
    }

    public void setFirstName(String firstName) throws NameException{
        if (firstName != null){
            nameChecker(firstName);
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName)  throws NameException{
        if (lastName != null){
            nameChecker(lastName);
            this.lastName = lastName;
        }
    }

    public void setIdCode(String idCode) throws CodeException {
        if (idCode != null){
            idChecker(idCode);
            this.idCode = idCode;
        }

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIdCode() {
        return idCode;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ": " + idCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(idCode, person.idCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, idCode);
    }
    public void nameChecker(String name) throws NameException{
        if (name.substring(0,1).equals(name.substring(0,1).toLowerCase())){
            throw new NameException("Incorrect value "+ name +" for firstName (should start from upper case and contains only alphabetic characters and symbols -, _)");
        }else {
            for (Character c: name.toCharArray()){
                if (String.valueOf(c).equals(" ") || String.valueOf(c).equals("-") || Character.isDigit(c) || String.valueOf(c).equals("_")){
                    throw new NameException("Incorrect value "+ name +" for firstName (should start from upper case and contains only alphabetic characters and symbols -, _)");
                }
            }
        }
    }
    public void idChecker(String id) throws CodeException{
        if (id.length() != 10){
            throw new CodeException("Incorrect value " + id + " for code (should contains exactly 10 digits)");
        }
        else {
            for (Character c : id.toCharArray()){
                if (!Character.isDigit(c)){
                    throw new CodeException("Incorrect value " + id + " for code (should contains exactly 10 digits)");
                }
            }
        }

    }
}
