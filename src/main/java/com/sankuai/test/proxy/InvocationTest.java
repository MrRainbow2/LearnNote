package com.sankuai.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/6/13 21:08
 */
public class InvocationTest implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("MethodName:" + method.getDeclaringClass() + method.getName());
        System.out.println("Args:" + args);
        preOperation();
        method.invoke(String.valueOf(args));
        afterOperation();
        return null;
    }

    private void preOperation() {
        System.out.println("Proxy PreOperation");
    }

    private void afterOperation() {
        System.out.println("Proxy afterOperation");
    }
}
