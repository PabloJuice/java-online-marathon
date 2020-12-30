package Main;


class ClassForAnnot {
    @CamelCase
    public static void example() {
    }
    public void inCorrect(){}
    public void JustMethod(){}

    @CamelCase
    public void Example() {
    }

    public static void _main(String args[]) {
    }

}
class Class1{
    @CamelCase
    public void correct(){}
    @CamelCase
    public void InCorrect(){}
    @CamelCase
    public void JustMethod(){}
}

class Class2{
    @CamelCase
    public void correct(){}
    @CamelCase
    public void oneMoreCorrect(){}
}

