package designpattern.proxy;

/**
 * @author Fenger
 * @date 2021-02-26 10:50
 */
public class HelloServiceProxy implements HelloService {

    HelloService helloService;

    public HelloServiceProxy(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public void sayHello() {
        helloService.sayHello();
        System.out.println("xiaomin");
    }
}
