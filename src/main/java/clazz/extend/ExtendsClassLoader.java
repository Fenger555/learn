package clazz.extend;

/**
 * @Author gaoxing
 * @Date 2020-12-08 10:45
 */
public class ExtendsClassLoader {

    static {
        System.out.println("parent static");
    }

    public ExtendsClassLoader() {
        System.out.println("parent init");
    }
}
