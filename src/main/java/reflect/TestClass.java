package reflect;

/**
 * @author Fenger
 * @date 2021-03-02 10:42
 */
public class TestClass {

    public static String STATIC_VAL = "static val";

    static {
        System.out.println("static code ");
    }

    public static void staticMethod() {
        System.out.println("static method");
    }

}
