package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Fenger
 * @date 2021-03-02 10:29
 */
public class ClassfornameDemo {

    public static void main(String[] args) {
        try {
            // Class forname在加载类的时候会对类初始化，因为设置了initialize参数为true
            // forName0(className, true, ClassLoader.getClassLoader(caller), caller);
            Class<?> clazz = Class.forName("reflect.TestClass");
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
