package designpattern.proxy;

/**
 * @author Fenger
 * @date 2021-02-26 10:25
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.print("hi, ");
    }
}
