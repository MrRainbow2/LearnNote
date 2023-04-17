package com.sankuai.test.annotation.annotation;

//import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.*;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/1/18 16:00
 * @Retention 表示被该注解修饰的对象的生命周期。SOURCE:表示在源文件中有效、CLASS:表示在class文件中有效，RUNTIME:表示在运行时有效
 * @Inherited 阐述了某个被标注的类型是被继承的，如果一个class被 Inherited annotation修饰，则这个annotation将作用于这个这个class的子类
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodAnnotation {
    String value() default "";
}
