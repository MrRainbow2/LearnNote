package com.sankuai.test.annotation.impl;

import com.sankuai.test.annotation.annotation.MethodAnnotation;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/1/18 16:18
 */
public class AnnotationTest {

    @MethodAnnotation(value = "DaMing")
    public void sayHello(String name){
        System.out.println(String.format("hello,%s",name));
    }
}
