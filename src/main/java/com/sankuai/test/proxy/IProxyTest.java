package com.sankuai.test.proxy;

import java.lang.reflect.InvocationHandler;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/6/13 21:03
 */
public interface IProxyTest {

    void testPrintWithNoparam();

    void testPrintWithParam(String Name);
}
