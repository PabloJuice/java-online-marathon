package TheSix;

import java.util.Arrays;
import java.util.Iterator;

enum SortOrder{
    ASC,DESC;
}

class AddressBook implements Iterable{
    private int counter = 0;
    private NameAddressPair[] addressBook;

    public AddressBook(int capacity){

    }
    public boolean create(String firstName, String lastName, String address){
        return true;
    }
    public String read(String firstName, String lastName){
        return new String();
    }
    public boolean update(String firstName, String lastName, String address){
        return true;
    }
    public boolean delete(String firstName, String lastName){
        return true;
    }
    public int size(){
        return 0;
    }
    public void sortedBy(SortOrder order){
        if (order == SortOrder.ASC){
            Arrays.sort(addressBook);
        }
        else {
            Arrays.sort(addressBook);
        }
    }
    public Iterator iterator(){
        return new AddressBookIterator();
    }

    private static class NameAddressPair{
        private Person person;
        private String address;
        private NameAddressPair(Person person, String address){
            this.person = person;
            this.address = address;
        }
        private static class Person{
            private String firstName;
            private String lastName;
            private Person(String firstName, String lastName){
                this.firstName = firstName;
                this.lastName = lastName;
            }
        }
    }


    private class AddressBookIterator implements Iterator {
        private int counter = 0;
        public boolean hasNext(){
            if (counter <= addressBook.length-1){
                return true;
            }
            else {
                return false;
            }
        }
        public String next(){
            if (hasNext()){
                counter++;
                return addressBook[counter-1].toString();
            }
            else {
                return "error";
            }
        }
    }
}
