package Main;

class NameList {
    private String[] names = {"Mike", "Emily", "Nick", "Patric", "Sara"};

    public Iterator getIterator() {
        return new Iterator();
    }

    public class Iterator{
        private int counter = 0;
        private Iterator(){}

        public boolean hasNext(){
            return counter < names.length;
        }
        public String next(){
            if (hasNext()){
                counter++;
                return names[counter-1];
            }
            else {
                return "error";
            }
        }

    }

}
