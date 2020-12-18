package clazz.extend;

/**
 * @Author gaoxing
 * @Date 2020-12-08 10:45
 */
public class ExtendsClassLoaderChild extends ExtendsClassLoader {

    static {
        System.out.println("child static");
    }

    public ExtendsClassLoaderChild() {
        super();
        System.out.println("child init");
    }

    public static void main(String[] args) {
        ExtendsClassLoaderChild extendsClassLoaderChild = new ExtendsClassLoaderChild();
    }
}
