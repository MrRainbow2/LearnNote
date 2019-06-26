package com.sankuai.test.proxy.impl;

import com.sankuai.test.proxy.IProxyTest;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/6/13 21:05
 */
public class ProxyTestImpl implements IProxyTest {
    @Override
    public void testPrintWithNoparam() {
        System.out.println("我是ProxyTest");
    }

    @Override
    public void testPrintWithParam(String Name) {
        System.out.println(String.format("我是%s", Name));
    }
}
