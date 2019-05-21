package TestCodeFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2018/4/16 15:41
 */
public class FilterTest {
    /**
     * 检测Param是否符合条件
     *
     * @param param
     * @return
     */
    public static List<Apple> filter(List<Apple> param, IApplePredict cal) {
        List<Apple> resultList = new ArrayList<>();
        param.forEach((apple) -> {
            if (cal.test(apple)) {
                resultList.add(apple);
            }
        });
        Callable<String> s;
        return resultList;

    }

    public static void main(String[] args) {
        Apple red = new Apple("red", 155);
        Apple green = new Apple("green", 145);
        List<Apple> paramList = new ArrayList() {{
            add(red);
            add(green);
        }};

        List<Apple> resultOfWeight = filter(paramList, (Apple apple) -> !"red".equals(apple.getColor()));
        System.out.println(resultOfWeight.size());
        System.out.println(resultOfWeight);
    }
}
