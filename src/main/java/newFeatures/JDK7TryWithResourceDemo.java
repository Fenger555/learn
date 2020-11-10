package newFeatures;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * try-with-resources
 *
 * @Author gaoxing
 * @Date 2020-07-27 17:01
 */
public class JDK7TryWithResourceDemo {

    public static void main(String[] args) {

    }

    /**
     * 传统方式
     */
    public static void tradition() {
        InputStream is = null;
        try {
            is = new FileInputStream("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (is!=null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * try-with-resources方式
     */
    public static void tryWithResources() {
        // InputStream实现了AutoCloseable接口，try代码块运行完或发生异常会自动执行close方法
        try(InputStream is = new FileInputStream("")) {
            is.read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
