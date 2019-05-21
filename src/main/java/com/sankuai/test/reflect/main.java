package com.sankuai.test.reflect;

import java.util.ArrayList;
import java.util.List;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2018/12/27 14:53
 */
public class main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String plusClassName = "com.sankuai.test.reflect.PlusCalculator";
        String subClassName = "com.sankuai.test.reflect.SubCalculator";
        String defaultClassName = "com.sankuai.test.reflect.DefaultCalculator";
        Class plus_clazz = Class.forName(plusClassName);
        Class sub_clazz = Class.forName(subClassName);
        Class default_clazz = Class.forName(defaultClassName);
        ICalculator plus_calculator = (ICalculator) plus_clazz.newInstance();
        ICalculator sub_calculator = (ICalculator) sub_clazz.newInstance();
        ICalculator default_calculator = (ICalculator) default_clazz.newInstance();
        List<ICalculator> calculatorList = new ArrayList<>();
        calculatorList.add(plus_calculator);
        calculatorList.add(default_calculator);
        calculatorList.add(sub_calculator);

        calculatorList.forEach(calculator -> {
            printCalName(calculator);
            printCalResult(calculator, 10, 20);
        });
    }

    public static void printCalResult(ICalculator calculator, int var1, int var2) {
        System.out.println(calculator.cal(var1, var2));
    }

    public static void printCalName(ICalculator calculator) {
        System.out.println(calculator.getName());
    }
}
