public class Test {
    Object foo;
    Test bar;

    void test() {
        Test t = null;
        t.foo = null;
        t.bar = null;

        t = null;

        this.test();
    }
    
    public static void main(String[] args) {
        System.out.println("Test Main Class");
    }
}
