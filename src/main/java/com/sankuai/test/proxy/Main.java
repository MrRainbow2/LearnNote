package com.sankuai.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/6/13 21:06
 */
public class Main{

        public static void main(String[] args) {
            InvocationHandler handler = new InvocationTest();
            IProxyTest test = (IProxyTest) Proxy.newProxyInstance(IProxyTest.class.getClassLoader(),new Class[]{IProxyTest.class},handler);
            test.testPrintWithParam("ProxyTest2");
        }
}
