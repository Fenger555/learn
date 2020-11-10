package classloader;

/**
 * @Author gaoxing
 * @Date 2020-09-10 10:42
 */
public class MyClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
