package reflect;

import org.apache.tools.ant.taskdefs.Classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Fenger
 * @date 2021-03-02 10:29
 */
public class ClassloaderDemo {

    public static void main(String[] args) {
        try {
            // 使用ClassLoader加载类时不会对类初始化，只是把类加载到JVM中
            Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass("reflect.TestClass");
            System.out.println("a");
            Method sayHelloMethod = clazz.getMethod("staticMethod");
            Object helloService = clazz.newInstance();
            sayHelloMethod.invoke(helloService);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
