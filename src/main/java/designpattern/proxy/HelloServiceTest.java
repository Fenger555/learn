package designpattern.proxy;

/**
 * @author Fenger
 * @date 2021-02-26 10:31
 */
public class HelloServiceTest {

    public static void main(String[] args) {

        HelloService helloService = new HelloServiceImpl();
        helloService.sayHello();

        // 动态代理增强
        HelloService helloServiceDynamicProxy = (HelloService) new HelloServiceDynamicProxy(helloService).getProxyInstance();
        helloServiceDynamicProxy.sayHello();

        // 静态代理增强
        HelloServiceProxy helloServiceProxy = new HelloServiceProxy(helloService);
        helloServiceProxy.sayHello();

    }
}
