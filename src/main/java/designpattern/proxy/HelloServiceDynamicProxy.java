package designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Fenger
 * @date 2021-02-26 10:26
 */
public class HelloServiceDynamicProxy implements InvocationHandler {

    HelloService helloService;

    public HelloServiceDynamicProxy(HelloService helloService) {
        this.helloService = helloService;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                helloService.getClass().getClassLoader(),
                helloService.getClass().getInterfaces(),
                this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = method.invoke(helloService, args);
        System.out.println("xiaomin");
        return obj;
    }
}
